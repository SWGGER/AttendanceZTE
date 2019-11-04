package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.vo.ControllerResult;
import com.zzxmh.userservice.vo.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class VerifyController {
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping(value="/getImage",method=RequestMethod.GET)
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //将字符串存入redis
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        jedis.set("verCode", verifyCode);
        jedis.close();
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }

    @RequestMapping(value="alidImage",method=RequestMethod.POST)
    public Object validImage(@RequestBody String valid_info){
        ControllerResult result=new ControllerResult();
        result.setCode(DataSourceEnum.NOFILE.getCode());
        result.setMsg(DataSourceEnum.NOFILE.getMsg());
        Map<String, Object> data = JSONObject.parseObject(valid_info);
        String code = (String) data.get("code");
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        String verCodeStr=jedis.get("verCode");
        jedis.close();
        if (code.equals(verCodeStr)) {
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            return result;
        }else {
            return result;
        }
    }

}

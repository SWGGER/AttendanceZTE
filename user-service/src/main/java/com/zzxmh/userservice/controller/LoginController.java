package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.user.User;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.user.UserService;
import com.zzxmh.userservice.vo.ControllerResult;
import com.zzxmh.userservice.vo.YYJHTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/login")
    public Object loginShiro(@RequestBody String login_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject = JSONObject.parseObject(login_info);
        Map<String, Object> data = (Map<String, Object>) jsonObject;
        String userId = (String) data.get("userId");
        String password = (String) data.get("password");
        if(userId!="") {
            User user = userService.getuserInfoByLoginId(userId);
            if (user != null){
                if (user != null && user.getPassword().equals(password)) {
                    // 2、登录成功后生成token。Token相当于原来的jsessionid，字符串，可以使用uuid。
                    YYJHTools yyjhTools=new YYJHTools();
                   // String token = yyjhTools.get32UUID();
                    // 3、把用户信息保存到redis。Key就是token，value就是TbUser对象转换成json。
                    // 4、使用String类型保存Session信息。可以使用“前缀:token”为key
                    Jedis jedis = null;
                    jedis = jedisPool.getResource();
                    jedis.set("user_one",userId);
                    // 5、设置key的过期时间。模拟Session的过期时间。一般半个小时。
//                    jedis.expire("user_one", 1800);
                    jedis.close();
//                    Cookie cookie = new Cookie("token", token);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
                    //将用户id和token返回到前台
                    Map<String,Object> returnmap = new HashMap<>();
                    returnmap.put("userId",userId);
                    result.setCode(DataSourceEnum.SUCCESS.getCode());
                    result.setMsg(DataSourceEnum.SUCCESS.getMsg());
                    result.setPayload(returnmap);
                    return result;
                }else {
                    result.setPayload("errorpassword");
                    return result;
                }

            }else {
                result.setPayload("norigister");
                return result;
            }
        }
        result.setPayload("nouser");
        //调用logout后通过setLoginUrl重新进入该方法
        return result;
    }
}

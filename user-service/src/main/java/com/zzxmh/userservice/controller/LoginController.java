package com.zzxmh.userservice.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.user.User;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.user.UserService;
import com.zzxmh.userservice.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
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
                    Map<String,Object> returnmap = new HashMap<>();
                    returnmap.put("userId",userId);
//                    //添加用户认证信息
//                    Subject subject = SecurityUtils.getSubject();
//                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginid, password);
//                    //进行验证。这里可以捕捉异常，然后返回对应信息
//                    subject.login(usernamePasswordToken);
//                    System.out.println("loginShiro:" + usernamePasswordToken);
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
//        return "";
    }
}

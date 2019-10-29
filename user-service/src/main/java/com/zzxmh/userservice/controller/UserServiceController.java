package com.zzxmh.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserServiceController {
    @RequestMapping("userservice")
    public Object userservice(){
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        return map;
    }
}

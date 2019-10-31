package com.zzxmh.userservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/router")
public class RouterController {


    @RequestMapping(value = "/index")
    public String index(){
        return "datasource/index";
    }
}

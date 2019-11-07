package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.domain.employee.Base_info;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.dept.DeptService;
import com.zzxmh.userservice.service.employee.Base_infoService;
import com.zzxmh.userservice.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
@Autowired
private Base_infoService base_infoService;
@Autowired
private DeptService deptService;

    @RequestMapping("/fuzzyselectNameAndId")
    public Object fuzzyselectNameAndId (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String searchinfo=map.get("data").toString();
        List<Base_info> list=base_infoService.fuzzyselectNameandId(searchinfo);
        List<String> userlist=new ArrayList<>();
        String userinfo="";
        if (list!=null){
            for(int i=0;i<list.size();i++){
                userinfo=list.get(i).geteName()+"@"+list.get(i).getUserId();
                userlist.add(userinfo);
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(userlist);
        }
        return result;
    }

    @RequestMapping("/fuzzyselectDeptnameAndLoc")
    public Object fuzzyselectDeptnameAndLoc (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String searchinfo=map.get("data").toString();
        List<Dept> list=deptService.fuzzyselectDeptnameAndLoc(searchinfo);
        List<String> userlist=new ArrayList<>();
        String userinfo="";
        if (list!=null){
            for(int i=0;i<list.size();i++){
                userinfo=list.get(i).getDeptName()+" "+list.get(i).getDeptLoc();
                userlist.add(userinfo);
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(userlist);
        }
        return result;
    }

}

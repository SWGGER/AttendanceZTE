package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.dept.DeptService;
import com.zzxmh.userservice.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/recheck")
    public Object recheckDept(@RequestBody String nameandloc){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(nameandloc);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        Dept dept=new Dept();
        dept.setDeptName(map.get("deptName").toString());
        dept.setDeptLoc(map.get("deptLoc").toString());
        boolean check_resut=deptService.recheckDept(dept);
        if(check_resut){
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            return result;
        }else{
            result.setCode(DataSourceEnum.NOFILE.getCode());
            result.setMsg(DataSourceEnum.NOFILE.getMsg());
            return result;
        }
    }
    @RequestMapping("/insertDept")
    public Object insertSelective(@RequestBody String dept_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(dept_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        Dept dept=new Dept();
        dept.setDeptName(map.get("deptName").toString());
        dept.setDeptLoc(map.get("deptLoc").toString());
        dept.setDeptPrefix(map.get("deptPrefix").toString());
        if(null!=map.get("deptFunction") && map.get("deptFunction")!=""){
            dept.setDeptFunction(map.get("deptFunction").toString());
        }
        boolean check_resut=deptService.insertSelective(dept);
        if(check_resut){
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            return result;
        }else{
            return result;
        }
    }
}

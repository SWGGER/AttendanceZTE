package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.domain.dept.Dept_role;
import com.zzxmh.userservice.domain.dept.Dept_role_permission;
import com.zzxmh.userservice.domain.dept.Role;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.dept.DeptService;
import com.zzxmh.userservice.service.dept.Dept_roleService;
import com.zzxmh.userservice.service.dept.Dept_role_permissionService;
import com.zzxmh.userservice.service.dept.RoleService;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private Dept_roleService dept_roleService;
    @Autowired
    private Dept_role_permissionService dept_role_permissionService;
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
        //判断添加dept成功
        boolean dept_insert=false;
        //判断添加dept_role成功
        boolean dept_role_insert=false;
        //判断添加dept_role_permission成功
        boolean dept_role_permission_insert=false;
        JSONObject jsonObject=JSONObject.parseObject(dept_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        Dept dept=new Dept();
        dept.setDeptName(map.get("deptName").toString());
        dept.setDeptLoc(map.get("deptLoc").toString());
        dept.setDeptPrefix(map.get("deptPrefix").toString());
        if(null!=map.get("deptFunction") && map.get("deptFunction")!=""){
            dept.setDeptFunction(map.get("deptFunction").toString());
        }
        dept_insert=deptService.insertSelective(dept);
        Dept_role dept_role=new Dept_role();
        //将新插入的职位id与职位名拿出
        Dept dept_lastData=deptService.selLastData();
        String dept_pre_name=dept_lastData.getDeptName();
        String nex="总监";
        String dept_name=dept_pre_name+nex;
        //查询该职位是否已存在总监,返回总监id或者null
        Integer role_exist_id=roleService.RoleNameExistRole(dept_name);
        //不存在总监情况
        if(null==role_exist_id){
            //插入总监
            Role role=new Role();
            role.setRoleName(dept_name);
            roleService.insertSelective(role);
            //查最新插入的总监的id
            Integer role_LastData=roleService.RoleLastData();
            //完善dept_role表
            dept_role.setDeptId(dept_lastData.getDeptId());
            dept_role.setRoleId(role_LastData);
            dept_role_insert=dept_roleService.insertSelective(dept_role);
        }else{
            //总监存在情况，完善dept_role表
            dept_role.setDeptId(dept_lastData.getDeptId());
            dept_role.setRoleId(role_exist_id);
            dept_role_insert=dept_roleService.insertSelective(dept_role);
        }
        //给该职位设置等级
        Dept_role_permission dept_role_permission=new Dept_role_permission();
        dept_role_permission.setDeptRoleId(dept_roleService.selLastData());
        dept_role_permission.setPermissionId(1);
        dept_role_permission_insert=dept_role_permissionService.insert(dept_role_permission);
        //插入职位与总监对应表
        if(dept_insert && dept_role_insert && dept_role_permission_insert){
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            return result;
        }else{
            return result;
        }
    }
}

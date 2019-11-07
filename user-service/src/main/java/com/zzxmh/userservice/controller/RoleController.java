package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.dept.*;
import com.zzxmh.userservice.domain.employee.Base_info;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.dept.DeptService;
import com.zzxmh.userservice.service.dept.LevelService;
import com.zzxmh.userservice.service.dept.PermissionService;
import com.zzxmh.userservice.service.dept.RoleService;
import com.zzxmh.userservice.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addRole")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private DeptService deptService;
//用户输入后模糊查询rolename
    @RequestMapping("/fuzzySelectRolename")
    public Object fuzzyselectNameAndId (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String searchinfo=map.get("data").toString();
        List<Role> list=roleService.fuzzyselectRolename(searchinfo);
        List<String> rolelist=new ArrayList<>();
        if(list.size() > 0 ){
            for(int i=0;i<list.size();i++){

                rolelist.add(list.get(i).getRoleName());
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(rolelist);
        }
        return result;
    }

    //查重
    @RequestMapping("/selectRolename")
    public Object selectRolename (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String searchinfo=map.get("data").toString();
        List<Role> list=roleService.selectRolename(searchinfo);
        if(list.size()!=0){
                result.setPayload("fail");
        }else{
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload("success");
        }
        return result;
    }
    @RequestMapping("/selectAllLevel")
    public Object selectAllLevel (){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        List<Level> list=levelService.getAllLevel();
        List<String> levellist=new ArrayList<>();
        if(list.size() > 0 ){
            for(int i=0;i<list.size();i++){

                levellist.add(list.get(i).getLevelName());
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(levellist);
        }
        return result;
    }

    @RequestMapping("/fuzzySelectPermissionname")
    public Object fuzzySelectPermissionname (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String searchinfo=map.get("data").toString();
        List<Permission> list=permissionService.fuzzyselectPermission(searchinfo);
        List<String> perlist=new ArrayList<>();
        if(list.size() > 0 ){
            for(int i=0;i<list.size();i++){
                perlist.add(list.get(i).getPermissionName());
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(perlist);
        }else{
            result.setCode(DataSourceEnum.FAIL.getCode());
            result.setMsg(DataSourceEnum.FAIL.getMsg());
        }
        return result;
    }

    @RequestMapping("/insertinfo")
    public Object insertinfo (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String deptname=map.get("deptname").toString();
        String deptloc=map.get("deptloc").toString();
        String rolename=map.get("rolename").toString();
        String rolefunc=map.get("rolefunc").toString();
        String level_start=map.get("level_start").toString();
        String level_end=map.get("level_end").toString();
        List<String> permissionname=(List<String>) map.get("permissionname");

        Dept dept=new Dept();
        dept.setDeptLoc(deptloc);
        dept.setDeptName(deptname);
        Integer deptid=deptService.getDeptInfo(dept).get(0).getDeptId();
        Role role=new Role();
        role.setRoleFunction(rolefunc);
        role.setRoleName(rolename);
        int flag=roleService.insertSelective(role);
        Integer roleid=null;
        if(flag==1) {
             roleid = roleService.selectBynameandFunc(role).getRoleId();
        }
        Dept_role deptRole=new Dept_role();
        deptRole.setDeptId(deptid);
        deptRole.setRoleId(roleid);
        int insertrp=deptService.insertSelective(deptRole);
        Integer dept_role_id=null;
        if(insertrp==1){
             dept_role_id=deptService.selectByRoleandDept(deptRole).getId();
        }
        Integer levelstartid=levelService.selectByLevelname(level_start).getLevelId();
        Integer levelendid=levelService.selectByLevelname(level_end).getLevelId();
        for(int i=levelstartid;i<=levelendid;i++)
        {
            Dept_role_level drl=new Dept_role_level();
            drl.setDeptRoleId(dept_role_id);
            drl.setLevelId(i);
            int insertlevel=levelService.insertSelective(drl);
        }

        for(int j=0;j<permissionname.size();j++){
            Integer permissionid=permissionService.selectByPermissionName(permissionname.get(j)).getPermissionId();
            Dept_role_permission drp=new Dept_role_permission();
            drp.setPermissionId(permissionid);
            drp.setDeptRoleId(dept_role_id);
            int insertpermission=permissionService.insertSelective(drp);

        }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());


        return result;
    }



}

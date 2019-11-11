package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.dao.dept.Dept_role_permissionMapper;
import com.zzxmh.userservice.domain.dept.*;
import com.zzxmh.userservice.domain.employee.Base_info;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
import com.zzxmh.userservice.service.dept.*;
import com.zzxmh.userservice.vo.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    @Autowired
    private Dept_roleService dept_roleService;
    @Autowired
    private Dept_role_permissionService dept_role_permissionService;

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

    @RequestMapping("/selectlocanddeptbyuserid")
    public Object selectlocanddeptbyuserid (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String userid=map.get("userid").toString();
        List<User_dept_role> dept_roles=dept_roleService.selectUDRbyuserid(userid);
        Integer deptid=null;
        for(int i=0;i<dept_roles.size();i++){
            deptid=dept_roleService.selectByPrimaryKey(dept_roles.get(i).getDeptRoleId()).getDeptId();
        }

        Dept dept=deptService.selectByPrimaryKey(deptid);
        Map<String,Object> returnmap = new HashMap<>();
        returnmap.put("deptname",dept.getDeptName());
        returnmap.put("deptloc",dept.getDeptLoc());
        result.setCode(DataSourceEnum.SUCCESS.getCode());
        result.setMsg(DataSourceEnum.SUCCESS.getMsg());
        result.setPayload(returnmap);
        return result;
    }


    @RequestMapping("/insertUDRinfo")
    public Object insertUDRinfo (@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        String deptinfo=map.get("deptinfo").toString();
        String[] splited = deptinfo.split("\\s+");
        String deptname=splited[0];
        String deptloc=splited[1];
        String userinfo=map.get("userinfo").toString();
        String[] userinfosp = userinfo.split("@");
        String username=userinfosp[0];
        String userid=userinfosp[1];
        Dept dept =new Dept();
        dept.setDeptName(deptname);
        dept.setDeptLoc(deptloc);
        Integer deptid=deptService.getDeptInfo(dept).get(0).getDeptId();
        Integer roleid=roleService.selectByRoleName(deptname+"总监").getRoleId();
        Dept_role dr=new Dept_role();
        dr.setRoleId(roleid);
        dr.setDeptId(deptid);
        Integer dept_role_id=deptService.selectByRoleandDept(dr).getId();
        User_dept_role udr=new User_dept_role();
        udr.setDeptRoleId(dept_role_id);
        udr.setUserId(userid);
        udr.setEntryTime(new Date());
        int flag=dept_roleService.insertSelective(udr);
        if(flag==1){
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(userid);
        }
       return result;
    }

    @RequestMapping("/insertinfo")
    public Object insertinfo(@RequestBody String row_info){
        ControllerResult result = new ControllerResult();
        result.setCode(DataSourceEnum.FAIL.getCode());
        result.setMsg(DataSourceEnum.FAIL.getMsg());
        JSONObject jsonObject=JSONObject.parseObject(row_info);
        Map<String,Object> map=(Map<String,Object>)jsonObject;
        Role role1=roleService.selectByRoleName(map.get("rolename").toString());
        List<String> permissionname=(List<String>) map.get("permissionname");
        int roleid=0;
        if(role1==null){
            Role role=new Role();
            role.setRoleName(map.get("rolename").toString());
            role.setRoleFunction(map.get("rolefunc").toString());
            int flag=roleService.insertSelective(role);
            if(flag==1){
                roleid=roleService.selectByRoleName(map.get("rolename").toString()).getRoleId();
            }
        }else {
            roleid=role1.getRoleId();
        }
        Dept dept=new Dept();
        dept.setDeptName(map.get("deptname").toString());
        dept.setDeptLoc(map.get("deptloc").toString());
        Dept dept1=deptService.selectByNameAndLoc(dept);
        Dept_role dept_role=new Dept_role();
        dept_role.setDeptId(dept1.getDeptId());
        dept_role.setRoleId(roleid);
        Dept_role dept_role1=dept_roleService.selectByRoleandDept(dept_role);
        if(dept_role1==null){
            Dept_role deptRole=new Dept_role();
            deptRole.setRoleId(roleid);
            deptRole.setDeptId(dept1.getDeptId());
            boolean flag=dept_roleService.insertSelective(deptRole);
            if(flag){
                Dept_role dept_role2=dept_roleService.selectByRoleandDept(deptRole);
                Integer levelstartid=levelService.selectByLevelname(map.get("level_start").toString()).getLevelId();
                Integer levelendid=levelService.selectByLevelname(map.get("level_end").toString()).getLevelId();
                for(int i=levelstartid;i<=levelendid;i++){
                    Dept_role_level deptRoleLevel=new Dept_role_level();
                    deptRoleLevel.setDeptRoleId(dept_role2.getId());
                    deptRoleLevel.setLevelId(i);
                    levelService.insertSelective(deptRoleLevel);
                }
                for(int i=0;i<permissionname.size();i++){
                    Permission permission=permissionService.selectByPermissionName(permissionname.get(i));
                    Dept_role_permission dept_role_permission=new Dept_role_permission();
                    dept_role_permission.setDeptRoleId(dept_role2.getId());
                    dept_role_permission.setPermissionId(permission.getPermissionId());
                    dept_role_permissionService.insert(dept_role_permission);
                }
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
        }else{
            result.setCode(DataSourceEnum.FAIL.getCode());
            result.setMsg(DataSourceEnum.FAIL.getMsg());
        }
        return result;
    }


}

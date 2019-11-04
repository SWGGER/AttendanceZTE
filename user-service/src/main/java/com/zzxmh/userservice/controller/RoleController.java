package com.zzxmh.userservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.userservice.domain.dept.Level;
import com.zzxmh.userservice.domain.dept.Permission;
import com.zzxmh.userservice.domain.dept.Role;
import com.zzxmh.userservice.domain.employee.Base_info;
import com.zzxmh.userservice.enumeration.DataSourceEnum;
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
        if(list!=null){
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
        if(list!=null){
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
        if(list!=null){
            for(int i=0;i<list.size();i++){
                perlist.add(list.get(i).getPermissionName());
            }
            result.setCode(DataSourceEnum.SUCCESS.getCode());
            result.setMsg(DataSourceEnum.SUCCESS.getMsg());
            result.setPayload(perlist);
        }
        return result;
    }




}

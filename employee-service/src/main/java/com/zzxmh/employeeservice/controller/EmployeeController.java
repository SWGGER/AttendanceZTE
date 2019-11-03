package com.zzxmh.employeeservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzxmh.employeeservice.domain.dept.Dept;
import com.zzxmh.employeeservice.domain.dept.Dept_role;
import com.zzxmh.employeeservice.domain.dept.Level;
import com.zzxmh.employeeservice.domain.dept.Role;
import com.zzxmh.employeeservice.domain.employee.Base_info;
import com.zzxmh.employeeservice.domain.employee.Detail_info;
import com.zzxmh.employeeservice.domain.user.User;
import com.zzxmh.employeeservice.domain.user.User_dept_role;
import com.zzxmh.employeeservice.service.dept.*;
import com.zzxmh.employeeservice.service.employee.Base_infoService;
import com.zzxmh.employeeservice.service.employee.Detail_infoService;
import com.zzxmh.employeeservice.service.employee.DimissionService;
import com.zzxmh.employeeservice.service.user.UserService;
import com.zzxmh.employeeservice.service.user.User_dept_roleService;
import com.zzxmh.employeeservice.tools.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private UserService userService;
    @Autowired
    private DimissionService dimissionService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private Dept_roleService dept_roleService;
    @Autowired
    private Base_infoService base_infoService;
    @Autowired
    private User_dept_roleService user_dept_roleService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private Detail_infoService detail_infoService;
    @Autowired
    private Dept_role_levelService dept_role_levelService;

    //通过录入按钮获取部门和地区
    @RequestMapping("/getUserId")
    public Object getUserId(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> dept=(Map<String,Object>)jsonObject;
        Dept d=new Dept();
        d.setDeptName(dept.get("dept").toString());
        d.setDeptLoc(dept.get("address").toString());
        Dept dept1=deptService.selectByNameAndLoc(d);
        int num=0,num1=0,num2=0;
        String user_user_id=userService.getMaxUserID(dept1.getDeptPrefix());
        String dimission_user_id=dimissionService.getMaxUserID(dept1.getDeptPrefix());

        if(user_user_id!=null)
            num1=Integer.parseInt(user_user_id.substring(dept1.getDeptPrefix().length(),user_user_id.length()));
        if(dimission_user_id!=null)
            num2=Integer.parseInt(dimission_user_id.substring(dept1.getDeptPrefix().length(),dimission_user_id.length()));
        if (user_user_id!=null||dimission_user_id!=null){
            if(num1>num2)
                num=num1;
            else if(num1<num2)
                num=num2;
            else
                num=0;
        }else{
            num=-1;
        }

        String user_id="";
        if(num==999){
            user_id=dimissionService.getMinUserID(dept1.getDeptPrefix());
        }else{
            user_id= CommonMethods.getUserId(dept1.getDeptPrefix(),num);
        }
        map.put("code",0);
        map.put("userid",user_id);
        return map;
    }

    //直接查询所有部门名
    @RequestMapping("/getDeptName")
    public Object getDeptName(){
        Map<String,Object> map=new HashMap<>();
        List<String> deptnames=deptService.getDeptName();
        if(deptnames.size()!=0){
            map.put("code",0);
            map.put("deptnames",deptnames);
        }else {
            map.put("code",-1);
        }
        return map;
    }

    //根据部门名获取地区
    @RequestMapping("/getAddress")
    public Object getAddress(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> m=(Map<String,Object>)jsonObject;
        List<String> addresses=deptService.getLOCByDeptName(m.get("dept").toString());
        if (addresses.size()!=0){
            map.put("code",0);
            map.put("addresses",addresses);
        }else {
            map.put("code",-1);
        }
        return map;
    }

    //根据部门名与地区获取职位
    @RequestMapping("/getJob")
    public Object getJob(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> m=(Map<String,Object>)jsonObject;
        Dept d=new Dept();
        d.setDeptLoc(m.get("address").toString());
        d.setDeptName(m.get("dept").toString());
        Dept dept=deptService.selectByNameAndLoc(d);
        List<Integer> role_ids=dept_roleService.selectByDeptId(dept.getDeptId());
        List<String> job=new ArrayList<>();
        for(int role_id:role_ids){
            job.add(roleService.selectByUserId(role_id));
        }
        if (job.size()!=0){
            map.put("code",0);
            map.put("job",job);
        }else{
            map.put("code",-1);
        }
        return map;
    }

    //根据部门和职位获取可选等级
    @RequestMapping("/getLevel")
    public Object getLevel(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        Dept d=new Dept();
        d.setDeptName(map1.get("dept").toString());
        d.setDeptLoc(map1.get("address").toString());
        Dept dept=deptService.selectByNameAndLoc(d);
        Role role=roleService.selectByName(map1.get("job").toString());
        Dept_role deptRole=new Dept_role();
        deptRole.setDeptId(dept.getDeptId());
        deptRole.setRoleId(role.getRoleId());
        Dept_role dept_role=dept_roleService.selectByDeptAndRole(deptRole);
        List<Integer> level_ids=dept_role_levelService.selectDeptRoleId(dept_role.getId());
        List<String> levels=new ArrayList<>();
        for(int level:level_ids){
            levels.add(levelService.selectByPrimaryKey(level));
        }
        if(levels.size()!=0){
            map.put("code",0);
            map.put("level",levels);
        }else{
            map.put("code",-1);
        }
        return map;
    }

    //录入所有的员工信息
    @RequestMapping("/insertAllInfo")
    public Object insertAllInfo(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        Base_info baseInfo=new Base_info();
        baseInfo.setUserId(map1.get("userid").toString());
        baseInfo.setEName(map1.get("name").toString());
        baseInfo.setEmail(map1.get("email").toString());
        baseInfo.setTel(map1.get("tel").toString());
        baseInfo.setSid(map1.get("sid").toString());
        baseInfo.setNation(map1.get("natation").toString());
        baseInfo.setLiveCity(map1.get("livecity").toString());
        baseInfo.setGender(map1.get("gender").toString());
        baseInfo.setSal(Double.parseDouble(map1.get("sal").toString()));
        int level=levelService.selectLevelName(map1.get("level").toString());
        baseInfo.setLevelId(level);
        boolean base_infoflag=base_infoService.insertSelective(baseInfo);
        User user=new User();
        user.setUserId(map1.get("userid").toString());
        user.setPassword("123456");
        user.setState("1");
        boolean userflag=userService.insert(user);
        Dept d=new Dept();
        d.setDeptName(map1.get("dept").toString());
        d.setDeptLoc(map1.get("address").toString());
        Dept dept=deptService.selectByNameAndLoc(d);
        Role role=roleService.selectByName(map1.get("job").toString());
        Dept_role dept_role=new Dept_role();
        dept_role.setRoleId(role.getRoleId());
        dept_role.setDeptId(dept.getDeptId());
        Dept_role dept_role1=dept_roleService.selectByDeptAndRole(dept_role);
        User_dept_role user_dept_role=new User_dept_role();
        user_dept_role.setUserId(map1.get("userid").toString());
        user_dept_role.setDeptRoleId(dept_role1.getId());
        boolean user_dept_roleflag=user_dept_roleService.insert(user_dept_role);
        Detail_info detail_info=new Detail_info();
        detail_info.setUserId(map1.get("userid").toString());
        detail_info.setEducation(map1.get("education").toString());
        detail_info.setEducationLevel(map1.get("educationlevel").toString());
        detail_info.setCertificate(map1.get("certificate").toString());
        boolean detail_infoflag=detail_infoService.insertSelective(detail_info);
        if(base_infoflag&&userflag&&user_dept_roleflag&&detail_infoflag){
            map.put("code",0);
        }else{
            map.put("code",-1);
        }
        return map;
    }

    //初始化数据
    @RequestMapping("/getAlldatas")
    public Object getAlldatas(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> page=(Map<String,Object>)jsonObject;
        if(Integer.parseInt(page.get("pageNumber").toString())==1){
            page.put("pageNumber",0);
        }else{
            page.put("pageNumber",(Integer.parseInt(page.get("pageNumber").toString())-1)*
                    Integer.parseInt(page.get("pageSize").toString()));
        }
        List<Base_info> base_infos=base_infoService.getAlldatas(page);
        List<Map<String,Object>> list=new ArrayList<>();
        for(Base_info baseInfo:base_infos){
            Map<String,Object> alldatas=new HashMap<>();
            alldatas.put("userid",baseInfo.getUserId());
            alldatas.put("name",baseInfo.getEName());
            alldatas.put("gender",baseInfo.getGender());
            int dept_role_id=user_dept_roleService.selectByUserId(baseInfo.getUserId());
            Dept_role dept_role=dept_roleService.selectByPrimaryKey(dept_role_id);
            Dept dept=deptService.selectByPrimaryKey(dept_role.getDeptId());
            alldatas.put("dept",dept.getDeptName());
            alldatas.put("address",dept.getDeptLoc());
            String job=roleService.selectByUserId(dept_role.getRoleId());
            alldatas.put("job",job);
            alldatas.put("level",levelService.selectByPrimaryKey(baseInfo.getLevelId()));
            list.add(alldatas);
        }
        map.put("code",0);
        map.put("adddatas",list);
        map.put("total",base_infoService.getTotal());
        return map;
    }

    //点击基本信息按钮
    @RequestMapping("/getBaseInfo")
    public Object getBaseInfo(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        Base_info baseInfo=base_infoService.selectByPrimaryKey(map1.get("userid").toString());
        if (baseInfo!=null){
            map.put("code",0);
            map.put("baseinfo",baseInfo);
        }else {
            map.put("code",-1);
        }
        return map;
    }

    //点击详细信息按钮
    @RequestMapping("/getDetailInfo")
    public Object getDetailInfo(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        Detail_info detailinfo=detail_infoService.selectByPrimaryKey(map1.get("userid").toString());
        if (detailinfo!=null){
            map.put("code",0);
            map.put("detailinfo",detailinfo);
        }else {
            map.put("code",-1);
        }
        return map;
    }

    //根据所选级别，判断薪资是否大于最大薪资
    @RequestMapping("/judgeSal")
    public Object judgeSal(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        Level le=new Level();
        le.setLevelName(map1.get("level").toString());
        le.setMaxSal(Double.parseDouble(map1.get("sal").toString()));
        Level level=levelService.judgeSal(le);
        if (level==null){
            map.put("code",-1);
        }else {
            map.put("code",0);
        }
        return map;
    }
}

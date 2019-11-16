package com.zzxmh.employeeservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzxmh.employeeservice.domain.dept.Dept;
import com.zzxmh.employeeservice.domain.dept.Dept_role;
import com.zzxmh.employeeservice.domain.dept.Level;
import com.zzxmh.employeeservice.domain.dept.Role;
import com.zzxmh.employeeservice.domain.employee.Base_info;
import com.zzxmh.employeeservice.domain.employee.Detail_info;
import com.zzxmh.employeeservice.domain.employee.Dimission;
import com.zzxmh.employeeservice.domain.user.User;
import com.zzxmh.employeeservice.domain.user.User_dept_role;
import com.zzxmh.employeeservice.service.dept.*;
import com.zzxmh.employeeservice.service.employee.Base_infoService;
import com.zzxmh.employeeservice.service.employee.Detail_infoService;
import com.zzxmh.employeeservice.service.employee.DimissionService;
import com.zzxmh.employeeservice.service.user.UserService;
import com.zzxmh.employeeservice.service.user.User_dept_roleService;
import com.zzxmh.employeeservice.tools.CommonMethods;
import com.zzxmh.employeeservice.tools.ExcelOper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

@RestController
public class EmployeeController {
    @Value("${file.excel}")
    private String excel_path;

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
            dimissionService.deleteByUserId(user_id);
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
        user_dept_role.setEntryTime(new Date());
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
            List<User_dept_role> user_dept_roles=user_dept_roleService.selectByUserId(baseInfo.getUserId());
            int dept_role_id=user_dept_roles.get(0).getDeptRoleId();
            Date min_date=user_dept_roles.get(0).getEntryTime();
            for (int i=1;i<user_dept_roles.size();i++){
                Date max_date=user_dept_roles.get(i).getEntryTime();
                if (min_date.getTime()>max_date.getTime()) {
                    min_date = max_date;
                    dept_role_id=user_dept_roles.get(i).getDeptRoleId();
                }
            }
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

    //获取模糊查询的所有数据
    @RequestMapping("/getfuzzyAlldatas")
    public Object getfuzzyAlldatas(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        if(Integer.parseInt(map1.get("pageNumber").toString())==1){
            map1.put("pageNumber",0);
        }else{
            map1.put("pageNumber",(Integer.parseInt(map1.get("pageNumber").toString())-1)*
                    Integer.parseInt(map1.get("pageSize").toString()));
        }
        List<Map<String,Object>> adddatas=base_infoService.getfuzzyAlldatas(map1);
        map.put("adddatas",adddatas);
        map.put("total",base_infoService.getfuzzyTotal(map1.get("searchtext").toString()));
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

    //user_id的查重
    @RequestMapping("/checkUserId")
    public Object checkUserId(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        User user=userService.selectByPrimaryKey(map1.get("userid").toString());
        String sidcheck = base_infoService.chechsid(map1.get("sid").toString());
        if (user==null){

            if(sidcheck==null){
                map.put("code",0);  //两个都不重  正确
            }else{
                map.put("code",-2);  //sid重
            }

        }else{

            if(sidcheck==null){
                map.put("code",-1);  //uid重
            }else{
                map.put("code",-3);  //sid、uid重
            }

        }


        return map;
    }

    //离职员工信息删除 dimission表的添加
    @RequestMapping("/dimissionUser")
    public Object dimissionUser(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        boolean userflag=userService.deleteByPrimaryKey(map1.get("userid").toString());
        List<User_dept_role> user_dept_roles=user_dept_roleService.selectByUserId(map1.get("userid").toString());
        boolean user_dept_roleflag=user_dept_roleService.deleteByUserId(map1.get("userid").toString());
        boolean base_infoflag=base_infoService.deleteByPrimaryKey(map1.get("userid").toString());
        boolean detail_infofalg=detail_infoService.deleteByPrimaryKey(map1.get("userid").toString());
        List<Dimission> dimission=new ArrayList<>();
        for(User_dept_role user_dept_role:user_dept_roles){
            Dimission d=new Dimission();
            d.setUserId(map1.get("userid").toString());
            d.setDimissionTime(new Date());
            d.setDeptRoleId(user_dept_role.getDeptRoleId());
            dimission.add(d);
        }
        System.out.println();
        boolean dimissionflag=dimissionService.insert(dimission);
        if(userflag&&user_dept_roleflag&&base_infoflag&&detail_infofalg&&dimissionflag){
            map.put("code",0);
        }else {
            map.put("code",-1);
        }
        return map;
    }

    //将模糊查询数据生成excel表格
    @RequestMapping("/generatorExcel")
    public Object generatorExcel(@RequestBody String data) throws IOException {
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        List<String> userids=(List<String>)map1.get("globle_selected_id");

        File file=new File(excel_path);
        if (!file.exists())
            file.mkdirs();

        String real_path = excel_path +"/"+ "download.xls";
        FileOutputStream fOut = null;
        File excel = new File(real_path);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("employee_info");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        int rowNum = 0;// 行标
        int colNum = 0;// 列标
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell = null;
        cell = row.createCell((short) 0);
        cell.setCellValue("User_ID");
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell = row.createCell((short) 2);
        cell.setCellValue("性别");
        cell = row.createCell((short) 3);
        cell.setCellValue("部门名");
        cell = row.createCell((short) 4);
        cell.setCellValue("职位");
        cell = row.createCell((short) 5);
        cell.setCellValue("级别");
        cell = row.createCell((short) 6);
        cell.setCellValue("地区");

        for(String s:userids){
            rowNum++;
            row = sheet.createRow((short) rowNum);
            cell = null;
            Map<String,Object> list = base_infoService.getDataByUserID(s);
            cell = row.createCell((short) 0);
            cell.setCellValue(list.get("USER_ID").toString());
            cell = row.createCell((short) 1);
            cell.setCellValue(list.get("E_NAME").toString());
            cell = row.createCell((short) 2);
            cell.setCellValue(list.get("GENDER").toString());
            cell = row.createCell((short) 3);
            cell.setCellValue(list.get("DEPT_NAME").toString());
            cell = row.createCell((short) 4);
            cell.setCellValue(list.get("DEPT_LOC").toString());
            cell = row.createCell((short) 5);
            cell.setCellValue(list.get("ROLE_NAME").toString());
            cell = row.createCell((short) 6);
            cell.setCellValue(list.get("LEVEL_NAME").toString());
        }

        fOut = new FileOutputStream(excel);
        workbook.write(fOut);

        fOut.flush();
        fOut.close();
        return map;

    }

    //下载excel表格
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        String real_path = excel_path +"/"+ "download.xls";
        response.addHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(real_path, "utf-8"));
        OutputStream out = response.getOutputStream();
        InputStream is = new FileInputStream(real_path);
        byte[] b = new byte[4096];
        int size = is.read(b);
        while (size > 0) {
            out.write(b, 0, size);
            size = is.read(b);
        }
        out.close();
        is.close();
    }

    //查看导入excel文件格式
    @RequestMapping("/excelimport")
    public Object excelUpload(@RequestParam("userinfo_upload") MultipartFile[] files) throws IOException {
        Map<String,Object> map=new HashMap<>();
        if(null !=files && files.length!=0){
            List<Map<String, Object>> remap = ExcelOper.translateExcels(files);
            for(int i = 0;i<remap.size();i++){
                Map<String, Object> listmap = remap.get(i);
                List<Map<String, Object>> file_datas = (List<Map<String, Object>>)listmap.get("file_datas");
                for(Map<String, Object> sheet_datas:file_datas) {
                    List<Map<String, Object>> rows = (List<Map<String, Object>>) sheet_datas.get("sheet_datas");
                    for(Map<String, Object> lmap:rows){
                        Pattern patternemail = Pattern.compile("^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$");
                        boolean emailflag=false;
                        if(lmap.get("email").equals("")){
                            emailflag = true;
                        }else{
                            String email =lmap.get("email").toString();
                            emailflag = patternemail.matcher(email).matches();
                        }

                        boolean telflag = false;
                        Pattern patternetelmobile = Pattern.compile("^(\\d{3,4}-)?\\d{6,9}$");
                        Pattern patternetelphone = Pattern.compile("^((\\+?86)|(\\(\\+86\\)))?( )?1(3|4|5|7|8)\\d{9}$");
                        if(lmap.get("tel").equals("")){
                            telflag = true;
                        }else{
                            String tel =lmap.get("tel").toString();
                            if(patternetelmobile.matcher(tel).matches()){
                                telflag = true;
                            }else{
                                telflag = patternetelphone.matcher(tel).matches();
                            }
                        }
                        boolean sidflag = false;
                        Pattern patternsid = Pattern.compile("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
                        if(lmap.get("sid").equals("")){
                            sidflag = false;
                        }else{
                            String sid =lmap.get("sid").toString();
                            sidflag = patternsid.matcher(sid).matches();
                        }
                        boolean genderflag = false;
                        if(lmap.get("gender").equals("男") || lmap.get("gender").equals("女")){
                            genderflag = true;
                        }else{
                            genderflag = false;
                        }
                        boolean salflag = false;
                        boolean levelflag = false;
                        Pattern patternsal = Pattern.compile("^(([1-9][0-9]*)|((([1-9][0-9]*)|0)\\.[0-9]{1,2}))$");
                        if(lmap.get("sal").equals("")){
                            salflag = false;
                        }else{
                            String sal =lmap.get("sal").toString();
                            if(patternsal.matcher(sal).matches()){
                                Map<String,Object> judgemap = new HashMap<>();
                                judgemap.put("dept",lmap.get("dept").toString());
                                judgemap.put("job",lmap.get("job").toString());
                                judgemap.put("address",lmap.get("address").toString());
                                List<String> returnjudgelist = base_infoService.existaddressdeptjob(judgemap);
                                if(lmap.get("level").equals("")){
                                    levelflag = false;
                                }else{
                                    String level = lmap.get("level").toString();
                                    if(returnjudgelist.contains(level)){
                                        levelflag = true;
                                        Level le=new Level();
                                        le.setLevelName(level);
                                        le.setMaxSal(Double.parseDouble(sal));
                                        Level leveljudge=levelService.judgeSal(le);
                                        if (leveljudge==null){
                                            salflag = false;
                                        }else {
                                            salflag = true;
                                        }
                                    }else{
                                        levelflag = false;
                                    }
                                }
                            }else{
                                salflag = false;
                            }
                        }

                        if(telflag && emailflag){
                            if(lmap.get("email").equals("") && lmap.get("tel").equals("")){
                                map.put("code",-1);  //格式错误
                                return map;
                            }else{
                                //联系方式（电话、邮箱）格式正确
                                if(levelflag && salflag && genderflag && sidflag){
                                    //插入

                                }else{
                                    map.put("code",-1);  //格式错误
                                    return map;
                                }
                            }
                        }else{
                            map.put("code",-1);  //格式错误
                            return map;
                        }
                    }
                }
            }
        }

        //code == 0 代表所有文件格式正确
        map.put("code",0);
        boolean insertsidflag = true;
        List<Map<String, Object>> rechecklist = new ArrayList<>();
        if(null !=files && files.length!=0){
            List<Map<String, Object>> remap = ExcelOper.translateExcels(files);
            for(int i = 0;i<remap.size();i++){
                Map<String, Object> listmap = remap.get(i);
                List<Map<String, Object>> file_datas = (List<Map<String, Object>>)listmap.get("file_datas");
                for(Map<String, Object> sheet_datas:file_datas) {
                    List<Map<String, Object>> rows = (List<Map<String, Object>>) sheet_datas.get("sheet_datas");
                    for(Map<String, Object> map1:rows){

                        String sidcheck = base_infoService.chechsid(map1.get("sid").toString());

                        System.out.println("-----------------------------");
                        System.out.println(sidcheck);
                        System.out.println("-----------------------------");

                        if(sidcheck==null){

                            //正确,直接插入

                            Map<String,Object> dept=map1;
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
                                dimissionService.deleteByUserId(user_id);
                            }else{
                                user_id= CommonMethods.getUserId(dept1.getDeptPrefix(),num);
                            }
                            Base_info baseInfo=new Base_info();
                            baseInfo.setUserId(user_id);
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
                            user.setUserId(user_id);
                            user.setPassword("123456");
                            user.setState("1");
                            boolean userflag=userService.insert(user);
                            Dept d2=new Dept();
                            d2.setDeptName(map1.get("dept").toString());
                            d2.setDeptLoc(map1.get("address").toString());
                            Dept dept2=deptService.selectByNameAndLoc(d);
                            Role role=roleService.selectByName(map1.get("job").toString());
                            Dept_role dept_role=new Dept_role();
                            dept_role.setRoleId(role.getRoleId());
                            dept_role.setDeptId(dept2.getDeptId());
                            Dept_role dept_role1=dept_roleService.selectByDeptAndRole(dept_role);
                            User_dept_role user_dept_role=new User_dept_role();
                            user_dept_role.setUserId(user_id);
                            user_dept_role.setDeptRoleId(dept_role1.getId());
                            user_dept_role.setEntryTime(new Date());
                            boolean user_dept_roleflag=user_dept_roleService.insert(user_dept_role);
                            Detail_info detail_info=new Detail_info();
                            detail_info.setUserId(user_id);
                            detail_info.setEducation(map1.get("education").toString());
                            detail_info.setEducationLevel(map1.get("educationlevel").toString());
                            detail_info.setCertificate(map1.get("certificate").toString());
                            boolean detail_infoflag=detail_infoService.insertSelective(detail_info);
                            if(base_infoflag&&userflag&&user_dept_roleflag&&detail_infoflag){
                                //插入成功
                            }else{
                                insertsidflag = false;
                            }
                        }else{
                            String userid = base_infoService.findsueridbysid(map1.get("sid").toString());
                            map1.put("userid",userid);
                            rechecklist.add(map1);
                        }
                    }
                }
            }
        }

        if(insertsidflag){
            map.put("insertsid",0);
        }else{
            map.put("insertsid",-1);
        }
        map.put("rechecklist",rechecklist);

        return map;
    }

    //批量导入员工信息的查重
    @RequestMapping("/batchinsert")
    public Object batchinsert(@RequestBody String data){
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject=JSONObject.parseObject(data);
        Map<String,Object> map1=(Map<String,Object>)jsonObject;
        List<Map<String,Object>> relists = (List<Map<String,Object>>)map1.get("rechecklist");
        for(Map<String,Object> recheck:relists){
            Map<String,Object> infomap=new HashMap<>();
            infomap.put("dept_name",recheck.get("dept").toString());
            infomap.put("dept_loc",recheck.get("address").toString());
            infomap.put("role_name",recheck.get("job").toString());
            int newdept_role_id = dept_roleService.finddeptroleidbyinfo(infomap);
            int olddept_role_id = user_dept_roleService.batcholddeptroleid(recheck.get("userid").toString());

            Map<String,Object> userdeptrolemap=new HashMap<>();
            userdeptrolemap.put("newdeptRoleId",newdept_role_id);
            userdeptrolemap.put("userId",recheck.get("userid").toString());
            userdeptrolemap.put("olddeptRoleId",olddept_role_id);
            boolean updateuserdeptroleflag = user_dept_roleService.updatebatchinfo(userdeptrolemap);

            int levelid=levelService.selectLevelName(recheck.get("level").toString());
            Base_info baseInfo=new Base_info();
            baseInfo.setUserId(recheck.get("userid").toString());
            baseInfo.setEName(recheck.get("name").toString());
            baseInfo.setEmail(recheck.get("email").toString());
            baseInfo.setTel(recheck.get("tel").toString());
            baseInfo.setSid(recheck.get("sid").toString());
            baseInfo.setNation(recheck.get("natation").toString());
            baseInfo.setLiveCity(recheck.get("livecity").toString());
            baseInfo.setGender(recheck.get("gender").toString());
            baseInfo.setSal(Double.parseDouble(recheck.get("sal").toString()));
            baseInfo.setLevelId(levelid);
            boolean base_infoflag=base_infoService.updateByPrimaryKeySelective(baseInfo);

            Detail_info detail_info=new Detail_info();
            detail_info.setUserId(recheck.get("userid").toString());
            detail_info.setEducation(recheck.get("education").toString());
            detail_info.setEducationLevel(recheck.get("educationlevel").toString());
            detail_info.setCertificate(recheck.get("certificate").toString());
            boolean detail_infoflag = detail_infoService.updateByPrimaryKeySelective(detail_info);

            if(updateuserdeptroleflag && base_infoflag && detail_infoflag){
                map.put("code",0);
            }else{
                map.put("code",-1);
            }
        }
        return map;
    }


}


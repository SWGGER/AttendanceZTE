package com.zzxmh.employeeservice;

import com.zzxmh.employeeservice.domain.dept.Dept;
import com.zzxmh.employeeservice.domain.dept.Dept_role;
import com.zzxmh.employeeservice.domain.dept.Role;
import com.zzxmh.employeeservice.domain.employee.Base_info;
import com.zzxmh.employeeservice.domain.employee.Detail_info;
import com.zzxmh.employeeservice.domain.user.User;
import com.zzxmh.employeeservice.domain.user.User_dept_role;
import com.zzxmh.employeeservice.service.dept.DeptService;
import com.zzxmh.employeeservice.service.dept.Dept_roleService;
import com.zzxmh.employeeservice.service.employee.Base_infoService;
import com.zzxmh.employeeservice.service.dept.RoleService;
import com.zzxmh.employeeservice.service.employee.Detail_infoService;
import com.zzxmh.employeeservice.service.employee.DimissionService;
import com.zzxmh.employeeservice.service.user.UserService;
import com.zzxmh.employeeservice.service.user.User_dept_roleService;
import com.zzxmh.employeeservice.tools.CommonMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceApplicationTests {

    @Autowired
    private Base_infoService base_infoService;
    @Autowired
    private Detail_infoService detail_infoService;
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
    private User_dept_roleService user_dept_roleService;
    @Test
    public void contextLoads() {
//        int num=0,num1=0,num2=0;
        Dept d=new Dept();
        d.setDeptName("研发部");
        d.setDeptLoc("上海");
        Dept dept=deptService.selectByNameAndLoc(d);
//        String user_user_id=userService.getMaxUserID(dept.getDeptPrefix());
//        String dimission_user_id=dimissionService.getMaxUserID(dept.getDeptPrefix());
//        if(user_user_id!=null)
//            num1=Integer.parseInt(user_user_id.substring(dept.getDeptPrefix().length(),user_user_id.length()));
//        if(dimission_user_id!=null)
//            num2=Integer.parseInt(dimission_user_id.substring(dept.getDeptPrefix().length(),dimission_user_id.length()));
//        if(num1>num2)
//            num=num1;
//        else if(num1<num2)
//            num=num2;
//        else
//            num=-1;
//        String user_id="";
//        if(num==999){
//            user_id=dimissionService.getMinUserID(dept.getDeptPrefix());
//        }else{
//            user_id= CommonMethods.getUserId(dept.getDeptPrefix(),num);
//        }
//
//        System.out.println(user_id);
//        System.out.println(getLevelAndSal());
        Role role=roleService.selectByName("Java开发工程师");
        Dept_role deptRole=new Dept_role();
        deptRole.setDeptId(dept.getDeptId());
        deptRole.setRoleId(role.getRoleId());
        Dept_role dept_role=dept_roleService.selectByDeptAndRole(deptRole);
//        System.out.println(dept_role.getId());

        for(int i=0;i<15;i++){
            int num=0,num1=0,num2=0;
            String user_user_id=userService.getMaxUserID(dept.getDeptPrefix());
            String dimission_user_id=dimissionService.getMaxUserID(dept.getDeptPrefix());

            if(user_user_id!=null)
                num1=Integer.parseInt(user_user_id.substring(dept.getDeptPrefix().length(),user_user_id.length()));
            if(dimission_user_id!=null)
                num2=Integer.parseInt(dimission_user_id.substring(dept.getDeptPrefix().length(),dimission_user_id.length()));
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
                user_id=dimissionService.getMinUserID(dept.getDeptPrefix());
            }else{
                user_id= CommonMethods.getUserId(dept.getDeptPrefix(),num);
            }

            User user=new User();
            user.setUserId(user_id);
            user.setPassword("123456");
            user.setState("1");
            boolean user_flag=userService.insert(user);
            User_dept_role user_dept_role=new User_dept_role();
            user_dept_role.setUserId(user_id);
            user_dept_role.setDeptRoleId(dept_role.getId());
            boolean user_dept_roleflag=user_dept_roleService.insert(user_dept_role);
            Base_info baseInfo=new Base_info();
            baseInfo.setUserId(user_id);
            baseInfo.setGender("女");
            baseInfo.setEmail(getEmail());
            baseInfo.setTel(getTel());
            baseInfo.setSid(getSID());
            baseInfo.setEName(getEName());
            baseInfo.setSal(Double.parseDouble(getLevelAndSal().get("sal").toString()));
            baseInfo.setLevelId(Integer.parseInt(getLevelAndSal().get("level").toString()));
            Detail_info detail_info=new Detail_info();
            if(i%3==0){
//                detail_info.setUserId(user_id);
//                detail_info.setEducation("南京师范大学");
//                detail_info.setEducationLevel("硕士");
//                detail_infoService.insertSelective(detail_info);
                baseInfo.setNation("深圳");
            }else if (i%5==0){
//                detail_info.setUserId(user_id);
//                detail_info.setEducation("上海外国语大学");
//                detail_info.setEducationLevel("硕士");
//                detail_infoService.insertSelective(detail_info);
                baseInfo.setNation("广东广州");
            }else if(i%7==0){
                baseInfo.setNation("广西桂林");
            }
            baseInfo.setLiveCity("上海");
            boolean base_infoflag=base_infoService.insertSelective(baseInfo);
//            boolean detail_infoflag=detail_infoService.insertSelective(detail_info);
        }
    }

    public Map<String,Object> getLevelAndSal(){
        Map<String,Object> map=new HashMap<>();
        int level=(int) (Math.random()*(12-11)+11);
        int sal=0;
        if(level==12)
            sal=(int) (Math.random()*(5250-3500)+3500);
        else
            sal=(int) (Math.random()*(6000-4000)+4000);
        map.put("level",level);
        map.put("sal",sal);
        return map;
    }

    public String getEmail(){
        Random random = new Random();
        int min = (int)Math.pow(10,9);
        int max = (int)Math.pow(10,10)-1;
        int numm = random.nextInt(max)%(max-min+1) + min;
        return numm+"@qq.com";
    }

    public String getTel(){
        String[] Top3 = {"133", "149", "153", "173", "177",
                "180", "181", "189", "199", "130", "131", "132",
                "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                "178", "182", "183", "184", "187", "188", "198", "170", "171"};
        String firstNum = Top3[(int) (Math.random() * Top3.length)];
        //随机出剩下的8位数
        String lastNum = "";
        final int last = 8;
        for (int i = 0; i < last; i++) {
            //每次循环都从0~9挑选一个随机数
            lastNum += (int) (Math.random() * 10);
        }
        //最终将号段和尾数连接起来
        String phoneNum = firstNum+ lastNum;
        return phoneNum;
    }

    public String getSID(){
        String id = "320724199710225429";
        // 随机生成省、自治区、直辖市代码 1-2
        String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82" };
        String province = randomOne(provinces);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = new Random().nextInt(899) + 100+"";
        // 随机生成校验码 18
        String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X" };
        String check = randomOne(checks);
        // 拼接身份证号码
        id = province + city + county + birth + no + check;
        return id;
    }
    public String randomOne(String s[]) {
        return s[new Random().nextInt(s.length - 1)];
    }
    public String randomCityCode(int max) {
        int i = new Random().nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }
    public String randomBirth(int minAge, int maxAge) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());// 设置当前日期
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    public String getEName(){
        Random random=new Random(System.currentTimeMillis());
        /* 598 百家姓 */
        String[] Surname= {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
                "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
                "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
                "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
                "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
                "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
                "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
                "宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
                "羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
                "谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
                "詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
                "蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
                "璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
                "宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
                "阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
                "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
                "督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
                "哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
                "疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
                "木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
                "闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
                "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
                "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
                "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
                "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"};

        int index=random.nextInt(Surname.length-1);
        String name = Surname[index]; //获得一个随机的姓氏

        /* 从常用字中选取一个或两个字作为名 */
        if(random.nextBoolean()){
            name+=getChinese()+getChinese();
        }else {
            name+=getChinese();
        }
        return name;
    }
    public String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = new Random();
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        random=new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

        byte[] bArr = new byte[2];
        bArr[0] = (new Integer(highPos)).byteValue();
        bArr[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(bArr, "GB2312");	//区位码组合成汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}

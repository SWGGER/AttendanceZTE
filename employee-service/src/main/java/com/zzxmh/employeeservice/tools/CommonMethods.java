package com.zzxmh.employeeservice.tools;

import com.zzxmh.employeeservice.service.employee.DimissionService;
import com.zzxmh.employeeservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonMethods {

    public static String getUserId(String dept_prefix,int num){
        String user_id=dept_prefix;
        num=num+1;
        user_id+=String.format("%03d",num);
        return user_id;
    }

}

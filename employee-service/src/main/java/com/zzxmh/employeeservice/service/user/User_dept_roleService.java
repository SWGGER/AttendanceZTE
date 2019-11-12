package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.domain.user.User_dept_role;

import java.util.List;
import java.util.Map;

public interface User_dept_roleService {
    boolean insert(User_dept_role record);
    List<User_dept_role> selectByUserId(String userId);
    boolean deleteByUserId(String userId);
    boolean updatebatchinfo(Map<String,Object> map);
    int batcholddeptroleid(String userid);
}

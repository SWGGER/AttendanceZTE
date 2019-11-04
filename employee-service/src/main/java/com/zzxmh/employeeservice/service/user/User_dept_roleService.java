package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.domain.user.User_dept_role;

import java.util.List;

public interface User_dept_roleService {
    boolean insert(User_dept_role record);
    List<User_dept_role> selectByUserId(String userId);
    boolean deleteByUserId(String userId);
}

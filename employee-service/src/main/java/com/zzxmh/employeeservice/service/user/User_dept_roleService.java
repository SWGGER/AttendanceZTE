package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.domain.dept.Dept_role;
import com.zzxmh.employeeservice.domain.user.User_dept_role;

public interface User_dept_roleService {
    boolean insert(User_dept_role record);
    int selectByUserId(String userId);
}

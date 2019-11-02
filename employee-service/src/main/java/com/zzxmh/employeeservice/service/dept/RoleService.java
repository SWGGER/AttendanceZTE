package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.domain.dept.Role;

public interface RoleService {
    Role selectByName(String roleName);
    String selectByUserId(int roleId);
}

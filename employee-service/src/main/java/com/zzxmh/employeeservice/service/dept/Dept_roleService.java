package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.domain.dept.Dept_role;

import java.util.List;

public interface Dept_roleService {
    Dept_role selectByDeptAndRole(Dept_role record);
    List<Integer> selectByDeptId(int deptId);
    Dept_role selectByPrimaryKey(int id);
}

package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.domain.dept.Dept;

import java.util.List;

public interface DeptService {
    Dept selectByNameAndLoc(Dept record);
    List<String> getDeptName();
    List<String> getLOCByDeptName(String deptName);
    Dept selectByPrimaryKey(int deptId);
}

package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.domain.dept.Dept_role;
import com.zzxmh.userservice.domain.dept.User_dept_role;

import java.util.List;

public interface DeptService {
    boolean insertSelective(Dept record);

    boolean recheckDept(Dept dept);

    //根据name和loc查出id
    List<Dept> getDeptInfo(Dept dept);

    Dept_role selectByRoleandDept(Dept_role record);

    int insertSelective(Dept_role record);

    Dept selLastData();

    Dept selectByPrimaryKey(int deptId);

    List<Dept> fuzzyselectDeptnameAndLoc(String deptName);

    Dept selectDeptId(Dept dept);

    //根据部门名和所在地查询部门信息
    Dept selectByNameAndLoc(Dept record);

}

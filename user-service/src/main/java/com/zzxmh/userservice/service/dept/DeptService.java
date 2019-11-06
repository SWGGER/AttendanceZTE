package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.domain.dept.Dept_role;

import java.util.List;

public interface DeptService {
    boolean insertSelective(Dept record);

    boolean recheckDept(Dept dept);

    //根据name和loc查出id
    List<Dept> getDeptInfo(Dept dept);

    Dept_role selectByRoleandDept(Dept_role record);

    int insertSelective(Dept_role record);

    Dept selLastData();

}

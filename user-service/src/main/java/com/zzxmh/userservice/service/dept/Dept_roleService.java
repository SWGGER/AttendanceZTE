package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept_role;
import com.zzxmh.userservice.domain.dept.User_dept_role;

import java.util.List;

public interface Dept_roleService {
    boolean insertSelective(Dept_role record);

    Dept_role selectByPrimaryKey(Integer id);

    List<User_dept_role> selectUDRbyuserid(String userid);

    int insertSelective(User_dept_role record);

    Dept_role selectByRoleandDept(Dept_role record);

    int  selLastData();
}

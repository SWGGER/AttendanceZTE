package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Role;

import java.util.List;

public interface RoleService {
    List<Role> fuzzyselectRolename(String rolename);

    List<Role> selectRolename(String rolename);

    Role selectBynameandFunc(Role record);


    //查询role表中是否存在该职务
    Integer RoleNameExistRole(String roleName);

    //查询Role表最后条记录的id
    Integer RoleLastData();

    //插入role表中数据
    int insertSelective(Role record);
}

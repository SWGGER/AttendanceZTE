package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKeyWithBLOBs(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> fuzzyselectRolename(String rolename);

    List<Role> selectRolename(String rolename);

    Role selectBynameandFunc(Role record);

    Integer RoleNameExistRole(String roleName);

    Integer RoleLastData();

    Role selectByRoleName(String roleName);


}
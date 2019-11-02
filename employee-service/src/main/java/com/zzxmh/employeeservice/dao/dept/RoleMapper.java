package com.zzxmh.employeeservice.dao.dept;

import com.zzxmh.employeeservice.domain.dept.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    //根据职位名获取职位信息
    Role selectByName(String roleName);

    //根据role_id获取职位名
    String selectByUserId(int roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKeyWithBLOBs(Role record);

    int updateByPrimaryKey(Role record);
}
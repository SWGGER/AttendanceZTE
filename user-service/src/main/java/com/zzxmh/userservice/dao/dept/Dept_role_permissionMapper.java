package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Dept_role_permission;
import org.springframework.stereotype.Repository;

@Repository
public interface Dept_role_permissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept_role_permission record);

    int insertSelective(Dept_role_permission record);

    Dept_role_permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept_role_permission record);

    int updateByPrimaryKey(Dept_role_permission record);
}
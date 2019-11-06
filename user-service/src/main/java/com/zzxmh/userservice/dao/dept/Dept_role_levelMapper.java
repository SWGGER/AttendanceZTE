package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Dept_role_level;

public interface Dept_role_levelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept_role_level record);

    int insertSelective(Dept_role_level record);

    Dept_role_level selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept_role_level record);

    int updateByPrimaryKey(Dept_role_level record);
}
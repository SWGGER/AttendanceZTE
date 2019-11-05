package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Dept_role;
import org.springframework.stereotype.Repository;

@Repository
public interface Dept_roleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept_role record);

    int insertSelective(Dept_role record);

    Dept_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept_role record);

    int updateByPrimaryKey(Dept_role record);
}
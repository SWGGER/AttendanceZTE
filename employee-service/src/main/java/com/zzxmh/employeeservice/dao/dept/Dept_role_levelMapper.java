package com.zzxmh.employeeservice.dao.dept;

import com.zzxmh.employeeservice.domain.dept.Dept_role_level;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dept_role_levelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept_role_level record);

    int insertSelective(Dept_role_level record);

    Dept_role_level selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept_role_level record);

    int updateByPrimaryKey(Dept_role_level record);

    //根据dept_role_id获取level_id
    List<Integer> selectDeptRoleId(int deptRoleId);
}
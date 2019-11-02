package com.zzxmh.employeeservice.dao.dept;

import com.zzxmh.employeeservice.domain.dept.Dept_role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dept_roleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept_role record);

    int insertSelective(Dept_role record);

    //根据dept_id和role_id查询结果
    Dept_role selectByDeptAndRole(Dept_role record);

    //根据dept_id获取role_id
    List<Integer> selectByDeptId(int deptId);

    //根据id查询
    Dept_role selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Dept_role record);

    int updateByPrimaryKey(Dept_role record);
}
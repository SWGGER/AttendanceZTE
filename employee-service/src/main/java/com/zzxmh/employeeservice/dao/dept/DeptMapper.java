package com.zzxmh.employeeservice.dao.dept;

import com.zzxmh.employeeservice.domain.dept.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    //根据部门名和所在地查询部门信息
    Dept selectByNameAndLoc(Dept record);

    //查询出所有部门名
    List<String> getDeptName();

    //根据部门查询地区
    List<String> getLOCByDeptName(String deptName);

    //根据dept_id查询出dept_name
    Dept selectByPrimaryKey(int deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKeyWithBLOBs(Dept record);

    int updateByPrimaryKey(Dept record);
}
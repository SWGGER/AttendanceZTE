package com.zzxmh.employeeservice.dao.user;

import com.zzxmh.employeeservice.domain.user.User_dept_role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_dept_roleMapper {

    User_dept_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User_dept_role record);

    int updateByPrimaryKey(User_dept_role record);

    //根据user_id删除表格数据
    int deleteByUserId(String userId);

    //插入员工职位表信息
    int insert(User_dept_role record);

    //根据user_id查询dept_role_id
    List<User_dept_role> selectByUserId(String userId);
}
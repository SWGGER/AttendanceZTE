package com.zzxmh.employeeservice.dao.user;

import com.zzxmh.employeeservice.domain.user.User_dept_role;
import org.springframework.stereotype.Repository;

@Repository
public interface User_dept_roleMapper {
    int deleteByPrimaryKey(Integer id);
    //插入员工职位表信息
    int insert(User_dept_role record);

    //根据user_id查询dept_role_id
    int selectByUserId(String userId);

    User_dept_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User_dept_role record);

    int updateByPrimaryKey(User_dept_role record);
}
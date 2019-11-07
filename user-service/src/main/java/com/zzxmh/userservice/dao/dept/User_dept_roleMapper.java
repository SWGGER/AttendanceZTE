package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.User_dept_role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface User_dept_roleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User_dept_role record);

    int insertSelective(User_dept_role record);

    User_dept_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User_dept_role record);

    int updateByPrimaryKey(User_dept_role record);

    List<User_dept_role> selectUDRbyuserid(String userid);
}
package com.zzxmh.employeeservice.dao.user;

import com.zzxmh.employeeservice.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //根据USER_ID删除user表
    int deleteByPrimaryKey(String userId);
    //根据USER_ID查询
    User selectByPrimaryKey(String userId);
    //插入员工信息
    int insert(User record);
    //获取最新员工工号
    String getMaxUserID(String prefix);
}
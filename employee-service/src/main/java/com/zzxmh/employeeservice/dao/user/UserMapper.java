package com.zzxmh.employeeservice.dao.user;

import com.zzxmh.employeeservice.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //插入员工信息
    int insert(User record);
    //获取最新员工工号
    String getMaxUserID(String prefix);
}
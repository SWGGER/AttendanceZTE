package com.zzxmh.userservice.dao.user;

import com.zzxmh.userservice.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getuserInfoByLoginId(String userId);//根据loginid找password

}
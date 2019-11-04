package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.dao.user.UserMapper;
import com.zzxmh.employeeservice.domain.user.User;
import com.zzxmh.employeeservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean insert(User record) {
        int count=userMapper.insert(record);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public String getMaxUserID(String prefix) {
        return userMapper.getMaxUserID(prefix);
    }

    @Override
    public boolean deleteByPrimaryKey(String userId) {
        int count=userMapper.deleteByPrimaryKey(userId);
        if (count>0)
            return true;
        return false;
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}

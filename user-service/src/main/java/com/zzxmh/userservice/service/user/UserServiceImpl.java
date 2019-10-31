package com.zzxmh.userservice.service.user;

import com.zzxmh.userservice.dao.user.UserMapper;
import com.zzxmh.userservice.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getuserInfoByLoginId(String userId) {
        return userMapper.getuserInfoByLoginId(userId);
    }
}

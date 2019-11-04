package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.dao.user.User_dept_roleMapper;
import com.zzxmh.employeeservice.domain.user.User_dept_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class User_dept_roleServiceImpl implements User_dept_roleService {

    @Autowired
    private User_dept_roleMapper user_dept_roleMapper;
    @Override
    public boolean insert(User_dept_role record) {
        int count=user_dept_roleMapper.insert(record);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public List<User_dept_role> selectByUserId(String userId) {
        return user_dept_roleMapper.selectByUserId(userId);
    }

    @Override
    public boolean deleteByUserId(String userId) {
        int count=user_dept_roleMapper.deleteByUserId(userId);
        if (count>0)
            return true;
        return false;
    }
}
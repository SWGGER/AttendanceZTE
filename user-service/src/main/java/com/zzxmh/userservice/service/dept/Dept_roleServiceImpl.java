package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.Dept_roleMapper;
import com.zzxmh.userservice.dao.dept.User_dept_roleMapper;
import com.zzxmh.userservice.domain.dept.Dept_role;
import com.zzxmh.userservice.domain.dept.User_dept_role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Slf4j
@Transactional
public class Dept_roleServiceImpl implements Dept_roleService{
    @Autowired
    private Dept_roleMapper dept_roleMapper;
    @Autowired
    private User_dept_roleMapper user_dept_roleMapper;

    @Override
    public boolean insertSelective(Dept_role record) {
        int count=dept_roleMapper.insertSelective(record);
        if(count>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Dept_role selectByPrimaryKey(Integer id) {
        return dept_roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User_dept_role> selectUDRbyuserid(String userid) {
        return user_dept_roleMapper.selectUDRbyuserid(userid);
    }

    @Override
    public int insertSelective(User_dept_role record) {
        return user_dept_roleMapper.insertSelective(record);
    }

    @Override
    public Dept_role selectByRoleandDept(Dept_role record) {
        return dept_roleMapper.selectByRoleandDept(record);
    }
}

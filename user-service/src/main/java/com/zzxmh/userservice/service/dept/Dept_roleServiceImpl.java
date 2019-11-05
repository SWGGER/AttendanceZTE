package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.Dept_roleMapper;
import com.zzxmh.userservice.domain.dept.Dept_role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Slf4j
@Transactional
public class Dept_roleServiceImpl implements Dept_roleService{
    @Autowired
    private Dept_roleMapper dept_roleMapper;
    @Override
    public boolean insertSelective(Dept_role record) {
        int count=dept_roleMapper.insertSelective(record);
        if(count>0){
            return true;
        }else{
            return false;
        }

    }
}

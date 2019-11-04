package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.DeptMapper;
import com.zzxmh.userservice.dao.user.UserMapper;
import com.zzxmh.userservice.domain.dept.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Slf4j
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public boolean insertSelective(Dept record) {
        int count=deptMapper.insertSelective(record);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean recheckDept(Dept dept) {
        int count=deptMapper.recheckDept(dept);
        if(count>0){
            return false;
        }else{
            return true;
        }
    }
}

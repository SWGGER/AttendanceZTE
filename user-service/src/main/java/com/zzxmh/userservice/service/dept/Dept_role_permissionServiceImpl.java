package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.Dept_role_permissionMapper;
import com.zzxmh.userservice.domain.dept.Dept_role_permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Dept_role_permissionServiceImpl implements Dept_role_permissionService{
    @Autowired
    private Dept_role_permissionMapper dept_role_permissionMapper;
    @Override
    public boolean insert(Dept_role_permission record) {
        int count=dept_role_permissionMapper.insert(record);
        if (count>0)
            return true;
        return false;
    }
}

package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.dao.dept.RoleMapper;
import com.zzxmh.employeeservice.domain.dept.Role;
import com.zzxmh.employeeservice.service.dept.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role selectByName(String roleName) {
        return roleMapper.selectByName(roleName);
    }

    @Override
    public String selectByUserId(int roleId) {
        return roleMapper.selectByUserId(roleId);
    }
}

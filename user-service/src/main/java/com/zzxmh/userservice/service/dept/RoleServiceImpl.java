package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.RoleMapper;
import com.zzxmh.userservice.domain.dept.Role;
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
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> fuzzyselectRolename(String rolename) {
        return roleMapper.fuzzyselectRolename(rolename);
    }

    @Override
    public Integer RoleNameExistRole(String roleName) {
        return roleMapper.RoleNameExistRole(roleName);
    }

    @Override
    public Integer RoleLastData() {
        return roleMapper.RoleLastData();
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }
}

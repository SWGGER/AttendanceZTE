package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.Dept_role_permissionMapper;
import com.zzxmh.userservice.dao.dept.PermissionMapper;
import com.zzxmh.userservice.domain.dept.Dept_role_permission;
import com.zzxmh.userservice.domain.dept.Permission;
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
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private Dept_role_permissionMapper dept_role_permissionMapper;
    @Override
    public List<Permission> fuzzyselectPermission(String context) {
        return permissionMapper.fuzzyselectPermission(context);
    }

    @Override
    public Permission selectByPermissionName(String permissionName) {
        return permissionMapper.selectByPermissionName(permissionName);
    }

    @Override
    public int insertSelective(Dept_role_permission record) {
        return dept_role_permissionMapper.insertSelective(record);
    }
}

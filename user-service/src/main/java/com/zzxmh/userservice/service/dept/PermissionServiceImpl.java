package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.PermissionMapper;
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
    @Override
    public List<Permission> fuzzyselectPermission(String context) {
        return permissionMapper.fuzzyselectPermission(context);
    }
}

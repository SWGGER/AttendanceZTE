package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept_role_permission;
import com.zzxmh.userservice.domain.dept.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> fuzzyselectPermission(String context);
    Permission selectByPermissionName(String permissionName);

    int insertSelective(Dept_role_permission record);
}

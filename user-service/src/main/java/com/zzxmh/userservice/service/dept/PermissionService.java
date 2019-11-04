package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> fuzzyselectPermission(String context);
}

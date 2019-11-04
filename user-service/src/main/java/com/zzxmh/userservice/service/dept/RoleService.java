package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Role;

import java.util.List;

public interface RoleService {
    List<Role> fuzzyselectRolename(String rolename);
}

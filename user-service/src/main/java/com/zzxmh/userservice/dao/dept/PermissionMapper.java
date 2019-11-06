package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Permission;
import com.zzxmh.userservice.domain.dept.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKeyWithBLOBs(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> fuzzyselectPermission(String context);

    Permission selectByPermissionName(String permissionName);
}
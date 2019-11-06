package com.zzxmh.userservice.domain.dept;

public class Dept_role_permission {
    private Integer id;

    private Integer deptRoleId;

    private Integer permissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptRoleId() {
        return deptRoleId;
    }

    public void setDeptRoleId(Integer deptRoleId) {
        this.deptRoleId = deptRoleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
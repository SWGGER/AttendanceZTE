package com.zzxmh.userservice.domain.dept;

public class Permission {
    private Integer permissionId;

    private String permissionName;

    private String state;

    private String permissionFunction;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPermissionFunction() {
        return permissionFunction;
    }

    public void setPermissionFunction(String permissionFunction) {
        this.permissionFunction = permissionFunction == null ? null : permissionFunction.trim();
    }
}
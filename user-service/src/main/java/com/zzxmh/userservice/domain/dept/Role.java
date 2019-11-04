package com.zzxmh.userservice.domain.dept;

public class Role {
    private Integer roleId;

    private String roleName;

    private String state;

    private String roleFunction;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRoleFunction() {
        return roleFunction;
    }

    public void setRoleFunction(String roleFunction) {
        this.roleFunction = roleFunction == null ? null : roleFunction.trim();
    }
}
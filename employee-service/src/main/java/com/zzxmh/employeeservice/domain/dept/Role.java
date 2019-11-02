package com.zzxmh.employeeservice.domain.dept;

import lombok.Data;

@Data
public class Role {
    private Integer roleId;

    private String roleName;

    private String state;

    private String roleFunction;
}
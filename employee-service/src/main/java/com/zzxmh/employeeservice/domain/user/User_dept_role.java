package com.zzxmh.employeeservice.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class User_dept_role {
    private Integer id;

    private String userId;

    private Integer deptRoleId;

    private Date entryTime;
}
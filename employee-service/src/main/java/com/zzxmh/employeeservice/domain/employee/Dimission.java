package com.zzxmh.employeeservice.domain.employee;

import lombok.Data;

import java.util.Date;

@Data
public class Dimission {
    private Integer id;

    private String userId;

    private Date dimissionTime;

    private Integer deptRoleId;

}
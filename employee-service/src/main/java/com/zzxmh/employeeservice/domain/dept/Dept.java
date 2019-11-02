package com.zzxmh.employeeservice.domain.dept;

import lombok.Data;

@Data
public class Dept {
    private Integer deptId;

    private String deptName;

    private String deptPrefix;

    private String deptLoc;

    private String state;

    private String deptFunction;

}
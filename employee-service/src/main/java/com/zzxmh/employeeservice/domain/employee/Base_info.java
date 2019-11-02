package com.zzxmh.employeeservice.domain.employee;

import lombok.Data;

@Data
public class Base_info {
    private String userId;

    private String eName;

    private String email;

    private String tel;

    private String sid;

    private String nation;

    private String liveCity;

    private String gender;

    private Double sal;

    private Integer levelId;
}
package com.zzxmh.employeeservice.domain.dept;

import lombok.Data;

@Data
public class Level {
    private Integer levelId;

    private String levelName;

    private Double maxSal;

    private Double floatRate;

    private String state;
}
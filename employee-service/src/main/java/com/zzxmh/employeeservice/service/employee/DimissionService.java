package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.domain.employee.Dimission;

import java.util.List;

public interface DimissionService {
    String getMaxUserID(String prefix);
    String getMinUserID(String prefix);
    boolean deleteByUserId(String userId);
    boolean insert(List<Dimission> record);
}

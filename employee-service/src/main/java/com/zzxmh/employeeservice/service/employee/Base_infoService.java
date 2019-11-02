package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.domain.employee.Base_info;

import java.util.List;

public interface Base_infoService {
    boolean insertSelective(Base_info record);
    Base_info selectByPrimaryKey(String userId);
    List<Base_info> getAlldatas();
}

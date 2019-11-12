package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.domain.employee.Detail_info;

public interface Detail_infoService {
    boolean insertSelective(Detail_info record);
    Detail_info selectByPrimaryKey(String userId);
    boolean deleteByPrimaryKey(String userId);
    boolean updateByPrimaryKeySelective(Detail_info record);
}

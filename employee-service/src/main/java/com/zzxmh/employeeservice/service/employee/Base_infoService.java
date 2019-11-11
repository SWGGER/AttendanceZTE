package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.domain.employee.Base_info;

import java.util.List;
import java.util.Map;

public interface Base_infoService {
    boolean insertSelective(Base_info record);
    Base_info selectByPrimaryKey(String userId);
    List<Base_info> getAlldatas(Map<String,Object> page);
    int getTotal();
    boolean deleteByPrimaryKey(String userId);
    List<Map<String,Object>> getfuzzyAlldatas(Map<String,Object> map);
    int getfuzzyTotal(String searchtext);
}

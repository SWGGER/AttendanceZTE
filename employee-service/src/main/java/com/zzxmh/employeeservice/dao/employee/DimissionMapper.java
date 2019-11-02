package com.zzxmh.employeeservice.dao.employee;

import com.zzxmh.employeeservice.domain.employee.Dimission;
import org.springframework.stereotype.Repository;

@Repository
public interface DimissionMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Dimission record);

    int insertSelective(Dimission record);

    Dimission selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Dimission record);

    int updateByPrimaryKey(Dimission record);
    //获取最新员工工号
    String getMaxUserID(String prefix);
    //获取最小员工工号
    String getMinUserID(String prefix);
}
package com.zzxmh.employeeservice.dao.employee;

import com.zzxmh.employeeservice.domain.employee.Dimission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimissionMapper {

    Dimission selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Dimission record);
    //获取最新员工工号
    String getMaxUserID(String prefix);
    //获取最小员工工号
    String getMinUserID(String prefix);
    //根据user_id删除表格
    int deleteByUserId(String userId);
    //插入离职员工信息
    int insert(List<Dimission> record);
}
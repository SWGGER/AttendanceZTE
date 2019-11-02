package com.zzxmh.employeeservice.dao.employee;

import com.zzxmh.employeeservice.domain.employee.Base_info;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Base_infoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Base_info record);

    //插入基本信息
    int insertSelective(Base_info record);

    //根据user_id获取基本信息
    Base_info selectByPrimaryKey(String userId);

    //查询所有基本信息
    List<Base_info> getAlldatas();

    int updateByPrimaryKeySelective(Base_info record);

    int updateByPrimaryKey(Base_info record);
}
package com.zzxmh.userservice.dao.employee;

import com.zzxmh.userservice.domain.employee.Base_info;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Base_infoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Base_info record);

    int insertSelective(Base_info record);

    Base_info selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Base_info record);

    int updateByPrimaryKey(Base_info record);

    List<Base_info>  fuzzyselectNameandId(String context);//根据输入框展示用户名和工号
}
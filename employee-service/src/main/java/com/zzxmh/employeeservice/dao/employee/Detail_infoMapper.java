package com.zzxmh.employeeservice.dao.employee;

import com.zzxmh.employeeservice.domain.employee.Detail_info;
import org.springframework.stereotype.Repository;

@Repository
public interface Detail_infoMapper {
    //根据user_id删除详细信息表
    int deleteByPrimaryKey(String userId);

    //插入详细信息
    int insertSelective(Detail_info record);

    //跟很有用user_id查询详细信息
    Detail_info selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Detail_info record);

    int updateByPrimaryKey(Detail_info record);
}
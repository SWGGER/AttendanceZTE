package com.zzxmh.employeeservice.dao.employee;

import com.zzxmh.employeeservice.domain.employee.Base_info;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Base_infoMapper {
    //根据user_id删除基本信息
    int deleteByPrimaryKey(String userId);

    //插入基本信息
    int insertSelective(Base_info record);

    //根据user_id获取基本信息
    Base_info selectByPrimaryKey(String userId);

    //查询所有基本信息
    List<Base_info> getAlldatas(Map<String,Object> page);

    //获取总记录数
    int getTotal();

    //获取模糊查询的信息
    List<Map<String,Object>> getfuzzyAlldatas(Map<String,Object> map);

    //获取模糊查询总记录数
    int getfuzzyTotal(String searchtext);

    //更新baseinfo表数据
    int updateByPrimaryKeySelective(Base_info record);

    int updateByPrimaryKey(Base_info record);

    //根据员工编号查找该员工的所有信息
    Map<String,Object> getDataByUserID(String userId);

    //查找是否有同样的sid
    String chechsid(String sid);

    //是否有南京研发部的java工程师这个岗位？  address dept job
    List<String> existaddressdeptjob(Map<String,Object> map);

    //根据sid查找到userid
    String findsueridbysid(String sid);

}
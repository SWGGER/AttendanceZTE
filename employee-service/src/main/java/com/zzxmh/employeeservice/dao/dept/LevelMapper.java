package com.zzxmh.employeeservice.dao.dept;

import com.zzxmh.employeeservice.domain.dept.Level;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelMapper {
    int deleteByPrimaryKey(Integer levelId);

    int insert(Level record);

    int insertSelective(Level record);

    //根据level_name获取level_id
    int selectLevelName(String levelName);

    //根据level_i查询level_name
    String selectByPrimaryKey(int levelId);

    //判断基本工资是否小于最大薪资
    Level judgeSal(Level record);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}
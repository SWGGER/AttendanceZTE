package com.zzxmh.userservice.dao.dept;

import com.zzxmh.userservice.domain.dept.Level;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LevelMapper {
    int deleteByPrimaryKey(Integer levelId);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Integer levelId);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);

    List<Level> getAllLevel();
}
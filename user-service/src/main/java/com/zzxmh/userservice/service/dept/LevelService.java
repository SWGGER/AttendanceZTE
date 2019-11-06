package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept_role_level;
import com.zzxmh.userservice.domain.dept.Level;

import java.util.List;

public interface LevelService {
    List<Level> getAllLevel();
    Level selectByLevelname(String levelname);

    int insertSelective(Dept_role_level record);
}

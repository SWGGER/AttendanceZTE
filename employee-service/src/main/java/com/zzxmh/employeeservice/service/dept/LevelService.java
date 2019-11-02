package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.domain.dept.Level;

public interface LevelService {
    int selectLevelName(String levelName);
    String selectByPrimaryKey(int levelId);
    Level judgeSal(Level record);
}

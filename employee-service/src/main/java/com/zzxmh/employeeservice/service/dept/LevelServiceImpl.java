package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.dao.dept.LevelMapper;
import com.zzxmh.employeeservice.domain.dept.Level;
import com.zzxmh.employeeservice.service.dept.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelMapper levelMapper;
    @Override
    public int selectLevelName(String levelName) {
        return levelMapper.selectLevelName(levelName);
    }

    @Override
    public String selectByPrimaryKey(int levelId) {
        return levelMapper.selectByPrimaryKey(levelId);
    }

    @Override
    public Level judgeSal(Level record) {
        return levelMapper.judgeSal(record);
    }
}

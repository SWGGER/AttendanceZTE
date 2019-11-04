package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.LevelMapper;
import com.zzxmh.userservice.domain.dept.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Primary
@Slf4j
@Transactional
public class LevelServiceImpl implements  LevelService {
    @Autowired
    private LevelMapper levelMapper;
    @Override
    public List<Level> getAllLevel() {
        return levelMapper.getAllLevel();
    }
}

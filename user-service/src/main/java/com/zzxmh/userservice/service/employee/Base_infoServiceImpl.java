package com.zzxmh.userservice.service.employee;

import com.zzxmh.userservice.dao.employee.Base_infoMapper;
import com.zzxmh.userservice.domain.employee.Base_info;
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
public class Base_infoServiceImpl implements Base_infoService {
    @Autowired
    private Base_infoMapper base_infoMapper;
    @Override
    public List<Base_info> fuzzyselectNameandId(String context) {
        return base_infoMapper.fuzzyselectNameandId(context);
    }
}

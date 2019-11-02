package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.dao.employee.DimissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DimissonServiceImpl implements DimissionService{
    @Autowired
    private DimissionMapper dimissionMapper;
    @Override
    public String getMaxUserID(String prefix) {
        return dimissionMapper.getMaxUserID(prefix);
    }

    @Override
    public String getMinUserID(String prefix) {
        return dimissionMapper.getMinUserID(prefix);
    }
}

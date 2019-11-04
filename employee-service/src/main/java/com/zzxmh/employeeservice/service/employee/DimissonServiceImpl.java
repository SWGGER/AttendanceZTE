package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.dao.employee.DimissionMapper;
import com.zzxmh.employeeservice.domain.employee.Dimission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean deleteByUserId(String userId) {
        int count=dimissionMapper.deleteByUserId(userId);
        if (count>0)
            return true;
        return false;
    }

    @Override
    public boolean insert(List<Dimission> record) {
        int count=dimissionMapper.insert(record);
        if (count>0)
            return true;
        return false;
    }
}

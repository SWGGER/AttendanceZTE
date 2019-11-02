package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.dao.employee.Base_infoMapper;
import com.zzxmh.employeeservice.domain.employee.Base_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class Base_infoServiceImpl implements Base_infoService{

    @Autowired
    private Base_infoMapper base_infoMapper;
    @Override
    public boolean insertSelective(Base_info record) {
        int count=base_infoMapper.insertSelective(record);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public Base_info selectByPrimaryKey(String userId) {
        return base_infoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Base_info> getAlldatas() {
        return base_infoMapper.getAlldatas();
    }
}

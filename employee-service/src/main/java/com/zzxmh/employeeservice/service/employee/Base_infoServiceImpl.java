package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.dao.employee.Base_infoMapper;
import com.zzxmh.employeeservice.domain.employee.Base_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Base_info> getAlldatas(Map<String,Object> page) {
        return base_infoMapper.getAlldatas(page);
    }

    @Override
    public int getTotal() {
        return base_infoMapper.getTotal();
    }

    @Override
    public boolean deleteByPrimaryKey(String userId) {
        int count=base_infoMapper.deleteByPrimaryKey(userId);
        if (count>0)
            return true;
        return false;
    }

    @Override
    public List<Map<String, Object>> getfuzzyAlldatas(Map<String, Object> map) {
        return base_infoMapper.getfuzzyAlldatas(map);
    }

    @Override
    public int getfuzzyTotal(String searchtext) {
        return base_infoMapper.getfuzzyTotal(searchtext);
    }
}

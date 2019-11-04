package com.zzxmh.employeeservice.service.employee;

import com.zzxmh.employeeservice.dao.employee.Detail_infoMapper;
import com.zzxmh.employeeservice.domain.employee.Detail_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Detail_infoServiceImpl implements Detail_infoService{

    @Autowired
    private Detail_infoMapper detail_infoMapper;
    @Override
    public boolean insertSelective(Detail_info record) {
        int count=detail_infoMapper.insertSelective(record);
        if (count>0)
            return true;
        return false;
    }

    @Override
    public Detail_info selectByPrimaryKey(String userId) {
        return detail_infoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean deleteByPrimaryKey(String userId) {
        int count=detail_infoMapper.deleteByPrimaryKey(userId);
        if (count>0)
            return true;
        return false;
    }
}

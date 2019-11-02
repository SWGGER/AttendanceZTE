package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.dao.dept.DeptMapper;
import com.zzxmh.employeeservice.domain.dept.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public Dept selectByNameAndLoc(Dept record) {
        return deptMapper.selectByNameAndLoc(record);
    }

    @Override
    public List<String> getDeptName() {
        return deptMapper.getDeptName();
    }

    @Override
    public List<String> getLOCByDeptName(String deptName) {
        return deptMapper.getLOCByDeptName(deptName);
    }

    @Override
    public Dept selectByPrimaryKey(int deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

}

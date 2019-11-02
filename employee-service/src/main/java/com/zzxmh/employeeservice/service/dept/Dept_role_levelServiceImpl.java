package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.dao.dept.Dept_role_levelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class Dept_role_levelServiceImpl implements Dept_role_levelService{
    @Autowired
    private Dept_role_levelMapper dept_role_levelMapper;
    @Override
    public List<Integer> selectDeptRoleId(int deptRoleId) {
        return dept_role_levelMapper.selectDeptRoleId(deptRoleId);
    }
}

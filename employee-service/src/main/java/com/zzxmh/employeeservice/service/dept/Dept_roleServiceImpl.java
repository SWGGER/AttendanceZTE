package com.zzxmh.employeeservice.service.dept;

import com.zzxmh.employeeservice.dao.dept.Dept_roleMapper;
import com.zzxmh.employeeservice.domain.dept.Dept_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Primary
public class Dept_roleServiceImpl implements Dept_roleService{
    @Autowired
    private Dept_roleMapper dept_roleMapper;

    @Override
    public Dept_role selectByDeptAndRole(Dept_role record) {
        return dept_roleMapper.selectByDeptAndRole(record);
    }

    @Override
    public List<Integer> selectByDeptId(int deptId) {
        return dept_roleMapper.selectByDeptId(deptId);
    }

    @Override
    public Dept_role selectByPrimaryKey(int id) {
        return dept_roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int finddeptroleidbyinfo(Map<String, Object> map) {
        return dept_roleMapper.finddeptroleidbyinfo(map);
    }
}

package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.dao.dept.DeptMapper;
import com.zzxmh.userservice.dao.dept.Dept_roleMapper;
import com.zzxmh.userservice.dao.user.UserMapper;
import com.zzxmh.userservice.domain.dept.Dept;
import com.zzxmh.userservice.domain.dept.Dept_role;
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
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    Dept_roleMapper dept_roleMapper;
    @Override
    public boolean insertSelective(Dept record) {
        int count=deptMapper.insertSelective(record);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean recheckDept(Dept dept) {
        int count=deptMapper.recheckDept(dept);
        if(count==0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Dept> getDeptInfo(Dept dept) {
        return deptMapper.getDeptInfo(dept);
    }

    @Override
    public Dept_role selectByRoleandDept(Dept_role record) {
        return dept_roleMapper.selectByRoleandDept(record);
    }

    @Override
    public int insertSelective(Dept_role record) {
        return dept_roleMapper.insertSelective(record);
    }

    @Override
    public Dept selLastData() {
        return deptMapper.selLastData();
    }

    @Override
    public Dept selectByPrimaryKey(int deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<Dept> fuzzyselectDeptnameAndLoc(String deptName) {
        return deptMapper.fuzzyselectDeptnameAndLoc(deptName);
    }

    @Override
    public Dept selectDeptId(Dept dept) {
        return deptMapper.selectDeptId(dept);
    }

    @Override
    public Dept selectByNameAndLoc(Dept record) {
        return deptMapper.selectByNameAndLoc(record);
    }
}

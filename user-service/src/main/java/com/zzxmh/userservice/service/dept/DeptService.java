package com.zzxmh.userservice.service.dept;

import com.zzxmh.userservice.domain.dept.Dept;

public interface DeptService {
    boolean insertSelective(Dept record);

    boolean recheckDept(Dept dept);
}

package com.zzxmh.userservice.service.employee;

import com.zzxmh.userservice.domain.employee.Base_info;

import java.util.List;

public interface Base_infoService {
    List<Base_info> fuzzyselectNameandId(String context);//根据输入框展示用户名和工号
}

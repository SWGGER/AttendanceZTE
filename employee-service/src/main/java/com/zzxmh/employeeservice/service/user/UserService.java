package com.zzxmh.employeeservice.service.user;

import com.zzxmh.employeeservice.domain.user.User;

public interface UserService {
    boolean insert(User record);

    String getMaxUserID(String prefix);
}

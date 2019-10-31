package com.zzxmh.userservice.service.user;

import com.zzxmh.userservice.domain.user.User;

public interface UserService {
    User getuserInfoByLoginId(String userId);//根据loginid找password
}

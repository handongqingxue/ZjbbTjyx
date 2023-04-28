package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.UserList;

public interface UserListService {
    //登陆
    UserList getUser(UserList user);

    //注册用户
    int addUser(UserList user);
}

package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.UserList;

public interface UserListMapper {

    //登陆
    UserList getUser(UserList user);

    //注册用户
    int addUser(UserList user);
}

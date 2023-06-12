package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.UserList;

import java.util.List;

public interface UserListService {
    //登陆
    UserList getUser(UserList user);

    //注册用户
    int addUser(UserList user);

    //删除用户
    int delUser(List<Integer> IdList);

    //全部用户
    List<UserList> getUserList();
}

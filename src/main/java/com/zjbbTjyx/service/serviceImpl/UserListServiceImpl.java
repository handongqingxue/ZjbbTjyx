package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.dao.UserListMapper;
import com.zjbbTjyx.entity.UserList;
import com.zjbbTjyx.service.UserListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListMapper userListMapper;

    public UserList getUser(UserList user) {
        return userListMapper.getUser(user);
    }

    public int addUser(UserList user) {
        return userListMapper.addUser(user);
    }
}

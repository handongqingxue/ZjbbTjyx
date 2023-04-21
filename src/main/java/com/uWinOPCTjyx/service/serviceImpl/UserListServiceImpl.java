package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.UserListMapper;
import com.uWinOPCTjyx.entity.UserList;
import com.uWinOPCTjyx.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListMapper userListMapper;

    public UserList login(UserList user) {
        return userListMapper.login(user);
    }

    public int addUser(UserList user) {
        return userListMapper.addUser(user);
    }
}

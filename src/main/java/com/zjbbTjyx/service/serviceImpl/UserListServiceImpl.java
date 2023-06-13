package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.dao.UserListMapper;
import com.zjbbTjyx.entity.UserList;
import com.zjbbTjyx.service.UserListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int delUser(List<Integer> IdList) {
        return userListMapper.delUser(IdList);
    }

    @Override
    public List<UserList> getUserList(String UserName,String RealName) {
        return userListMapper.getUserList(UserName,RealName);
    }
}

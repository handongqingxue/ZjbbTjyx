package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserListService {
    //登陆
    UserList getUser(UserList user);

    //注册用户
    int addUser(UserList user);

    //删除用户
    int delUser(List<Integer> IdList);

    //全部用户
    List<UserList> getUserList(String UserName,String RealName);

    //通过用户名查询改用户
    UserList getUserByUserName(String UserName);

    //添加用户和角色的关系表
    int addUserRole(Integer UId,Integer[] RIds);

    //删除用户和角色的关系表
    int delUserRole(Integer UId);

    //通过id查询用户
    UserList getUserById(Integer Id);

    //修改用户信息
    int editUser(UserList user);

    //通过userid删除user_role关系表里面的信息
    int delUserRoleByUserId(Integer Id);
}

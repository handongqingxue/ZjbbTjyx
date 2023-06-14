package com.zjbbTjyx.dao;

import com.zjbbTjyx.entity.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserListMapper {

    //登陆
    UserList getUser(UserList user);

    //注册用户
    int addUser(UserList user);

    //删除用户
    int delUser(@Param("idList") List<Integer> idList);

    //全部用户
    List<UserList> getUserList(@Param("UserName") String UserName,@Param("RealName") String RealName);

    //通过用户名查询改用户
    UserList getUserByUserName(@Param("UserName") String UserName);

    //添加用户和角色的关系表
    int addUserRole(@Param("UId") Integer UId,@Param("RId") Integer RId);

    //删除用户和角色的关系表
    int delUserRole(@Param("UId") Integer UId);
}

package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    //查询全部角色
    List<Role> getRoleList(String RoleName,String Detail);

    //查询单个角色
    Role getRoleByRoleId(Integer Id);

    //通过名称查询角色
    Role getRoleByName(String RoleName);

    //删除角色
    int delRole(List<Integer> idList);

    //修改角色
    int editRole(Role role);

    //添加角色
    int addRole(Role role);

    //通过用户id查询用户的角色
    List<Role> getRoleByUserId(Integer Id);

    //通过userid删除user_role关系表里面的信息
    int delUserRoleByUserId(Integer Id);

    //添加用户id和角色id到关系表
    int addUserRole(Integer UId,Integer RId);
}

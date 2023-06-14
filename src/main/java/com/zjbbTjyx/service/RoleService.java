package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.Role;

import java.util.List;

public interface RoleService {
    //查询全部角色
    List<Role> getRoleList(String RoleName,String Detail);


    //查询单个角色
    Role getRoleById(Integer Id);
}

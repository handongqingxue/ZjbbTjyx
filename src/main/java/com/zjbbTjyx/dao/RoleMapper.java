package com.zjbbTjyx.dao;

import com.zjbbTjyx.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //查询全部角色(多条件模糊查询)
    List<Role> getRoleList(@Param("RoleName") String RoleName,@Param("Detail") String Detail);

    //通过id查询单个角色
    Role getRoleById(Integer Id);

    //通过名称查询角色
    Role getRoleByName(@Param("RoleName") String RoleName);

    //删除角色
    int delRole(@Param("idList") List<Integer> idList);

    //修改角色
    int editRole(@Param("role") Role role);

    //添加角色
    int addRole(@Param("role") Role role);

    //通过用户id查询用户的角色
    List<Role> getRoleByUserId(@Param("Id") Integer Id);

}

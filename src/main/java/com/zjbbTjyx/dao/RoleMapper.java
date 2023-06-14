package com.zjbbTjyx.dao;

import com.zjbbTjyx.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //查询全部角色
    List<Role> getRoleList(@Param("RoleName") String RoleName,@Param("Detail") String Detail);


    //查询单个角色
    Role getRoleById(Integer Id);
}

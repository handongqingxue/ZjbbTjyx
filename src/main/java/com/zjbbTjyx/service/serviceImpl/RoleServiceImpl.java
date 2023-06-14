package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.dao.RoleMapper;
import com.zjbbTjyx.entity.Role;
import com.zjbbTjyx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleList(String RoleName,String Detail) {
        return roleMapper.getRoleList(RoleName, Detail);

    }

    @Override
    public Role getRoleById(Integer Id) {
        return roleMapper.getRoleById(Id);
    }
}

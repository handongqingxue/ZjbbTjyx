package com.zjbbTjyx.entity;

public class Role {
    private Integer Id;
    private String RoleName;
    private String Detail;

    public Role() {
    }

    public Role(Integer id, String roleName, String detail) {
        Id = id;
        RoleName = roleName;
        Detail = detail;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}

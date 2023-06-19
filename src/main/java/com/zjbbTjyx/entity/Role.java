package com.zjbbTjyx.entity;

public class Role {
    private Integer Id;
    private String RoleName;
    private String Detail;
    private String CTime;

    public Role() {
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

    public String getCTime() {
        return CTime;
    }

    public void setCTime(String CTime) {
        this.CTime = CTime;
    }

    public Role(Integer id, String roleName, String detail, String CTime) {
        Id = id;
        RoleName = roleName;
        Detail = detail;
        this.CTime = CTime;
    }

//    @Override
//    public String toString() {
//        return "Role{" +
//                "Id=" + Id +
//                ", RoleName='" + RoleName + '\'' +
//                ", Detail='" + Detail + '\'' +
//                ", CTime='" + CTime + '\'' +
//                '}';
//    }
}

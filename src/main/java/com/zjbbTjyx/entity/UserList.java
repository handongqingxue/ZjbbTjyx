package com.zjbbTjyx.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserList implements Serializable {
    private Integer Id;
    private String UserName;
    private String RealName;
    private String Psd;
    private String CTime;
    private Integer Type;

    public UserList(String userName, String psd) {

        UserName = userName;
        Psd = psd;

    }

    public UserList() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getPsd() {
        return Psd;
    }

    public void setPsd(String psd) {
        Psd = psd;
    }

    public String getCTime() {
        return CTime;
    }

    public void setCTime(String CTime) {
        this.CTime = CTime;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public UserList(Integer id, String userName, String realName, String psd, String CTime, Integer type) {
        Id = id;
        UserName = userName;
        RealName = realName;
        Psd = psd;
        this.CTime = CTime;
        Type = type;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "Id=" + Id +
                ", UserName='" + UserName + '\'' +
                ", RealName='" + RealName + '\'' +
                ", Psd='" + Psd + '\'' +
                ", CTime='" + CTime + '\'' +
                ", Type=" + Type +
                '}';
    }
}

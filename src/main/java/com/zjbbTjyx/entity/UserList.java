package com.zjbbTjyx.entity;

import java.io.Serializable;

public class UserList implements Serializable {
    private Integer Id;
    private String UserName;
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

    @Override
    public String toString() {
        return "UserList{" +
                "Id=" + Id +
                ", UserName='" + UserName + '\'' +
                ", Psd='" + Psd + '\'' +
                ", CTime='" + CTime + '\'' +
                ", Type=" + Type +
                '}';
    }
}

package com.uWinOPCTjyx.entity;

public class UserList {
    private Integer id;
    private String UserName;
    private String Psd;
    private String CTime;
    private Integer Type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", Psd='" + Psd + '\'' +
                ", CTime='" + CTime + '\'' +
                ", Type=" + Type +
                '}';
    }
}

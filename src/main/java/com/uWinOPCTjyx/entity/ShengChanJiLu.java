package com.uWinOPCTjyx.entity;

public class ShengChanJiLu {

    private Integer id;//记录id
    private Integer scbh;//生产编号
    private Integer fyfh;//反应釜号
    private String kssj;//开始时间
    private String jssj;//结束时间
    private String scgs;//生产工时
    private String scrq;//生产日期
    private String ysd101;//YSD101信息
    private String ysd102;//YSD102信息
    private String ysd103;//YSD103信息
    private String dbczy;//当班操作员
    private String jbczy;//接班操作员
    private Integer lx;//类型

    @Override
    public String toString() {
        return "ShengChanJiLu{" +
                "id=" + id +
                ", scbh=" + scbh +
                ", fyfh=" + fyfh +
                ", kssj='" + kssj + '\'' +
                ", jssj='" + jssj + '\'' +
                ", scgs='" + scgs + '\'' +
                ", scrq='" + scrq + '\'' +
                ", ysd101='" + ysd101 + '\'' +
                ", ysd102='" + ysd102 + '\'' +
                ", ysd103='" + ysd103 + '\'' +
                ", dbczy='" + dbczy + '\'' +
                ", jbczy='" + jbczy + '\'' +
                ", lx=" + lx +
                '}';
    }

    public ShengChanJiLu() {
    }

    public ShengChanJiLu(Integer id, Integer scbh, Integer fyfh, String kssj, String jssj, String scgs, String scrq, String ysd101, String ysd102, String ysd103, String dbczy, String jbczy, Integer lx) {
        this.id = id;
        this.scbh = scbh;
        this.fyfh = fyfh;
        this.kssj = kssj;
        this.jssj = jssj;
        this.scgs = scgs;
        this.scrq = scrq;
        this.ysd101 = ysd101;
        this.ysd102 = ysd102;
        this.ysd103 = ysd103;
        this.dbczy = dbczy;
        this.jbczy = jbczy;
        this.lx = lx;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScbh() {
        return scbh;
    }

    public void setScbh(Integer scbh) {
        this.scbh = scbh;
    }

    public Integer getFyfh() {
        return fyfh;
    }

    public void setFyfh(Integer fyfh) {
        this.fyfh = fyfh;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getScgs() {
        return scgs;
    }

    public void setScgs(String scgs) {
        this.scgs = scgs;
    }

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }

    public String getYsd101() {
        return ysd101;
    }

    public void setYsd101(String ysd101) {
        this.ysd101 = ysd101;
    }

    public String getYsd102() {
        return ysd102;
    }

    public void setYsd102(String ysd102) {
        this.ysd102 = ysd102;
    }

    public String getYsd103() {
        return ysd103;
    }

    public void setYsd103(String ysd103) {
        this.ysd103 = ysd103;
    }

    public String getDbczy() {
        return dbczy;
    }

    public void setDbczy(String dbczy) {
        this.dbczy = dbczy;
    }

    public String getJbczy() {
        return jbczy;
    }

    public void setJbczy(String jbczy) {
        this.jbczy = jbczy;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }
}

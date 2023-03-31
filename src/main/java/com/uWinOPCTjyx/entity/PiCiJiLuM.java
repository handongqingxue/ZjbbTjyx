package com.uWinOPCTjyx.entity;

public class PiCiJiLuM {

    private Integer id;//批次记录id
    private Integer pcId;//批次id
    private Integer csId;//参数id
    private Integer jlsjId;//记录事件id
	private String jlnr;//记录内容
	private String jlkssj;//记录开始时间
	private String jljssj;//记录结束时间
	private Float zqzl;//之前重量
	private Float zhzl;//之后重量
    private Integer jllx;//记录类型
    private Integer jdId;//阶段id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPcId() {
		return pcId;
	}
	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}
	public Integer getCsId() {
		return csId;
	}
	public void setCsId(Integer csId) {
		this.csId = csId;
	}
	public Integer getJlsjId() {
		return jlsjId;
	}
	public void setJlsjId(Integer jlsjId) {
		this.jlsjId = jlsjId;
	}
	public String getJlnr() {
		return jlnr;
	}
	public void setJlnr(String jlnr) {
		this.jlnr = jlnr;
	}
	public String getJlkssj() {
		return jlkssj;
	}
	public void setJlkssj(String jlkssj) {
		this.jlkssj = jlkssj;
	}
	public String getJljssj() {
		return jljssj;
	}
	public void setJljssj(String jljssj) {
		this.jljssj = jljssj;
	}
	public Float getZqzl() {
		return zqzl;
	}
	public void setZqzl(Float zqzl) {
		this.zqzl = zqzl;
	}
	public Float getZhzl() {
		return zhzl;
	}
	public void setZhzl(Float zhzl) {
		this.zhzl = zhzl;
	}
	public Integer getJllx() {
		return jllx;
	}
	public void setJllx(Integer jllx) {
		this.jllx = jllx;
	}
	public Integer getJdId() {
		return jdId;
	}
	public void setJdId(Integer jdId) {
		this.jdId = jdId;
	}
}

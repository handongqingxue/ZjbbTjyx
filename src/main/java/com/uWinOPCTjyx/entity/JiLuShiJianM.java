package com.uWinOPCTjyx.entity;

public class JiLuShiJianM {

	public static final String JIA_LIAO_LIANG="加料量";
	public static final String SHI_JIAN_CHA="时间差";
	public static final String ZHONG_LIANG_CHA="重量差";
	
	private Integer id;//事件id
	private String mc;//名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
}

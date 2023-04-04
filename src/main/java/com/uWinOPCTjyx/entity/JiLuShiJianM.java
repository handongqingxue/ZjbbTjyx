package com.uWinOPCTjyx.entity;

public class JiLuShiJianM {

	public static final String JIA_LIAO_LIANG_TEXT="加料量";
	public static final String SHI_JIAN_CHA_TEXT="时间差";
	public static final String ZHONG_LIANG_CHA_TEXT="重量差";
	public static final String PI_CI_TEXT="批次";
	public static final String WEN_DU_TEXT="温度";
	
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

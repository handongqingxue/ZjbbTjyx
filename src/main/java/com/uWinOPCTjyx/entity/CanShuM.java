package com.uWinOPCTjyx.entity;

public class CanShuM {

	/**
	 * 甲醛实际进料重量参数名称
	 */
	public static final String JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT="甲醛实际进料重量";
	/**
	 * 加水实际重量参数名称
	 */
	public static final String JIA_SHUI_SHI_JI_ZHONG_LIANG_TEXT="加水实际重量";
	/**
	 * 反应釜温度参数名称
	 */
	public static final String FAN_YING_FU_WEN_DU_TEXT="反应釜温度";
	/**
	 * 加碱前PH参数名称
	 */
	public static final String JIA_JIAN_QIAN_PH_TEXT="加碱前PH";
	
	private Integer id;//参数id
	private String mc;//名称
	private String dw;//单位
	private Integer lx;//类型
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
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public Integer getLx() {
		return lx;
	}
	public void setLx(Integer lx) {
		this.lx = lx;
	}
}

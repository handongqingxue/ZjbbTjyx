package com.uWinOPCTjyx.entity;

public class JiLuShiJianM {

	/**
	 * 加料量记录事件名称
	 */
	public static final String JIA_LIAO_LIANG_TEXT="加料量";
	/**
	 * 时间差记录事件名称
	 */
	public static final String SHI_JIAN_CHA_TEXT="时间差";
	/**
	 * 重量差记录事件名称
	 */
	public static final String ZHONG_LIANG_CHA_TEXT="重量差";
	/**
	 * 批次记录事件名称
	 */
	public static final String PI_CI_TEXT="批次";
	/**
	 * 温度记录事件名称
	 */
	public static final String WEN_DU_TEXT="温度";
	/**
	 * Ph值记录事件名称
	 */
	public static final String PH_ZHI_TEXT="Ph值";
	/**
	 * 称重记录事件名称
	 */
	public static final String CHENG_ZHONG_TEXT="称重";
	
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

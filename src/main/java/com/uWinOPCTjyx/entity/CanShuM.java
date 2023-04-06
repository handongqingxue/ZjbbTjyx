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
	/**
	 * 加碱量参数名称
	 */
	public static final String JIA_JIAN_LIANG_TEXT="加碱量";
	/**
	 * 加碱后PH参数名称
	 */
	public static final String JIA_JIAN_HOU_PH_TEXT="加碱后PH";
	/**
	 * 助剂计量罐1-2称重参数名称
	 */
	public static final String ZHU_JI_JI_LIANG_GUAN_1_2_CHENG_ZHONG_TEXT="助剂计量罐1-2称重";
	/**
	 * 助剂计量罐3-5称重参数名称
	 */
	public static final String ZHU_JI_JI_LIANG_GUAN_3_5_CHENG_ZHONG_TEXT="助剂计量罐3-5称重";
	/**
	 * 粉料重量参数名称
	 */
	public static final String FEN_LIAO_ZHONG_LIANG_TEXT="粉料重量";
	
	/**
	 * 物料参数
	 */
	public static final int WU_LIAO_CAN_SHU=1;
	/**
	 * 工艺参数
	 */
	public static final int GONG_YI_CAN_SHU=2;
	
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

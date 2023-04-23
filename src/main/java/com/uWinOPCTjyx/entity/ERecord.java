package com.uWinOPCTjyx.entity;

import com.uWinOPCTjyx.util.Constant;

public class ERecord {
	
	/**
	 * 未生成报表
	 */
	public static final int WSCBB=0;
	/**
	 * 已生成报表
	 */
	public static final int YSCBB=1;

	/**
	 * M类配方类型
	 */
	public static final String M="M";
	/**
	 * U类配方类型
	 */
	public static final String U="U";
	
	/**
	 * 备料开始上升沿时间
	 */
	public static final String BLKSSSYSJ=Constant.BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 开始到结束时间
	 */
	public static final String KSDJSSJ=Constant.KAI_SHI+Constant.DAO+Constant.JIE_SHU+Constant.SHI_JIAN;
	/**
	 * 反应结束上升沿时间
	 */
	public static final String FYJSSSYSJ=Constant.FAN_YING_JIE_SHU+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 备料开始到反应结束时间差
	 */
	public static final String BLKSDFYJSSJC=Constant.BEI_LIAO_KAI_SHI+Constant.DAO+Constant.FAN_YING_JIE_SHU+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 甲醛备料开始上升沿时间
	 */
	public static final String JQBLKSSSYSJ=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 甲醛备料开始到甲醛放料完成时间
	 */
	public static final String JQBLKSDJQFLWCSJ=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN;
	/**
	 * 甲醛备料开始上升沿釜称重
	 */
	public static final String JQBLKSSSYFCZ=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 甲醛备料开始到甲醛放料完成釜重量
	 */
	public static final String JQBLKSDJQFLWCFZL=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG;
	/**
	 * 甲醛放料完成上升沿釜称重
	 */
	public static final String JQFLWCSSYFCZ=Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 甲醛备料开始到甲醛放料完成釜重量差
	 */
	public static final String JQBLKSDJQFLWCFZLC=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;
	/**
	 * 甲醛备料开始到甲醛放料完成时间差
	 */
	public static final String JQBLKSDJQFLWCSJC=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 允许一次加助剂上升沿时间
	 */
	public static final String YXYCJZJSSYSJ=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;

	private Integer Id;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getVarName() {
		return VarName;
	}
	public void setVarName(String varName) {
		VarName = varName;
	}
	public String getVarValue() {
		return VarValue;
	}
	public void setVarValue(String varValue) {
		VarValue = varValue;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getPreValue() {
		return PreValue;
	}
	public void setPreValue(String preValue) {
		PreValue = preValue;
	}
	public String getNxtValue() {
		return NxtValue;
	}
	public void setNxtValue(String nxtValue) {
		NxtValue = nxtValue;
	}
	public String getPtnValue() {
		return PtnValue;
	}
	public void setPtnValue(String ptnValue) {
		PtnValue = ptnValue;
	}
	public String getRecType() {
		return RecType;
	}
	public void setRecType(String recType) {
		RecType = recType;
	}
	public Integer getFId() {
		return FId;
	}
	public void setFId(Integer fId) {
		FId = fId;
	}
	public String getRecordTime() {
		return RecordTime;
	}
	public void setRecordTime(String recordTime) {
		RecordTime = recordTime;
	}
	public String getBatchID() {
		return BatchID;
	}
	public void setBatchID(String batchID) {
		BatchID = batchID;
	}
	public String getPhaseName() {
		return PhaseName;
	}
	public void setPhaseName(String phaseName) {
		PhaseName = phaseName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	private String VarName;
	private String VarValue;
	private String Unit;
	private String PreValue;
	private String NxtValue;
	private String PtnValue;
	private String RecType;
	private Integer FId;
	private String RecordTime;
	private String BatchID;
	private String PhaseName;
	private String Remark;
}

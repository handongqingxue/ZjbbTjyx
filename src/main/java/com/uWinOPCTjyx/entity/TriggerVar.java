package com.uWinOPCTjyx.entity;

public class TriggerVar {

	/**
	 * 已上升
	 */
	public static final int UP=1;
	/**
	 * 已下降
	 */
	public static final int DOWN=0;
	
	/**
	 * M类配方类型
	 */
	public static final String M="M";
	/**
	 * U类配方类型
	 */
	public static final String U="U";
	/**
	 * MU类配方类型
	 */
	public static final String MU="MU";
	
	private Integer Id;
	private String VarName;
	private Float VarValue;
	private String RecType;
	private Integer FId;
	private String Desc;

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
	public Float getVarValue() {
		return VarValue;
	}
	public void setVarValue(Float varValue) {
		VarValue = varValue;
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
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}

	@Override
	public String toString() {
		return "TriggerVar{" +
				"Id=" + Id +
				", VarName='" + VarName + '\'' +
				", VarValue=" + VarValue +
				", RecType='" + RecType + '\'' +
				", FId=" + FId +
				", Desc='" + Desc + '\'' +
				'}';
	}
}

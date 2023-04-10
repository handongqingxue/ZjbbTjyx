package com.uWinOPCTjyx.entity;

public class TriggerVar {

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
	private String VarName;
	private Float VarValue;
	private String RecType;
	private Integer FId;
	private String Desc;
}

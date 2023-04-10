package com.uWinOPCTjyx.entity;

public class ProcessVar {

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
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getRecType() {
		return RecType;
	}
	public void setRecType(String recType) {
		RecType = recType;
	}
	public Integer getDealBz() {
		return DealBz;
	}
	public void setDealBz(Integer dealBz) {
		DealBz = dealBz;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public Integer getParaType() {
		return ParaType;
	}
	public void setParaType(Integer paraType) {
		ParaType = paraType;
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
	private String Unit;
	private String RecType;
	private Integer DealBz;
	private String UpdateTime;
	private Integer ParaType;
	private Integer FId;
	private String Desc;
}

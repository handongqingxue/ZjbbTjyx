package com.uWinOPCTjyx.entity;

public class ERecord {

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
	private String RecordTime;
	private String BatchID;
	private String PhaseName;
	private String Remark;
}

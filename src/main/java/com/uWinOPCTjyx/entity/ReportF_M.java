package com.uWinOPCTjyx.entity;

public class ReportF_M {
    private Integer Id;
    private String RowNumber;//行号
    private String ColNumber;//列号
    private String Value;//值
    private String BatchID;//生产编号
    private String CreateTime;//创建时间
    private String Remark;//备注

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(String rowNumber) {
        RowNumber = rowNumber;
    }

    public String getColNumber() {
        return ColNumber;
    }

    public void setColNumber(String colNumber) {
        ColNumber = colNumber;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getBatchID() {
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}

package com.uWinOPCTjyx.entity;

public class ReportF_M {
	
	/**
	 * 自动表单设计行号
	 */
	public static final int ZDBDSJ_RN=1;
	/**
	 * 自动表单设计列号
	 */
	public static final int ZDBDSJ_CN=1;

	/**
	 * 设计号行号
	 */
	public static final int SJH_RN=1;
	/**
	 * 设计号列号
	 */
	public static final int SJH_CN=2;
	
	/**
	 * 生产编号行号
	 */
	public static final int SCBH_RN=3;
	/**
	 * 生产编号列号
	 */
	public static final int SCBH_CN=2;
	
	/**
	 * 反应釜行号
	 */
	public static final int FYF_RN=3;
	/**
	 * 反应釜列号
	 */
	public static final int FYF_CN=4;
	
	/**
	 * 开始时间行号
	 */
	public static final int KSSJ_RN=3;
	/**
	 * 开始时间列号
	 */
	public static final int KSSJ_CN=6;
	
	/**
	 * 结束时间行号
	 */
	public static final int JSSJ_RN=3;
	/**
	 * 结束时间列号
	 */
	public static final int JSSJ_CN=8;

	/**
	 * 生产工时行号
	 */
	public static final int SCGS_RN=3;
	/**
	 * 生产工时列号
	 */
	public static final int SCGS_CN=10;

	/**
	 * 生产日期行号
	 */
	public static final int SCRQ_RN=3;
	/**
	 * 生产日期列号
	 */
	public static final int SCRQ_CN=12;
	
	/**
	 * 1号罐用前重行号
	 */
	public static final int GYQZ1_RN=5;
	/**
	 * 1号罐用前重列号
	 */
	public static final int GYQZ1_CN=2;
	
	/**
	 * 1号罐用后重行号
	 */
	public static final int GYHZ1_RN=5;
	/**
	 * 1号罐用后重列号
	 */
	public static final int GYHZ1_CN=4;
	
	/**
	 * 2号罐用前重行号
	 */
	public static final int GYQZ2_RN=5;
	/**
	 * 2号罐用前重列号
	 */
	public static final int GYQZ2_CN=6;

	/**
	 * 2号罐用后重行号
	 */
	public static final int GYHZ2_RN=5;
	/**
	 * 2号罐用后重列号
	 */
	public static final int GYHZ2_CN=8;
	
	/**
	 * 甲醛实际进料重量行号
	 */
	public static final int JQSJJLZL_RN=7;
	/**
	 * 甲醛实际进料重量列号
	 */
	public static final int JQSJJLZL_CN=2;
	
	/**
	 * 加水实际重量行号
	 */
	public static final int JSSJZL_RN=8;
	/**
	 * 加水实际重量列号
	 */
	public static final int JSSJZL_CN=2;
	
	/**
	 * 甲醛备料开始时间行号
	 */
	public static final int JQBLKSSJ_RN=7;
	/**
	 * 甲醛备料开始时间列号
	 */
	public static final int JQBLKSSJ_CN=3;
	
	/**
	 * 甲醛放料完成时间行号
	 */
	public static final int JQFLWCSJ_RN=7;
	/**
	 * 甲醛放料完成时间列号
	 */
	public static final int JQFLWCSJ_CN=4;

	/**
	 * 甲醛放料完成反应釜温度行号
	 */
	public static final int JQFLWCFYFWD_RN=7;
	/**
	 * 甲醛放料完成反应釜温度列号
	 */
	public static final int JQFLWCFYFWD_CN=5;
	
	/**
	 * 加碱前PH输入值行号
	 */
	public static final int JJQPHSRZ_RN=7;
	/**
	 * 加碱前PH输入值列号
	 */
	public static final int JJQPHSRZ_CN=6;

	/**
	 * 甲醛备料开始釜称重行号
	 */
	public static final int JQBLKSFCZ_RN=7;
	/**
	 * 甲醛备料开始釜称重列号
	 */
	public static final int JQBLKSFCZ_CN=8;

	/**
	 * 甲醛放料完成釜称重行号
	 */
	public static final int JQFLWCFCZ_RN=7;
	/**
	 * 甲醛放料完成釜称重列号
	 */
	public static final int JQFLWCFCZ_CN=9;
	
	/**
	 * 甲醛备料开始到放料完成重量差行号
	 */
	public static final int JQBLKSDFLWCZLC_RN=7;
	/**
	 * 甲醛备料开始到放料完成重量差列号
	 */
	public static final int JQBLKSDFLWCZLC_CN=10;

	/**
	 * 甲醛备料开始到放料完成时间差行号
	 */
	public static final int JQBLKSDFLWCSJC_RN=7;
	/**
	 * 甲醛备料开始到放料完成时间差列号
	 */
	public static final int JQBLKSDFLWCSJC_CN=11;
	
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

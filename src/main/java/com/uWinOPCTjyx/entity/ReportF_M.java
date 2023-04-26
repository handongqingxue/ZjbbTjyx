package com.uWinOPCTjyx.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	//生产编号-3
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
	
	//1号罐用前重-5
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
	
	//YSD101-7
	/**
	 * 甲醛实际进料重量行号
	 */
	public static final int JQSJJLZL_RN=7;
	/**
	 * 甲醛实际进料重量列号
	 */
	public static final int JQSJJLZL_CN=2;
	
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
	
	//纯净水-8
	/**
	 * 加水实际重量行号
	 */
	public static final int JSSJZL_RN=8;
	/**
	 * 加水实际重量列号
	 */
	public static final int JSSJZL_CN=2;
	
	//YSD109-9
	/**
	 * 加碱量提示行号
	 */
	public static final int JJLTS_RN=9;
	
	/**
	 * 加碱量提示列号
	 */
	public static final int JJLTS_CN=2;
	
	/**
	 * 加碱后PH输入值行号
	 */
	public static final int JJHPHSRZ_RN=9;
	/**
	 * 加碱后PH输入值列号
	 */
	public static final int JJHPHSRZ_CN=4;
	
	//YSD106-10
	/**
	 * 助剂计量罐1-2称重行号
	 */
	public static final int ZJJLG12CZ_RN=10;
	/**
	 * 助剂计量罐1-2称重列号
	 */
	public static final int ZJJLG12CZ_CN=2;
	
	/**
	 * 允许一次加助剂时间行号
	 */
	public static final int YXYCJZJSJ_RN=10;
	/**
	 * 允许一次加助剂时间列号
	 */
	public static final int YXYCJZJSJ_CN=3;
	
	/**
	 * 所有助剂加料完成1时间行号
	 */
	public static final int SYZJJLWC1SJ_RN=10;
	/**
	 * 所有助剂加料完成1时间列号
	 */
	public static final int SYZJJLWC1SJ_CN=4;
	
	/**
	 * 所有助剂加料完成1反应釜温度行号
	 */
	public static final int SYZJJLWC1FYFWD_RN=10;
	/**
	 * 所有助剂加料完成1反应釜温度列号
	 */
	public static final int SYZJJLWC1FYFWD_CN=5;
	
	/**
	 * 允许一次加助剂釜称重行号
	 */
	public static final int YXYCJZJFCZ_RN=10;
	/**
	 * 允许一次加助剂釜称重列号
	 */
	public static final int YXYCJZJFCZ_CN=7;
	
	/**
	 * 所有助剂加料完成1釜称重行号
	 */
	public static final int SYZJJLWC1FCZ_RN=10;
	/**
	 * 所有助剂加料完成1釜称重列号
	 */
	public static final int SYZJJLWC1FCZ_CN=8;
	
	/**
	 * 允许一次加助剂到所有助剂加料完成1重量差行号
	 */
	public static final int YXYCJZJDSYZJJLWC1ZLC_RN=10;
	/**
	 * 允许一次加助剂到所有助剂加料完成1重量差列号
	 */
	public static final int YXYCJZJDSYZJJLWC1ZLC_CN=9;
	
	/**
	 * 允许一次加助剂到所有助剂加料完成1时间差行号
	 */
	public static final int YXYCJZJDSYZJJLWC1SJC_RN=10;
	/**
	 * 允许一次加助剂到所有助剂加料完成1时间差列号
	 */
	public static final int YXYCJZJDSYZJJLWC1SJC_CN=10;
	
	//YSD102-12
	/**
	 * 粉料重量设定行号
	 */
	public static final int FLZLSD_RN=12;
	/**
	 * 粉料重量设定列号
	 */
	public static final int FLZLSD_CN=2;
	
	/**
	 * 釜尿素放料阀上升沿时间行号
	 */
	public static final int FNSFLFSSYSJ_RN=12;
	/**
	 * 釜尿素放料阀上升沿时间列号
	 */
	public static final int FNSFLFSSYSJ_CN=3;
	
	/**
	 * 釜尿素放料阀下降沿时间行号
	 */
	public static final int FNSFLFXJYSJ_RN=12;
	/**
	 * 釜尿素放料阀下降沿时间列号
	 */
	public static final int FNSFLFXJYSJ_CN=4;
	
	/**
	 * 釜尿素放料阀下降沿反应釜温度行号
	 */
	public static final int FNSFLFXJYFYFWD_RN=12;
	/**
	 * 釜尿素放料阀下降沿反应釜温度列号
	 */
	public static final int FNSFLFXJYFYFWD_CN=5;
	
	/**
	 * 加粉料PH输入值行号
	 */
	public static final int JFLPHSRZ_RN=12;
	/**
	 * 加粉料PH输入值列号
	 */
	public static final int JFLPHSRZ_CN=7;
	
	/**
	 * 釜尿素放料阀上升沿釜称重行号
	 */
	public static final int FNSFLFSSYFCZ_RN=12;
	/**
	 * 釜尿素放料阀上升沿釜称重列号
	 */
	public static final int FNSFLFSSYFCZ_CN=8;

	/**
	 * 釜尿素放料阀下降沿釜称重行号
	 */
	public static final int FNSFLFXJYFCZ_RN=12;
	/**
	 * 釜尿素放料阀下降沿釜称重列号
	 */
	public static final int FNSFLFXJYFCZ_CN=9;
	
	/**
	 * 釜尿素放料阀上升沿到下降沿重量差行号
	 */
	public static final int FNSFLFSSYDXJYZLC_RN=12;
	/**
	 * 釜尿素放料阀上升沿到下降沿重量差列号
	 */
	public static final int FNSFLFSSYDXJYZLC_CN=10;

	/**
	 * 釜尿素放料阀上升沿到下降沿时间差行号
	 */
	public static final int FNSFLFSSYDXJYSJC_RN=12;
	/**
	 * 釜尿素放料阀上升沿到下降沿时间差列号
	 */
	public static final int FNSFLFSSYDXJYSJC_CN=11;
	
	//开始升温-13
	/**
	 * 升温开始时间行号
	 */
	public static final int SWKSSJ_RN=13;
	/**
	 * 升温开始时间列号
	 */
	public static final int SWKSSJ_CN=4;
	
	/**
	 * 蒸汽压力行号
	 */
	public static final int ZQYL_RN=13;
	/**
	 * 蒸汽压力列号
	 */
	public static final int ZQYL_CN=5;
	
	//PH检测（中温温度）-14
	/**
	 * 温度85与二次投料提醒时间行号
	 */
	public static final int WD85YECTLTXSJ_RN=14;
	/**
	 * 温度85与二次投料提醒时间列号
	 */
	public static final int WD85YECTLTXSJ_CN=4;
	
	/**
	 * 温度85与二次投料提醒反应釜温度行号
	 */
	public static final int WD85YECTLTXFYFWD_RN=14;
	/**
	 * 温度85与二次投料提醒反应釜温度列号
	 */
	public static final int WD85YECTLTXFYFWD_CN=5;
	
	/**
	 * 二次投料PH输入值行号
	 */
	public static final int ECTLPHSRZ_RN=14;
	/**
	 * 二次投料PH输入值列号
	 */
	public static final int ECTLPHSRZ_CN=7;
	
	/**
	 * 升温开始到温度85与二次投料提醒时间差行号
	 */
	public static final int SWKSDWD85YECTLTXSJC_RN=14;
	/**
	 * 升温开始到温度85与二次投料提醒时间差列号
	 */
	public static final int SWKSDWD85YECTLTXSJC_CN=9;
	
	//YSD104-15
	/**
	 * 允许二次加助剂时间行号
	 */
	public static final int YXECJZJSJ_RN=15;
	/**
	 * 允许二次加助剂时间列号
	 */
	public static final int YXECJZJSJ_CN=3;
	
	/**
	 * 所有助剂加料完成2时间行号
	 */
	public static final int SYZJJLWC2SJ_RN=15;
	/**
	 * 所有助剂加料完成2时间列号
	 */
	public static final int SYZJJLWC2SJ_CN=4;
	
	/**
	 * 所有助剂加料完成2反应釜温度行号
	 */
	public static final int SYZJJLWC2FYFWD_RN=15;
	/**
	 * 所有助剂加料完成2反应釜温度列号
	 */
	public static final int SYZJJLWC2FYFWD_CN=5;
	
	/**
	 * 允许二次加助剂釜称重行号
	 */
	public static final int YXECJZJFCZ_RN=15;
	/**
	 * 允许二次加助剂釜称重列号
	 */
	public static final int YXECJZJFCZ_CN=8;
	
	/**
	 * 所有助剂加料完成2釜称重行号
	 */
	public static final int SYZJJLWC2FCZ_RN=15;
	/**
	 * 所有助剂加料完成2釜称重列号
	 */
	public static final int SYZJJLWC2FCZ_CN=9;
	
	/**
	 * 允许二次加助剂到所有助剂加料完成2重量差行号
	 */
	public static final int YXECJZJDSYZJJLWC2ZLC_RN=15;
	/**
	 * 允许二次加助剂到所有助剂加料完成2重量差列号
	 */
	public static final int YXECJZJDSYZJJLWC2ZLC_CN=10;
	
	/**
	 * 允许二次加助剂到所有助剂加料完成2时间差行号
	 */
	public static final int YXECJZJDSYZJJLWC2SJC_RN=15;
	/**
	 * 允许二次加助剂到所有助剂加料完成2时间差列号
	 */
	public static final int YXECJZJDSYZJJLWC2SJC_CN=11;
	
	//停汽-16
	/**
	 * 升温完成时间行号
	 */
	public static final int SWWCSJ_RN=16;
	/**
	 * 升温完成时间列号
	 */
	public static final int SWWCSJ_CN=3;
	
	/**
	 * 升温完成反应釜温度行号
	 */
	public static final int SWWCFYFWD_RN=16;
	/**
	 * 升温完成反应釜温度列号
	 */
	public static final int SWWCFYFWD_CN=4;
	
	/**
	 * 温度98PH行号
	 */
	public static final int WD98PH_RN=16;
	/**
	 * 温度98PH列号
	 */
	public static final int WD98PH_CN=6;
	
	/**
	 * 升温开始到升温完成时间差行号
	 */
	public static final int SWKSDSWWCSJC_RN=16;
	/**
	 * 升温开始到升温完成时间差列号
	 */
	public static final int SWKSDSWWCSJC_CN=10;
	
	//冰水检测-17
	/**
	 * 测量冰水雾点输入值行号
	 */
	public static final int CLBSWDSRZ_RN=17;
	/**
	 * 测量冰水雾点输入值列号
	 */
	public static final int CLBSWDSRZ_CN=8;
	
	//20度检测-18
	/**
	 * 测20雾点输入值行号
	 */
	public static final int C20WDSRZ_RN=18;
	/**
	 * 测20雾点输入值列号
	 */
	public static final int C20WDSRZ_CN=8;
	
	//降温水数-19
	/**
	 * 聚合终点反应釜温度行号
	 */
	public static final int JHZDFYFWD_RN=19;
	/**
	 * 聚合终点反应釜温度列号
	 */
	public static final int JHZDFYFWD_CN=7;
	
	/**
	 * 停热降温水数输入值行号
	 */
	public static final int TRJWSSSRZ_RN=19;
	/**
	 * 停热降温水数输入值列号
	 */
	public static final int TRJWSSSRZ_CN=8;
	
	//冷却-20
	/**
	 * 聚合终点时间行号
	 */
	public static final int JHZDSJ_RN=20;
	/**
	 * 聚合终点时间列号
	 */
	public static final int JHZDSJ_CN=3;
	
	/**
	 * 降温完成时间行号
	 */
	public static final int JWWCSJ_RN=20;
	/**
	 * 降温完成时间列号
	 */
	public static final int JWWCSJ_CN=4;
	
	/**
	 * 降温完成反应釜温度行号
	 */
	public static final int JWWCFYFWD_RN=20;
	/**
	 * 降温完成反应釜温度列号
	 */
	public static final int JWWCFYFWD_CN=6;
	
	/**
	 * 开始降温到停止降温时间差行号
	 */
	public static final int KSJWDTZJWSJC_RN=20;
	/**
	 * 开始降温到停止降温时间差列号
	 */
	public static final int KSJWDTZJWSJC_CN=11;
	
	
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

	@Override
	public String toString() {
		return "ReportF_M{" +
				"Id=" + Id +
				", RowNumber='" + RowNumber + '\'' +
				", ColNumber='" + ColNumber + '\'' +
				", Value='" + Value + '\'' +
				", BatchID='" + BatchID + '\'' +
				", CreateTime='" + CreateTime + '\'' +
				", Remark='" + Remark + '\'' +
				'}';
	}
}

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
	/**
	 * 甲醛放料完成上升沿反应釜温度
	 */
	public static final String JQFLWCSSYFYFWD=Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 助剂计量罐1-2称重
	 */
	public static final String ZJJLG12CZ=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+"-"+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG;
	/**
	 * 允许一次加助剂到所有助剂加料完成1时间
	 */
	public static final String YXYCJZJDSYZJJLWC1SJ=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN;
	/**
	 * 所有助剂加料完成1上升沿时间
	 */
	public static final String SYZJJLWC1SSYSJ=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 所有助剂加料完成1上升沿反应釜温度
	 */
	public static final String SYZJJLWC1SSYFYFWD=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 允许一次加助剂上升沿釜称重
	 */
	public static final String YXYCJZJSSYFCZ=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 允许一次加助剂到所有助剂加料完成1釜重量
	 */
	public static final String YXYCJZJDSYZJJLWC1FZL=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG;
	/**
	 * 所有助剂加料完成1上升沿釜称重
	 */
	public static final String SYZJJLWC1SSYFCZ=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 允许一次加助剂到所有助剂加料完成1重量差
	 */
	public static final String YXYCJZJDSYZJJLWC1FZLC=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;
	/**
	 * 允许一次加助剂到所有助剂加料完成1时间差
	 */
	public static final String YXYCJZJDSYZJJLWC1SJC=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 釜尿素放料阀上升沿时间
	 */
	public static final String FNSFLFSSYSJ=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 釜尿素放料阀上升沿到下降沿时间
	 */
	public static final String FNSFLFSSYDXJYSJ=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN;
	/**
	 * 釜尿素放料阀上升沿釜称重
	 */
	public static final String FNSFLFSSYFCZ=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 釜尿素放料阀上升沿到下降沿重量
	 */
	public static final String FNSFLFSSYDXJYZL=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG;
	/**
	 * 釜尿素放料阀下降沿釜称重
	 */
	public static final String FNSFLFXJYFCZ=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 釜尿素放料阀重量差
	 */
	public static final String FNSFLFSSYDXJYZLC=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG+Constant.CHA;
	/**
	 * 釜尿素放料阀时间差
	 */
	public static final String FNSFLFSSYDXJYSJC=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 釜尿素放料阀下降沿反应釜温度
	 */
	public static final String FNSFLFXJYFYFWD=Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 升温开始上升沿时间
	 */
	public static final String SWKSSSYSJ=Constant.SHENG_WEN_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 升温开始到温度85与二次投料提醒时间
	 */
	public static final String SWKSDWD85YECTLTXSJ=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN;
	/**
	 * 升温开始到升温完成时间
	 */
	public static final String SWKSDSWWCSJ=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN;
	/**
	 * 温度85与二次投料提醒上升沿时间
	 */
	public static final String WD85YECTLTXSSYSJ=Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 温度85与二次投料提醒上升沿反应釜温度
	 */
	public static final String WD85YECTLTXSSYFYFWD=Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 升温开始到温度85与二次投料提醒时间差
	 */
	public static final String SWKSDWD85YECTLTXSJC=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 允许二次加助剂上升沿时间
	 */
	public static final String YXECJZJSSYSJ=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 允许二次加助剂到所有助剂加料完成2时间
	 */
	public static final String YXECJZJDSYZJJLWC2SJ=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN;
	/**
	 * 所有助剂加料完成2上升沿时间
	 */
	public static final String SYZJJLWC2SSYSJ=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 所有助剂加料完成2上升沿反应釜温度
	 */
	public static final String SYZJJLWC2SSYFYFWD=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 允许二次加助剂上升沿釜称重
	 */
	public static final String YXECJZJSSYFCZ=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 允许二次加助剂到所有助剂加料完成2釜称重
	 */
	public static final String YXECJZJDSYZJJLWC2FCZ=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 所有助剂加料完成2上升沿釜称重
	 */
	public static final String SYZJJLWC2SSYFCZ=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
	/**
	 * 允许二次加助剂到所有助剂加料完成2釜重量差
	 */
	public static final String YXECJZJDSYZJJLWC2FZLC=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;
	/**
	 * 允许二次加助剂到所有助剂加料完成2时间差
	 */
	public static final String YXECJZJDSYZJJLWC2SJC=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 升温完成上升沿时间 
	 */
	public static final String SWWCSSYSJ=Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 升温完成上升沿反应釜温度
	 */
	public static final String SWWCSSYFYFWD=Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 升温开始到升温完成时间差
	 */
	public static final String SWKSDSWWCSJC=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA;
	/**
	 * 聚合终点上升沿反应釜温度
	 */
	public static final String JHZDSSYFYFWD=Constant.JU_HE_ZHONG_DIAN+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 聚合终点上升沿时间
	 */
	public static final String JHZDSSYSJ=Constant.JU_HE_ZHONG_DIAN+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 开始降温到停止降温时间
	 */
	public static final String KSJWDTZJWSJ=Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN;
	/**
	 * 降温完成上升沿时间
	 */
	public static final String JWWCSSYSJ=Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
	/**
	 * 降温完成上升沿反应釜温度
	 */
	public static final String JWWCSSYFYFWD=Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
	/**
	 * 从开始降温到停止降温时间差
	 */
	public static final String KSJWDTZJWSJC=Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN+Constant.CHA;

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

	@Override
	public String toString() {
		return "ERecord{" +
				"Id=" + Id +
				", VarName='" + VarName + '\'' +
				", VarValue='" + VarValue + '\'' +
				", Unit='" + Unit + '\'' +
				", PreValue='" + PreValue + '\'' +
				", NxtValue='" + NxtValue + '\'' +
				", PtnValue='" + PtnValue + '\'' +
				", RecType='" + RecType + '\'' +
				", FId=" + FId +
				", RecordTime='" + RecordTime + '\'' +
				", BatchID='" + BatchID + '\'' +
				", PhaseName='" + PhaseName + '\'' +
				", Remark='" + Remark + '\'' +
				'}';
	}
}

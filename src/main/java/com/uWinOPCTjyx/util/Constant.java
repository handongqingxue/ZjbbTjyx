package com.uWinOPCTjyx.util;

public class Constant {

	/**
	 * 反应釜初始值
	 */
	public static final int FYF_CSZ=0;
	/**
	 * 1号反应釜id
	 */
	public static final int F1_ID=1;
	/**
	 * 2号反应釜id
	 */
	public static final int F2_ID=2;
	/**
	 * 3号反应釜id
	 */
	public static final int F3_ID=3;
	/**
	 * 4号反应釜id
	 */
	public static final int F4_ID=4;
	/**
	 * 5号反应釜id
	 */
	public static final int F5_ID=5;
	
	/**
	 * 1号反应釜(M类)
	 */
	public static final String BSF_F1="F1";
	/**
	 * 2号反应釜(M类)
	 */
	public static final String BSF_F2="F2";
	/**
	 * 3号反应釜(M类)
	 */
	public static final String BSF_F3="F3";
	/**
	 * 4号反应釜(M类)
	 */
	public static final String BSF_F4="F4";
	/**
	 * 5号反应釜(M类)
	 */
	public static final String BSF_F5="F5";
	
	/**
	 * 1号反应釜(U类)
	 */
	public static final String BSF_F1U="F1U";
	/**
	 * 2号反应釜(U类)
	 */
	public static final String BSF_F2U="F2U";
	/**
	 * 3号反应釜(U类)
	 */
	public static final String BSF_F3U="F3U";
	/**
	 * 4号反应釜(U类)
	 */
	public static final String BSF_F4U="F4U";
	/**
	 * 5号反应釜(U类)
	 */
	public static final String BSF_F5U="F5U";

	/**
	 * 反应釜号Ph值(M类)
	 */
	public static final String BSF_PF="PF";

	/**
	 * 1号反应釜Ph值(M类)
	 */
	public static final String BSF_PF1="PF1";
	/**
	 * 2号反应釜Ph值(M类)
	 */
	public static final String BSF_PF2="PF2";
	/**
	 * 3号反应釜Ph值(M类)
	 */
	public static final String BSF_PF3="PF3";
	/**
	 * 4号反应釜Ph值(M类)
	 */
	public static final String BSF_PF4="PF4";
	/**
	 * 5号反应釜Ph值(M类)
	 */
	public static final String BSF_PF5="PF5";
	
	/**
	 * 助剂计量罐1(M类)
	 */
	public static final int BSF_ZJJLG1=1;
	/**
	 * 助剂计量罐2(M类)
	 */
	public static final int BSF_ZJJLG2=2;
	/**
	 * 助剂计量罐3(M类)
	 */
	public static final int BSF_ZJJLG3=3;
	/**
	 * 助剂计量罐4(M类)
	 */
	public static final int BSF_ZJJLG4=4;
	/**
	 * 助剂计量罐5(M类)
	 */
	public static final int BSF_ZJJLG5=5;

	/**
	 * 反应釜号数组
	 */
	public static final Integer[] F_ID_ARR=new Integer[]{F1_ID,F2_ID,F3_ID,F4_ID,F5_ID};

	/**
	 * 反应釜标识符数组(M类)
	 */
	public static final String[] BSF_F_M_ARR=new String[] {BSF_F1,BSF_F2,BSF_F3,BSF_F4,BSF_F5};
	/**
	 * 反应釜标识符数组(U类)
	 */
	public static final String[] BSF_F_U_ARR=new String[] {BSF_F1U,BSF_F2U,BSF_F3U,BSF_F4U,BSF_F5U};
	
	/**
	 * 反应釜Ph标识符数组(M类)
	 */
	public static final String[] BSF_PF_M_ARR=new String[] {BSF_PF1,BSF_PF2,BSF_PF3,BSF_PF4,BSF_PF5};
	
	/**
	 * 助剂计量罐1-2标识符数组(M类)
	 */
	public static final Integer[] BSF_ZJJLG_1_2_M_ARR=new Integer[] {BSF_ZJJLG1,BSF_ZJJLG2};
	/**
	 * 助剂计量罐3-5标识符数组(M类)
	 */
	public static final Integer[] BSF_ZJJLG_3_5_M_ARR=new Integer[] {BSF_ZJJLG3,BSF_ZJJLG4,BSF_ZJJLG5};
	
	/**
	 * 开始到结束时间
	 */
	public static final String KAI_SHI_DAO_JIE_SHU_SHI_JIAN="开始到结束时间";
	/**
	 * 备料开始
	 */
	public static final String BEI_LIAO_KAI_SHI="备料开始";
	/**
	 * 备料开始时间
	 */
	public static final String BEI_LIAO_KAI_SHI_SHI_JIAN="备料开始时间";
	/**
	 * 反应结束
	 */
	public static final String FAN_YING_JIE_SHU="反应结束";
	/**
	 * 反应结束时间
	 */
	public static final String FAN_YING_JIE_SHU_SHI_JIAN="反应结束时间";
	/**
	 * 生产工时
	 */
	public static final String SHENG_CHAN_GONG_SHI="生产工时";
	/**
	 * 生产日期
	 */
	public static final String SHENG_CHAN_RI_QI="生产日期";
	/**
	 * 甲醛实际进料重量
	 */
	public static final String JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG="甲醛实际进料重量";
	/**
	 * 加水实际重量
	 */
	public static final String JIA_SHUI_SHI_JI_ZHONG_LIANG="加水实际重量";
	/**
	 * 甲醛备料开始
	 */
	public static final String JIA_QUAN_BEI_LIAO_KAI_SHI="甲醛备料开始";
	/**
	 * 甲醛放料完成
	 */
	public static final String JIA_QUAN_FANG_LIAO_WAN_CHENG="甲醛放料完成";
	/**
	 * 加碱PH值正常
	 */
	public static final String JIA_JIAN_PH_ZHI_ZHENG_CHANG="加碱PH值正常";
	/**
	 * 加碱前PH输入值
	 */
	public static final String JIA_JIAN_QIAN_PH_SHU_RU_ZHI="加碱前PH输入值";
	/**
	 * 加碱量提示
	 */
	public static final String JIA_JIAN_LIANG_TI_SHI="加碱量提示";
	/**
	 * 加碱后PH输入值
	 */
	public static final String JIA_JIAN_HOU_PH_SHU_RU_ZHI="加碱后PH输入值";
	/**
	 * 助剂计量罐
	 */
	public static final String ZHU_JI_JI_LIANG_GUAN="助剂计量罐";
	/**
	 * 允许一次加助剂
	 */
	public static final String YUN_XU_YI_CI_JIA_ZHU_JI="允许一次加助剂";

	/**
	 * 所有助剂加料完成1
	 */
	public static final String SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1="所有助剂加料完成1";
	/**
	 * 加粉料提醒
	 */
	public static final String JIA_FEN_LIAO_TI_XING="加粉料提醒";
	/**
	 * 粉料重量设定
	 */
	public static final String FEN_LIAO_ZHONG_LIANG_SHE_DING="粉料重量设定";
	/**
	 * 尿素放料阀
	 */
	public static final String NIAO_SU_FANG_LIAO_FA="尿素放料阀";
	/**
	 * 加粉料PH合格
	 */
	public static final String JIA_FEN_LIAO_PH_HE_GE="加粉料PH合格";
	/**
	 * 加粉料PH输入值
	 */
	public static final String JIA_FEN_LIAO_PH_SHU_RU_ZHI="加粉料PH输入值";
	/**
	 * 升温开始
	 */
	public static final String SHENG_WEN_KAI_SHI="升温开始";
	/**
	 * 蒸汽压力
	 */
	public static final String ZHENG_QI_YA_LI="蒸汽压力";
	/**
	 * 温度85与二次投料提醒
	 */
	public static final String WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING="温度85与二次投料提醒";
	/**
	 * 二次助剂后测PH提醒
	 */
	public static final String ER_CI_ZHU_JI_HOU_CE_PH_TI_XING="二次助剂后测PH提醒";
	/**
	 * 二次投料PH输入值
	 */
	public static final String ER_CI_TOU_LIAO_PH_SHU_RU_ZHI="二次投料PH输入值";
	/**
	 * 允许二次加助剂
	 */
	public static final String YUN_XU_ER_CI_JIA_ZHU_JI="允许二次加助剂";
	/**
	 * 所有助剂加料完成2
	 */
	public static final String SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2="所有助剂加料完成2";
	/**
	 * 升温完成
	 */
	public static final String SHENG_WEN_WAN_CHENG="升温完成";
	/**
	 * 温度98PH
	 */
	public static final String WEN_DU_98_PH="温度98PH";
	/**
	 * 合格
	 */
	public static final String HE_GE="合格";
	/**
	 * 测量冰水雾点提醒
	 */
	public static final String CE_LIANG_BING_SHUI_WU_DIAN_TI_XING="测量冰水雾点提醒";
	/**
	 * 测量冰水雾点输入值
	 */
	public static final String CE_LIANG_BSWD_SRZ="测量冰水雾点输入值";
	/**
	 * 测20雾点输入值
	 */
	public static final String CE_20_WU_DIAN_SRZ="测20雾点输入值";
	/**
	 * 聚合终点
	 */
	public static final String JU_HE_ZHONG_DIAN="聚合终点";
	/**
	 * 测水数提醒
	 */
	public static final String CE_SHUI_SHU_TI_XING="测水数提醒";
	/**
	 * 停热降温水数输入值
	 */
	public static final String TING_RE_JIANG_WEN_SHUI_SHU_SRZ="停热降温水数输入值";
	/**
	 * 降温完成
	 */
	public static final String JIANG_WEN_WAN_CHENG="降温完成";
	/**
	 * 开始降温
	 */
	public static final String KAI_SHI_JIANG_WEN="开始降温";
	/**
	 * 停止降温
	 */
	public static final String TING_ZHI_JIANG_WEN="停止降温";
	
	/**
	 * 百分号
	 */
	public static final String BAI_FEN_HAO="%";
	
	/**
	 * 釜
	 */
	public static final String FU="釜";
	/**
	 * 称重
	 */
	public static final String CHENG_ZHONG="称重";
	
	/**
	 * 反应釜
	 */
	public static final String FAN_YING_FU="反应釜";
	/**
	 * 温度
	 */
	public static final String WEN_DU="温度";
	/**
	 * 到
	 */
	public static final String DAO="到";
	/**
	 * 时间
	 */
	public static final String SHI_JIAN="时间";
	/**
	 * 重量
	 */
	public static final String ZHONG_LIANG="重量";
	/**
	 * 差
	 */
	public static final String CHA="差";
	/**
	 * 千克单位文字
	 */
	public static final String KG="kg";
	/**
	 * 温度单位符号
	 */
	public static final String WEN_DU_DAN_WEI_SIGN="°C";
	/**
	 * MPa
	 */
	public static final String MPA="MPa";
	
	/**
	 * 上升沿
	 */
	public static final String SHANG_SHENG_YAN="上升沿";
	/**
	 * 下降沿
	 */
	public static final String XIA_JIANG_YAN="下降沿";

	/**
	 * 生产编号阶段
	 */
	public static final String SHENG_CHAN_BIAN_HAO="生产编号";
	/**
	 * 甲醛阶段
	 */
	public static final String YSD101="YSD101";
	/**
	 * YSD109阶段
	 */
	public static final String YSD109="YSD109";
	/**
	 * YSD106阶段
	 */
	public static final String YSD106="YSD106";
	/**
	 * YSD102阶段
	 */
	public static final String YSD102="YSD102";
	/**
	 * 开始升温
	 */
	public static final String KAI_SHI_SHENG_WEN="开始升温";
	/**
	 * PH检测
	 */
	public static final String PH_JIAN_CE="PH检测";
	/**
	 * YSD104阶段
	 */
	public static final String YSD104="YSD104";
	/**
	 * 停汽
	 */
	public static final String TING_QI="停汽";
}

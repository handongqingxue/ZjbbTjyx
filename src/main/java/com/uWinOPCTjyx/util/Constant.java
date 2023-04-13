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
	 * 开始到结束时间文字
	 */
	public static final String KAI_SHI_DAO_JIE_SHU_SHI_JIAN_TEXT="开始到结束时间";
	/**
	 * 备料开始文字
	 */
	public static final String BEI_LIAO_KAI_SHI_TEXT="备料开始";
	/**
	 * 备料开始时间文字
	 */
	public static final String BEI_LIAO_KAI_SHI_SHI_JIAN_TEXT="备料开始时间";
	/**
	 * 反应结束文字
	 */
	public static final String FAN_YING_JIE_SHU_TEXT="反应结束";
	/**
	 * 反应结束时间文字
	 */
	public static final String FAN_YING_JIE_SHU_SHI_JIAN_TEXT="反应结束时间";
	/**
	 * 生产日期文字
	 */
	public static final String SHENG_CHAN_RI_QI_TEXT="生产日期";
	/**
	 * 甲醛实际进料重量文字
	 */
	public static final String JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT="甲醛实际进料重量";
	/**
	 * 甲醛备料开始文字
	 */
	public static final String JIA_QUAN_BEI_LIAO_KAI_SHI_TEXT="甲醛备料开始";
	/**
	 * 甲醛放料完成文字
	 */
	public static final String JIA_QUAN_FANG_LIAO_WAN_CHENG_TEXT="甲醛放料完成";
	/**
	 * 加碱PH值正常文字
	 */
	public static final String JIA_JIAN_PH_ZHI_ZHENG_CHANG_TEXT="加碱PH值正常";
	/**
	 * 加碱前PH输入值文字
	 */
	public static final String JIA_JIAN_QIAN_PH_SHU_RU_ZHI_TEXT="加碱前PH输入值";
	/**
	 * 加碱量提示文字
	 */
	public static final String JIA_JIAN_LIANG_TI_SHI_TEXT="加碱量提示";
	/**
	 * 加碱后PH输入值文字
	 */
	public static final String JIA_JIAN_HOU_PH_SHU_RU_ZHI_TEXT="加碱后PH输入值";
	/**
	 * 助剂计量罐文字
	 */
	public static final String ZHU_JI_JI_LIANG_GUAN_TEXT="助剂计量罐";
	/**
	 * 允许一次加助剂文字
	 */
	public static final String YUN_XU_YI_CI_JIA_ZHU_JI_TEXT="允许一次加助剂";

	/**
	 * 所有助剂加料完成1文字
	 */
	public static final String SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1_TEXT="所有助剂加料完成1";
	/**
	 * 加粉料提醒文字
	 */
	public static final String JIA_FEN_LIAO_TI_XING_TEXT="加粉料提醒";
	/**
	 * 粉料重量设定文字
	 */
	public static final String FEN_LIAO_ZHONG_LIANG_SHE_DING_TEXT="粉料重量设定";
	/**
	 * 加粉料PH合格文字
	 */
	public static final String JIA_FEN_LIAO_PH_HE_GE_TEXT="加粉料PH合格";
	/**
	 * 加粉料PH输入值文字
	 */
	public static final String JIA_FEN_LIAO_PH_SHU_RU_ZHI_TEXT="加粉料PH输入值";
	/**
	 * 升温开始文字
	 */
	public static final String SHENG_WEN_KAI_SHI_TEXT="升温开始";
	/**
	 * 温度85与二次投料提醒文字
	 */
	public static final String WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING_TEXT="温度85与二次投料提醒";
	/**
	 * 二次助剂后测PH提醒文字
	 */
	public static final String ER_CI_ZHU_JI_HOU_CE_PH_TI_XING_TEXT="二次助剂后测PH提醒";
	/**
	 * 允许二次加助剂文字
	 */
	public static final String YUN_XU_ER_CI_JIA_ZHU_JI_TEXT="允许二次加助剂";
	/**
	 * 所有助剂加料完成2文字
	 */
	public static final String SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2_TEXT="所有助剂加料完成2";
	/**
	 * 升温完成文字
	 */
	public static final String SHENG_WEN_WAN_CHENG_TEXT="升温完成";
	/**
	 * 最高温PH录入值文字
	 */
	public static final String ZUI_GAO_WEN_PH_LRZ_TEXT="最高温PH录入值";
	/**
	 * 温度98PH文字
	 */
	public static final String WEN_DU_98_PH_TEXT="温度98PH";
	/**
	 * 测量冰水雾点输入值文字
	 */
	public static final String CE_LIANG_BSWD_SRZ_TEXT="测量冰水雾点输入值";
	/**
	 * 测20雾点输入值文字
	 */
	public static final String CE_20_WU_DIAN_SRZ_TEXT="测20雾点输入值";
	/**
	 * 聚合终点文字
	 */
	public static final String JU_HE_ZHONG_DIAN_TEXT="聚合终点";
	/**
	 * 测水数提醒文字
	 */
	public static final String CE_SHUI_SHU_TI_XING_TEXT="测水数提醒";
	/**
	 * 停热降温水数输入值文字
	 */
	public static final String TING_RE_JIANG_WEN_SHUI_SHU_SRZ_TEXT="停热降温水数输入值";
	/**
	 * 降温完成文字
	 */
	public static final String JIANG_WEN_WAN_CHENG_TEXT="降温完成";
	
	/**
	 * 百分号文字
	 */
	public static final String BAI_FEN_HAO_TEXT="%";
	
	/**
	 * 釜文字
	 */
	public static final String FU_TEXT="釜";
	/**
	 * 称重文字
	 */
	public static final String CHENG_ZHONG_TEXT="称重";
	
	/**
	 * 反应釜文字
	 */
	public static final String FAN_YING_FU_TEXT="反应釜";
	/**
	 * 温度文字
	 */
	public static final String WEN_DU_TEXT="温度";
	/**
	 * 到文字
	 */
	public static final String DAO_TEXT="到";
	/**
	 * 时间文字
	 */
	public static final String SHI_JIAN_TEXT="时间";
	/**
	 * 重量文字
	 */
	public static final String ZHONG_LIANG_TEXT="重量";
	/**
	 * 差文字
	 */
	public static final String CHA_TEXT="差";
	/**
	 * 千克单位文字
	 */
	public static final String KG="kg";
	/**
	 * 温度单位符号
	 */
	public static final String WEN_DU_DAN_WEI_SIGN="°C";

	/**
	 * 生产编号阶段文字
	 */
	public static final String SHENG_CHAN_BIAN_HAO_TEXT="生产编号";
	/**
	 * 甲醛阶段文字
	 */
	public static final String YSD101_TEXT="YSD101";
	/**
	 * YSD109阶段文字
	 */
	public static final String YSD109_TEXT="YSD109";
}

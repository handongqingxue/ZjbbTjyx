package com.zjbbTjyx.util;

public class Constant {
	
	/**
	 * 运行opc服务的服务器ip
	 */
	public static final String OPC_HOST="127.0.0.1";
	/**
	 * opc服务名
	 */
	public static final String OPC_SERVER_PROG_ID="UWinTech.UWinOPCS.1";
	//public static final String OPC_SERVER_PROG_ID="kepware.KEPServerEX.V6";
	
	/**
	 * opc服务器计算机名
	 */
	public static final String OPC_SERVER_CLIENT_HANDLE="MM-202303181234";
	//public static final String OPC_SERVER_CLIENT_HANDLE="M3N881PM37O1M1D";
	
	/**
	 * opc变量组名
	 */
	public static final String OPC_GROUP_NAME="Group1";
	//public static final String OPC_GROUP_NAME="_System";

	/**
	 * 下划线
	 */
	public static final String XHX="_";
	/**
	 * AV后缀
	 */
	public static final String AV="AV";
	
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
	 * 1号反应釜Ph值(U类)
	 */
	public static final String BSF_PF1U="PF1U";
	/**
	 * 2号反应釜Ph值(U类)
	 */
	public static final String BSF_PF2U="PF2U";
	/**
	 * 3号反应釜Ph值(U类)
	 */
	public static final String BSF_PF3U="PF3U";
	/**
	 * 4号反应釜Ph值(U类)
	 */
	public static final String BSF_PF4U="PF4U";
	/**
	 * 5号反应釜Ph值(U类)
	 */
	public static final String BSF_PF5U="PF5U";
	
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
	 * 胶罐1
	 */
	public static final int BSF_JG1=1;
	/**
	 * 胶罐2
	 */
	public static final int BSF_JG2=2;
	/**
	 * 胶罐3
	 */
	public static final int BSF_JG3=3;
	/**
	 * 胶罐4
	 */
	public static final int BSF_JG4=4;
	/**
	 * 胶罐5
	 */
	public static final int BSF_JG5=5;
	/**
	 * 胶罐6
	 */
	public static final int BSF_JG6=6;
	/**
	 * 胶罐7
	 */
	public static final int BSF_JG7=7;
	/**
	 * 胶罐8
	 */
	public static final int BSF_JG8=8;
	/**
	 * 胶罐9
	 */
	public static final int BSF_JG9=9;

	/**
	 * 反应釜号数组
	 */
	public static final int[] F_ID_ARR=new int[]{F1_ID,F2_ID,F3_ID,F4_ID,F5_ID};

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
	 * 反应釜Ph标识符数组(U类)
	 */
	public static final String[] BSF_PF_U_ARR=new String[] {BSF_PF1U,BSF_PF2U,BSF_PF3U,BSF_PF4U,BSF_PF5U};
	
	/**
	 * 助剂计量罐1-2标识符数组(M类)
	 */
	public static final int[] BSF_ZJJLG_1_2_M_ARR=new int[] {BSF_ZJJLG1,BSF_ZJJLG2};
	/**
	 * 助剂计量罐3-5标识符数组(M类)
	 */
	public static final int[] BSF_ZJJLG_3_5_M_ARR=new int[] {BSF_ZJJLG3,BSF_ZJJLG4,BSF_ZJJLG5};
	
	public static final int[] BSF_JG_ARR=new int[] {BSF_JG1,BSF_JG2,BSF_JG3,BSF_JG4,BSF_JG5,BSF_JG6,BSF_JG7,BSF_JG8,BSF_JG9};

	/**
	 * 甲醛厂家信息
	 */
	public static final String JIA_QUAN_CHANG_JIA_XIN_XI="甲醛厂家信息";
	
	/**
	 * 三安厂家信息
	 */
	public static final String SAN_AN_CHANG_JIA_XIN_XI="三安厂家信息";
	
	/**
	 * 当班操作员
	 */
	public static final String DANG_BAN_CAO_ZUO_YUAN="当班操作员";
	
	/**
	 * 接班操作员
	 */
	public static final String JIE_BAN_CAO_ZUO_YUAN="接班操作员";
	
	/**
	 * 至
	 */
	public static final String ZHI="至";
	
	/**
	 * 批次记录
	 */
	public static final String PI_CI_JI_LU="批次记录";
	/**
	 * 开始
	 */
	public static final String KAI_SHI="开始";
	/**
	 * 结束
	 */
	public static final String JIE_SHU="结束";
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
	 * 当前胶种显示
	 */
	public static final String DANG_QIAN_JIAO_ZHONG_XIAN_SHI="当前胶种显示";
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
	 * 二次投料PH输入
	 */
	public static final String ER_CI_TOU_LIAO_PH_SHU_RU="二次投料PH输入";
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
	 * 测量冰水雾点输入值
	 */
	public static final String CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI="测量冰水雾点输入值";
	/**
	 * 保温分钟计时
	 */
	public static final String BAO_WEN_FEN_ZHONG_JI_SHI="保温分钟计时";
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
	 * 生产总重
	 */
	public static final String SHENG_CHAN_ZONG_ZHONG="生产总重";
	/**
	 * 终检水PH提醒
	 */
	public static final String ZHONG_JIAN_SHUI_PH_TI_XING="终检水PH提醒";
	/**
	 * 终检水数
	 */
	public static final String ZHONG_JIAN_SHUI_SHU="终检水数";
	/**
	 * 终检PH
	 */
	public static final String ZHONG_JIAN_PH="终检PH";
	/**
	 * 允许开始排胶
	 */
	public static final String YUN_XU_KAI_SHI_PAI_JIAO="允许开始排胶";
	/**
	 * 排胶完成
	 */
	public static final String PAI_JIAO_WAN_CHENG="排胶完成";


	/**
	 * 开始加料
	 */
	public static final String KAI_SHI_JIA_LIAO="开始加料";

	/**
	 * 助剂六一次添加完成
	 */
	public static final String ZHU_JI_LIU_YI_CI_TIAN_JIA_WAN_CHENG="助剂六一次添加完成";

	/**
	 * 助剂六二次备料完成
	 */
	public static final String ZHU_JI_LIU_ER_CI_BEI_LIAO_WAN_CHENG="助剂六二次备料完成";

	/**
	 * 助剂六二次添加完成
	 */
	public static final String ZHU_JI_LIU_ER_CI_TIAN_JIA_WAN_CHENG="助剂六二次添加完成";
	
	/**
	 * 第一次保温启动
	 */
	public static final String DI_YI_CI_BAO_WEN_QI_DONG="第一次保温启动";

	/**
	 * 第一次保温合格
	 */
	public static final String DI_YI_CI_BAO_WEN_HE_GE="第一次保温合格";

	/**
	 * 一次降温加酸提醒
	 */
	public static final String YI_CI_JIANG_WEN_JIA_SUAN_TI_XING="一次降温加酸提醒";

	/**
	 * 一次降温加酸合格
	 */
	public static final String YI_CI_JIANG_WEN_JIA_SUAN_HE_GE="一次降温加酸合格";
	/**
	 * 加碱PH合格
	 */
	public static final String JIA_JIAN_PH_HE_GE="加碱PH合格";
	/**
	 * 二次投粉
	 */
	public static final String ER_CI_TOU_FEN="二次投粉";

	/**
	 * 二次加215启动
	 */
	public static final String ER_CI_JIA_215_QI_DONG="二次加215启动";

	/**
	 * 二次加215完成
	 */
	public static final String ER_CI_JIA_215_WAN_CHENG="二次加215完成";

	/**
	 * 二次加小料和水提醒
	 */
	public static final String ER_CI_JIA_XIAO_LIAO_HE_SHUI_TI_XING="二次加小料和水提醒";

	/**
	 * 二次加水启动
	 */
	public static final String ER_CI_JIA_SHUI_QI_DONG="二次加水启动";

	/**
	 * 二次加水完成
	 */
	public static final String ER_CI_JIA_SHUI_WAN_CHENG="二次加水完成";

	/**
	 * 粉料1重量设定
	 */
	public static final String FEN_LIAO_1_ZHONG_LIANG_SHE_DING="粉料1重量设定";

	/**
	 * 粉料2重量设定
	 */
	public static final String FEN_LIAO_2_ZHONG_LIANG_SHE_DING="粉料2重量设定";

	/**
	 * 温度98PH合格
	 */
	public static final String WEN_DU_98_PH_HE_GE="温度98PH合格";
	/**
	 * 一次降温加酸量
	 */
	public static final String YI_CI_JIANG_WEN_JIA_SUAN_LIANG="一次降温加酸量";
	/**
	 * 一次降温加酸PH输入
	 */
	public static final String YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU="一次降温加酸PH输入";
	/**
	 * 加碱量范围下限
	 */
	public static final String JIA_JIA_LIANG_FAN_WEI_XIA_XIAN="加碱量范围下限";
	/**
	 * 加碱PH输入
	 */
	public static final String JIA_JIAN_PH_SHU_RU="加碱PH输入";
	/**
	 * 加碱量范围下限
	 */
	public static final String JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN="加碱量范围下限";
	/**
	 * 二次加料粉料1重量设定
	 */
	public static final String ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING="二次加粉料1重量设定";
	/**
	 * 保温后加助剂6量设定
	 */
	public static final String BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING="保温后加助剂6量设定";
	/**
	 * 保温后加水量设定
	 */
	public static final String BAO_WEN_HOU_JIA_SHUI_LIANG_SHE_DING="保温后加水量设定";
	/**
	 * 胶罐
	 */
	public static final String JIAO_GUAN="胶灌";
	
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
	 * 胶种类型
	 */
	public static final String JIAO_ZHONG_LEI_XING="胶种类型";
	
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
	 * 分钟单位符号
	 */
	public static final String MIN="Min";
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
	/**
	 * 保温
	 */
	public static final String BAO_WEN="保温";
	/**
	 * 冷却
	 */
	public static final String LENG_QUE="冷却";
	/**
	 * 质量终检
	 */
	public static final String ZHI_LIANG_ZHONG_JIAN="质量终检";
	/**
	 * 排胶
	 */
	public static final String PAI_JIAO="排胶";
	
	/**
	 * YSD215一次阶段
	 */
	public static final String YSD215_YI_CI="YSD215一次";
	/**
	 * YSD215二次阶段
	 */
	public static final String YSD215_ER_CI="YSD215二次";
	/**
	 * YSD103阶段
	 */
	public static final String YSD103="YSD103";
	/**
	 * 升温至高温度
	 */
	public static final String SHENG_WEN_ZHI_GAO_WEN_DU="升温至高温度";
	/**
	 * 一次保温10分钟测PH
	 */
	public static final String YCBWSFZCPH="一次保温10分钟测PH";
	/**
	 * 降温开始
	 */
	public static final String JIANG_WEN_KAI_SHI="降温开始";
	/**
	 * 降温停止
	 */
	public static final String JIANG_WEN_TING_ZHI="降温停止";
	/**
	 * 加酸并计时
	 */
	public static final String JIA_SUAN_BING_JI_SHI="加酸并计时";
	/**
	 * 冰水雾点
	 */
	public static final String BING_SHUI_WU_DIAN="冰水雾点";
	/**
	 * 20度雾点
	 */
	public static final String ER_SHI_DU_WU_DIAN="20度雾点";
	/**
	 * 二次降温
	 */
	public static final String ER_CI_JIANG_WEN="二次降温";
	/**
	 * 加碱
	 */
	public static final String JIA_JIAN="加碱";
	/**
	 * 70度终止降温
	 */
	public static final String QI_SHI_DU_ZHONG_ZHI_JIANG_WEN="70度终止降温";
	/**
	 * 二次粉料加入
	 */
	public static final String ER_CI_FEN_LIAO_JIA_RU="二次粉料加入";
	/**
	 * 二次保温时间20
	 */
	public static final String ECBWSJES="二次保温时间20";
	/**
	 * 助剂6加入
	 */
	public static final String ZHU_JI_LIU_JIA_RU="助剂6加入";
	/**
	 * 水加入
	 */
	public static final String SHUI_JIA_RU="水加入";
	/**
	 * 酸计量筒称重
	 */
	public static final String SUAN_JI_LIANG_TONG_CHENG_ZHONG="酸计量筒称重";
	/**
	 * 冷却至结束
	 */
	public static final String LQZJS=LENG_QUE+ZHI+JIE_SHU;
	
	public static final String VALUE="value";
	public static final String UNIT="unit";
	public static final String ROW_NUMBER="rowNumber";
	public static final String COL_NUMBER="colNumber";
	
	/**
	 * M类未生成
	 */
	public static final String M_WSC="mWsc";
	/**
	 * U类未生成
	 */
	public static final String U_WSC="uWsc";
	/**
	 * M类已生成
	 */
	public static final String M_YSC="mYsc";
	/**
	 * U类已生成
	 */
	public static final String U_YSC="uYsc";
	
	public static final int OK_STATUS=1;
	public static final int NO_STATUS=0;
}

package javafish.clients.opc.connect;

import java.util.ArrayList;
import java.util.List;

import com.uWinOPCTjyx.util.APIUtil;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import javafish.clients.opc.JOpc;
import javafish.clients.opc.SynchReadItemExample;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.variant.Variant;

/**
 * 读取opc服务端
 * @author Administrator
 * @param <>
 */
//https://blog.csdn.net/weixin_37998887/article/details/72648809
//https://github.com/shuaiwang-java/reader_opc.git
public class ReaderOpc {
	
	//private static final Logger logger = LoggerFactory.getLogger(ReaderOpc.class);
	
	public static void main(String[] args) {
		
		SynchReadItemExample test = new SynchReadItemExample();
		JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
		
		/**
		 * 创建连接对象
		 * 127.0.0.1  				 opcService的地址,opcService在本地就填写本地地址
		 * KingView.View.1     opcService在系统注册表中的注册名,  组态王中的opcService默认名KingView.View.1,如果你不是使用组态王中的opcService那么请修改你要连接的注册名(opcService服务名称)
		 * JOPC1						OPC客户端的用户名---按你喜欢来填
		 */
		/*
		JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "M3N881PM37O1M1D");
		
		OpcGroup group = new OpcGroup("chanel1.device1._System", true, 500, 0.0f);
		
		// new Opcitem("K1.Value",true,"");    "K1.Value"  表示要读取opc服务器中的变量名称的值。
		group.addItem(new OpcItem("chanel1.device1._System._Error", true, ""));      
		group.addItem(new OpcItem("chanel1.device1._System._NoError", true, ""));
		group.addItem(new OpcItem("chanel1.device1._System._ScanMode", true, ""));
		group.addItem(new OpcItem("chanel1.device1._System._EncapsulationPort", true, ""));
		*/
		
//		JOpc jopc = new JOpc("127.0.0.1", "UWinTech.UWinOPCS.1", "OPS3-PC");

		//JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");

	//	JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");

		// OpcGroup group = new OpcGroup("_System", true, 500, 0.0f);

//
		JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");

		OpcGroup group = new OpcGroup("_System", true, 500, 0.0f);

		// new Opcitem("K1.Value",true,"");    "K1.Value"  表示要读取opc服务器中的变量名称的值。
		group.addItem(new OpcItem("_System._Date", true, ""));
		group.addItem(new OpcItem("_System._Date_Day", true, ""));
//-----------------------------------------------------------------------------------------------------
//		JOpc jopc = new JOpc("127.0.0.1", "UWinTech.UWinOPCS.1", "OPS3-PC");
//
//		OpcGroup group = new OpcGroup("反应釜1执行配方M[50]", true, 500, 0.0f);

//		//胶罐选择
//		group.addItem(new OpcItem("胶罐选择1_F2_AV", true, ""));
//
//		//釜底阀关闭提醒
//		group.addItem(new OpcItem("釜底阀关闭提醒_F2_AV", true, ""));
//
//		//加碱量范围上限
//		group.addItem(new OpcItem("加碱量范围上限_PF2_AV", true, ""));
//
//		//反应釜温度
//		group.addItem(new OpcItem("反应釜1温度_AV", true, ""));
//
//		//釜称重
//		group.addItem(new OpcItem("釜1称重_AV", true, ""));
//
//		//釜尿素放料阀
//		group.addItem(new OpcItem("釜1尿素放料阀_AV", true, ""));
//
//		//备料开始变量(M类)
//		group.addItem(new OpcItem("备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("备料开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("备料开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("备料开始_F5_AV", true, ""));
//
//
//		//备料开始变量(U类)???(报表里好像没有)
//
//
//		//降温完成(M类)
//		group.addItem(new OpcItem("降温完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F5_AV", true, ""));
//
//		//降温完成(U类)
//		group.addItem(new OpcItem("降温完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("降温完成_F5U_AV", true, ""));
//
//		//罐用前、后重量???(报表里好像没有)
//
//		//甲醛实际进料重量(M类)
//		group.addItem(new OpcItem("甲醛实际进料重量_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛实际进料重量_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛实际进料重量_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛实际进料重量_F5_AV", true, ""));
//
//		//甲醛实际进料重量(U类)???(报表里好像没有)
//
//		//甲醛备料开始(M类)
//		group.addItem(new OpcItem("甲醛备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F5_AV", true, ""));
//
//		//甲醛备料开始(U类)
//		group.addItem(new OpcItem("甲醛备料开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料开始_F5U_AV", true, ""));
//
//		//甲醛放料开始(M类)(流程里暂时好像用不上)
//		group.addItem(new OpcItem("甲醛放料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F5_AV", true, ""));
//
//		//甲醛放料开始(U类)(流程里暂时好像用不上)
//		group.addItem(new OpcItem("甲醛放料开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料开始_F5U_AV", true, ""));
//
//		//甲醛放料完成(M类)
//		group.addItem(new OpcItem("甲醛放料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F5_AV", true, ""));
//
//		//甲醛放料完成(U类)
//		group.addItem(new OpcItem("甲醛放料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛放料完成_F5U_AV", true, ""));
//
//		//加碱PH值正常(M类)
//		group.addItem(new OpcItem("加碱PH值正常_F1_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F2_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F3_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F4_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F5_AV", true, ""));
//
//		//加碱PH值正常(U类)
//		group.addItem(new OpcItem("加碱PH值正常_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH值正常_F5U_AV", true, ""));
//
//		//加碱前PH输入值(M类)
//		group.addItem(new OpcItem("加碱前PH输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F5_AV", true, ""));
//
//		//加碱前PH输入值(U类)
//		group.addItem(new OpcItem("加碱前PH输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱前PH输入值_F5U_AV", true, ""));
//
//		//加碱量提示
//		group.addItem(new OpcItem("加碱量提示_PF1_AV", true, ""));
//		group.addItem(new OpcItem("加碱量提示_PF3_AV", true, ""));
//		group.addItem(new OpcItem("加碱量提示_PF4_AV", true, ""));
//		group.addItem(new OpcItem("加碱量提示_PF5_AV", true, ""));
//
//		//加碱后PH输入值(M类)
//		group.addItem(new OpcItem("加碱后PH输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F5_AV", true, ""));
//
//		//加碱后PH输入值(U类)
//		group.addItem(new OpcItem("加碱后PH输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后PH输入值_F5U_AV", true, ""));
//
//		//助剂计量罐1、2称重
//		group.addItem(new OpcItem("助剂计量罐1称重", true, ""));
//		group.addItem(new OpcItem("助剂计量罐2称重", true, ""));
//
//		//允许一次加助剂(M类)
//		group.addItem(new OpcItem("允许一次加助剂_F1_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F2_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F3_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F4_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F5_AV", true, ""));
//
//		//允许一次加助剂(U类)
//		group.addItem(new OpcItem("允许一次加助剂_F1U_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F2U_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F3U_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F4U_AV", true, ""));
//		group.addItem(new OpcItem("允许一次加助剂_F5U_AV", true, ""));
//
//		//所有助剂加料完成(M类)
//		group.addItem(new OpcItem("所有助剂加料完成1_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F2_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F3_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F4_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F5_AV", true, ""));
//
//		//所有助剂加料完成(U类)
//		group.addItem(new OpcItem("所有助剂加料完成1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F2U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成1_F5U_AV", true, ""));
//
//		//加粉料提醒(M类)
//		group.addItem(new OpcItem("加粉料提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F5_AV", true, ""));
//
//		//加粉料提醒(U类)
//		group.addItem(new OpcItem("加粉料提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料提醒_F5U_AV", true, ""));
//
//		//粉料重量设定
//		group.addItem(new OpcItem("粉料重量设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("粉料重量设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("粉料重量设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("粉料重量设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("粉料重量设定_PF5_AV", true, ""));
//
//		//加粉料PH合格(M类)
//		group.addItem(new OpcItem("加粉料PH合格_F1_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F2_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F3_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F4_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F5_AV", true, ""));
//
//		//加粉料PH合格(U类)
//		group.addItem(new OpcItem("加粉料PH合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH合格_F5U_AV", true, ""));
//
//		//加粉料PH输入值(M类)
//		group.addItem(new OpcItem("加粉料PH输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F5_AV", true, ""));
//
//		//加粉料PH输入值(U类)
//		group.addItem(new OpcItem("加粉料PH输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加粉料PH输入值_F5U_AV", true, ""));
//
//		//升温开始(M类)
//		group.addItem(new OpcItem("升温开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F5_AV", true, ""));
//
//		//升温开始(U类)
//		group.addItem(new OpcItem("升温开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("升温开始_F5U_AV", true, ""));
//
//		//蒸汽压力???(好像没有)
//
//		//温度85与二次投料提醒(M类)
//		group.addItem(new OpcItem("温度85与二次投料提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F5_AV", true, ""));
//
//		//温度85与二次投料提醒(U类)
//		group.addItem(new OpcItem("温度85与二次投料提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("温度85与二次投料提醒_F5U_AV", true, ""));
//
//		//二次投料PH输入(M类)
//		group.addItem(new OpcItem("二次投料PH输入_F1_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F2_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F3_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F4_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F5_AV", true, ""));
//
//		//二次投料PH输入(U类)
//		group.addItem(new OpcItem("二次投料PH输入_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH输入_F5U_AV", true, ""));
//
//		//助剂计量罐3-5称重
//		group.addItem(new OpcItem("助剂计量罐3称重", true, ""));
//		group.addItem(new OpcItem("助剂计量罐4称重", true, ""));
//		group.addItem(new OpcItem("助剂计量罐5称重", true, ""));
//
//		//允许二次加助剂(M类)
//		group.addItem(new OpcItem("允许二次加助剂_F1_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F2_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F3_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F4_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F5_AV", true, ""));
//
//		//允许二次加助剂(U类)
//		group.addItem(new OpcItem("允许二次加助剂_F1U_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F2U_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F3U_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F4U_AV", true, ""));
//		group.addItem(new OpcItem("允许二次加助剂_F5U_AV", true, ""));
//
//		//所有助剂加料完成2(M类)
//		group.addItem(new OpcItem("所有助剂加料完成2_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F2_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F3_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F4_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F5_AV", true, ""));
//
//		//所有助剂加料完成2(U类)
//		group.addItem(new OpcItem("所有助剂加料完成2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F2U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂加料完成2_F5U_AV", true, ""));
//
//		//升温完成(M类)
//		group.addItem(new OpcItem("升温完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F5_AV", true, ""));
//
//		//升温完成(U类)
//		group.addItem(new OpcItem("升温完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("升温完成_F5U_AV", true, ""));
//
//		//温度98PH合格(M类)
//		group.addItem(new OpcItem("温度98PH合格_F1_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F2_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F3_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F4_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F5_AV", true, ""));
//
//		//温度98PH合格(U类)
//		group.addItem(new OpcItem("温度98PH合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH合格_F5U_AV", true, ""));
//
//		//温度98PH(M类)
//		group.addItem(new OpcItem("温度98PH_F1_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F2_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F3_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F4_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F5_AV", true, ""));
//
//		//温度98PH(U类)
//		group.addItem(new OpcItem("温度98PH_F1U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F2U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F3U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F4U_AV", true, ""));
//		group.addItem(new OpcItem("温度98PH_F5U_AV", true, ""));
//
//		//测量冰水雾点提醒(M类)
//		group.addItem(new OpcItem("测量冰水雾点提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F5_AV", true, ""));
//
//		//测量冰水雾点提醒(U类)
//		group.addItem(new OpcItem("测量冰水雾点提醒_F1U_AV", true, ""));
//		//???好像缺少F2U_AV
//		group.addItem(new OpcItem("测量冰水雾点提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒_F5U_AV", true, ""));
//
//		//测量冰水雾点输入值(M类)
//		group.addItem(new OpcItem("测量冰水雾点输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F5_AV", true, ""));
//
//		//测量冰水雾点输入值(U类)
//		group.addItem(new OpcItem("测量冰水雾点输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点输入值_F5U_AV", true, ""));
//
//		//以下变量暂时用不到，不过也得分组。先留着以后可能用
//		//85度PH4上限
//		group.addItem(new OpcItem("_85度PH4上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4上限_PF5_AV", true, ""));
//
//		//反应结束
//		group.addItem(new OpcItem("反应结束F1_AV", true, ""));
//		group.addItem(new OpcItem("反应结束F2_AV", true, ""));
//		group.addItem(new OpcItem("反应结束F3_AV", true, ""));
//		group.addItem(new OpcItem("反应结束F4_AV", true, ""));
//		group.addItem(new OpcItem("反应结束F5_AV", true, ""));
//
//		//二次助剂后测PH提醒
//		group.addItem(new OpcItem("二次助剂后测PH提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("二次助剂后测PH提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("二次助剂后测PH提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("二次助剂后测PH提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("二次助剂后测PH提醒_F5_AV", true, ""));
//
//		//反应釜配方类型
//		group.addItem(new OpcItem("反应釜1配方类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜2配方类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜4配方类型_AV", true, ""));
//
//		//反应釜胶种类型
//		group.addItem(new OpcItem("反应釜1胶种类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜2胶种类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜3胶种类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜4胶种类型_AV", true, ""));
//		group.addItem(new OpcItem("反应釜5胶种类型_AV", true, ""));
//
//		//当前胶种显示
//		group.addItem(new OpcItem("当前胶种显示_F1_AV", true, ""));
//		group.addItem(new OpcItem("当前胶种显示_F2_AV", true, ""));
//		group.addItem(new OpcItem("当前胶种显示_F3_AV", true, ""));
//		group.addItem(new OpcItem("当前胶种显示_F4_AV", true, ""));
//		group.addItem(new OpcItem("当前胶种显示_F5_AV", true, ""));
//
//		//结束生产
//		group.addItem(new OpcItem("结束生产_F1_AV", true, ""));
//		group.addItem(new OpcItem("结束生产_F2_AV", true, ""));
//		group.addItem(new OpcItem("结束生产_F3_AV", true, ""));
//		group.addItem(new OpcItem("结束生产_F4_AV", true, ""));
//		group.addItem(new OpcItem("结束生产_F5_AV", true, ""));
//
//		//保温秒显示
//		group.addItem(new OpcItem("保温秒显示_F1_AV", true, ""));
//		group.addItem(new OpcItem("保温秒显示_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温秒显示_F3_AV", true, ""));
//		group.addItem(new OpcItem("保温秒显示_F4_AV", true, ""));
//		group.addItem(new OpcItem("保温秒显示_F5_AV", true, ""));
//
//		//釜助剂进料阀手动
//		group.addItem(new OpcItem("釜助剂进料阀手动_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜助剂进料阀手动_F2_AV", true, ""));
//		group.addItem(new OpcItem("釜助剂进料阀手动_F3_AV", true, ""));
//		group.addItem(new OpcItem("釜助剂进料阀手动_F4_AV", true, ""));
//		group.addItem(new OpcItem("釜助剂进料阀手动_F5_AV", true, ""));
//
//		//水备料开始
//		group.addItem(new OpcItem("水备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("水备料开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("水备料开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("水备料开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("水备料开始_F5_AV", true, ""));
//
//		//趋势图
//		group.addItem(new OpcItem("趋势图_F1_AV", true, ""));
//		group.addItem(new OpcItem("趋势图_F2_AV", true, ""));
//		group.addItem(new OpcItem("趋势图_F3_AV", true, ""));
//		group.addItem(new OpcItem("趋势图_F4_AV", true, ""));
//		group.addItem(new OpcItem("趋势图_F5_AV", true, ""));
//
//		//画面运行釜
//		group.addItem(new OpcItem("画面运行釜1_AV", true, ""));
//		group.addItem(new OpcItem("画面运行釜2_AV", true, ""));
//		group.addItem(new OpcItem("画面运行釜4_AV", true, ""));
//
//		//助剂一次放料开始
//		group.addItem(new OpcItem("助剂一次放料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F5_AV", true, ""));
//
//		//加粉PH2
//		group.addItem(new OpcItem("加粉PH2_F1_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F2_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F3_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F4_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F5_AV", true, ""));
//
//		//升温按钮
//		group.addItem(new OpcItem("升温按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F3_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F5_AV", true, ""));
//
//		//升温结束
//		group.addItem(new OpcItem("升温结束_F1_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F2_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F3_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F4_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F5_AV", true, ""));
//
//		//温度98提醒
//		group.addItem(new OpcItem("温度98提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F5_AV", true, ""));
//
//		//二次投料PH合格
//		group.addItem(new OpcItem("二次投料PH合格_F1_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F2_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F3_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F4_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F5_AV", true, ""));
//
//		//二次投放助剂提醒
//		group.addItem(new OpcItem("二次投放助剂提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F5_AV", true, ""));
//
//		//所有助剂备料完成
//		group.addItem(new OpcItem("所有助剂备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F1_AV", true, ""));
//
//		//一次助剂备料开始
//		group.addItem(new OpcItem("一次助剂备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F1_AV", true, ""));
//
//		//加热启动
//		group.addItem(new OpcItem("加热启动_F1_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F2_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F3_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F4_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F5_AV", true, ""));
//
//		//保温按钮
//		group.addItem(new OpcItem("保温按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F3_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F5_AV", true, ""));
//
//		//保温启动
//		group.addItem(new OpcItem("保温启动_F1_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F3_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F4_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F5_AV", true, ""));
//
//		//保温完成
//		group.addItem(new OpcItem("保温完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F5_AV", true, ""));
//
//		//蒸汽阀开次数
//		group.addItem(new OpcItem("蒸汽阀开次数_F1_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F2_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F3_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F4_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F5_AV", true, ""));
//
//		//蒸汽阀关次数
//		group.addItem(new OpcItem("蒸汽阀关次数_F1_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F2_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F3_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F4_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F5_AV", true, ""));
//
//		//测20雾点时间
//		group.addItem(new OpcItem("测20雾点时间_F1_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F3_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F4_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F5_AV", true, ""));
//
//		//测20雾点提醒
//		group.addItem(new OpcItem("测20雾点提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F5_AV", true, ""));
//
//		//测20雾点输入值
//		group.addItem(new OpcItem("测20雾点输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F5_AV", true, ""));
//
//		//测水数时间
//		group.addItem(new OpcItem("测水数时间_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F3_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F5_AV", true, ""));
//
//		//测水数提醒
//		group.addItem(new OpcItem("测水数提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F5_AV", true, ""));
//
//		//测水数输入值1
//		group.addItem(new OpcItem("测水数输入值1_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F3_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F5_AV", true, ""));
//
//		//聚合终点
//		group.addItem(new OpcItem("聚合终点_F1_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F2_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F3_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F4_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F5_AV", true, ""));
//
//		//停热降温
//		group.addItem(new OpcItem("停热降温_F1_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F2_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F3_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F4_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F5_AV", true, ""));
//
//		//停热降温水数提醒
//		group.addItem(new OpcItem("停热降温水数提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F5_AV", true, ""));
//
//		//停热降温水数输入值
//		group.addItem(new OpcItem("停热降温水数输入值_F1_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F2_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F3_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F4_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F5_AV", true, ""));
//
//		//保温分钟计时
//		group.addItem(new OpcItem("保温分钟计时_F1_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F3_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F4_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F5_AV", true, ""));
//
//		//测水数输入值2
//		group.addItem(new OpcItem("测水数输入值2_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F5_AV", true, ""));
//
//		//测水数输入值3
//		group.addItem(new OpcItem("测水数输入值3_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F5_AV", true, ""));
//
//		//测水数输入值4
//		group.addItem(new OpcItem("测水数输入值4_F1_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F5_AV", true, ""));
//
//		//冷却按钮
//		group.addItem(new OpcItem("冷却按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F5_AV", true, ""));
//
//		//冷却启动
//		group.addItem(new OpcItem("冷却启动_F1_AV", true, ""));
//
//		//冷却结束
//		group.addItem(new OpcItem("冷却结束_F1_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F2_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F4_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F5_AV", true, ""));
//
//		//开始降温
//		group.addItem(new OpcItem("开始降温_F1_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F2_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F3_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F4_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F5_AV", true, ""));
//
//		//冷却升温开始
//		group.addItem(new OpcItem("冷却升温开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F5_AV", true, ""));
//
//		//终检按钮
//		group.addItem(new OpcItem("终检按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F5_AV", true, ""));
//
//		//终检开始
//		group.addItem(new OpcItem("终检开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F5_AV", true, ""));
//
//		//终检结束
//		group.addItem(new OpcItem("终检结束_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F5_AV", true, ""));
//
//		//终检PH
//		group.addItem(new OpcItem("终检PH_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F5_AV", true, ""));
//
//		//终检水数
//		group.addItem(new OpcItem("终检水数_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F5_AV", true, ""));
//
//		//终检数据3
//		group.addItem(new OpcItem("终检数据3_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F5_AV", true, ""));
//
//		//排胶启动
//		group.addItem(new OpcItem("排胶启动_F1_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F2_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F3_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F4_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F5_AV", true, ""));
//
//		//排胶开始
//		group.addItem(new OpcItem("排胶开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F3_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F5_AV", true, ""));
//
//		//釜1底阀开启提醒
//		group.addItem(new OpcItem("釜1底阀开启提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F5_AV", true, ""));
//
//		//允许开始排胶
//		group.addItem(new OpcItem("允许开始排胶_F1_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F2_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F3_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F4_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F5_AV", true, ""));
//
//		//排胶完成
//		group.addItem(new OpcItem("排胶完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("排胶完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("排胶完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("排胶完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("排胶完成_F5_AV", true, ""));
//
//		//釜1底阀关闭提醒
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F5_AV", true, ""));
//
//		//釜运行标记
//		group.addItem(new OpcItem("釜运行标记_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F2_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F3_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_4_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F5_AV", true, ""));
//
//		//助剂7加料完成
//		group.addItem(new OpcItem("助剂7加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂7加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂7加料完成_F4_AV", true, ""));
//
//		//自检开始
//		group.addItem(new OpcItem("自检开始_F1_AV", true, ""));
//		group.addItem(new OpcItem("自检开始_F2_AV", true, ""));
//		group.addItem(new OpcItem("自检开始_F4_AV", true, ""));
//		group.addItem(new OpcItem("自检开始_F5_AV", true, ""));
//
//		//自检完成
//		group.addItem(new OpcItem("自检完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("自检完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("自检完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("自检完成_F5_AV", true, ""));
//
//		//备料按钮
//		group.addItem(new OpcItem("备料按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("备料按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("备料按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("备料按钮_F5_AV", true, ""));
//
//		//备料完成
//		group.addItem(new OpcItem("备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("备料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("备料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("备料完成_F5_AV", true, ""));
//
//		//助剂1实际进料
//		group.addItem(new OpcItem("助剂1实际进料_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂1实际进料_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂1实际进料_F4_AV", true, ""));
//
//		//助剂2实际进料
//		group.addItem(new OpcItem("助剂2实际进料_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂2实际进料_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂2实际进料_F4_AV", true, ""));
//
//		//助剂3实际进料
//		group.addItem(new OpcItem("助剂3实际进料_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂3实际进料_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂3实际进料_F4_AV", true, ""));
//
//		//助剂4实际进料
//		group.addItem(new OpcItem("助剂4实际进料_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂4实际进料_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂4实际进料_F4_AV", true, ""));
//
//		//助剂5实际进料
//		group.addItem(new OpcItem("助剂5实际进料_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂5实际进料_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂5实际进料_F4_AV", true, ""));
//
//		//甲醛加注完成
//		group.addItem(new OpcItem("甲醛加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛加注完成_F5_AV", true, ""));
//
//		//加水实际重量
//		group.addItem(new OpcItem("加水实际重量_F1_AV", true, ""));
//		group.addItem(new OpcItem("加水实际重量_F2_AV", true, ""));
//		group.addItem(new OpcItem("加水实际重量_F4_AV", true, ""));
//		group.addItem(new OpcItem("加水实际重量_F5_AV", true, ""));
//
//
//		//加水完成
//		group.addItem(new OpcItem("加水完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("加水完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("加水完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("加水完成_F5_AV", true, ""));
//
//		//助剂1加注完成
//		group.addItem(new OpcItem("助剂1加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加注完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加注完成_F5_AV", true, ""));
//
//		//助剂2加注完成
//		group.addItem(new OpcItem("助剂2加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加注完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加注完成_F5_AV", true, ""));
//
//		//助剂3加注完成
//		group.addItem(new OpcItem("助剂3加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加注完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加注完成_F5_AV", true, ""));
//
//		//助剂4加注完成
//		group.addItem(new OpcItem("助剂4加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加注完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加注完成_F5_AV", true, ""));
//
//		//助剂5加注完成
//		group.addItem(new OpcItem("助剂5加注完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加注完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加注完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加注完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加注完成_F5_AV", true, ""));
//
//		//甲醛混合物计算值
//		group.addItem(new OpcItem("甲醛混合物计算值_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛混合物计算值_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛混合物计算值_F5_AV", true, ""));
//
//		//甲醛混合物重量
//		group.addItem(new OpcItem("甲醛混合物重量_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛混合物重量_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛混合物重量_F5_AV", true, ""));
//
//		//甲醛备料完成
//		group.addItem(new OpcItem("甲醛备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛备料完成_F5_AV", true, ""));
//
//		//加料按钮
//		group.addItem(new OpcItem("加料按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F5_AV", true, ""));
//
//		//开始加料
//		group.addItem(new OpcItem("开始加料_F1_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F2_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F3_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F4_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F5_AV", true, ""));
//
//		//加料完成
//		group.addItem(new OpcItem("加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F5_AV", true, ""));
//
//		//加甲醛前釜重量
//		group.addItem(new OpcItem("加甲醛前釜重量_F1_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛前釜重量_F2_AV", true, ""));
//
//		//加甲醛后釜重量
//		group.addItem(new OpcItem("加甲醛后釜重量_F1_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛后釜重量_F2_AV", true, ""));
//
//		//加甲醛过程结束
//		group.addItem(new OpcItem("加甲醛过程结束_F1_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F2_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F4_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F5_AV", true, ""));
//
//		//加甲醛重量正常
//		group.addItem(new OpcItem("加甲醛重量正常_F1_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F2_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F4_AV", true, ""));
//
//		//釜加料温度正常
//		group.addItem(new OpcItem("釜加料温度正常_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F2_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F4_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F5_AV", true, ""));
//
//		//助剂1加料完成
//		group.addItem(new OpcItem("助剂1加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F5_AV", true, ""));
//
//		//助剂2加料完成
//		group.addItem(new OpcItem("助剂2加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F5_AV", true, ""));
//
//		//助剂3加料完成
//		group.addItem(new OpcItem("助剂3加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F5_AV", true, ""));
//
//		//助剂4加料完成
//		group.addItem(new OpcItem("助剂4加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F5_AV", true, ""));
//
//		//助剂5加料完成
//		group.addItem(new OpcItem("助剂5加料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F5_AV", true, ""));
//
//		//加碱操作提醒
//		group.addItem(new OpcItem("加碱操作提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F5_AV", true, ""));
//
//		//水放料开始
//		group.addItem(new OpcItem("水放料开始_F3_AV", true, ""));
//
//		//水放料完成
//		group.addItem(new OpcItem("水放料完成_F3_AV", true, ""));
//
//		//Date_174
//		group.addItem(new OpcItem("Date_174_F2_AV", true, ""));
//		group.addItem(new OpcItem("Date_174_F3_AV", true, ""));
//		group.addItem(new OpcItem("Date_174_F5_AV", true, ""));
//
//		//Date_175
//		group.addItem(new OpcItem("Date_175_F2_AV", true, ""));
//		group.addItem(new OpcItem("Date_175_F3_AV", true, ""));
//		group.addItem(new OpcItem("Date_175_F4_AV", true, ""));
//		group.addItem(new OpcItem("Date_175_F5_AV", true, ""));
//
//		//Date_176
//		group.addItem(new OpcItem("Date_176_F2_AV", true, ""));
//		group.addItem(new OpcItem("Date_176_F3_AV", true, ""));
//		group.addItem(new OpcItem("Date_176_F4_AV", true, ""));
//		group.addItem(new OpcItem("Date_176_F5_AV", true, ""));
//
//		//终检水PH提醒
//		group.addItem(new OpcItem("终检水PH提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("终检水PH提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("终检水PH提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("终检水PH提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("终检水PH提醒_F5_AV", true, ""));
//
//		//胶种选择按钮
//		group.addItem(new OpcItem("胶种选择按钮_F1_AV", true, ""));
//		group.addItem(new OpcItem("胶种选择按钮_F2_AV", true, ""));
//		group.addItem(new OpcItem("胶种选择按钮_F3_AV", true, ""));
//		group.addItem(new OpcItem("胶种选择按钮_F4_AV", true, ""));
//		group.addItem(new OpcItem("胶种选择按钮_F5_AV", true, ""));
//
//		//Date_192
//		group.addItem(new OpcItem("Date_192_F3_AV", true, ""));
//
//		//测量冰水雾点提醒时间
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F1_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F4_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F5_AV", true, ""));
//
//		//测水数输入值5
//		group.addItem(new OpcItem("测水数输入值5_F2_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F4_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F5_AV", true, ""));
//
//		//酸备料开始
//		group.addItem(new OpcItem("酸备料开始_F4_AV", true, ""));
//
//		//Date_108
//		group.addItem(new OpcItem("Date_108_F2_AV", true, ""));
//		group.addItem(new OpcItem("Date_108_F5_AV", true, ""));
//
//		//Date_109
//		group.addItem(new OpcItem("Date_109_F2_AV", true, ""));
//		group.addItem(new OpcItem("Date_109_F5_AV", true, ""));
//
//		//开始加料
//		group.addItem(new OpcItem("开始加料_F1U_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F2U_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F3U_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F4U_AV", true, ""));
//		group.addItem(new OpcItem("开始加料_F5U_AV", true, ""));
//
//		//加料完成
//		group.addItem(new OpcItem("加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加料完成_F5U_AV", true, ""));
//
//		//加甲醛过程结束
//		group.addItem(new OpcItem("加甲醛过程结束_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛过程结束_F5U_AV", true, ""));
//
//		//加甲醛重量正常
//		group.addItem(new OpcItem("加甲醛重量正常_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛重量正常_F5U_AV", true, ""));
//
//		//釜加料温度正常
//		group.addItem(new OpcItem("釜加料温度正常_F1U_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F2U_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F3U_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F4U_AV", true, ""));
//		group.addItem(new OpcItem("釜加料温度正常_F5U_AV", true, ""));
//
//		//助剂1加料完成
//		group.addItem(new OpcItem("助剂1加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂1加料完成_F5U_AV", true, ""));
//
//		//助剂2加料完成
//		group.addItem(new OpcItem("助剂2加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂2加料完成_F5U_AV", true, ""));
//
//		//助剂3加料完成
//		group.addItem(new OpcItem("助剂3加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂3加料完成_F5U_AV", true, ""));
//
//		//助剂4加料完成
//		group.addItem(new OpcItem("助剂4加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂4加料完成_F5U_AV", true, ""));
//
//		//助剂5加料完成
//		group.addItem(new OpcItem("助剂5加料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂5加料完成_F5U_AV", true, ""));
//
//		//加碱操作提醒
//		group.addItem(new OpcItem("加碱操作提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱操作提醒_F5U_AV", true, ""));
//
//		//加粉PH2
//		group.addItem(new OpcItem("加粉PH2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加粉PH2_F5U_AV", true, ""));
//
//		//升温按钮
//		group.addItem(new OpcItem("升温按钮_F1U_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F2U_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F3U_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F4U_AV", true, ""));
//		group.addItem(new OpcItem("升温按钮_F5U_AV", true, ""));
//
//		//升温结束
//		group.addItem(new OpcItem("升温结束_F1U_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F2U_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F3U_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F4U_AV", true, ""));
//		group.addItem(new OpcItem("升温结束_F5U_AV", true, ""));
//
//		//二次投放助剂提醒
//		group.addItem(new OpcItem("二次投放助剂提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次投放助剂提醒_F5U_AV", true, ""));
//
//		//所有助剂备料完成
//		group.addItem(new OpcItem("所有助剂备料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("所有助剂备料完成_F5U_AV", true, ""));
//
//		//一次助剂备料开始
//		group.addItem(new OpcItem("一次助剂备料开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("一次助剂备料开始_F5U_AV", true, ""));
//
//		//助剂一次放料开始
//		group.addItem(new OpcItem("助剂一次放料开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂一次放料开始_F5U_AV", true, ""));
//
//		//温度98提醒
//		group.addItem(new OpcItem("温度98提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("温度98提醒_F5U_AV", true, ""));
//
//		//二次投料PH合格
//		group.addItem(new OpcItem("二次投料PH合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次投料PH合格_F5U_AV", true, ""));
//
//		//加热启动
//		group.addItem(new OpcItem("加热启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加热启动_F5U_AV", true, ""));
//
//		//保温按钮
//		group.addItem(new OpcItem("保温按钮_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温按钮_F5U_AV", true, ""));
//
//		//保温启动
//		group.addItem(new OpcItem("保温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温启动_F5U_AV", true, ""));
//
//		//保温完成
//		group.addItem(new OpcItem("保温完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温完成_F5U_AV", true, ""));
//
//		//蒸汽阀开次数
//		group.addItem(new OpcItem("蒸汽阀开次数_F1U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F2U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F3U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F4U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀开次数_F5U_AV", true, ""));
//
//		//蒸汽阀关次数
//		group.addItem(new OpcItem("蒸汽阀关次数_F1U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F2U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F3U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F4U_AV", true, ""));
//		group.addItem(new OpcItem("蒸汽阀关次数_F5U_AV", true, ""));
//
//		//釜内有胶
//		group.addItem(new OpcItem("釜内有胶_F1_AV", true, ""));
//		group.addItem(new OpcItem("釜内有胶_F2_AV", true, ""));
//		group.addItem(new OpcItem("釜内有胶_F3_AV", true, ""));
//		group.addItem(new OpcItem("釜内有胶_F4_AV", true, ""));
//		group.addItem(new OpcItem("釜内有胶_F5_AV", true, ""));
//
//		//加水开始
//		group.addItem(new OpcItem("加水开始F1_AV", true, ""));
//		group.addItem(new OpcItem("加水开始F2_AV", true, ""));
//		group.addItem(new OpcItem("加水开始F3_AV", true, ""));
//		group.addItem(new OpcItem("加水开始F4_AV", true, ""));
//		group.addItem(new OpcItem("加水开始F5_AV", true, ""));
//
//		//其他釜备放料提醒
//		group.addItem(new OpcItem("其他釜备放料提醒_F1_AV", true, ""));
//		group.addItem(new OpcItem("其他釜备放料提醒_F2_AV", true, ""));
//		group.addItem(new OpcItem("其他釜备放料提醒_F3_AV", true, ""));
//		group.addItem(new OpcItem("其他釜备放料提醒_F4_AV", true, ""));
//		group.addItem(new OpcItem("其他釜备放料提醒_F5_AV", true, ""));
//
//		//测20雾点时间
//		group.addItem(new OpcItem("测20雾点时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点时间_F5U_AV", true, ""));
//
//		//测20雾点提醒
//		group.addItem(new OpcItem("测20雾点提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点提醒_F5U_AV", true, ""));
//
//		//测20雾点输入值
//		group.addItem(new OpcItem("测20雾点输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测20雾点输入值_F5U_AV", true, ""));
//
//		//测水数时间
//		group.addItem(new OpcItem("测水数时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数时间_F5U_AV", true, ""));
//
//		//测水数提醒
//		group.addItem(new OpcItem("测水数提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数提醒_F5U_AV", true, ""));
//
//		//测水数输入值1
//		group.addItem(new OpcItem("测水数输入值1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值1_F5U_AV", true, ""));
//
//
//		//聚合终点
//		group.addItem(new OpcItem("聚合终点_F1U_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F2U_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F3U_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F4U_AV", true, ""));
//		group.addItem(new OpcItem("聚合终点_F5U_AV", true, ""));
//
//		//停热降温
//		group.addItem(new OpcItem("停热降温_F1U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F2U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F3U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F4U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温_F5U_AV", true, ""));
//
//		//停热降温水数提醒
//		group.addItem(new OpcItem("停热降温水数提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数提醒_F5U_AV", true, ""));
//
//		//停热降温水数输入值
//		group.addItem(new OpcItem("停热降温水数输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("停热降温水数输入值_F5U_AV", true, ""));
//
//		//保温分钟计时
//		group.addItem(new OpcItem("保温分钟计时_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温分钟计时_F5U_AV", true, ""));
//
//		//测水数输入值2
//		group.addItem(new OpcItem("测水数输入值2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值2_F5U_AV", true, ""));
//
//		//测水数输入值3
//		group.addItem(new OpcItem("测水数输入值3_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值3_F5U_AV", true, ""));
//
//		//测水数输入值4
//		group.addItem(new OpcItem("测水数输入值4_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值4_F5U_AV", true, ""));
//
//		//测水数输入值5
//		group.addItem(new OpcItem("测水数输入值5_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F2U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测水数输入值5_F5U_AV", true, ""));
//
//		//终检合格
//		group.addItem(new OpcItem("终检合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检合格_F5U_AV", true, ""));
//
//		//二次加215启动
//		group.addItem(new OpcItem("二次加215启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次加215启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次加215启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次加215启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次加215启动_F5U_AV", true, ""));
//
//		//冷却按钮
//		group.addItem(new OpcItem("冷却按钮_F1U_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F2U_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F3U_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F4U_AV", true, ""));
//		group.addItem(new OpcItem("冷却按钮_F5U_AV", true, ""));
//
//		//冷却启动
//		group.addItem(new OpcItem("冷却启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("冷却启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("冷却启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("冷却启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("冷却启动_F5U_AV", true, ""));
//
//		//冷却结束
//		group.addItem(new OpcItem("冷却结束_F1U_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F2U_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F3U_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F4U_AV", true, ""));
//		group.addItem(new OpcItem("冷却结束_F5U_AV", true, ""));
//
//		//开始降温
//		group.addItem(new OpcItem("开始降温_F1U_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F2U_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F3U_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F4U_AV", true, ""));
//		group.addItem(new OpcItem("开始降温_F5U_AV", true, ""));
//
//		//冷却升温开始
//		group.addItem(new OpcItem("冷却升温开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("冷却升温开始_F5U_AV", true, ""));
//
//		//终检按钮
//		group.addItem(new OpcItem("终检按钮_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检按钮_F5U_AV", true, ""));
//
//		//终检开始
//		group.addItem(new OpcItem("终检开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检开始_F5U_AV", true, ""));
//
//		//终检结束
//		group.addItem(new OpcItem("终检结束_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检结束_F5U_AV", true, ""));
//
//		//终检PH
//		group.addItem(new OpcItem("终检PH_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检PH_F5U_AV", true, ""));
//
//		//终检水数
//		group.addItem(new OpcItem("终检水数_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检水数_F5U_AV", true, ""));
//
//		//终检数据3
//		group.addItem(new OpcItem("终检数据3_F1U_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F2U_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F3U_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F4U_AV", true, ""));
//		group.addItem(new OpcItem("终检数据3_F5U_AV", true, ""));
//
//		//排胶启动
//		group.addItem(new OpcItem("排胶启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("排胶启动_F5U_AV", true, ""));
//
//		//排胶开始
//		group.addItem(new OpcItem("排胶开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("排胶开始_F5U_AV", true, ""));
//
//		//釜1底阀开启提醒
//		group.addItem(new OpcItem("釜1底阀开启提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀开启提醒_F5U_AV", true, ""));
//
//		//最终降温开始
//		group.addItem(new OpcItem("最终降温开始_F1U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温开始_F2U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温开始_F3U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温开始_F4U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温开始_F5U_AV", true, ""));
//
//		//第三次降温启动
//		group.addItem(new OpcItem("第三次降温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第三次降温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第三次降温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第三次降温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第三次降温启动_F5U_AV", true, ""));
//
//		//碱量输入值
//		group.addItem(new OpcItem("加碱量输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量输入值_F5U_AV", true, ""));
//
//		//允许开始排胶
//		group.addItem(new OpcItem("允许开始排胶_F1U_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F2U_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F3U_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F4U_AV", true, ""));
//		group.addItem(new OpcItem("允许开始排胶_F5U_AV", true, ""));
//
//		//保温运行指令
//		group.addItem(new OpcItem("保温运行指令_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温运行指令_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温运行指令_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温运行指令_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温运行指令_F5U_AV", true, ""));
//
//		//第二次保温启动
//		group.addItem(new OpcItem("第二次保温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第二次保温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第二次保温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第二次保温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第二次保温启动_F5U_AV", true, ""));
//
//		//第一次降温启动
//		group.addItem(new OpcItem("第一次降温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次降温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第一次降温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次降温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次降温启动_F5U_AV", true, ""));
//
//		//釜1底阀关闭提醒
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("釜1底阀关闭提醒_F5U_AV", true, ""));
//
//		//釜运行标记
//		group.addItem(new OpcItem("釜运行标记_F1U_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F2U_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F3U_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F4U_AV", true, ""));
//		group.addItem(new OpcItem("釜运行标记_F5U_AV", true, ""));
//
//		//第一次保温测量提醒
//		group.addItem(new OpcItem("第一次保温测量提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温测量提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温测量提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温测量提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温测量提醒_F5U_AV", true, ""));
//
//		//第一次保温PH输入1
//		group.addItem(new OpcItem("第一次保温PH输入1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入1_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入1_F5U_AV", true, ""));
//
//		//第一次保温PH输入2
//		group.addItem(new OpcItem("第一次保温PH输入2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入2_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温PH输入2_F5U_AV", true, ""));
//
//		//第一次保温合格
//		group.addItem(new OpcItem("第一次保温合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温合格_F5U_AV", true, ""));
//
//		//一次降温加酸提醒
//		group.addItem(new OpcItem("一次降温加酸提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸提醒_F5U_AV", true, ""));
//
//		//一次降温加酸PH输入
//		group.addItem(new OpcItem("一次降温加酸PH输入_F1U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH输入_F2U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH输入_F3U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH输入_F4U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH输入_F5U_AV", true, ""));
//
//		//一次降温加酸PH合格
//		group.addItem(new OpcItem("一次降温加酸PH合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸PH合格_F5U_AV", true, ""));
//
//		//一次降温加酸量
//		group.addItem(new OpcItem("一次降温加酸量_F1U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸量_F2U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸量_F3U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸量_F4U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温加酸量_F5U_AV", true, ""));
//
//
//		//聚合度输入提醒
//		group.addItem(new OpcItem("聚合度输入提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入提醒_F5U_AV", true, ""));
//
//		//聚合度输入值
//		group.addItem(new OpcItem("聚合度输入值_F1U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入值_F2U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入值_F3U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入值_F4U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度输入值_F5U_AV", true, ""));
//
//		//聚合度合格
//		group.addItem(new OpcItem("聚合度合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("聚合度合格_F5U_AV", true, ""));
//
//		//二次保温分钟计时
//		group.addItem(new OpcItem("二次保温分钟计时_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温分钟计时_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温分钟计时_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温分钟计时_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温分钟计时_F5U_AV", true, ""));
//
//		//第二次降温启动
//		group.addItem(new OpcItem("第二次降温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第二次降温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第二次降温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第二次降温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第二次降温启动_F5U_AV", true, ""));
//
//		//二次投粉提醒
//		group.addItem(new OpcItem("二次投粉提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次投粉提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次投粉提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次投粉提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次投粉提醒_F5U_AV", true, ""));
//
//		//第三次保温启动
//		group.addItem(new OpcItem("第三次保温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温启动_F2U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温启动_F5U_AV", true, ""));
//
//
//		//加碱提醒
//		group.addItem(new OpcItem("加碱提醒_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱提醒_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱提醒_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱提醒_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱提醒_F5U_AV", true, ""));
//
//		//加碱PH输入
//		group.addItem(new OpcItem("加碱PH输入_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH输入_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH输入_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH输入_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH输入_F5U_AV", true, ""));
//
//		//加碱PH合格
//		group.addItem(new OpcItem("加碱PH合格_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH合格_F2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH合格_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH合格_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱PH合格_F5U_AV", true, ""));
//
//		//二次加粉完成
//		group.addItem(new OpcItem("二次加粉完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("二次加粉完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("二次加粉完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("二次加粉完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("二次加粉完成_F5U_AV", true, ""));
//
//		//加料按钮
//		group.addItem(new OpcItem("加料按钮_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F3U_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加料按钮_F5U_AV", true, ""));
//
//		//保温提醒1时间
//		group.addItem(new OpcItem("保温提醒1时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1时间_F5U_AV", true, ""));
//
//		//保温提醒2时间
//		group.addItem(new OpcItem("保温提醒2时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2时间_F5U_AV", true, ""));
//
//		//保温提醒3时间
//		group.addItem(new OpcItem("保温提醒3时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3时间_F5U_AV", true, ""));
//
//		//保温提醒4时间
//		group.addItem(new OpcItem("保温提醒4时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4时间_F5U_AV", true, ""));
//
//		//保温提醒5时间
//		group.addItem(new OpcItem("保温提醒5时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5时间_F5U_AV", true, ""));
//
//		//保温提醒1
//		group.addItem(new OpcItem("保温提醒1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒1_F5U_AV", true, ""));
//
//		//保温提醒2
//		group.addItem(new OpcItem("保温提醒2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2_F5U_AV", true, ""));
//
//		//保温提醒3
//		group.addItem(new OpcItem("保温提醒3_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3_F5U_AV", true, ""));
//
//		//保温提醒4
//		group.addItem(new OpcItem("保温提醒4_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4_F5U_AV", true, ""));
//
//		//保温提醒5
//		group.addItem(new OpcItem("保温提醒5_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5_F5U_AV", true, ""));
//
//		//保温提醒水记录1
//		group.addItem(new OpcItem("保温提醒水记录1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录1_F5U_AV", true, ""));
//
//		//保温提醒水记录2
//		group.addItem(new OpcItem("保温提醒水记录2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录2_F5U_AV", true, ""));
//
//		//保温提醒水记录3
//		group.addItem(new OpcItem("保温提醒水记录3_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录3_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录3_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录3_F5U_AV", true, ""));
//
//		//保温提醒水记录4
//		group.addItem(new OpcItem("保温提醒水记录4_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录4_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录4_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录4_F5U_AV", true, ""));
//
//		//保温提醒水记录5
//		group.addItem(new OpcItem("保温提醒水记录5_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录5_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录5_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录5_F5U_AV", true, ""));
//
//		//保温提醒PH记录1
//		group.addItem(new OpcItem("保温提醒PH记录1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录1_F5U_AV", true, ""));
//
//		//保温提醒PH记录2
//		group.addItem(new OpcItem("保温提醒PH记录2_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录2_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录2_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录2_F5U_AV", true, ""));
//
//		//保温提醒PH记录3
//		group.addItem(new OpcItem("保温提醒PH记录3_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录3_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录3_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录3_F5U_AV", true, ""));
//
//		//保温提醒PH记录4
//		group.addItem(new OpcItem("保温提醒PH记录4_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录4_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录4_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录4_F5U_AV", true, ""));
//
//		//保温提醒PH记录5
//		group.addItem(new OpcItem("保温提醒PH记录5_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录5_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录5_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录5_F5U_AV", true, ""));
//
//		//测量冰水雾点提醒时间
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("测量冰水雾点提醒时间_F5U_AV", true, ""));
//
//		//第一次保温启动
//		group.addItem(new OpcItem("第一次保温启动_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温启动_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温启动_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第一次保温启动_F5U_AV", true, ""));
//
//		//第三次保温时间
//		group.addItem(new OpcItem("第三次保温时间_F1U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温时间_F3U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温时间_F4U_AV", true, ""));
//		group.addItem(new OpcItem("第三次保温时间_F5U_AV", true, ""));
//
//		//加甲醛前釜重量
//		group.addItem(new OpcItem("加甲醛前釜重量_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛前釜重量_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛前釜重量_F5U_AV", true, ""));
//
//		//加甲醛后釜重量
//		group.addItem(new OpcItem("加甲醛后釜重量_F1U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛后釜重量_F4U_AV", true, ""));
//		group.addItem(new OpcItem("加甲醛后釜重量_F5U_AV", true, ""));
//
//		//胶罐选择1
//		group.addItem(new OpcItem("胶罐选择1_F4U_AV", true, ""));
//
//		//胶罐选择4
//		group.addItem(new OpcItem("胶罐选择4_F4U_AV", true, ""));
//
//		//中转罐容量允许
//		group.addItem(new OpcItem("中转罐容量允许_F4U_AV", true, ""));
//
//		//二次加水和小料提醒
//		group.addItem(new OpcItem("二次加水和小料提醒_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次加水和小料提醒_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次加水和小料提醒_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次加水和小料提醒_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次加水和小料提醒_FU5_AV", true, ""));
//
//		//二次加水完成
//		group.addItem(new OpcItem("二次加水完成_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次加水完成_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次加水完成_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次加水完成_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次加水完成_FU5_AV", true, ""));
//
//		//二次加215完成
//		group.addItem(new OpcItem("二次加215完成_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次加215完成_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次加215完成_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次加215完成_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次加215完成_FU5_AV", true, ""));
//
//		//二次加水启动
//		group.addItem(new OpcItem("二次加水启动_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次加水启动_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次加水启动_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次加水启动_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次加水启动_FU5_AV", true, ""));
//
//		//助剂6一次添加完成
//		group.addItem(new OpcItem("助剂6一次添加完成_FU1_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次添加完成_FU2_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次添加完成_FU3_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次添加完成_FU4_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次添加完成_FU5_AV", true, ""));
//
//		//助剂6备料开始1
//		group.addItem(new OpcItem("助剂6备料开始1_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料开始1_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料开始1_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料开始1_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料开始1_F5U_AV", true, ""));
//
//		//助剂6备料完成
//		group.addItem(new OpcItem("助剂6备料完成_F1_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料完成_F2_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料完成_F3_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料完成_F4_AV", true, ""));
//		group.addItem(new OpcItem("助剂6备料完成_F5_AV", true, ""));
//
//		//加碱后测PH2上限
//		group.addItem(new OpcItem("加碱后测PH2上限_PU1_AV", true, ""));
//
//		//加碱后测PH2下限
//		group.addItem(new OpcItem("加碱后测PH2下限_PU1_AV", true, ""));
//
//		//加碱后测PH2上限
//		group.addItem(new OpcItem("加碱后测PH2上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2上限_PF4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2上限_PF5U_AV", true, ""));
//
//		//加碱后测PH2下限
//		group.addItem(new OpcItem("加碱后测PH2下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2下限_PF4U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH2下限_PF5U_AV", true, ""));
//
//		//助剂6二次添加完成
//		group.addItem(new OpcItem("助剂6二次添加完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次添加完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次添加完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次添加完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次添加完成_F5U_AV", true, ""));
//
//		//助剂6二次备料完成
//		group.addItem(new OpcItem("助剂6二次备料完成_F5U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次备料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次备料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次备料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次备料完成_F1U_AV", true, ""));
//
//		//保温后助剂6备料完成
//		group.addItem(new OpcItem("保温后助剂6备料完成_F1U_AV", true, ""));
//		group.addItem(new OpcItem("保温后助剂6备料完成_F2U_AV", true, ""));
//		group.addItem(new OpcItem("保温后助剂6备料完成_F3U_AV", true, ""));
//		group.addItem(new OpcItem("保温后助剂6备料完成_F4U_AV", true, ""));
//		group.addItem(new OpcItem("保温后助剂6备料完成_F5U_AV", true, ""));
//
//		//二次放水启动
//		group.addItem(new OpcItem("二次放水启动_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次放水启动_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次放水启动_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次放水启动_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次放水启动_FU5_AV", true, ""));
//
//		//二次加水备料完成
//		group.addItem(new OpcItem("二次加水备料完成_FU1_AV", true, ""));
//		group.addItem(new OpcItem("二次加水备料完成_FU2_AV", true, ""));
//		group.addItem(new OpcItem("二次加水备料完成_FU3_AV", true, ""));
//		group.addItem(new OpcItem("二次加水备料完成_FU4_AV", true, ""));
//		group.addItem(new OpcItem("二次加水备料完成_FU5_AV", true, ""));
//
//		//水转换值
//		group.addItem(new OpcItem("水转换值_PU1_AV", true, ""));
//
//		//甲醛转换值
//		group.addItem(new OpcItem("甲醛转换值_PU1_AV", true, ""));
//
//		//甲醛
//		group.addItem(new OpcItem("甲醛_PF1_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF2_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF3_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF4_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF5_AV", true, ""));
//
//		//水
//		group.addItem(new OpcItem("水_PF1_AV", true, ""));
//		group.addItem(new OpcItem("水_PF2_AV", true, ""));
//		group.addItem(new OpcItem("水_PF3_AV", true, ""));
//		group.addItem(new OpcItem("水_PF4_AV", true, ""));
//		group.addItem(new OpcItem("水_PF5_AV", true, ""));
//
//		//PH1上限
//		group.addItem(new OpcItem("PH1上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF5_AV", true, ""));
//
//		//PH1下限
//		group.addItem(new OpcItem("PH1下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF5_AV", true, ""));
//
//		//加碱量范围下限
//		group.addItem(new OpcItem("加碱量范围下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF5_AV", true, ""));
//
//		//PH2上限
//		group.addItem(new OpcItem("PH2上限_PF1_AV", true, ""));
//
//		//PH2下限
//		group.addItem(new OpcItem("PH2下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH2下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH2下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH2下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH2下限_PF5_AV", true, ""));
//
//		//助剂1
//		group.addItem(new OpcItem("助剂1_PF1_AV", true, ""));
//		group.addItem(new OpcItem("助剂1_PF2_AV", true, ""));
//		group.addItem(new OpcItem("助剂1_PF3_AV", true, ""));
//		group.addItem(new OpcItem("助剂1_PF4_AV", true, ""));
//		group.addItem(new OpcItem("助剂1_PF5_AV", true, ""));
//
//		//助剂2
//		group.addItem(new OpcItem("助剂2_PF1_AV", true, ""));
//		group.addItem(new OpcItem("助剂2_PF2_AV", true, ""));
//		group.addItem(new OpcItem("助剂2_PF3_AV", true, ""));
//		group.addItem(new OpcItem("助剂2_PF4_AV", true, ""));
//		group.addItem(new OpcItem("助剂2_PF5_AV", true, ""));
//
//		//PH3上限
//		group.addItem(new OpcItem("PH3上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH3上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH3上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH3上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH3上限_PF5_AV", true, ""));
//
//		//PH3下限
//		group.addItem(new OpcItem("PH3下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH3下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH3下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH3下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH3下限_PF5_AV", true, ""));
//
//		//最高温度设定
//		group.addItem(new OpcItem("最高温度设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF5_AV", true, ""));
//
//		//保温温度范围上限
//		group.addItem(new OpcItem("保温温度范围上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围上限_PF5_AV", true, ""));
//
//		//保温温度范围下限
//		group.addItem(new OpcItem("保温温度范围下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("保温温度范围下限_PF5_AV", true, ""));
//
//		//升温中加料温度设定
//		group.addItem(new OpcItem("升温中加料温度设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("升温中加料温度设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("升温中加料温度设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("升温中加料温度设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("升温中加料温度设定_PF5_AV", true, ""));
//
//		//_85度PH4下限
//		group.addItem(new OpcItem("_85度PH4下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("_85度PH4下限_PF5_AV", true, ""));
//
//		//助剂3
//		group.addItem(new OpcItem("助剂3_PF1_AV", true, ""));
//		group.addItem(new OpcItem("助剂3_PF2_AV", true, ""));
//		group.addItem(new OpcItem("助剂3_PF3_AV", true, ""));
//		group.addItem(new OpcItem("助剂3_PF4_AV", true, ""));
//		group.addItem(new OpcItem("助剂3_PF5_AV", true, ""));
//
//		//助剂4
//		group.addItem(new OpcItem("助剂4_PF1_AV", true, ""));
//		group.addItem(new OpcItem("助剂4_PF2_AV", true, ""));
//		group.addItem(new OpcItem("助剂4_PF3_AV", true, ""));
//		group.addItem(new OpcItem("助剂4_PF4_AV", true, ""));
//		group.addItem(new OpcItem("助剂4_PF5_AV", true, ""));
//
//		//助剂5
//		group.addItem(new OpcItem("助剂5_PF1_AV", true, ""));
//		group.addItem(new OpcItem("助剂5_PF2_AV", true, ""));
//		group.addItem(new OpcItem("助剂5_PF3_AV", true, ""));
//		group.addItem(new OpcItem("助剂5_PF4_AV", true, ""));
//		group.addItem(new OpcItem("助剂5_PF5_AV", true, ""));
//
//		//PH5上限
//		group.addItem(new OpcItem("PH5上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF5_AV", true, ""));
//
//		//PH5下限
//		group.addItem(new OpcItem("PH5下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("PH5下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("PH5下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("PH5下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("PH5下限_PF5_AV", true, ""));
//
//		//测冰水雾点时间设定
//		group.addItem(new OpcItem("测冰水雾点时间设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("测冰水雾点时间设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("测冰水雾点时间设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("测冰水雾点时间设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("测冰水雾点时间设定_PF5_AV", true, ""));
//
//		//测常温雾点时间设定
//		group.addItem(new OpcItem("测常温雾点时间设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("测常温雾点时间设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("测常温雾点时间设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("测常温雾点时间设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("测常温雾点时间设定_PF5_AV", true, ""));
//
//		//停止保温开始降温
//		group.addItem(new OpcItem("停止保温开始降温_PF1_AV", true, ""));
//		group.addItem(new OpcItem("停止保温开始降温_PF2_AV", true, ""));
//		group.addItem(new OpcItem("停止保温开始降温_PF3_AV", true, ""));
//		group.addItem(new OpcItem("停止保温开始降温_PF4_AV", true, ""));
//		group.addItem(new OpcItem("停止保温开始降温_PF5_AV", true, ""));
//
//		//停止降温温度设定
//		group.addItem(new OpcItem("停止降温温度设定_PF1_AV", true, ""));
//		group.addItem(new OpcItem("停止降温温度设定_PF2_AV", true, ""));
//		group.addItem(new OpcItem("停止降温温度设定_PF3_AV", true, ""));
//		group.addItem(new OpcItem("停止降温温度设定_PF4_AV", true, ""));
//		group.addItem(new OpcItem("停止降温温度设定_PF5_AV", true, ""));
//
//		//停止搅拌
//		group.addItem(new OpcItem("停止搅拌_PF1_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF2_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF3_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF4_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF5_AV", true, ""));
//
//		//检测水数范围上限
//		group.addItem(new OpcItem("检测水数范围上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF5_AV", true, ""));
//
//		//检测水数范围下限
//		group.addItem(new OpcItem("检测水数范围下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF5_AV", true, ""));
//
//		//检测PH范围上限
//		group.addItem(new OpcItem("检测PH范围上限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF5_AV", true, ""));
//
//		//检测PH范围下限
//		group.addItem(new OpcItem("检测PH范围下限_PF1_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF2_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF3_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF4_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF5_AV", true, ""));
//
//		//甲醛
//		group.addItem(new OpcItem("甲醛_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("甲醛_PF4U_AV", true, ""));
//
//		//水
//		group.addItem(new OpcItem("水_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("水_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("水_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("水_PF4U_AV", true, ""));
//
//		//PH1上限
//		group.addItem(new OpcItem("PH1上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("PH1上限_PF4U_AV", true, ""));
//
//		//PH1下限
//		group.addItem(new OpcItem("PH1下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("PH1下限_PF4U_AV", true, ""));
//
//		//加碱量范围上限
//		group.addItem(new OpcItem("加碱量范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围上限_PF4U_AV", true, ""));
//
//		//加碱量范围下限
//		group.addItem(new OpcItem("加碱量范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱量范围下限_PF4U_AV", true, ""));
//
//		//助剂6一次加
//		group.addItem(new OpcItem("助剂6一次加_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次加_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次加_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6一次加_PF4U_AV", true, ""));
//
//		//助剂6二次加
//		group.addItem(new OpcItem("助剂6二次加_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次加_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次加_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("助剂6二次加_PF4U_AV", true, ""));
//
//		//粉料1重量设定
//		group.addItem(new OpcItem("粉料1重量设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("粉料1重量设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("粉料1重量设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("粉料1重量设定_PF4U_AV", true, ""));
//
//		//粉料2重量设定
//		group.addItem(new OpcItem("粉料2重量设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("粉料2重量设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("粉料2重量设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("粉料2重量设定_PF4U_AV", true, ""));
//
//		//投料PH3上限
//		group.addItem(new OpcItem("投料PH3上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3上限_PF4U_AV", true, ""));
//
//		//投料PH3下限
//		group.addItem(new OpcItem("投料PH3下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("投料PH3下限_PF4U_AV", true, ""));
//
//		//最高温度设定
//		group.addItem(new OpcItem("最高温度设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("最高温度设定_PF4U_AV", true, ""));
//
//		//一次保温温度范围上限
//		group.addItem(new OpcItem("一次保温温度范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围上限_PF4U_AV", true, ""));
//
//		//一次保温温度范围下限
//		group.addItem(new OpcItem("一次保温温度范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温温度范围下限_PF4U_AV", true, ""));
//
//		//一次保温时间设定
//		group.addItem(new OpcItem("一次保温时间设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温时间设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温时间设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("一次保温时间设定_PF4U_AV", true, ""));
//
//		//最高温PH4上限
//		group.addItem(new OpcItem("最高温PH4上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4上限_PF4U_AV", true, ""));
//
//		//最高温PH4下限
//		group.addItem(new OpcItem("最高温PH4下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("最高温PH4下限_PF4U_AV", true, ""));
//
//		//PH5上限
//		group.addItem(new OpcItem("PH5上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("PH5上限_PF4U_AV", true, ""));
//
//		//PH5下限
//		group.addItem(new OpcItem("PH5下限_PF1U_AV",  true, ""));
//		group.addItem(new OpcItem("PH5下限_PF2U_AV",  true, ""));
//		group.addItem(new OpcItem("PH5下限_PF3U_AV",  true, ""));
//		group.addItem(new OpcItem("PH5下限_PF4U_AV",  true, ""));
//
//		//PH6上限
//		group.addItem(new OpcItem("PH6上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("PH6上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("PH6上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("PH6上限_PF4U_AV", true, ""));
//
//		//PH6下限
//		group.addItem(new OpcItem("PH6下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("PH6下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("PH6下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("PH6下限_PF4U_AV", true, ""));
//
//		//一次降温终止温度设定
//		group.addItem(new OpcItem("一次降温终止温度设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温终止温度设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温终止温度设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("一次降温终止温度设定_PF4U_AV", true, ""));
//
//		//加酸量设定提醒
//		group.addItem(new OpcItem("加酸量设定提醒_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加酸量设定提醒_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加酸量设定提醒_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加酸量设定提醒_PF4U_AV", true, ""));
//
//		//加酸后PH7上限
//		group.addItem(new OpcItem("加酸后PH7上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7上限_PF4U_AV", true, ""));
//
//		//加酸后PH7下限
//		group.addItem(new OpcItem("加酸后PH7下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后PH7下限_PF4U_AV", true, ""));
//
//		//加酸后输入之后自动计时
//		group.addItem(new OpcItem("加酸后输入之后自动计时_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后输入之后自动计时_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后输入之后自动计时_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加酸后输入之后自动计时_PF4U_AV", true, ""));
//
//		//系统提示不停测水数
//		group.addItem(new OpcItem("系统提示不停测水数_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("系统提示不停测水数_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("系统提示不停测水数_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("系统提示不停测水数_PF4U_AV", true, ""));
//
//		//二次降温终止温度
//		group.addItem(new OpcItem("二次降温终止温度_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("二次降温终止温度_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("二次降温终止温度_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("二次降温终止温度_PF4U_AV", true, ""));
//
//		//加215量
//		group.addItem(new OpcItem("加215量_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加215量_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加215量_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加215量_PF4U_AV", true, ""));
//
//		//加碱后测PH8上限
//		group.addItem(new OpcItem("加碱后测PH8上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8上限_PF4U_AV", true, ""));
//
//		//加碱后测PH8下限
//		group.addItem(new OpcItem("加碱后测PH8下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加碱后测PH8下限_PF4U_AV", true, ""));
//
//		//二次加料粉料1重量设定
//		group.addItem(new OpcItem("二次加料粉料1重量设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("二次加料粉料1重量设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("二次加料粉料1重量设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("二次加料粉料1重量设定_PF4U_AV", true, ""));
//
//		//加料后自动保温的时间设定
//		group.addItem(new OpcItem("加料后自动保温的时间设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("加料后自动保温的时间设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("加料后自动保温的时间设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("加料后自动保温的时间设定_PF4U_AV", true, ""));
//
//		//保温后加水量设定
//		group.addItem(new OpcItem("保温后加水量设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加水量设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加水量设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加水量设定_PF4U_AV", true, ""));
//
//		//保温后加助剂6量设定
//		group.addItem(new OpcItem("保温后加助剂6量设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加助剂6量设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加助剂6量设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("保温后加助剂6量设定_PF4U_AV", true, ""));
//
//		//最终降温终点温度设定
//		group.addItem(new OpcItem("最终降温终点温度设定_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温终点温度设定_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温终点温度设定_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("最终降温终点温度设定_PF4U_AV", true, ""));
//
//		//停止搅拌
//		group.addItem(new OpcItem("停止搅拌_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("停止搅拌_PF4U_AV", true, ""));
//
//		//检测水数范围上限
//		group.addItem(new OpcItem("检测水数范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围上限_PF4U_AV", true, ""));
//
//		//检测水数范围下限
//		group.addItem(new OpcItem("检测水数范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测水数范围下限_PF4U_AV", true, ""));
//
//		//检测PH范围上限
//		group.addItem(new OpcItem("检测PH范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围上限_PF4U_AV", true, ""));
//
//		//检测PH范围下限
//		group.addItem(new OpcItem("检测PH范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF4U_AV", true, ""));
//		group.addItem(new OpcItem("检测PH范围下限_PF5U_AV", true, ""));
//
//		//检测聚合度高限
//		group.addItem(new OpcItem("检测聚合度高限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度高限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度高限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度高限_PF4U_AV", true, ""));
//
//		//检测聚合度低限
//		group.addItem(new OpcItem("检测聚合度低限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度低限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度低限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("检测聚合度低限_PF4U_AV", true, ""));
//
//		//二次保温温度范围上限
//		group.addItem(new OpcItem("二次保温温度范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围上限_PF4U_AV", true, ""));
//
//		//二次保温温度范围下限
//		group.addItem(new OpcItem("二次保温温度范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("二次保温温度范围下限_PF4U_AV", true, ""));
//
//		//三次保温温度范围上限
//		group.addItem(new OpcItem("三次保温温度范围上限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围上限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围上限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围上限_PF4U_AV", true, ""));
//
//		//三次保温温度范围下限
//		group.addItem(new OpcItem("三次保温温度范围下限_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围下限_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围下限_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("三次保温温度范围下限_PF4U_AV", true, ""));
//
//		//保温提醒1时间
//		group.addItem(new OpcItem("保温提醒1时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4时间_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5时间_F2_AV", true, ""));
//
//		//保温提醒
//		group.addItem(new OpcItem("保温提醒1_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒2_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒3_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒4_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒5_F2_AV", true, ""));
//
//		//保温提醒水记录
//		group.addItem(new OpcItem("保温提醒水记录1_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录2_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录3_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录4_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒水记录5_F2_AV", true, ""));
//
//		//保温提醒PH记录
//		group.addItem(new OpcItem("保温提醒PH记录1_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录2_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录3_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录4_F2_AV", true, ""));
//		group.addItem(new OpcItem("保温提醒PH记录5_F2_AV", true, ""));
//
//		//计时提醒时间设置1
//		group.addItem(new OpcItem("计时提醒时间设置1_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置1_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置1_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置1_PF4U_AV", true, ""));
//
//		//计时提醒时间设置2
//		group.addItem(new OpcItem("计时提醒时间设置2_PF1U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置2_PF2U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置2_PF3U_AV", true, ""));
//		group.addItem(new OpcItem("计时提醒时间设置2_PF4U_AV", true, ""));


		jopc.addGroup(group);   //添加组
		OpcGroup responseGroup;

		try {
			jopc.connect();   //连接
			jopc.registerGroups();  //注册组
		} catch (ConnectivityException e1) {
			System.out.println("ConnectivityException="+e1.getMessage());
			//logger.error(e1.getMessage());
		} catch (UnableAddGroupException e) {
			System.out.println("UnableAddGroupException="+e.getMessage());
			//logger.error(e.getMessage());
		} catch (UnableAddItemException e) {
			System.out.println("UnableAddItemException="+e.getMessage());
			//logger.error(e.getMessage());
		}
		synchronized(test) {
			try {
				test.wait(50);
			} catch (InterruptedException e) {
				//logger.error(e.getMessage());
			}
		}
		//同步读取
		while (true) {
			try {
				synchronized(test) {
					test.wait(5000);
				}
				responseGroup = jopc.synchReadGroup(group);
//				ArrayList<OpcItem> opcItems = responseGroup.getItems();
				List<OpcItem> opcItems = new ArrayList<OpcItem>();
				OpcItem opcItem1 = new OpcItem("备料开始_F1_AV",false,"111");
				opcItem1.setValue(new Variant("0"));
				OpcItem opcItem2 = new OpcItem("降温完成_F1_AV",false,"111");
				opcItem2.setValue(new Variant("0"));
				OpcItem opcItem3 = new OpcItem("降温完成_F2_AV",false,"111");
				opcItem3.setValue(new Variant("0"));
				opcItems.add(opcItem1);
				opcItems.add(opcItem2);
				opcItems.add(opcItem3);
				for (OpcItem opcItem : opcItems) {
					System.out.println("Item名:" + opcItem.getItemName() + "  Item值: " + opcItem.getValue());
					//APIUtil.addPiCiU(opcItem.getValue().toString());
					//break;
				}
				APIUtil.addVar("addTriggerVarFromOpc",opcItems);
				break;
			} catch (ComponentNotFoundException e) {
				//logger.error(e.getMessage()); //获取responseGroup错误
				JOpc.coUninitialize();     //错误关闭连接
			}catch (InterruptedException e) {
				//logger.error(e.getMessage());
				JOpc.coUninitialize(); //错误关闭连接
			} catch (SynchReadException e) {
				e.printStackTrace();
			}
		}
	}
}



































































package com.zjbbTjyx.util;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.SynchReadItemExample;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.*;
import javafish.clients.opc.variant.Variant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zjbbTjyx.entity.*;

public class OpcUtil {
	
	private static boolean IS_TEST=false;
	private static List<OpcItem> imiOpcItemTVList,imiOpcItemPVList;
	private static JOpc jopcTV,jopcPV;
	private static OpcGroup opcGroupTV,opcGroupPV;
	private static SynchReadItemExample srieTV=new SynchReadItemExample();
	private static SynchReadItemExample sriePV=new SynchReadItemExample();
	
	
    public static void main(String[] args) {
        SynchReadItemExample test = new SynchReadItemExample();

        JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc

        JOpc jopc = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);

        OpcGroup group = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);

//        if (varNameList.size()>0){
//            for (String varName : varNameList) {
//                // new Opcitem("K1.Value",true,"");  "K1.Value" 表示要读取opc服务器中的变量名称的值。
////                group.addItem(new OpcItem( varName, true, ""));
//            }
//        }
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
        
        List<String> varNameList = new ArrayList<String>();
//		ArrayList<OpcItem> opcItems = responseGroup.getItems();
        List<OpcItem> opcItems = new ArrayList<OpcItem>();
        OpcItem opcItem1 = new OpcItem(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG+Constant.XHX+Constant.BSF_F1+Constant.XHX+Constant.AV,false,"111");
        opcItem1.setValue(new Variant("0"));
        opcItems.add(opcItem1);
        
        OpcItem opcItem2 = new OpcItem(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG+Constant.XHX+Constant.BSF_F1+Constant.XHX+Constant.AV,false,"111");
        opcItem2.setValue(new Variant("0"));
        opcItems.add(opcItem2);
        
        for (OpcItem opcItem : opcItems) {
        	varNameList.add(opcItem.getItemName());
            System.out.println("Item名:" + opcItem.getItemName() + "  Item值: " + opcItem.getValue());
        }
        System.out.println(opcItems.toString());

        try {
            getOpcItemTestList(varNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String readRecTypeByFId(int fId) {
    	String valueTxt=null;
    	if(IS_TEST) {
    		valueTxt="U";
    	}
    	else {
	    	List<String> opcVarNameList=new ArrayList<String>();
	    	opcVarNameList.add(Constant.FAN_YING_FU+fId+Constant.JIAO_ZHONG_LEI_XING+Constant.XHX+Constant.AV);//反应釜胶种类型
	    	ArrayList<OpcItem> opcItems = readJOpcPV(opcVarNameList);
	    	OpcItem opcItem = opcItems.get(0);
	    	String valueStr = opcItem.getValue().toString();
	    	System.out.println("valueStr==="+valueStr);
	    	switch (valueStr) {
			case "0.0":
			case "1.0":
			case "2.0":
			case "3.0":
			case "4.0":
			case "5.0":
				valueTxt="M";
				break;
			case "6.0":
			case "7.0":
				valueTxt="U";
				break;
			}
    	}
    	return valueTxt;
	}
    
    public static String readJZLXByFId(int fId) {
    	//反应釜胶种类型:1:A、2:B、3:G、4:F、5:H、6:C（U类）、7:C#（U类）
    	String valueTxt=null;
    	if(IS_TEST) {
    		valueTxt="A";
    	}
    	else {
	    	List<String> opcVarNameList=new ArrayList<String>();
	    	opcVarNameList.add(Constant.FAN_YING_FU+fId+Constant.JIAO_ZHONG_LEI_XING+Constant.XHX+Constant.AV);//反应釜胶种类型
	    	ArrayList<OpcItem> opcItems = readJOpcPV(opcVarNameList);
	    	OpcItem opcItem = opcItems.get(0);
	    	String valueStr = opcItem.getValue().toString();
	    	System.out.println("valueStr==="+valueStr);
	    	switch (valueStr) {
			case "0.0":
				valueTxt="";
				break;
			case "1.0":
				valueTxt="A";
				break;
			case "2.0":
				valueTxt="B";
				break;
			case "3.0":
				valueTxt="G";
				break;
			case "4.0":
				valueTxt="F";
				break;
			case "5.0":
				valueTxt="H";
				break;
			case "6.0":
				valueTxt="C";
				break;
			case "7.0":
				valueTxt="C#";
				break;
			}
    	}
    	return valueTxt;
    }
    
    public static Map<String, Object> readerOpcProVarByTVList(List<TriggerVar> triggerVarList){
    	Map<String,Object> json=new HashMap<String, Object>();
    	try {
	        TriggerVar triggerVar1 = null;//第一个触发器变量，可能是上升沿，也可能是下降沿
	        TriggerVar triggerVar2 = null;//第二个触发器变量，可能是上升沿，也可能是下降沿
	        for (int i = 0; i < triggerVarList.size(); i++) {
	            TriggerVar triggerVar = triggerVarList.get(i);
				if(i==0)
					triggerVar1 = triggerVar;
				else if(i==1)
					triggerVar2 = triggerVar;
			}
	        List<String> opcVarNameList=new ArrayList<String>();//要读取的opc变量集合
	        String tv1VarName = triggerVar1.getVarName();
	        Integer tv1FId=Integer.valueOf(triggerVar1.getFId());
	        
	        String tv2VarName = null;
	        Float tv2VarValue = null;
	        if(triggerVar2!=null) {
		        tv2VarName = triggerVar2.getVarName();
		        tv2VarValue = triggerVar2.getVarValue();
	        }
        
        	String tvRecType = triggerVar1.getRecType();
    		if(TriggerVar.M.equals(tvRecType)) {
				if (tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
					Integer tvFId = triggerVar1.getFId();
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) {//甲醛放料完成要记录(甲醛实际进料重量、加水实际重量、釜1称重、反应釜1温度)
		        	Integer tvFId = triggerVar1.getFId();
		        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		        	
		        	//甲醛实际进料重量
		            String jqsjjlzlPvVarNameQz=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
		            String jqsjjlzlOpcVarName = jqsjjlzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jqsjjlzlOpcVarName);
		            
		            //加水实际重量
		            String jssjzlPvVarNameQz=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
		            String jssjzlOpcVarName=jssjzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jssjzlOpcVarName);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		            
		            //釜(反应釜号)称重
		            String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=f1czPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        } 
		        else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)){//甲醛备料开始要记录(釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        } 
		        else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)){//加碱PH值正常要记录(加碱前PH输入值、加碱量提示、加碱后PH输入值、助剂计量罐1称重、助剂计量罐2称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //加碱前PH输入值
		            String jjqsrzPvVarName= Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;
		            String jjqsrzOpcVarName=jjqsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jjqsrzOpcVarName);
		            
		            //加碱量提示
		            String jjltsPvVarNameQz= Constant.JIA_JIAN_LIANG_TI_SHI;
		            String jjltsOpcVarName=jjltsPvVarNameQz+Constant.XHX+Constant.BSF_PF+tvFId+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jjltsOpcVarName);
		            
		            //加碱后PH输入值
		            String jjhsrzPvVarName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
		            String jjhsrzOpcVarName=jjhsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jjhsrzOpcVarName);
		            
		            //助剂计量罐1称重
		            String zjjlg1czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+Constant.CHENG_ZHONG;
		            String zjjlg1czOpcVarName=zjjlg1czPvVarName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(zjjlg1czOpcVarName);
		            
		            //助剂计量罐2称重
		            String zjjlg2czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG;
		            String zjjlg2czOpcVarName=zjjlg2czPvVarName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(zjjlg2czOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.XHX)){//允许一次加助剂要记录(釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        } 
		        else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.XHX)){//所有助剂加料完成1要记录(反应釜(号)温度、釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName==null) {//加粉料提醒(无尿素放料阀变量)要记录(粉料重量设定)
		            Integer tvFId = triggerVar1.getFId();
		            
		            //粉料重量设定
		        	String flzlsdVarNameQz = Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX+Constant.BSF_PF+tvFId;
		        	String flzlsdVarName = flzlsdVarNameQz+Constant.XHX+Constant.AV;
		        	
		            opcVarNameList.add(flzlsdVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {//加粉料提醒(有尿素放料阀变量)要记录(反应釜(号)温度、釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		        	if(tv2VarName.startsWith(Constant.FU+tvFId+Constant.NIAO_SU_FANG_LIAO_FA)) {
		                //反应釜(反应釜号)温度
		                String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		                String fyfwdPvVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		                
		                opcVarNameList.add(fyfwdPvVarName);
		                
		                //釜(反应釜号)称重
		                String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		                String fhczPvVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		                
		                opcVarNameList.add(fhczPvVarName);
		            }
		        }
		        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_PH_HE_GE)) {
		        	Integer tvFId = triggerVar1.getFId();
		        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		        	
		        	//加粉料PH输入值
		            String jflphsrzPvVarNameQz=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;
		            String jflphsrzOpcVarName=jflphsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jflphsrzOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始要记录(蒸汽压力MPa)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //蒸汽压力MPa
		            String zqylmpaPvVarNameQz=Constant.ZHENG_QI_YA_LI;
		            String zqylOpcVarName=zqylmpaPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(zqylOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.XHX)){//温度85与二次投料提醒要记录(反应釜(号)温度)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX)) {//二次助剂后测PH提醒要记录(二次投料PH输入)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //二次投料PH输入
		            String ectlphsrPvVarNameQz=Constant.ER_CI_TOU_LIAO_PH_SHU_RU;
		            String ectlphsrVarName=ectlphsrPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(ectlphsrVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.XHX)){//允许二次加助剂要记录(釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.XHX)){//所有助剂加料完成2要记录(反应釜(号)温度、釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成要记录(反应釜(号)温度)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.WEN_DU_98_PH+Constant.HE_GE+Constant.XHX)){//温度98PH合格要记录(温度98PH)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //温度98PH
		            String wd98phPvVarNameQz=Constant.WEN_DU_98_PH;
		            String wd98phOpcVarName=wd98phPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(wd98phOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+Constant.XHX)){//测量冰水雾点提醒要记录(测量冰水雾点输入值、测20雾点输入值、停热降温水数输入值)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //测量冰水雾点输入值
		            String clbswdsrzPvVarNameQz=Constant.CE_LIANG_BSWD_SRZ;
		            String clbswdsrzOpcVarName=clbswdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(clbswdsrzOpcVarName);
		            
		            //测20雾点输入值
		            String c20wdsrzPvVarNameQz=Constant.CE_20_WU_DIAN_SRZ;
		            String c20wdsrzOpcVarName=c20wdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(c20wdsrzOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.CE_SHUI_SHU_TI_XING+Constant.XHX)){//测水数提醒
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //停热降温水数输入值
		            String trjwsssrzPvVarNameQz=Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ;
		            String trjwsssrzOpcVarName=trjwsssrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(trjwsssrzOpcVarName);
		        }
		        else if(tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.XHX)){//聚合终点要记录(反应釜温度)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if(tv1VarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX)){//降温完成要记录(反应釜温度)
		            Integer tvFId = triggerVar1.getFId();
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING+Constant.XHX)){//终检水PH提醒
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //终检水数
		            String zjssPvVarNameQz=Constant.ZHONG_JIAN_SHUI_SHU;
		            String zjssOpcVarName=zjssPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(zjssOpcVarName);
		            
		            //终检PH
		            String zjphPvVarNameQz=Constant.ZHONG_JIAN_PH;
		            String zjphOpcVarName=zjphPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(zjphOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null) {//允许开始排胶(无胶罐选择变量)
		        	Integer tvFId = triggerVar1.getFId();
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		            
		            String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;
		            
		            addVarNameInList(opcVarNameList,jgxzPvVarNameQz);
		        }
		        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName!=null) {//允许开始排胶(有胶罐选择变量)
		        	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        	String jgcbzPvVarNameQz=Constant.JIAO_GUAN_CBZ;
			        	
			        	String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;
	                	int jghStart=tv2VarName.indexOf(jgxzPvVarNameQz)+jgxzPvVarNameQz.length();
	                	int jghEnd=tv2VarName.indexOf(Constant.XHX+Constant.AV);
	                	int jgh = Float.valueOf(tv2VarName.substring(jghStart,jghEnd)).intValue();
			        	
			        	addVarNameInList(opcVarNameList,jgcbzPvVarNameQz+jgh+Constant.CHENG_ZHONG);
		        	}
		        }
		        else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null) {//排胶完成(无胶罐选择变量)
		        	Integer tvFId = triggerVar1.getFId();
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        }
		        else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName!=null) {//排胶完成(有胶罐选择变量)
		        	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        	String jgcbzPvVarNameQz=Constant.JIAO_GUAN_CBZ;
			        	
			        	String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;//胶罐选择
	                	int jghStart=tv2VarName.indexOf(jgxzPvVarNameQz)+jgxzPvVarNameQz.length();
	                	int jghEnd=tv2VarName.indexOf(Constant.XHX+Constant.AV);
	                	int jgh = Float.valueOf(tv2VarName.substring(jghStart,jghEnd)).intValue();
			        	
			        	addVarNameInList(opcVarNameList,jgcbzPvVarNameQz+jgh+Constant.CHENG_ZHONG);
		        	}
		        }
    		}
    		else if(TriggerVar.U.equals(tvRecType)) {
    			//甲醛放料完成
		        if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) {//甲醛放料完成要记录(甲醛实际进料重量、加水实际重量、釜1称重、反应釜1温度)
		        	Integer tvFId = triggerVar1.getFId();
		        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		        	
		        	//甲醛实际进料重量
		            String jqsjjlzlPvVarNameQz=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
		            String jqsjjlzlOpcVarName = jqsjjlzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jqsjjlzlOpcVarName);
		            
		            //加水实际重量
		            String jssjzlPvVarNameQz=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
		            String jssjzlOpcVarName=jssjzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jssjzlOpcVarName);
		            
		            //反应釜(反应釜号)温度
		            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fyfwdOpcVarName);
		            
		            //釜(反应釜号)称重
		            String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=f1czPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        } 
		        else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)){//甲醛备料开始要记录(釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //釜(反应釜号)称重
		            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(fhczOpcVarName);
		        } 
		        else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)){//加碱PH值正常要记录(加碱前PH输入值、加碱量提示、加碱后PH输入值)
		            Integer tvFId = triggerVar1.getFId();
		            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
		            
		            //加碱前PH输入值
		            String jjqsrzPvVarName= Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;
		            String jjqsrzOpcVarName=jjqsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;

		            opcVarNameList.add(jjqsrzOpcVarName);
		            
		            //加碱量提示
		            String jjltsPvVarNameQz= Constant.JIA_JIAN_LIANG_TI_SHI;
		            String jjltsOpcVarName=jjltsPvVarNameQz+Constant.XHX+Constant.BSF_PF+tvFId+ERecord.U+Constant.XHX+Constant.AV;
		            
		            opcVarNameList.add(jjltsOpcVarName);
		            
		            //加碱后PH输入值
		            String jjhsrzPvVarName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
		            String jjhsrzOpcVarName=jjhsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
		            
					opcVarNameList.add(jjhsrzOpcVarName);
					
					//釜(反应釜号)称重
					String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
		        }
				else if (tv1VarName.startsWith(Constant.KAI_SHI_JIA_LIAO+Constant.XHX)){//开始加料
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//酸计量筒称重
					String fhczPvVarNameQz=Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG;
					String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)){//助剂6一次添加完成
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
					
					//釜(反应釜号)称重
					String fczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=fczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG+Constant.XHX)){//助剂6二次备料完成
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//酸计量筒称重
					String sjltczPvVarNameQz=Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG;
					String sjltczOpcVarName=sjltczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(sjltczOpcVarName);
					
					//釜(反应釜号)称重
					String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=f1czPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)){//助剂6二次添加完成
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
					
					//釜(反应釜号)称重
					String fczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=fczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName==null){//加粉料提醒(无尿素放料阀变量)要记录(粉料1重量设定、加粉料PH输入值)
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//粉料1重量设定
		        	String fl1zlsdPvVarNameQz = Constant.FEN_LIAO_1_ZHONG_LIANG_SHE_DING+Constant.XHX+Constant.BSF_PF+tvFId+TriggerVar.U;
					String fl1zlsdOpcVarName=fl1zlsdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fl1zlsdOpcVarName);
					
					//加粉料PH输入值
					String jflphsrzPvVarNameQz=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;
					String jflphsrzOpcVarName=jflphsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(jflphsrzOpcVarName);
				}
		        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {//加粉料提醒(有尿素放料阀变量)要记录(反应釜(号)温度、釜(号)称重)
		            Integer tvFId = triggerVar1.getFId();
		        	if(tv2VarName.startsWith(Constant.FU+tvFId+Constant.NIAO_SU_FANG_LIAO_FA)) {
		                //反应釜(反应釜号)温度
		                String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
		                String fyfwdPvVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
		                
		                opcVarNameList.add(fyfwdPvVarName);
		                
		                //釜(反应釜号)称重
		                String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
		                String fhczPvVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
		                
		                opcVarNameList.add(fhczPvVarName);
		            }
		        }
				else if (tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//蒸汽压力
					String zqylPvVarNameQz=Constant.ZHENG_QI_YA_LI;
					String zqylOpcVarName=zqylPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(zqylOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.WEN_DU_98_PH_HE_GE+Constant.XHX)){//温度98PH合格
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//温度98PH
					String wd98phPvVarNameQz=Constant.WEN_DU_98_PH;
					String wd98phOpcVarName=wd98phPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(wd98phOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.XHX)){//第一次保温启动
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.XHX)){//第一次保温合格
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XHX)){//一次降温加酸提醒
					Integer tvFId = triggerVar1.getFId();
					Float tvVarValue = triggerVar1.getVarValue();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					if(TriggerVar.UP==tvVarValue) {
						//反应釜(反应釜号)温度
						String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
						String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
						
						opcVarNameList.add(fyfwdOpcVarName);
					}
					else if(TriggerVar.DOWN==tvVarValue) {
						//一次降温加酸PH输入
						String ycjwjsphsrPvVarNameQz=Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU;
						String ycjwjsphsrOpcVarName=ycjwjsphsrPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
						
						opcVarNameList.add(ycjwjsphsrOpcVarName);
					}
				}
				else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.XHX)){//一次降温加酸合格
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//一次降温加酸量
					String ycjwjslPvVarNameQz=Constant.YI_CI_JIANG_WEN_JIA_SUAN_LIANG;
					String ycjwjslOpcVarName=ycjwjslPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(ycjwjslOpcVarName);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
					
					//一次降温加酸PH输入
					String ycjwjsphsrPvVarNameQz=Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU;
					String ycjwjsphsrOpcVarName=ycjwjsphsrPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(ycjwjsphsrOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+Constant.XHX)){//测量冰水雾点提醒
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//测量冰水雾点输入值
					String clbswdsrzPvVarNameQz=Constant.CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI;
					String clbswdsrzOpcVarName=clbswdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(clbswdsrzOpcVarName);
					
					//保温分钟计时
					String bwfzjsPvVarNameQz=Constant.BAO_WEN_FEN_ZHONG_JI_SHI;
					String bwfzjsOpcVarName=bwfzjsPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(bwfzjsOpcVarName);
					
					//测20雾点输入值
					String c20wdsrzPvVarNameQz=Constant.CE_20_WU_DIAN_SRZ;
					String c20wdsrzOpcVarName=c20wdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(c20wdsrzOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_HE_GE+Constant.XHX)){//加碱PH合格
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//加碱量范围下限
					String jjlfwxxPvVarNameQz=Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN;
					String jjlfwxxOpcVarName=jjlfwxxPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(jjlfwxxOpcVarName);
					
					//加碱PH输入
					String jjphsrPvVarNameQz=Constant.JIA_JIAN_PH_SHU_RU;
					String jjphsrOpcVarName=jjphsrPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(jjphsrOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)){//二次投粉
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//二次加粉料1重量设定
					String ecjfl1zlsdPvVarNameQz=Constant.ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING;
					String ecjfl1zlsdOpcVarName=ecjfl1zlsdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(ecjfl1zlsdOpcVarName);
					
					//保温后加助剂6量设定
					String bwhjzj6lsdPvVarNameQz=Constant.BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING;
					String bwhjzj6lsdOpcVarName=bwhjzj6lsdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(bwhjzj6lsdOpcVarName);
					
					//保温后加水量设定
					String bwhjslsdPvVarNameQz=Constant.BAO_WEN_HOU_JIA_SHUI_LIANG_SHE_DING;
					String bwhjslsdOpcVarName=bwhjslsdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(bwhjslsdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING+Constant.XHX)){//二次加水和小料提醒
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//反应釜(反应釜号)温度
					String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
					String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fyfwdOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null){//允许开始排胶(无胶罐选择变量)
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//釜(反应釜号)称重
					String fczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=fczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
		            
		            String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;
		            
		            addVarNameInList(opcVarNameList,jgxzPvVarNameQz);
				}
		        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName!=null) {//允许开始排胶(有胶罐选择变量)
		        	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        	String jgcbzPvVarNameQz=Constant.JIAO_GUAN_CBZ;
			        	
	                	int jghStart=tv2VarName.indexOf(Constant.JIAO_GUAN+Constant.XUAN_ZE)+4;
	                	int jghEnd=tv2VarName.indexOf(Constant.XHX+Constant.AV);
	                	int jgh = Float.valueOf(tv2VarName.substring(jghStart,jghEnd)).intValue();
			        	
			        	addVarNameInList(opcVarNameList,jgcbzPvVarNameQz+jgh+Constant.CHENG_ZHONG);
		        	}
		        }
				else if (tv1VarName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING+Constant.XHX)){//终检水PH提醒
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//终检水数
					String zjssPvVarNameQz=Constant.ZHONG_JIAN_SHUI_SHU;
					String zjssOpcVarName=zjssPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(zjssOpcVarName);
					
					//终检水PH提醒
					String zjsphtxPvVarNameQz=Constant.ZHONG_JIAN_SHUI_PH_TI_XING;
					String zjsphtxOpcVarName=zjsphtxPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(zjsphtxOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null){//排胶完成(无胶罐选择变量)
					Integer tvFId = triggerVar1.getFId();
					String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
					
					//釜(反应釜号)称重
					String fczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
					String fhczOpcVarName=fczPvVarNameQz+Constant.XHX+Constant.AV;
					
					opcVarNameList.add(fhczOpcVarName);
				}
				else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName!=null){//排胶完成(有胶罐选择变量)
					if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        	String jgcbzPvVarNameQz=Constant.JIAO_GUAN_CBZ;
			        	
	                	int jghStart=tv2VarName.indexOf(Constant.JIAO_GUAN+Constant.XUAN_ZE)+4;
	                	int jghEnd=tv2VarName.indexOf(Constant.XHX+Constant.AV);
	                	int jgh = Float.valueOf(tv2VarName.substring(jghStart,jghEnd)).intValue();
			        	
			        	addVarNameInList(opcVarNameList,jgcbzPvVarNameQz+jgh+Constant.CHENG_ZHONG);
		        	}
				}
			}
        

	        
	        ArrayList<OpcItem> opcItems = null;
	        if(IS_TEST) {
	            try {
	                opcItems = getOpcItemTestList(opcVarNameList);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        else {
	    		opcItems = readJOpcPV(opcVarNameList);
	        }

	        List<ProcessVar> proVarList=new ArrayList<ProcessVar>();
	        ProcessVar proVar=null;
	        if(opcItems!=null) {
	        	if(TriggerVar.M.equals(tvRecType)) {
			        for (OpcItem opcItem : opcItems) {//一个触发变量可能会查询多个过程变量，得用集合存储
			        	String itemName = opcItem.getItemName();
			        	String valueStr = opcItem.getValue().toString();
			        	Float value = null;
			        	if(itemName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        		Boolean valueBool = Boolean.valueOf(valueStr);
			        		value = (float)(valueBool?1:0);
			        	}
			        	else
			        		value = Float.valueOf(valueStr);
			        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
			        	
			        	String varName=null;
			        	if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) {//甲醛放料完成
			        		if(itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG+Constant.XHX)) {
			        			varName=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
			        		}
			        		else if(itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG+Constant.XHX)){
			                    varName=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
			                }
			        		else if(itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)) {
			        			varName=ERecord.JQFLWCSSYFYFWD;//甲醛放料完成上升沿反应釜温度
			        		}
			        		else if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.JQFLWCSSYFCZ;//甲醛放料完成上升沿釜称重
			                }
			        	}
			        	else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)){//甲醛备料开始
			                if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.JQBLKSSSYFCZ;//甲醛备料开始上升沿釜称重
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)){//加碱PH值正常
			                if(itemName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI+Constant.XHX)) {
			                    varName=Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;
			                }
			                else if(itemName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX)){
			                    varName=Constant.JIA_JIAN_LIANG_TI_SHI;
			                }
			                else if(itemName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI+Constant.XHX)){
			                    varName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
			                }
			                else if(itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)){
			                    varName=Constant.ZHU_JI_JI_LIANG_GUAN+tv1FId+Constant.CHENG_ZHONG;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.XHX)){//允许一次加助剂
			        	    if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.YXYCJZJSSYFCZ;//允许一次加助剂上升沿釜称重
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.XHX)){//所有助剂加料完成1
			                if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.SYZJJLWC1SSYFCZ;//所有助剂加料完成1上升沿釜称重
			                }else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=ERecord.SYZJJLWC1SSYFYFWD;//所有助剂加料完成1上升沿反应釜温度
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)){//加粉料提醒(无尿素放料阀变量)
			                if (itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX)){
			                    varName=Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {//加粉料提醒(有尿素放料阀变量)
			        		if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
			        			if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
				        			if(tv2VarValue==TriggerVar.UP) {
				        				varName=ERecord.FNSFLFSSYFCZ;//釜尿素放料阀上升沿釜称重
				        			}
				        			else {
				        				varName=ERecord.FNSFLFXJYFCZ;//釜尿素放料阀下降沿釜称重
				        			}
			        			}
			        			else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
				        			if(tv2VarValue==TriggerVar.DOWN) {
				        				varName=ERecord.FNSFLFXJYFYFWD;//釜尿素放料阀下降沿反应釜温度
				        			}
			        			}
			        		}
			        	}
			        	else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_PH_HE_GE+Constant.XHX)){//加粉料PH合格
			                if(itemName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI+Constant.XHX)){
			                    varName=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始
			                if (itemName.startsWith(Constant.ZHENG_QI_YA_LI)){
			                    varName=Constant.ZHENG_QI_YA_LI;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.XHX)){//温度85与二次投料提醒
			        	    if(itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX)){//二次助剂后测PH提醒
			        	    if (itemName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU+Constant.XHX)){
			                    varName=Constant.ER_CI_TOU_LIAO_PH_SHU_RU;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.XHX)){//允许二次加助剂
			        	    if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.XHX)){//'所有助剂加料完成2
			                if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			                }
			                else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成
			        	    if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=ERecord.SWWCSSYFYFWD;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.WEN_DU_98_PH+Constant.HE_GE+Constant.XHX)){//温度98PH合格
			                if (itemName.startsWith(Constant.WEN_DU_98_PH+Constant.XHX)){
			                    varName=Constant.WEN_DU_98_PH;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+Constant.XHX)){//测量冰水雾点提醒
			                if (itemName.startsWith(Constant.CE_LIANG_BSWD_SRZ+Constant.XHX)){
			                    varName=Constant.CE_LIANG_BSWD_SRZ;
			                }
			                else if(itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ+Constant.XHX)){
			                    varName=Constant.CE_20_WU_DIAN_SRZ;
			                }
			            }
			            else if(tv1VarName.startsWith(Constant.CE_SHUI_SHU_TI_XING+Constant.XHX)){//测水数提醒
			                if(itemName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ+Constant.XHX)){
			                    varName=Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.XHX)){//聚合终点
			        	    if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=ERecord.JHZDSSYFYFWD;
			                }
			            }
			            else if(tv1VarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX)){//降温完成
			                if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=ERecord.JWWCSSYFYFWD;
			                }
			            }
			            else if(tv1VarName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING+Constant.XHX)){//终检水PH提醒
			                if(itemName.startsWith(Constant.ZHONG_JIAN_SHUI_SHU+Constant.XHX)){
			                    varName=Constant.ZHONG_JIAN_SHUI_SHU;
			                }
			                else if(itemName.startsWith(Constant.ZHONG_JIAN_PH+Constant.XHX)){
			                    varName=Constant.ZHONG_JIAN_PH;
			                }
			            }
			            else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null) {//允许开始排胶
			            	if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)) {
			            		varName=ERecord.YXKSPJSSYFCZ;
			            	}
			                else if(itemName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)&&itemName.endsWith(Constant.XHX+Constant.AV)){
			                	if(value==TriggerVar.UP) {
					        		int lastXhxLoc = tv1VarName.lastIndexOf(Constant.XHX)+Constant.XHX.length();
					        		String rujgFlagStr = tv1VarName.substring(lastXhxLoc, tv1VarName.length());
					        		System.out.println("rujgFlagStr="+rujgFlagStr);
					        		int rujgFlag = Integer.valueOf(rujgFlagStr);
					        		switch (rujgFlag) {
									case Constant.BSF_JG1:
						        		varName=ERecord.DRJG1JGH;
										break;
									case Constant.BSF_JG2:
						        		varName=ERecord.DRJG2JGH;
										break;
									}
				                	
			                		String jgxzPvVarNameQz = Constant.JIAO_GUAN+Constant.XUAN_ZE;
				                	int jghStart=itemName.indexOf(jgxzPvVarNameQz)+jgxzPvVarNameQz.length();
				                	int jghEnd=itemName.indexOf(Constant.XHX+Constant.AV);
				                	Integer jgh = Integer.valueOf(itemName.substring(jghStart,jghEnd));
				                	/*
				                	switch (jgh) {
									case 1:
										jgmcQz="A1";
										break;
									case 2:
										jgmcQz="A2";
										break;
									case 3:
										jgmcQz="B1";
										break;
									case 4:
										jgmcQz="B2";
										break;
									case 5:
										jgmcQz="C1";
										break;
									case 6:
										jgmcQz="C2";
										break;
									case 7:
										jgmcQz="D1";
										break;
									case 8:
										jgmcQz="D2";
										break;
									case 9:
										jgmcQz="D3";
										break;
									case 10:
										jgmcQz="中转";
										break;
									}
									*/
				                	value=(float)jgh;
			                	}
			                }
			            }
				        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName!=null) {//允许开始排胶(有胶罐选择变量)
				        	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
				        		int lastXhxLoc = tv2VarName.indexOf(Constant.XHX);
				        		String rujgFlagStr = tv2VarName.substring(lastXhxLoc, tv2VarName.length());
				        		int rujgFlag = Integer.valueOf(rujgFlagStr);
				        		switch (rujgFlag) {
								case Constant.BSF_JG1:
					        		varName=ERecord.YXKSPJSSYJG1ZL;
									break;
								case Constant.BSF_JG2:
					        		varName=ERecord.YXKSPJSSYJG2ZL;
									break;
								}
				        	}
				        }
			            else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null) {//排胶完成
			            	if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)) {
			            		varName=ERecord.PJWCSSYFCZ;
			            	}
			            }
			            else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName!=null) {//排胶完成(有胶罐选择变量)
			            	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
				        		varName=ERecord.PJWCSSYJG1ZL;
				        	}
			            }
			
			        	if(StringUtils.isEmpty(varName))
			        		continue;
			        	
			        	String unit=null;//单位
			        	//判断单位
			        	if (itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||
			                itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)||
			                itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)||
			                itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)||
			                itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+Constant.CHENG_ZHONG)||
			                itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG)||
			                itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG3+Constant.CHENG_ZHONG)||
			                itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG4+Constant.CHENG_ZHONG)||
			                itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG5+Constant.CHENG_ZHONG)){
			                unit=Constant.KG;//kg
			            }
			        	else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			        	    unit=Constant.WEN_DU_DAN_WEI_SIGN;//°C
			            }
			        	else if (itemName.startsWith(Constant.ZHENG_QI_YA_LI)){
			        	    unit=Constant.MPA;//MPa
			            }
			
			            int paraType=0;//参数类型
			        	if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)||
			                itemName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)||
			                itemName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.ZHENG_QI_YA_LI)||
			                itemName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU)||
			                itemName.startsWith(Constant.WEN_DU_98_PH)||
			                itemName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING)||
			                itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ)||
			                itemName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)){
			                paraType=ProcessVar.GYCS;
			            }
			        	else if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)||
			                    itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)||
			                    itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||
			                    itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)){
			        	    paraType=ProcessVar.YLCS;
			            }
			        	
			        	proVar=new ProcessVar();
			        	proVar.setVarName(varName);
			        	proVar.setVarValue(value);
			        	proVar.setDealBz(ProcessVar.WCL);
			        	proVar.setUpdateTime(sysTime);
			        	proVar.setFId(triggerVar1.getFId());
			        	proVar.setRecType(triggerVar1.getRecType());
			        	proVar.setUnit(unit);
			        	proVar.setParaType(paraType);
			        	proVarList.add(proVar);
			            System.out.println("Item名:" + itemName + "  Item值: " + value);
			        }
	        	}
	        	else if(TriggerVar.U.equals(tvRecType)) {
	        		for (OpcItem opcItem : opcItems) {//一个触发变量可能会查询多个过程变量，得用集合存储
			        	String itemName = opcItem.getItemName();
			        	String valueStr = opcItem.getValue().toString();
			        	Float value = null;
			        	if(itemName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
			        		Boolean valueBool = Boolean.valueOf(valueStr);
			        		value = (float)(valueBool?1:0);
			        	}
			        	else
			        		value = Float.valueOf(valueStr);
			        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
			        	
			        	String varName=null;
			        	if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) {//甲醛放料完成
			        		if(itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG+Constant.XHX)) {
			        			varName=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
			        		}
			        		else if(itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG+Constant.XHX)){
			                    varName=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
			                }
			        		else if(itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)) {
			        			varName=ERecord.JQFLWCSSYFYFWD;//甲醛放料完成上升沿反应釜温度
			        		}
			        		else if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.JQFLWCSSYFCZ;//甲醛放料完成上升沿釜称重
			                }
			        	}
			        	else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)){//甲醛备料开始
			                if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.JQBLKSSSYFCZ;//甲醛备料开始上升沿釜称重
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)){//加碱PH值正常
			                if(itemName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI+Constant.XHX)) {
			                    varName=Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;
			                }
			                else if(itemName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX)){
			                    varName=Constant.JIA_JIAN_LIANG_TI_SHI;
			                }
			                else if(itemName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI+Constant.XHX)){
			                    varName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
			                }
			                else if(itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)){
			                    varName=Constant.ZHU_JI_JI_LIANG_GUAN+tv1FId+Constant.CHENG_ZHONG;
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.KAI_SHI_JIA_LIAO+Constant.XHX)){//开始加料
			        	    if (itemName.startsWith(Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG)){
			                    varName=ERecord.KSJLSSYSJLTCZ;//开始加料上升沿酸计量筒称重
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)){//助剂6一次添加完成
			        		if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			                    varName=ERecord.ZJLYCTJWCSSYFYFWD;//助剂6一次添加完成上升沿反应釜温度
			                }
			                else if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.ZJLYCTJWCSSYFCZ;//助剂6一次添加完成上升沿釜称重
			                }
			            }
			        	else if(tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG+Constant.XHX)) {//助剂6二次备料完成
			        		if (itemName.startsWith(Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG)){
			                    varName=ERecord.ZJLECBLWCSSYSJLTCZ;//助剂6二次备料完成上升沿酸计量筒称重
			                }
			                else if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
			                    varName=ERecord.ZJLECBLWCSSYFCZ;//助剂6二次备料完成上升沿釜称重
			                }
			        	}
						else if(tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)){//助剂6二次添加完成
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.ZJLECTJWCSSYFYFWD;//助剂6二次添加完成上升沿反应釜温度
							}
							else if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
								varName=ERecord.ZJLECTJWCSSYFCZ;//助剂6二次添加完成上升沿釜称重
							}
						}
						else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName==null){//加粉料提醒(无尿素放料阀变量)
							if (itemName.startsWith(Constant.FEN_LIAO_1_ZHONG_LIANG_SHE_DING)){
								varName=ERecord.JFLTXSSYFL1ZLSD;//粉料1重量设定
							}
							else if (itemName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)){
								varName=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;//加粉料PH输入值
							}
						}
						else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {//加粉料提醒(有尿素放料阀变量)
							if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
								if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
									if(tv2VarValue==TriggerVar.UP) {
										varName=ERecord.FNSFLFSSYFCZ;//釜尿素放料阀上升沿釜称重
									}
									else {
										varName=ERecord.FNSFLFXJYFCZ;//釜尿素放料阀下降沿釜称重
									}
								}
								else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
									if(tv2VarValue==TriggerVar.DOWN) {
										varName=ERecord.FNSFLFXJYFYFWD;//釜尿素放料阀下降沿反应釜温度
									}
								}
							}
						}
						else if(tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始
							if (itemName.startsWith(Constant.ZHENG_QI_YA_LI)){
								varName=Constant.ZHENG_QI_YA_LI;//蒸汽压力
							}
						}
						else if(tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.SWWCSSYFYFWD;//升温完成上升沿反应釜温度
							}
						}
						else if(tv1VarName.startsWith(Constant.WEN_DU_98_PH_HE_GE+Constant.XHX)){//温度98PH合格
							if (itemName.startsWith(Constant.WEN_DU_98_PH)){
								varName=Constant.WEN_DU_98_PH;//温度98PH
							}
						}
						else if(tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.XHX)){//第一次保温启动
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.DYCBWQDSSYFYFWD;//第一次保温启动反应釜温度
							}
						}
						else if(tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.XHX)){//第一次保温合格
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.DYCBWHGSSYFYFWD;//第一次保温合格反应釜温度
							}
						}
						else if(tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XHX)){//一次降温加酸提醒
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.YCJWJSTXSSYFYFWD;//一次降温加酸提醒反应釜温度
							}
							else if (itemName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU)){
								varName=Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XIA_JIANG_YAN+Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU;//一次降温加酸提醒下降沿一次降温加酸PH输入
							}
						}
						else if(tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.XHX)){//一次降温加酸合格
							if (itemName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_LIANG)){
								varName=ERecord.YCJWJSL;//一次降温加酸量
							}
							else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.YCJWJSHGSSYFYFWD;//一次降温加酸合格反应釜温度
							}
							else if (itemName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU)){
								varName=Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.SHANG_SHENG_YAN+ERecord.YCJWJSHGSSYYCJWJSPHSR;//一次降温加酸合格上升沿一次降温加酸PH输入
							}
						}
						else if(tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+Constant.XHX)){//测量冰水雾点提醒
							if (itemName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI)){
								varName=Constant.CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI;//测量冰水雾点输入值
							}
							else if (itemName.startsWith(Constant.BAO_WEN_FEN_ZHONG_JI_SHI)){
								varName=Constant.BAO_WEN_FEN_ZHONG_JI_SHI;//保温分钟计时
							}
							else if (itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ)){
								varName=Constant.CE_20_WU_DIAN_SRZ;//测20雾点输入值
							}
						}
						else if(tv1VarName.startsWith(Constant.JIA_JIAN_PH_HE_GE+Constant.XHX)){//加碱PH合格
							if (itemName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)){
								varName=Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN;//加碱量范围下限
							}
							else if (itemName.startsWith(Constant.JIA_JIAN_PH_SHU_RU)){
								varName=Constant.JIA_JIAN_PH_SHU_RU;//加碱PH输入
							}
						}
						else if(tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)){//二次投粉
							if (itemName.startsWith(Constant.ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING)){
								varName=Constant.ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING;//二次加料粉料1重量设定
							}else if(itemName.startsWith(Constant.BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING)){
								varName=Constant.BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING;//保温后加助剂6量设定
							}else if(itemName.startsWith(Constant.BAO_WEN_HOU_JIA_SHUI_LIANG_SHE_DING)){
								varName=Constant.BAO_WEN_HOU_JIA_SHUI_LIANG_SHE_DING;//保温后加水量设定
							}
						}
						else if(tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)&&tv2VarName!=null) {
							if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
								if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
									if(tv2VarValue==TriggerVar.UP) {
										varName=Constant.ER_CI_TOU_FEN+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFSSYFCZ;//釜尿素放料阀上升沿釜称重
									}
									else {
										varName=Constant.ER_CI_TOU_FEN+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFXJYFCZ;//釜尿素放料阀下降沿釜称重
									}
								}
								else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
									if(tv2VarValue==TriggerVar.DOWN) {
										varName=ERecord.FNSFLFXJYFYFWD;//釜尿素放料阀下降沿反应釜温度
									}
								}
							}
						}
						else if(tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING+Constant.XHX)){//二次加水和小料提醒
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.ECJXLHSTXXJYFYHWD;//二次加水和小料提醒下降沿反应釜温度
							}
						}
						else if(tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
							if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
								varName=ERecord.FYJSSSYFYFWD;//反应结束上升沿反应釜温度
							}
						}
						else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null){//允许开始排胶
			            	if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)) {
			            		varName=ERecord.YXKSPJSSYFCZ;
			            	}
			                else if(itemName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)&&itemName.endsWith(Constant.XHX+Constant.AV)){
				            	if(itemName.equals("胶罐选择1_AV"))
				            		value=(float)1;
				            	
			                	if(value==TriggerVar.UP) {
					        		int lastXhxLoc = tv1VarName.lastIndexOf(Constant.XHX)+Constant.XHX.length();
					        		String rujgFlagStr = tv1VarName.substring(lastXhxLoc, tv1VarName.length());
					        		System.out.println("rujgFlagStr="+rujgFlagStr);
					        		int rujgFlag = Integer.valueOf(rujgFlagStr);
					        		switch (rujgFlag) {
									case Constant.BSF_JG1:
						        		varName=ERecord.DRJG1JGH;
										break;
									case Constant.BSF_JG2:
						        		varName=ERecord.DRJG2JGH;
										break;
									}
					        		
					        		String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;
				                	int jghStart=itemName.indexOf(jgxzPvVarNameQz)+jgxzPvVarNameQz.length();
				                	int jghEnd=itemName.indexOf(Constant.XHX+Constant.AV);
				                	Integer jgh = Integer.valueOf(itemName.substring(jghStart,jghEnd));
				                	value=(float)jgh;
			                	}
			                }
						}
				        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName!=null) {//允许开始排胶(有胶罐选择变量)
				        	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
				        		int lastXhxLoc = tv1VarName.lastIndexOf(Constant.XHX)+1;
				        		String rujgFlagStr = tv1VarName.substring(lastXhxLoc, tv1VarName.length());
				        		System.out.println("rujgFlagStr="+rujgFlagStr);
				        		int rujgFlag = Integer.valueOf(rujgFlagStr);
				        		switch (rujgFlag) {
								case Constant.BSF_JG1:
					        		varName=ERecord.YXKSPJSSYJG1ZL;
									break;
								case Constant.BSF_JG2:
					        		varName=ERecord.YXKSPJSSYJG2ZL;
									break;
								}
				        	}
				        }
						else if(tv1VarName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING+Constant.XHX)){//终检水PH提醒
							if (itemName.startsWith(Constant.ZHONG_JIAN_SHUI_SHU)){
								varName=Constant.ZHONG_JIAN_SHUI_SHU;//终检水数
							}else if (itemName.startsWith(Constant.ZHONG_JIAN_PH)){
								varName=Constant.ZHONG_JIAN_PH;//终检PH
							}
						}
						else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null){//排胶完成
							if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)){
								varName=ERecord.PJWCSSYFCZ;//排胶完成上升沿釜称重
							}
						}
			            else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName!=null) {//排胶完成(有胶罐选择变量)
			            	System.out.println("tv2VarName="+tv2VarName);
			            	if(tv2VarName.startsWith(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
				        		varName=ERecord.PJWCSSYJG1ZL;
				        	}
			            }
			        	
			        	if(StringUtils.isEmpty(varName))
			        		continue;
			        	
			        	String unit=null;//单位
			        	if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)||
				           itemName.startsWith(Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG)) {
			        		unit=Constant.KG;//kg
			        	}
			        	else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
			        	    unit=Constant.WEN_DU_DAN_WEI_SIGN;//°C
			            }
			        	else if (itemName.startsWith(Constant.ZHENG_QI_YA_LI)){
			        	    unit=Constant.MPA;//MPa
			            }
			
			            int paraType=0;//参数类型
			        	if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)||
			                itemName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)||
			                itemName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)||
			                itemName.startsWith(Constant.ZHENG_QI_YA_LI)||
			                itemName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU)||
			                itemName.startsWith(Constant.WEN_DU_98_PH)||
			                itemName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING)||
			                itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ)||
			                itemName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)){
			                paraType=ProcessVar.GYCS;
			            }
			        	else if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)||
			                    itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)||
			                    itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||
			                    itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)){
			        	    paraType=ProcessVar.YLCS;
			            }
			        	
			        	proVar=new ProcessVar();
			        	proVar.setVarName(varName);
			        	proVar.setVarValue(value);
			        	proVar.setDealBz(ProcessVar.WCL);
			        	proVar.setUpdateTime(sysTime);
			        	proVar.setFId(triggerVar1.getFId());
			        	proVar.setRecType(triggerVar1.getRecType());
			        	proVar.setUnit(unit);
			        	proVar.setParaType(paraType);
			        	proVarList.add(proVar);
			            System.out.println("Item名:" + itemName + "  Item值: " + value);
	        		}
	        	}
	        }
        

	        //以下是报表里所需的系统时间，opc上没有这些变量，就得根据服务器的系统时间获取，再存入集合里
	        String itemName = null;
	        if(TriggerVar.M.equals(tvRecType)) {	        
		        if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI+Constant.XHX)) {//备料开始
		        	itemName = ERecord.BLKSSSYSJ;//备料开始上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)) {//甲醛备料开始
		        	itemName = ERecord.JQBLKSSSYSJ;//甲醛备料开始上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) { //甲醛放料完成
		        	itemName = ERecord.JQFLWCSSYSJ;//甲醛放料完成上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.XHX)) {//允许一次加助剂
		        	itemName = ERecord.YXYCJZJSSYSJ;//允许一次加助剂上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.XHX)){//所有助剂加料完成1
		        	itemName = ERecord.SYZJJLWC1SSYSJ;//所有助剂加料完成1上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {
		        	if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
		    			if(tv2VarValue==TriggerVar.UP) {
		    				itemName=ERecord.FNSFLFSSYSJ;//釜尿素放料阀上升沿时间
		    			}
		    			else {
		    				itemName=ERecord.FNSFLFXJYSJ;//釜尿素放料阀下降沿时间
		    			}
		    		}
		        }
		        else if(tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始
		        	itemName = ERecord.SWKSSSYSJ;//升温开始上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.XHX)){//温度85与二次投料提醒
		        	itemName = ERecord.WD85YECTLTXSSYSJ;//温度85与二次投料提醒上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.XHX)){//所有助剂加料完成2
		            itemName = ERecord.SYZJJLWC2SSYSJ;//所有助剂加料完成2上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.XHX)){//允许二次加助剂
		            itemName = ERecord.YXECJZJSSYSJ;//允许二次加助剂上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成
		            itemName = ERecord.SWWCSSYSJ;//升温完成上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.XHX)){//聚合终点
		            itemName = ERecord.JHZDSSYSJ;//聚合终点上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX)){//降温完成
		            itemName = ERecord.JWWCSSYSJ;//降温完成上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null) {//允许开始排胶
		        	itemName = ERecord.YXKSPJSSYSJ;//允许开始排胶上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null) {//排胶完成
		        	itemName = ERecord.PJWCSSYSJ;//排胶完成上升沿时间
		        }
		        else if (tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
		            itemName = ERecord.FYJSSSYSJ;//反应结束上升沿时间
		        }
		        
        
		        //触发器变量1在满足以上几种情况时，说明需要添加系统时间，就调用下面这个逻辑。若加在上面代码量太多，就简化一下加在下面
		        if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null&&itemName.contains(Constant.SHI_JIAN)||
				   tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.XHX)||
		           tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.XHX)||
		           tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.XHX)||
		           tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)||
		           tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.XHX)||
		           tv1VarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null||
				   tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null||
		           tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)) {
		        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
		        	
		        	proVar=new ProcessVar();
		        	proVar.setVarName(itemName);
		        	proVar.setDealBz(ProcessVar.WCL);
		        	proVar.setUpdateTime(sysTime);
		        	proVar.setFId(triggerVar1.getFId());
		        	proVar.setRecType(triggerVar1.getRecType());
		        	
		        	proVarList.add(proVar);
		        }
	        }
	        else if(TriggerVar.U.equals(tvRecType)) {        
		        if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI+Constant.XHX)) {//备料开始
		        	itemName = ERecord.BLKSSSYSJ;//备料开始上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)) {//甲醛备料开始
		        	itemName = ERecord.JQBLKSSSYSJ;//甲醛备料开始上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) { //甲醛放料完成
		        	itemName = ERecord.JQFLWCSSYSJ;//甲醛放料完成上升沿时间
		        }
		        else if(tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)) { //加碱PH值正常
		        	itemName = ERecord.JJPHZZCSSYSJ;//加碱PH值正常上升沿时间
		        }
				else if(tv1VarName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)) { //助剂6一次添加完成
					itemName = ERecord.ZJLYCTJWCSSYSJ;//助剂6一次添加完成上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG+Constant.XHX)) { //助剂6二次备料完成
					itemName = ERecord.ZJLECBLWCSSYSJ;//助剂6二次备料完成上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)) { //助剂6二次添加完成
					itemName = ERecord.ZJLECTJWCSSYSJ;//助剂6二次添加完成上升沿时间
				}
		        else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {
		        	if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
		    			if(tv2VarValue==TriggerVar.UP) {
		    				itemName=ERecord.FNSFLFSSYSJ;//釜尿素放料阀上升沿时间
		    			}
		    			else {
		    				itemName=ERecord.FNSFLFXJYSJ;//釜尿素放料阀下降沿时间
		    			}
		    		}
		        }
				else if(tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)) { //升温开始
					itemName = ERecord.SWKSSSYSJ;//升温开始上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)) { //升温完成
					itemName = ERecord.SWWCSSYSJ;//升温完成上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.XHX)) { //第一次保温启动
					itemName = ERecord.DYCBWQDSSYSJ;//第一次保温启动上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.XHX)) { //第一次保温合格
					itemName = ERecord.DYCBWHGSSYSJ;//第一次保温合格上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XHX)) { //一次降温加酸提醒
					Float tvVarValue = triggerVar1.getVarValue();
					if(TriggerVar.UP==tvVarValue)
						itemName = ERecord.YCJWJSTXSSYSJ;//一次降温加酸提醒上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)&&tv2VarName==null) {
					itemName = ERecord.ECTFSSYSJ;//二次投粉上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)&&tv2VarName!=null) {//二次投粉
					if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
						if(tv2VarValue==TriggerVar.UP) {
							itemName=Constant.ER_CI_TOU_FEN+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFSSYSJ;//釜尿素放料阀上升沿时间
						}
						else {
							itemName=Constant.ER_CI_TOU_FEN+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFXJYSJ;//釜尿素放料阀下降沿时间
						}
					}
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_JIA_215_QI_DONG+Constant.XHX)) { //二次加215启动
					itemName = ERecord.ECJ215QDSSYSJ;//二次加215启动上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG+Constant.XHX)) { //二次加215完成
					itemName = ERecord.ECJ215WCSSYSJ;//二次加215完成上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG+Constant.XHX)) { //二次加水启动
					itemName = ERecord.ECJSQDSSYSJ;//二次加水启动上升沿时间
				}
				else if(tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG+Constant.XHX)) { //二次加水完成
					itemName = ERecord.ECJSWCSSYSJ;//二次加水完成上升沿时间
				}
		        else if (tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
		            itemName = ERecord.FYJSSSYSJ;//反应结束上升沿时间
		        }
				else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null){//允许开始排胶
					itemName = ERecord.YXKSPJSSYSJ;//允许开始排胶上升沿时间
				}
				else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null){//排胶完成
					itemName = ERecord.PJWCSSYSJ;//排胶完成上升沿时间
				}
		        
		        if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null&&itemName.contains(Constant.SHI_JIAN)||
				   tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)||
				   tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.XHX)||
				   tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XHX)&&(TriggerVar.UP==triggerVar1.getVarValue())||
				   tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)&&tv2VarName==null&&itemName.contains(Constant.SHI_JIAN)||
				   tv1VarName.startsWith(Constant.ER_CI_TOU_FEN+Constant.XHX)&&tv2VarName!=null&&itemName.contains(Constant.SHI_JIAN)||
				   tv1VarName.startsWith(Constant.ER_CI_JIA_215_QI_DONG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG+Constant.XHX)||
				   tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)||
				   tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)&&tv2VarName==null||
				   tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)&&tv2VarName==null) {
		        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
		        	
		        	proVar=new ProcessVar();
		        	proVar.setVarName(itemName);
		        	proVar.setDealBz(ProcessVar.WCL);
		        	proVar.setUpdateTime(sysTime);
		        	proVar.setFId(triggerVar1.getFId());
		        	proVar.setRecType(triggerVar1.getRecType());
		        	
		        	proVarList.add(proVar);
		        }
	        }
        
	        
	        if(proVarList.size()>0) {
		        json.put("status", "ok");
		        json.put("proVarList", proVarList);
	        }
	        else {
	        	json.put("status", "no");
	        	json.put("message", "没有找到变量");
	        }
        
		} catch (Exception e) {
			// TODO: handle exception
	        json.put("status", "no");
        	json.put("message", "异常");
        	e.printStackTrace();
		}
    	finally {
            return json;
		}
        
    }
    
    /**
     * 根据变量名从过程变量集合里查找变量值
     * @param processVarList
     * @param varName
     * @return
     */
    public static Float getVarValueFromPVListByName(List<ProcessVar> processVarList,String varName) {
    	Float varValue=null;
    	for (ProcessVar processVar : processVarList) {
    		String pvVarName = processVar.getVarName();
    		if(pvVarName.equals(varName)) {
    			varValue = processVar.getVarValue();
    			break;
    		}
		}
    	return varValue;
	}
    
    /**
     * 根据变量名往变量集合里添加待查询的变量
     * @param opcVarNameList
     * @param varName
     */
    public static void addVarNameInList(List<String> opcVarNameList, String varName) {
		// TODO Auto-generated method stub
		if(varName.equals(Constant.JIAO_GUAN+Constant.XUAN_ZE)) {
            for (int jgId : Constant.BSF_JG_ARR) {
	            String jgxzPvVarName=varName+jgId+Constant.XHX+Constant.AV;
	            opcVarNameList.add(jgxzPvVarName);
            }
		}
		else if(varName.startsWith(Constant.JIAO_GUAN_CBZ)&&varName.endsWith(Constant.CHENG_ZHONG)) {
            String zhjgPvVarNameQz=Constant.ZHONG_ZHUAN+Constant.JIAO_GUAN;
            
        	int jghStart=varName.indexOf(Constant.JIAO_GUAN_CBZ)+2;
        	int jghEnd=varName.indexOf(Constant.CHENG_ZHONG);
        	int jgh = Float.valueOf(varName.substring(jghStart,jghEnd)).intValue();
        	
        	String jgcbzPvVarName=null;
        	switch (jgh) {
			case Constant.BSF_JG1:
			case Constant.BSF_JG2:
			case Constant.BSF_JG3:
			case Constant.BSF_JG4:
			case Constant.BSF_JG5:
			case Constant.BSF_JG6:
			case Constant.BSF_JG7:
			case Constant.BSF_JG8:
			case Constant.BSF_JG9:
				jgcbzPvVarName=varName+Constant.XHX+Constant.AV;
				opcVarNameList.add(jgcbzPvVarName);
				break;
			case Constant.BSF_JG10:
				opcVarNameList.add(zhjgPvVarNameQz+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
				break;
			}
		}
	}
    
    /**
     * 根据变量名从过程变量集合里获取过程变量
     * @param varName
     * @param proVarList
     * @return
     */
    public static ProcessVar getProVarInListByVarName(String varName, List<ProcessVar> proVarList) {
    	ProcessVar proVar=null;
		for (ProcessVar proVarItem : proVarList) {
			if(proVarItem.getVarName().equals(varName)) {
				proVar=proVarItem;
				break;
			}
		}
		return proVar;
	}
    
    /**
     * 根据釜id和胶类型获取釜名
     * @param fId
     * @param recType
     * @return
     */
    public static String getFNameByFIdRecType(Integer fId,String recType) {
    	String fName=null;
    	switch (fId) {
		case Constant.F1_ID:
			if(TriggerVar.M.equals(recType))
				fName=Constant.BSF_F1;
			else if(TriggerVar.U.equals(recType))
				fName=Constant.BSF_F1U;
			break;
		case Constant.F2_ID:
			if(TriggerVar.M.equals(recType))
				fName=Constant.BSF_F2;
			else if(TriggerVar.U.equals(recType))
				fName=Constant.BSF_F2U;
			break;
		case Constant.F3_ID:
			if(TriggerVar.M.equals(recType))
				fName=Constant.BSF_F3;
			else if(TriggerVar.U.equals(recType))
				fName=Constant.BSF_F3U;
			break;
		case Constant.F4_ID:
			if(TriggerVar.M.equals(recType))
				fName=Constant.BSF_F4;
			else if(TriggerVar.U.equals(recType))
				fName=Constant.BSF_F4U;
			break;
		case Constant.F5_ID:
			if(TriggerVar.M.equals(recType))
				fName=Constant.BSF_F5;
			else if(TriggerVar.U.equals(recType))
				fName=Constant.BSF_F5U;
			break;
		}
    	return fName;
	}
    
    private static ArrayList<OpcItem> getOpcItemTestList(List<String> varNameList){
        ArrayList<OpcItem> opcItemList =null;
        try {
            List<OpcVarTest> opcVarTestList = null;
            Map<String, Object> bodyParamMap=new HashMap<String, Object>();
            String varNames="";
            for (String varName : varNameList) {
                varNames+=","+varName;
            }
            varNames=varNames.substring(1);
            bodyParamMap.put("varNames", varNames);
            JSONObject resultJO = APIUtil.doHttp("getOVTListByVarNames", bodyParamMap);
            String status = resultJO.get("status").toString();
            if("ok".equals(status)) {
                String opcVarTestListStr = resultJO.get("opcVarTestList").toString();
                opcVarTestList = JSON.parseArray(opcVarTestListStr, OpcVarTest.class);
            }
            opcItemList = convertOVTToOIList(opcVarTestList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return opcItemList;
        }
    }
    
    /**
     * 把数据库测试用变量转换为opc变量格式
     * @param opcVarTestList
     * @return
     */
    private static ArrayList<OpcItem> convertOVTToOIList(List<OpcVarTest> opcVarTestList) {
    	ArrayList<OpcItem> opcItemList=new ArrayList<OpcItem>();
    	OpcItem opcItem=null;
    	for (OpcVarTest opcVarTest : opcVarTestList) {
    		String varName = opcVarTest.getVarName();
    		Float varValue = opcVarTest.getVarValue();
    		
    		opcItem=new OpcItem(varName,false,"");
    		opcItem.setValue(new Variant(varValue));
    		
    		opcItemList.add(opcItem);
		}
    	return opcItemList;
	}
    
    /**
     * 获取待从opc上读取的触发变量列表
     * @return
     */
    public static List<String> getOpcTVNameList() {
    	List<String> opcTVNameList=new ArrayList<String>();
    	/*
    	for(int i=0;i<3;i++) {
    		opcTVNameList.add("_System._DateTime");
    	}
    	*/

    	List<String> opcTVNamePreMList=new ArrayList<String>();//前缀集合
    	opcTVNamePreMList.add(Constant.BEI_LIAO_KAI_SHI);//备料开始前缀
    	opcTVNamePreMList.add(Constant.FAN_YING_JIE_SHU);//反应结束
    	opcTVNamePreMList.add(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI);//甲醛备料开始
        opcTVNamePreMList.add(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG);//甲醛放料完成
        opcTVNamePreMList.add(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG);//加碱PH值正常
        opcTVNamePreMList.add(Constant.YUN_XU_YI_CI_JIA_ZHU_JI);//允许一次加助剂
        opcTVNamePreMList.add(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1);//所有助剂加料完成1
        opcTVNamePreMList.add(Constant.JIA_FEN_LIAO_TI_XING);//加粉料提醒
        opcTVNamePreMList.add(Constant.JIA_FEN_LIAO_PH_HE_GE);//加粉料PH合格
        opcTVNamePreMList.add(Constant.SHENG_WEN_KAI_SHI);//升温开始
        opcTVNamePreMList.add(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING);//温度85与二次投料提醒
    	opcTVNamePreMList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING);//二次助剂后测PH提醒----这个变量F2没有
        opcTVNamePreMList.add(Constant.YUN_XU_ER_CI_JIA_ZHU_JI);//允许二次加助剂
        opcTVNamePreMList.add(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2);//所有助剂加料完成2
        opcTVNamePreMList.add(Constant.SHENG_WEN_WAN_CHENG);//升温完成
        opcTVNamePreMList.add(Constant.WEN_DU_98_PH+Constant.HE_GE);//温度98PH合格
        opcTVNamePreMList.add(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING);//测量冰水雾点提醒
        opcTVNamePreMList.add(Constant.CE_SHUI_SHU_TI_XING);//测水数提醒----这个只有F2有值
        opcTVNamePreMList.add("停热降温水数提醒");
        opcTVNamePreMList.add(Constant.JU_HE_ZHONG_DIAN);//聚合终点----这个F5没值,因为5号的生产流程还没有做，报表可以先不做5号的
        opcTVNamePreMList.add(Constant.JIANG_WEN_WAN_CHENG);//降温完成----这个变量F5没有
        opcTVNamePreMList.add(Constant.ZHONG_JIAN_SHUI_PH_TI_XING);//终检水PH提醒
        opcTVNamePreMList.add(Constant.YUN_XU_KAI_SHI_PAI_JIAO);//允许开始排胶
        opcTVNamePreMList.add(Constant.PAI_JIAO_WAN_CHENG);//排胶完成---F5没有
        

    	for (String opcTVNamePreM : opcTVNamePreMList) {//循环拼接上反应釜号作为完整的变量
    		for (String fMName : Constant.BSF_F_M_ARR) {
    			String opcTVName = null;
    			if(Constant.FAN_YING_JIE_SHU.equals(opcTVNamePreM))
    				opcTVName = opcTVNamePreM+fMName+Constant.XHX+Constant.AV;
    			else
    				opcTVName = opcTVNamePreM+Constant.XHX+fMName+Constant.XHX+Constant.AV;
    			
    			opcTVNameList.add(opcTVName);
			}
		}
    	/*
        */
    	
    	/*
    	List<String> opcTVNamePhPreMList=new ArrayList<String>();

    	for (String opcTVNamePhPreM : opcTVNamePhPreMList) {//循环拼接上反应釜号作为完整的变量
    		for (String fMName : Constant.BSF_PF_M_ARR) {
    			String opcTVName = opcTVNamePhPreM+Constant.XHX+fMName+Constant.XHX+Constant.AV;
    			opcTVNameList.add(opcTVName);
			}
    		
		}
		*/
    	

    	List<String> opcTVNamePreUList=new ArrayList<String>();//前缀集合
    	opcTVNamePreUList.add(Constant.FAN_YING_JIE_SHU);//反应结束
    	opcTVNamePreUList.add(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI);//甲醛备料开始
    	opcTVNamePreUList.add(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG);//甲醛放料完成
    	opcTVNamePreUList.add(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG);//加碱PH值正常
    	opcTVNamePreUList.add(Constant.KAI_SHI_JIA_LIAO);//开始加料
    	opcTVNamePreUList.add(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG);//助剂6一次添加完成
    	opcTVNamePreUList.add(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG);//助剂6二次备料完成
    	opcTVNamePreUList.add(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG);//助剂6二次添加完成
    	opcTVNamePreUList.add(Constant.JIA_FEN_LIAO_TI_XING);//加粉料提醒
    	opcTVNamePreUList.add(Constant.SHENG_WEN_KAI_SHI);//升温开始
    	opcTVNamePreUList.add(Constant.SHENG_WEN_WAN_CHENG);//升温完成
    	opcTVNamePreUList.add(Constant.WEN_DU_98_PH+Constant.HE_GE);//温度98PH合格
    	opcTVNamePreUList.add(Constant.DI_YI_CI_BAO_WEN_QI_DONG);//第一次保温启动
    	opcTVNamePreUList.add(Constant.DI_YI_CI_BAO_WEN_HE_GE);//第一次保温合格
    	opcTVNamePreUList.add(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING);//一次降温加酸提醒
    	opcTVNamePreUList.add(Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE);//一次降温加酸合格
    	opcTVNamePreUList.add(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING);//测量冰水雾点提醒
    	opcTVNamePreUList.add(Constant.JIA_JIAN_PH_HE_GE);//加碱PH合格
    	opcTVNamePreUList.add(Constant.ER_CI_TOU_FEN);//二次投粉
    	opcTVNamePreUList.add(Constant.ER_CI_JIA_215_QI_DONG);//二次加215启动
    	opcTVNamePreUList.add(Constant.ER_CI_JIA_215_WAN_CHENG);//二次加215完成
    	opcTVNamePreUList.add(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING);//二次加水和小料提醒
    	opcTVNamePreUList.add(Constant.ER_CI_JIA_SHUI_QI_DONG);//二次加水启动
    	opcTVNamePreUList.add(Constant.ER_CI_JIA_SHUI_WAN_CHENG);//二次加水完成
        opcTVNamePreUList.add(Constant.ZHONG_JIAN_SHUI_PH_TI_XING);//终检水PH提醒
        
        for (String opcTVNamePreU : opcTVNamePreUList) {
    		for (String fUName : Constant.BSF_F_U_ARR) {
    			String opcTVName = opcTVNamePreU+Constant.XHX+fUName+Constant.XHX+Constant.AV;
				if(Constant.FAN_YING_JIE_SHU.equals(opcTVNamePreU))
					opcTVName = opcTVNamePreU+fUName+Constant.XHX+Constant.AV;
				else
					opcTVName = opcTVNamePreU+Constant.XHX+fUName+Constant.XHX+Constant.AV;
			
    			opcTVNameList.add(opcTVName);
			}
        }

    	List<String> opcTVNamePhPreUList=new ArrayList<String>();
    	for (String opcTVNamePhPreU : opcTVNamePhPreUList) {
    		for (String fUName : Constant.BSF_PF_U_ARR) {
    			String opcTVName = opcTVNamePhPreU+Constant.XHX+fUName+Constant.XHX+Constant.AV;
    			opcTVNameList.add(opcTVName);
			}
    	}
    	/*
    	*/
    	
    	opcTVNameList.add("红色报警消音_AV");
		//System.out.println("opcTVNameList==="+opcTVNameList.toString());
    	
    	return opcTVNameList;
	}

	/**
	 * 获取待从opc上读取的过程变量名集合
	 * @return
	 */
	public static List<String> getOpcPVNameList() {
		// TODO Auto-generated method stub
    	List<String> opcPVNameList=new ArrayList<String>();
    	
    	
		for (int fId : Constant.F_ID_ARR) {
			opcPVNameList.add(Constant.FAN_YING_FU+fId+Constant.WEN_DU+Constant.XHX+Constant.AV);//反应釜温度
			opcPVNameList.add(Constant.FU+fId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);//釜称重
	        opcPVNameList.add(Constant.FAN_YING_FU+fId+Constant.JIAO_ZHONG_LEI_XING+Constant.XHX+Constant.AV);//反应釜胶种类型
		}
		
		for (String pFM : Constant.BSF_PF_M_ARR) {
			opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+pFM+Constant.XHX+Constant.AV);//加碱量提示
			opcPVNameList.add(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN+Constant.XHX+pFM+Constant.XHX+Constant.AV);//加碱量范围下限
			opcPVNameList.add(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX+pFM+Constant.XHX+Constant.AV);//粉料重量设定
		}
		
		opcPVNameList.add(Constant.ZHENG_QI_YA_LI+Constant.XHX+Constant.AV);//蒸汽压力
		
    	List<String> opcPVNamePreList=new ArrayList<String>();//前缀集合

    	opcPVNamePreList.add(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG);//甲醛实际进料重量前缀
    	opcPVNamePreList.add(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG);//加水实际重量
    	opcPVNamePreList.add(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI);//加碱前PH输入值
    	opcPVNamePreList.add(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI);//加碱后PH输入值

    	for (int zjjlgId : Constant.BSF_ZJJLG_1_2_M_ARR) {//助剂计量罐1-2
    		opcPVNameList.add(Constant.ZHU_JI_JI_LIANG_GUAN+zjjlgId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
		}

    	for (int zjjlgId : Constant.BSF_ZJJLG_3_5_M_ARR) {//助剂计量罐3-5
    		opcPVNameList.add(Constant.ZHU_JI_JI_LIANG_GUAN+zjjlgId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
		}
    	
    	for (int jgId : Constant.BSF_JG_ARR) {
    		switch (jgId) {
			case Constant.BSF_JG1:
			case Constant.BSF_JG2:
			case Constant.BSF_JG3:
			case Constant.BSF_JG4:
			case Constant.BSF_JG5:
			case Constant.BSF_JG6:
			case Constant.BSF_JG7:
			case Constant.BSF_JG8:
			case Constant.BSF_JG9:
	    		opcPVNameList.add(Constant.JIAO_GUAN_CBZ+jgId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
				break;
			case Constant.BSF_JG10:
	    		opcPVNameList.add(Constant.ZHONG_ZHUAN+Constant.JIAO_GUAN+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
				break;
			}
    	}
    	
		opcPVNameList.add(Constant.ZHONG_ZHUAN+Constant.JIAO_GUAN+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);

    	opcPVNamePreList.add(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI);//加粉料PH输入值
    	opcPVNamePreList.add(Constant.ER_CI_TOU_LIAO_PH_SHU_RU);//二次投料PH输入
    	opcPVNamePreList.add(Constant.WEN_DU_98_PH);//温度98PH
    	opcPVNamePreList.add(Constant.CE_LIANG_BSWD_SRZ);//测量冰水雾点输入值
    	opcPVNamePreList.add(Constant.CE_20_WU_DIAN_SRZ);//测20雾点输入值
    	opcPVNamePreList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ);//停热降温水数输入值-----这个缺少F2变量
    	
    	for (String opcPVNamePre : opcPVNamePreList) {//循环拼接上反应釜号作为完整的变量
    		for (String fMName : Constant.BSF_F_M_ARR) {
    			String opcPVName = opcPVNamePre+Constant.XHX+fMName+Constant.XHX+Constant.AV;
    			opcPVNameList.add(opcPVName);
			}
    		for (String fUName : Constant.BSF_F_U_ARR) {
    			String opcPVName = opcPVNamePre+Constant.XHX+fUName+Constant.XHX+Constant.AV;
    			opcPVNameList.add(opcPVName);
			}
    	}
        
        String jgxzPvVarNameQz=Constant.JIAO_GUAN+Constant.XUAN_ZE;
        String jgcbzPvVarNameQz=Constant.JIAO_GUAN_CBZ;
        String zhjgPvVarNameQz=Constant.ZHONG_ZHUAN+Constant.JIAO_GUAN;
        for (int jgId : Constant.BSF_JG_ARR) {
        	String jgxzPvVarName=jgxzPvVarNameQz+jgId+Constant.XHX+Constant.AV;
			opcPVNameList.add(jgxzPvVarName);

        	String jgcbzPvVarName=null;
        	switch (jgId) {
			case Constant.BSF_JG1:
			case Constant.BSF_JG2:
			case Constant.BSF_JG3:
			case Constant.BSF_JG4:
			case Constant.BSF_JG5:
			case Constant.BSF_JG6:
			case Constant.BSF_JG7:
			case Constant.BSF_JG8:
			case Constant.BSF_JG9:
				jgcbzPvVarName=jgcbzPvVarNameQz+jgId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV;
	    		opcPVNameList.add(jgcbzPvVarName);
				break;
			case Constant.BSF_JG10:
	    		opcPVNameList.add(zhjgPvVarNameQz+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
				break;
			}
		}
        
		return opcPVNameList;
	}
	
	/**
	 * 初始化opc服务器端触发器变量(为了判断变量是否存在与opc服务器上，只能遍历变量集合，逐一添加到组里,包括触发器变量和过程变量.若opc端不存在某个变量,就用模拟变量代替)
	 * @param opcTVNameList
	 */
	public static void initJOpcTV(List<String> opcTVNameList) {
        try {
        	imiOpcItemTVList=new ArrayList<OpcItem>();

			SynchReadItemExample test = new SynchReadItemExample();
	    	JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
	    	
        	jopcTV = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);
	        opcGroupTV = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);
	        
	        OpcItem opcItem=null;
			for (String opcTVName : opcTVNameList) {
				if(opcTVName.endsWith(TriggerVar.U+Constant.XHX+Constant.AV)) {
					if(
						opcTVName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)||//助剂6一次添加完成
						opcTVName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)||//加碱量范围下限
						opcTVName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)||//二次加215完成
						opcTVName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING)||//二次加水和小料提醒
						opcTVName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
						opcTVName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)//二次加水完成
					) {
						opcTVName=reverseVarSuffix(opcTVName);
					}
				}
				
				opcItem=new OpcItem(opcTVName, true, "");
				opcGroupTV.addItem(opcItem);
				if(jopcTV.getGroupsAsArray().length==0)//为了避免重复添加组，先判断下是否已经添加过组
					jopcTV.addGroup(opcGroupTV);   //添加组
				//System.out.println("aaa==="+jopcTV.getGroupsAsArray().length);

		        try {
		        	jopcTV.connect();   //连接
		        	jopcTV.registerGroups();  //注册组
		        } catch (ConnectivityException e1) {
		            System.out.println("ConnectivityException="+e1.getMessage());
		            //logger.error(e1.getMessage());
		        } catch (UnableAddGroupException e) {
		        	//System.out.println("GroupName===="+opcGroupTV.getGroupName());
		            System.out.println("UnableAddGroupException="+e.getMessage());
		            //logger.error(e.getMessage());
		        } catch (UnableAddItemException e) {
		            System.out.println("UnableAddItemException="+e.getMessage());//若组内不存在该变量，就会报这个异常
		            //logger.error(e.getMessage());
		            //ArrayList<OpcItem> its = opcGroupTV.getItems();
		            //System.out.println("its1==="+its.size());
		            opcGroupTV.removeItem(opcItem);//把这个不存在的变量从组内移除
		            //its = opcGroupTV.getItems();
		            //System.out.println("its2==="+its.size());
		            
		            jopcTV.removeGroup(opcGroupTV);//之前注册过该组，得移除该组
		            jopcTV.addGroup(opcGroupTV);//移除后重新添加该组
		            jopcTV.connect();   //重新连接
		            jopcTV.registerGroups();  //重新注册组
		            
		            OpcItem ImiOpcItem = getImiOpcItem(opcTVName);//把不存在的变量名生成模拟变量
		            imiOpcItemTVList.add(ImiOpcItem);//添加到模拟变量集合里
		        }
			}
			/*
	        synchronized(test) {
	            test.wait(50);
	        }
			OpcGroup responseGroup = jopcTV.synchReadGroup(opcGroupTV);
	        ArrayList<OpcItem> opcItems = responseGroup.getItems();
	        for (OpcItem opcItem1 : opcItems) {
		        String valueStr = opcItem1.getValue().toString();
		        System.out.println(opcItem1.getItemName()+",valueStr==="+valueStr);
			}
			*/
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据变量名集合，初始化jopc过程变量
	 * @param opcPVNameList
	 */
	public static void initJOpcPV(List<String> opcPVNameList) {
        try {
        	imiOpcItemPVList=new ArrayList<OpcItem>();

			SynchReadItemExample test = new SynchReadItemExample();
			JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc

			jopcPV = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);
			opcGroupPV = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);

			OpcItem opcItem=null;

			for (String opcPVName : opcPVNameList) {
				if(opcPVName.endsWith(TriggerVar.U+Constant.XHX+Constant.AV)) {
					if(
						opcPVName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)||//助剂6一次添加完成
						opcPVName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)||//加碱量范围下限
						opcPVName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)||//二次加215完成
						opcPVName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING)||//二次加水和小料提醒
						opcPVName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
						opcPVName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)//二次加水完成
					) {
						opcPVName=reverseVarSuffix(opcPVName);
					}
				}
				opcItem=new OpcItem(opcPVName, true, "");
				opcGroupPV.addItem(opcItem);
				if(jopcPV.getGroupsAsArray().length==0)
					jopcPV.addGroup(opcGroupPV);   //添加组
				//System.out.println("aaa==="+jopcPV.getGroupsAsArray().length);

				try {
					jopcPV.connect();   //连接
					jopcPV.registerGroups();  //注册组
				} catch (ConnectivityException e1) {
					System.out.println("ConnectivityException="+e1.getMessage());
					//logger.error(e1.getMessage());
				} catch (UnableAddGroupException e) {
					//System.out.println("GroupName===="+opcGroupTV.getGroupName());
					System.out.println("UnableAddGroupException="+e.getMessage());
					//logger.error(e.getMessage());
				} catch (UnableAddItemException e) {
					System.out.println("UnableAddItemException="+e.getMessage());
					//logger.error(e.getMessage());
					//ArrayList<OpcItem> its = opcGroupPV.getItems();
					//System.out.println("its1==="+its.size());
					opcGroupPV.removeItem(opcItem);
					//its = opcGroupPV.getItems();
					//System.out.println("its2==="+its.size());

					jopcPV.removeGroup(opcGroupPV);
					jopcPV.addGroup(opcGroupPV);
					jopcPV.connect();   //连接
					jopcPV.registerGroups();  //注册组

					OpcItem ImiOpcItem = getImiOpcItem(opcPVName);
					imiOpcItemPVList.add(ImiOpcItem);
				}
			}
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取opc服务器端触发器变量
	 * @return
	 */
	public static ArrayList<OpcItem> readJOpcTV() {
		ArrayList<OpcItem> opcItemList = new ArrayList<OpcItem>();
		
        try {
			synchronized(srieTV) {
				srieTV.wait(50);
			}
	        OpcGroup responseGroup = jopcTV.synchReadGroup(opcGroupTV);
	        ArrayList<OpcItem> opcItems = responseGroup.getItems();
	        for (OpcItem opcItem : opcItems) {
	        	String valueStr = opcItem.getValue().toString();
		        if(StringUtils.isEmpty(valueStr)) {
		        	opcItem.setValue(new Variant(0));
		        }
		        System.out.println("getItemName==="+opcItem.getItemName()+",getValue==="+valueStr);
				opcItemList.add(opcItem);
			}
	        
			opcItemList.addAll(imiOpcItemTVList);
			
			for (OpcItem opcItem : opcItemList) {
				String itemName = opcItem.getItemName();
				if(itemName.contains("FU")) {
					if(
						itemName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)||//助剂6一次添加完成
						itemName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)||//加碱量范围下限
						itemName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)||//二次加215完成
						itemName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING)||//二次加水和小料提醒
						itemName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
						itemName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)//二次加水完成
					) {
						itemName=reverseVarSuffix(itemName);
						opcItem.setItemName(itemName);
					}
				}
			}
        }catch (ComponentNotFoundException e) {
			//logger.error(e.getMessage()); //获取responseGroup错误
			JOpc.coUninitialize();     //错误关闭连接
			e.printStackTrace();
		}catch (InterruptedException e) {
			//logger.error(e.getMessage());
			JOpc.coUninitialize(); //错误关闭连接
			e.printStackTrace();
		}catch (SynchReadException e) {
			e.printStackTrace();
		}
        finally {
        	return opcItemList;
		}
	}
	
	/**
	 * 从已经初始化好的jopcmap里读取变量
	 * @param opcPVNameList
	 * @return
	 */
	public static ArrayList<OpcItem> readJOpcPV(List<String> opcPVNameList) {
		ArrayList<OpcItem> opcItemList = new ArrayList<OpcItem>();
        try {
			synchronized(sriePV) {
				sriePV.wait(50);
			}
			
	        OpcGroup responseGroup = jopcPV.synchReadGroup(opcGroupPV);
	        ArrayList<OpcItem> opcItems = responseGroup.getItems();
        	
        	for (String opcPVName : opcPVNameList) {//遍历变量名集合，判断变量是否存在
            	boolean exist=false;
            	
            	for (OpcItem opcItem : opcItems) {
    	        	String itemName = opcItem.getItemName();
    	        	if(itemName.equals(opcPVName)) {
    	        		String valueStr = opcItem.getValue().toString();
        		        if(StringUtils.isEmpty(valueStr)) {
        		        	opcItem.setValue(new Variant(0));
        		        }
        		        System.out.println("getItemName==="+itemName+",getValue==="+valueStr);
        				opcItemList.add(opcItem);
        				
        				exist=true;
        				break;
    	        	}
    			}
            	
        		if(!exist) {//不存在则从模拟变量集合里获取
        			for (OpcItem imiOpcItemPV : imiOpcItemPVList) {
						if(imiOpcItemPV.getItemName().equals(opcPVName)) {
							opcItemList.add(imiOpcItemPV);
	        				break;
						}
					}
        		}
			}
        	
        	for (OpcItem opcItem1 : opcItemList) {
				String itemName = opcItem1.getItemName();
				if(itemName.contains("FU")) {//下列U类变量后缀是FUX结尾的，为了在java端方便处理，需要转换为FXU后缀
					if(
						itemName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)||//助剂6一次添加完成
						itemName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)||//加碱量范围下限
						itemName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)||//二次加215完成
						itemName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
						itemName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)//二次加水完成
					) {
						itemName=reverseVarSuffix(itemName);
						opcItem1.setItemName(itemName);
					}
				}
			}
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	return opcItemList;
		}
	}
	
	/**
	 * 颠倒变量后缀里字符位置(opc端有些变量是F1U、而有些变量是FU1,在java端统一替换为F1U)
	 * @param varName
	 * @return
	 */
	public static String reverseVarSuffix(String varName) {
		//System.out.println("varName==="+varName);
		char preChar = varName.charAt(varName.length()-5);
		char nxtChar = varName.charAt(varName.length()-4);
		//System.out.println("preChar==="+preChar+",nxtChar==="+nxtChar);
		varName=varName.replace(preChar+""+nxtChar+"_AV", nxtChar+""+nxtChar+"_AV");
		//System.out.println("varName==="+varName);
		varName=varName.replace(nxtChar+""+nxtChar, nxtChar+""+preChar);
		//System.out.println("itemName==="+varName);
		return varName;
	}
	
	/**
	 * 根据变量名返回模拟变量，当变量在opc端不存在的话才返回模拟变量
	 * @param itemName
	 * @return
	 */
	public static OpcItem getImiOpcItem(String itemName) {
		//System.out.println("getImiOpcItem..........");
		float value=0;
		if(
		   itemName.startsWith(Constant.BEI_LIAO_KAI_SHI)||//备料开始
		   itemName.startsWith(Constant.FAN_YING_JIE_SHU)||//反应结束
		   itemName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI)||//甲醛备料开始
		   itemName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG)||//甲醛放料完成
		   itemName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG)||//加碱PH值正常
		   itemName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI)||//允许一次加助剂
		   itemName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1)||//所有助剂加料完成1
		   itemName.startsWith(Constant.JIA_FEN_LIAO_TI_XING)||//加粉料提醒
		   itemName.startsWith(Constant.JIA_FEN_LIAO_PH_HE_GE)||//加粉料PH合格
		   itemName.startsWith(Constant.SHENG_WEN_KAI_SHI)||//升温开始
		   itemName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING)||//温度85与二次投料提醒
		   itemName.startsWith(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING)||//二次助剂后测PH提醒
		   itemName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI)||//允许二次加助剂
		   itemName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2)||//所有助剂加料完成2
		   itemName.startsWith(Constant.SHENG_WEN_WAN_CHENG)||//升温完成
		   itemName.startsWith(Constant.WEN_DU_98_PH)||//温度98PH合格
		   itemName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING)||//测量冰水雾点提醒
		   itemName.startsWith(Constant.CE_SHUI_SHU_TI_XING)||//测水数提醒
		   itemName.startsWith("停热降温水数提醒")||//停热降温水数提醒
		   itemName.startsWith(Constant.JU_HE_ZHONG_DIAN)||//聚合终点
		   itemName.startsWith(Constant.JIANG_WEN_WAN_CHENG)||//降温完成
		   itemName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING)||//终检水PH提醒
		   itemName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO)||//允许开始排胶
		   itemName.startsWith(Constant.PAI_JIAO_WAN_CHENG)||//排胶完成
		   itemName.startsWith(Constant.KAI_SHI_JIA_LIAO)||//开始加料
		   itemName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)||//助剂6一次添加完成
		   itemName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG)||//助剂6二次备料完成
		   itemName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG)||//助剂6二次添加完成
		   itemName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG)||//第一次保温启动
		   itemName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE)||//第一次保温合格
		   itemName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING)||//一次降温加酸提醒
		   itemName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE)||//一次降温加酸合格
		   itemName.startsWith(Constant.JIA_JIAN_PH_HE_GE)||//加碱PH合格
		   itemName.startsWith(Constant.ER_CI_TOU_FEN)||//二次投粉
		   itemName.startsWith(Constant.ER_CI_JIA_215_QI_DONG)||//二次加215启动
		   itemName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)||//二次加215完成
		   itemName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
		   itemName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)||//二次加水完成
		   itemName.startsWith(Constant.FAN_YING_FU)&&itemName.endsWith(Constant.WEN_DU+Constant.XHX+Constant.AV)||//反应釜温度
		   itemName.startsWith(Constant.FU)&&itemName.endsWith(Constant.CHENG_ZHONG+Constant.XHX+Constant.AV)||//釜称重
		   itemName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)||//加碱量提示
		   itemName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)||//加碱量范围下限
		   itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)||//粉料重量设定
		   itemName.startsWith(Constant.ZHENG_QI_YA_LI)||//蒸汽压力
		   itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||//甲醛实际进料重量
		   itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)||//加水实际重量
		   itemName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)||//加碱前PH输入值
		   itemName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)||//加碱后PH输入值
		   itemName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)&&itemName.endsWith(Constant.CHENG_ZHONG+Constant.XHX+Constant.AV)||//助剂计量罐称重
		   itemName.startsWith(Constant.JIAO_GUAN_CBZ)&&itemName.endsWith(Constant.CHENG_ZHONG+Constant.XHX+Constant.AV)||//胶灌称重
		   itemName.equals(Constant.ZHONG_ZHUAN+Constant.JIAO_GUAN+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV)||//中转胶罐称重
		   itemName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)||//加粉料PH输入值
		   itemName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU)||//二次投料PH输入
		   itemName.startsWith(Constant.WEN_DU_98_PH)||//温度98PH
		   itemName.startsWith(Constant.CE_LIANG_BSWD_SRZ)||//测量冰水雾点输入值
		   itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ)||//测20雾点输入值
		   itemName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)||//停热降温水数输入值
		   itemName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)||//二次加水启动
		   itemName.startsWith(Constant.FAN_YING_FU)&&itemName.contains(Constant.JIAO_ZHONG_LEI_XING)//反应釜胶种类型
		   )
			value=0;
		
		OpcItem opcItem = new OpcItem(itemName,true,"");
		opcItem.setValue(new Variant(value));
        System.out.println("getItemName1==="+opcItem.getItemName()+",getValue1==="+opcItem.getValue().toString());
		return opcItem;
	}
	
	/**
	 * 创建带有新名字的触发器变量
	 * @param oldTV
	 * @param joinStr
	 * @return
	 */
	public static TriggerVar createNewVarNameTV(TriggerVar oldTV,String joinStr) {
		
		TriggerVar newTV = new TriggerVar();
		newTV.setVarName(oldTV.getVarName()+Constant.XHX+joinStr);
		newTV.setVarValue(oldTV.getVarValue());
		newTV.setRecType(oldTV.getRecType());
		newTV.setFId(oldTV.getFId());
		
		return newTV;
	}
}

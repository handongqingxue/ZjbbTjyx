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

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zjbbTjyx.entity.*;

public class OpcUtil {
	
	private static boolean IS_TEST=true;
	
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
    
    public static Map<String, Object> readerOpcProVarByTVList(List<TriggerVar> triggerVarList){
    	Map<String,Object> json=new HashMap<String, Object>();
    	try {
	    	/*
	        SynchReadItemExample test = new SynchReadItemExample();
	        JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
	        JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");
	        */
        
	    	SynchReadItemExample test = new SynchReadItemExample();
	    	JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
			JOpc jopc = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);
	        OpcGroup group = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);
	        
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
        
	        //甲醛放料完成
	        if(tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.XHX)) {//甲醛放料完成要记录(甲醛实际进料重量、加水实际重量、釜1称重、反应釜1温度)
	        	Integer tvFId = triggerVar1.getFId();
	        	String tvRecType = triggerVar1.getRecType();
	        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	        	//甲醛实际进料重量
	            String jqsjjlzlPvVarNameQz=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
	            String jqsjjlzlOpcVarName = jqsjjlzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            //加水实际重量
	            String jssjzlPvVarNameQz=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
	            String jssjzlOpcVarName=jssjzlPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            //反应釜(反应釜号)温度
	            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	            //釜(反应釜号)称重
	            String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=f1czPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(jqsjjlzlOpcVarName);
	            opcVarNameList.add(jssjzlOpcVarName);
	            opcVarNameList.add(fyfwdOpcVarName);
	            opcVarNameList.add(fhczOpcVarName);
	        } 
	        else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.XHX)){//甲醛备料开始要记录(釜(号)称重)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //釜(反应釜号)称重
	            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fhczOpcVarName);
	        } 
	        else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.XHX)){//加碱PH值正常要记录(加碱前PH输入值、加碱量提示、加碱后PH输入值、助剂计量罐1称重、助剂计量罐2称重)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //加碱前PH输入值
	            String jjqsrzPvVarName= Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;
	            String jjqsrzOpcVarName=jjqsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            //加碱量提示
	            String jjltsPvVarNameQz= Constant.JIA_JIAN_LIANG_TI_SHI;
	            String jjltsOpcVarName=jjltsPvVarNameQz+Constant.XHX+Constant.BSF_PF+tvFId+Constant.XHX+Constant.AV;
	            //加碱后PH输入值
	            String jjhsrzPvVarName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
	            String jjhsrzOpcVarName=jjhsrzPvVarName+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            //助剂计量罐1称重
	            String zjjlg1czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+Constant.CHENG_ZHONG;
	            String zjjlg1czOpcVarName=zjjlg1czPvVarName+Constant.XHX+Constant.AV;
	            //助剂计量罐2称重
	            String zjjlg2czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG;
	            String zjjlg2czOpcVarName=zjjlg2czPvVarName+Constant.XHX+Constant.AV;
	            opcVarNameList.add(jjqsrzOpcVarName);
	            opcVarNameList.add(jjltsOpcVarName);
	            opcVarNameList.add(jjhsrzOpcVarName);
	            opcVarNameList.add(zjjlg1czOpcVarName);
	            opcVarNameList.add(zjjlg2czOpcVarName);
	        } 
	        else if (tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.XHX)){//允许一次加助剂要记录(釜(号)称重)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //釜(反应釜号)称重
	            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fhczOpcVarName);
	        } 
	        else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.XHX)){//所有助剂加料完成1要记录(反应釜(号)温度、釜(号)称重)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //釜(反应釜号)称重
	            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	            //反应釜(反应釜号)温度
	            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fhczOpcVarName);
	            opcVarNameList.add(fyfwdOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName==null) {
	            Integer tvFId = triggerVar1.getFId();
	            
	            //粉料重量设定
	        	String flzlsdVarNameQz = Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX+Constant.BSF_PF+tvFId;
	        	String flzlsdVarName = flzlsdVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(flzlsdVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {
	            Integer tvFId = triggerVar1.getFId();
	        	if(tv2VarName.startsWith(Constant.FU+tvFId+Constant.NIAO_SU_FANG_LIAO_FA)) {
	                //釜(反应釜号)称重
	                String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	                String fhczPvVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	                opcVarNameList.add(fhczPvVarName);
	                
	                //反应釜(反应釜号)温度
	                String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	                String fyfwdPvVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	                opcVarNameList.add(fyfwdPvVarName);
	            }
	        }
	        else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_PH_HE_GE)) {
	        	Integer tvFId = triggerVar1.getFId();
	        	String tvRecType = triggerVar1.getRecType();
	        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	        	//加粉料PH输入值
	            String jflphsrzPvVarNameQz=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;
	            String jflphsrzOpcVarName=jflphsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            opcVarNameList.add(jflphsrzOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.XHX)){//升温开始要记录(蒸汽压力MPa)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //蒸汽压力MPa
	            String zqylmpaPvVarNameQz=Constant.ZHENG_QI_YA_LI+Constant.MPA;
	            String zqylOpcVarName=zqylmpaPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(zqylOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.XHX)){//温度85与二次投料提醒要记录(反应釜(号)温度)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //反应釜(反应釜号)温度
	            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fyfwdOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX)) {//二次助剂后测PH提醒要记录(二次投料PH输入)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //二次投料PH输入
	            String ectlphsrPvVarNameQz=Constant.ER_CI_TOU_LIAO_PH_SHU_RU;
	            String ectlphsrVarName=ectlphsrPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            opcVarNameList.add(ectlphsrVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.XHX)){//允许二次加助剂要记录(釜(号)称重)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
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
	            
	            //反应釜(反应釜号)温度
	            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fyfwdOpcVarName);
	            opcVarNameList.add(fhczOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.XHX)){//升温完成要记录(反应釜(号)温度)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //反应釜(反应釜号)温度
	            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
	            String fyfwdOpcVarName=fyfwdPvVarNameQz+Constant.XHX+Constant.AV;
	            opcVarNameList.add(fyfwdOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.WEN_DU_98_PH+Constant.HE_GE+Constant.XHX)){//温度98PH合格要记录(温度98PH)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //温度98PH
	            String wd98phPvVarNameQz=Constant.WEN_DU_98_PH;
	            String wd98phOpcVarName=wd98phPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            opcVarNameList.add(wd98phOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+Constant.XHX)){//测量冰水雾点提醒要记录(测量冰水雾点输入值、测20雾点输入值、停热降温水数输入值)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            
	            //测量冰水雾点输入值
	            String clbswdsrzPvVarNameQz=Constant.CE_LIANG_BSWD_SRZ;
	            String clbswdsrzOpcVarName=clbswdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            
	            //测20雾点输入值
	            String c20wdsrzPvVarNameQz=Constant.CE_20_WU_DIAN_SRZ;
	            String c20wdsrzOpcVarName=c20wdsrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            
	            opcVarNameList.add(clbswdsrzOpcVarName);
	            opcVarNameList.add(c20wdsrzOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.CE_SHUI_SHU_TI_XING+Constant.XHX)){//测水数提醒
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            //停热降温水数输入值
	            String trjwsssrzPvVarNameQz=Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ;
	            String trjwsssrzOpcVarName=trjwsssrzPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            opcVarNameList.add(trjwsssrzOpcVarName);
	        }
	        else if(tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.XHX)){//聚合终点要记录(反应釜温度)
	            Integer tvFId = triggerVar1.getFId();
	            String tvRecType = triggerVar1.getRecType();
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
	            String tvRecType = triggerVar1.getRecType();
	            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
	            
	            //终检水数
	            String zjssPvVarNameQz=Constant.ZHONG_JIAN_SHUI_SHU;
	            String zjssOpcVarName=zjssPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            
	            //终检PH
	            String zjphPvVarNameQz=Constant.ZHONG_JIAN_PH;
	            String zjphOpcVarName=zjphPvVarNameQz+Constant.XHX+opcFName+Constant.XHX+Constant.AV;
	            
	            opcVarNameList.add(zjssOpcVarName);
	            opcVarNameList.add(zjphOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)) {//允许开始排胶
	        	Integer tvFId = triggerVar1.getFId();
	            
	            //釜(反应釜号)称重
	            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	            
	            opcVarNameList.add(fhczOpcVarName);
	        }
	        else if (tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)) {//排胶完成
	        	Integer tvFId = triggerVar1.getFId();
	            
	            //釜(反应釜号)称重
	            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
	            String fhczOpcVarName=fhczPvVarNameQz+Constant.XHX+Constant.AV;
	            
	            opcVarNameList.add(fhczOpcVarName);
	        }
        

	        if(!IS_TEST){
	            //要要读取的值循环添加到group里面
	            for (String opcVarName : opcVarNameList) {
	                // new Opcitem("K1.Value",true,"");  "K1.Value" 表示要读取opc服务器中的变量名称的值。
	                group.addItem(new OpcItem( opcVarName, true, ""));
	            }
	        }

	        jopc.addGroup(group);   //添加组
	
	        OpcGroup responseGroup = null;
	
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
        
	        List<ProcessVar> proVarList=new ArrayList<ProcessVar>();
	        
	        ArrayList<OpcItem> opcItems = null;
	        if(IS_TEST) {
	            try {
	                opcItems = getOpcItemTestList(opcVarNameList);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        else {
				responseGroup = jopc.synchReadGroup(group);
	    		opcItems = responseGroup.getItems();
	        }
	
	        /*
	        OpcItem opcItem1 = new OpcItem("甲醛实际进料重量_F1_AV",false,"111");
	        opcItem1.setValue(new Variant("0"));
	        opcItems.add(opcItem1);
	        */
        
	        ProcessVar proVar=null;
	        if(opcItems!=null) {
		        for (OpcItem opcItem : opcItems) {//一个触发变量可能会查询多个过程变量，得用集合存储
		        	String itemName = opcItem.getItemName();
		        	Float value = Float.valueOf(opcItem.getValue().toString());
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
		        	else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName==null){//加粉料提醒
		                if (itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX)){
		                    varName=Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING;
		                }
		            }
		        	else if(tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING+Constant.XHX)&&tv2VarName!=null) {
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
		                if (itemName.startsWith(Constant.ZHENG_QI_YA_LI+Constant.MPA)){
		                    varName=Constant.ZHENG_QI_YA_LI+Constant.MPA;
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
		                }else if (itemName.startsWith(Constant.FAN_YING_FU+tv1FId+Constant.WEN_DU)){
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
		            else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)) {//允许开始排胶
		            	if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)) {
		            		varName=ERecord.YXKSPJSSYFCZ;
		            	}
		            }
		            else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)) {//排胶完成
		            	if (itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)) {
		            		varName=ERecord.PJWCSSYFCZ;
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
		                itemName.startsWith(Constant.ZHENG_QI_YA_LI+Constant.MPA)||
		                itemName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU)||
		                itemName.startsWith(Constant.WEN_DU_98_PH)||
		                itemName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING)||
		                itemName.startsWith(Constant.CE_20_WU_DIAN_SRZ)||
		                itemName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)
		            ){
		                paraType=ProcessVar.GYCS;
		            }
		        	else if(itemName.startsWith(Constant.FU+tv1FId+Constant.CHENG_ZHONG)||
		                    itemName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)||
		                    itemName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||
		                    itemName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)
		            ){
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
        

	        //以下是报表里所需的系统时间，opc上没有这些变量，就得根据服务器的系统时间获取，再存入集合里
	        String itemName = null;
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
	        else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)) {//允许开始排胶
	        	itemName = ERecord.YXKSPJSSYSJ;//允许开始排胶上升沿时间
	        }
	        else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)) {//排胶完成
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
			   tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.XHX)||
			   tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX)||
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
        
	        json.put("status", "ok");
	        json.put("proVarList", proVarList);
        
		} catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
            return json;
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
    	
        opcTVNameList.add(Constant.CE_SHUI_SHU_TI_XING+Constant.XHX+"F2"+Constant.XHX+Constant.AV);//测水数提醒
        
        opcTVNameList.add(Constant.JU_HE_ZHONG_DIAN+Constant.XHX+"F1"+Constant.XHX+Constant.AV);//聚合终点
        opcTVNameList.add(Constant.JU_HE_ZHONG_DIAN+Constant.XHX+"F2"+Constant.XHX+Constant.AV);//聚合终点
        opcTVNameList.add(Constant.JU_HE_ZHONG_DIAN+Constant.XHX+"F3"+Constant.XHX+Constant.AV);//聚合终点
        opcTVNameList.add(Constant.JU_HE_ZHONG_DIAN+Constant.XHX+"F4"+Constant.XHX+Constant.AV);//聚合终点
        
        opcTVNameList.add(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX+"F1"+Constant.XHX+Constant.AV);//排胶完成
        opcTVNameList.add(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX+"F2"+Constant.XHX+Constant.AV);//排胶完成
        opcTVNameList.add(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX+"F3"+Constant.XHX+Constant.AV);//排胶完成
        opcTVNameList.add(Constant.PAI_JIAO_WAN_CHENG+Constant.XHX+"F4"+Constant.XHX+Constant.AV);//排胶完成
        
        opcTVNameList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX+"F1"+Constant.XHX+Constant.AV);//二次助剂后测PH提醒
        opcTVNameList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX+"F3"+Constant.XHX+Constant.AV);//二次助剂后测PH提醒
        opcTVNameList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX+"F4"+Constant.XHX+Constant.AV);//二次助剂后测PH提醒
        opcTVNameList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+Constant.XHX+"F5"+Constant.XHX+Constant.AV);//二次助剂后测PH提醒
        
        opcTVNameList.add(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX+"F1"+Constant.XHX+Constant.AV);//降温完成
        opcTVNameList.add(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX+"F2"+Constant.XHX+Constant.AV);//降温完成
        opcTVNameList.add(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX+"F3"+Constant.XHX+Constant.AV);//降温完成
        opcTVNameList.add(Constant.JIANG_WEN_WAN_CHENG+Constant.XHX+"F4"+Constant.XHX+Constant.AV);//降温完成

    	List<String> opcTVNamePreList=new ArrayList<String>();//前缀集合
    	opcTVNamePreList.add(Constant.BEI_LIAO_KAI_SHI);//备料开始前缀
        opcTVNamePreList.add(Constant.FAN_YING_JIE_SHU);//反应结束
        //opcTVNamePreList.add(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI);//甲醛备料开始
        opcTVNamePreList.add(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG);//甲醛放料完成
        opcTVNamePreList.add(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG);//加碱PH值正常
        opcTVNamePreList.add(Constant.YUN_XU_YI_CI_JIA_ZHU_JI);//允许一次加助剂
        opcTVNamePreList.add(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1);//所有助剂加料完成1
        opcTVNamePreList.add(Constant.JIA_FEN_LIAO_TI_XING);//加粉料提醒
        opcTVNamePreList.add(Constant.JIA_FEN_LIAO_PH_HE_GE);//加粉料PH合格
        opcTVNamePreList.add(Constant.SHENG_WEN_KAI_SHI);//升温开始
        opcTVNamePreList.add(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING);//温度85与二次投料提醒
        //opcTVNamePreList.add(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING);//二次助剂后测PH提醒----这个变量F2没有
        opcTVNamePreList.add(Constant.YUN_XU_ER_CI_JIA_ZHU_JI);//允许二次加助剂
        opcTVNamePreList.add(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2);//所有助剂加料完成2
        opcTVNamePreList.add(Constant.SHENG_WEN_WAN_CHENG);//升温完成
        opcTVNamePreList.add(Constant.WEN_DU_98_PH);//温度98PH合格
        opcTVNamePreList.add(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING);//测量冰水雾点提醒
        //opcTVNamePreList.add(Constant.CE_SHUI_SHU_TI_XING);//测水数提醒----这个只有F2有值
        opcTVNamePreList.add("停热降温水数提醒");
        //opcTVNamePreList.add(Constant.JU_HE_ZHONG_DIAN);//聚合终点----这个F5没值,因为5号的生产流程还没有做，报表可以先不做5号的
        //opcTVNamePreList.add(Constant.JIANG_WEN_WAN_CHENG);//降温完成----这个变量F5没有
        opcTVNamePreList.add(Constant.YUN_XU_KAI_SHI_PAI_JIAO);//允许开始排胶
        //opcTVNamePreList.add(Constant.PAI_JIAO_WAN_CHENG);//排胶完成---F5没有

    	for (String opcTVNamePre : opcTVNamePreList) {//循环拼接上反应釜号作为完整的变量
    		for (String fMName : Constant.BSF_F_M_ARR) {
    			String opcTVName = null;
    			if(Constant.WEN_DU_98_PH.equals(opcTVNamePre))
    				continue;
    				
    			if(Constant.FAN_YING_JIE_SHU.equals(opcTVNamePre))
    				opcTVName = opcTVNamePre+fMName+Constant.XHX+Constant.AV;
    			else
    				opcTVName = opcTVNamePre+Constant.XHX+fMName+Constant.XHX+Constant.AV;
    			
    			System.out.println("opcTVName==="+opcTVName);
    			opcTVNameList.add(opcTVName);
			}
    		/*
    		for (String fMName : Constant.BSF_F_U_ARR) {
    			String opcTVName = opcTVNamePre+"_"+fMName+"_AV";
    			opcTVNameList.add(opcTVName);
			}
			*/
		}
    	
    	return opcTVNameList;
	}

	public static List<String> getOpcPVNameList() {
		// TODO Auto-generated method stub
    	List<String> opcPVNameList=new ArrayList<String>();
    	
    	
		for (int fId : Constant.F_ID_ARR) {
			opcPVNameList.add(Constant.FAN_YING_FU+fId+Constant.WEN_DU+Constant.XHX+Constant.AV);//反应釜温度
			opcPVNameList.add(Constant.FU+fId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);//釜称重
		}
		
		for (String pFM : Constant.BSF_PF_M_ARR) {
			//opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+pFM+Constant.XHX+Constant.AV);//加碱量提示---F2找不到
			opcPVNameList.add(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING+Constant.XHX+pFM+Constant.XHX+Constant.AV);//粉料重量设定
		}
		
		opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF1"+Constant.XHX+Constant.AV);//加碱量提示
		//opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF2"+Constant.XHX+Constant.AV);//加碱量提示
		opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF3"+Constant.XHX+Constant.AV);//加碱量提示
		opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF4"+Constant.XHX+Constant.AV);//加碱量提示
		opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF5"+Constant.XHX+Constant.AV);//加碱量提示
		
		opcPVNameList.add(Constant.ZHENG_QI_YA_LI+Constant.XHX+Constant.AV);//蒸汽压力
		
		opcPVNameList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ+Constant.XHX+"F1"+Constant.XHX+Constant.AV);//停热降温水数输入值
		opcPVNameList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ+Constant.XHX+"F3"+Constant.XHX+Constant.AV);
		opcPVNameList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ+Constant.XHX+"F4"+Constant.XHX+Constant.AV);
		opcPVNameList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ+Constant.XHX+"F5"+Constant.XHX+Constant.AV);
        
		opcPVNameList.add(Constant.JIA_JIAN_LIANG_TI_SHI+Constant.XHX+"PF1"+Constant.XHX+Constant.AV);
		opcPVNameList.add("加碱量范围上限"+Constant.XHX+"PF2"+Constant.XHX+Constant.AV);//----这个F3-F5没有变量
        //opcPVNameList.add("加碱量范围上限"+Constant.XHX+"PF3"+Constant.XHX+Constant.AV);
        //opcPVNameList.add("加碱量范围上限"+Constant.XHX+"PF4"+Constant.XHX+Constant.AV);
        //opcPVNameList.add("加碱量范围上限"+Constant.XHX+"PF5"+Constant.XHX+Constant.AV);
        
        //opcPVNameList.add("反应釜1胶种类型"+Constant.XHX+Constant.AV);//这个变量没有

    	
    	List<String> opcPVNamePreList=new ArrayList<String>();//前缀集合

    	opcPVNamePreList.add(Constant.DANG_QIAN_JIAO_ZHONG_XIAN_SHI);//当前胶种显示---这个变量的值是0.0
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
    		opcPVNameList.add(Constant.JIAO_GUAN+jgId+Constant.CHENG_ZHONG+Constant.XHX+Constant.AV);
    	}

    	opcPVNamePreList.add(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI);//加粉料PH输入值
    	opcPVNamePreList.add(Constant.ER_CI_TOU_LIAO_PH_SHU_RU);//二次投料PH输入
    	opcPVNamePreList.add(Constant.WEN_DU_98_PH);//温度98PH
    	opcPVNamePreList.add(Constant.CE_LIANG_BSWD_SRZ);//测量冰水雾点输入值
    	opcPVNamePreList.add(Constant.CE_20_WU_DIAN_SRZ);//测20雾点输入值
    	//opcPVNamePreList.add(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ);//停热降温水数输入值-----这个缺少F2变量
    	
    	for (String opcPVNamePre : opcPVNamePreList) {//循环拼接上反应釜号作为完整的变量
    		for (String fMName : Constant.BSF_F_M_ARR) {
    			String opcPVName = opcPVNamePre+Constant.XHX+fMName+Constant.XHX+Constant.AV;
    			opcPVNameList.add(opcPVName);
			}
    	}
    	
		return opcPVNameList;
	}
    
    /**
     * 从opc服务器端同步数据库的触发器变量
     * @param opcVarNameList
     */
    public static void syncTVByOpcVNList(List<String> opcVarNameList) {
        try {
	    	SynchReadItemExample test = new SynchReadItemExample();
	    	JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
			JOpc jopc = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);
	    	
	        OpcGroup group = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);
	    	for (String opcVarName : opcVarNameList) {
	        	group.addItem(new OpcItem( opcVarName, true, ""));
			}
	
	        jopc.addGroup(group);   //添加组
	        
	        /*
	        OpcGroup[] groups = jopc.getGroupsAsArray();
	        System.out.println("groups.length==="+groups.length);
	        for (int i = 0; i < groups.length; i++) {
	            System.out.println("getGroupName"+i+"==="+groups[i].getGroupName());
			}
			*/
	        
	        OpcGroup responseGroup = null;
	
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
	            test.wait(50);
	        }
	
			responseGroup = jopc.synchReadGroup(group);
	        ArrayList<OpcItem> opcItemList = responseGroup.getItems();
	        for (OpcItem opcItem : opcItemList) {
	            System.out.println("getItemName==="+opcItem.getItemName()+",getValue==="+opcItem.getValue().toString());
			}
			APIUtil.addVar("addTriggerVarFromOpc",opcItemList);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取opc服务器端过程变量
	 * @param opcPVNameList
	 */
	public static void readPVByOpcVNList(List<String> opcVarNameList) {
		// TODO Auto-generated method stub
        try {
			SynchReadItemExample test = new SynchReadItemExample();
	    	JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
			JOpc jopc = new JOpc(Constant.OPC_HOST, Constant.OPC_SERVER_PROG_ID, Constant.OPC_SERVER_CLIENT_HANDLE);
	    	
	        OpcGroup group = new OpcGroup(Constant.OPC_GROUP_NAME, true, 500, 0.0f);
	    	for (String opcVarName : opcVarNameList) {
	        	group.addItem(new OpcItem( opcVarName, true, ""));
			}
	
	        jopc.addGroup(group);   //添加组
	
	        OpcGroup responseGroup = null;
	
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
	            test.wait(50);
	        }
	
			responseGroup = jopc.synchReadGroup(group);
	        ArrayList<OpcItem> opcItemList = responseGroup.getItems();
	        for (OpcItem opcItem : opcItemList) {
	            System.out.println("getItemName==="+opcItem.getItemName()+",getValue==="+opcItem.getValue().toString());
			}
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

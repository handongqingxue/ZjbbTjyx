package com.uWinOPCTjyx.util;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.SynchReadItemExample;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.*;
import javafish.clients.opc.variant.Variant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uWinOPCTjyx.entity.*;

public class OpcUtil {
	
	private static SynchReadItemExample test = null;
	private static JOpc jopc = null;
	
	static {
		test = new SynchReadItemExample();
		JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc
		jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");
	}

    public static void main(String[] args) {
        SynchReadItemExample test = new SynchReadItemExample();

        JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc

        JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");

        OpcGroup group = new OpcGroup("_System", true, 500, 0.0f);

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
//		ArrayList<OpcItem> opcItems = responseGroup.getItems();
        List<OpcItem> opcItems = new ArrayList<OpcItem>();
        OpcItem opcItem1 = new OpcItem("'甲醛实际进料重量_F1_AV",false,"111");
        opcItem1.setValue(new Variant("0"));
        opcItems.add(opcItem1);
        for (OpcItem opcItem : opcItems) {
            System.out.println("Item名:" + opcItem.getItemName() + "  Item值: " + opcItem.getValue());
        }
        System.out.println(opcItems.toString());
    }
    
    public static Map<String, Object> readerOpcProVarByTVList(List<TriggerVar> triggerVarList){

    	Map<String,Object> json=new HashMap<String, Object>();
    	
    	/*
        SynchReadItemExample test = new SynchReadItemExample();

        JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc

        JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");
        */
        
        OpcGroup group = new OpcGroup("_System", true, 500, 0.0f);
        
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
        String varName1 = triggerVar1.getVarName();
        //甲醛放料完成
        if(varName1.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+"_")) {//甲醛放料完成要记录(甲醛实际进料重量、加水实际重量、釜1称重、反应釜1温度)
        	Integer tvFId = triggerVar1.getFId();
        	String tvRecType = triggerVar1.getRecType();
        	String opcFName = getFNameByFIdRecType(tvFId,tvRecType);
        	//甲醛实际进料重量
            String jqsjjlzlPvVarNameQz=Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG;
            String jqsjjlzlOpcVarName = jqsjjlzlPvVarNameQz+"_"+opcFName+"_AV";

            //加水实际重量
            String jssjzlPvVarNameQz=Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG;
            String jssjzlOpcVarName=jssjzlPvVarNameQz+"_"+opcFName+"_AV";

            //反应釜(反应釜号)温度
            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
            String fyfwdOpcVarName=fyfwdPvVarNameQz+"_AV";
            
            //釜(反应釜号)称重
            String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
            String fhczOpcVarName=f1czPvVarNameQz+"_AV";

            opcVarNameList.add(jqsjjlzlOpcVarName);
            opcVarNameList.add(jssjzlOpcVarName);
            opcVarNameList.add(fyfwdOpcVarName);
            opcVarNameList.add(fhczOpcVarName);
        }
        //甲醛备料完成
        if (varName1.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_")){//甲醛备料完成要记录(釜(号)称重)
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);

            //釜(反应釜号)称重
            String f1czPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
            String fhczOpcVarName=f1czPvVarNameQz+"_AV";

            opcVarNameList.add(fhczOpcVarName);
        }
        //加碱PH值正常
        if (varName1.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+"_")){//加碱PH值正常要记录(加碱量提示、加碱后PH输入值、助剂计量罐
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);



        }
        if(false){
            //要要读取的值循环添加到group里面
            for (String opcVarName : opcVarNameList) {
                // new Opcitem("K1.Value",true,"");  "K1.Value" 表示要读取opc服务器中的变量名称的值。
                group.addItem(new OpcItem( opcVarName, true, ""));
            }
        }

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
//		ArrayList<OpcItem> opcItems = responseGroup.getItems();
        
        List<ProcessVar> proVarList=new ArrayList<ProcessVar>();
        
        List<OpcItem> opcItems = new ArrayList<OpcItem>();
        OpcItem opcItem1 = new OpcItem("甲醛实际进料重量_F1_AV",false,"111");
        opcItem1.setValue(new Variant("0"));
        opcItems.add(opcItem1);
        
        ProcessVar proVar=null;
        for (OpcItem opcItem : opcItems) {//一个触发变量可能会查询多个过程变量，得用集合存储
        	String itemName = opcItem.getItemName();
        	Float value = Float.valueOf(opcItem.getValue().toString());
        	String unit=null;
        	//判断单位
        	if (itemName.contains(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||
                    itemName.contains(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)
            ){
                unit=Constant.KG;//kg
            }
        	if (itemName.contains("")){
        	    unit=Constant.WEN_DU_DAN_WEI_SIGN;//°C
            }
        	proVar=new ProcessVar();
        	proVar.setVarName(itemName);
        	proVar.setVarValue(value);
        	proVar.setDealBz(ProcessVar.WCL);
        	proVar.setRecType(triggerVar1.getRecType());
        	proVar.setUnit(unit);
        	proVarList.add(proVar);
            System.out.println("Item名:" + itemName + "  Item值: " + value);
        }

        //以下是报表里所需的系统时间，opc上没有这些变量，就得根据服务器的系统时间获取，再存入集合里
        //备料开始
        if(varName1.contains(Constant.BEI_LIAO_KAI_SHI+"_")) {
        	String varName = Constant.BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
        	proVar=new ProcessVar();
        	proVar.setVarName(varName);
        	proVar.setUpdateTime(sysTime);
        	proVarList.add(proVar);
        }
        //甲醛放料完成
        else if(varName1.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+"_")) {
        	String varName = Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        	String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
        	proVar=new ProcessVar();
        	proVar.setVarName(varName);
        	proVar.setUpdateTime(sysTime);
        	proVarList.add(proVar);
        }
        //甲醛备料完成
        else if (varName1.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_")){
            String varName = Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
            String sysTime = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);//系统时间
            proVar=new ProcessVar();
            proVar.setVarName(varName);
            proVar.setUpdateTime(sysTime);
            proVarList.add(proVar);
        }
        
        json.put("status", "ok");
        json.put("proVarList", proVarList);
        
        return json;
    }
    
    /**
     * 根据釜id和胶类型获取釜名
     * @param fId
     * @param recType
     * @return
     */
    private static String getFNameByFIdRecType(Integer fId,String recType) {
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
}

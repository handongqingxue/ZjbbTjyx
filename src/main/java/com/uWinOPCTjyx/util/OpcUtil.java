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

import org.json.JSONArray;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.uWinOPCTjyx.entity.*;

public class OpcUtil {
	
	private static SynchReadItemExample test = null;
	private static JOpc jopc = null;
	private static boolean IS_TEST=true;
	
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
        
        List<String> varNameList = new ArrayList<String>();
//		ArrayList<OpcItem> opcItems = responseGroup.getItems();
        List<OpcItem> opcItems = new ArrayList<OpcItem>();
        OpcItem opcItem1 = new OpcItem("甲醛实际进料重量_F1_AV",false,"111");
        opcItem1.setValue(new Variant("0"));
        opcItems.add(opcItem1);
        
        OpcItem opcItem2 = new OpcItem("加水实际重量_F1_AV",false,"111");
        opcItem2.setValue(new Variant("0"));
        opcItems.add(opcItem2);
        
        for (OpcItem opcItem : opcItems) {
        	varNameList.add(opcItem.getItemName());
            System.out.println("Item名:" + opcItem.getItemName() + "  Item值: " + opcItem.getValue());
        }
        System.out.println(opcItems.toString());
        
        getOpcItemTestList(varNameList);
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
        else if (varName1.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_")){//甲醛备料完成要记录(釜(号)称重)
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);

            //釜(反应釜号)称重
            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
            String fhczOpcVarName=fhczPvVarNameQz+"_AV";

            opcVarNameList.add(fhczOpcVarName);
        }
        else if (varName1.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+"_")){//加碱PH值正常要记录(加碱量提示、加碱后PH输入值、助剂计量罐1称重、助剂计量罐2称重)
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);

            //加碱量提示
            String jjltsPvVarNameQz= Constant.JIA_JIAN_LIANG_TI_SHI;
            String jjltsOpcVarName=jjltsPvVarNameQz+"_"+Constant.BSF_PF+tvFId+"_AV";

            //加碱后PH输入值
            String jjhsrzPvVarName=Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI;
            String jjhsrzOpcVarName=jjhsrzPvVarName+"_"+opcFName+"_AV";

            //助剂计量罐1称重
            String zjjlg1czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+Constant.CHENG_ZHONG;
            String zjjlg1czOpcVarName=zjjlg1czPvVarName+"_AV";

            //助剂计量罐2称重
            String zjjlg2czPvVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG;
            String zjjlg2czOpcVarName=zjjlg2czPvVarName+"_AV";

            opcVarNameList.add(jjltsOpcVarName);
            opcVarNameList.add(jjhsrzOpcVarName);
            opcVarNameList.add(zjjlg1czOpcVarName);
            opcVarNameList.add(zjjlg2czOpcVarName);
        }
        else if (varName1.contains(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+"_")){//允许一次加助剂要记录(釜(号)称重)
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);

            //釜(反应釜号)称重
            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
            String fhczOpcVarName=fhczPvVarNameQz+"_AV";
            opcVarNameList.add(fhczOpcVarName);
        }
        else if (varName1.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+"_")){//所有助剂加料完成1要记录(反应釜(号)温度、釜(号)称重)
            Integer tvFId = triggerVar1.getFId();
            String tvRecType = triggerVar1.getRecType();
            String opcFName = getFNameByFIdRecType(tvFId,tvRecType);

            //釜(反应釜号)称重
            String fhczPvVarNameQz=Constant.FU+tvFId+Constant.CHENG_ZHONG;
            String fhczOpcVarName=fhczPvVarNameQz+"_AV";

            //反应釜(反应釜号)温度
            String fyfwdPvVarNameQz=Constant.FAN_YING_FU+tvFId+Constant.WEN_DU;
            String fyfwdOpcVarName=fyfwdPvVarNameQz+"_AV";

            opcVarNameList.add(fhczOpcVarName);
            opcVarNameList.add(fyfwdOpcVarName);
        }

        if(false){
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
        if(IS_TEST)
        	opcItems = getOpcItemTestList(opcVarNameList);
        else
    		opcItems = responseGroup.getItems();
        /*
        OpcItem opcItem1 = new OpcItem("甲醛实际进料重量_F1_AV",false,"111");
        opcItem1.setValue(new Variant("0"));
        opcItems.add(opcItem1);
        */
        
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
        String varName = null;
        if(varName1.contains(Constant.BEI_LIAO_KAI_SHI+"_")) {//备料开始
        	varName = Constant.BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        }
        else if(varName1.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+"_")) { //甲醛放料完成
        	varName = Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        }
        else if (varName1.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_")){//甲醛备料完成
            varName = Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        }
        else if(varName1.contains(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+"_")) {//允许一次加助剂
            varName = Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        }
        else if (varName1.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+"_")){//所有助剂加料完成
            varName = Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
        }
        
        
        //触发器变量1在满足以上几种情况时，说明需要添加系统时间，就调用下面这个逻辑。若加在上面代码量太多，就简化一下加在下面
        if(varName1.contains(Constant.BEI_LIAO_KAI_SHI+"_")||
           varName1.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+"_")||
           varName1.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_")||
           varName1.contains(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+"_")||
           varName1.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1)
        ) {
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
    
    private static ArrayList<OpcItem> getOpcItemTestList(List<String> varNameList) {
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
		ArrayList<OpcItem> opcItemList = convertOVTToOIList(opcVarTestList);
		return opcItemList;
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
}

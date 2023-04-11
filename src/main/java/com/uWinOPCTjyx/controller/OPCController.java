package com.uWinOPCTjyx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import javafish.clients.opc.component.OpcItem;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.*;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/opc")
public class OPCController {

	@Autowired
	private ERecordService eRecordService;
	@Autowired
	private TriggerVarService triggerVarService;
	@Autowired
	private ProcessVarService processVarService;
	private Map<String,Object> f1Map,f2Map,f3Map,f4Map,f5Map;

	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/opcu")
	public String goOpcU(HttpServletRequest request) {
		//localhost:8080/UWinOPCTjyx/opc/opcu
		//访问opcu的web页面
		return MODULE_NAME+"/opcu";
	}

	@RequestMapping(value="/opcm")
	public String goOpcM(HttpServletRequest request) {
		//localhost:8080/UWinOPCTjyx/opc/opcm
		//访问opcm的web页面
		return MODULE_NAME+"/opcm";
	}

	@RequestMapping(value = "/editTriggerVarByReqBody", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editTriggerVarByReqBody(@RequestBody String bodyStr){
		
		Map<String,Object> json=new HashMap<String, Object>();
		
		System.out.println("bodyStr==="+bodyStr);
		List<TriggerVar> triggerVarList = JSON.parseArray(bodyStr, TriggerVar.class);
		try {
			int count = triggerVarService.editFromList(triggerVarList);
			if (count>0){
				json.put("message","ok");
				json.put("info","编辑成功");
			}
			else {
				json.put("message","no");
				json.put("info","编辑失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return json;
		}
	}
	
	@RequestMapping(value = "/initFMap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initFMap(@RequestBody String bodyStr) {

		HashMap<String,Object> json=new HashMap<String, Object>();
		//1号釜
		HashMap<String,Object> f1MMap=new HashMap<String, Object>();
		HashMap<String,Object> f1UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f1Map=new HashMap<String, Object>();
		f1Map.put("run",true);
		f1Map.put("f1MMap",f1MMap);
		f1Map.put("f1UMap",f1UMap);

		//2号釜
		HashMap<String,Object> f2MMap=new HashMap<String, Object>();
		HashMap<String,Object> f2UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f2Map=new HashMap<String, Object>();
		f2Map.put("run",false);
		f2Map.put("f2MMap",f1MMap);
		f2Map.put("f2UMap",f1UMap);


		//3号釜
		HashMap<String,Object> f3MMap=new HashMap<String, Object>();
		HashMap<String,Object> f3UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f3Map=new HashMap<String, Object>();
		f3Map.put("run",false);
		f3Map.put("f3MMap",f1MMap);
		f3Map.put("f3UMap",f1UMap);

		//4号釜
		HashMap<String,Object> f4MMap=new HashMap<String, Object>();
		HashMap<String,Object> f4UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f4Map=new HashMap<String, Object>();
		f4Map.put("run",false);
		f4Map.put("f4MMap",f1MMap);
		f4Map.put("f4UMap",f1UMap);

		//5号釜
		HashMap<String,Object> f5MMap=new HashMap<String, Object>();
		HashMap<String,Object> f5UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f5Map=new HashMap<String, Object>();
		f5Map.put("run",false);
		f5Map.put("f5MMap",f1MMap);
		f5Map.put("f5UMap",f1UMap);
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/keepWatchOnTriggerVar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> keepWatchOnTriggerVar() {

		Map<String,Object> json=new HashMap<String, Object>();
		
		List<Integer> runFIdList=new ArrayList<Integer>();
		
		//判断反应釜是否在运行
		boolean runF1 = Boolean.parseBoolean(f1Map.get("run").toString());
		System.out.println("runF1==="+runF1);
		if(runF1) {
			runFIdList.add(Constant.F1_ID);
		}
		
		boolean runF2 = Boolean.parseBoolean(f2Map.get("run").toString());
		System.out.println("runF2==="+runF2);
		if(runF2) {
			runFIdList.add(Constant.F2_ID);
		}
		
		boolean runF3 = Boolean.parseBoolean(f3Map.get("run").toString());
		System.out.println("runF3==="+runF3);
		if(runF3) {
			runFIdList.add(Constant.F3_ID);
		}
		
		boolean runF4 = Boolean.parseBoolean(f4Map.get("run").toString());
		System.out.println("runF4==="+runF4);
		if(runF4) {
			runFIdList.add(Constant.F4_ID);
		}
		
		boolean runF5 = Boolean.parseBoolean(f5Map.get("run").toString());
		System.out.println("runF5==="+runF5);
		if(runF5) {
			runFIdList.add(Constant.F5_ID);
		}
		
		HashMap<String,Object> preValueF1MMap=(HashMap<String,Object>)f1Map.get("f1MMap");
		HashMap<String,Object> preValueF1UMap=(HashMap<String,Object>)f1Map.get("f1UMap");
		

		//每次检索只获取一次所有的触发量就行，下面的逻辑里会根据不同的变量从反应釜列表里读取
		List<TriggerVar> triggerVarList = triggerVarService.getListByFIdList(runFIdList);//先获取所有反应釜触发量,不管是否是上升沿
		Map<String,List<TriggerVar>> triggerVarMap=getTriVarListGroupMap(triggerVarList);
		
		//降温完成
		List<Integer> jwwcFIdList=new ArrayList<Integer>();//降温完成反应釜号集合(M类和U类共用)
		List<TriggerVar> jwwcTVList=(List<TriggerVar>)triggerVarMap.get(Constant.JIANG_WEN_WAN_CHENG_TEXT);//先获取所有反应釜降温完成触发量,不管是否是上升沿
		List<TriggerVar> upJwwcTVList = getUpDownVarValueListFromList(jwwcTVList,TriggerVar.UP);//获取上升的降温完成变量
		for (TriggerVar upJwwcTV : upJwwcTVList) {
			Integer upFId = upJwwcTV.getFId();
			String upRecType = upJwwcTV.getRecType();
			String upVarName = upJwwcTV.getVarName();//上次变量名和本次变量名其实是一致的
			switch (upFId) {
			case Constant.F1_ID:
				if(TriggerVar.M.equals(upRecType)) {
					Float preValue = Float.valueOf(preValueF1MMap.get(upVarName).toString());
					if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
						jwwcFIdList.add(upFId);
					}
				}
				else if(TriggerVar.U.equals(upRecType)) {
					Float preValue = Float.valueOf(preValueF1UMap.get(upVarName).toString());
					if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
						jwwcFIdList.add(upFId);
					}
				}
				break;
			}
		}
		
		if(jwwcFIdList.size()>0) {//若有需要处理的降温完成节点的反应釜，说明这些反应釜的批次执行完成，就从过程变量表(ProcessVar)里读取已采集好的变量，经过加工处理存入批记录表(ERecord)里
			List<ProcessVar> proVarList=processVarService.getUnDealListByFIdList(jwwcFIdList);
		}
		
		updateProTVListByCurrList(jwwcTVList);//这个方法用来存储本次变量值，作为下次检索里的上次变量值来使用。每次检索结束后都要记录一下
		
		
		
		return json;
	}

	/**
	 * 根据不同变量给触发器变量集合分组(备料开始、甲醛备料开始这些变量组)
	 * @param triggerVarList
	 * @return
	 */
	private Map<String, List<TriggerVar>> getTriVarListGroupMap(List<TriggerVar> triggerVarList) {
		// TODO Auto-generated method stub
		Map<String, List<TriggerVar>> tvGroupMap=new HashMap<String, List<TriggerVar>>();
		List<TriggerVar> jwwcTVList=new ArrayList<TriggerVar>();
		
		for (TriggerVar triggerVar : triggerVarList) {
			String varName = triggerVar.getVarName();
			String recType = triggerVar.getRecType();
			
			String fyfh=null;
			Integer fId = triggerVar.getFId();
			switch (fId) {
			case Constant.F1_ID:
				if(TriggerVar.M.equals(recType))
					fyfh=Constant.BSF_F1;
				else if(TriggerVar.U.equals(recType))
					fyfh=Constant.BSF_F1U;
				break;
			case Constant.F2_ID:
				if(TriggerVar.M.equals(recType))
					fyfh=Constant.BSF_F2;
				else if(TriggerVar.U.equals(recType))
					fyfh=Constant.BSF_F2U;
				break;
			case Constant.F3_ID:
				if(TriggerVar.M.equals(recType))
					fyfh=Constant.BSF_F3;
				else if(TriggerVar.U.equals(recType))
					fyfh=Constant.BSF_F3U;
				break;
			case Constant.F4_ID:
				if(TriggerVar.M.equals(recType))
					fyfh=Constant.BSF_F4;
				else if(TriggerVar.U.equals(recType))
					fyfh=Constant.BSF_F4U;
				break;
			case Constant.F5_ID:
				if(TriggerVar.M.equals(recType))
					fyfh=Constant.BSF_F5;
				else if(TriggerVar.U.equals(recType))
					fyfh=Constant.BSF_F5U;
				break;
			}
			
			if((Constant.JIANG_WEN_WAN_CHENG_TEXT+"_"+fyfh+"_AV").equals(varName)) {
				jwwcTVList.add(triggerVar);
			}
		}
		
		tvGroupMap.put(Constant.JIANG_WEN_WAN_CHENG_TEXT, jwwcTVList);
		
		return tvGroupMap;
	}

	@RequestMapping(value = "/addTriggerVarFromOpc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTriggerVarFromOpc(@RequestBody String bodyStr){
		
		Map<String,Object> json=new HashMap<String, Object>();
		
		System.out.println("进来");

		System.out.println(bodyStr);
		
		return json;
	}
	
	/**
	 * 记录当前触发器变量数值，作为下次检索时的上次变量使用
	 * @param currTVList
	 */
	private void updateProTVListByCurrList(List<TriggerVar> currTVList) {
		for (TriggerVar currTV : currTVList) {
			String varName = currTV.getVarName();
			String recType = currTV.getRecType();
			Float varValue = currTV.getVarValue();
			Integer fId = currTV.getFId();
			switch (fId) {
			case Constant.F1_ID:
				if(TriggerVar.M.equals(recType)) {
					HashMap<String,Object> preValueF1MMap=(HashMap<String,Object>)f1Map.get("f1MMap");
					putTriVarValueInPreMap(varValue,varName,preValueF1MMap);
				}
				break;
			}
		}
	}
	
	/**
	 * 根据标识符获取上升或下降变量集合
	 * @param triggerVarList
	 * @param flag
	 * @return
	 */
	private List<TriggerVar> getUpDownVarValueListFromList(List<TriggerVar> triggerVarList,int flag) {
		List<TriggerVar> upDownVarValueTVList=new ArrayList<TriggerVar>();
		for (TriggerVar triggerVar : triggerVarList) {
			Float varValue = triggerVar.getVarValue();
			if(varValue==flag) {
				upDownVarValueTVList.add(triggerVar);
			}
		}
		return upDownVarValueTVList;
	}
	
	/**
	 * 添加或更新opc变量上次数值到哈希表里
	 * @param varValue
	 * @param varName
	 * @param preValMap
	 */
	private void putTriVarValueInPreMap(Float varValue,String varName,HashMap<String,Object> preValMap) {
		boolean exist=checkTriVarIfExistInPreValMap(varName,preValMap);
		if(exist) {
			Set<String> preVarNameSet = preValMap.keySet();
			for (String preVarName : preVarNameSet) {
				if(preVarName.equals(varName)) {
					preValMap.put(preVarName, varValue);
					break;
				}
			}
		}
		else {
			preValMap.put(varName, varValue);
		}
	}
	
	/**
	 * 根据变量名称检测上次变量数值是否存在于哈希表里
	 * @param varName
	 * @param preValMap
	 * @return
	 */
	private boolean checkTriVarIfExistInPreValMap(String varName,HashMap<String,Object> preValMap) {
		boolean exist=false;
		Set<String> preVarNameSet = preValMap.keySet();
		for (String preVarName : preVarNameSet) {
			if(preVarName.equals(varName)) {
				exist=true;
				break;
			}
		}
		return exist;
	}
	
}

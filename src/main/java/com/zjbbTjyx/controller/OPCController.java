package com.zjbbTjyx.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
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
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

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
	@Autowired
	private OpcVarTestService opcVarTestService;

	private Map<String,Object> f1Map,f2Map,f3Map,f4Map,f5Map;

	public static final String MODULE_NAME="opc";
	private boolean initFMap=false;
	
	@RequestMapping(value="/opc")
	public String goOpcU(HttpServletRequest request) {
		//localhost:8080/ZjbbTjyx/opc/opcu
		//访问opcu的web页面
		return MODULE_NAME+"/opc";
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

	/**
	 * 同步触发器变量
	 * @return
	 */
	@RequestMapping(value = "/syncTriggerVar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> syncTriggerVar() {

		Map<String,Object> json=new HashMap<String, Object>();
		
		List<String> opcTVNameList=OpcUtil.getOpcTVNameList();
		OpcUtil.syncTVByOpcVNList(opcTVNameList);
		
		return json;
	}

	@RequestMapping(value = "/readOpcProVarList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> readOpcProVarList() {

		Map<String,Object> json=new HashMap<String, Object>();
		
		List<String> opcPVNameList=OpcUtil.getOpcPVNameList();
		OpcUtil.readPVByOpcVNList(opcPVNameList);
		
		return json;
	}
	
	@RequestMapping(value = "/initFMap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> initFMap(@RequestBody String bodyStr) {
		HashMap<String,Object> json=new HashMap<String, Object>();

		List<Integer> fIdList=new ArrayList<Integer>();//创建反应釜号集合，用来存储反应釜号
		for (Integer fId : Constant.F_ID_ARR) {//遍历出所有的反应釜号
			fIdList.add(fId);
		}
		List<TriggerVar> triggerVarList = triggerVarService.getListByFIdList(fIdList);//查询全部触发器变量
		System.out.println("全部触发器变量");
		for (TriggerVar triggerVar : triggerVarList) {
			System.out.println(triggerVar.toString());
		}
		//1号釜
		HashMap<String,Object> f1MMap=new HashMap<String, Object>();
		HashMap<String,Object> f1UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		//1号釜
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
		f2Map.put("f2MMap",f2MMap);
		f2Map.put("f2UMap",f2UMap);

		//3号釜
		HashMap<String,Object> f3MMap=new HashMap<String, Object>();
		HashMap<String,Object> f3UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f3Map=new HashMap<String, Object>();
		f3Map.put("run",false);
		f3Map.put("f3MMap",f3MMap);
		f3Map.put("f3UMap",f3UMap);

		//4号釜
		HashMap<String,Object> f4MMap=new HashMap<String, Object>();
		HashMap<String,Object> f4UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f4Map=new HashMap<String, Object>();
		f4Map.put("run",false);
		f4Map.put("f4MMap",f4MMap);
		f4Map.put("f4UMap",f4UMap);

		//5号釜
		HashMap<String,Object> f5MMap=new HashMap<String, Object>();
		HashMap<String,Object> f5UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f5Map=new HashMap<String, Object>();
		f5Map.put("run",false);
		f5Map.put("f5MMap",f5MMap);
		f5Map.put("f5UMap",f5UMap);

		for (TriggerVar triggerVar : triggerVarList) {
			switch (triggerVar.getFId()){
				case Constant.F1_ID:
					if (triggerVar.getRecType().equals(TriggerVar.M)){
						f1MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					else if (triggerVar.getRecType().equals(TriggerVar.U)){
						f1UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					else if (triggerVar.getRecType().equals(TriggerVar.MU)){
						f1MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
						f1UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					break;
				case Constant.F2_ID:
					if (triggerVar.getRecType().equals(TriggerVar.M)){
						f2MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}else if (triggerVar.getRecType().equals(TriggerVar.U)){
						f2UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					break;
				case Constant.F3_ID:
					if (triggerVar.getRecType().equals(TriggerVar.M)){
						f3MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}else if (triggerVar.getRecType().equals(TriggerVar.U)){
						f3UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					break;
				case Constant.F4_ID:
					if (triggerVar.getRecType().equals(TriggerVar.M)){
						f4MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}else if (triggerVar.getRecType().equals(TriggerVar.U)){
						f4UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					break;
				case Constant.F5_ID:
					if (triggerVar.getRecType().equals(TriggerVar.M)){
						f5MMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}else if (triggerVar.getRecType().equals(TriggerVar.U)){
						f5UMap.put(triggerVar.getVarName(),Constant.FYF_CSZ);
					}
					break;
			}
		}
		return json;
	}

	@RequestMapping(value = "/keepWatchOnTriggerVar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> keepWatchOnTriggerVar() {
		if(!initFMap) {
			System.out.println("初始化反应釜Map........");
			initFMap("");
			initFMap=true;
		}

		Map<String,Object> json=new HashMap<String, Object>();
		List<Integer> runFIdList=new ArrayList<Integer>();//用于存放运行的反应釜号的集合
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
		System.out.println("运行的釜号:"+runFIdList.toString());
		
		HashMap<String,Object> preValueF1MMap=(HashMap<String,Object>)f1Map.get("f1MMap");//获取1号釜M类
		HashMap<String,Object> preValueF1UMap=(HashMap<String,Object>)f1Map.get("f1UMap");//获取1号釜U类
		System.out.println("1号釜M类"+preValueF1MMap.toString());
		System.out.println("1号釜U类"+preValueF1UMap.toString());

		HashMap<String,Object> preValueF2MMap=(HashMap<String,Object>)f2Map.get("f2MMap");//获取2号釜M类
		HashMap<String,Object> preValueF2UMap=(HashMap<String,Object>)f2Map.get("f2UMap");//获取2号釜U类
		System.out.println("2号釜M类"+preValueF2MMap.toString());
		System.out.println("2号釜U类"+preValueF2UMap.toString());

		HashMap<String,Object> preValueF3MMap=(HashMap<String,Object>)f3Map.get("f3MMap");//获取3号釜M类
		HashMap<String,Object> preValueF3UMap=(HashMap<String,Object>)f3Map.get("f3UMap");//获取3号釜U类
		System.out.println("3号釜M类"+preValueF3MMap.toString());
		System.out.println("3号釜U类"+preValueF3UMap.toString());

		HashMap<String,Object> preValueF4MMap=(HashMap<String,Object>)f4Map.get("f4MMap");//获取4号釜M类
		HashMap<String,Object> preValueF4UMap=(HashMap<String,Object>)f4Map.get("f4UMap");//获取4号釜U类
		System.out.println("4号釜M类"+preValueF4MMap.toString());
		System.out.println("4号釜U类"+preValueF4UMap.toString());

		HashMap<String,Object> preValueF5MMap=(HashMap<String,Object>)f5Map.get("f5MMap");//获取5号釜M类
		HashMap<String,Object> preValueF5UMap=(HashMap<String,Object>)f5Map.get("f5UMap");//获取5号釜U类
		System.out.println("5号釜M类"+preValueF5MMap.toString());
		System.out.println("5号釜U类"+preValueF5UMap.toString());
		
		//每次检索只获取一次所有的触发量就行，下面的逻辑里会根据不同的变量从反应釜列表里读取
		List<TriggerVar> triggerVarList = triggerVarService.getListByFIdList(runFIdList);//先获取所有运行的反应釜触发量,不管是否是上升沿
		System.out.println("所有的反应釜触发变量↓");
		for (TriggerVar triggerVar : triggerVarList) {
			System.out.println(triggerVar.toString());
		}

		Map<String,List<TriggerVar>> triggerVarMap=getTriVarListGroupMap(triggerVarList);//将获取的变量分组
		System.out.println("------------------------------");
		System.out.println(triggerVarMap.toString());
		//李工的代码逻辑从这里开始写
		//if(false) {
		//备料开始触发量
		String blksTVVarNamePre=Constant.BEI_LIAO_KAI_SHI;
		List<TriggerVar> blksTVList = (List<TriggerVar>)triggerVarMap.get(blksTVVarNamePre);//获取备料开始触发变量,不管是否是上升沿
		System.out.println("备料开始==="+blksTVList.toString());
		List<TriggerVar> upBlksTVList = getUpDownVarValueListFromList(blksTVList, TriggerVar.UP);//获取上升的备料开始变量
		System.out.println("upBlksTVList的长度"+upBlksTVList.size());
		System.out.println("upBlksTVList"+upBlksTVList.toString());
		for (TriggerVar upBlksTV : upBlksTVList) {
			Integer upFId = upBlksTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",blksTVVarNamePre);
					paramF1Map.put("upBlksTV",upBlksTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",blksTVVarNamePre);
					paramF2Map.put("upBlksTV",upBlksTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",blksTVVarNamePre);
					paramF3Map.put("upBlksTV",upBlksTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",blksTVVarNamePre);
					paramF4Map.put("upBlksTV",upBlksTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",blksTVVarNamePre);
					paramF5Map.put("upBlksTV",upBlksTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		//}

		//反应结束
		String fyjsTVVarNamePre=Constant.FAN_YING_JIE_SHU;
		List<TriggerVar> fyjsTVList = (List<TriggerVar>)triggerVarMap.get(fyjsTVVarNamePre);//获取反应结束变量,不管是否是上升沿
		List<TriggerVar> upFyjsTVList = getUpDownVarValueListFromList(fyjsTVList, TriggerVar.UP);//获取上升的反应结束变量
		for (TriggerVar upFyjsTV : upFyjsTVList) {
			Integer upFId = upFyjsTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",fyjsTVVarNamePre);
					paramF1Map.put("upFyjsTV",upFyjsTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",fyjsTVVarNamePre);
					paramF2Map.put("upFyjsTV",upFyjsTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",fyjsTVVarNamePre);
					paramF3Map.put("upFyjsTV",upFyjsTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",fyjsTVVarNamePre);
					paramF4Map.put("upFyjsTV",upFyjsTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",fyjsTVVarNamePre);
					paramF5Map.put("upFyjsTV",upFyjsTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;

			}
		}

		//甲醛备料开始
		String jqblksTVVarNamePre=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI;
		List<TriggerVar> jqblksTVList = (List<TriggerVar>)triggerVarMap.get(jqblksTVVarNamePre);//获取甲醛备料开始变量,不管是否是上升沿
		List<TriggerVar> upJqblksTVList = getUpDownVarValueListFromList(jqblksTVList, TriggerVar.UP);//获取上升的甲醛备料开始完成变量
		for (TriggerVar upJqblksTV : upJqblksTVList) {
			Integer upFId = upJqblksTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jqblksTVVarNamePre);
					paramF1Map.put("upJqblksTV",upJqblksTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jqblksTVVarNamePre);
					paramF2Map.put("upJqblksTV",upJqblksTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jqblksTVVarNamePre);
					paramF3Map.put("upJqblksTV",upJqblksTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jqblksTVVarNamePre);
					paramF4Map.put("upJqblksTV",upJqblksTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jqblksTVVarNamePre);
					paramF5Map.put("upJqblksTV",upJqblksTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;

			}
		}


		//if(false) {
		//甲醛放料完成
		String jqflwcTVVarNamePre=Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG;
		List<TriggerVar> jqflwcTVList = (List<TriggerVar>)triggerVarMap.get(jqflwcTVVarNamePre);//获取甲醛放料完成变量,不管是否是上升沿
		List<TriggerVar> upJqflwcTVList = getUpDownVarValueListFromList(jqflwcTVList, TriggerVar.UP);//获取上升的甲醛放料完成变量
		for (TriggerVar upJqflwcTV : upJqflwcTVList) {
			Integer upFId = upJqflwcTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jqflwcTVVarNamePre);
					paramF1Map.put("upJqflwcTV",upJqflwcTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jqflwcTVVarNamePre);
					paramF2Map.put("upJqflwcTV",upJqflwcTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jqflwcTVVarNamePre);
					paramF3Map.put("upJqflwcTV",upJqflwcTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jqflwcTVVarNamePre);
					paramF4Map.put("upJqflwcTV",upJqflwcTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jqflwcTVVarNamePre);
					paramF5Map.put("upJqflwcTV",upJqflwcTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;

			}
		}
		//}

		
		//加碱PH值正常
		String jjphzzcTVVarNamePre=Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG;
		List<TriggerVar> jjphzzcTVList = (List<TriggerVar>)triggerVarMap.get(jjphzzcTVVarNamePre);//获取加碱PH值正常变量,不管是否是上升沿
		List<TriggerVar> upJjphzzcTVList = getUpDownVarValueListFromList(jjphzzcTVList, TriggerVar.UP);//获取上升的加碱PH值正常完成变量
		for (TriggerVar upJjphzzcTV : upJjphzzcTVList) {
			Integer upFId = upJjphzzcTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jjphzzcTVVarNamePre);
					paramF1Map.put("upJjphzzcTV",upJjphzzcTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jjphzzcTVVarNamePre);
					paramF2Map.put("upJjphzzcTV",upJjphzzcTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jjphzzcTVVarNamePre);
					paramF3Map.put("upJjphzzcTV",upJjphzzcTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jjphzzcTVVarNamePre);
					paramF4Map.put("upJjphzzcTV",upJjphzzcTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jjphzzcTVVarNamePre);
					paramF5Map.put("upJjphzzcTV",upJjphzzcTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		

		//允许一次加助剂
		String yxycjzjTVVarNamePre=Constant.YUN_XU_YI_CI_JIA_ZHU_JI;
		List<TriggerVar> yxycjzjTVList = (List<TriggerVar>)triggerVarMap.get(yxycjzjTVVarNamePre);//获取允许一次加助剂变量,不管是否是上升沿
		List<TriggerVar> upYxycjzjTVList = getUpDownVarValueListFromList(yxycjzjTVList, TriggerVar.UP);//获取上升的允许一次加助剂变量
		for (TriggerVar upYxycjzjTV : upYxycjzjTVList) {
			Integer upFId = upYxycjzjTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",yxycjzjTVVarNamePre);
					paramF1Map.put("upYxycjzjTV",upYxycjzjTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",yxycjzjTVVarNamePre);
					paramF2Map.put("upYxycjzjTV",upYxycjzjTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",yxycjzjTVVarNamePre);
					paramF3Map.put("upYxycjzjTV",upYxycjzjTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",yxycjzjTVVarNamePre);
					paramF4Map.put("upYxycjzjTV",upYxycjzjTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",yxycjzjTVVarNamePre);
					paramF5Map.put("upYxycjzjTV",upYxycjzjTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//所有助剂加料完成1
		String syzjjlwc1TVVarNamePre=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1;
		List<TriggerVar> syzjjlwc1TVList = (List<TriggerVar>)triggerVarMap.get(syzjjlwc1TVVarNamePre);//获取所有助剂加料完成1变量,不管是否是上升沿
		List<TriggerVar> upSyzjjlwc1TVList = getUpDownVarValueListFromList(syzjjlwc1TVList, TriggerVar.UP);//获取上升的所有助剂加料完成1变量
		for (TriggerVar upSyzjjlwc1TV : upSyzjjlwc1TVList) {
			Integer upFId = upSyzjjlwc1TV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",syzjjlwc1TVVarNamePre);
					paramF1Map.put("upSyzjjlwc1TV",upSyzjjlwc1TV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",syzjjlwc1TVVarNamePre);
					paramF2Map.put("upSyzjjlwc1TV",upSyzjjlwc1TV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",syzjjlwc1TVVarNamePre);
					paramF3Map.put("upSyzjjlwc1TV",upSyzjjlwc1TV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",syzjjlwc1TVVarNamePre);
					paramF4Map.put("upSyzjjlwc1TV",upSyzjjlwc1TV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",syzjjlwc1TVVarNamePre);
					paramF5Map.put("upSyzjjlwc1TV",upSyzjjlwc1TV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		

		//int jfltxSign=TriggerVar.DOWN;//加粉料提醒标志
		//int jfltxFId=0;//加粉料提醒釜id
		//加粉料提醒
		String jfltxTVVarNamePre=Constant.JIA_FEN_LIAO_TI_XING;
		List<TriggerVar> jfltxTVList = (List<TriggerVar>)triggerVarMap.get(jfltxTVVarNamePre);//获取加粉料提醒变量,不管是否是上升沿
		List<TriggerVar> upJfltxTVList = getUpDownVarValueListFromList(jfltxTVList, TriggerVar.UP);//获取上升的加粉料提醒变量
		List<TriggerVar> fnsflfTVList = triggerVarMap.get(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA);////获取釜尿素放料阀变量,不管是否是上升沿
		for (TriggerVar upJfltxTV : upJfltxTVList) {
			//jfltxSign=TriggerVar.UP;//上升沿为1
			Integer upFId = upJfltxTV.getFId();//获取反应釜号
			//jfltxFId=upFId;//设置加粉料提醒釜id
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jfltxTVVarNamePre);
					paramF1Map.put("upJfltxTV",upJfltxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					paramF1Map.put("fnsflfTVList",fnsflfTVList);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jfltxTVVarNamePre);
					paramF2Map.put("upJfltxTV",upJfltxTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					paramF2Map.put("fnsflfTVList",fnsflfTVList);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jfltxTVVarNamePre);
					paramF3Map.put("upJfltxTV",upJfltxTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					paramF3Map.put("fnsflfTVList",fnsflfTVList);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jfltxTVVarNamePre);
					paramF4Map.put("upJfltxTV",upJfltxTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					paramF4Map.put("fnsflfTVList",fnsflfTVList);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jfltxTVVarNamePre);
					paramF5Map.put("upJfltxTV",upJfltxTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					paramF5Map.put("fnsflfTVList",fnsflfTVList);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//加粉料PH合格
		String jflphhgTVVarNamePre=Constant.JIA_FEN_LIAO_PH_HE_GE;
		List<TriggerVar> jflphhgTVList = (List<TriggerVar>)triggerVarMap.get(jflphhgTVVarNamePre);//获取加粉料PH合格变量,不管是否是上升沿
		List<TriggerVar> upJflphhgTVList = getUpDownVarValueListFromList(jflphhgTVList, TriggerVar.UP);//获取上升的加粉料PH合格变量
		for (TriggerVar upJflphhgTV : upJflphhgTVList) {
			Integer upFId = upJflphhgTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jflphhgTVVarNamePre);
					paramF1Map.put("upJflphhgTV",upJflphhgTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jflphhgTVVarNamePre);
					paramF2Map.put("upJflphhgTV",upJflphhgTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jflphhgTVVarNamePre);
					paramF3Map.put("upJflphhgTV",upJflphhgTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jflphhgTVVarNamePre);
					paramF4Map.put("upJflphhgTV",upJflphhgTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jflphhgTVVarNamePre);
					paramF5Map.put("upJflphhgTV",upJflphhgTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//升温开始
		String swksTVVarNamePre=Constant.SHENG_WEN_KAI_SHI;
		List<TriggerVar> swksTVList = (List<TriggerVar>)triggerVarMap.get(swksTVVarNamePre);//获取升温开始变量,不管是否是上升沿
		List<TriggerVar> upSwksTVList = getUpDownVarValueListFromList(swksTVList, TriggerVar.UP);//获取上升的升温开始变量
		for (TriggerVar upSwksTV : upSwksTVList) {
			Integer upFId = upSwksTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",swksTVVarNamePre);
					paramF1Map.put("upSwksTV",upSwksTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",swksTVVarNamePre);
					paramF2Map.put("upSwksTV",upSwksTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",swksTVVarNamePre);
					paramF3Map.put("upSwksTV",upSwksTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",swksTVVarNamePre);
					paramF4Map.put("upSwksTV",upSwksTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",swksTVVarNamePre);
					paramF5Map.put("upSwksTV",upSwksTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		

		//温度85与二次投料提醒
		String wd85yectltxTVVarNamePre=Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING;
		List<TriggerVar> wd85yectltxTVList = (List<TriggerVar>)triggerVarMap.get(wd85yectltxTVVarNamePre);//获取温度85与二次投料提醒变量,不管是否是上升沿
		List<TriggerVar> upWd85yectltxTVList = getUpDownVarValueListFromList(wd85yectltxTVList, TriggerVar.UP);//获取上升的温度85与二次投料提醒变量
		for (TriggerVar upWd85yectltxTV : upWd85yectltxTVList) {
			Integer upFId = upWd85yectltxTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",wd85yectltxTVVarNamePre);
					paramF1Map.put("upWd85yectltxTV",upWd85yectltxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",wd85yectltxTVVarNamePre);
					paramF2Map.put("upWd85yectltxTV",upWd85yectltxTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",wd85yectltxTVVarNamePre);
					paramF3Map.put("upWd85yectltxTV",upWd85yectltxTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",wd85yectltxTVVarNamePre);
					paramF4Map.put("upWd85yectltxTV",upWd85yectltxTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",wd85yectltxTVVarNamePre);
					paramF5Map.put("upWd85yectltxTV",upWd85yectltxTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//二次助剂后测PH提醒
		String eczjhcphtxTVVarNamePre=Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING;
		List<TriggerVar> eczjhcphtxTVList = (List<TriggerVar>)triggerVarMap.get(eczjhcphtxTVVarNamePre);//获取二次助剂后测PH提醒变量,不管是否是上升沿
		List<TriggerVar> upEczjhcphtxTVList = getUpDownVarValueListFromList(eczjhcphtxTVList, TriggerVar.UP);//获取上升的二次助剂后测PH提醒变量
		for (TriggerVar upEczjhcphtxTV : upEczjhcphtxTVList) {
			Integer upFId = upEczjhcphtxTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",eczjhcphtxTVVarNamePre);
					paramF1Map.put("upEczjhcphtxTV",upEczjhcphtxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",eczjhcphtxTVVarNamePre);
					paramF2Map.put("upEczjhcphtxTV",upEczjhcphtxTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",eczjhcphtxTVVarNamePre);
					paramF3Map.put("upEczjhcphtxTV",upEczjhcphtxTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",eczjhcphtxTVVarNamePre);
					paramF4Map.put("upEczjhcphtxTV",upEczjhcphtxTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",eczjhcphtxTVVarNamePre);
					paramF5Map.put("upEczjhcphtxTV",upEczjhcphtxTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//允许二次加助剂
		String yxecjzjTVVarNamePre=Constant.YUN_XU_ER_CI_JIA_ZHU_JI;
		List<TriggerVar> yxecjzjTVList = (List<TriggerVar>)triggerVarMap.get(yxecjzjTVVarNamePre);//获取二次加助剂变量,不管是否是上升沿
		List<TriggerVar> upYxecjzjTVList = getUpDownVarValueListFromList(yxecjzjTVList, TriggerVar.UP);//获取上升的允许二次加助剂变量
		for (TriggerVar upYxecjzjTV : upYxecjzjTVList) {
			Integer upFId = upYxecjzjTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",yxecjzjTVVarNamePre);
					paramF1Map.put("upYxecjzjTV",upYxecjzjTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",yxecjzjTVVarNamePre);
					paramF2Map.put("upYxecjzjTV",upYxecjzjTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",yxecjzjTVVarNamePre);
					paramF3Map.put("upYxecjzjTV",upYxecjzjTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",yxecjzjTVVarNamePre);
					paramF4Map.put("upYxecjzjTV",upYxecjzjTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",yxecjzjTVVarNamePre);
					paramF5Map.put("upYxecjzjTV",upYxecjzjTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//所有助剂加料完成2
		String syzjjlwcTVVarNamePre=Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2;
		List<TriggerVar> syzjjlwc2TVList = (List<TriggerVar>)triggerVarMap.get(syzjjlwcTVVarNamePre);//获取所有助剂加料完成2变量,不管是否是上升沿
		List<TriggerVar> upSyzjjlwc2TVList = getUpDownVarValueListFromList(syzjjlwc2TVList, TriggerVar.UP);//获取上升的允所有助剂加料完成2变量
		for (TriggerVar upSyzjjlwc2TV : upSyzjjlwc2TVList) {
			Integer upFId = upSyzjjlwc2TV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",syzjjlwcTVVarNamePre);
					paramF1Map.put("upSyzjjlwc2TV",upSyzjjlwc2TV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",syzjjlwcTVVarNamePre);
					paramF2Map.put("upSyzjjlwc2TV",upSyzjjlwc2TV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",syzjjlwcTVVarNamePre);
					paramF3Map.put("upSyzjjlwc2TV",upSyzjjlwc2TV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",syzjjlwcTVVarNamePre);
					paramF4Map.put("upSyzjjlwc2TV",upSyzjjlwc2TV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",syzjjlwcTVVarNamePre);
					paramF5Map.put("upSyzjjlwc2TV",upSyzjjlwc2TV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//升温完成
		String swwcTVVarNamePre=Constant.SHENG_WEN_WAN_CHENG;
		List<TriggerVar> swwcTVList = (List<TriggerVar>)triggerVarMap.get(swwcTVVarNamePre);//获取升温完成变量,不管是否是上升沿
		List<TriggerVar> upSwwcTVList = getUpDownVarValueListFromList(swwcTVList, TriggerVar.UP);//获取上升的升温完成变量
		for (TriggerVar upSwwcTV : upSwwcTVList) {
			Integer upFId = upSwwcTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",swwcTVVarNamePre);
					paramF1Map.put("upSwwcTV",upSwwcTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",swwcTVVarNamePre);
					paramF2Map.put("upSwwcTV",upSwwcTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",swwcTVVarNamePre);
					paramF3Map.put("upSwwcTV",upSwwcTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",swwcTVVarNamePre);
					paramF4Map.put("upSwwcTV",upSwwcTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",swwcTVVarNamePre);
					paramF5Map.put("upSwwcTV",upSwwcTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//if(false) {
		//温度98PH合格
		String wd98phheTVVarNamePre=Constant.WEN_DU_98_PH+Constant.HE_GE;
		List<TriggerVar> wd98phhgTVList = (List<TriggerVar>)triggerVarMap.get(wd98phheTVVarNamePre);//获取温度98PH合格变量,不管是否是上升沿
		List<TriggerVar> upWd98phhgTVList = getUpDownVarValueListFromList(wd98phhgTVList, TriggerVar.UP);//获取上升的温度98PH合格变量
		for (TriggerVar upWd98phhgTV : upWd98phhgTVList) {
			Integer upFId = upWd98phhgTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",wd98phheTVVarNamePre);
					paramF1Map.put("upWd98phhgTV",upWd98phhgTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",wd98phheTVVarNamePre);
					paramF2Map.put("upWd98phhgTV",upWd98phhgTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",wd98phheTVVarNamePre);
					paramF3Map.put("upWd98phhgTV",upWd98phhgTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",wd98phheTVVarNamePre);
					paramF4Map.put("upWd98phhgTV",upWd98phhgTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",wd98phheTVVarNamePre);
					paramF5Map.put("upWd98phhgTV",upWd98phhgTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		//}

		
		//测量冰水雾点提醒
		String clbswdtxTVVarNamePre=Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING;
		List<TriggerVar> clbswdtxTVList = (List<TriggerVar>)triggerVarMap.get(clbswdtxTVVarNamePre);//获取测量冰水雾点提醒变量,不管是否是上升沿
		List<TriggerVar> downClbswdtxTVList = getUpDownVarValueListFromList(clbswdtxTVList, TriggerVar.DOWN);//获取下降的测量冰水雾点提醒变量
		for (TriggerVar downClbswdtxTV : downClbswdtxTVList) {
			Integer downFId = downClbswdtxTV.getFId();//获取反应釜号
			switch (downFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",clbswdtxTVVarNamePre);
					paramF1Map.put("downClbswdtxTV",downClbswdtxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",clbswdtxTVVarNamePre);
					paramF2Map.put("downClbswdtxTV",downClbswdtxTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",clbswdtxTVVarNamePre);
					paramF3Map.put("downClbswdtxTV",downClbswdtxTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",clbswdtxTVVarNamePre);
					paramF4Map.put("downClbswdtxTV",downClbswdtxTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",clbswdtxTVVarNamePre);
					paramF5Map.put("downClbswdtxTV",downClbswdtxTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}

		
		//聚合终点
		String jhzdTVVarNamePre=Constant.JU_HE_ZHONG_DIAN;
		List<TriggerVar> jhzdTVList = (List<TriggerVar>)triggerVarMap.get(jhzdTVVarNamePre);//获取聚合终点变量,不管是否是上升沿
		List<TriggerVar> upJhzdTVList = getUpDownVarValueListFromList(jhzdTVList, TriggerVar.UP);//获取上升的聚合终点变量
		for (TriggerVar upJhzdTV : upJhzdTVList) {
			Integer upFId = upJhzdTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jhzdTVVarNamePre);
					paramF1Map.put("upJhzdTV",upJhzdTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
				case Constant.F2_ID:
					Map<String,Object> paramF2Map=new HashMap<String,Object>();
					paramF2Map.put("tvVarNamePre",jhzdTVVarNamePre);
					paramF2Map.put("upJhzdTV",upJhzdTV);
					paramF2Map.put("preValueFMMap",preValueF2MMap);
					paramF2Map.put("preValueFUMap",preValueF2UMap);
					addProVarByParamMap(paramF2Map);
					break;
				case Constant.F3_ID:
					Map<String,Object> paramF3Map=new HashMap<String,Object>();
					paramF3Map.put("tvVarNamePre",jhzdTVVarNamePre);
					paramF3Map.put("upJhzdTV",upJhzdTV);
					paramF3Map.put("preValueFMMap",preValueF3MMap);
					paramF3Map.put("preValueFUMap",preValueF3UMap);
					addProVarByParamMap(paramF3Map);
					break;
				case Constant.F4_ID:
					Map<String,Object> paramF4Map=new HashMap<String,Object>();
					paramF4Map.put("tvVarNamePre",jhzdTVVarNamePre);
					paramF4Map.put("upJhzdTV",upJhzdTV);
					paramF4Map.put("preValueFMMap",preValueF4MMap);
					paramF4Map.put("preValueFUMap",preValueF4UMap);
					addProVarByParamMap(paramF4Map);
					break;
				case Constant.F5_ID:
					Map<String,Object> paramF5Map=new HashMap<String,Object>();
					paramF5Map.put("tvVarNamePre",jhzdTVVarNamePre);
					paramF5Map.put("upJhzdTV",upJhzdTV);
					paramF5Map.put("preValueFMMap",preValueF5MMap);
					paramF5Map.put("preValueFUMap",preValueF5UMap);
					addProVarByParamMap(paramF5Map);
					break;
			}
		}
		
		
		
		//测水数提醒
		String csstxTVVarNamePre=Constant.CE_SHUI_SHU_TI_XING;
		List<TriggerVar> csstxTVList = (List<TriggerVar>)triggerVarMap.get(csstxTVVarNamePre);//获取测水数提醒变量,不管是否是上升沿
		List<TriggerVar> downCsstxTVList = getUpDownVarValueListFromList(csstxTVList, TriggerVar.DOWN);//获取下降的测水数提醒变量
		for (TriggerVar downCsstxTV : downCsstxTVList) {
			Integer downFId = downCsstxTV.getFId();//获取反应釜号
			switch (downFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",csstxTVVarNamePre);
					paramF1Map.put("downCsstxTV",downCsstxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
			}
		}

		
		//if(false) {
		//降温完成
		List<Integer> jwwcFIdList = new ArrayList<Integer>();//降温完成反应釜号集合(M类和U类共用)
		String jwwcTVVarNamePre=Constant.JIANG_WEN_WAN_CHENG;
		List<TriggerVar> jwwcTVList = (List<TriggerVar>) triggerVarMap.get(jwwcTVVarNamePre);//先获取所有反应釜降温完成触发量,不管是否是上升沿
		List<TriggerVar> upJwwcTVList = getUpDownVarValueListFromList(jwwcTVList, TriggerVar.UP);//获取上升的降温完成变量
		for (TriggerVar upJwwcTV : upJwwcTVList) {
			Integer upFId = upJwwcTV.getFId();
			switch (upFId) {
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",jwwcTVVarNamePre);
					paramF1Map.put("upJwwcTV",upJwwcTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					paramF1Map.put("jwwcFIdList",jwwcFIdList);
					addProVarByParamMap(paramF1Map);
					break;
			}
		}

		if (jwwcFIdList.size() > 0) {//若有需要处理的降温完成节点的反应釜，说明这些反应釜的批次执行完成，就从过程变量表(ProcessVar)里读取已采集好的变量，经过加工处理存入批记录表(ERecord)里
			List<ProcessVar> udProVarList = processVarService.getUnDealListByFIdList(jwwcFIdList);
			int c = eRecordService.addFromProVarList(udProVarList);
		}
		//}
		
		
		//终检水PH提醒
		String zjsphtxTVVarNamePre=Constant.ZHONG_JIAN_SHUI_PH_TI_XING;
		List<TriggerVar> zjsphtxTVList = (List<TriggerVar>) triggerVarMap.get(zjsphtxTVVarNamePre);//先获取所有反应釜终检水PH提醒触发量,不管是否是上升沿
		List<TriggerVar> downZjsphtxTVList = getUpDownVarValueListFromList(zjsphtxTVList, TriggerVar.DOWN);//获取下降的终检水PH提醒变量
		for (TriggerVar downZjsphtxTV : downZjsphtxTVList) {
			Integer downFId = downZjsphtxTV.getFId();//获取反应釜号
			switch (downFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",zjsphtxTVVarNamePre);
					paramF1Map.put("downZjsphtxTV",downZjsphtxTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
			}
		}
		
		
		//允许开始排胶
		String yxkspjTVVarNamePre=Constant.YUN_XU_KAI_SHI_PAI_JIAO;
		List<TriggerVar> yxkspjTVList = (List<TriggerVar>) triggerVarMap.get(yxkspjTVVarNamePre);//先获取所有反应釜允许开始排胶触发量,不管是否是上升沿
		List<TriggerVar> upYxkspjTVList = getUpDownVarValueListFromList(yxkspjTVList, TriggerVar.UP);//获取上升的允许开始排胶变量
		for (TriggerVar upYxkspjTV : upYxkspjTVList) {
			Integer upFId = upYxkspjTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",yxkspjTVVarNamePre);
					paramF1Map.put("upYxkspjTV",upYxkspjTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
			}
		}
		
		
		//排胶完成
		String pjwcTVVarNamePre=Constant.PAI_JIAO_WAN_CHENG;
		List<TriggerVar> pjwcTVList = (List<TriggerVar>) triggerVarMap.get(pjwcTVVarNamePre);//先获取所有反应釜排胶完成触发量,不管是否是上升沿
		List<TriggerVar> upPjwcTVList = getUpDownVarValueListFromList(pjwcTVList, TriggerVar.UP);//获取上升的排胶完成变量
		for (TriggerVar upPjwcTV : upPjwcTVList) {
			Integer upFId = upPjwcTV.getFId();//获取反应釜号
			switch (upFId) {//匹配反应釜号
				case Constant.F1_ID:
					Map<String,Object> paramF1Map=new HashMap<String,Object>();
					paramF1Map.put("tvVarNamePre",yxkspjTVVarNamePre);
					paramF1Map.put("upPjwcTV",upPjwcTV);
					paramF1Map.put("preValueFMMap",preValueF1MMap);
					paramF1Map.put("preValueFUMap",preValueF1UMap);
					addProVarByParamMap(paramF1Map);
					break;
			}
		}
		
		
		updateProTVListByCurrList(triggerVarList);//这个方法用来存储本次变量值，作为下次检索里的上次变量值来使用。每次检索结束后都要记录一下

		
		return json;
	}
	
	/**
	 * 根据参数Map，添加过程变量
	 * @param paramMap
	 */
	private void addProVarByParamMap(Map<String,Object> paramMap) {
		String tvVarNamePre = paramMap.get("tvVarNamePre").toString();
		if(Constant.BEI_LIAO_KAI_SHI.equals(tvVarNamePre)) {//备料开始
			TriggerVar upBlksTV = (TriggerVar)paramMap.get("upBlksTV");
			String upRecType = upBlksTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upBlksTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());//可能是F1-F5之间的任何一个反应釜
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					//删除ProcessVar表里处理标志为1的数据
					Integer upFId = upBlksTV.getFId();//获取反应釜号
					processVarService.deleteDealed(upFId);
					
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upBlksTV);//根据备料开始触发变量从opc端查找对应的过程变量
					
					Map<String, Object> blksMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);
					List<ProcessVar> blksMResPVList = (List<ProcessVar>)blksMResMap.get("proVarList");
					int i = processVarService.addFromList(blksMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upBlksTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					//删除ProcessVar表里处理标志为1的数据
					Integer upFId = upBlksTV.getFId();//获取反应釜号
					processVarService.deleteDealed(upFId);
					
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upBlksTV);//根据备料开始触发变量从opc端查找对应的过程变量
					
					Map<String, Object> blksUResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);
					List<ProcessVar> blksUResPVList = (List<ProcessVar>)blksUResMap.get("proVarList");
					int i = processVarService.addFromList(blksUResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.FAN_YING_JIE_SHU.equals(tvVarNamePre)) {//反应结束
			TriggerVar upFyjsTV = (TriggerVar)paramMap.get("upFyjsTV");
			String upRecType = upFyjsTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upFyjsTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upFyjsTV);
					Map<String, Object> fyjsMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据反应结束触发变量从opc端查找对应的过程变量
					List<ProcessVar> fyjsMResPVList = (List<ProcessVar>)fyjsMResMap.get("proVarList");

					//获取反应结束时间变量名
					String fyjsSjVarName = Constant.FAN_YING_JIE_SHU+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
					ProcessVar fyjsSjPV = OpcUtil.getProVarInListByVarName(fyjsSjVarName, fyjsMResPVList);
					String fyjsSjVarValue = fyjsSjPV.getUpdateTime();
					ProcessVar ptnSjPV = processVarService.getPtnValuePV(fyjsSjVarName,fyjsSjVarValue+"",fyjsSjPV);
					fyjsMResPVList.add(ptnSjPV);//将时间差对象添加到集合里
					
					int i = processVarService.addFromList(fyjsMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upFyjsTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upFyjsTV);
					Map<String, Object> fyjsMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据反应结束触发变量从opc端查找对应的过程变量
					List<ProcessVar> fyjsMResPVList = (List<ProcessVar>)fyjsMResMap.get("proVarList");
					
					int i = processVarService.addFromList(fyjsMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI.equals(tvVarNamePre)) {//甲醛备料开始
			TriggerVar upJqblksTV = (TriggerVar)paramMap.get("upJqblksTV");
			String upRecType = upJqblksTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJqblksTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJqblksTV);
					Map<String, Object> jqblksMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据甲醛备料开始触发变量从opc端查找对应的过程变量
					List<ProcessVar> jqblksMResPVList = (List<ProcessVar>)jqblksMResMap.get("proVarList");
					int i = processVarService.addFromList(jqblksMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJqblksTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJqblksTV);
					Map<String, Object> jqblksMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据甲醛备料开始触发变量从opc端查找对应的过程变量
					List<ProcessVar> jqblksMResPVList = (List<ProcessVar>)jqblksMResMap.get("proVarList");
					int i = processVarService.addFromList(jqblksMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG.equals(tvVarNamePre)) {//甲醛放料完成
			TriggerVar upJqflwcTV = (TriggerVar)paramMap.get("upJqflwcTV");
			String upRecType = upJqflwcTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJqflwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJqflwcTV);
					Map<String, Object> jqflwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据甲醛放料完成触发变量从opc端查找对应的过程变量
					List<ProcessVar> jqflwcMResPVList = (List<ProcessVar>)jqflwcMResMap.get("proVarList");
					
					//获取甲醛放料完成釜称重变量名
					String jqflwcFczVarName = Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
					ProcessVar jqflwcFczPV = OpcUtil.getProVarInListByVarName(jqflwcFczVarName, jqflwcMResPVList);
					Float jqflwcFczVarValue = jqflwcFczPV.getVarValue();
					
					ProcessVar ptnFczPV = processVarService.getPtnValuePV(jqflwcFczVarName,jqflwcFczVarValue+"",jqflwcFczPV);
					jqflwcMResPVList.add(ptnFczPV);//将重量差对象添加到集合里
					
					//获取甲醛放料完成时间变量名
					String jqflwcSjVarName = Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
					ProcessVar jqflwcSjPV = OpcUtil.getProVarInListByVarName(jqflwcSjVarName, jqflwcMResPVList);
					String jqflwcSjVarValue = jqflwcSjPV.getUpdateTime();
					ProcessVar ptnSjPV = processVarService.getPtnValuePV(jqflwcSjVarName,jqflwcSjVarValue+"",jqflwcSjPV);
					jqflwcMResPVList.add(ptnSjPV);//将时间差对象添加到集合里
					
					int i = processVarService.addFromList(jqflwcMResPVList);//调用添加过程接口
					System.out.println("M添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJqflwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJqflwcTV);//根据甲醛放料完成触发变量从opc端查找对应的过程变量
					Map<String, Object> jqflwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据甲醛放料完成触发变量从opc端查找对应的过程变量
					List<ProcessVar> jqflwcMResPVList = (List<ProcessVar>)jqflwcMResMap.get("proVarList");
					int i = processVarService.addFromList(jqflwcMResPVList);//调用添加过程接口
					System.out.println("U添加"+i);
				}
			}
		}
		else if(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG.equals(tvVarNamePre)) {
			TriggerVar upJjphzzcTV = (TriggerVar)paramMap.get("upJjphzzcTV");
			String upRecType = upJjphzzcTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJjphzzcTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJjphzzcTV);
					Map<String, Object> jjphzzcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加碱PH值正常触发变量从opc端查找对应的过程变量
					List<ProcessVar> jjphzzcMResPVList = (List<ProcessVar>)jjphzzcMResMap.get("proVarList");
					totalZJJLGChengZhongSum(jjphzzcMResPVList);
					int i = processVarService.addFromList(jjphzzcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJjphzzcTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJjphzzcTV);
					Map<String, Object> jjphzzcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加碱PH值正常触发变量从opc端查找对应的过程变量
					List<ProcessVar> jjphzzcMResPVList = (List<ProcessVar>)jjphzzcMResMap.get("proVarList");
					int i = processVarService.addFromList(jjphzzcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.YUN_XU_YI_CI_JIA_ZHU_JI.equals(tvVarNamePre)) {
			TriggerVar upYxycjzjTV = (TriggerVar)paramMap.get("upYxycjzjTV");
			String upRecType = upYxycjzjTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upYxycjzjTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxycjzjTV);
					Map<String, Object> yxycjzjMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许一次加助剂触发变量从opc端查找对应的过程变量
					List<ProcessVar> yxycjzjMResPVList = (List<ProcessVar>)yxycjzjMResMap.get("proVarList");
					int i = processVarService.addFromList(yxycjzjMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upYxycjzjTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxycjzjTV);
					Map<String, Object> yxycjzjMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许一次加助剂变量从opc端查找对应的过程变量
					List<ProcessVar> yxycjzjMResPVList = (List<ProcessVar>)yxycjzjMResMap.get("proVarList");
					int i = processVarService.addFromList(yxycjzjMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1.equals(tvVarNamePre)) {
			TriggerVar upSyzjjlwc1TV = (TriggerVar)paramMap.get("upSyzjjlwc1TV");
			String upRecType = upSyzjjlwc1TV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upSyzjjlwc1TV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSyzjjlwc1TV);
					Map<String, Object> syzjjlwc1MResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据所有助剂加料完成1触发变量从opc端查找对应的过程变量
					List<ProcessVar> syzjjlwc1MResPVList = (List<ProcessVar>)syzjjlwc1MResMap.get("proVarList");

                    //获取所有助剂加料完成1釜称重变量名
                    String syzjjlwc1FczVarName = Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
                    ProcessVar syzjjlwc1FczPV = OpcUtil.getProVarInListByVarName(syzjjlwc1FczVarName, syzjjlwc1MResPVList);
                    Float syzjjlwc1FczVarValue = syzjjlwc1FczPV.getVarValue();
                    ProcessVar ptnFczPV = processVarService.getPtnValuePV(syzjjlwc1FczVarName,syzjjlwc1FczVarValue+"",syzjjlwc1FczPV);
                    syzjjlwc1MResPVList.add(ptnFczPV);//将重量差对象添加到集合里

                    //获取所有助剂加料完成1时间变量名
                    String syzjjlwc1SjVarName = Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
                    ProcessVar syzjjlwc1SjPV = OpcUtil.getProVarInListByVarName(syzjjlwc1SjVarName, syzjjlwc1MResPVList);
                    String syzjjlwc1SjVarValue = syzjjlwc1SjPV.getUpdateTime();
                    ProcessVar ptnSjPV = processVarService.getPtnValuePV(syzjjlwc1SjVarName,syzjjlwc1SjVarValue+"",syzjjlwc1SjPV);
                    syzjjlwc1MResPVList.add(ptnSjPV);//将时间差对象添加到集合里

                    int i = processVarService.addFromList(syzjjlwc1MResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upSyzjjlwc1TV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSyzjjlwc1TV);
					Map<String, Object> syzjjlwc1MResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据所有助剂加料完成1变量从opc端查找对应的过程变量
					List<ProcessVar> syzjjlwc1MResPVList = (List<ProcessVar>)syzjjlwc1MResMap.get("proVarList");
					int i = processVarService.addFromList(syzjjlwc1MResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.JIA_FEN_LIAO_TI_XING.equals(tvVarNamePre)) {
			TriggerVar JfltxTV=null;
			TriggerVar upJfltxTV = (TriggerVar)paramMap.get("upJfltxTV");
			Integer upFId = upJfltxTV.getFId();//获取反应釜号
			String upRecType = upJfltxTV.getRecType();//获取配方类型
			HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
			HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
			if(TriggerVar.M.equals(upRecType)) {
				String upVarName = upJfltxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				JfltxTV=upJfltxTV;//放在判断上次是否是下降值的外面，是因为不管上次是不是，在下面尿素阀的逻辑里都要用到
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJfltxTV);
					
					Map<String, Object> jfltxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加粉料提醒触发变量从opc端查找对应的过程变量
					List<ProcessVar> jfltxMResPVList = (List<ProcessVar>)jfltxMResMap.get("proVarList");
					int i = processVarService.addFromList(jfltxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				String upVarName = upJfltxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				JfltxTV=upJfltxTV;
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJfltxTV);
					
					Map<String, Object> jfltxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加粉料提醒变量从opc端查找对应的过程变量
					List<ProcessVar> jfltxMResPVList = (List<ProcessVar>)jfltxMResMap.get("proVarList");
					int i = processVarService.addFromList(jfltxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			
			List<TriggerVar> fnsflfTVList = (List<TriggerVar>)paramMap.get("fnsflfTVList");
			TriggerVar fnsflfTV = getTriggerVarInListByFId(fnsflfTVList,Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA,upFId);
			String fnsflfVarName = fnsflfTV.getVarName();
			Float fnsflfVarValue = fnsflfTV.getVarValue();
			if(fnsflfVarValue==TriggerVar.UP) {
				Float fnsflfPreValue = Float.valueOf(preValueFMMap.get(fnsflfVarName).toString());
				if(fnsflfPreValue==TriggerVar.DOWN) {
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(JfltxTV);
					opcTVList.add(fnsflfTV);
					Map<String, Object> fhnsflfMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据釜(号)尿素放料阀变量从opc端查找对应的过程变量
					List<ProcessVar> fhnsflfMResPVList = (List<ProcessVar>)fhnsflfMResMap.get("proVarList");
					int i = processVarService.addFromList(fhnsflfMResPVList);//调用添加过程接口
				}
			}
			else if(fnsflfVarValue==TriggerVar.DOWN) {
				Float fnsflfPreValue = Float.valueOf(preValueFMMap.get(fnsflfVarName).toString());
				if(fnsflfPreValue==TriggerVar.UP) {
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(JfltxTV);
					opcTVList.add(fnsflfTV);
					Map<String, Object> fhnsflfMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据釜(号)尿素放料阀变量从opc端查找对应的过程变量
					List<ProcessVar> fhnsflfMResPVList = (List<ProcessVar>)fhnsflfMResMap.get("proVarList");

					//获取尿素放料阀釜称重变量名
					String nsflfFczVarName = Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FU+Constant.CHENG_ZHONG;
					ProcessVar nsflfFczFczPV = OpcUtil.getProVarInListByVarName(nsflfFczVarName, fhnsflfMResPVList);
					Float nsflfFczFczVarValue = nsflfFczFczPV.getVarValue();
					ProcessVar ptnFczPV = processVarService.getPtnValuePV(nsflfFczVarName,nsflfFczFczVarValue+"",nsflfFczFczPV);
					fhnsflfMResPVList.add(ptnFczPV);//将重量差对象添加到集合里

					//获取尿素放料阀时间变量名
					String nsflfSjVarName = Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN;
					ProcessVar nsflfSjPV = OpcUtil.getProVarInListByVarName(nsflfSjVarName, fhnsflfMResPVList);
					String nsflfSjVarValue = nsflfSjPV.getUpdateTime();
					ProcessVar ptnSjPV = processVarService.getPtnValuePV(nsflfSjVarName,nsflfSjVarValue+"",nsflfSjPV);
					fhnsflfMResPVList.add(ptnSjPV);//将时间差对象添加到集合里

					int i = processVarService.addFromList(fhnsflfMResPVList);//调用添加过程接口
				}
			}
		}
		else if(Constant.JIA_FEN_LIAO_PH_HE_GE.equals(tvVarNamePre)) {
			TriggerVar upJflphhgTV = (TriggerVar)paramMap.get("upJflphhgTV");
			String upRecType = upJflphhgTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJflphhgTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJflphhgTV);
					Map<String, Object> jflphhgMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加粉料PH合格变量从opc端查找对应的过程变量
					List<ProcessVar> jflphhgMResPVList = (List<ProcessVar>)jflphhgMResMap.get("proVarList");
					int i = processVarService.addFromList(jflphhgMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJflphhgTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJflphhgTV);
					Map<String, Object> jflphhgMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据加粉料PH合格变量从opc端查找对应的过程变量
					List<ProcessVar> jflphhgMResPVList = (List<ProcessVar>)jflphhgMResMap.get("proVarList");
					int i = processVarService.addFromList(jflphhgMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.SHENG_WEN_KAI_SHI.equals(tvVarNamePre)) {
			TriggerVar upSwksTV = (TriggerVar)paramMap.get("upSwksTV");
			String upRecType = upSwksTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upSwksTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSwksTV);
					Map<String, Object> swksMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据升温开始变量从opc端查找对应的过程变量
					List<ProcessVar> swksMResPVList = (List<ProcessVar>)swksMResMap.get("proVarList");
					int i = processVarService.addFromList(swksMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upSwksTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSwksTV);
					Map<String, Object> swksMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据升温开始变量从opc端查找对应的过程变量
					List<ProcessVar> swksMResPVList = (List<ProcessVar>)swksMResMap.get("proVarList");
					int i = processVarService.addFromList(swksMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING.equals(tvVarNamePre)) {
			TriggerVar upWd85yectltxTV = (TriggerVar)paramMap.get("upWd85yectltxTV");
			String upRecType = upWd85yectltxTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upWd85yectltxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upWd85yectltxTV);
					Map<String, Object> wd85yectltxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据温度85与二次投料提醒变量从opc端查找对应的过程变量
					List<ProcessVar> wd85yectltxMResPVList = (List<ProcessVar>)wd85yectltxMResMap.get("proVarList");

                    //获取温度85与二次投料提醒时间变量名
                    String wd85yectltxSjVarName = Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
                    ProcessVar wd85yectltxSjPV = OpcUtil.getProVarInListByVarName(wd85yectltxSjVarName, wd85yectltxMResPVList);
                    String wd85yectltxSjVarValue = wd85yectltxSjPV.getUpdateTime();
                    ProcessVar ptnSjPV = processVarService.getPtnValuePV(wd85yectltxSjVarName,wd85yectltxSjVarValue+"",wd85yectltxSjPV);
                    wd85yectltxMResPVList.add(ptnSjPV);//将时间差对象添加到集合里

					int i = processVarService.addFromList(wd85yectltxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upWd85yectltxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upWd85yectltxTV);
					Map<String, Object> wd85yectltxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据温度85与二次投料提醒变量从opc端查找对应的过程变量
					List<ProcessVar> wd85yectltxMResPVList = (List<ProcessVar>)wd85yectltxMResMap.get("proVarList");
					int i = processVarService.addFromList(wd85yectltxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING.equals(tvVarNamePre)) {
			TriggerVar upEczjhcphtxTV = (TriggerVar)paramMap.get("upEczjhcphtxTV");
			String upRecType = upEczjhcphtxTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upEczjhcphtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upEczjhcphtxTV);
					Map<String, Object> eczjhcphtxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据二次助剂后测PH提醒变量从opc端查找对应的过程变量
					List<ProcessVar> eczjhcphtxMResPVList = (List<ProcessVar>)eczjhcphtxMResMap.get("proVarList");
					int i = processVarService.addFromList(eczjhcphtxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upEczjhcphtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upEczjhcphtxTV);
					Map<String, Object> eczjhcphtxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据二次助剂后测PH提醒变量从opc端查找对应的过程变量
					List<ProcessVar> eczjhcphtxMResPVList = (List<ProcessVar>)eczjhcphtxMResMap.get("proVarList");
					int i = processVarService.addFromList(eczjhcphtxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.YUN_XU_ER_CI_JIA_ZHU_JI.equals(tvVarNamePre)) {
			TriggerVar upYxecjzjTV = (TriggerVar)paramMap.get("upYxecjzjTV");
			String upRecType = upYxecjzjTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upYxecjzjTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxecjzjTV);
					Map<String, Object> yxecjzjMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许二次加助剂变量从opc端查找对应的过程变量
					List<ProcessVar> yxecjzjMResPVList = (List<ProcessVar>)yxecjzjMResMap.get("proVarList");
					int i = processVarService.addFromList(yxecjzjMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upYxecjzjTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxecjzjTV);
					Map<String, Object> yxecjzjMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许二次加助剂变量从opc端查找对应的过程变量
					List<ProcessVar> yxecjzjMResPVList = (List<ProcessVar>)yxecjzjMResMap.get("proVarList");
					int i = processVarService.addFromList(yxecjzjMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2.equals(tvVarNamePre)) {
			TriggerVar upSyzjjlwc2TV = (TriggerVar)paramMap.get("upSyzjjlwc2TV");
			String upRecType = upSyzjjlwc2TV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upSyzjjlwc2TV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSyzjjlwc2TV);
					Map<String, Object> syzjjlwc2MResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据所有助剂加料完成2变量从opc端查找对应的过程变量
					List<ProcessVar> syzjjlwc2MResPVList = (List<ProcessVar>)syzjjlwc2MResMap.get("proVarList");

                    //获取所有助剂加料完成2釜称重变量名
                    String syzjjlwc2FczVarName = Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
                    ProcessVar syzjjlwc2FczPV = OpcUtil.getProVarInListByVarName(syzjjlwc2FczVarName, syzjjlwc2MResPVList);
                    Float syzjjlwc2FczVarValue = syzjjlwc2FczPV.getVarValue();
                    ProcessVar ptnFczPV = processVarService.getPtnValuePV(syzjjlwc2FczVarName,syzjjlwc2FczVarValue+"",syzjjlwc2FczPV);
                    syzjjlwc2MResPVList.add(ptnFczPV);//将重量差对象添加到集合里
                    //获取所有助剂加料完成2时间变量名
                    String syzjjlwc2SjVarName = Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
                    ProcessVar syzjjlwc2SjPV = OpcUtil.getProVarInListByVarName(syzjjlwc2SjVarName, syzjjlwc2MResPVList);
                    String syzjjlwc2SjVarValue = syzjjlwc2SjPV.getUpdateTime();
                    ProcessVar ptnSjPV = processVarService.getPtnValuePV(syzjjlwc2SjVarName,syzjjlwc2SjVarValue+"",syzjjlwc2SjPV);
                    syzjjlwc2MResPVList.add(ptnSjPV);//将时间差对象添加到集合里
					int i = processVarService.addFromList(syzjjlwc2MResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upSyzjjlwc2TV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSyzjjlwc2TV);
					Map<String, Object> syzjjlwc2MResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据所有助剂加料完成2变量从opc端查找对应的过程变量
					List<ProcessVar> syzjjlwc2MResPVList = (List<ProcessVar>)syzjjlwc2MResMap.get("proVarList");
					int i = processVarService.addFromList(syzjjlwc2MResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.SHENG_WEN_WAN_CHENG.equals(tvVarNamePre)) {
			TriggerVar upSwwcTV = (TriggerVar)paramMap.get("upSwwcTV");
			String upRecType = upSwwcTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upSwwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSwwcTV);
					Map<String, Object> swwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据升温完成变量从opc端查找对应的过程变量
					List<ProcessVar> swwcMResPVList = (List<ProcessVar>)swwcMResMap.get("proVarList");
                    //获取所有助剂加料完成2时间变量名
                    String swwcSjVarName = Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
                    ProcessVar swwcSjPV = OpcUtil.getProVarInListByVarName(swwcSjVarName, swwcMResPVList);
                    String swwcSjVarValue = swwcSjPV.getUpdateTime();
                    ProcessVar ptnSjPV = processVarService.getPtnValuePV(swwcSjVarName,swwcSjVarValue+"",swwcSjPV);
                    swwcMResPVList.add(ptnSjPV);//将时间差对象添加到集合里
					int i = processVarService.addFromList(swwcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upSwwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upSwwcTV);
					Map<String, Object> swwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据升温完成变量从opc端查找对应的过程变量
					List<ProcessVar> swwcMResPVList = (List<ProcessVar>)swwcMResMap.get("proVarList");
					int i = processVarService.addFromList(swwcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if((Constant.WEN_DU_98_PH+Constant.HE_GE).equals(tvVarNamePre)) {
			TriggerVar upWd98phhgTV = (TriggerVar)paramMap.get("upWd98phhgTV");
			String upRecType = upWd98phhgTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upWd98phhgTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upWd98phhgTV);
					Map<String, Object> wd98phhgMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据温度98PH合格变量从opc端查找对应的过程变量
					List<ProcessVar> wd98phhgMResPVList = (List<ProcessVar>)wd98phhgMResMap.get("proVarList");
					int i = processVarService.addFromList(wd98phhgMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upWd98phhgTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upWd98phhgTV);
					Map<String, Object> wd98phhgMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据温度98PH合格变量从opc端查找对应的过程变量
					List<ProcessVar> wd98phhgMResPVList = (List<ProcessVar>)wd98phhgMResMap.get("proVarList");
					int i = processVarService.addFromList(wd98phhgMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING.equals(tvVarNamePre)) {
			TriggerVar downClbswdtxTV = (TriggerVar)paramMap.get("downClbswdtxTV");
			String downRecType = downClbswdtxTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(downRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String downVarName = downClbswdtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downClbswdtxTV);
					Map<String, Object> clbswdtxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据测量冰水雾点提醒变量从opc端查找对应的过程变量
					List<ProcessVar> clbswdtxMResPVList = (List<ProcessVar>)clbswdtxMResMap.get("proVarList");
					int i = processVarService.addFromList(clbswdtxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(downRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String downVarName = downClbswdtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downClbswdtxTV);
					Map<String, Object> clbswdtxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据测量冰水雾点提醒变量从opc端查找对应的过程变量
					List<ProcessVar> clbswdtxMResPVList = (List<ProcessVar>)clbswdtxMResMap.get("proVarList");
					int i = processVarService.addFromList(clbswdtxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.JU_HE_ZHONG_DIAN.equals(tvVarNamePre)) {
			TriggerVar upJhzdTV = (TriggerVar)paramMap.get("upJhzdTV");
			String upRecType = upJhzdTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJhzdTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJhzdTV);
					Map<String, Object> jhzdMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据聚合终点变量从opc端查找对应的过程变量
					List<ProcessVar> jhzdMResPVList = (List<ProcessVar>)jhzdMResMap.get("proVarList");
					int i = processVarService.addFromList(jhzdMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJhzdTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJhzdTV);
					Map<String, Object> jhzdMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据聚合终点变量从opc端查找对应的过程变量
					List<ProcessVar> jhzdMResPVList = (List<ProcessVar>)jhzdMResMap.get("proVarList");
					int i = processVarService.addFromList(jhzdMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.CE_SHUI_SHU_TI_XING.equals(tvVarNamePre)) {
			TriggerVar downCsstxTV = (TriggerVar)paramMap.get("downCsstxTV");
			String downRecType = downCsstxTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(downRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String downVarName = downCsstxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downCsstxTV);
					Map<String, Object> csstxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据测水数提醒变量从opc端查找对应的过程变量
					List<ProcessVar> csstxMResPVList = (List<ProcessVar>)csstxMResMap.get("proVarList");
					int i = processVarService.addFromList(csstxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(downRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String downVarName = downCsstxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downCsstxTV);
					Map<String, Object> csstxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据测水数提醒变量从opc端查找对应的过程变量
					List<ProcessVar> csstxMResPVList = (List<ProcessVar>)csstxMResMap.get("proVarList");
					int i = processVarService.addFromList(csstxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.JIANG_WEN_WAN_CHENG.equals(tvVarNamePre)) {
			List<Integer> jwwcFIdList=(List<Integer>)paramMap.get("jwwcFIdList");
			TriggerVar upJwwcTV = (TriggerVar)paramMap.get("upJwwcTV");
			Integer upFId = upJwwcTV.getFId();
			String upRecType = upJwwcTV.getRecType();//获取配方类型
			if (TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upJwwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if (preValue == TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					jwwcFIdList.add(upFId);
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJwwcTV);
					Map<String, Object> jwwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据降温完成变量从opc端查找对应的过程变量
					List<ProcessVar> jwwcMResPVList = (List<ProcessVar>)jwwcMResMap.get("proVarList");
					
					//获取降温完成时间变量名
					String jwwcSjVarName = Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
					ProcessVar jwwcSjPV = OpcUtil.getProVarInListByVarName(jwwcSjVarName, jwwcMResPVList);
					String jwwcSjVarValue = jwwcSjPV.getUpdateTime();
					ProcessVar ptnSjPV = processVarService.getPtnValuePV(jwwcSjVarName,jwwcSjVarValue+"",jwwcSjPV);
					jwwcMResPVList.add(ptnSjPV);//将时间差对象添加到集合里

					int i = processVarService.addFromList(jwwcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
					
				}
			} 
			else if (TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upJwwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if (preValue == TriggerVar.DOWN) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					jwwcFIdList.add(upFId);
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upJwwcTV);
					Map<String, Object> jwwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据降温完成变量从opc端查找对应的过程变量
					List<ProcessVar> jwwcMResPVList = (List<ProcessVar>)jwwcMResMap.get("proVarList");
					int i = processVarService.addFromList(jwwcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.ZHONG_JIAN_SHUI_PH_TI_XING.equals(tvVarNamePre)) {
			TriggerVar downZjsphtxTV = (TriggerVar)paramMap.get("downZjsphtxTV");
			String downRecType = downZjsphtxTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(downRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String downVarName = downZjsphtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downZjsphtxTV);
					Map<String, Object> zjsphtxMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据终检水PH提醒变量从opc端查找对应的过程变量
					List<ProcessVar> zjsphtxMResPVList = (List<ProcessVar>)zjsphtxMResMap.get("proVarList");
					int i = processVarService.addFromList(zjsphtxMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(downRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String downVarName = downZjsphtxTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(downVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(downZjsphtxTV);
					Map<String, Object> zjsphtxUResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据终检水PH提醒变量从opc端查找对应的过程变量
					List<ProcessVar> zjsphtxUResPVList = (List<ProcessVar>)zjsphtxUResMap.get("proVarList");
					int i = processVarService.addFromList(zjsphtxUResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.YUN_XU_KAI_SHI_PAI_JIAO.equals(tvVarNamePre)) {
			TriggerVar upYxkspjTV = (TriggerVar)paramMap.get("upYxkspjTV");
			String upRecType = upYxkspjTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upYxkspjTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxkspjTV);
					Map<String, Object> yxkspjMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许开始排胶变量从opc端查找对应的过程变量
					List<ProcessVar> yxkspjMResPVList = (List<ProcessVar>)yxkspjMResMap.get("proVarList");
					int i = processVarService.addFromList(yxkspjMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upYxkspjTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upYxkspjTV);
					Map<String, Object> yxkspjUResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据允许开始排胶变量从opc端查找对应的过程变量
					List<ProcessVar> yxkspjUResPVList = (List<ProcessVar>)yxkspjUResMap.get("proVarList");
					int i = processVarService.addFromList(yxkspjUResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
		else if(Constant.PAI_JIAO_WAN_CHENG.equals(tvVarNamePre)) {
			TriggerVar upPjwcTV = (TriggerVar)paramMap.get("upPjwcTV");
			String upRecType = upPjwcTV.getRecType();//获取配方类型
			if(TriggerVar.M.equals(upRecType)) {
				HashMap<String, Object> preValueFMMap = (HashMap<String,Object>)paramMap.get("preValueFMMap");
				String upVarName = upPjwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFMMap.get(upVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upPjwcTV);
					Map<String, Object> pjwcMResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据排胶完成变量从opc端查找对应的过程变量
					List<ProcessVar> pjwcMResPVList = (List<ProcessVar>)pjwcMResMap.get("proVarList");
					int i = processVarService.addFromList(pjwcMResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
			else if(TriggerVar.U.equals(upRecType)) {
				HashMap<String, Object> preValueFUMap = (HashMap<String,Object>)paramMap.get("preValueFUMap");
				String upVarName = upPjwcTV.getVarName();
				Float preValue = Float.valueOf(preValueFUMap.get(upVarName).toString());
				if(preValue==TriggerVar.UP) {//当上一次的变量值为0，说明这次刚上升，变量刚从0变为1，就记录一下反应釜id
					List<TriggerVar> opcTVList=new ArrayList<TriggerVar>();
					opcTVList.add(upPjwcTV);
					Map<String, Object> pjwcUResMap = OpcUtil.readerOpcProVarByTVList(opcTVList);//根据排胶完成变量从opc端查找对应的过程变量
					List<ProcessVar> pjwcUResPVList = (List<ProcessVar>)pjwcUResMap.get("proVarList");
					int i = processVarService.addFromList(pjwcUResPVList);//调用添加过程接口
					System.out.println("添加"+i);
				}
			}
		}
	}

	/**
	 * 根据不同变量给触发器变量集合分组(备料开始、甲醛备料开始这些变量组)
	 * @param triggerVarList
	 * @return
	 */
	private Map<String, List<TriggerVar>> getTriVarListGroupMap(List<TriggerVar> triggerVarList) {

		System.out.println("getTriVarListGroupMap方法:");
		for (TriggerVar triggerVar : triggerVarList) {
				System.out.println("分组方法"+triggerVar.toString());
		}
		Map<String, List<TriggerVar>> tvGroupMap=new HashMap<String, List<TriggerVar>>();
		List<TriggerVar> blksTVList=new ArrayList<TriggerVar>();//备料开始新集合,用来存放对象
		List<TriggerVar> fyjsTVList=new ArrayList<TriggerVar>();//反应结束新集合,用来存放对象
		List<TriggerVar> jqblksTVList=new ArrayList<TriggerVar>();//甲醛备料开始新集合,用来存放对象
		List<TriggerVar> jqflwcTVList=new ArrayList<TriggerVar>();//甲醛放料完成新集合,用来存放对象
		List<TriggerVar> jjphzzcTVList=new ArrayList<TriggerVar>();//加碱PH值正常
		List<TriggerVar> yxycjzjTVList=new ArrayList<TriggerVar>();//允许一次加助剂
		List<TriggerVar> syzjjlwc1TVList=new ArrayList<TriggerVar>();//所有助剂加料完成1
		List<TriggerVar> jfltxTVList=new ArrayList<TriggerVar>();//加粉料提醒
		List<TriggerVar> fhnsflfTVList=new ArrayList<TriggerVar>();//釜号尿素放料阀
		List<TriggerVar> jflphhgTVList=new ArrayList<TriggerVar>();//加粉料PH合格
		List<TriggerVar> swksTVList=new ArrayList<TriggerVar>();//升温开始
		List<TriggerVar> wd85yectltxTVList=new ArrayList<TriggerVar>();//温度85与二次投料提醒
		List<TriggerVar> eczjhcphtxTVList=new ArrayList<TriggerVar>();//二次助剂后测PH提醒
		List<TriggerVar> yxecjzjTVList=new ArrayList<TriggerVar>();//允许二次加助剂
		List<TriggerVar> syzjjlwc2TVList=new ArrayList<TriggerVar>();//所有助剂加料完成2
		List<TriggerVar> swwcTVList=new ArrayList<TriggerVar>();//升温完成
		List<TriggerVar> wd98phhgTVList=new ArrayList<TriggerVar>();//温度98PH合格
		List<TriggerVar> clbswdtxTVList=new ArrayList<TriggerVar>();//测量冰水雾点提醒
		List<TriggerVar> csstxTVList=new ArrayList<TriggerVar>();//测水数提醒
		List<TriggerVar> jhzdTVList=new ArrayList<TriggerVar>();//聚合终点
		List<TriggerVar> jwwcTVList=new ArrayList<TriggerVar>();//降温完成新集合,用来存放对象

		for (TriggerVar triggerVar : triggerVarList) {//遍历全部变量对象
			String varName = triggerVar.getVarName();//获取变量名称
			String recType = triggerVar.getRecType();//获取配方类型
			
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
			
			if((Constant.BEI_LIAO_KAI_SHI+"_"+fyfh+"_AV").equals(varName)) {//备料开始
				blksTVList.add(triggerVar);
			}
			else if((Constant.FAN_YING_JIE_SHU+"_"+fyfh+"_AV").equals(varName)) {//反应结束
				fyjsTVList.add(triggerVar);
			}
			else if((Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+"_"+fyfh+"_AV").equals(varName)) {//甲醛备料开始
				jqblksTVList.add(triggerVar);
			}
			else if((Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+"_"+fyfh+"_AV").equals(varName)){//甲醛放料完成
				jqflwcTVList.add(triggerVar);
			}
			else if((Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+"_"+fyfh+"_AV").equals(varName)){//加碱PH值正常
				jjphzzcTVList.add(triggerVar);
			}
			else if((Constant.YUN_XU_YI_CI_JIA_ZHU_JI+"_"+fyfh+"_AV").equals(varName)){//允许一次加助剂
				yxycjzjTVList.add(triggerVar);
			}
			else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+"_"+fyfh+"_AV").equals(varName)){//所有助剂加料完成1
				syzjjlwc1TVList.add(triggerVar);
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+"_"+fyfh+"_AV").equals(varName)){//加粉料提醒
				jfltxTVList.add(triggerVar);
			}
			else if((Constant.FU+fId+Constant.NIAO_SU_FANG_LIAO_FA+"_AV").equals(varName)){//釜号尿素放料阀
				fhnsflfTVList.add(triggerVar);
			}
			else if((Constant.JIA_FEN_LIAO_PH_HE_GE+"_"+fyfh+"_AV").equals(varName)){//加粉料PH合格
				jflphhgTVList.add(triggerVar);
			}
			else if((Constant.SHENG_WEN_KAI_SHI+"_"+fyfh+"_AV").equals(varName)){//升温开始
				swksTVList.add(triggerVar);
			}
			else if((Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+"_"+fyfh+"_AV").equals(varName)){//温度85与二次投料提醒
				wd85yectltxTVList.add(triggerVar);
			}
			else if((Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING+"_"+fyfh+"_AV").equals(varName)){//二次助剂后测PH提醒
				eczjhcphtxTVList.add(triggerVar);
			}
			else if((Constant.YUN_XU_ER_CI_JIA_ZHU_JI+"_"+fyfh+"_AV").equals(varName)){//允许二次加助剂
				yxecjzjTVList.add(triggerVar);
			}
			else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+"_"+fyfh+"_AV").equals(varName)){//所有助剂加料完成2
				syzjjlwc2TVList.add(triggerVar);
			}
			else if((Constant.SHENG_WEN_WAN_CHENG+"_"+fyfh+"_AV").equals(varName)){//升温完成
				swwcTVList.add(triggerVar);
			}
			else if((Constant.WEN_DU_98_PH+Constant.HE_GE+"_"+fyfh+"_AV").equals(varName)){//温度98PH合格
				wd98phhgTVList.add(triggerVar);
			}
			else if((Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING+"_"+fyfh+"_AV").equals(varName)){//测量冰水雾点提醒
				clbswdtxTVList.add(triggerVar);
			}
			else if((Constant.CE_SHUI_SHU_TI_XING+"_"+fyfh+"_AV").equals(varName)){//测水数提醒
				csstxTVList.add(triggerVar);
			}
			else if((Constant.JU_HE_ZHONG_DIAN+"_"+fyfh+"_AV").equals(varName)){//聚合终点
				jhzdTVList.add(triggerVar);
			}
			else if((Constant.JIANG_WEN_WAN_CHENG+"_"+fyfh+"_AV").equals(varName)) {//降温完成
				jwwcTVList.add(triggerVar);
			}
		}
		
		tvGroupMap.put(Constant.BEI_LIAO_KAI_SHI, blksTVList);//备料开始
		tvGroupMap.put(Constant.FAN_YING_JIE_SHU, fyjsTVList);//反应结束
		tvGroupMap.put(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI, jqblksTVList);//甲醛备料开始
		tvGroupMap.put(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG,jqflwcTVList);//甲醛放料完成
		tvGroupMap.put(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG,jjphzzcTVList);//加碱PH值正常
		tvGroupMap.put(Constant.YUN_XU_YI_CI_JIA_ZHU_JI,yxycjzjTVList);//允许一次加助剂
		tvGroupMap.put(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1,syzjjlwc1TVList);//所有助剂加料完成1
		tvGroupMap.put(Constant.JIA_FEN_LIAO_TI_XING,jfltxTVList);//加粉料提醒
		tvGroupMap.put(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA,fhnsflfTVList);//釜号尿素放料阀
		tvGroupMap.put(Constant.JIA_FEN_LIAO_PH_HE_GE,jflphhgTVList);//加粉料PH合格
		tvGroupMap.put(Constant.SHENG_WEN_KAI_SHI,swksTVList);//升温开始
		tvGroupMap.put(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING,wd85yectltxTVList);//温度85与二次投料提醒
		tvGroupMap.put(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING,eczjhcphtxTVList);//二次助剂后测PH提醒
		tvGroupMap.put(Constant.YUN_XU_ER_CI_JIA_ZHU_JI,yxecjzjTVList);//允许二次加助剂
		tvGroupMap.put(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2,syzjjlwc2TVList);//所有助剂加料完成2
		tvGroupMap.put(Constant.SHENG_WEN_WAN_CHENG,swwcTVList);//升温完成
		tvGroupMap.put(Constant.WEN_DU_98_PH+Constant.HE_GE,wd98phhgTVList);//温度98PH合格
		tvGroupMap.put(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING,clbswdtxTVList);//测量冰水雾点提醒
		tvGroupMap.put(Constant.CE_SHUI_SHU_TI_XING,csstxTVList);//测水数提醒
		tvGroupMap.put(Constant.JU_HE_ZHONG_DIAN,jhzdTVList);//聚合终点
		tvGroupMap.put(Constant.JIANG_WEN_WAN_CHENG, jwwcTVList);

		return tvGroupMap;
	}

	@RequestMapping(value = "/addTriggerVarFromOpc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTriggerVarFromOpc(@RequestBody String bodyStr){
		System.out.println(bodyStr);
		Map<String,Object> json=new HashMap<String, Object>();
		//List<TriggerVar> triggerVarList = JSON.parseArray(bodyStr, TriggerVar.class);
		List<TriggerVar> triggerVarList=com.alibaba.fastjson.JSONArray.parseArray(bodyStr,TriggerVar.class);
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

	@RequestMapping(value = "/getOVTListByVarNames", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOVTListByVarNames(String varNames) {

		Map<String,Object> json=new HashMap<String, Object>();
		
		System.out.println("varNames==="+varNames);
		List<OpcVarTest> opcVarTestList = opcVarTestService.getListByVarNames(varNames);
		System.out.println("opcVarTestListSize==="+opcVarTestList.size());
		
		if (opcVarTestList.size()>0){
			json.put("status","ok");
			json.put("opcVarTestList",opcVarTestList);
		}
		else {
			json.put("status","no");
			json.put("info","无变量");
		}
		
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
				if(TriggerVar.M.equals(recType)||TriggerVar.MU.equals(recType)) {
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
		if(triggerVarList!=null) {
			for (TriggerVar triggerVar : triggerVarList) {
				Float varValue = triggerVar.getVarValue();
				if(varValue==flag) {
					upDownVarValueTVList.add(triggerVar);
				}
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
	
	/**
	 * 根据反应釜id从触发量组集合里查找某个釜的触发量
	 * @param triggerVarList
	 * @param groupName
	 */
	private TriggerVar getTriggerVarInListByFId(List<TriggerVar> triggerVarList,String groupName,Integer fId) {
		TriggerVar triggerVar=null;
		if((Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA).equals(groupName)) {
			for (TriggerVar triggerVarItem : triggerVarList) {
				if((Constant.FU+fId+Constant.NIAO_SU_FANG_LIAO_FA+"_AV").equals(triggerVarItem.getVarName())) {
					triggerVar=triggerVarItem;
					break;
				}
			}
		}
		return triggerVar;
	}
	
	private void totalZJJLGChengZhongSum(List<ProcessVar> processVarList) {
		ProcessVar sumPV=null;
		float sumVarValue=0;
		List<ProcessVar> zjjlgPVList=new ArrayList<ProcessVar>();
		List<Integer> removeIndexList=new ArrayList<Integer>();
		for (int i = 0; i < processVarList.size(); i++) {
			ProcessVar processVar = processVarList.get(i);
			String varName = processVar.getVarName();
			if(varName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)) {
				zjjlgPVList.add(processVar);
				removeIndexList.add(i);
			}
		}
		
		for (int i = removeIndexList.size()-1; i >=0 ; i--) {
			int removeIndex = removeIndexList.get(i);
			processVarList.remove(removeIndex);
		}
		
		for (int i = 0; i < zjjlgPVList.size(); i++) {
			ProcessVar zjjlgPV = zjjlgPVList.get(i);
			if(i==0)
				sumPV=zjjlgPV;
			sumVarValue+=zjjlgPV.getVarValue();
		}
		
		String sumVarName=null;
		if(zjjlgPVList.size()==2)
			sumVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+"-"+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG;
		else if(zjjlgPVList.size()==3)
			sumVarName=Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG3+"-"+Constant.BSF_ZJJLG5+Constant.CHENG_ZHONG;
		
		sumPV.setVarName(sumVarName);
		sumPV.setVarValue(sumVarValue);
		
		processVarList.add(sumPV);
	}
}

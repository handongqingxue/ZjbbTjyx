package com.uWinOPCTjyx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javafish.clients.opc.component.OpcItem;
import org.apache.poi.hssf.record.formula.functions.T;
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
	private TriggerVarService triggerVarService;
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

		Map<String,Object> json=new HashMap<String, Object>();
		//1号釜
		Map<String,Object> f1MMap=new HashMap<String, Object>();
		Map<String,Object> f1UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f1Map=new HashMap<String, Object>();
		f1Map.put("run",false);
		f1Map.put("f1MMap",f1MMap);
		f1Map.put("f1UMap",f1UMap);

		//2号釜
		Map<String,Object> f2MMap=new HashMap<String, Object>();
		Map<String,Object> f2UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f2Map=new HashMap<String, Object>();
		f2Map.put("run",false);
		f2Map.put("f2MMap",f1MMap);
		f2Map.put("f2UMap",f1UMap);


		//3号釜
		Map<String,Object> f3MMap=new HashMap<String, Object>();
		Map<String,Object> f3UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f3Map=new HashMap<String, Object>();
		f3Map.put("run",false);
		f3Map.put("f3MMap",f1MMap);
		f3Map.put("f3UMap",f1UMap);

		//4号釜
		Map<String,Object> f4MMap=new HashMap<String, Object>();
		Map<String,Object> f4UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f4Map=new HashMap<String, Object>();
		f4Map.put("run",false);
		f4Map.put("f4MMap",f1MMap);
		f4Map.put("f4UMap",f1UMap);

		//5号釜
		Map<String,Object> f5MMap=new HashMap<String, Object>();
		Map<String,Object> f5UMap=new HashMap<String, Object>();
		//初始化run标识位、M类哈希表、U类哈希表
		f5Map=new HashMap<String, Object>();
		f5Map.put("run",false);
		f5Map.put("f5MMap",f1MMap);
		f5Map.put("f5UMap",f1UMap);
		return json;
	}
	
	@RequestMapping(value = "/keepWatchOnTriggerVar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> keepWatchOnTriggerVar() {

		Map<String,Object> json=new HashMap<String, Object>();
		
		boolean run = Boolean.parseBoolean(f1Map.get("run").toString());
		System.out.println("run==="+run);
		
		return json;
	}

	@RequestMapping(value = "/addTrigger", method = RequestMethod.POST)
	@ResponseBody
	public void addTrigger(@RequestBody String list){
		System.out.println("进来");

		System.out.println(list);
	}
	
}

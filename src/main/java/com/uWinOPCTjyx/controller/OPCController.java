package com.uWinOPCTjyx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	private ZhiLiangZhongJianBzzUService zhiLiangZhongJianBzzUService;
	@Autowired
	private PiCiMService piCiMService;
	@Autowired
	private PiCiUService piCiUService;
	@Autowired
	private OpcBianLiangService opcBianLiangService;
	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/opcu")
	public String goOpcU(HttpServletRequest request) {
		
		//localhost:8080/UWinOPCTjyx/opc/test
		
		return MODULE_NAME+"/opcu";
	}

	@RequestMapping(value="/opcm")
	public String goOpcM(HttpServletRequest request, Model model) {

		//localhost:8080/UWinOPCTjyx/opc/test
//		List<ZhiLiangZhongJianBzzU> list = zhiLiangZhongJianBzzUService.getList();
//		System.out.println("size==="+list.size());
		return MODULE_NAME+"/opcm";
	}

	@RequestMapping(value = "/editOpcBianLiangByReqBody", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editOpcBianLiangByReqBody(@RequestBody String bodyStr){
		
		Map<String,Object> json=new HashMap<String, Object>();
		
		System.out.println("bodyStr==="+bodyStr);
		List<OpcBianLiang> OpcBianLiangList = JSON.parseArray(bodyStr, OpcBianLiang.class);
		try {
			int count = opcBianLiangService.editFromList(OpcBianLiangList);
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

	@RequestMapping(value = "/keepWatchOnOpcBianLiang", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> keepWatchOnOpcBianLiang() {

		Map<String,Object> json=new HashMap<String, Object>();

		List<OpcBianLiang> blksMOBLList=new ArrayList<OpcBianLiang>();
		List<OpcBianLiang> blksUOBLList=new ArrayList<OpcBianLiang>();
		List<OpcBianLiang> blksOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.BEI_LIAO_KAI_SHI_TEXT);
		for (OpcBianLiang blksOBL : blksOBLList) {
			Integer lx = blksOBL.getLx();
			if(OpcBianLiang.LX_M==lx) {
				blksMOBLList.add(blksOBL);
			}
			else if(OpcBianLiang.LX_U==lx) {
				blksUOBLList.add(blksOBL);
			}
		}
		
		int c=piCiMService.addByBlksOBLList(blksMOBLList);
		
		return json;
	}

	@RequestMapping("/addPiCiU")
	@ResponseBody
	public Map<String, Object> addPiCiU(PiCiU piCiU){
		System.out.println(piCiU+"-");
		Map<String,Object> json=new HashMap<String, Object>();
		try {
			int count = piCiUService.add(piCiU);
			if (count>0){
				json.put("message","ok");
				json.put("info","添加成功");
			}
			else {
				json.put("message","no");
				json.put("info","添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return json;
		}
	}
}

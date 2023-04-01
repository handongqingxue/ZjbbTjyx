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
	private PiCiJiLuMService piCiJiLuMService;
	@Autowired
	private JiLuShiJianMService jiLuShiJianMService;
	@Autowired
	private JieDuanMService jieDuanMService;
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
		
		//M类
		Map<String,Integer> jlsjIdMMap=jiLuShiJianMService.getIdMap();
		Map<String,Integer> jieDuanIdMMap=jieDuanMService.getIdMap();
		
		//U类

		//检测备料开始上升沿
		List<OpcBianLiang> blksMOBLList=new ArrayList<OpcBianLiang>();
		List<OpcBianLiang> blksUOBLList=new ArrayList<OpcBianLiang>();
		List<String> blksMcList=new ArrayList<String>();
		List<String> blksFyfhList=new ArrayList<String>();
		List<OpcBianLiang> blksOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.BEI_LIAO_KAI_SHI_TEXT);
		for (OpcBianLiang blksOBL : blksOBLList) {
			Integer lx = blksOBL.getLx();
			if(OpcBianLiang.LX_M==lx) {
				blksMOBLList.add(blksOBL);
			}
			else if(OpcBianLiang.LX_U==lx) {
				blksUOBLList.add(blksOBL);
			}
			
			String mc = blksOBL.getMc();
			String fyfh = blksOBL.getFyfh();
			blksMcList.add(mc);
			blksFyfhList.add(fyfh);
		}
		
		if(blksMOBLList.size()>0) {
			//M类
			piCiMService.addByBlksOBLList(blksMOBLList);
			List<Integer> blksPcIdMList=piCiMService.getIdListByFyfhList(blksFyfhList);//根据备料开始变量里的反应釜号获取批次id列表
			Integer pcJlsjId = Integer.valueOf(jlsjIdMMap.get(JiLuShiJianM.PI_CI_TEXT).toString());//获取批次记录事件id
			piCiJiLuMService.addPcgcFromPcIdList(blksPcIdMList,pcJlsjId);//添加批次过程记录
		}
		if(blksUOBLList.size()>0) {
			//U类
			piCiUService.addByBlksOBLList(blksUOBLList);
		}
		if(blksMcList.size()>0)
			opcBianLiangService.updateSzyssByMcList(OpcBianLiang.YSS,blksMcList);
		

		//检测甲醛备料开始上升沿
		List<OpcBianLiang> jqblksMOBLList=new ArrayList<OpcBianLiang>();
		List<OpcBianLiang> jqblksUOBLList=new ArrayList<OpcBianLiang>();
		List<String> jqblksMcList=new ArrayList<String>();
		List<String> jqblksFyfhList=new ArrayList<String>();
		List<OpcBianLiang> jqblksOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI_TEXT);
		for (OpcBianLiang jqblksOBL : jqblksOBLList) {
			Integer lx = jqblksOBL.getLx();
			if(OpcBianLiang.LX_M==lx) {
				jqblksMOBLList.add(jqblksOBL);
			}
			else if(OpcBianLiang.LX_U==lx) {
				jqblksUOBLList.add(jqblksOBL);
			}
			
			String mc = jqblksOBL.getMc();
			String fyfh = jqblksOBL.getFyfh();
			jqblksMcList.add(mc);
			jqblksFyfhList.add(fyfh);//添加甲醛备料开始反应釜号
		}
		
		if(jqblksMOBLList.size()>0) {
			//M类
			List<PiCiM> jqblksPcMList=piCiMService.getListByFyfhList(jqblksFyfhList);//根据甲醛备料开始变量里的反应釜号获取批次列表
			
			//添加与M类批次相关的加甲醛时间差阶段批次记录
			Integer sjcJlsjId = Integer.valueOf(jlsjIdMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT).toString());//获取时间差记录事件id
			Integer jjqJieDuanId = Integer.valueOf(jieDuanIdMMap.get(JieDuanM.JIA_JIA_QUAN_TEXT).toString());
			piCiJiLuMService.addJdgcFromPcList(jqblksPcMList,sjcJlsjId,JiLuShiJianM.SHI_JIAN_CHA_TEXT,jjqJieDuanId);//添加加甲醛阶段过程记录
		}
		if(jqblksUOBLList.size()>0) {
			//U类
		}
		
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

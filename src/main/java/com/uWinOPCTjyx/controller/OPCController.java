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
	private PiCiJiLuUService piCiJiLuUService;
	@Autowired
	private JiLuShiJianMService jiLuShiJianMService;
	@Autowired
	private JieDuanMService jieDuanMService;
	@Autowired
	private JieDuanUService jieDuanUService;
	@Autowired
	private OpcBianLiangService opcBianLiangService;
	@Autowired
	private JiLuShiJianUService jiLuShiJianUService;
	@Autowired
	private CanShuMService canShuMService;
	private List<Map<String, Object>> opcBLScszList=new ArrayList<Map<String, Object>>();

	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/opcu")
	public String goOpcU(HttpServletRequest request) {
		
		//localhost:8080/UWinOPCTjyx/opc/test
		
		return MODULE_NAME+"/opcu";
	}

	@RequestMapping(value="/opcm")
	public String goOpcM(HttpServletRequest request, Model model) {
		//访问opcm的web页面
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

		Map<String,Object> json=new HashMap<String, Object>();//return
		
		//M类
		Map<String,Map<String, Object>> jlsjMMap=jiLuShiJianMService.getMap();//获取名称、id键值对，下面的逻辑里关联id时要用
		Map<String,Map<String, Object>> jieDuanMMap=jieDuanMService.getMap();
		Map<String,Map<String, Object>> canShuMMap=canShuMService.getMap();
		
		//U类
		Map<String, Map<String, Object>> jlsjUMap = jiLuShiJianUService.getMap();
		Map<String, Integer> jieDuanIdUMap = jieDuanUService.getIdMap();

		//检测备料开始上升沿
		List<OpcBianLiang> blksMOBLList=new ArrayList<OpcBianLiang>();//备料开始M类opc变量集合
		List<OpcBianLiang> blksUOBLList=new ArrayList<OpcBianLiang>();//备料开始U类opc变量集合
		List<String> blksMcList=new ArrayList<String>();//备料开始名称集合(不管是M类还是U类都放进去)
		List<String> blksFyfhList=new ArrayList<String>();//备料开始反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> blksOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.BEI_LIAO_KAI_SHI_TEXT);//获取备料开始上升沿集合
		for (OpcBianLiang blksOBL : blksOBLList) {
			String mc = blksOBL.getMc();
			for (Map<String, Object> opcBLScszMap : opcBLScszList) {//遍历opc变量上次数值集合
				String scmc = opcBLScszMap.get("mc").toString();
				Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
				if(mc.equals(scmc)&&!scsz) {//若上次数值的集合里的某个变量名称是这次的变量名称，就根据上次数值判断。若上次数值未上升置1，而这次数值为1，说明这次数值是刚上升的，备料刚开始,符合条件才往集合里存
					Integer lx = blksOBL.getLx();
					if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
						blksMOBLList.add(blksOBL);
					}
					else if(OpcBianLiang.LX_U==lx) {
						blksUOBLList.add(blksOBL);
					}
					
					String fyfh = blksOBL.getFyfh();
					blksMcList.add(mc);//不管是M类还是U类，都往集合里放
					blksFyfhList.add(fyfh);
				}
			}
		}
		
		if(blksMOBLList.size()>0) {//若opc变量表M类里有上升沿数据，就开始创建M类批次
			//M类
			piCiMService.addByBlksOBLList(blksMOBLList);
			List<Integer> blksPcIdMList=piCiMService.getIdListByFyfhList(blksFyfhList);//根据备料开始变量里的反应釜号获取批次idM类列表
			Map<String, Object> pcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.PI_CI_TEXT);//获取批次记录事件信息
			piCiJiLuMService.addPcgcFromPcIdList(blksPcIdMList,pcJlsjMap);//添加批次过程记录
		}
		if(blksUOBLList.size()>0) {
			//U类
			piCiUService.addByBlksOBLList(blksUOBLList);
			List<Integer> blksPcIdUList=piCiUService.getIdListByFyfhList(blksFyfhList);//根据备料开始变量里的反应釜号获取批次id列表
			Map<String, Object> pcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.PI_CI_TEXT);//获取批次记录事件id
			piCiJiLuUService.addPcgcFromPcIdList(blksPcIdUList,pcJlsjMap);//添加批次过程记录

		}
		if(blksMcList.size()>0) {
			for (String blksMc : blksMcList) {//循环备料开始名称集合
				addOpcBLScszInList(OpcBianLiang.YSS+"",blksMc);//根据备料开始名称更新数值为已上升.若已经创建过批次和批次记录了,就将已上升标识置1,存入上次数值集合里,作为上次变量数值.下次就不会再被检索到了
			}
		}
		

		//检测甲醛备料开始上升沿
		List<OpcBianLiang> jqblksMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类甲醛备料开始的变量集合
		List<OpcBianLiang> jqblksUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类甲醛备料开始的变量集合
		List<String> jqblksMcList=new ArrayList<String>();//创建甲醛备料开始的名称集合(不管是M类还是U类都放进去)
		List<String> jqblksFyfhList=new ArrayList<String>();//创建甲醛备料开始的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jqblksOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI_TEXT);//获取甲醛备料开始上升沿集合
		for (OpcBianLiang jqblksOBL : jqblksOBLList) {
			String mc = jqblksOBL.getMc();
			for (Map<String, Object> opcBLScszMap : opcBLScszList) {
				String scmc = opcBLScszMap.get("mc").toString();
				Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
				if(mc.equals(scmc)&&!scsz) {
					Integer lx = jqblksOBL.getLx();
					if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
						jqblksMOBLList.add(jqblksOBL);
					}
					else if(OpcBianLiang.LX_U==lx) {
						jqblksUOBLList.add(jqblksOBL);
					}
					
					String fyfh = jqblksOBL.getFyfh();
					jqblksMcList.add(mc);//不管是M类还是U类，都往集合里放
					jqblksFyfhList.add(fyfh);//添加甲醛备料开始反应釜号
				}
			}
		}
		
		if(jqblksMOBLList.size()>0) {
			//M类
			List<PiCiM> jqblksPcMList=piCiMService.getListByFyfhList(jqblksFyfhList);//根据甲醛备料开始变量里的反应釜号获取批次列表

			Map<String, Object> jjqJieDuanMap = (Map<String, Object>)jieDuanMMap.get(JieDuanM.JIA_JIA_QUAN_TEXT);//获取加甲醛阶段信息
			
			//添加与M类批次相关的加甲醛时间差阶段批次记录
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			piCiJiLuMService.addJdgcFromPcList(jqblksPcMList,sjcJlsjMap,jjqJieDuanMap);//添加加甲醛时间差阶段过程记录

			//添加与M类批次相关的加甲醛重量差阶段批次记录
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuMService.addJdgcFromPcList(jqblksPcMList,zlcJlsjMap,jjqJieDuanMap);//添加加甲醛重量差阶段过程记录

			
		}
		if(jqblksUOBLList.size()>0) {
			//U类
			List<PiCiU> jqblksPcUList=piCiUService.getListByFyfhList(jqblksFyfhList);//根据甲醛备料开始变量里的反应釜号获取批次列表
			
			Integer jjqJieDuanId = Integer.valueOf(jieDuanIdUMap.get(JieDuanU.JIA_JIA_QUAN_TEXT).toString());

			//添加与U类批次相关的加甲醛时间差阶段批次记录
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjUMap.get(JiLuShiJianU.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			piCiJiLuUService.addJdgcFromPcList(jqblksPcUList,sjcJlsjMap,jjqJieDuanId);//添加加甲醛阶段过程记录

			//添加与U类批次相关的加甲醛重量差阶段批次记录
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjUMap.get(JiLuShiJianU.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuUService.addJdgcFromPcList(jqblksPcUList,zlcJlsjMap,jjqJieDuanId);//添加加甲醛重量差阶段过程记录
		}
		if(jqblksMcList.size()>0) {
			for (String jqblksMc : jqblksMcList) {
				addOpcBLScszInList(OpcBianLiang.YSS+"",jqblksMc);
			}
		}
		
		
		//检测甲醛放料完成上升沿
		List<OpcBianLiang> jqflwcMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类甲醛放料完成的变量集合
		List<OpcBianLiang> jqflwcUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类甲醛放料完成的变量集合
		List<String> jqflwcMcList=new ArrayList<String>();
		List<String> jqflwcFyfhList=new ArrayList<String>();//创建甲醛放料完成的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jqflwcOBLList=opcBianLiangService.getUpSzListByMcQz(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG_TEXT);//获取甲醛放料完成上升沿集合
		for (OpcBianLiang jqflwcOBL : jqflwcOBLList) {
			String mc = jqflwcOBL.getMc();
			for (Map<String, Object> opcBLScszMap : opcBLScszList) {
				String scmc = opcBLScszMap.get("mc").toString();
				Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
				if(mc.equals(scmc)&&!scsz) {
					Integer lx = jqflwcOBL.getLx();
					if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
						jqflwcMOBLList.add(jqflwcOBL);
					}
					else if(OpcBianLiang.LX_U==lx) {
						jqflwcUOBLList.add(jqflwcOBL);
					}
					
					String fyfh = jqflwcOBL.getFyfh();
					jqflwcMcList.add(mc);
					jqflwcFyfhList.add(fyfh);//添加甲醛放料完成反应釜号
				}
			}
		}
		
		if(jqflwcMOBLList.size()>0) {
			//M类
			List<PiCiM> jqflwcPcMList=piCiMService.getListByFyfhList(jqflwcFyfhList);//根据甲醛放料完成变量里的反应釜号获取批次列表
			
			Map<String, Object> jqsjjlzlCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT);//获取甲醛实际进料重量参数信息
			Map<String, Object> jllJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.JIA_LIAO_LIANG_TEXT);//获取加料量记录事件id
			piCiJiLuMService.addCsjl(jqflwcPcMList,jqsjjlzlCsMap,jllJlsjMap);//添加甲醛实际进料重量参数记录
			
			//加水实际重量
		}
		if(jqflwcUOBLList.size()>0) {
			//U类
			
		}
		if(jqflwcMcList.size()>0) {
			for (String jqflwcMc : jqflwcMcList) {
				addOpcBLScszInList(OpcBianLiang.YSS+"",jqflwcMc);
			}
		}
		
		return json;
	}
	
	private void addOpcBLScszInList(String sz, String mc) {
		boolean exist=checkOpcBLIfExistInScszList(mc);
		System.out.println("exist==="+exist);
		if(exist) {
			for (Map<String, Object> opcBLScszMap : opcBLScszList) {
				String scmc = opcBLScszMap.get("mc").toString();
				if(mc.equals(mc)) {
					opcBLScszMap.put("sz", sz);
				}
			}
		}
		else {
			Map<String,Object> scszMap=new HashMap<String,Object>();
			scszMap.put("mc", mc);
			scszMap.put("sz", sz);
			opcBLScszList.add(scszMap);
		}
	}
	
	private boolean checkOpcBLIfExistInScszList(String mc) {
		boolean exist=false;
		for (Map<String, Object> opcBLScszMap : opcBLScszList) {
			String scmc = opcBLScszMap.get("mc").toString();
			if(mc.equals(scmc)) {
				exist=true;
				break;
			}
		}
		return exist;
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

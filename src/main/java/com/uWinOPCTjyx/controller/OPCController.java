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
	private List<Map<String, Object>> opcBLScszList=new ArrayList<Map<String, Object>>();//opc变量上次数值集合

	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/opcu")
	public String goOpcU(HttpServletRequest request) {
		
		//localhost:8080/UWinOPCTjyx/opc/opcm
		
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
		Map<String,Map<String, Object>> jlsjMMap=jiLuShiJianMService.getMap();//M类记录事件参数map集合，获取名称、id键值对，下面的逻辑里关联id时要用
		Map<String,Map<String, Object>> jieDuanMMap=jieDuanMService.getMap();//M类阶段参数map集合
		Map<String,Map<String, Object>> canShuMMap=canShuMService.getMap();//M类参数map集合
		
		//U类
		Map<String, Map<String, Object>> jlsjUMap = jiLuShiJianUService.getMap();
		Map<String, Map<String, Object>> jieDuanUMap = jieDuanUService.getMap();

		if(false) {
			//检测备料开始上升沿
			List<OpcBianLiang> blksMOBLList=new ArrayList<OpcBianLiang>();//备料开始M类opc变量集合
			List<OpcBianLiang> blksUOBLList=new ArrayList<OpcBianLiang>();//备料开始U类opc变量集合
			List<String> blksFyfhList=new ArrayList<String>();//备料开始反应釜号集合(不管是M类还是U类都放进去)
			List<OpcBianLiang> blksOBLList=opcBianLiangService.getListByMcQz(Constant.BEI_LIAO_KAI_SHI_TEXT);//不管变量值有没有上升都获取，为下面存储为上一次数值提供变量集合
			List<OpcBianLiang> upSzBlksOBLList=getUpSzListFromList(blksOBLList);//从所有备料开始有关的变量里获取备料开始上升沿集合
			for (OpcBianLiang upSzBlksOBL : upSzBlksOBLList) {
				String mc = upSzBlksOBL.getMc();//获取变量的名称
				if(opcBLScszList.size()==0) {
					Integer lx = upSzBlksOBL.getLx();//获取类型
					if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
						blksMOBLList.add(upSzBlksOBL);
					}
					else if(OpcBianLiang.LX_U==lx) {
						blksUOBLList.add(upSzBlksOBL);
					}
					String fyfh = upSzBlksOBL.getFyfh();//获取反应釜号
					blksFyfhList.add(fyfh);//添加到反应釜号集合
				}
				else {
					for (Map<String, Object> opcBLScszMap : opcBLScszList) {//遍历opc变量上次数值集合
						String scmc = opcBLScszMap.get("mc").toString();
						Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
						if(mc.equals(scmc)&&!scsz) {//若上次数值的集合里的某个变量名称是这次的变量名称，就根据上次数值判断。若上次数值未上升置1，而这次数值为1，说明这次数值是刚上升的，备料刚开始,符合条件才往集合里存
							Integer lx = upSzBlksOBL.getLx();
							if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
								blksMOBLList.add(upSzBlksOBL);
							}
							else if(OpcBianLiang.LX_U==lx) {
								blksUOBLList.add(upSzBlksOBL);
							}

							String fyfh = upSzBlksOBL.getFyfh();
							blksFyfhList.add(fyfh);
						}
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
			if(blksOBLList.size()>0) {
				for (OpcBianLiang blksOBL : blksOBLList) {//循环备料开始集合
					String blksMc = blksOBL.getMc();
					String blksSz = blksOBL.getSz();
					addOpcBLScszInList(blksSz,blksMc);//根据备料开始名称更新数值,存入上次数值集合里,作为上次变量数值.下次就不会再被检索到了
				}
			}
		}
		
		

		//if(false) {
		//检测甲醛备料开始上升沿
		List<OpcBianLiang> jqblksMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类甲醛备料开始的变量集合
		List<OpcBianLiang> jqblksUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类甲醛备料开始的变量集合
		List<String> jqblksFyfhList=new ArrayList<String>();//创建甲醛备料开始的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jqblksOBLList=opcBianLiangService.getListByMcQz(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI_TEXT);//获取甲醛备料开始上升沿集合
		List<OpcBianLiang> upSzJqBlksOBLList=getUpSzListFromList(jqblksOBLList);
		for (OpcBianLiang upSzJqblksOBL : upSzJqBlksOBLList) {
			String mc = upSzJqblksOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzJqblksOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					jqblksMOBLList.add(upSzJqblksOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					jqblksUOBLList.add(upSzJqblksOBL);
				}

				String fyfh = upSzJqblksOBL.getFyfh();
				jqblksFyfhList.add(fyfh);//添加甲醛备料开始反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzJqblksOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							jqblksMOBLList.add(upSzJqblksOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							jqblksUOBLList.add(upSzJqblksOBL);
						}

						String fyfh = upSzJqblksOBL.getFyfh();
						jqblksFyfhList.add(fyfh);//添加甲醛备料开始反应釜号
					}
				}
			}
		}

		if(jqblksMOBLList.size()>0) {
			//M类
			List<PiCiM> jqblksPcMList=piCiMService.getListByFyfhList(jqblksFyfhList);//根据甲醛备料开始变量里的反应釜号获取批次列表
			
			Map<String, Object> jjqJieDuanMap = (Map<String, Object>)jieDuanMMap.get(JieDuanM.JIA_JIA_QUAN_TEXT);//获取加甲醛阶段信息

			//添加与M类批次相关的加甲醛重量差阶段批次记录
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuMService.addJdgcFromPcList(jqblksPcMList,zlcJlsjMap,jjqJieDuanMap);//添加加甲醛重量差阶段过程记录
			
			//添加与M类批次相关的加甲醛时间差阶段批次记录
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			piCiJiLuMService.addJdgcFromPcList(jqblksPcMList,sjcJlsjMap,jjqJieDuanMap);//添加加甲醛时间差阶段过程记录



		}
		if(jqblksUOBLList.size()>0) {
			//U类
			List<PiCiU> jqblksPcUList=piCiUService.getListByFyfhList(jqblksFyfhList);//根据甲醛备料开始变量里的反应釜号获取批次列表

			Map<String, Object> jjqJieDuanMap = (Map<String, Object>)jieDuanUMap.get(JieDuanU.JIA_JIA_QUAN_TEXT);

			//添加与U类批次相关的加甲醛时间差阶段批次记录
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjUMap.get(JiLuShiJianU.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			piCiJiLuUService.addJdgcFromPcList(jqblksPcUList,sjcJlsjMap,jjqJieDuanMap);//添加加甲醛阶段过程记录

			//添加与U类批次相关的加甲醛重量差阶段批次记录
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjUMap.get(JiLuShiJianU.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuUService.addJdgcFromPcList(jqblksPcUList,zlcJlsjMap,jjqJieDuanMap);//添加加甲醛重量差阶段过程记录
		}

		if(jqblksOBLList.size()>0) {
			for (OpcBianLiang jqblksOBL : jqblksOBLList) {//循环备料开始集合
				String jqblksMc = jqblksOBL.getMc();
				String jqblksSz = jqblksOBL.getSz();
				addOpcBLScszInList(jqblksSz,jqblksMc);//根据甲醛备料开始名称更新数值,存入上次数值集合里,作为上次变量数值.下次就不会再被检索到了
			}
		}
		//}


		//if(false) {
		//检测甲醛放料完成上升沿
		List<OpcBianLiang> jqflwcMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类甲醛放料完成的变量集合
		List<OpcBianLiang> jqflwcUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类甲醛放料完成的变量集合
		List<String> jqflwcFyfhList=new ArrayList<String>();//创建甲醛放料完成的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jqflwcOBLList=opcBianLiangService.getListByMcQz(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG_TEXT);//获取甲醛放料完成上升沿集合
		List<OpcBianLiang> upSzJqflwcOBLList=getUpSzListFromList(jqflwcOBLList);//
		for (OpcBianLiang upSzJqflwcOBL : upSzJqflwcOBLList) {
			String mc = upSzJqflwcOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzJqflwcOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					jqflwcMOBLList.add(upSzJqflwcOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					jqflwcUOBLList.add(upSzJqflwcOBL);
				}
				
				String fyfh = upSzJqflwcOBL.getFyfh();
				jqflwcFyfhList.add(fyfh);//添加甲醛放料完成反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzJqflwcOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							jqflwcMOBLList.add(upSzJqflwcOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							jqflwcUOBLList.add(upSzJqflwcOBL);
						}
						
						String fyfh = upSzJqflwcOBL.getFyfh();
						jqflwcFyfhList.add(fyfh);//添加甲醛放料完成反应釜号
					}
				}
			}
		}

		if(jqflwcMOBLList.size()>0) {
			//M类
			List<PiCiM> jqflwcPcMList=piCiMService.getListByFyfhList(jqflwcFyfhList);//根据甲醛放料完成变量里的反应釜号获取批次列表
			Map<String, Object> jllJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.JIA_LIAO_LIANG_TEXT);//获取加料量记录事件id
			
			//甲醛实际进料重量
			Map<String, Object> jqsjjlzlCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT);//获取甲醛实际进料重量参数信息
			piCiJiLuMService.addCsjl(jqflwcPcMList,jqsjjlzlCsMap,jllJlsjMap);//添加甲醛实际进料重量参数记录
			
			//加水实际重量
			Map<String, Object> jssjzlCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_SHUI_SHI_JI_ZHONG_LIANG_TEXT);//获取加水实际重量参数信息
			piCiJiLuMService.addCsjl(jqflwcPcMList,jssjzlCsMap,jllJlsjMap);//添加水实际重量参数记录

			Map<String, Object> wdJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.WEN_DU_TEXT);//获取温度记录事件id
			
			//反应釜温度
			Map<String, Object> fyfwdCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.FAN_YING_FU_WEN_DU_TEXT);//获取反应釜温度参数信息 
			piCiJiLuMService.addCsjl(jqflwcPcMList,fyfwdCsMap,wdJlsjMap);//添加反应釜温度参数记录
			
			//加甲醛阶段信息
			Map<String, Object> jjqJieDuanMap = (Map<String, Object>)jieDuanMMap.get(JieDuanM.JIA_JIA_QUAN_TEXT);//获取加甲醛阶段信息
			
			//编辑与M类批次相关的加甲醛时间差阶段批次记录(填充结束时间、时间差)
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			int c=piCiJiLuMService.editJdgcFromPcList(jqflwcPcMList,sjcJlsjMap,jjqJieDuanMap);//编辑加甲醛时间差阶段过程记录

			//编辑与M类批次相关的加甲醛重量差阶段批次记录(填充之后重量、重量差)
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuMService.editJdgcFromPcList(jqflwcPcMList,zlcJlsjMap,jjqJieDuanMap);//编辑加甲醛重量差阶段过程记录

		}
		if(jqflwcUOBLList.size()>0) {
			//U类
			
		}
		if(jqflwcOBLList.size()>0) {
			for (OpcBianLiang jqflwcOBL : jqflwcOBLList) {//循环放料完成集合
				String jqflwcMc = jqflwcOBL.getMc();
				String jqflwcSz = jqflwcOBL.getSz();
				addOpcBLScszInList(jqflwcSz,jqflwcMc);//根据甲醛放料完成名称更新数值,存入上次数值集合里,作为上次变量数值.下次就不会再被检索到了
			}
		}
		//}
		
		
		////剩余逻辑在这里写
		//加碱PH值正常上升沿
		List<OpcBianLiang> jjphzzcMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类加碱PH值正常的变量集合
		List<OpcBianLiang> jjphzzcUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类加碱PH值正常的变量集合
		List<String> jjphzzcFyfhList=new ArrayList<String>();//创建加碱PH值正常的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jjphzzcOBLList=opcBianLiangService.getListByMcQz(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG_TEXT);//获取加碱PH值正常上升沿集合
		List<OpcBianLiang> upSzJjphzzcOBLList=getUpSzListFromList(jjphzzcOBLList);//
		for (OpcBianLiang upSzJjphzzcOBL : upSzJjphzzcOBLList) {
			String mc = upSzJjphzzcOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzJjphzzcOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					jjphzzcMOBLList.add(upSzJjphzzcOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					jjphzzcUOBLList.add(upSzJjphzzcOBL);
				}
				
				String fyfh = upSzJjphzzcOBL.getFyfh();
				jjphzzcFyfhList.add(fyfh);//添加加碱PH值正常反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzJjphzzcOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							jjphzzcMOBLList.add(upSzJjphzzcOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							jjphzzcUOBLList.add(upSzJjphzzcOBL);
						}
						
						String fyfh = upSzJjphzzcOBL.getFyfh();
						jjphzzcFyfhList.add(fyfh);//添加加碱PH值正常反应釜号
					}
				}
			}
		}
		if(jjphzzcMOBLList.size()>0) {
			//M类
			List<PiCiM> jjphzzcPcMList=piCiMService.getListByFyfhList(jjphzzcFyfhList);//根据加碱PH值正常里的反应釜号获取批次列表

			Map<String,Object> phzJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.PH_ZHI_TEXT);//获取Ph值记录事件id
			Map<String, Object> jllJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.JIA_LIAO_LIANG_TEXT);//获取加料量记录事件id
			Map<String, Object> czJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.CHENG_ZHONG_TEXT);//获取称重记录事件id 

			//加碱前PH值
			Map<String, Object> jjqphCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_JIAN_QIAN_PH_TEXT);//获取加碱前ph参数信息
			piCiJiLuMService.addCsjl(jjphzzcPcMList,jjqphCsMap,phzJlsjMap);//添加加碱前ph参数记录

			//加碱量提示
			Map<String, Object> jjltsCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_JIAN_LIANG_TEXT);//获取加碱量提示参数信息
			piCiJiLuMService.addCsjl(jjphzzcPcMList,jjltsCsMap,jllJlsjMap);//添加加碱量提示参数记录

			//加碱后PH值
			Map<String, Object> jjhphCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_JIAN_HOU_PH_TEXT);//获取加碱后PH值参数信息
			piCiJiLuMService.addCsjl(jjphzzcPcMList,jjhphCsMap,phzJlsjMap);//添加加碱后PH值参数记录
			
			//计算助剂计量罐1、2重量之和
			Map<String, Object> zjjlgczCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.ZHU_JI_JI_LIANG_GUAN_1_2_CHENG_ZHONG_TEXT);//获取助剂计量罐1-2称重参数信息
			
			piCiJiLuMService.addCsjl(jjphzzcPcMList,zjjlgczCsMap,czJlsjMap);//添加助剂计量罐1-2称重参数记录

		}
		if(jjphzzcOBLList.size()>0) {
			for (OpcBianLiang jjphzzcOBL : jjphzzcOBLList) {
				String jjphzzcMc = jjphzzcOBL.getMc();
				String jjphzzcSz = jjphzzcOBL.getSz();
				addOpcBLScszInList(jjphzzcSz,jjphzzcMc);
			}
		}

		//允许一次加助剂上升沿(这是往批次记录表里插入阶段数据，前面有例子)
		List<OpcBianLiang> yxycjzjMOBLList = new ArrayList<OpcBianLiang>();//创建M类允许一次加助剂的变量集合
		List<OpcBianLiang> yxycjzjUOBLList = new ArrayList<OpcBianLiang>();//创建U类允许一次加助剂的变量集合
		List<String> yxycjzjFyfhList = new ArrayList<String>();//创建允许一次加助剂的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> yxycjzjOBLList=opcBianLiangService.getListByMcQz(Constant.YUN_XU_YI_CI_JIA_ZHU_JI_TEXT);//获取允许一次加助剂上升沿集合
		List<OpcBianLiang> upSzYxycjzjOBLList=getUpSzListFromList(yxycjzjOBLList);//
		for (OpcBianLiang upSzYxycjzjOBL : upSzYxycjzjOBLList) {
			String mc = upSzYxycjzjOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzYxycjzjOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					yxycjzjMOBLList.add(upSzYxycjzjOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					yxycjzjUOBLList.add(upSzYxycjzjOBL);
				}

				String fyfh = upSzYxycjzjOBL.getFyfh();
				yxycjzjFyfhList.add(fyfh);//添加允许一次加助剂反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzYxycjzjOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							yxycjzjMOBLList.add(upSzYxycjzjOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							yxycjzjUOBLList.add(upSzYxycjzjOBL);
						}

						String fyfh = upSzYxycjzjOBL.getFyfh();
						yxycjzjFyfhList.add(fyfh);//添加允许一次加助剂反应釜号
					}
				}
			}
		}
		if(yxycjzjMOBLList.size()>0) {
			//M类
			List<PiCiM> yxycjzjPcMList=piCiMService.getListByFyfhList(yxycjzjFyfhList);//根据允许一次加助剂里的反应釜号获取批次列表

			Map<String, Object> ycjzjJieDuanMap = (Map<String, Object>)jieDuanMMap.get(JieDuanM.YI_CI_JIA_ZHU_JI_TEXT);//获取一次加助剂信息

			Map<String,Object> zlcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件id
			piCiJiLuMService.addJdgcFromPcList(yxycjzjPcMList,zlcJlsjMap,ycjzjJieDuanMap);//添加重量差阶段过程记录

			Map<String,Object> sjcJlsjsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT);//获取时间差记录事件id
			piCiJiLuMService.addJdgcFromPcList(yxycjzjPcMList,sjcJlsjsjMap,ycjzjJieDuanMap);//添加时间差阶段过程记录

		}
		if(yxycjzjOBLList.size()>0) {
			for (OpcBianLiang yxycjzjOBL : yxycjzjOBLList) {
				String yxycjzjMc = yxycjzjOBL.getMc();
				String yxycjzjSz = yxycjzjOBL.getSz();
				addOpcBLScszInList(yxycjzjSz,yxycjzjMc);
			}
		}

		//所有助剂加料完成1上升沿(这是更新批次记录表里插入阶段数据，调用editJdgcFromPcList前面有例子)
		List<OpcBianLiang> syzjjlwc1MOBLList = new ArrayList<OpcBianLiang>();//创建所有助剂加料完成1M类变量集合
		List<OpcBianLiang> syzjjlwc1UOBLList = new ArrayList<OpcBianLiang>();//创建所有助剂加料完成1U类变量集合
		List<String> syzjjlwc1FyfhList = new ArrayList<String>();//创建所有助剂加料完成1的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> syzjjlwc1OBLList=opcBianLiangService.getListByMcQz(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1_TEXT);//获取所有助剂加料完成1上升沿集合
		List<OpcBianLiang> upSzSyzjjlwc1OBLList=getUpSzListFromList(syzjjlwc1OBLList);//
		for (OpcBianLiang upSzSyzjjlwc1OBL : upSzSyzjjlwc1OBLList) {
			String mc = upSzSyzjjlwc1OBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzSyzjjlwc1OBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					syzjjlwc1MOBLList.add(upSzSyzjjlwc1OBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					syzjjlwc1UOBLList.add(upSzSyzjjlwc1OBL);
				}

				String fyfh = upSzSyzjjlwc1OBL.getFyfh();
				syzjjlwc1FyfhList.add(fyfh);//添加所有助剂加料完成1反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzSyzjjlwc1OBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							syzjjlwc1MOBLList.add(upSzSyzjjlwc1OBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							syzjjlwc1UOBLList.add(upSzSyzjjlwc1OBL);
						}

						String fyfh = upSzSyzjjlwc1OBL.getFyfh();
						syzjjlwc1FyfhList.add(fyfh);//添加所有助剂加料完成1反应釜号
					}
				}
			}
		}
		if(syzjjlwc1MOBLList.size()>0) {
			//M类
			List<PiCiM> syzjjlwc1PcMList=piCiMService.getListByFyfhList(syzjjlwc1FyfhList);//根据所有助剂加料完成1变量里的反应釜号获取批次列表

			//所有助剂加料完成1
			Map<String, Object> syzjjlwc1DuanMap = (Map<String, Object>)jieDuanMMap.get(JieDuanM.YI_CI_JIA_ZHU_JI_TEXT);//获取所有助剂加料完成1

			//编辑与M类批次相关的加所有助剂加料完成1时间差阶段批次记录(填充结束时间、时间差)
			Map<String, Object> sjcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.SHI_JIAN_CHA_TEXT);//获取时间差记录事件信息
			int c=piCiJiLuMService.editJdgcFromPcList(syzjjlwc1PcMList,sjcJlsjMap,syzjjlwc1DuanMap);//编辑所有助剂加料完成1时间差阶段过程记录

			//编辑与M类批次相关的加所有助剂加料完成1重量差阶段批次记录(填充之后重量、重量差)
			Map<String, Object> zlcJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);//获取重量差记录事件信息
			piCiJiLuMService.editJdgcFromPcList(syzjjlwc1PcMList,zlcJlsjMap,syzjjlwc1DuanMap);//编辑所有助剂加料完成1重量差阶段过程记录

		}
		if(syzjjlwc1OBLList.size()>0) {
			for (OpcBianLiang syzjjlwc1OBL : syzjjlwc1OBLList) {
				String syzjjlwc1Mc = syzjjlwc1OBL.getMc();
				String syzjjlwc1Sz = syzjjlwc1OBL.getSz();
				addOpcBLScszInList(syzjjlwc1Sz,syzjjlwc1Mc);
			}
		}
		
		//检测加粉料提醒上升沿(我来写)
		List<OpcBianLiang> jfltxMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类加粉料提醒的变量集合
		List<OpcBianLiang> jfltxUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类加粉料提醒的变量集合
		List<String> jfltxFyfhList=new ArrayList<String>();//创建加粉料提醒的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jfltxOBLList=opcBianLiangService.getListByMcQz(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG_TEXT);//获取加粉料提醒上升沿集合
		List<OpcBianLiang> upSzJfltxOBLList=getUpSzListFromList(jfltxOBLList);//
		for (OpcBianLiang upSzJfltxOBL : upSzJfltxOBLList) {
			String mc = upSzJfltxOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzJfltxOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					jfltxMOBLList.add(upSzJfltxOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					jfltxUOBLList.add(upSzJfltxOBL);
				}
				
				String fyfh = upSzJfltxOBL.getFyfh();
				jfltxFyfhList.add(fyfh);//添加加粉料提醒反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzJfltxOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							jfltxMOBLList.add(upSzJfltxOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							jfltxUOBLList.add(upSzJfltxOBL);
						}
						
						String fyfh = upSzJfltxOBL.getFyfh();
						jfltxFyfhList.add(fyfh);//添加加粉料提醒反应釜号
					}
				}
			}
		}

		if(jfltxMOBLList.size()>0) {
			//M类
			List<PiCiM> jfltxPcMList=piCiMService.getListByFyfhList(jfltxFyfhList);//根据加粉料提醒变量里的反应釜号获取批次列表
			Map<String, Object> jllJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.JIA_LIAO_LIANG_TEXT);//获取加料量记录事件id
			
			//加粉料重量
			Map<String, Object> jflzlCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.FEN_LIAO_ZHONG_LIANG_TEXT);//获取加粉料重量参数信息
			piCiJiLuMService.addCsjl(jfltxPcMList,jflzlCsMap,jllJlsjMap);//添加粉料重量参数记录
			
			//釜1尿素放料阀(有待跟徐龙确认)???
		}

		//检测加粉料ph合格上升沿
		List<OpcBianLiang> jflphhzMOBLList=new ArrayList<OpcBianLiang>();//创建存放M类加粉料ph合格的变量集合
		List<OpcBianLiang> jflphhzUOBLList=new ArrayList<OpcBianLiang>();//创建存放U类加粉料ph合格的变量集合
		List<String> jflphhzFyfhList=new ArrayList<String>();//创建加粉料ph合格的反应釜号集合(不管是M类还是U类都放进去)
		List<OpcBianLiang> jflphhzOBLList=opcBianLiangService.getListByMcQz(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG_TEXT);//获取加粉料ph合格上升沿集合
		List<OpcBianLiang> upSzJflphhzOBLList=getUpSzListFromList(jflphhzOBLList);//
		for (OpcBianLiang upSzJflphhzOBL : upSzJflphhzOBLList) {
			String mc = upSzJflphhzOBL.getMc();
			if(opcBLScszList.size()==0) {
				Integer lx = upSzJflphhzOBL.getLx();
				if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
					jflphhzMOBLList.add(upSzJflphhzOBL);
				}
				else if(OpcBianLiang.LX_U==lx) {
					jflphhzMOBLList.add(upSzJflphhzOBL);
				}
				
				String fyfh = upSzJflphhzOBL.getFyfh();
				jflphhzFyfhList.add(fyfh);//添加加粉料ph合格反应釜号
			}
			else {
				for (Map<String, Object> opcBLScszMap : opcBLScszList) {
					String scmc = opcBLScszMap.get("mc").toString();
					Boolean scsz = Boolean.valueOf(opcBLScszMap.get("sz").toString());
					if(mc.equals(scmc)&&!scsz) {
						Integer lx = upSzJflphhzOBL.getLx();
						if(OpcBianLiang.LX_M==lx) {//根据类型判断是M类还是U类，往对应的集合里放
							jflphhzMOBLList.add(upSzJflphhzOBL);
						}
						else if(OpcBianLiang.LX_U==lx) {
							jflphhzUOBLList.add(upSzJflphhzOBL);
						}
						
						String fyfh = upSzJflphhzOBL.getFyfh();
						jflphhzFyfhList.add(fyfh);//添加加粉料ph合格反应釜号
					}
				}
			}
		}
		
		if(jflphhzMOBLList.size()>0) {
			//M类
			List<PiCiM> jflphhzPcMList=piCiMService.getListByFyfhList(jflphhzFyfhList);//根据加粉料ph合格变量里的反应釜号获取批次列表
			Map<String, Object> phzJlsjMap = (Map<String, Object>)jlsjMMap.get(JiLuShiJianM.PH_ZHI_TEXT);//获取加料量记录事件id

			//加粉料PH
			Map<String, Object> jflphCsMap = (Map<String, Object>)canShuMMap.get(CanShuM.JIA_FEN_LIAO_PH_TEXT);//获取加粉料PH参数信息
			piCiJiLuMService.addCsjl(jflphhzPcMList,jflphCsMap,phzJlsjMap);//添加加粉料PH参数记录
		}
		
		
		//检测升温开始上升沿
		//1.记录蒸汽压力参数
		
		
		//检测温度85与二次投料提醒上升沿
		//1.记录反应釜1温度参数
		
		
		//检测二次助剂后测PH提醒上升沿
		//1.记录二次投料PH参数
		
		
		return json;
	}
	
	private List<OpcBianLiang> getUpSzListFromList(List<OpcBianLiang> opcBianLiangList) {
		List<OpcBianLiang> upSzOblList=new ArrayList<OpcBianLiang>();
		for (OpcBianLiang opcBianLiang : opcBianLiangList) {
			boolean sz = Boolean.parseBoolean(opcBianLiang.getSz());
			if(sz) {
				upSzOblList.add(opcBianLiang);
			}
		}
		return upSzOblList;
	}
	
	/**
	 * 添加或更新opc变量上次数值到集合里
	 * @param sz
	 * @param mc
	 */
	private void addOpcBLScszInList(String sz, String mc) {
		boolean exist=checkOpcBLIfExistInScszList(mc);
		System.out.println("exist==="+exist);
		if(exist) {
			for (Map<String, Object> opcBLScszMap : opcBLScszList) {
				String scmc = opcBLScszMap.get("mc").toString();
				if(mc.equals(scmc)) {
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
	
	/**
	 * 根据变量名称检测上次变量数值是否存在与集合里
	 * @param mc
	 * @return
	 */
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

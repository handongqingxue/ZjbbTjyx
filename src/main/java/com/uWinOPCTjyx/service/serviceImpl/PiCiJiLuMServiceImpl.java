package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.*;

@Service
public class PiCiJiLuMServiceImpl implements PiCiJiLuMService {

    @Autowired
    private PiCiJiLuMMapper piCiJiLuMMapper;
    @Autowired
    private OpcBianLiangMapper opcBianLiangMapper;

	public int addPcgcFromPcIdList(List<Integer> pcIdList, Map<String, Object> jlsjMap) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;
        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
		Date date = new Date();
		for (Integer pcId : pcIdList) {
			piCiJiLuM=new PiCiJiLuM();
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setJlsjId(jlsjId);
			piCiJiLuM.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
			piCiJiLuM.setJllx(PiCiJiLuM.PI_CI_GUO_CHENG_JI_LU);
			
			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}

	public int addJdgcFromPcList(List<PiCiM> pcList, Map<String, Object> jlsjMap, Map<String, Object> jieDuanMap) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;

        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
        String jlsjMc = jlsjMap.get("mc").toString();
        Integer jieDuanId = Integer.valueOf(jieDuanMap.get("id").toString());
        
		List<OpcBianLiang> opcBLList=null;
		if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {//重量差的事件类型需要根据反应釜号获取重量
			String mc=Constant.FU_TEXT+Constant.BAI_FEN_HAO_TEXT+Constant.CHENG_ZHONG_TEXT+"_AV";//釜称重名称
			List<String> fyfhList=new ArrayList<String>();
			for (PiCiM pc : pcList) {
				String fyfh = pc.getFyfh();
				fyfhList.add(fyfh);
			}
			
			opcBLList=opcBianLiangMapper.getListByFyfhList(mc,fyfhList);
		}
		
		for (PiCiM pc : pcList) {
			piCiJiLuM=new PiCiJiLuM();
			Integer pcId = pc.getId();
			String jlkssj=null;
			Float zqzl=null;
			if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc)) {
				Date date = new Date();
				jlkssj=DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND);
			}
			else if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {
				String fyfh = pc.getFyfh();
				for (OpcBianLiang opcBL : opcBLList) {
					if(fyfh.equals(opcBL.getFyfh())) {
						zqzl=Float.valueOf(opcBL.getSz());//因为是刚开始添加的阶段过程记录，设置重量为之前重量
						break;
					}
				}
			}
			
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setJlsjId(jlsjId);
			if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc)) {
				piCiJiLuM.setJlkssj(jlkssj);
			}
			else if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc))//重量差的事件类型需要设置之前重量
				piCiJiLuM.setZqzl(zqzl);
			piCiJiLuM.setJllx(PiCiJiLuM.JIE_DUAN_GUO_CHENG_JI_LU);
			piCiJiLuM.setJdId(jieDuanId);
			
			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}

	public int editJdgcFromPcList(List<PiCiM> pcList, Map<String, Object> jlsjMap, Map<String, Object> jieDuanMap) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;

        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
        String jlsjMc = jlsjMap.get("mc").toString();
        Integer jieDuanId = Integer.valueOf(jieDuanMap.get("id").toString());
        
		List<OpcBianLiang> opcBLList=null;
		if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {//重量差的事件类型需要根据反应釜号获取重量
			String mc=Constant.FU_TEXT+Constant.BAI_FEN_HAO_TEXT+Constant.CHENG_ZHONG_TEXT+"_AV";//釜称重名称
			List<String> fyfhList=new ArrayList<String>();
			for (PiCiM pc : pcList) {
				String fyfh = pc.getFyfh();
				fyfhList.add(fyfh);
			}
			
			opcBLList=opcBianLiangMapper.getListByFyfhList(mc,fyfhList);
		}
        
        List<Integer> pcIdList=new ArrayList<Integer>();
        for (PiCiM pc : pcList) {
			Integer pcId = pc.getId();
			pcIdList.add(pcId);
        }
        
        List<PiCiJiLuM> jdgcList=piCiJiLuMMapper.getJdgcListByPcIdList(pcIdList,jlsjId,jieDuanId);
        for (PiCiJiLuM jdgc : jdgcList) {
        	Integer jdgcId = jdgc.getId();
        	String jljssj=null;
			Float zhzl=null;
			String jlnr=null;
			if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {
				String fyfh = jdgc.getFyfh();
				for (OpcBianLiang opcBL : opcBLList) {
					if(fyfh.equals(opcBL.getFyfh())) {
			        	Float zqzl = jdgc.getZqzl();
						zhzl=Float.valueOf(opcBL.getSz());//因为是刚开始添加的阶段过程记录，设置重量为之前重量
						jlnr=(zqzl-zhzl)+"kg";
						break;
					}
				}
			}
			
			if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc)) {
				String jlkssj = jdgc.getJlkssj();
				Date date = new Date();
				jdgc.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
			}
			else if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {
				jdgc.setZhzl(zhzl);
				jdgc.setJlnr(jlnr);
			}
		}
        
		return 0;
	}

	public int addCsjl(List<PiCiM> pcList, Map<String, Object> csMap, Map<String, Object> jlsjMap) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;
		
		List<OpcBianLiang> opcBLList=null;
		List<String> mcList=new ArrayList<String>();
        String csmc = csMap.get("mc").toString();
		if(CanShuM.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT.equals(csmc)
			||CanShuM.JIA_SHUI_SHI_JI_ZHONG_LIANG_TEXT.equals(csmc)) {
			String[] bsfFMArr = Constant.BSF_F_M_ARR;
			for (int i = 0; i < bsfFMArr.length; i++) {
				mcList.add(csmc+"_"+bsfFMArr[i]+"_AV");
			}
			opcBLList=opcBianLiangMapper.getListByFyMcList(mcList);
		}
		else if(CanShuM.FAN_YING_FU_WEN_DU_TEXT.equals(csmc)) {
			String mc=Constant.FAN_YING_FU_TEXT+Constant.BAI_FEN_HAO_TEXT+Constant.WEN_DU_TEXT+"_AV";
			List<String> fyfhList=new ArrayList<String>();
			for (PiCiM pc : pcList) {
				String fyfh = pc.getFyfh();
				fyfhList.add(fyfh);
			}
			
			opcBLList=opcBianLiangMapper.getListByFyfhList(mc,fyfhList);
		}
		
		Integer csId = Integer.valueOf(csMap.get("id").toString());//参数id
		String csdw = csMap.get("dw").toString();//参数单位
		Integer cslx = Integer.valueOf(csMap.get("lx").toString());//参数类型
		Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());//记录事件id
		
		for (PiCiM pc : pcList) {
			piCiJiLuM=new PiCiJiLuM();
			Integer pcId = pc.getId();
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setCsId(csId);
			piCiJiLuM.setJlsjId(jlsjId);
			
			String fyfh = pc.getFyfh();
			String jlnr = null;
			for (OpcBianLiang opcBL : opcBLList) {
				if(fyfh.equals(opcBL.getFyfh())) {
					jlnr = Float.valueOf(opcBL.getSz())+csdw;
					break;
				}
			}
			piCiJiLuM.setJlnr(jlnr);
			
			Integer jllx=null;
			if(CanShuM.WU_LIAO_CAN_SHU==cslx)
				jllx=PiCiJiLuM.YUAN_LIAO_JIN_LIAO_JI_LU;
			else if(CanShuM.GONG_YI_CAN_SHU==cslx)
				jllx=PiCiJiLuM.GONG_YI_CAN_SHU_JI_LU;
			piCiJiLuM.setJllx(jllx);

			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}
}

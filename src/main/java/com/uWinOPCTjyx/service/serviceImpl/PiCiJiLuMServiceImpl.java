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

		List<OpcBianLiang> fczOBLList=null;
		if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {//重量差的事件类型需要根据反应釜号获取重量
			String fczMc=Constant.FU_TEXT+Constant.BAI_FEN_HAO_TEXT+Constant.CHENG_ZHONG_TEXT+"_AV";//釜称重名称
			List<String> fyfhList=new ArrayList<String>();
			for (PiCiM pc : pcList) {
				String fyfh = pc.getFyfh();
				fyfhList.add(fyfh);
			}
			
			fczOBLList=opcBianLiangMapper.getFczListByFyfhList(fczMc,fyfhList);
		}
		
		for (PiCiM pc : pcList) {
			piCiJiLuM=new PiCiJiLuM();
			Integer pcId = pc.getId();
			Float zqzl=null;
			if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {
				String fyfh = pc.getFyfh();
				for (OpcBianLiang fczOBL : fczOBLList) {
					if(fyfh.equals(fczOBL.getFyfh())) {
						zqzl=Float.valueOf(fczOBL.getSz());//因为是刚开始添加的阶段过程记录，设置重量为之前重量
						break;
					}
				}
			}
			
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setJlsjId(jlsjId);
			if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc)) {
				Date date = new Date();
				piCiJiLuM.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
			}
			else if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc))//重量差的事件类型需要设置之前重量
				piCiJiLuM.setZqzl(zqzl);
			piCiJiLuM.setJllx(PiCiJiLuM.JIE_DUAN_GUO_CHENG_JI_LU);
			piCiJiLuM.setJdId(jieDuanId);
			
			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}

	public int addCsjl(List<PiCiM> pcList, Map<String, Object> jqsjjlzlCsMap, Map<String, Object> jlsjMap) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;
		
		List<String> mcList=new ArrayList<String>();
		String[] bsfFMArr = Constant.BSF_F_M_ARR;
		String csmc = jqsjjlzlCsMap.get("mc").toString();
		for (int i = 0; i < bsfFMArr.length; i++) {
			mcList.add(csmc+"_"+bsfFMArr[i]+"_AV");
		}
		List<OpcBianLiang> jqsjjlzlOBLList=opcBianLiangMapper.getJqsjjlzlByFyMcList(mcList);
		
		Integer csId = Integer.valueOf(jqsjjlzlCsMap.get("id").toString());//参数id
		String csdw = jqsjjlzlCsMap.get("dw").toString();//参数单位
		Integer cslx = Integer.valueOf(jqsjjlzlCsMap.get("lx").toString());//参数类型
		Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());//记录事件id
		
		for (PiCiM pc : pcList) {
			piCiJiLuM=new PiCiJiLuM();
			Integer pcId = pc.getId();
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setCsId(csId);
			piCiJiLuM.setJlsjId(jlsjId);
			
			String fyfh = pc.getFyfh();
			String jlnr = null;
			for (OpcBianLiang jqsjjlzlOBL : jqsjjlzlOBLList) {
				if(fyfh.equals(jqsjjlzlOBL.getFyfh())) {
					jlnr = Float.valueOf(jqsjjlzlOBL.getSz())+csdw;
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

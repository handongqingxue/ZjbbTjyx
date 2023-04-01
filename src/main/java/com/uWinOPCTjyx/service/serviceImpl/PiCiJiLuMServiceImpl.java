package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public int addPcgcFromPcIdList(List<Integer> pcIdList, Integer jlsjId) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;
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

	public int addJdgcFromPcList(List<PiCiM> pcList, Integer jlsjId, String jlsjMc, Integer jieDuanId) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiJiLuM piCiJiLuM=null;
		
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
			String fyfh = pc.getFyfh();
			for (OpcBianLiang fczOBL : fczOBLList) {
				if(fyfh.equals(fczOBL.getFyfh())) {
					zqzl=Float.valueOf(fczOBL.getSz());
					break;
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
			
			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}
}

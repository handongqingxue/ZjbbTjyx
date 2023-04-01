package com.uWinOPCTjyx.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.DateUtil;

@Service
public class PiCiJiLuMServiceImpl implements PiCiJiLuMService {

    @Autowired
    private PiCiJiLuMMapper piCiJiLuMMapper;

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
		Date date = new Date();
		for (PiCiM pc : pcList) {
			piCiJiLuM=new PiCiJiLuM();
			Integer pcId = pc.getId();
			piCiJiLuM.setPcId(pcId);
			piCiJiLuM.setJlsjId(jlsjId);
			if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc))
				piCiJiLuM.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
			piCiJiLuM.setJllx(PiCiJiLuM.JIE_DUAN_GUO_CHENG_JI_LU);
			
			count+=piCiJiLuMMapper.add(piCiJiLuM);
		}
		return count;
	}
}

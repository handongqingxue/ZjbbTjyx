package com.uWinOPCTjyx.service.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.DateUtil;

@Service
public class PiCiMServiceImpl implements PiCiMService {

    @Autowired
    private PiCiMMapper piCiMMapper;

	public int addByBlksOBLList(List<OpcBianLiang> blksOBLList) {
		// TODO Auto-generated method stub
		int count=0;
		PiCiM piCiM=null;
		for (OpcBianLiang blksOBL : blksOBLList) {
			Integer scnf = Integer.valueOf(DateUtil.getYYYY());
			Integer scbh = piCiMMapper.getMaxScbhByScnf(scnf);
			if(scbh==null)
				scbh=1;
			else
				scbh++;
			
			String mc = blksOBL.getMc();
			int fyfhStartLoc = mc.indexOf("_")+1;
			int fyfhEndLoc = mc.length();
			Integer fyfh=Integer.valueOf(mc.substring(fyfhStartLoc, fyfhEndLoc));
			
			piCiM=new PiCiM();
			piCiM.setScnf(scnf);
			piCiM.setScbh(scbh);
			piCiM.setFyfh(fyfh);
			
			count+=piCiMMapper.add(piCiM);
		}
		return count;
	}
}

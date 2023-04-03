package com.uWinOPCTjyx.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class CanShuMServiceImpl implements CanShuMService {

    @Autowired
    private CanShuMMapper canShuMMapper;

	public Map<String, Map<String, Object>> getMap() {
		// TODO Auto-generated method stub
		List<CanShuM> canShuList=canShuMMapper.getList();
		
		Map<String, Map<String, Object>> xxMap=new HashMap<String, Map<String, Object>>();
		Map<String, Object> valMap=null;
		for (CanShuM canShu : canShuList) {
			valMap=new HashMap<String, Object>();
			Integer id = canShu.getId();
			String mc = canShu.getMc();
			String dw = canShu.getDw();
			Integer lx = canShu.getLx();
			
			valMap.put("id", id);
			valMap.put("mc", mc);
			valMap.put("dw", dw);
			valMap.put("lx", lx);
			
			xxMap.put(mc, valMap);
		}
		return xxMap;
	}
}

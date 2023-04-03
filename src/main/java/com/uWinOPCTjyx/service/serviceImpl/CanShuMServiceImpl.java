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

	public Map<String, String> getIdMap() {
		// TODO Auto-generated method stub
		List<CanShuM> canShuList=canShuMMapper.getList();
		
		Map<String, String> idMap=new HashMap<String, String>();
		for (CanShuM canShu : canShuList) {
			Integer id = canShu.getId();
			String mc = canShu.getMc();
			String dw = canShu.getDw();
			Integer lx = canShu.getLx();
			
			idMap.put(mc, id+"_"+dw+"_"+lx);
		}
		return idMap;
	}
}

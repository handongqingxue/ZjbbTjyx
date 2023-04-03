package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class JieDuanMServiceImpl implements JieDuanMService {

    @Autowired
    private JieDuanMMapper jieDuanMMapper;

	public Map<String, Map<String, Object>> getMap() {
		// TODO Auto-generated method stub
		List<JieDuanM> jieDuanList=jieDuanMMapper.getList();
		
		Map<String, Map<String, Object>> xxMap=new HashMap<String, Map<String, Object>>();
		Map<String, Object> valMap=null;
		for (JieDuanM jieDuan : jieDuanList) {
			valMap=new HashMap<String, Object>();
			Integer id = jieDuan.getId();
			String mc = jieDuan.getMc();

			valMap.put("id", id);
			valMap.put("mc", mc);
			
			xxMap.put(mc, valMap);
		}
		return xxMap;
	}
}

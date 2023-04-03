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
public class JiLuShiJianMServiceImpl implements JiLuShiJianMService {

    @Autowired
    private JiLuShiJianMMapper jiLuShiJianMMapper;

	public Map<String, Map<String, Object>> getMap() {
		// TODO Auto-generated method stub
		List<JiLuShiJianM> jlsjList=jiLuShiJianMMapper.getList();
		
		Map<String, Map<String, Object>> xxMap=new HashMap<String, Map<String, Object>>();
		Map<String, Object> valMap=null;
		for (JiLuShiJianM jlsj : jlsjList) {
			valMap=new HashMap<String, Object>();
			Integer id = jlsj.getId();
			String mc = jlsj.getMc();

			valMap.put("id", id);
			valMap.put("mc", mc);
			
			xxMap.put(mc, valMap);
		}
		return xxMap;
	}
}

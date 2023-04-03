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

	public Map<String, Integer> getIdMap() {
		// TODO Auto-generated method stub
		List<JieDuanM> jieDuanList=jieDuanMMapper.getList();
		
		Map<String, Integer> idMap=new HashMap<String, Integer>();
		for (JieDuanM jieDuan : jieDuanList) {
			Integer id = jieDuan.getId();
			String mc = jieDuan.getMc();
			
			idMap.put(mc, id);
		}
		return idMap;
	}
}

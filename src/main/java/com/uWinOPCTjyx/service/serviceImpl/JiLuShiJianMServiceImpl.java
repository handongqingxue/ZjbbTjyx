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

	public Map<String, Integer> getIdMap() {
		// TODO Auto-generated method stub
		List<JiLuShiJianM> jlsjList=jiLuShiJianMMapper.getList();
		
		Map<String, Integer> idMap=new HashMap<String, Integer>();
		for (JiLuShiJianM jlsj : jlsjList) {
			Integer id = jlsj.getId();
			String mc = jlsj.getMc();
			
			idMap.put(mc, id);
		}
		return idMap;
	}
}

package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.JiLuShiJianUMapper;
import com.uWinOPCTjyx.entity.JiLuShiJianU;
import com.uWinOPCTjyx.service.JiLuShiJianUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiLuShiJianUServiceImpl implements JiLuShiJianUService {

    @Autowired
    private JiLuShiJianUMapper jiLuShiJianUMapper;

    public Map<String, Map<String, Object>> getMap() {
        List<JiLuShiJianU> jlsjList=jiLuShiJianUMapper.getList();

        Map<String, Map<String, Object>> xxMap=new HashMap<String, Map<String, Object>>();
		Map<String, Object> valMap=null;
        for (JiLuShiJianU jlsj : jlsjList) {
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

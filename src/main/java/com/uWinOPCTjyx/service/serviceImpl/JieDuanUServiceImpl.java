package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.JieDuanUMapper;
import com.uWinOPCTjyx.entity.JieDuanM;
import com.uWinOPCTjyx.entity.JieDuanU;
import com.uWinOPCTjyx.service.JieDuanUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JieDuanUServiceImpl implements JieDuanUService {

    @Autowired
    private JieDuanUMapper jieDuanUMapper;

    public Map<String, Map<String, Object>> getMap() {
        List<JieDuanU> jieDuanList=jieDuanUMapper.getList();

        Map<String, Map<String, Object>> xxMap=new HashMap<String, Map<String, Object>>();
		Map<String, Object> valMap=null;
        for (JieDuanU jieDuan : jieDuanList) {
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

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

    public Map<String, Integer> getIdMap() {
        List<JieDuanU> jieDuanList=jieDuanUMapper.getList();

        Map<String, Integer> idMap=new HashMap<String, Integer>();
        for (JieDuanU jieDuan : jieDuanList) {
            Integer id = jieDuan.getId();
            String mc = jieDuan.getMc();
            idMap.put(mc, id);
        }
        return idMap;
    }
}

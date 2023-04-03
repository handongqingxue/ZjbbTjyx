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

    public Map<String, Integer> getIdMap() {
        List<JiLuShiJianU> jlsjList=jiLuShiJianUMapper.getList();

        Map<String, Integer> idMap=new HashMap<String, Integer>();
        for (JiLuShiJianU jlsj : jlsjList) {
            Integer id = jlsj.getId();
            String mc = jlsj.getMc();

            idMap.put(mc, id);
        }
        return idMap;
    }
}

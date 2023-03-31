package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
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

	public List<Map<String, Integer>> getIdMapListByMcList() {
		// TODO Auto-generated method stub
		List<String> mcList=new ArrayList<String>();
		mcList.add(JiLuShiJianM.JIA_LIAO_LIANG_TEXT);
		mcList.add(JiLuShiJianM.SHI_JIAN_CHA_TEXT);
		mcList.add(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT);
		mcList.add(JiLuShiJianM.PI_CI_TEXT);
		
		List<JiLuShiJianM> jlsjList=jiLuShiJianMMapper.getListByMcList(mcList);
		return null;
	}
}

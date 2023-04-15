package com.uWinOPCTjyx.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class OpcVarTestServiceImpl implements OpcVarTestService {
	
    @Autowired
    private OpcVarTestMapper opcVarTestMapper;

	public List<OpcVarTest> getListByVarNames(String varNames) {
		// TODO Auto-generated method stub
		String[] varNameArr = varNames.split(",");
		List<String> varNameList = Arrays.asList(varNameArr);
		return opcVarTestMapper.getListByVarNameList(varNameList);
	}

}

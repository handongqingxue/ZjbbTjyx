package com.uWinOPCTjyx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;

@Service
public interface OpcVarTestService {

	List<OpcVarTest> getListByVarNames(String varNames);

}

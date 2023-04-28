package com.zjbbTjyx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zjbbTjyx.entity.*;

@Service
public interface OpcVarTestService {

	List<OpcVarTest> getListByVarNames(String varNames);

}

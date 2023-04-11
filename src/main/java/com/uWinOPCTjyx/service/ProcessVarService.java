package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface ProcessVarService {

	/**
	 * 根据反应釜id集合，获取未处理的过程变量列表
	 * @param fIdList
	 * @return
	 */
	List<ProcessVar> getUnDealListByFIdList(List<Integer> fIdList);

}

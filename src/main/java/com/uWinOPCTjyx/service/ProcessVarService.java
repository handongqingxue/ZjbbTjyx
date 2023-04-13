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

	/**
	 * 添加工艺过程
	 * @param processVar
	 * @return
	 */
	int addProcessVar(ProcessVar processVar);

	/**
	 * 添加工艺过程(集合)
	 * @param processVarList
	 * @return
	 */
	int addProcessVarList(List<ProcessVar> processVarList);
}

package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface PiCiMService {

	/**
	 * 根据备料开始变量集合添加
	 * @param blksOBLList
	 * @return
	 */
	int addByBlksOBLList(List<OpcBianLiang> blksOBLList);

	/**
	 * 根据反应釜号集合，获取批次id集合
	 * @param fyfhList
	 * @return
	 */
	List<Integer> getIdListByFyfhList(List<String> fyfhList);

}

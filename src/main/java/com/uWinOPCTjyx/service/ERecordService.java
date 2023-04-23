package com.uWinOPCTjyx.service;

import java.util.List;
import java.util.Map;

import com.uWinOPCTjyx.entity.*;

public interface ERecordService {

	int addFromProVarList(List<ProcessVar> processVarList);

	/**
	 * 根据批次id查询批记录
	 * @param batchID
	 * @return
	 */
	List<ERecord> getListByBatchID(String batchID);

	/**
	 * 根据批次
	 * @param batchID
	 * @return
	 */
	int updatePCJLReportedByBatchID(String batchID);

	/**
	 * 查询全部批次记录
	 * @return
	 */
	Map<String,Object> getListByPcjl();

	/**
	 * 通过类型来获取批次记录
	 */
	List<ERecord> getListByType(String type);

	List<Map<String, Object>> getUnCreRepVarList(String batchID);
}

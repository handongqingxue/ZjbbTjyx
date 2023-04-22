package com.uWinOPCTjyx.service;

import java.util.List;

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

}

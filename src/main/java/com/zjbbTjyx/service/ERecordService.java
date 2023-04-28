package com.zjbbTjyx.service;

import java.util.List;
import java.util.Map;

import com.zjbbTjyx.entity.*;

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
	 * @param type 
	 * @return
	 */
	Map<String,Object> getListByPcjl(String type);

	/**
	 * 已生成的m类批次记录
	 * @param  type, CreateTime, endTime, batchID
	 * @return
	 */
	List<ERecord> getYscPcjlListByType(String type);

	List<Map<String, Object>> getUnCreRepVarList(String batchID);

}

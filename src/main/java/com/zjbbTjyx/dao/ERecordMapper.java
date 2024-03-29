package com.zjbbTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface ERecordMapper {

	int add(ERecord eRecord);

	Integer getMaxBatchNumByYear(@Param("year") String year);

	List<ERecord> getListByBatchID(@Param("batchID") String batchID);

	int updatePCJLReportedByBatchID(@Param("batchID") String batchID);
	
	List<ERecord> getListByPcjl();

	/**
	 * 通过类型查找批次
	 * @param record
	 * @return
	 */
	List<ERecord> getYscPcjlListByType(ERecord record);

}

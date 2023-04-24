package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface ERecordMapper {

	int add(ERecord eRecord);

	Integer getMaxBatchNumByYear(@Param("year") String year);

	List<ERecord> getListByBatchID(@Param("batchID") String batchID);

	int updatePCJLReportedByBatchID(@Param("batchID") String batchID);
	
	List<ERecord> getListByPcjl();


	/**
	 * 已生成的m类批次记录
	 * @param record
	 * @return
	 */
	List<ERecord> getMYscPcjlList(ERecord record);

}

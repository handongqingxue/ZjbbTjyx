package com.uWinOPCTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface ERecordMapper {

	int add(ERecord eRecord);

	Integer getMaxBatchNumByYear(@Param("year") String year);

}

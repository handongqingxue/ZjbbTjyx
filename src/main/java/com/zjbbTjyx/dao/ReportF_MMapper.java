package com.zjbbTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

import java.util.List;

public interface ReportF_MMapper {

	int add(ReportF_M reportF_M);

	List<ReportF_M> getReportFMList(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("batchID") String batchID);

	List<ReportF_M> getReportFMByBatchID(@Param("batchId") String batchID);
}

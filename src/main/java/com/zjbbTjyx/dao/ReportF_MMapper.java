package com.zjbbTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

import java.util.List;

public interface ReportF_MMapper {

	int add(ReportF_M reportF_M);

	List<ReportF_M> getReportFMList(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("batchID") String batchID);

	List<ReportF_M> getReportFMByBatchID(@Param("batchID") String batchID);

	int getCount(@Param("rowNumber") int rowNumber, @Param("colNumber") int colNumber, @Param("batchID") String batchID);

	int edit(ReportF_M reportF_M);

	int resetCTabInp(@Param("reportF_MList") List<ReportF_M> reportF_MList, @Param("batchID") String batchID);
}

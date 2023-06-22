package com.zjbbTjyx.dao;

import com.zjbbTjyx.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportF_UMapper {
    int add(ReportF_U reportF_U);

    List<ReportF_U> getReportFUList(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("batchID") String batchID);

    List<ReportF_U> getReportFUByBatchID(@Param("batchID") String batchID);

	int getCount(@Param("rowNumber") int rowNumber, @Param("colNumber") int colNumber, @Param("batchID") String batchID);

	int edit(ReportF_U reportF_U);

	int resetCTabInp(@Param("reportF_UList") List<ReportF_U> reportF_UList, @Param("batchID") String batchID);

}

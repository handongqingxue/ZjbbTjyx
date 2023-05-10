package com.zjbbTjyx.dao;

import com.zjbbTjyx.entity.ReportF_M;
import com.zjbbTjyx.entity.ReportF_U;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportF_UMapper {

    List<ReportF_U> getReportFUList(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("batchID") String batchID);

    List<ReportF_U> getReportFUByBatchID(@Param("batchId") String batchID);

}

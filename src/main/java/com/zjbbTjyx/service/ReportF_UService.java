package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.ReportF_U;

import java.util.List;

public interface ReportF_UService {
    /**
     * 根据条件查询u批记录
     * @param
     * @return
     */
    List<List<ReportF_U>> getReportFUPageList(String type, String startTime, String endTime, String batchID);

    /**
     * 通过batchId获取批次记录
     * @param batchID
     * @return
     */
    List<ReportF_U> getReportFUByBatchID(String batchID);
}

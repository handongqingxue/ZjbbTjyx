package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.*;

import java.util.List;

public interface ReportF_UService {
	
	/**
	 * 把批记录集合转换成报表数据
	 * @param eRecordList
	 * @return
	 */
	int addByERecordList(List<ERecord> eRecordList);
	
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

	int resetCTabInp(String batchID);
}

package com.zjbbTjyx.service;

import java.util.List;
import com.zjbbTjyx.entity.*;

public interface ReportF_MService {

	/**
	 * 把批记录集合转换成报表数据
	 * @param eRecordList
	 * @return
	 */
	int addByERecordList(List<ERecord> eRecordList);

	/**
	 * 根据条件查询m批记录
	 * @param
	 * @return
	 */
	List<List<ReportF_M>> getReportFMPageList(String type, String startTime, String endTime,String batchID);

	/**
	 * 通过batchId获取批次记录
	 * @param batchID
	 * @return
	 */
	List<ReportF_M> getReportFMByBatchID( String batchID);

	int resetCTabInp(String batchID);
}

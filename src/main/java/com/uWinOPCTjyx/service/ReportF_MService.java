package com.uWinOPCTjyx.service;

import java.util.List;
import java.util.Map;

import com.uWinOPCTjyx.entity.*;

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
	List<List<ReportF_M>> getReportFMList(String type, String createTime, String endTime,String batchID, Integer currentPage);
}

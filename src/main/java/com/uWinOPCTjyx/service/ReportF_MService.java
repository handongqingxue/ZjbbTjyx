package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface ReportF_MService {

	/**
	 * 把批记录集合转换成报表数据
	 * @param eRecordList
	 * @return
	 */
	int addByERecordList(List<ERecord> eRecordList);

}

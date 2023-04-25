package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportF_MMapper {

	int add(ReportF_M reportF_M);

	List<ReportF_M> getReportFMList(@Param("type") String type, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("batchID") String batchID);

}

package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface TriggerVarMapper {

	int getCountByVarName(@Param("varName") String varName);

	int add(TriggerVar triggerVar);

	int editByVarName(TriggerVar triggerVar);
	
	List<TriggerVar> getListByVarNameQzFIdList(@Param("varNameQz") String varNameQz, @Param("runFIdList") List<Integer> runFIdList);
}

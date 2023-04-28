package com.zjbbTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface TriggerVarMapper {

	int getCountByVarName(@Param("varName") String varName);

	int add(TriggerVar triggerVar);

	int editByVarName(TriggerVar triggerVar);
	
	List<TriggerVar> getListByFIdList(@Param("runFIdList") List<Integer> runFIdList);
}

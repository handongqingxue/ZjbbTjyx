package com.zjbbTjyx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface TriggerVarMapper {

	int getCountByVarName(@Param("varName") String varName);

	int add(TriggerVar triggerVar);

	int addByList(@Param("triggerVarList") List<TriggerVar> triggerVarList);

	int editByVarName(TriggerVar triggerVar);
	
	List<TriggerVar> getListByFIdList(@Param("runFIdList") List<Integer> runFIdList);

	List<TriggerVar> getListByVarNameList(@Param("varNameList") List<String> varNameList);

	int editByList(@Param("triggerVarList") List<TriggerVar> triggerVarList);
}

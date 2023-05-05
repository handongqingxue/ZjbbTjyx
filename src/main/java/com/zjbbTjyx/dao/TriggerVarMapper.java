package com.zjbbTjyx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface TriggerVarMapper {

	int getCountByVarName(@Param("varName") String varName);

	/**
	 * 添加变量(单条添加)
	 * @param triggerVar
	 * @return
	 */
	int add(TriggerVar triggerVar);

	/**
	 * 根据变量集合里的信息添加变量
	 * @param triggerVarList
	 * @return
	 */
	int addByList(@Param("triggerVarList") List<TriggerVar> triggerVarList);

	/**
	 * 根据变量名编辑变量
	 * @param triggerVar
	 * @return
	 */
	int editByVarName(TriggerVar triggerVar);

	/**
	 * 根据变量集合里的信息编辑变量
	 * @param triggerVarList
	 * @return
	 */
	int editByList(@Param("triggerVarList") List<TriggerVar> triggerVarList);
	
	List<TriggerVar> getListByFIdList(@Param("runFIdList") List<Integer> runFIdList);

	/**
	 * 根据变量名集合获取变量集合(有些变量名对应的变量可能没有,用于同步触发器变量时判断是否存在,是添加还是编辑)
	 * @param varNameList
	 * @return
	 */
	List<TriggerVar> getListByVarNameList(@Param("varNameList") List<String> varNameList);
}

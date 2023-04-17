package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface ProcessVarMapper {

	/**
	 * 根据反应釜id
	 * @param fIdList
	 * @return
	 */
	List<ProcessVar> getUnDealListByFIdList(@Param("fIdList") List<Integer> fIdList);

	/**
	 * 添加工艺过程
	 * @param processVar
	 * @return
	 */
	int add(ProcessVar processVar);

	/**
	 * 根据id集合更新处理状态
	 * @param dealBz
	 * @param idList
	 * @return
	 */
	int updateDealBzByIdList(@Param("dealBz") int dealBz, @Param("idList") List<Integer> idList);

}

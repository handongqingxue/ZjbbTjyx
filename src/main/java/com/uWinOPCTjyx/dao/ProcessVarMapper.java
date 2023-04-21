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

	/**
	 * 根据之前变量名获取变量值
	 * @param preName
	 * @return
	 */
	String getPreValueByPreName(@Param("preName") String preName);

	/**
	 * 删除ProcessVar表里面处理标志为1的数据
	 * @return
	 */
	int deleteProByDealBz();
}

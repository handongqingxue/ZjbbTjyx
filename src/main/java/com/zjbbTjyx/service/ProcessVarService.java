package com.zjbbTjyx.service;

import java.util.List;

import com.zjbbTjyx.entity.*;

public interface ProcessVarService {

	/**
	 * 根据反应釜id集合，获取未处理的过程变量列表
	 * @param fIdList
	 * @return
	 */
	List<ProcessVar> getUnDealListByFIdList(List<Integer> fIdList);

	/**
	 * 添加工艺过程
	 * @param proVar
	 * @return
	 */
	int add(ProcessVar proVar);

	/**
	 * 添加工艺过程(集合)
	 * @param proVarList
	 * @return
	 */
	int addFromList(List<ProcessVar> proVarList);

	/**
	 * 根据之后变量名获取差值对象
	 * @param nxtName
	 * @param nxtValue
	 * @param nxtPV
	 * @return
	 */
	ProcessVar getPtnValuePV(String nxtName, String nxtValue, ProcessVar nxtPV);

	/**
	 * 删除ProcessVar表里面处理标志为1的数据
	 * @param fId 
	 * @return
	 */
	int deleteDealed(int fId);

	boolean checkAllowAdd(String tvVarNamePre, Integer upFId);
}

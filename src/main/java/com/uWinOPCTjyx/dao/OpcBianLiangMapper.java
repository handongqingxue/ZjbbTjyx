package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface OpcBianLiangMapper {

    /**
     * 添加opc变量
     * @param opcBianliang
     * @return
     */
    int add(OpcBianLiang opcBianliang);

	/**
	 * 根据名称编辑opc变量
	 * @param opcBianliang
	 * @return
	 */
	int editByMc(OpcBianLiang opcBianliang);

	/**
	 * 根据名称获得变量数量
	 * @param mc
	 * @return
	 */
	Integer getCountByMc(@Param("mc") String mc);

	/**
	 * 根据名称获取变量信息
	 * @param mc
	 * @return
	 */
	OpcBianLiang getByMc(@Param("mc") String mc);

	/**
	 * 根据名称集合，获取变量信息列表(多用于非中间间断式变量名查询)
	 * @param mcList
	 * @return
	 */
	List<OpcBianLiang> getListByMcList(@Param("mcList") List<String> mcList);

	/**
	 * 根据反应釜号集合，获取变量信息列表(多用于中间间断式变量名查询)
	 * @param mc
	 * @param fyfhList
	 * @return
	 */
	List<OpcBianLiang> getListByFyfhList(@Param("mc") String mc, @Param("fyfhList") List<String> fyfhList);

	/**
	 * 根据计量罐号集合，获取变量信息列表(多用于中间间断式变量名查询)
	 * @param mc
	 * @param jlghList
	 * @return
	 */
	List<OpcBianLiang> getListByJlghList(@Param("mc") String mc, @Param("jlghList") List<Integer> jlghList);
}

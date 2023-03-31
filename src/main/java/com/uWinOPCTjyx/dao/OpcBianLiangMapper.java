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

	List<OpcBianLiang> getListByMcList(@Param("mcList") List<String> mcList);

	int updateSzyssByMcList(@Param("szyss") int szyss, @Param("mcList") List<String> mcList);
}

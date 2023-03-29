package com.uWinOPCTjyx.dao;

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
	int getCountByMc(@Param("mc") String mc);
}

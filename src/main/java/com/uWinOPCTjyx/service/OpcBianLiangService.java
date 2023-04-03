package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface OpcBianLiangService {

    /**
     * 编辑opc变量
     * @param opcBianliang
     * @return
     */
    int edit(OpcBianLiang opcBianliang);

	int editFromList(List<OpcBianLiang> opcBianLiangList);

	/**
	 * 根据名称前缀获得上升数值列表
	 * @param mcQz
	 * @return
	 */
	List<OpcBianLiang> getUpSzListByMcQz(String mcQz);

}

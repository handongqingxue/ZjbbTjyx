package com.uWinOPCTjyx.service;

import com.uWinOPCTjyx.entity.*;

import java.util.List;

public interface PiCiUService {

    int add(PiCiU piCiU);
    
    List<PiCiU> getList();

    /**
     * 根据备料开始变量集合添加
     * @param blksOBLList
     * @return
     */
    int addByBlksOBLList(List<OpcBianLiang> blksOBLList);

    /**
     * 根据反应釜号集合，获取批次id集合
     * @param fyfhList
     * @return
     */
    List<Integer> getIdListByFyfhList(List<String> fyfhList);

    /**
     * 根据反应釜号集合，获取批次集合
     * @param fyfhList
     * @return
     */
    List<PiCiU> getListByFyfhList(List<String> fyfhList);
}

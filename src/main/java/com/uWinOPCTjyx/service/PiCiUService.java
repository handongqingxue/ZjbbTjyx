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
}

package com.uWinOPCTjyx.service;

import com.uWinOPCTjyx.entity.ShengChanJiLu;

import java.util.List;

public interface ShengChanJiLuService {
    List<ShengChanJiLu> getList();

    int addSCJL(ShengChanJiLu shengChanJiLu);
}

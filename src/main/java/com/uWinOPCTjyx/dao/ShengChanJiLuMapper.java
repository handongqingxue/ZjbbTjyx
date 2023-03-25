package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.ShengChanJiLu;

import java.util.List;

public interface ShengChanJiLuMapper {

    List<ShengChanJiLu> getList();

    int addSCJL(ShengChanJiLu shengChanJiLu);
}

package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.JiLuShiJianU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JiLuShiJianUMapper {

    Integer getIdByMc(@Param("mc") String mc);

    List<JiLuShiJianU> getList();

}

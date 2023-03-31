package com.uWinOPCTjyx.dao;

import org.apache.ibatis.annotations.Param;

public interface JiLuShiJianMMapper {

	Integer getIdByMc(@Param("mc") String mc);
}

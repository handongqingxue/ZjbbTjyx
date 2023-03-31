package com.uWinOPCTjyx.dao;

import org.apache.ibatis.annotations.Param;

public interface JieDuanMMapper {

	Integer getIdByMc(@Param("mc") String mc);
}

package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface JieDuanMMapper {

	Integer getIdByMc(@Param("mc") String mc);

	List<JieDuanM> getList();
}

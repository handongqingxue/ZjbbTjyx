package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.JiLuShiJianM;

public interface JiLuShiJianMMapper {

	Integer getIdByMc(@Param("mc") String mc);

	List<JiLuShiJianM> getList();
}

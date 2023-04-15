package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface OpcVarTestMapper {

	List<OpcVarTest> getListByVarNameList(@Param("varNameList") List<String> varNameList);

}

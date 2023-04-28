package com.zjbbTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface OpcVarTestMapper {

	List<OpcVarTest> getListByVarNameList(@Param("varNameList") List<String> varNameList);

}

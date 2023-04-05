package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface PiCiJiLuMMapper {

    int add(PiCiJiLuM piCiJiLuM);

	List<PiCiJiLuM> getJdgcListByPcIdList(@Param("pcIdList") List<Integer> pcIdList, @Param("jlsjId") Integer jlsjId, @Param("jdId") Integer jdId);
}

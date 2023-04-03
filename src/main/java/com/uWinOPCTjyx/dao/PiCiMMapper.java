package com.uWinOPCTjyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface PiCiMMapper {

    int add(PiCiM piCiM);

	Integer getMaxScbhByScnf(@Param("scnf") Integer scnf);

	List<Integer> getIdListByFyfhList(@Param("fyfhList") List<String> fyfhList);

	List<PiCiM> getListByFyfhList(@Param("fyfhList") List<String> fyfhList);
}

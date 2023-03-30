package com.uWinOPCTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface PiCiMMapper {

    int add(PiCiM piCiM);

	Integer getMaxScbhByScnf(@Param("scnf") Integer scnf);
}

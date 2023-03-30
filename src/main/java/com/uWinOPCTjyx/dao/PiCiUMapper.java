package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PiCiUMapper {

    int add(PiCiU piCiU);

    List<PiCiU> getList();
}

package com.uWinOPCTjyx.dao;

import com.uWinOPCTjyx.entity.JieDuanM;
import com.uWinOPCTjyx.entity.JieDuanU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JieDuanUMapper {

    Integer getIdByMc(@Param("mc") String mc);

    List<JieDuanU> getList();
}

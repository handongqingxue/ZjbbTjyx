package com.uWinOPCTjyx.service;

import com.uWinOPCTjyx.entity.*;

import java.util.List;
import java.util.Map;

public interface PiCiJiLuUService {

    int addPcgcFromPcIdList(List<Integer> pcIdList, Map<String, Object> jlsjMap);

    int addJdgcFromPcList(List<PiCiU> pcList, Map<String, Object> jlsjMap, Map<String, Object> jieDuanMap);
}

package com.uWinOPCTjyx.service;

import java.util.List;
import java.util.Map;

import com.uWinOPCTjyx.entity.*;

public interface PiCiJiLuMService {

	int addPcgcFromPcIdList(List<Integer> pcIdList, Map<String, Object> jlsjMap);

	int addJdgcFromPcList(List<PiCiM> pcList, Map<String, Object> jlsjMap, Map<String, Object> jieDuanMap);

	int addCsjl(List<PiCiM> pcList, Map<String, Object> jqsjjlzlCsMap, Map<String, Object> jlsjMap);

}

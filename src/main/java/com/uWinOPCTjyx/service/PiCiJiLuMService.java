package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface PiCiJiLuMService {

	int addPcgcFromPcIdList(List<Integer> pcIdList, Integer jlsjId);

	int addJdgcFromPcList(List<PiCiM> pcList, Integer jlsjId, String jlsjMc, Integer jieDuanId);

}

package com.uWinOPCTjyx.service;

import com.uWinOPCTjyx.entity.PiCiM;
import com.uWinOPCTjyx.entity.PiCiU;

import java.util.List;

public interface PiCiJiLuUService {

    int addPcgcFromPcIdList(List<Integer> pcIdList, Integer jlsjId);

    int addJdgcFromPcList(List<PiCiU> pcList, Integer jlsjId, String jlsjMc, Integer jieDuanId);
}

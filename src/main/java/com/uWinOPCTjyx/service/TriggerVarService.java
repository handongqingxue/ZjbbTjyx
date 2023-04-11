package com.uWinOPCTjyx.service;

import java.util.List;

import com.uWinOPCTjyx.entity.*;

public interface TriggerVarService {

	int editFromList(List<TriggerVar> triggerVarList);

	List<TriggerVar> getListByVarNameQzFIdList(String varNameQz, List<Integer> runFIdList);
}

package com.zjbbTjyx.service;

import java.util.List;

import com.zjbbTjyx.entity.*;

public interface TriggerVarService {

	int editFromList(List<TriggerVar> triggerVarList);

	List<TriggerVar> getListByFIdList(List<Integer> runFIdList);
}

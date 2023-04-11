package com.uWinOPCTjyx.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class TriggerVarServiceImpl implements TriggerVarService {
	
    @Autowired
    private TriggerVarMapper triggerVarMapper;

	public int editFromList(List<TriggerVar> triggerVarList) {
		// TODO Auto-generated method stub
		int editCount=0;
		for (TriggerVar triggerVar : triggerVarList) {
			String varName = triggerVar.getVarName();
			int count=triggerVarMapper.getCountByVarName(varName);
			if(count==0) {
				editCount+=triggerVarMapper.add(triggerVar);
			}
			else
				editCount+=triggerVarMapper.editByVarName(triggerVar);
		}
		return editCount;
	}

	public List<TriggerVar> getListByVarNameQzFIdList(String varNameQz, List<Integer> runFIdList) {
		// TODO Auto-generated method stub
		return triggerVarMapper.getListByVarNameQzFIdList(varNameQz,runFIdList);
	}

}

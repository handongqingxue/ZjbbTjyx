package com.uWinOPCTjyx.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class ProcessVarServiceImpl implements ProcessVarService {

    @Autowired
    private ProcessVarMapper processVarMapper;

	public List<ProcessVar> getUnDealListByFIdList(List<Integer> fIdList) {
		// TODO Auto-generated method stub
		return processVarMapper.getUnDealListByFIdList(fIdList);
	}

	public int addProcessVar(ProcessVar processVar) {
		return processVarMapper.addProcessVar(processVar);
	}

	public int addProcessVarList(List<ProcessVar> processVarList) {
		int count = 0;
		if (processVarList.size()>0){
			for (ProcessVar processVar : processVarList) {
				processVarMapper.addProcessVar(processVar);
				count++;
			}
		}
		return count;
	}

}

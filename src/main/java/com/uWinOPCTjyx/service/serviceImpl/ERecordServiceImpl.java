package com.uWinOPCTjyx.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.*;

@Service
public class ERecordServiceImpl implements ERecordService {

    @Autowired
    private ERecordMapper eRecordMapper;

	public int addFromProVarList(List<ProcessVar> processVarList) {
		// TODO Auto-generated method stub
		int count=0;
		ERecord eRecord=null;
		for (ProcessVar processVar : processVarList) {
			eRecord=new ERecord();
			String pvVarName = processVar.getVarName();
			
			if(pvVarName.contains(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT)) {
				Float pvVarValue = processVar.getVarValue();
				Date date=new Date();
				String recordTime = DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR_TO_SECOND);
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.KG);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				//eRecord.setBatchID(batchID);
				eRecord.setPhaseName("YSD101");
			}
			count+=eRecordMapper.add(eRecord);
		}
		return count;
	}
}

package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<ERecord> eRecordList=new ArrayList<ERecord>();
		ERecord eRecord=null;
		Date date=new Date();
		String recordTime = DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR_TO_SECOND);
		for (ProcessVar processVar : processVarList) {
			eRecord=new ERecord();
			String pvVarName = processVar.getVarName();
			
			if(pvVarName.contains(Constant.BEI_LIAO_KAI_SHI_TEXT)) {
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG_TEXT)) {
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.KG);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				//eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD101);
			}
			eRecordList.add(eRecord);
		}
		//count+=eRecordMapper.add(eRecord);
		return count;
	}
	
	private List<Map<String,Object>> getFIDListFromProVarList(List<ProcessVar> proVarList) {
		List<Map<String,Object>> fMapList=new ArrayList<Map<String,Object>>();
		Map<String,Object> fMap=null;
		for (ProcessVar proVar : proVarList) {
			boolean exist=false;
			Integer pvFId = proVar.getFId();
			for (Map<String,Object> fMapItem : fMapList) {
				Integer fId = Integer.valueOf(fMapItem.get("fId").toString());
				if(fId==pvFId) {
					exist=false;
					break;
				}
			}
			if(exist) {
				Integer fId = proVar.getFId();
				String recType = proVar.getRecType();
				
				fMap=new HashMap<String, Object>();
				fMap.put("fId", fId);
				fMap.put("recType", recType);
				
				fMapList.add(fMap);
			}
		}
		return fMapList;
	}
	
	private HashMap<String,String> getBatchIDMap(Date date, List<Map<String,Object>> fMapList) {
		HashMap<String,String> batchIDMap=new HashMap<String,String>();
		String year=DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR);
		Integer maxBatchNum = eRecordMapper.getMaxBatchNumByYear(year);
		for (int i = 0; i < fMapList.size(); i++) {
			Map<String, Object> fMap = fMapList.get(i);
			String batchID="";
		}
		return batchIDMap;
	}
}

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
    private ProcessVarMapper processVarMapper;
    @Autowired
    private ERecordMapper eRecordMapper;

	public int addFromProVarList(List<ProcessVar> processVarList) {
		// TODO Auto-generated method stub
		int count=0;
		
		List<ERecord> eRecordList=new ArrayList<ERecord>();
		ERecord eRecord=null;
		Date date=new Date();
		String recordTime = DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR_TO_SECOND);

		List<Map<String,Object>> fMapList=getFMapListFromProVarList(processVarList);
		HashMap<Integer,String> batchIDMap=getBatchIDMap(date,fMapList);
		
		List<Integer> pvIdList=new ArrayList<Integer>();
		for (ProcessVar processVar : processVarList) {
			Integer pvId = processVar.getId();
			String pvVarName = processVar.getVarName();
			
			pvIdList.add(pvId);
			
			if(pvVarName.startsWith(Constant.BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//备料开始上升沿时间 //生产编号阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
				}
				eRecord.setPreValue(updateTime);//备料开始时间是数据采集过程中记录的，与记录时间不是一回事
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.FAN_YING_JIE_SHU_SHI_JIAN+Constant.SHANG_SHENG_YAN)) {//反应结束上升沿时间
				String updateTime = processVar.getUpdateTime();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				
				eRecord=getFromList(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					eRecord=new ERecord();
					eRecord.setVarName(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
				}
				eRecord.setNxtValue(updateTime);//反应结束时间是数据采集过程中记录的，与记录时间不是一回事
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.SHENG_CHAN_GONG_SHI)) {//生产工时
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.KAI_SHI_DAO_JIE_SHU_SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.SHENG_CHAN_RI_QI)) {//生产日期
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(recordTime);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				
				eRecordList.add(eRecord);
			}//生产编号阶段结束
			else if(pvVarName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||//甲醛实际进料重量    //YSD101阶段开始
					pvVarName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)) {//加水实际重量
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.KG);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD101);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//甲醛备料开始上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
				}
				eRecord.setPreValue(updateTime);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//甲醛放料完成上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//甲醛放料完成上升沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD101);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)) {//加碱前PH输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD101);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//甲醛备料开始上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//甲醛放料完成上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA)) {//从甲醛备料开始到甲醛放料完成釜重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				String unit = processVar.getUnit();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setUnit(unit);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA)) {//甲醛备料开始到甲醛放料完成时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				String unit = processVar.getUnit();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setUnit(unit);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//YSD101阶段结束
			else if(pvVarName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)) {//加碱量提示   //YSD109阶段开始
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD109);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)) {//加碱后PH输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD109);
				
				eRecordList.add(eRecord);
			}//YSD109阶段结束
			else if(pvVarName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)&&pvVarName.endsWith(Constant.CHENG_ZHONG)) {//助剂计量罐1-2称重    //YSD106阶段开始
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD106);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//允许一次加助剂上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//所有助剂加料完成1上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//所有助剂加料完成1上升沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				//eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD106);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//允许一次加助剂上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//所有助剂加料完成1上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA)) {//允许一次加助剂到所有助剂加料完成1重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA)) {//允许一次加助剂到所有助剂加料完成1时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//YSD106阶段结束
			else if(pvVarName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)) {//粉料重量设定    //YSD102阶段开始
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD102);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//釜尿素放料阀上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN)) {//釜尿素放料阀下降沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//釜尿素放料阀下降沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				//eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD102);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)) {//加粉料PH输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD102);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//釜尿素放料阀上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//釜尿素放料阀下降沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG+Constant.CHA)) {//釜尿素放料阀重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.ZHONG_LIANG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN+Constant.CHA)) {//釜尿素放料阀时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//YSD102阶段结束
			else if(pvVarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//升温开始上升沿时间    //开始升温阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				ERecord eRecord1 = getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord1==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord1=new ERecord();
					eRecord1.setVarName(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setPhaseName(Constant.KAI_SHI_SHENG_WEN);

					eRecordList.add(eRecord1);
				}
				eRecord1.setPreValue(updateTime);
				
				ERecord eRecord2 = getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord2==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord2=new ERecord();
					eRecord2.setVarName(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN);
					eRecord2.setRecType(pvRecType);
					eRecord2.setFId(pvFId);
					eRecord2.setRecordTime(recordTime);
					eRecord2.setBatchID(batchID);
					eRecord2.setPhaseName(Constant.TING_QI);

					eRecordList.add(eRecord2);
				}
				eRecord2.setPreValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.ZHENG_QI_YA_LI)) {//蒸汽压力
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.MPA);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.KAI_SHI_SHENG_WEN);

				eRecordList.add(eRecord);
			}//开始升温阶段结束
			else if(pvVarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//温度85与二次投料提醒上升沿时间    //PH检测阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.PH_JIAN_CE);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//温度85与二次投料提醒上升沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				//eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.PH_JIAN_CE);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU_ZHI)) {//二次投料PH输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.PH_JIAN_CE);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN+Constant.CHA)){//升温开始到温度85与二次投料提醒时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.PH_JIAN_CE);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//PH检测阶段结束
			else if(pvVarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//允许二次加助剂上升沿时间    //YSD104阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);				
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//所有助剂加料完成2上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//所有助剂加料完成2上升沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvUnit = processVar.getUnit();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				//eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setUnit(pvUnit);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.YSD104);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//允许二次加助剂上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//所有助剂加料完成2上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA)) {//允许二次加助剂到所有助剂加料完成2釜重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN+Constant.CHA)) {//允许二次加助剂到所有助剂加料完成2时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");				
			}//YSD104阶段结束
			else if(pvVarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//升温完成上升沿时间    //停汽阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.TING_QI);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//升温完成上升沿反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.TING_QI);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.WEN_DU_98_PH)) {//温度98PH
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.TING_QI);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA)) {//升温开始到升温完成时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				String unit = processVar.getUnit();

				eRecord=getFromList(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setUnit(unit);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.TING_QI);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.CE_LIANG_BSWD_SRZ)||//测量冰水雾点输入值
					pvVarName.startsWith(Constant.CE_20_WU_DIAN_SRZ)) {//测20雾点输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.BAO_WEN);

				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//聚合终点反应釜温度
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setUnit(Constant.WEN_DU_DAN_WEI_SIGN);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.BAO_WEN);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)) {//停热降温水数输入值
				Float pvVarValue = processVar.getVarValue();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(pvVarValue+"");
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
				eRecord.setBatchID(batchID);
				eRecord.setPhaseName(Constant.BAO_WEN);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.SHI_JIAN)) {//聚合终点时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.LENG_QUE);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//降温完成上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.LENG_QUE);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//降温完成上升沿反应釜温度
				
			}
		}
		
		//处理完批记录集合的信息后，通过循环一起插入数据库表
		for (ERecord eRecordItem : eRecordList) {
			count+=eRecordMapper.add(eRecordItem);
		}
		
		if(pvIdList.size()>0) {
			processVarMapper.updateDealBzByIdList(ProcessVar.YCL,pvIdList);
		}
		
		return count;
	}
	
	/**
	 * 根据未处理的过程变量，获取这些变量牵涉到的反应釜Map集合。为后面生成批次编号做准备
	 * @param proVarList
	 * @return
	 */
	private List<Map<String,Object>> getFMapListFromProVarList(List<ProcessVar> proVarList) {
		List<Map<String,Object>> fMapList=new ArrayList<Map<String,Object>>();
		Map<String,Object> fMap=null;
		for (ProcessVar proVar : proVarList) {
			boolean exist=false;
			Integer pvFId = proVar.getFId();
			for (Map<String,Object> fMapItem : fMapList) {
				Integer fId = Integer.valueOf(fMapItem.get("fId").toString());
				if(fId==pvFId) {
					exist=true;
					break;
				}
			}
			if(!exist) {
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
	
	/**
	 * 每个釜对应一个批次编号
	 * @param date
	 * @param fMapList
	 * @return
	 */
	private HashMap<Integer,String> getBatchIDMap(Date date, List<Map<String,Object>> fMapList) {
		HashMap<Integer,String> batchIDMap=new HashMap<Integer,String>();
		String year=DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR);
		Integer maxBatchNum = eRecordMapper.getMaxBatchNumByYear(year);
		maxBatchNum=maxBatchNum==null?0:maxBatchNum;
		for (int i = 0; i < fMapList.size(); i++) {
			Map<String, Object> fMap = fMapList.get(i);
			Integer fId = Integer.valueOf(fMap.get("fId").toString());
			String recType = fMap.get("recType").toString();
			String batchNumStr=null;
			int batchNum=maxBatchNum+i+1;
			if(batchNum<10)
				batchNumStr="00000"+batchNum;
			else if(batchNum<100)
				batchNumStr="0000"+batchNum;
			else if(batchNum<1000)
				batchNumStr="000"+batchNum;
			else if(batchNum<10000)
				batchNumStr="00"+batchNum;
			else if(batchNum<100000)
				batchNumStr="0"+batchNum;
			else
				batchNumStr=""+batchNum;
			String batchID=recType+"A"+year+batchNumStr;
			
			batchIDMap.put(fId, batchID);
		}
		return batchIDMap;
	}
	
	/**
	 * 根据变量名和批次编号，从批记录集合里验证是否存在
	 * @param varName
	 * @param batchID
	 * @param eRecordList
	 * @return
	 */
	private ERecord getFromList(String varName, String batchID, List<ERecord> eRecordList) {
		ERecord eRecord=null;
		for (ERecord eRecordItem : eRecordList) {
			String itemVarName = eRecordItem.getVarName();
			String itemBatchID = eRecordItem.getBatchID();
			if(varName.equals(itemVarName)&&batchID.equals(itemBatchID)) {
				eRecord=eRecordItem;
				break;
			}
		}
		return eRecord;
	}
}

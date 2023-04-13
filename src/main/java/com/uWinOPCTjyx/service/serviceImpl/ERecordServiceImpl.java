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

		List<Map<String,Object>> fMapList=getFMapListFromProVarList(processVarList);
		HashMap<Integer,String> batchIDMap=getBatchIDMap(date,fMapList);
		
		
		for (ProcessVar processVar : processVarList) {
			String pvVarName = processVar.getVarName();
			
			if(pvVarName.contains(Constant.BEI_LIAO_KAI_SHI_SHI_JIAN+Constant.SHANG_SHENG_YAN)) {//备料开始上升沿时间
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
			}
			else if(pvVarName.contains(Constant.FAN_YING_JIE_SHU_SHI_JIAN+Constant.SHANG_SHENG_YAN)) {//反应结束上升沿时间
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
			}
			else if(pvVarName.contains(Constant.SHENG_CHAN_RI_QI)) {//生产日期
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();

				eRecord=new ERecord();
				eRecord.setVarName(pvVarName);
				eRecord.setVarValue(recordTime);
				eRecord.setRecType(pvRecType);
				eRecord.setFId(pvFId);
				eRecord.setRecordTime(recordTime);
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)) {//甲醛实际进料重量
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
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//甲醛备料开始上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//甲醛放料完成上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//甲醛放料完成上升沿反应釜温度
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
			}
			else if(pvVarName.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.SHANG_SHENG_YAN+Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)) {//加碱PH值正常上升沿加碱前PH输入值
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
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG)) {//甲醛备料开始上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.CHENG_ZHONG)) {//甲醛放料完成釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA)) {//从甲醛备料开始到甲醛放料完成釜重量差
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
			}
			else if(pvVarName.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.JIA_JIAN_LIANG_TI_SHI)) {//加碱PH值正常加碱量提示
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
				eRecord.setPhaseName(Constant.YSD109);
			}
			else if(pvVarName.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)) {//加碱PH值正常加碱后PH输入值
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
			}
			else if(pvVarName.contains(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.ZHU_JI_JI_LIANG_GUAN+Constant.CHENG_ZHONG)) {//加碱PH值正常助剂计量罐称重
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
				eRecord.setPhaseName(Constant.YSD106);
			}
			else if(pvVarName.contains(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHI_JIAN)) {//允许一次加助剂时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN)) {//所有助剂加料完成1时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FAN_YING_FU+Constant.WEN_DU)) {//所有助剂加料完成1反应釜温度
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
				eRecord.setPhaseName(Constant.YSD106);
			}
			else if(pvVarName.contains(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.FU+Constant.CHENG_ZHONG)) {//允许一次加助剂釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.CHENG_ZHONG)) {//所有助剂加料完成1釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.ZHONG_LIANG+Constant.CHA)) {//所有助剂加料完成1重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA)) {//所有助剂加料完成1时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.SHI_JIAN+Constant.CHA, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.SHI_JIAN+Constant.CHA);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.contains(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)) {
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
				eRecord.setPhaseName(Constant.YSD102);
			}
			else if(pvVarName.contains(Constant.JIA_FEN_LIAO_TI_XING)) {
				
			}
			else if(pvVarName.contains(Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN)) {//升温完成时间???????
				
			}
			else if(pvVarName.contains(Constant.SHENG_WEN_WAN_CHENG+Constant.FAN_YING_FU+Constant.WEN_DU)) {//升温完成反应釜温度
				
			}
			else if(pvVarName.contains(Constant.ZUI_GAO_WEN_PH_LRZ)) {//最高温PH录入值
				
			}
			else if(pvVarName.contains(Constant.WEN_DU_98_PH)) {//温度98PH
				
			}
			else if(pvVarName.contains(Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA)) {//升温开始到升温完成时间差
				
			}
			else if(pvVarName.contains(Constant.CE_LIANG_BSWD_SRZ)) {//测量冰水雾点输入值
				
			}
			else if(pvVarName.contains(Constant.CE_20_WU_DIAN_SRZ)) {//测20雾点输入值
				
			}
			else if(pvVarName.contains(Constant.JU_HE_ZHONG_DIAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//聚合终点反应釜温度
				
			}
			else if(pvVarName.contains(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)) {//停热降温水数输入值
				
			}
			else if(pvVarName.contains(Constant.JU_HE_ZHONG_DIAN+Constant.SHI_JIAN)) {//聚合终点时间
				
			}
			else if(pvVarName.contains(Constant.JIANG_WEN_WAN_CHENG+Constant.SHI_JIAN)) {//降温完成时间
				
			}
			else if(pvVarName.contains(Constant.JIANG_WEN_WAN_CHENG+Constant.FAN_YING_FU+Constant.WEN_DU)) {//降温完成反应釜温度
				
			}
			eRecordList.add(eRecord);
		}
		
		//处理完批记录集合的信息后，通过循环一起插入数据库表
		for (ERecord eRecordItem : eRecordList) {
			count+=eRecordMapper.add(eRecordItem);
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

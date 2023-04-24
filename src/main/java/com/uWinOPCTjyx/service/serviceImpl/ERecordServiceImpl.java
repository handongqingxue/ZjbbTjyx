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
			
			if(pvVarName.startsWith(ERecord.BLKSSSYSJ)) {//备料开始上升沿时间 //生产编号阶段开始
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String pvFName = OpcUtil.getFNameByFIdRecType(pvFId, pvRecType);
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				ERecord eRecord1=getFromList(Constant.PI_CI_JI_LU, batchID, eRecordList);
				if(eRecord1==null) {
					eRecord1=new ERecord();
					eRecord1.setVarName(Constant.PI_CI_JI_LU);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setRemark(ERecord.WSCBB+"");
					
					eRecordList.add(eRecord1);
				}
				eRecord1.setPreValue(updateTime);
				
				ERecord eRecord2=new ERecord();
				eRecord2.setVarName(Constant.SHENG_CHAN_BIAN_HAO);
				eRecord2.setVarValue(batchID.substring(8));
				eRecord2.setRecType(pvRecType);
				eRecord2.setFId(pvFId);
				eRecord2.setRecordTime(recordTime);
				eRecord2.setBatchID(batchID);
				eRecord2.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
				
				eRecordList.add(eRecord2);
				
				ERecord eRecord3=new ERecord();
				eRecord3.setVarName(Constant.FAN_YING_FU);
				eRecord3.setVarValue(pvFName);
				eRecord3.setRecType(pvRecType);
				eRecord3.setFId(pvFId);
				eRecord3.setRecordTime(recordTime);
				eRecord3.setBatchID(batchID);
				eRecord3.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
				
				eRecordList.add(eRecord3);

				ERecord eRecord4=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);//开始到结束时间
				if(eRecord4==null) {
					eRecord4=new ERecord();
					eRecord4.setVarName(ERecord.KSDJSSJ);
					eRecord4.setRecType(pvRecType);
					eRecord4.setFId(pvFId);
					eRecord4.setRecordTime(recordTime);
					eRecord4.setBatchID(batchID);
					eRecord4.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
					
					eRecordList.add(eRecord4);
				}
				eRecord4.setPreValue(updateTime);//备料开始时间是数据采集过程中记录的，与记录时间不是一回事
				
			}
			else if(pvVarName.startsWith(ERecord.FYJSSSYSJ)) {//反应结束上升沿时间
				String updateTime = processVar.getUpdateTime();
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				
				ERecord eRecord1=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);
				if(eRecord1==null) {
					eRecord1=new ERecord();
					eRecord1.setVarName(ERecord.KSDJSSJ);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
					
					eRecordList.add(eRecord1);
				}
				eRecord1.setNxtValue(updateTime);//反应结束时间是数据采集过程中记录的，与记录时间不是一回事
				
				ERecord eRecord2=getFromList(Constant.PI_CI_JI_LU, batchID, eRecordList);
				if(eRecord2==null) {
					eRecord2=new ERecord();
					eRecord2.setVarName(Constant.PI_CI_JI_LU);
					eRecord2.setRecType(pvRecType);
					eRecord2.setFId(pvFId);
					eRecord2.setRecordTime(recordTime);
					eRecord2.setBatchID(batchID);
					eRecord2.setRemark(ERecord.WSCBB+"");
					
					eRecordList.add(eRecord2);
				}
				eRecord2.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.BLKSDFYJSSJC)) {//备料开始到反应结束时间差
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				
				ERecord eRecord1=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);
				if(eRecord1==null) {
					eRecord1=new ERecord();
					eRecord1.setVarName(ERecord.KSDJSSJ);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setPhaseName(Constant.SHENG_CHAN_BIAN_HAO);
					
					eRecordList.add(eRecord1);
				}
				eRecord1.setPtnValue(varValue+"");
				
				ERecord eRecord2=getFromList(Constant.PI_CI_JI_LU, batchID, eRecordList);
				if(eRecord2==null) {
					eRecord2=new ERecord();
					eRecord2.setVarName(Constant.PI_CI_JI_LU);
					eRecord2.setRecType(pvRecType);
					eRecord2.setFId(pvFId);
					eRecord2.setRecordTime(recordTime);
					eRecord2.setBatchID(batchID);
					eRecord2.setRemark(ERecord.WSCBB+"");
					
					eRecordList.add(eRecord2);
				}
				eRecord2.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(Constant.SHENG_CHAN_GONG_SHI)) {//生产工时
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.KSDJSSJ);
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
			else if(pvVarName.startsWith(ERecord.JQBLKSSSYSJ)) {//甲醛备料开始上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.JQBLKSDJQFLWCSJ);
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

				eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.JQBLKSDJQFLWCSJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.JQFLWCSSYFYFWD)) {//甲醛放料完成上升沿反应釜温度
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
			else if(pvVarName.startsWith(ERecord.JQBLKSSSYFCZ)) {//甲醛备料开始上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.JQBLKSDJQFLWCFZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.JQFLWCSSYFCZ)) {//甲醛放料完成上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.JQBLKSDJQFLWCFZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD101);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCFZLC)) {//从甲醛备料开始到甲醛放料完成釜重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				String unit = processVar.getUnit();

				eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.JQBLKSDJQFLWCFZL);
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
			else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCSJC)) {//甲醛备料开始到甲醛放料完成时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				String unit = processVar.getUnit();

				eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
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
			else if(pvVarName.startsWith(ERecord.YXYCJZJSSYSJ)) {//允许一次加助剂上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1SJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYSJ)) {//所有助剂加料完成1上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1SJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYFYFWD)) {//所有助剂加料完成1上升沿反应釜温度
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
			else if(pvVarName.startsWith(ERecord.YXYCJZJSSYFCZ)) {//允许一次加助剂上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1FZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYFCZ)) {//所有助剂加料完成1上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1FZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.YXYCJZJDSYZJJLWC1FZLC)) {//允许一次加助剂到所有助剂加料完成1重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1FZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD106);
					
					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.YXYCJZJDSYZJJLWC1SJC)) {//允许一次加助剂到所有助剂加料完成1时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1SJ);
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
			else if(pvVarName.startsWith(ERecord.FNSFLFSSYSJ)) {//釜尿素放料阀上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
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

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.FNSFLFXJYFYFWD)) {//釜尿素放料阀下降沿反应釜温度
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
			else if(pvVarName.startsWith(ERecord.FNSFLFSSYFCZ)) {//釜尿素放料阀上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.FNSFLFXJYFCZ)) {//釜尿素放料阀下降沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYZLC)) {//釜尿素放料阀重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYSJC)) {//釜尿素放料阀时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD102);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//YSD102阶段结束
			else if(pvVarName.startsWith(ERecord.SWKSSSYSJ)) {//升温开始上升沿时间    //开始升温阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				ERecord eRecord1 = getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
				if(eRecord1==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord1=new ERecord();
					eRecord1.setVarName(ERecord.SWKSDWD85YECTLTXSJ);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setPhaseName(Constant.KAI_SHI_SHENG_WEN);

					eRecordList.add(eRecord1);
				}
				eRecord1.setPreValue(updateTime);
				
				ERecord eRecord2 = getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
				if(eRecord2==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord2=new ERecord();
					eRecord2.setVarName(ERecord.SWKSDSWWCSJ);
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
			else if(pvVarName.startsWith(ERecord.WD85YECTLTXSSYSJ)) {//温度85与二次投料提醒上升沿时间    //PH检测阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.SWKSDWD85YECTLTXSJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.PH_JIAN_CE);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.WD85YECTLTXSSYFYFWD)) {//温度85与二次投料提醒上升沿反应釜温度
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
			else if(pvVarName.startsWith(ERecord.SWKSDWD85YECTLTXSJC)){//升温开始到温度85与二次投料提醒时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.SWKSDWD85YECTLTXSJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.PH_JIAN_CE);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}//PH检测阶段结束
			else if(pvVarName.startsWith(ERecord.YXECJZJSSYSJ)) {//允许二次加助剂上升沿时间    //YSD104阶段开始
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2SJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(updateTime);				
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYSJ)) {//所有助剂加料完成2上升沿时间
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2SJ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYFYFWD)) {//所有助剂加料完成2上升沿反应釜温度
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
			else if(pvVarName.startsWith(ERecord.YXECJZJSSYFCZ)) {//允许二次加助剂上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2FCZ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPreValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYFCZ)) {//所有助剂加料完成2上升沿釜称重
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2FCZ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setNxtValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.YXECJZJDSYZJJLWC2FZLC)) {//允许二次加助剂到所有助剂加料完成2釜重量差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2FCZ);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD104);

					eRecordList.add(eRecord);
				}
				eRecord.setPtnValue(varValue+"");
			}
			else if(pvVarName.startsWith(ERecord.YXECJZJDSYZJJLWC2SJC)) {//允许二次加助剂到所有助剂加料完成2时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();

				eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.YXECJZJDSYZJJLWC2SJ);
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

				eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.SWKSDSWWCSJ);
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

				eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
				if(eRecord==null) {
					String pvRecType = processVar.getRecType();
					
					eRecord=new ERecord();
					eRecord.setVarName(ERecord.SWKSDSWWCSJ);
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
			else if(pvVarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//聚合终点上升沿反应釜温度
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
			else if(pvVarName.startsWith(Constant.JU_HE_ZHONG_DIAN+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN)) {//聚合终点上升沿时间
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
				String pvRecType = processVar.getRecType();
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				String updateTime = processVar.getUpdateTime();
				
				ERecord eRecord1=getFromList(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN, batchID, eRecordList);
				if(eRecord1==null) {
					eRecord1=new ERecord();
					eRecord1.setVarName(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN);
					eRecord1.setRecType(pvRecType);
					eRecord1.setFId(pvFId);
					eRecord1.setRecordTime(recordTime);
					eRecord1.setBatchID(batchID);
					eRecord1.setPhaseName(Constant.LENG_QUE);
					
					eRecordList.add(eRecord1);
				}
				
				if(eRecord1!=null)
					eRecord1.setNxtValue(updateTime);
			}
			else if(pvVarName.startsWith(Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU)) {//降温完成上升沿反应釜温度
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
				eRecord.setPhaseName(Constant.LENG_QUE);
				
				eRecordList.add(eRecord);
			}
			else if(pvVarName.startsWith(Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN+Constant.CHA)) {//从开始降温到停止降温时间差
				Integer pvFId = processVar.getFId();
				String batchID = batchIDMap.get(pvFId).toString();
				Float varValue = processVar.getVarValue();
				
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
				eRecord.setPtnValue(varValue+"");
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

	public List<ERecord> getListByBatchID(String batchID) {
		// TODO Auto-generated method stub
		return eRecordMapper.getListByBatchID(batchID);
	}

	public int updatePCJLReportedByBatchID(String batchID) {
		// TODO Auto-generated method stub
		return eRecordMapper.updatePCJLReportedByBatchID(batchID);
	}

	public Map<String,Object> getListByPcjl() {
		Map<String,Object> map = new HashMap<String, Object>();
		List<ERecord> mYscPcjlList = new ArrayList<ERecord>();//M类已生成的批次记录集合
		List<ERecord> uYscPcjlList = new ArrayList<ERecord>();//U类已生成的批次记录集合
		List<ERecord> mWscPcjlList = new ArrayList<ERecord>();//M类未生成的批次记录集合
		List<ERecord> uWscPcjlList = new ArrayList<ERecord>();//U类未生成的批次记录集合
		List<ERecord> pcjlList = eRecordMapper.getListByPcjl();//查询全部批次记录
		for (ERecord pcjl : pcjlList) {
			if (pcjl.getRemark().equals("0")){
				if (pcjl.getRecType().equals("M")){
					mYscPcjlList.add(pcjl);
				}else if(pcjl.getRecType().equals("U")){
					uYscPcjlList.add(pcjl);
				}
			}else if (pcjl.getRemark().equals("1")){
				if (pcjl.getRecType().equals("M")){
					mWscPcjlList.add(pcjl);
				}else if(pcjl.getRecType().equals("U")){
					uWscPcjlList.add(pcjl);
				}
			}
		}
		map.put("mYscPcjlList",mYscPcjlList);
		map.put("uYscPcjlList",uYscPcjlList);
		map.put("mWscPcjlList",mWscPcjlList);
		map.put("uWscPcjlList",uWscPcjlList);
		return map;
	}

	public List<ERecord> getListByType(String type) {
		List<ERecord> pcjls = new ArrayList<ERecord>();
		List<ERecord> pcjlList = eRecordMapper.getListByPcjl();
		for (ERecord pcjl : pcjlList) {
			String pcjlType = pcjl.getBatchID().substring(0, 2);
			if (pcjlType.equals(type)){
				pcjls.add(pcjl);
			}
		}
		return pcjls;
	}

	public List<Map<String, Object>> getUnCreRepVarList(String batchID) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> varMapList=new ArrayList<Map<String,Object>>();
		Map<String, Object> varMap=null;
		List<ERecord> eRecordList = eRecordMapper.getListByBatchID(batchID);
		for (ERecord eRecord : eRecordList) {
			String varName = eRecord.getVarName();
			if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.SCBH_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.SCBH_CN);
				varMapList.add(varMap);
			}
			else if(Constant.FAN_YING_FU.equals(varName)) {
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.FYF_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.FYF_CN);
				varMapList.add(varMap);
			}
			else if(Constant.PI_CI_JI_LU.equals(varName)) {
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.KSSJ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.KSSJ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.JSSJ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.JSSJ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.SCGS_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.SCGS_CN);
				varMapList.add(ptnVarMap);
			}
			else if(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG.equals(varName)) {
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JQSJJLZL_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JQSJJLZL_CN);
				varMapList.add(varMap);
			}
			else if(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG.equals(varName)) {
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JSSJZL_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JSSJZL_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.JQBLKSDJQFLWCSJ.equals(varName)) {
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQBLKSSJ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.JQBLKSSJ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQFLWCSJ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.JQFLWCSJ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQBLKSDFLWCSJC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.JQBLKSDFLWCSJC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(ERecord.JQFLWCSSYFYFWD.equals(varName)) {//甲醛放料完成上升沿反应釜温度
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JQFLWCFYFWD_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JQFLWCFYFWD_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.JQBLKSDJQFLWCFZL.equals(varName)) {//甲醛备料开始到甲醛放料完成釜重量
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue+unit);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQBLKSFCZ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.JQBLKSFCZ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue+unit);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQFLWCFCZ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.JQFLWCFCZ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.JQBLKSDFLWCZLC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.JQBLKSDFLWCZLC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI.equals(varName)) {//加碱前PH输入值
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JJQPHSRZ_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JJQPHSRZ_CN);
				varMapList.add(varMap);
			}
			else if(Constant.JIA_JIAN_LIANG_TI_SHI.equals(varName)) {//加碱量提示
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JJLTS_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JJLTS_CN);
				varMapList.add(varMap);
			}
			else if(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI.equals(varName)) {//加碱后PH输入值
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JJHPHSRZ_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JJHPHSRZ_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.ZJJLG12CZ.equals(varName)) {//助剂计量罐1-2称重
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.ZJJLG12CZ_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.ZJJLG12CZ_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.YXYCJZJDSYZJJLWC1SJ.equals(varName)) {//允许一次加助剂到所有助剂加料完成1时间
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXYCJZJSJ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXYCJZJSJ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC1SJ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC1SJ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXYCJZJDSYZJJLWC1SJC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.YXYCJZJDSYZJJLWC1SJC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(ERecord.SYZJJLWC1SSYFYFWD.equals(varName)) {//所有助剂加料完成1上升沿反应釜温度
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC1FYFWD_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC1FYFWD_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.YXYCJZJDSYZJJLWC1FZL.equals(varName)) {//允许一次加助剂到所有助剂加料完成1釜重量
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXYCJZJFCZ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXYCJZJFCZ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC1FCZ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC1FCZ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXYCJZJDSYZJJLWC1ZLC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.YXYCJZJDSYZJJLWC1ZLC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING.equals(varName)) {//粉料重量设定
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.FLZLSD_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.FLZLSD_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.FNSFLFSSYDXJYSJ.equals(varName)) {//釜尿素放料阀上升沿到下降沿时间
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFSSYSJ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFSSYSJ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFXJYSJ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFXJYSJ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFSSYDXJYSJC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFSSYDXJYSJC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(ERecord.FNSFLFXJYFYFWD.equals(varName)) {//釜尿素放料阀下降沿反应釜温度
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFXJYFYFWD_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFXJYFYFWD_CN);
				varMapList.add(varMap);
			}
			else if(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI.equals(varName)) {//加粉料PH输入值
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.JFLPHSRZ_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.JFLPHSRZ_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.FNSFLFSSYDXJYZL.equals(varName)) {//釜尿素放料阀上升沿到下降沿重量
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFSSYFCZ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFSSYFCZ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFXJYFCZ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFXJYFCZ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.FNSFLFSSYDXJYZLC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.FNSFLFSSYDXJYZLC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(ERecord.SWKSDWD85YECTLTXSJ.equals(varName)) {//升温开始到温度85与二次投料提醒时间
				String preValue = eRecord.getPreValue();
				String nxtValue = eRecord.getNxtValue();
				String ptnValue = eRecord.getPtnValue();
				String unit = eRecord.getUnit();
				
				HashMap<String, Object> preVarMap = new HashMap<String, Object>();
				preVarMap.put(Constant.VALUE, preValue);
				preVarMap.put(Constant.ROW_NUMBER, ReportF_M.SWKSSJ_RN);
				preVarMap.put(Constant.COL_NUMBER, ReportF_M.SWKSSJ_CN);
				varMapList.add(preVarMap);
				
				HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
				nxtVarMap.put(Constant.VALUE, nxtValue);
				nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.WD85YECTLTXSJ_RN);
				nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.WD85YECTLTXSJ_CN);
				varMapList.add(nxtVarMap);
				
				HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
				ptnVarMap.put(Constant.VALUE, ptnValue+unit);
				ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.SWKSDWD85YECTLTXSJC_RN);
				ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.SWKSDWD85YECTLTXSJC_CN);
				varMapList.add(ptnVarMap);
			}
			else if(Constant.ZHENG_QI_YA_LI.equals(varName)) {//蒸汽压力
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.ZQYL_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.ZQYL_CN);
				varMapList.add(varMap);
			}
			else if(ERecord.WD85YECTLTXSSYFYFWD.equals(varName)) {//温度85与二次投料提醒上升沿反应釜温度
				String varValue = eRecord.getVarValue();
				String unit = eRecord.getUnit();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue+unit);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.WD85YECTLTXFYFWD_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.WD85YECTLTXFYFWD_CN);
				varMapList.add(varMap);
			}
			else if(Constant.ER_CI_TOU_LIAO_PH_SHU_RU_ZHI.equals(varName)) {//二次投料PH输入值
				String varValue = eRecord.getVarValue();
				
				varMap = new HashMap<String, Object>();
				varMap.put(Constant.VALUE, varValue);
				varMap.put(Constant.ROW_NUMBER, ReportF_M.ECTLPHSRZ_RN);
				varMap.put(Constant.COL_NUMBER, ReportF_M.ECTLPHSRZ_CN);
				varMapList.add(varMap);
			}
		}
		return varMapList;
	}
}

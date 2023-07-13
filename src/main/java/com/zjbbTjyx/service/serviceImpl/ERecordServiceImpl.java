package com.zjbbTjyx.service.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjbbTjyx.dao.*;
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

@Service
public class ERecordServiceImpl implements ERecordService {

    @Autowired
    private ProcessVarMapper processVarMapper;
    @Autowired
    private ERecordMapper eRecordMapper;
    private HashMap<Integer,String> batchIDMap=new HashMap<Integer,String>();
    
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
			String pvRecType = processVar.getRecType();
			
			if(ERecord.M.equals(pvRecType)) {
				if(pvVarName.startsWith(ERecord.BLKSSSYSJ)) {//备料开始上升沿时间 //生产编号阶段开始
					Integer pvFId = processVar.getFId();
					String pvFName = OpcUtil.getFNameByFIdRecType(pvFId, pvRecType);
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();

					//获取反应结束时间变量名
					//String fyjsSjVarName = ERecord.FYJSSSYSJ;
					//ProcessVar fyjsSjPV = OpcUtil.getProVarInListByVarName(fyjsSjVarName, processVarList);
					//if(fyjsSjPV!=null) {
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
						
						pvIdList.add(pvId);
					//}
					
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
					
					/*
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
					*/
					
					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.BLKSDFYJSSJC)) {//备料开始到反应结束时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
					
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
					eRecord1.setUnit(unit);
					
					/*
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
					eRecord2.setUnit(unit);
					*/

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.SHENG_CHAN_GONG_SHI)) {//生产工时
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.SHENG_CHAN_RI_QI)) {//生产日期
					Integer pvFId = processVar.getFId();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(recordTime);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}//生产编号阶段结束
				else if(pvVarName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||//甲醛实际进料重量    //YSD101阶段开始
						pvVarName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)) {//加水实际重量
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSSSYSJ)) {//甲醛备料开始上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYSJ)) {//甲醛放料完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYFYFWD)) {//甲醛放料完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)) {//加碱前PH输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSSSYFCZ)) {//甲醛备料开始上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYFCZ)) {//甲醛放料完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCFZLC)) {//从甲醛备料开始到甲醛放料完成釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JQBLKSDJQFLWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD101);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCSJC)) {//甲醛备料开始到甲醛放料完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(pvVarName);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD101);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD101阶段结束
				else if(pvVarName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)) {//加碱量提示   //YSD109阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)) {//加碱后PH输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}//YSD109阶段结束
				else if(pvVarName.startsWith(Constant.ZHU_JI_JI_LIANG_GUAN)&&pvVarName.endsWith(Constant.CHENG_ZHONG)) {//助剂计量罐1-2称重    //YSD106阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXYCJZJSSYSJ)) {//允许一次加助剂上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYSJ)) {//所有助剂加料完成1上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYFYFWD)) {//所有助剂加料完成1上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXYCJZJSSYFCZ)) {//允许一次加助剂上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.YXYCJZJDSYZJJLWC1FZL);
						eRecord.setUnit(unit);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD106);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC1SSYFCZ)) {//所有助剂加料完成1上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXYCJZJDSYZJJLWC1FZLC)) {//允许一次加助剂到所有助剂加料完成1重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1FZL, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXYCJZJDSYZJJLWC1SJC)) {//允许一次加助剂到所有助剂加料完成1时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXYCJZJDSYZJJLWC1SJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD106阶段结束
				else if(pvVarName.startsWith(Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING)) {//粉料重量设定    //YSD102阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYSJ)) {//釜尿素放料阀上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYSJ)) {//釜尿素放料阀下降沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYFYFWD)) {//釜尿素放料阀下降沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)) {//加粉料PH输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYFCZ)) {//釜尿素放料阀上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYFCZ)) {//釜尿素放料阀下降沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYZLC)) {//釜尿素放料阀重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYSJC)) {//釜尿素放料阀时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD102阶段结束
				else if(pvVarName.startsWith(ERecord.SWKSSSYSJ)) {//升温开始上升沿时间    //开始升温阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					ERecord eRecord1 = getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
					if(eRecord1==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.ZHENG_QI_YA_LI)) {//蒸汽压力
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}//开始升温阶段结束
				else if(pvVarName.startsWith(ERecord.WD85YECTLTXSSYSJ)) {//温度85与二次投料提醒上升沿时间    //PH检测阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.WD85YECTLTXSSYFYFWD)) {//温度85与二次投料提醒上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.ER_CI_TOU_LIAO_PH_SHU_RU)) {//二次投料PH输入
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SWKSDWD85YECTLTXSJC)){//升温开始到温度85与二次投料提醒时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.SWKSDWD85YECTLTXSJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//PH检测阶段结束
				else if(pvVarName.startsWith(ERecord.YXECJZJSSYSJ)) {//允许二次加助剂上升沿时间    //YSD104阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);	
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYSJ)) {//所有助剂加料完成2上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYFYFWD)) {//所有助剂加料完成2上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXECJZJSSYFCZ)) {//允许二次加助剂上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SYZJJLWC2SSYFCZ)) {//所有助剂加料完成2上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXECJZJDSYZJJLWC2FZLC)) {//允许二次加助剂到所有助剂加料完成2釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2FCZ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXECJZJDSYZJJLWC2SJC)) {//允许二次加助剂到所有助剂加料完成2时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXECJZJDSYZJJLWC2SJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD104阶段结束
				else if(pvVarName.startsWith(ERecord.SWWCSSYSJ)) {//升温完成上升沿时间    //停汽阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SWWCSSYFYFWD)) {//升温完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.WEN_DU_98_PH)) {//温度98PH
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SWKSDSWWCSJC)) {//升温开始到升温完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.SWKSDSWWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.TING_QI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.CE_LIANG_BSWD_SRZ)||//测量冰水雾点输入值
						pvVarName.startsWith(Constant.CE_20_WU_DIAN_SRZ)) {//测20雾点输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JHZDSSYFYFWD)) {//聚合终点上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ)) {//停热降温水数输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JHZDSSYSJ)) {//聚合终点上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.KSJWDTZJWSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.KSJWDTZJWSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.LENG_QUE);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JWWCSSYSJ)) {//降温完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					ERecord eRecord1=getFromList(ERecord.KSJWDTZJWSJ, batchID, eRecordList);
					if(eRecord1==null) {
						eRecord1=new ERecord();
						eRecord1.setVarName(ERecord.KSJWDTZJWSJ);
						eRecord1.setRecType(pvRecType);
						eRecord1.setFId(pvFId);
						eRecord1.setRecordTime(recordTime);
						eRecord1.setBatchID(batchID);
						eRecord1.setPhaseName(Constant.LENG_QUE);
						
						eRecordList.add(eRecord1);
					}
					eRecord1.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JWWCSSYFYFWD)) {//降温完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.KSJWDTZJWSJC)) {//从开始降温到停止降温时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
					
					eRecord=getFromList(ERecord.KSJWDTZJWSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.KSJWDTZJWSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.LENG_QUE);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//冷却阶段结束
				else if(pvVarName.startsWith(Constant.ZHONG_JIAN_SHUI_SHU)||//终检水数
						pvVarName.startsWith(Constant.ZHONG_JIAN_PH)) {//终检PH
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.ZHI_LIANG_ZHONG_JIAN);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXKSPJSSYSJ)) {//允许开始排胶上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.YXKSPJDPJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.YXKSPJDPJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.PAI_JIAO);
					}
					eRecord.setPreValue(updateTime);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.PJWCSSYSJ)) {//排胶完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					ERecord eRecord1=getFromList(ERecord.YXKSPJDPJWCSJ, batchID, eRecordList);
					if(eRecord1==null) {
						eRecord1=new ERecord();
						eRecord1.setVarName(ERecord.YXKSPJDPJWCSJ);
						eRecord1.setRecType(pvRecType);
						eRecord1.setFId(pvFId);
						eRecord1.setRecordTime(recordTime);
						eRecord1.setBatchID(batchID);
						eRecord1.setPhaseName(Constant.PAI_JIAO);
						
						eRecordList.add(eRecord1);
					}
					eRecord1.setNxtValue(updateTime);
					
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXKSPJDPJWCSJC)) {//允许开始排胶到排胶完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					ERecord eRecord1=getFromList(ERecord.YXKSPJDPJWCSJC, batchID, eRecordList);
					if(eRecord1==null) {
						eRecord1=new ERecord();
						eRecord1.setVarName(pvVarName);
						eRecord1.setRecType(pvRecType);
						eRecord1.setFId(pvFId);
						eRecord1.setRecordTime(recordTime);
						eRecord1.setBatchID(batchID);
						eRecord1.setPhaseName(Constant.PAI_JIAO);
						
						eRecordList.add(eRecord1);
					}
					eRecord1.setPtnValue(varValue+"");
					eRecord1.setUnit(unit);
					
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
					eRecord2.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXKSPJSSYFCZ)) {//允许开始排胶上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.YXKSPJDPJWCFCZ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.YXKSPJDPJWCFCZ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.PAI_JIAO);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.PJWCSSYFCZ)) {//排胶完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.YXKSPJDPJWCFCZ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.YXKSPJDPJWCFCZ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.PAI_JIAO);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YXKSPJDPJWCFZLC)) {//允许开始排胶到排胶完成釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.YXKSPJDPJWCFCZ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.YXKSPJDPJWCFCZ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.PAI_JIAO);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//排胶阶段结束
			}
			else if(ERecord.U.equals(pvRecType)) {
				if(pvVarName.startsWith(ERecord.BLKSSSYSJ)) {//备料开始上升沿时间 //生产编号阶段开始
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FYJSSSYSJ)) {//反应结束上升沿时间
					String updateTime = processVar.getUpdateTime();
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
					
					/*
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
					*/

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.BLKSDFYJSSJC)) {//备料开始到反应结束时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
					
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
					eRecord1.setUnit(unit);
					
					/*
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
					eRecord2.setUnit(unit);
					*/

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.SHENG_CHAN_GONG_SHI)) {//生产工时
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.KSDJSSJ, batchID, eRecordList);
					if(eRecord==null) {
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
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.SHENG_CHAN_RI_QI)) {//生产日期
					Integer pvFId = processVar.getFId();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(recordTime);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}//生产编号阶段结束
				else if(pvVarName.startsWith(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG)||//甲醛实际进料重量    //YSD101阶段开始
						pvVarName.startsWith(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG)) {//加水实际重量
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSSSYSJ)) {//甲醛备料开始上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYSJ)) {//甲醛放料完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYFYFWD)) {//甲醛放料完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI)) {//加碱前PH输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSSSYFCZ)) {//甲醛备料开始上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQFLWCSSYFCZ)) {//甲醛放料完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCFZLC)) {//从甲醛备料开始到甲醛放料完成釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JQBLKSDJQFLWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD101);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JQBLKSDJQFLWCSJC)) {//甲醛备料开始到甲醛放料完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JQBLKSDJQFLWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(pvVarName);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD101);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD101阶段结束
				else if(pvVarName.startsWith(Constant.JIA_JIAN_LIANG_TI_SHI)) {//加碱量提示   //YSD109阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI)) {//加碱后PH输入值
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}//YSD109阶段结束
				else if(pvVarName.startsWith(ERecord.KSJLSSYSJLTCZ)) {//开始加料上升沿酸计量筒称重   //YSD215一次阶段开始
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD215_YI_CI);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JJPHZZCSSYSJ)) {//加碱PH值正常上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLYCTJWCSSYSJ)) {//助剂6一次添加完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLYCTJWCSSYFYFWD)) {//助剂6一次添加完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.YSD215_YI_CI);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JJPHZZCSSYFCZ)) {//加碱PH值正常上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCFZL);
						eRecord.setUnit(unit);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLYCTJWCSSYFCZ)) {//助剂6一次添加完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JJPHZZCDZJLYCTJWCFZLC)) {//加碱PH值正常到助剂6一次添加完成釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.JJPHZZCDZJLYCTJWCSJC)) {//加碱PH值正常到助剂6一次添加完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.JJPHZZCDZJLYCTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.JJPHZZCDZJLYCTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_YI_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD215一次阶段结束
				else if(pvVarName.startsWith(ERecord.ZJLECBLWCSSYSJLTCZ)) {//助剂6二次备料完成上升沿酸计量筒称重   //YSD215二次阶段开始
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD215_ER_CI);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECBLWCSSYSJ)) {//助剂6二次备料完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECTJWCSSYSJ)) {//助剂6二次添加完成上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECTJWCSSYFYFWD)) {//助剂6二次添加完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.YSD215_ER_CI);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECBLWCSSYFCZ)) {//助剂6二次备料完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCFZL);
						eRecord.setUnit(unit);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECTJWCSSYFCZ)) {//助剂6二次添加完成上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECBLWCDZJLECTJWCFZLC)) {//助剂6二次备料完成到助剂6二次添加完成釜重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCFZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCFZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.ZJLECBLWCDZJLECTJWCSJC)) {//助剂6二次备料完成到助剂6二次添加完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.ZJLECBLWCDZJLECTJWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.ZJLECBLWCDZJLECTJWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD215_ER_CI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD215二次阶段结束
				else if(pvVarName.startsWith(ERecord.JFLTXSSYFL1ZLSD)) {//加粉料提醒上升沿粉料1重量设定   //YSD103阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.YSD103);
					
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYSJ)) {//釜尿素放料阀上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYSJ)) {//釜尿素放料阀下降沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYFYFWD)) {//釜尿素放料阀下降沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.YSD103);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI)) {//加粉料PH输入值
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YSD103);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYFCZ)) {//釜尿素放料阀上升沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFXJYFCZ)) {//釜尿素放料阀下降沿釜称重
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(varValue+"");

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYZLC)) {//釜尿素放料阀重量差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYZL, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYZL);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.FNSFLFSSYDXJYSJC)) {//釜尿素放料阀时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.FNSFLFSSYDXJYSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.FNSFLFSSYDXJYSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.YSD103);
	
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//YSD103阶段结束
				else if(pvVarName.startsWith(ERecord.SWKSSSYSJ)) {//升温开始上升沿时间    //开始升温阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord = getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.SWKSDSWWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.KAI_SHI_SHENG_WEN);
	
						eRecordList.add(eRecord);
					}
					eRecord.setPreValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.ZHENG_QI_YA_LI)) {//蒸汽压力
					Float pvVarValue = processVar.getVarValue();
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

					pvIdList.add(pvId);
				}//开始升温阶段结束
				else if(pvVarName.startsWith(ERecord.SWWCSSYSJ)) {//升温完成上升沿时间    //升温至高温度阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.SWKSDSWWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.SHENG_WEN_ZHI_GAO_WEN_DU);
	
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SWWCSSYFYFWD)) {//升温完成上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
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
					eRecord.setPhaseName(Constant.SHENG_WEN_ZHI_GAO_WEN_DU);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.WEN_DU_98_PH)) {//温度98PH
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.SHENG_WEN_ZHI_GAO_WEN_DU);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.SWKSDSWWCSJC)) {//升温开始到升温完成时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.SWKSDSWWCSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.SWKSDSWWCSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.SHENG_WEN_ZHI_GAO_WEN_DU);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//升温至高温度阶段结束
				else if(pvVarName.startsWith(ERecord.DYCBWQDSSYSJ)) {//第一次保温启动上升沿时间    //一次保温10分钟测PH阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(updateTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.YCBWSFZCPH);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.DYCBWQDSSYFYFWD)) {//第一次保温启动上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.YCBWSFZCPH);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.DYCBWHGSSYSJ)) {//第一次保温合格上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
					
					eRecord=getFromList(ERecord.DYCBWHGDYCJWJSTXSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.DYCBWHGDYCJWJSTXSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.JIANG_WEN_KAI_SHI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.DYCBWHGSSYFYFWD)) {//第一次保温合格上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.JIANG_WEN_KAI_SHI);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YCJWJSTXSSYSJ)) {//一次降温加酸提醒上升沿时间
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.DYCBWHGDYCJWJSTXSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.DYCBWHGDYCJWJSTXSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.JIANG_WEN_KAI_SHI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YCJWJSTXSSYFYFWD)) {//一次降温加酸提醒上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.JIANG_WEN_TING_ZHI);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.DYCBWHGDYCJWJSTXSJC)) {//第一次保温合格到一次降温加酸提醒时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.DYCBWHGDYCJWJSTXSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.DYCBWHGDYCJWJSTXSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.JIANG_WEN_KAI_SHI);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YCJWJSL)) {//一次降温加酸量
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.JIA_SUAN_BING_JI_SHI);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YCJWJSHGSSYFYFWD)) {//一次降温加酸合格上升沿反应釜温度
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.JIA_SUAN_BING_JI_SHI);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.YCJWJSHGSSYYCJWJSPHSR)) {//一次降温加酸合格上升沿一次降温加酸PH输入
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.JIA_SUAN_BING_JI_SHI);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.CE_LIANG_BSWD_SRZ)) {//测量冰水雾点输入值
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.BING_SHUI_WU_DIAN);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.CE_20_WU_DIAN_SRZ)) { //测20雾点输入值
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.ER_SHI_DU_WU_DIAN);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU)) {//一次降温加酸PH输入    //二次降温阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.ER_CI_JIANG_WEN);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}//二次降温阶段结束
				else if(pvVarName.startsWith(Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN)) {//加碱量范围下限   //加碱阶段开始
					Float pvVarValue = processVar.getVarValue();
					String pvUnit = processVar.getUnit();
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
					eRecord.setPhaseName(Constant.JIA_JIAN);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(Constant.JIA_JIAN_PH_SHU_RU)) {//加碱PH输入
					Float pvVarValue = processVar.getVarValue();
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
	
					eRecord=new ERecord();
					eRecord.setVarName(pvVarName);
					eRecord.setVarValue(pvVarValue+"");
					eRecord.setRecType(pvRecType);
					eRecord.setFId(pvFId);
					eRecord.setRecordTime(recordTime);
					eRecord.setBatchID(batchID);
					eRecord.setPhaseName(Constant.JIA_JIAN);
	
					eRecordList.add(eRecord);

					pvIdList.add(pvId);
				}//加碱阶段结束
				else if(pvVarName.startsWith(ERecord.ECTFSSYSJ)) {//二次投粉上升沿时间    //70度终止降温阶段开始
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					String updateTime = processVar.getUpdateTime();
	
					eRecord=getFromList(ERecord.DYCBWHGDECTFSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.DYCBWHGDECTFSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.QI_SHI_DU_ZHONG_ZHI_JIANG_WEN);
	
						eRecordList.add(eRecord);
					}
					eRecord.setNxtValue(updateTime);

					pvIdList.add(pvId);
				}
				else if(pvVarName.startsWith(ERecord.DYCBWHGDECTFSJC)) {//第一次保温合格到二次投粉时间差
					Integer pvFId = processVar.getFId();
					String batchID = batchIDMap.get(pvFId).toString();
					Float varValue = processVar.getVarValue();
					String unit = processVar.getUnit();
	
					eRecord=getFromList(ERecord.DYCBWHGDECTFSJ, batchID, eRecordList);
					if(eRecord==null) {
						eRecord=new ERecord();
						eRecord.setVarName(ERecord.DYCBWHGDECTFSJ);
						eRecord.setRecType(pvRecType);
						eRecord.setFId(pvFId);
						eRecord.setRecordTime(recordTime);
						eRecord.setBatchID(batchID);
						eRecord.setPhaseName(Constant.QI_SHI_DU_ZHONG_ZHI_JIANG_WEN);
						
						eRecordList.add(eRecord);
					}
					eRecord.setPtnValue(varValue+"");
					eRecord.setUnit(unit);

					pvIdList.add(pvId);
				}//70度终止降温阶段结束
			}
		}
		
		System.out.println("eRecordListSize==="+eRecordList.size());
		
		//处理完批记录集合的信息后，通过循环一起插入数据库表
		for (ERecord eRecordItem : eRecordList) {
			
			//在这里写逻辑，凡是batchid第二位是C的说明是U类胶。徐龙那边写的有点问题，咱这边暂时处理下，验证下第一位是不是U，不是的话就把M替换为U,替换之后再验证下RecType是不是U，不是的话也把M替换为U
			String batchID = eRecordItem.getBatchID();
			String index2 = batchID.substring(1,2);
			String index1 = batchID.substring(0,1);
			if ("C".equals(index2)){
				if ("M".equals(index1)){
					String substring = batchID.substring(1);
					String newBatchID = "U" + substring;
					eRecordItem.setBatchID(newBatchID);
					
				}
				String recType = eRecordItem.getRecType();
				if("M".equals(recType)) {
					eRecordItem.setRecType("U");
				}
			}
			
			count+=eRecordMapper.add(eRecordItem);
		}
		
		int pvIdListSize = pvIdList.size();
		System.out.println("pvIdListSize"+pvIdListSize);
		if(pvIdListSize>0) {
			List<Integer> pvIdTmpList=new ArrayList<Integer>();
			for (int i = 0; i < pvIdListSize; i++) {
				Integer pvId = pvIdList.get(i);
				pvIdTmpList.add(pvId);
				if(i%2000==0) {
					processVarMapper.updateDealBzByIdList(ProcessVar.YCL,pvIdTmpList);
					pvIdTmpList.clear();
				}
			}
			if(pvIdTmpList.size()>0) {
				processVarMapper.updateDealBzByIdList(ProcessVar.YCL,pvIdTmpList);
				pvIdTmpList.clear();
			}
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
		List<Map<String,Object>> unAddFMapList=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> fMap : fMapList) {
			Integer fId = Integer.valueOf(fMap.get("fId").toString());
			Set<Integer> fIdSet = batchIDMap.keySet();
			boolean exist=false;
			for (Integer fMapKey : fIdSet) {
				if(fId==fMapKey) {
					exist=true;
					break;
				}
			}
			if(!exist) {
				unAddFMapList.add(fMap);
			}
		}
		
		if(unAddFMapList.size()>0) {
			String year=DateUtil.getTimeStrByFormatStr(date, DateUtil.YEAR);
			Integer maxBatchNum = eRecordMapper.getMaxBatchNumByYear(year);
			maxBatchNum=maxBatchNum==null?0:maxBatchNum;
			for (int i = 0; i < unAddFMapList.size(); i++) {
				Map<String, Object> fMap = unAddFMapList.get(i);
				Integer fId = Integer.valueOf(fMap.get("fId").toString());
				String jzlx=OpcUtil.readJZLXByFId(fId);
				String recType = null;
				//recType = fMap.get("recType").toString();
				if("C".equals(jzlx))
					recType = "U";
				else
					recType = "M";
				String batchNumStr=null;
				int batchNum=maxBatchNum+i+1;
				if(batchNum<10)
					batchNumStr="0000000"+batchNum;
				else if(batchNum<100)
					batchNumStr="000000"+batchNum;
				else if(batchNum<1000)
					batchNumStr="00000"+batchNum;
				else if(batchNum<10000)
					batchNumStr="0000"+batchNum;
				else if(batchNum<100000)
					batchNumStr="000"+batchNum;
				else if(batchNum<1000000)
					batchNumStr="00"+batchNum;
				else if(batchNum<10000000)
					batchNumStr="0"+batchNum;
				else
					batchNumStr=""+batchNum;
				String batchID=recType+jzlx+year+batchNumStr;
				
				batchIDMap.put(fId, batchID);
			}
		}
		return batchIDMap;
	}

	@Override
	public void clearBatchIDMap(List<Integer> fIdList) {
		// TODO Auto-generated method stub
		for (Integer fId : fIdList) {
			batchIDMap.remove(fId);
		}
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

	public Map<String,Object> getListByPcjl(String type) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<ERecord> mWscBatchList = new ArrayList<ERecord>();//M类未生成的批次记录集合
		List<ERecord> uWscBatchList = new ArrayList<ERecord>();//U类未生成的批次记录集合
		List<String> mYscGlueTypeList = new ArrayList<String>();//M类已生成的胶种集合
		List<String> uYscGlueTypeList = new ArrayList<String>();//U类已生成的胶种集合
		List<ERecord> pcjlList = eRecordMapper.getListByPcjl();//查询全部批次记录
		
		String nowDateStr = DateUtil.getTimeStrByFormatStr(new Date(),DateUtil.YEAR_TO_SECOND);
		
		if(Constant.M_WSC.equals(type)) {
			for (ERecord pcjl : pcjlList) {
				if (pcjl.getRemark().equals(ERecord.WSCBB+"")){
					if (pcjl.getRecType().equals(ERecord.M)){
						mWscBatchList.add(pcjl);
					}
				}
				else {
					if (pcjl.getRecType().equals(ERecord.M)){
						mWscBatchList.add(pcjl);
					}
				}
			}
			//System.out.println("mWscBatchListSize===="+mWscBatchList.size());
			
			map.put("mWscBatchList",mWscBatchList);
		}
		else if(Constant.U_WSC.equals(type)) {
			for (ERecord pcjl : pcjlList) {
				if (pcjl.getRemark().equals(ERecord.WSCBB+"")){
					if (pcjl.getRecType().equals(ERecord.U)){
						uWscBatchList.add(pcjl);
					}
				}
			}
			
			map.put("uWscBatchList",uWscBatchList);
		}
		else if(StringUtils.isEmpty(type)) {
			for (ERecord pcjl : pcjlList) {
				if (pcjl.getRemark().equals(ERecord.WSCBB+"")){
					if (pcjl.getRecType().equals(ERecord.M)){
						mWscBatchList.add(pcjl);
					}
					else if(pcjl.getRecType().equals(ERecord.U)){
						uWscBatchList.add(pcjl);
					}
				}
				else if (pcjl.getRemark().equals(ERecord.YSCBB+"")){
					String batchID = pcjl.getBatchID();
					String glueType = batchID.substring(0, 2);
					if (pcjl.getRecType().equals(ERecord.M)){
						String recordTime = pcjl.getRecordTime();
						
						long daySpace = DateUtil.betweenTime(DateUtil.convertStrToLong(recordTime),DateUtil.convertStrToLong(nowDateStr),DateUtil.TIAN);
						//if(daySpace<7)
							mWscBatchList.add(pcjl);
						
						//查找m类胶种
						boolean exist = checkGlueTypeIfExistInList(glueType, mYscGlueTypeList);
						if(!exist)
							mYscGlueTypeList.add(glueType);
					}
					else if(pcjl.getRecType().equals(ERecord.U)){
						uWscBatchList.add(pcjl);
						
						//查找u类胶种
						boolean exist = checkGlueTypeIfExistInList(glueType, uYscGlueTypeList);
						if(!exist)
							uYscGlueTypeList.add(glueType);
					}
				}
			}
			//System.out.println("mWscBatchListSize===="+mWscBatchList.size());
			map.put("mWscBatchList",mWscBatchList);
			map.put("uWscBatchList",uWscBatchList);
			map.put("mYscGlueTypeList",mYscGlueTypeList);
			map.put("uYscGlueTypeList",uYscGlueTypeList);
		}
		
		return map;
	}

	/**
	 * 验证胶种是否存在于胶种集合里
	 * @param glueType
	 * @param glueTypeList
	 * @return
	 */
	private boolean checkGlueTypeIfExistInList(String glueType, List<String> glueTypeList) {
		boolean exist=false;
		for (String glueTypeItem : glueTypeList) {
			if (glueTypeItem.equals(glueType)){
				exist=true;
				break;
			}
		}
		return exist;
	}

	public List<ERecord> getYscPcjlListByType(String type) {
		ERecord eRecord = new ERecord();
		eRecord.setRecType(type);
		List<ERecord> pcjlList = eRecordMapper.getYscPcjlListByType(eRecord);
		return pcjlList;
	}

	public List<Map<String, Object>> getUnCreRepVarList(String batchID) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> varMapList=new ArrayList<Map<String,Object>>();
		Map<String, Object> varMap=null;
		List<ERecord> eRecordList = eRecordMapper.getListByBatchID(batchID);
		String recType=batchID.substring(0, 1);
		if(ERecord.M.equals(recType)) {
			for (ERecord eRecord : eRecordList) {
				String varName = eRecord.getVarName();
				if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.SCBH_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.SCBH_CN);
					varMapList.add(varMap);
				}
				else if(Constant.FAN_YING_FU.equals(varName)) {//反应釜
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.FYF_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.FYF_CN);
					varMapList.add(varMap);
				}
				else if(Constant.PI_CI_JI_LU.equals(varName)) {//批次记录
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
				else if(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG.equals(varName)) {//甲醛实际进料重量
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.JQSJJLZL_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.JQSJJLZL_CN);
					varMapList.add(varMap);
				}
				else if(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG.equals(varName)) {//加水实际重量
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.JSSJZL_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.JSSJZL_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.JQBLKSDJQFLWCSJ.equals(varName)) {//甲醛备料开始到甲醛放料完成时间
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
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXYCJZJFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXYCJZJFCZ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
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
				else if(varName.startsWith(Constant.ZHENG_QI_YA_LI)) {//蒸汽压力
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
				else if(Constant.ER_CI_TOU_LIAO_PH_SHU_RU.equals(varName)) {//二次投料PH输入
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.ECTLPHSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.ECTLPHSRZ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.YXECJZJDSYZJJLWC2SJ.equals(varName)) {//允许二次加助剂到所有助剂加料完成2时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXECJZJSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXECJZJSJ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC2SJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC2SJ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXECJZJDSYZJJLWC2SJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.YXECJZJDSYZJJLWC2SJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.SYZJJLWC2SSYFYFWD.equals(varName)) {//所有助剂加料完成2上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC2FYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC2FYFWD_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.YXECJZJDSYZJJLWC2FCZ.equals(varName)) {//允许二次加助剂到所有助剂加料完成2釜称重
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXECJZJFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXECJZJFCZ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.SYZJJLWC2FCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.SYZJJLWC2FCZ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXECJZJDSYZJJLWC2ZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.YXECJZJDSYZJJLWC2ZLC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.SWKSDSWWCSJ.equals(varName)) {//升温开始到升温完成时间
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.SWWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.SWWCSJ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.SWKSDSWWCSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.SWKSDSWWCSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.SWWCSSYFYFWD.equals(varName)) {//升温完成上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.SWWCFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.SWWCFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(Constant.WEN_DU_98_PH.equals(varName)) {//温度98PH
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.WD98PH_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.WD98PH_CN);
					varMapList.add(varMap);
				}
				else if(Constant.CE_LIANG_BSWD_SRZ.equals(varName)) {//测量冰水雾点输入值
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.CLBSWDSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.CLBSWDSRZ_CN);
					varMapList.add(varMap);
				}
				else if(Constant.CE_20_WU_DIAN_SRZ.equals(varName)) {//测20雾点输入值
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.C20WDSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.C20WDSRZ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.JHZDSSYFYFWD.equals(varName)) {//聚合终点上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.JHZDFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.JHZDFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ.equals(varName)) {//停热降温水数输入值
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.TRJWSSSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.TRJWSSSRZ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.KSJWDTZJWSJ.equals(varName)) {//开始降温到停止降温时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.JHZDSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.JHZDSJ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.JWWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.JWWCSJ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.KSJWDTZJWSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.KSJWDTZJWSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.JWWCSSYFYFWD.equals(varName)) {//降温完成上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.JWWCFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.JWWCFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(Constant.ZHONG_JIAN_SHUI_SHU.equals(varName)) {//终检水数
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.ZJSS_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.ZJSS_CN);
					varMapList.add(varMap);
				}
				else if(Constant.ZHONG_JIAN_PH.equals(varName)) {//终检PH
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.ZJPH_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.ZJPH_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.YXKSPJDPJWCSJ.equals(varName)) {//允许开始排胶到排胶完成时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXKSPJSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXKSPJSJ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.PJWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.PJWCSJ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.PJSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.PJSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.YXKSPJDPJWCFCZ.equals(varName)) {//允许开始排胶到排胶完成釜称重
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> sczzVarMap = new HashMap<String, Object>();
					sczzVarMap.put(Constant.VALUE, preValue);
					sczzVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXKSPJFCZ_RN);
					sczzVarMap.put(Constant.COL_NUMBER, ReportF_M.YXKSPJFCZ_CN);
					varMapList.add(sczzVarMap);
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_M.YXKSPJFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_M.YXKSPJFCZ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_M.PJWCFCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_M.PJWCFCZ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_M.PJZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_M.PJZLC_CN);
					varMapList.add(ptnVarMap);
				}
			}
		}
		else if(ERecord.U.equals(recType)) {
			for (ERecord eRecord : eRecordList) {
				String varName = eRecord.getVarName();
				if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
					String varValue = eRecord.getVarValue();
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.SCBH_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.SCBH_CN);
					varMapList.add(varMap);
				}
				else if(Constant.FAN_YING_FU.equals(varName)) {//反应釜
					String varValue = eRecord.getVarValue();
					
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.FYF_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.FYF_CN);
					varMapList.add(varMap);
				}
				else if(Constant.PI_CI_JI_LU.equals(varName)) {//批次记录
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();
					
					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.KSSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.KSSJ_CN);
					varMapList.add(preVarMap);
					
					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.JSSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.JSSJ_CN);
					varMapList.add(nxtVarMap);
					
					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.SCGS_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.SCGS_CN);
					varMapList.add(ptnVarMap);
				}
				else if(Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG.equals(varName)) {//甲醛实际进料重量
					String varValue = eRecord.getVarValue();
					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_M.JQSJJLZL_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_M.JQSJJLZL_CN);
					varMapList.add(varMap);
				}
				else if(Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG.equals(varName)) {//加水实际重量
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JSSJZL_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JSSJZL_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.JQBLKSDJQFLWCSJ.equals(varName)) {//甲醛备料开始到甲醛放料完成时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQBLKSSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JQBLKSSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQFLWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.JQFLWCSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQBLKSDFLWCSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JQBLKSDFLWCSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.JQFLWCSSYFYFWD.equals(varName)) {//甲醛放料完成上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JQFLWCFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JQFLWCFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.JQBLKSDJQFLWCFZL.equals(varName)) {//甲醛备料开始到甲醛放料完成釜重量
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQBLKSFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JQBLKSFCZ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQFLWCFCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.JQFLWCFCZ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JQBLKSDFLWCZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JQBLKSDFLWCZLC_CN);
					varMapList.add(ptnVarMap);
				}
				else if (Constant.JIA_JIAN_LIANG_TI_SHI.equals(varName)){//加碱量提示
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JJLTS_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JJLTS_CN);
					varMapList.add(varMap);
				}
				else if (Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI.equals(varName)){//加碱后PH输入值
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JJHPHSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JJHPHSRZ_CN);
					varMapList.add(varMap);
				}
				else if (ERecord.KSJLSSYSJLTCZ.equals(varName)){//开始加料酸计量筒称重
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.KSJLSJLTCZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.KSJLSJLTCZ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.JQBLKSDJQFLWCFZL.equals(varName)) {//加碱PH值正常到助剂6一次添加完成釜重量
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JJPHZZCFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JJPHZZCFCZ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLYCTJWCFCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLYCTJWCFCZ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JJPHZZCDZJLYCTJWCZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JJPHZZCDZJLYCTJWCZLC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.JQBLKSDJQFLWCSJ.equals(varName)) {//加碱PH值正常到助剂6一次添加完成时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JJPHZCSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JJPHZCSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLYCTJWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLYCTJWCSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JJPHZZCDZJLYCTJWCSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JJPHZZCDZJLYCTJWCSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if (ERecord.ZJLECBLWCSSYSJLTCZ.equals(varName)){//助剂6二次备料完成酸计量筒称重
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECBLWCSJLTCZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECBLWCSJLTCZ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.ZJLECBLWCDZJLECTJWCSJ.equals(varName)) {//助剂6二次备料完成到助剂6二次添加完成时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECBLWCSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECBLWCSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECTJWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECTJWCSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECBLWCDTJWCSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECBLWCDTJWCSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.ZJLECBLWCDZJLECTJWCFZL.equals(varName)) {//助剂6二次备料完成到助剂6二次添加完成釜称重
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue+unit);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECBLWCFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECBLWCFCZ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue+unit);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECTJWCFCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECTJWCFCZ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.ZJLECBLWCDTJWCZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.ZJLECBLWCDTJWCZLC_CN);
					varMapList.add(ptnVarMap);
				}
				else if (Constant.FEN_LIAO_1_ZHONG_LIANG_SHE_DING.equals(varName)){//粉料1重量设定
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXSSYFL1ZLSD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXSSYFL1ZLSD_CN);
					varMapList.add(varMap);
				}
				else if (Constant.FEN_LIAO_2_ZHONG_LIANG_SHE_DING.equals(varName)){//粉料2重量设定
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXSSYFL2ZLSD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXSSYFL2ZLSD_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.FNSFLFSSYDXJYSJ.equals(varName)) {//釜尿素放料阀上升沿到下降沿时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFSSYSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFSSYSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFXJYSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFXJYSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFSSYDXJYSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFSSYDXJYSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.FNSFLFXJYFYFWD.equals(varName)) {//釜尿素放料阀下降沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFXJYFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFXJYFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI.equals(varName)) {//加粉料PH输入值
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JFLPHSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JFLPHSRZ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.JIA_FEN_LIAO_TI_XING+ERecord.FNSFLFSSYDXJYZL).equals(varName)) {//加粉料提醒釜尿素放料阀上升沿到下降沿重量
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFSSYFCZ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFSSYFCZ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFXJYFCZ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFXJYFCZ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.JFLTXFNSFLFSSYDXJYZLC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.JFLTXFNSFLFSSYDXJYZLC_CN);
					varMapList.add(ptnVarMap);
				}
				else if(ERecord.SWKSDSWWCSJ.equals(varName)) {//升温开始到升温完成时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.SWKSSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.SWKSSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.SWWCSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.SWWCSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.SWKSDSWWCSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.SWKSDSWWCSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if((Constant.SHENG_WEN_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//升温完成反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.SWWCFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.SWWCFYFWD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//第一次保温启动上升沿时间
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWQDSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWQDSJ_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.SWKSDSWWCSJ.equals(varName)) {//第一次保温合格到一次降温加酸提醒时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSTXSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSTXSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGDYCJWJSTXSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGDYCJWJSTXSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if((Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//第一次保温合格上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWQDSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//一次降温加酸提醒上升沿反应釜温度
					String varValue = eRecord.getVarValue();
					String unit = eRecord.getUnit();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue+unit);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSTXFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSTXFYFWD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_LIANG).equals(varName)) {//一次降温加酸量
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSL_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSL_CN);
					varMapList.add(varMap);
				}
				else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//一次降温加酸合格上升沿反应釜温度
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSHGFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSHGFYFWD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.SHANG_SHENG_YAN+Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU).equals(varName)) {//一次降温加酸合格上升沿一次降温加酸PH输入
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSHGSSYYCJWJSPHSZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSHGSSYYCJWJSPHSZ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI).equals(varName)) {//测量冰水雾点输入值
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.CLBSWDSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.CLBSWDSRZ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.BAO_WEN_FEN_ZHONG_JI_SHI).equals(varName)) {//保温分钟计时1-------------？？？？
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.BWFZJS1_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.BWFZJS1_CN);
					varMapList.add(varMap);
				}
				else if((Constant.CE_20_WU_DIAN_SRZ).equals(varName)) {//测20雾点输入值
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.C20WDSRZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.C20WDSRZ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XIA_JIANG_YAN+Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU).equals(varName)) {//一次降温加酸提醒下降沿一次降温加酸PH输入
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YCJWJSTXXJYYCJWJSPHSR_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YCJWJSTXXJYYCJWJSPHSR_CN);
					varMapList.add(varMap);
				}
				else if((Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN).equals(varName)) {//加碱量范围下限
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JJLFWXX_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JJLFWXX_CN);
					varMapList.add(varMap);
				}
				else if((Constant.JIA_JIAN_PH_SHU_RU).equals(varName)) {//加碱PH输入
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.JJPHSR_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.JJPHSR_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.DYCBWHGDECTFSJ.equals(varName)) {//第一次保温合格到二次投粉时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ECTFSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ECTFSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGDECTFSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGDECTFSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if((Constant.ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING).equals(varName)) {//二次加粉料1重量设定
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJFL1ZLSD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJFL1ZLSD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_TOU_FEN+ERecord.FNSFLFSSYDXJYZL).equals(varName)) {//二次投粉釜尿素放料阀上升沿到下降沿时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.ECTFFNSFLFSSYSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.ECTFFNSFLFSSYSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.ECTFFNSFLFXJYSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.ECTFFNSFLFXJYSJ_CN);
					varMapList.add(nxtVarMap);

//					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
//					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
//					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.ECTFFNSFLFSSYDXJYSJC_RN);
//					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.ECTFFNSFLFSSYDXJYZLC_CN);
//					varMapList.add(ptnVarMap);
				}
				else if((Constant.BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING).equals(varName)) {//保温后加助剂6量设定
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.BWHJZJ6LSD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.BWHJZJ6LSD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_JIA_215_QI_DONG).equals(varName)) {//二次加215启动上升沿记录系统时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJ215QDSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJ215QDSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_JIA_215_WAN_CHENG).equals(varName)) {//二次加215完成上升沿记录系统时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJ215WCSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJ215WCSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING).equals(varName)) {//二次加水和小料提醒
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJSHXLTXXJYFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJSHXLTXXJYFYFWD_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_JIA_SHUI_QI_DONG).equals(varName)) {//二次加水启动上升沿记录系统时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJSQDSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJSQDSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.ER_CI_JIA_SHUI_WAN_CHENG).equals(varName)) {//二次加水完成上升沿记录系统时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ECJSWCSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ECJSWCSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.FAN_YING_JIE_SHU_SHI_JIAN).equals(varName)) {//反应结束上升沿记录系统时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.FYJSSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.FYJSSJ_CN);
					varMapList.add(varMap);
				}
				else if((Constant.FAN_YING_JIE_SHU_SHI_JIAN+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//反应结束上升沿记录反应釜温度
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.FYJSFYFWD_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.FYJSFYFWD_CN);
					varMapList.add(varMap);
				}
				else if(ERecord.DYCBWHGDFYJSSJC.equals(varName)) {//第一次保温合格到反应结束时间
					String preValue = eRecord.getPreValue();
					String nxtValue = eRecord.getNxtValue();
					String ptnValue = eRecord.getPtnValue();
					String unit = eRecord.getUnit();

					HashMap<String, Object> preVarMap = new HashMap<String, Object>();
					preVarMap.put(Constant.VALUE, preValue);
					preVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGSJ_RN);
					preVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGSJ_CN);
					varMapList.add(preVarMap);

					HashMap<String, Object> nxtVarMap = new HashMap<String, Object>();
					nxtVarMap.put(Constant.VALUE, nxtValue);
					nxtVarMap.put(Constant.ROW_NUMBER, ReportF_U.FYJSSJ_RN);
					nxtVarMap.put(Constant.COL_NUMBER, ReportF_U.FYJSSJ_CN);
					varMapList.add(nxtVarMap);

					HashMap<String, Object> ptnVarMap = new HashMap<String, Object>();
					ptnVarMap.put(Constant.VALUE, ptnValue+unit);
					ptnVarMap.put(Constant.ROW_NUMBER, ReportF_U.DYCBWHGDFYJSSJC_RN);
					ptnVarMap.put(Constant.COL_NUMBER, ReportF_U.DYCBWHGDFYJSSJC_CN);
					varMapList.add(ptnVarMap);
				}
				else if((ERecord.YXKSPJSSYFCZ).equals(varName)) {//允许开始排胶上升沿记录釜称重
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YXKSPJFCZ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YXKSPJFCZ_CN);
					varMapList.add(varMap);
				}
				else if((ERecord.ZJSPHTXXJYZJSS).equals(varName)) {//终检水PH提醒降沿记录终检水数
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ZJSS_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ZJSS_CN);
					varMapList.add(varMap);
				}
				else if((ERecord.ZJSPHTXXJYZJPH).equals(varName)) {//终检水PH提醒降沿记录终检PH
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.ZJPH_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.ZJPH_CN);
					varMapList.add(varMap);
				}
				else if((ERecord.YXKSPJSSYSJ).equals(varName)) {//允许开始排胶上升沿时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.YXKSPJSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.YXKSPJSJ_CN);
					varMapList.add(varMap);
				}
				else if((ERecord.PJWCSSYSJ).equals(varName)) {//排胶完成上升沿时间
					String varValue = eRecord.getVarValue();

					varMap = new HashMap<String, Object>();
					varMap.put(Constant.VALUE, varValue);
					varMap.put(Constant.ROW_NUMBER, ReportF_U.PJWCSJ_RN);
					varMap.put(Constant.COL_NUMBER, ReportF_U.PJWCSJ_CN);
					varMapList.add(varMap);
				}
			}
		}
		return varMapList;
	}

}

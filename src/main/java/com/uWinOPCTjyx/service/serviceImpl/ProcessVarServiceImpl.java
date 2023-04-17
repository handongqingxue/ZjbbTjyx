package com.uWinOPCTjyx.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.Constant;
import com.uWinOPCTjyx.util.DateUtil;

@Service
public class ProcessVarServiceImpl implements ProcessVarService {

    @Autowired
    private ProcessVarMapper processVarMapper;

	public List<ProcessVar> getUnDealListByFIdList(List<Integer> fIdList) {
		// TODO Auto-generated method stub
		return processVarMapper.getUnDealListByFIdList(fIdList);
	}

	public int add(ProcessVar proVar) {
		return processVarMapper.add(proVar);
	}

	public int addFromList(List<ProcessVar> proVarList) {
		int count = 0;
		for (ProcessVar proVar : proVarList) {
			count+=processVarMapper.add(proVar);
		}
		return count;
	}

	public ProcessVar getPtnValuePV(String nxtName, String nxtValue, ProcessVar nxtPV) {
		// TODO Auto-generated method stub
		String preName=null;
		String ptnName=null;
		Float ptnValue=null;
		if((Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(nxtName)) {
			preName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			ptnName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;//甲醛备料开始到甲醛放料完成的重量差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(nxtName)){
			preName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			ptnName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;//允许一次加助剂到所有助剂加料完成1的重量差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(nxtName)){
			preName=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			ptnName=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;//允许二次加助剂到所有助剂加料完成2的重量差
		}
		else if((Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA;//甲醛备料开始到甲醛放料完成时间差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA;//允许一次加助剂到所有助剂加料完成时间差
		}
		else if((Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.SHENG_WEN_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN+Constant.CHA;//升温开始到温度85与二次投料提醒时间差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN+Constant.CHA;//允许二次加助剂到所有助剂加料完成2时间差
		}
		else if((Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.SHENG_WEN_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA;//升温开始到升温完成时间差
		}
		
		String preValue=processVarMapper.getPreValueByPreName(preName);
		if(nxtName.contains(Constant.FU+Constant.CHENG_ZHONG)) {//计算重量差
			ptnValue=Float.valueOf(nxtValue)-Float.valueOf(preValue);
		}
		else if(nxtName.contains(Constant.SHI_JIAN)) {//计算时间差，需要调用日期工具类方法处理下
			Date date = new Date();
			long preValueLong = date.parse(preValue);
			long nxtValueLong = date.parse(nxtValue);
			ptnValue = (float)DateUtil.betweenTime(nxtValueLong, preValueLong, DateUtil.FEN);
		}
		
		String nxtUpdateTime = nxtPV.getUpdateTime();
		Integer nxtFId = nxtPV.getFId();
		String nxtRecType = nxtPV.getRecType();
		String nxtUnit = nxtPV.getUnit();
		Integer nxtParaType = nxtPV.getParaType();
		
    	ProcessVar ptnValuePV = new ProcessVar();
    	ptnValuePV.setVarName(ptnName);
    	ptnValuePV.setVarValue(ptnValue);
    	ptnValuePV.setDealBz(ProcessVar.WCL);
    	ptnValuePV.setUpdateTime(nxtUpdateTime);
    	ptnValuePV.setFId(nxtFId);
    	ptnValuePV.setRecType(nxtRecType);
    	ptnValuePV.setUnit(nxtUnit);
    	ptnValuePV.setParaType(nxtParaType);
    	
		return ptnValuePV;
	}

}

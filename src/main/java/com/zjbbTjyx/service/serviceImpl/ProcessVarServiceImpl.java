package com.zjbbTjyx.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjbbTjyx.dao.*;
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.Constant;
import com.zjbbTjyx.util.DateUtil;

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
		else if((Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN+Constant.CHA;//甲醛备料开始到甲醛放料完成时间差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(nxtName)){
			preName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG;
			ptnName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG+Constant.CHA;//允许一次加助剂到所有助剂加料完成1的重量差
		}
		else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(nxtName)){
			preName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN;
			ptnName=Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN+Constant.CHA;//允许一次加助剂到所有助剂加料完成时间差
		}
		else if(ERecord.SYZJJLWC2SSYFCZ.equals(nxtName)){//所有助剂加料完成2上升沿釜称重
			preName=ERecord.YXECJZJSSYFCZ;//允许二次加助剂上升沿釜称重
			ptnName=ERecord.YXECJZJDSYZJJLWC2FZLC;//允许二次加助剂到所有助剂加料完成2的重量差
		}
		else if(ERecord.SYZJJLWC2SSYSJ.equals(nxtName)){//所有助剂加料完成2上升沿时间
			preName=ERecord.YXECJZJSSYSJ;//允许二次加助剂上升沿时间
			ptnName=ERecord.YXECJZJDSYZJJLWC2SJC;//允许二次加助剂到所有助剂加料完成2时间差
		}
		else if(ERecord.WD85YECTLTXSSYSJ.equals(nxtName)){//温度85与二次投料提醒上升沿时间
			preName=ERecord.SWKSSSYSJ;//升温开始上升沿时间
			ptnName=ERecord.SWKSDWD85YECTLTXSJC;//升温开始到温度85与二次投料提醒时间差
		}
		else if(ERecord.SWWCSSYSJ.equals(nxtName)){//升温完成上升沿时间
			preName=ERecord.SWKSSSYSJ;//升温开始上升沿时间
			ptnName=ERecord.SWKSDSWWCSJC;//升温开始到升温完成时间差
		}
		else if(ERecord.FNSFLFXJYSJ.equals(nxtName)){//釜尿素放料阀下降沿时间
			preName=ERecord.FNSFLFSSYSJ;//釜尿素放料阀上升沿时间
			ptnName=ERecord.FNSFLFSSYDXJYSJC;//釜尿素放料阀时间差
		}
		else if(ERecord.FNSFLFXJYFCZ.equals(nxtName)){//釜尿素放料阀下降沿釜称重
			preName=ERecord.FNSFLFSSYFCZ;//釜尿素放料阀上升沿釜称重
			ptnName=ERecord.FNSFLFSSYDXJYZLC;//尿素放料阀上升沿到尿素放料阀下降沿重量差
		}
		else if((ERecord.JWWCSSYSJ).equals(nxtName)){//降温完成上升沿时间
			preName=ERecord.JHZDSSYSJ;//聚合终点上升沿时间
			ptnName=ERecord.KSJWDTZJWSJC;//开始降温到停止降温时间差
		}
		else if((ERecord.PJWCSSYFCZ).equals(nxtName)){//排胶完成上升沿釜称重
			preName=ERecord.YXKSPJSSYFCZ;
			ptnName=ERecord.YXKSPJDPJWCFZLC;//允许开始排胶到排胶完成的重量差
		}
		else if((ERecord.PJWCSSYSJ).equals(nxtName)){//排胶完成上升沿时间
			preName=ERecord.YXKSPJSSYSJ;
			ptnName=ERecord.YXKSPJDPJWCSJC;//允许开始排胶到排胶完成时间差
		}
		else if((ERecord.FYJSSSYSJ).equals(nxtName)){//反应结束上升沿时间
			preName=ERecord.BLKSSSYSJ;
			ptnName=ERecord.BLKSDFYJSSJC;
		}
		String preValue=null;
		String ptnUnit = null;
		preValue=processVarMapper.getPreValueByPreName(preName);
		if(nxtName.contains(Constant.FU+Constant.CHENG_ZHONG)) {//计算重量差
			ptnValue=Float.valueOf(nxtValue)-Float.valueOf(preValue);
			ptnUnit = nxtPV.getUnit();
		}
		else if(nxtName.contains(Constant.SHI_JIAN)) {//计算时间差，需要调用日期工具类方法处理下
			long preValueLong = DateUtil.convertStrToLong(preValue);
			long nxtValueLong = DateUtil.convertStrToLong(nxtValue);
			ptnValue = (float)DateUtil.betweenTime(preValueLong, nxtValueLong, DateUtil.FEN);
			ptnUnit = Constant.MIN;
		}
		
		String nxtUpdateTime = nxtPV.getUpdateTime();
		Integer nxtFId = nxtPV.getFId();
		String nxtRecType = nxtPV.getRecType();
		Integer nxtParaType = nxtPV.getParaType();
		
    	ProcessVar ptnValuePV = new ProcessVar();
    	ptnValuePV.setVarName(ptnName);
    	ptnValuePV.setVarValue(ptnValue);
    	ptnValuePV.setDealBz(ProcessVar.WCL);
    	ptnValuePV.setUpdateTime(nxtUpdateTime);
    	ptnValuePV.setFId(nxtFId);
    	ptnValuePV.setRecType(nxtRecType);
    	ptnValuePV.setUnit(ptnUnit);
    	ptnValuePV.setParaType(nxtParaType);
    	
		return ptnValuePV;
	}

	public int deleteDealed(int fId) {
		int count=processVarMapper.getDealedCount(fId);
		if(count>0)
			count=processVarMapper.deleteDealed(fId);
		return count;
	}

}

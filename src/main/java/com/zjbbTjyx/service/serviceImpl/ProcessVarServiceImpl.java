package com.zjbbTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		System.out.println("proVarList==="+proVarList);
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
		if(ERecord.JQFLWCSSYFCZ.equals(nxtName)) {//甲醛放料完成上升沿釜称重
			preName=ERecord.JQBLKSSSYFCZ;//甲醛备料开始上升沿釜称重
			ptnName=ERecord.JQBLKSDJQFLWCFZLC;//甲醛备料开始到甲醛放料完成的重量差
		}
		else if(ERecord.JQFLWCSSYSJ.equals(nxtName)){//甲醛放料完成上升沿时间
			preName=ERecord.JQBLKSSSYSJ;//甲醛备料开始上升沿时间
			ptnName=ERecord.JQBLKSDJQFLWCSJC;//甲醛备料开始到甲醛放料完成时间差
		}
		else if(ERecord.SYZJJLWC1SSYFCZ.equals(nxtName)){//所有助剂加料完成1上升沿釜称重
			preName=ERecord.YXYCJZJSSYFCZ;//允许一次加助剂上升沿釜称重
			ptnName=ERecord.YXYCJZJDSYZJJLWC1FZLC;//允许一次加助剂到所有助剂加料完成1的重量差
		}
		else if(ERecord.SYZJJLWC1SSYSJ.equals(nxtName)){//所有助剂加料完成1上升沿时间
			preName=ERecord.YXYCJZJSSYSJ;//允许一次加助剂上升沿时间
			ptnName=ERecord.YXYCJZJDSYZJJLWC1SJC;//允许一次加助剂到所有助剂加料完成时间差
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
		else if((ERecord.ZJLYCTJWCSSYFCZ).equals(nxtName)){//助剂6一次添加完成上升沿釜称重
			preName=ERecord.JJPHZZCSSYFCZ;
			ptnName=ERecord.JJPHZZCDZJLYCTJWCFZLC;
		}
		else if((ERecord.ZJLYCTJWCSSYSJ).equals(nxtName)){//助剂6一次添加完成上升沿时间
			preName=ERecord.JJPHZZCSSYSJ;
			ptnName=ERecord.JJPHZZCDZJLYCTJWCSJC;
		}
		else if((ERecord.ZJLECTJWCSSYFCZ).equals(nxtName)){//助剂6二次添加完成上升沿时间
			preName=ERecord.ZJLECBLWCSSYFCZ;
			ptnName=ERecord.ZJLECBLWCDZJLECTJWCFZLC;
		}
		else if((ERecord.ZJLECTJWCSSYSJ).equals(nxtName)){//助剂6二次添加完成上升沿时间
			preName=ERecord.ZJLECBLWCSSYSJ;
			ptnName=ERecord.ZJLECBLWCDZJLECTJWCSJC;
		}
		else if((ERecord.YCJWJSTXSSYSJ).equals(nxtName)){//一次降温加酸提醒上升沿时间
			preName=ERecord.DYCBWHGSSYSJ;
			ptnName=ERecord.DYCBWHGDYCJWJSTXSJC;
		}
		else if((ERecord.ECTFSSYSJ).equals(nxtName)){//二次投粉
			preName=ERecord.DYCBWHGSSYSJ;
			ptnName=ERecord.DYCBWHGDECTFSJC;
		}
		String preValue=null;
		String ptnUnit = null;
		preValue=processVarMapper.getPreValueByPreName(preName);
		if(!StringUtils.isEmpty(preValue)) {
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

	@Override
	public boolean checkAllowAdd(List<TriggerVar> triggerVarList) {
		// TODO Auto-generated method stub
		boolean allowAdd=false;
		String pvVarName=null;
		TriggerVar triggerVar1 = null;
		TriggerVar triggerVar2 = null;
        for (int i = 0; i < triggerVarList.size(); i++) {
            TriggerVar triggerVar = triggerVarList.get(i);
			if(i==0)
				triggerVar1 = triggerVar;
			else if(i==1)
				triggerVar2 = triggerVar;
		}
        String tv1VarName = triggerVar1.getVarName();
        
        String tv2VarName = null;
        Float tv2VarValue = null;
        if(triggerVar2!=null) {
	        tv2VarName = triggerVar2.getVarName();
	        tv2VarValue = triggerVar2.getVarValue();
        }
        
		if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI)) {//备料开始
			pvVarName = ERecord.BLKSSSYSJ;
		}
		else if (tv1VarName.startsWith(Constant.FAN_YING_JIE_SHU)){//反应结束
			pvVarName = ERecord.FYJSSSYFYFWD;//反应结束上升沿反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI)){//甲醛备料开始
			pvVarName = ERecord.JQBLKSSSYSJ;//甲醛备料开始上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG)){//甲醛放料完成
			pvVarName = ERecord.JQFLWCSSYSJ;//甲醛放料完成上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG)){//加碱ph正常
			pvVarName = ERecord.JJPHZZCSSYSJ;//加碱PH值正常上升沿时间
		}
		else if(tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI)) {//允许一次加助剂
			pvVarName = ERecord.YXYCJZJSSYSJ;//允许一次加助剂上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1)){//所有助剂加料完成1
			pvVarName = ERecord.SYZJJLWC1SSYSJ;//所有助剂加料完成1上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING)){//加粉料提醒
			if(StringUtils.isEmpty(tv2VarName)) {
				pvVarName=Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING;
			}
			else {
				if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
					if(tv2VarValue==TriggerVar.UP) {
						pvVarName=ERecord.FNSFLFSSYFCZ;//釜尿素放料阀上升沿釜称重
        			}
        			else {
        				pvVarName=ERecord.FNSFLFXJYFCZ;//釜尿素放料阀下降沿釜称重
        			}
				}
			}
		}
		else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_PH_HE_GE)){//加粉料PH合格
			pvVarName=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_ZHU_JI_HOU_CE_PH_TI_XING)){//二次助剂后测PH提醒
			pvVarName=Constant.ER_CI_TOU_LIAO_PH_SHU_RU;
		}
		else if (tv1VarName.startsWith(Constant.WEN_DU_98_PH+Constant.HE_GE)){//温度98PH合格
			pvVarName=Constant.WEN_DU_98_PH;
		}
		else if (tv1VarName.startsWith(Constant.CE_LIANG_BING_SHUI_WU_DIAN_TI_XING)){//测量冰水雾点提醒
			pvVarName=Constant.CE_LIANG_BSWD_SRZ;
		}
		else if (tv1VarName.startsWith(Constant.CE_SHUI_SHU_TI_XING)){//测水数提醒
			pvVarName=Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ;
		}
		else if (tv1VarName.startsWith(Constant.ZHONG_JIAN_SHUI_PH_TI_XING)){//终检水PH提醒
			pvVarName=Constant.ZHONG_JIAN_SHUI_SHU;
		}
		else if (tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG)){//助剂6二次添加完成
			pvVarName=ERecord.ZJLECTJWCSSYFYFWD;//助剂6二次添加完成上升沿反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_QI_DONG)){//第一次保温启动
			pvVarName=ERecord.DYCBWQDSSYFYFWD;//第一次保温启动反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.DI_YI_CI_BAO_WEN_HE_GE)){//第一次保温合格
			pvVarName=ERecord.DYCBWHGSSYFYFWD;//第一次保温合格反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING)){//一次降温加酸提醒？？？？？

		}
		else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE)){//一次降温加酸合格？？？？？

		}
		else if (tv1VarName.startsWith(Constant.ER_CI_TOU_FEN)){//二次投粉？？？？？

		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_215_QI_DONG)){//二次加215启动？？？？？

		}
		else if (Constant.ER_CI_JIA_215_WAN_CHENG.equals(tvVarName1Pre)){//二次加215完成？？？？？

		}
		else if (Constant.ER_CI_JIA_SHUI_QI_DONG.equals(tvVarName1Pre)){//二次加水启动？？？？？

		}
		else if (Constant.ER_CI_JIA_SHUI_WAN_CHENG.equals(tvVarName1Pre)){//二次加水完成？？？？？

		}
		else if (Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING.equals(tvVarName1Pre)){//二次加水和小料提醒？？？？？

		}
//		else if (){//釜尿素放料阀上升沿时间???????
//
//		}
		else if(tv1VarName.startsWith(Constant.SHENG_WEN_KAI_SHI)){//升温开始
			pvVarName = ERecord.SWKSSSYSJ;//升温开始上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING)){//温度85与二次投料提醒
			pvVarName = ERecord.WD85YECTLTXSSYSJ;//温度85与二次投料提醒上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2)){//所有助剂加料完成2
			pvVarName = ERecord.SYZJJLWC2SSYSJ;//所有助剂加料完成2上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.YUN_XU_ER_CI_JIA_ZHU_JI)){//允许二次加助剂
			pvVarName = ERecord.YXECJZJSSYSJ;//允许二次加助剂上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.SHENG_WEN_WAN_CHENG)){//升温完成
			pvVarName = ERecord.SWWCSSYSJ;//升温完成上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JU_HE_ZHONG_DIAN)){//聚合终点
			pvVarName = ERecord.JHZDSSYSJ;//聚合终点上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIANG_WEN_WAN_CHENG)){//降温完成
			pvVarName = ERecord.JWWCSSYSJ;//降温完成上升沿时间
		}
		else if(tv1VarName.startsWith(Constant.YUN_XU_KAI_SHI_PAI_JIAO)) {//允许开始排胶
			pvVarName = ERecord.YXKSPJSSYSJ;//允许开始排胶上升沿时间
		}
		else if(tv1VarName.startsWith(Constant.PAI_JIAO_WAN_CHENG)) {//排胶完成
			pvVarName = ERecord.PJWCSSYSJ;//排胶完成上升沿时间
		}
		else if(tv1VarName.startsWith(Constant.KAI_SHI_JIA_LIAO)) {//开始加料？？？？？？

		}


		List<ProcessVar> processVarList=processVarMapper.getByVarNameFId(pvVarName,upFId);
		if(processVarList.size()==0) {
			allowAdd=true;
		}
		else {
			ProcessVar processVar = processVarList.get(0);
			if(Constant.BEI_LIAO_KAI_SHI.equals(tvVarName1Pre)) {
				if(processVar==null) {
					allowAdd=true;
				}
				else {
					Integer dealBz = processVar.getDealBz();
					if(ProcessVar.YCL==dealBz)
						allowAdd=true;
				}
			}
			else {
				if(processVar==null) {
					allowAdd=true;
				}
			}
		}
		return allowAdd;
	}

}

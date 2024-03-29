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
		else if((ERecord.PJWCSSYJG1ZL).equals(nxtName)) {//排胶完成上升沿胶罐1重量
			preName=ERecord.YXKSPJSSYJG1ZL;
			ptnName=ERecord.YXKSPJDPJWCJG1ZLC;//允许开始排胶到排胶完成胶罐1重量差
		}
		else if((ERecord.PJWCSSYJG2ZL).equals(nxtName)) {//排胶完成上升沿胶罐2重量
			preName=ERecord.YXKSPJSSYJG2ZL;
			ptnName=ERecord.YXKSPJDPJWCJG2ZLC;//允许开始排胶到排胶完成胶罐2重量差
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
		else if((ERecord.ECTFTXSSYSJ).equals(nxtName)){//二次投粉提醒
			preName=ERecord.DYCBWHGSSYSJ;
			ptnName=ERecord.DYCBWHGDECTFTXSJC;
		}
		String preValue=null;
		String ptnUnit = null;
		preValue=processVarMapper.getPreValueByPreName(preName);
		if(!StringUtils.isEmpty(preValue)) {
			if(nxtName.contains(Constant.FU+Constant.CHENG_ZHONG)||
			   nxtName.contains(Constant.JIAO_GUAN)&&nxtName.contains(Constant.ZHONG_LIANG)) {//计算重量差
				if(ERecord.PJWCSSYFCZ.equals(nxtName))
					ptnValue=Float.valueOf(preValue)-Float.valueOf(nxtValue);//排完胶时之前的釜重量肯定比之后的重，就用之前的-之后的
				else
					ptnValue=Float.valueOf(nxtValue)-Float.valueOf(preValue);//除了排胶完成外，其他时候都是往釜里加料，肯定是之前的比之后的重量轻
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

	@Override
	public ProcessVar getRatValuePV(String numVarName, ProcessVar denPV) {
		// TODO Auto-generated method stub
		String ratVarName=null;
		Float ratVarValue=null;
		String denVarName = denPV.getVarName();
		Float denVarValue = denPV.getVarValue();
		Integer denFId = denPV.getFId();
		String denUpdateTime = denPV.getUpdateTime();
		String denRecType = denPV.getRecType();
		Integer denParaType = denPV.getParaType();
		
		if((ERecord.YXKSPJDPJWCFZLC).equals(numVarName)&&(ERecord.YXKSPJDPJWCJG1ZLC).equals(denVarName)) {
			ratVarName=ERecord.FYFYJG1ZLCBZ;
		}
		else if((ERecord.YXKSPJDPJWCFZLC).equals(numVarName)&&(ERecord.YXKSPJDPJWCJG2ZLC).equals(denVarName)) {
			ratVarName=ERecord.FYFYJG2ZLCBZ;
		}
		ProcessVar numPV = processVarMapper.getUnDealByVarNameFId(numVarName,denFId);
		if(numPV!=null) {
			Float numValue = numPV.getVarValue();
			if((float)denVarValue==0)
				ratVarValue=(float)0;
			else
				ratVarValue=numValue/denVarValue;
		}
		
    	ProcessVar ratValuePV = new ProcessVar();
    	ratValuePV.setVarName(ratVarName);
    	ratValuePV.setVarValue(ratVarValue);
    	ratValuePV.setDealBz(ProcessVar.WCL);
    	ratValuePV.setUpdateTime(denUpdateTime);
    	ratValuePV.setFId(denFId);
    	ratValuePV.setRecType(denRecType);
    	ratValuePV.setParaType(denParaType);
    	
		return ratValuePV;
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
        Integer tv1FId=Integer.valueOf(triggerVar1.getFId());
        String tv1RecType=triggerVar1.getRecType();
        
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
			pvVarName = ERecord.FYJSSSYSJ;//反应结束上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_QUAN_BEI_LIAO_KAI_SHI)){//甲醛备料开始
			pvVarName = ERecord.JQBLKSSSYSJ;//甲醛备料开始上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG)){//甲醛放料完成
			pvVarName = ERecord.JQFLWCSSYSJ;//甲醛放料完成上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG)){//加碱ph正常
			pvVarName = Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI;//加碱前PH输入值
		}
		else if(tv1VarName.startsWith(Constant.YUN_XU_YI_CI_JIA_ZHU_JI)) {//允许一次加助剂
			pvVarName = ERecord.YXYCJZJSSYSJ;//允许一次加助剂上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1)){//所有助剂加料完成1
			pvVarName = ERecord.SYZJJLWC1SSYSJ;//所有助剂加料完成1上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.JIA_FEN_LIAO_TI_XING)){//加粉料提醒
			if(StringUtils.isEmpty(tv2VarName)) {
				if(TriggerVar.M.equals(tv1RecType))
					pvVarName=Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING;
				else
					pvVarName=Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI;//这个上升沿里的其他变量opc端未添加，暂时用这个变量来检测
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
		else if (tv1VarName.startsWith(Constant.ZHONG_JIAN_KAI_SHI)){//终检开始
			pvVarName=Constant.ZHONG_JIAN_SHUI_SHU;
		}
		else if (tv1VarName.startsWith(Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG)){//助剂6一次添加完成
			pvVarName=ERecord.ZJLYCTJWCSSYFYFWD;//助剂6一次添加完成上升沿反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.ZHU_JI_6_ER_CI_BEI_LIAO_WAN_CHENG)){//助剂6二次备料完成
			pvVarName=ERecord.ZJLECBLWCSSYFCZ;//助剂6二次备料完成上升沿釜称重
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
		else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING)){//一次降温加酸提醒
			pvVarName=ERecord.YCJWJSTXSSYFYFWD;//一次降温加酸提醒反应釜温度
		}
		else if (tv1VarName.startsWith(Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_HE_GE)){//一次降温加酸PH合格
			pvVarName=ERecord.YCJWJSL;//一次降温加酸量
		}
		else if (tv1VarName.startsWith(Constant.JIA_JIAN_PH_HE_GE)){//加碱PH合格
			pvVarName=Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN;//加碱量范围下限
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_TOU_FEN_TI_XING)){//二次投粉提醒
			if(StringUtils.isEmpty(tv2VarName)) {
				pvVarName=ERecord.ECTFTXSSYSJ;//二次投粉提醒上升沿时间
			}
			else {
				if(tv2VarName.contains(Constant.NIAO_SU_FANG_LIAO_FA)) {
					if(tv2VarValue==TriggerVar.UP) {
						pvVarName=Constant.ER_CI_TOU_FEN_TI_XING+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFSSYSJ;//釜尿素放料阀上升沿时间
        			}
        			else {
        				pvVarName=Constant.ER_CI_TOU_FEN_TI_XING+Constant.SHANG_SHENG_YAN+ERecord.FNSFLFXJYSJ;//釜尿素放料阀下降沿时间
        			}
				}
			}
			
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_215_QI_DONG)){//二次加215启动
			pvVarName = ERecord.ECJ215QDSSYSJ;//二次加215启动上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_215_WAN_CHENG)){//二次加215完成
			pvVarName = ERecord.ECJ215WCSSYSJ;//二次加215完成上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_QI_DONG)){//二次加水启动
			pvVarName = ERecord.ECJSQDSSYSJ;//二次加水启动上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_WAN_CHENG)){//二次加水完成
			pvVarName = ERecord.ECJSWCSSYSJ;//二次加水完成上升沿时间
		}
		else if (tv1VarName.startsWith(Constant.ER_CI_JIA_SHUI_HE_XIAO_LIAO_TI_XING)){//二次加水和小料提醒
			pvVarName=ERecord.ECJXLHSTXXJYFYHWD;//二次加水和小料提醒下降沿反应釜温度
		}
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
		else if(tv1VarName.startsWith(Constant.KAI_SHI_JIA_LIAO)) {//开始加料
			pvVarName = ERecord.KSJLSSYSJLTCZ;//开始加料上升沿酸计量筒称重
		}


		List<ProcessVar> processVarList=processVarMapper.getByVarNameFId(pvVarName,tv1FId);
		if(processVarList.size()==0) {
			allowAdd=true;
		}
		else {
			ProcessVar processVar = processVarList.get(0);
			if(tv1VarName.startsWith(Constant.BEI_LIAO_KAI_SHI)) {
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

	@Override
	public ProcessVar getByVarNameFId(String varName, Integer fId) {
		// TODO Auto-generated method stub
		ProcessVar processVar = processVarMapper.getUnDealByVarNameFId(varName,fId);
		return processVar;
	}

}

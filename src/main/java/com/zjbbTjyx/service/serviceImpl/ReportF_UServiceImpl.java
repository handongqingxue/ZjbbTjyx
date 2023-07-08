package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.dao.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportF_UServiceImpl implements ReportF_UService {
    @Autowired
    private ReportF_UMapper reportF_UMapper;
    
	@Override
	public int addByERecordList(List<ERecord> eRecordList) {
		// TODO Auto-generated method stub
		int count=0;
		List<ReportF_U> reportF_UList=new ArrayList<ReportF_U>();
		for (ERecord eRecord : eRecordList) {

			System.out.println(eRecord.toString());
			System.out.println("-------------------------开始生成---------------------------");
			String varName = eRecord.getVarName();
			String varValue = eRecord.getVarValue();
			String unit = eRecord.getUnit();
			String preValue = eRecord.getPreValue();
			String nxtValue = eRecord.getNxtValue();
			String ptnValue = eRecord.getPtnValue();
			String batchID = eRecord.getBatchID();
			
			if(Constant.JIA_QUAN_CHANG_JIA_XIN_XI.equals(varName)) {//甲醛厂家信息
				int rowNumber=ReportF_M.JQCJXX_RN;
				int colNumber=ReportF_M.JQCJXX_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.SAN_AN_CHANG_JIA_XIN_XI.equals(varName)) {//三安厂家信息
				int rowNumber=ReportF_M.SACJXX_RN;
				int colNumber=ReportF_M.SACJXX_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.DANG_BAN_CAO_ZUO_YUAN.equals(varName)) {//当班操作员
				int rowNumber=ReportF_M.DBCZY_RN;
				int colNumber=ReportF_M.DBCZY_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.JIE_BAN_CAO_ZUO_YUAN.equals(varName)) {//接班操作员
				int rowNumber=ReportF_M.JBCZY_RN;
				int colNumber=ReportF_M.JBCZY_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
				int rowNumber=ReportF_U.SCBH_RN;
				int colNumber=ReportF_U.SCBH_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.FAN_YING_FU.equals(varName)) {//反应釜
				int rowNumber=ReportF_U.FYF_RN;
				int colNumber=ReportF_U.FYF_CN;
				reportF_UList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if((Constant.KAI_SHI+Constant.DAO+Constant.JIE_SHU+Constant.SHI_JIAN).equals(varName)) {//开始到结束时间
				int kssjRowNumber=ReportF_U.KSSJ_RN;
				int kssjColNumber=ReportF_U.KSSJ_CN;
				reportF_UList.add(createByParams(kssjRowNumber, kssjColNumber, preValue, batchID));
				
				int jssjRowNumber=ReportF_U.JSSJ_RN;
				int jssjColNumber=ReportF_U.JSSJ_CN;
				reportF_UList.add(createByParams(jssjRowNumber, jssjColNumber, nxtValue, batchID));
				
				int scgsRowNumber=ReportF_U.SCGS_RN;
				int scgsColNumber=ReportF_U.SCGS_CN;
				reportF_UList.add(createByParams(scgsRowNumber, scgsColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {//甲醛备料开始到甲醛放料完成时间
				int jqblkssjRowNumber=ReportF_U.JQBLKSSJ_RN;
				int jqblkssjColNumber=ReportF_U.JQBLKSSJ_CN;
				reportF_UList.add(createByParams(jqblkssjRowNumber, jqblkssjColNumber, preValue, batchID));

				int jqflwcsjRowNumber=ReportF_U.JQFLWCSJ_RN;
				int jqflwcsjColNumber=ReportF_U.JQFLWCSJ_CN;
				reportF_UList.add(createByParams(jqflwcsjRowNumber, jqflwcsjColNumber, nxtValue, batchID));

				int jqblksdflwcsjcRowNumber=ReportF_U.JQBLKSDFLWCSJC_RN;
				int jqblksdflwcsjcColNumber=ReportF_U.JQBLKSDFLWCSJC_CN;
				reportF_UList.add(createByParams(jqblksdflwcsjcRowNumber, jqblksdflwcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG).equals(varName)) {//甲醛备料开始到甲醛放料完成釜重量
				int jqblksfczRowNumber=ReportF_U.JQBLKSFCZ_RN;
				int jqblksfczColNumber=ReportF_U.JQBLKSFCZ_CN;
				reportF_UList.add(createByParams(jqblksfczRowNumber, jqblksfczColNumber, preValue, batchID));

				int jqflwcfczRowNumber=ReportF_U.JQFLWCFCZ_RN;
				int jqflwcfczColNumber=ReportF_U.JQFLWCFCZ_CN;
				reportF_UList.add(createByParams(jqflwcfczRowNumber, jqflwcfczColNumber, nxtValue, batchID));

				int jqblksdflwczlcRowNumber=ReportF_U.JQBLKSDFLWCZLC_RN;
				int jqblksdflwczlcColNumber=ReportF_U.JQBLKSDFLWCZLC_CN;
				reportF_UList.add(createByParams(jqblksdflwczlcRowNumber, jqblksdflwczlcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG.equals(varName))) {//甲醛实际进料重量
				int jqsjjlzlRowNumber=ReportF_U.JQSJJLZL_RN;
				int jqsjjlzlColNumber=ReportF_U.JQSJJLZL_CN;
				reportF_UList.add(createByParams(jqsjjlzlRowNumber, jqsjjlzlColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG.equals(varName))) {//加水实际重量
				int jssjzlRowNumber=ReportF_U.JSSJZL_RN;
				int jssjzlColNumber=ReportF_U.JSSJZL_CN;
				reportF_UList.add(createByParams(jssjzlRowNumber, jssjzlColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//甲醛放料完成上升沿反应釜温度
				int jqflwcfyfwdRowNumber=ReportF_U.JQFLWCFYFWD_RN;
				int jqflwcfyfwdColNumber=ReportF_U.JQFLWCFYFWD_CN;
				reportF_UList.add(createByParams(jqflwcfyfwdRowNumber, jqflwcfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI.equals(varName))) {//加碱前PH输入值
				int jjqphsrzRowNumber=ReportF_U.JJQPHSRZ_RN;
				int jjqphsrzColNumber=ReportF_U.JJQPHSRZ_CN;
				reportF_UList.add(createByParams(jjqphsrzRowNumber, jjqphsrzColNumber, varValue, batchID));
			}
			else if((Constant.JIA_JIAN_LIANG_TI_SHI.equals(varName))) {//加碱量提示
				int jjltsRowNumber=ReportF_U.JJLTS_RN;
				int jjltsColNumber=ReportF_U.JJLTS_CN;
				reportF_UList.add(createByParams(jjltsRowNumber, jjltsColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI.equals(varName))) {//加碱后PH输入值
				int jjhphsrzRowNumber=ReportF_U.JJHPHSRZ_RN;
				int jjhphsrzColNumber=ReportF_U.JJHPHSRZ_CN;
				reportF_UList.add(createByParams(jjhphsrzRowNumber, jjhphsrzColNumber, varValue, batchID));
			}
			else if((Constant.KAI_SHI_JIA_LIAO+Constant.SHANG_SHENG_YAN+Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG).equals(varName)) {//开始加料酸计量筒称重
				int ksjlsjltczRowNumber=ReportF_U.KSJLSJLTCZ_RN;
				int ksjlsjltczColNumber=ReportF_U.KSJLSJLTCZ_CN;
				reportF_UList.add(createByParams(ksjlsjltczRowNumber, ksjlsjltczColNumber, varValue, batchID));
			}
			else if((Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.DAO+Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {//加碱PH值正常到助剂6一次添加完成时间
				int jjphzcRowNumber=ReportF_U.JJPHZCSJ_RN;
				int jjphzcColNumber=ReportF_U.JJPHZCSJ_CN;
				reportF_UList.add(createByParams(jjphzcRowNumber, jjphzcColNumber, preValue, batchID));

				int zjlyctjwcsjRowNumber=ReportF_U.ZJLYCTJWCSJ_RN;
				int zjlyctjwcsjColNumber=ReportF_U.ZJLYCTJWCSJ_CN;
				reportF_UList.add(createByParams(zjlyctjwcsjRowNumber, zjlyctjwcsjColNumber, nxtValue, batchID));

				int zjlyctjwcdjjphzzcsjcRowNumber=ReportF_U.JJPHZZCDZJLYCTJWCSJC_RN;
				int zjlyctjwcdjjphzzcsjcColNumber=ReportF_U.JJPHZZCDZJLYCTJWCSJC_CN;
				reportF_UList.add(createByParams(zjlyctjwcdjjphzzcsjcRowNumber, zjlyctjwcdjjphzzcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_PH_ZHI_ZHENG_CHANG+Constant.DAO+Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.FU+Constant.CHENG_ZHONG).equals(varName)) {//加碱PH值正常到助剂6一次添加完成釜称重
				int jjphzcfczRowNumber=ReportF_U.JJPHZZCFCZ_RN;
				int jjphzcfczColNumber=ReportF_U.JJPHZZCFCZ_CN;
				reportF_UList.add(createByParams(jjphzcfczRowNumber, jjphzcfczColNumber, preValue, batchID));

				int zjlyctjwcfczRowNumber=ReportF_U.ZJLYCTJWCFCZ_RN;
				int zjlyctjwcfczColNumber=ReportF_U.ZJLYCTJWCFCZ_CN;
				reportF_UList.add(createByParams(zjlyctjwcfczRowNumber, zjlyctjwcfczColNumber, nxtValue, batchID));

				int zjlyctjwcdjjphzzcsjcRowNumber=ReportF_U.JJPHZZCDZJLYCTJWCZLC_RN;
				int zjlyctjwcdjjphzzcsjcColNumber=ReportF_U.JJPHZZCDZJLYCTJWCZLC_CN;
				reportF_UList.add(createByParams(zjlyctjwcdjjphzzcsjcRowNumber, zjlyctjwcdjjphzzcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.ZHU_JI_6_YI_CI_TIAN_JIA_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//助剂6一次添加完成上升沿反应釜温度
				int zjlyctjwcfyfwdRowNumber=ReportF_U.ZJLYCTJWCFYFWD_RN;
				int zjlyctjwcfyfwdColNumber=ReportF_U.ZJLYCTJWCFYFWD_CN;
				reportF_UList.add(createByParams(zjlyctjwcfyfwdRowNumber, zjlyctjwcfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.ZHU_JI_LIU_ER_CI_BEI_LIAO_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SUAN_JI_LIANG_TONG_CHENG_ZHONG).equals(varName)) {//剂六二次备料完成酸计量筒称重
				int zjlecblwcsjltczRowNumber=ReportF_U.ZJLECBLWCSJLTCZ_RN;
				int zjlecblwcsjltczColNumber=ReportF_U.ZJLECBLWCSJLTCZ_CN;
				reportF_UList.add(createByParams(zjlecblwcsjltczRowNumber, zjlecblwcsjltczColNumber, varValue, batchID));
			}
			else if((Constant.ZHU_JI_LIU_ER_CI_BEI_LIAO_WAN_CHENG+Constant.DAO+Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {//剂六二次备料完成到剂六二次添加完成时间
				int zjlecblwcsjRowNumber=ReportF_U.ZJLECBLWCSJ_RN;
				int zjlecblwcsjColNumber=ReportF_U.ZJLECBLWCSJ_CN;
				reportF_UList.add(createByParams(zjlecblwcsjRowNumber, zjlecblwcsjColNumber, preValue, batchID));

				int zjlectjwcsjRowNumber=ReportF_U.ZJLECTJWCSJ_RN;
				int zjlectjwcsjColNumber=ReportF_U.ZJLECTJWCSJ_CN;
				reportF_UList.add(createByParams(zjlectjwcsjRowNumber, zjlectjwcsjColNumber, nxtValue, batchID));

				int zjlectjwcdblwcsjcRowNumber=ReportF_U.ZJLECBLWCDTJWCSJC_RN;
				int zjlectjwcdblwcsjcColNumber=ReportF_U.ZJLECBLWCDTJWCSJC_CN;
				reportF_UList.add(createByParams(zjlectjwcdblwcsjcRowNumber, zjlectjwcdblwcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.ZHU_JI_LIU_ER_CI_BEI_LIAO_WAN_CHENG+Constant.DAO+Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.FU+Constant.CHENG_ZHONG).equals(varName)) {//剂六二次备料完成到剂六二次添加完成釜称重
				int zjlecblwcfczRowNumber=ReportF_U.ZJLECBLWCFCZ_RN;
				int zjlecblwcfczColNumber=ReportF_U.ZJLECBLWCFCZ_CN;
				reportF_UList.add(createByParams(zjlecblwcfczRowNumber, zjlecblwcfczColNumber, preValue, batchID));

				int zjlectjwcfczRowNumber=ReportF_U.ZJLECTJWCFCZ_RN;
				int zjlectjwcfczColNumber=ReportF_U.ZJLECTJWCFCZ_CN;
				reportF_UList.add(createByParams(zjlectjwcfczRowNumber, zjlectjwcfczColNumber, nxtValue, batchID));

				int zjlectjwcdblwczlcRowNumber=ReportF_U.ZJLECBLWCDTJWCZLC_RN;
				int zjlectjwcdblwczlcColNumber=ReportF_U.ZJLECBLWCDTJWCZLC_CN;
				reportF_UList.add(createByParams(zjlectjwcdblwczlcRowNumber, zjlectjwcdblwczlcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.ZHU_JI_6_ER_CI_TIAN_JIA_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//助剂6二次添加完成上升沿反应釜温度
				int zjlectjwcfyfwdRowNumber=ReportF_U.ZJLECTJWCFYFWD_RN;
				int zjlectjwcfyfwdColNumber=ReportF_U.ZJLECTJWCFYFWD_CN;
				reportF_UList.add(createByParams(zjlectjwcfyfwdRowNumber, zjlectjwcfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN).equals(varName)) {//加粉料提醒为1釜尿素放料阀上升沿到下降沿时间
				int jfltxfnsflfssysjRowNumber=ReportF_U.JFLTXFNSFLFSSYSJ_RN;
				int jfltxfnsflfssysjColNumber=ReportF_U.JFLTXFNSFLFSSYSJ_CN;
				reportF_UList.add(createByParams(jfltxfnsflfssysjRowNumber, jfltxfnsflfssysjColNumber, preValue, batchID));

				int jfltxfnsflfxjysjRowNumber=ReportF_U.JFLTXFNSFLFXJYSJ_RN;
				int jfltxfnsflfxjysjColNumber=ReportF_U.JFLTXFNSFLFXJYSJ_CN;
				reportF_UList.add(createByParams(jfltxfnsflfxjysjRowNumber, jfltxfnsflfxjysjColNumber, nxtValue, batchID));

				int jfltxfnsflfssydxjysjcRowNumber=ReportF_U.JFLTXFNSFLFSSYDXJYSJC_RN;
				int jfltxfnsflfssydxjysjcColNumber=ReportF_U.JFLTXFNSFLFSSYDXJYSJC_CN;
				reportF_UList.add(createByParams(jfltxfnsflfssydxjysjcRowNumber, jfltxfnsflfssydxjysjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+Constant.FEN_LIAO_1_ZHONG_LIANG_SHE_DING).equals(varName)) {//加粉料提醒粉料1重量设定
				int jfltxssyfl1zlsdRowNumber=ReportF_U.JFLTXSSYFL1ZLSD_RN;
				int jfltxssyfl1zlsdColNumber=ReportF_U.JFLTXSSYFL1ZLSD_CN;
				reportF_UList.add(createByParams(jfltxssyfl1zlsdRowNumber, jfltxssyfl1zlsdColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+Constant.FEN_LIAO_2_ZHONG_LIANG_SHE_DING).equals(varName)) {//加粉料提醒粉料2重量设定
				int jfltxssyfl2zlsdRowNumber=ReportF_U.JFLTXSSYFL2ZLSD_RN;
				int jfltxssyfl2zlsdColNumber=ReportF_U.JFLTXSSYFL2ZLSD_CN;
				reportF_UList.add(createByParams(jfltxssyfl2zlsdRowNumber, jfltxssyfl2zlsdColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//加粉料提醒为1釜尿素放料阀下降沿反应釜温度
				int fnsflfxjyfyfwdRowNumber=ReportF_U.JFLTXFNSFLFXJYFYFWD_RN;
				int fnsflfxjyfyfwdColNumber=ReportF_U.JFLTXFNSFLFXJYFYFWD_CN;
				reportF_UList.add(createByParams(fnsflfxjyfyfwdRowNumber, fnsflfxjyfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_FEN_LIAO_TI_XING+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(varName)) {//加粉料提醒为1釜尿素放料阀上升沿到下降沿釜称重
				int jfltxfnsflfssyfczRowNumber=ReportF_U.JFLTXFNSFLFSSYFCZ_RN;
				int jfltxfnsflfssyfczColNumber=ReportF_U.JFLTXFNSFLFSSYFCZ_CN;
				reportF_UList.add(createByParams(jfltxfnsflfssyfczRowNumber, jfltxfnsflfssyfczColNumber, preValue, batchID));

				int jfltxfnsflfxjyfczRowNumber=ReportF_U.JFLTXFNSFLFXJYFCZ_RN;
				int jfltxfnsflfxjyfczColNumber=ReportF_U.JFLTXFNSFLFXJYFCZ_RN;
				reportF_UList.add(createByParams(jfltxfnsflfxjyfczRowNumber, jfltxfnsflfxjyfczColNumber, nxtValue, batchID));

				int jfltxfnsflfssydxjyzlcRowNumber=ReportF_U.JFLTXFNSFLFSSYDXJYZLC_RN;
				int jfltxfnsflfssydxjyzlcColNumber=ReportF_U.JFLTXFNSFLFSSYDXJYZLC_CN;
				reportF_UList.add(createByParams(jfltxfnsflfssydxjyzlcRowNumber, jfltxfnsflfssydxjyzlcColNumber, ptnValue+unit, batchID));
			}
			else if(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI.equals(varName)) {//加粉料PH输入值
				int jflphsrzRowNumber=ReportF_U.JFLPHSRZ_RN;
				int jflphsrzColNumber=ReportF_U.JFLPHSRZ_CN;
				reportF_UList.add(createByParams(jflphsrzRowNumber, jflphsrzColNumber, varValue+unit, batchID));
			}
			else if((Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {//升温开始到升温完成时间
				int swkssjRowNumber=ReportF_U.SWKSSJ_RN;
				int swkssjColNumber=ReportF_U.SWKSSJ_CN;
				reportF_UList.add(createByParams(swkssjRowNumber, swkssjColNumber, preValue, batchID));

				int swwcsjRowNumber=ReportF_U.SWWCSJ_RN;
				int swwcsjColNumber=ReportF_U.SWWCSJ_CN;
				reportF_UList.add(createByParams(swwcsjRowNumber, swwcsjColNumber, nxtValue, batchID));

				int swwcdkssjcRowNumber=ReportF_U.SWKSDSWWCSJC_RN;
				int swwcdkssjcColNumber=ReportF_U.SWKSDSWWCSJC_CN;
				reportF_UList.add(createByParams(swwcdkssjcRowNumber, swwcdkssjcColNumber, ptnValue+unit, batchID));
			}
			else if(Constant.ZHENG_QI_YA_LI.equals(varName)) {//蒸汽压力
				int zqylRowNumber=ReportF_U.ZQYL_RN;
				int zqylColNumber=ReportF_U.ZQYL_CN;
				reportF_UList.add(createByParams(zqylRowNumber, zqylColNumber, varValue+unit, batchID));
			}
			else if(Constant.WEN_DU_98_PH.equals(varName)) {//温度98PH
				int wd98phRowNumber=ReportF_U.WD98PH_RN;
				int wd98phColNumber=ReportF_U.WD98PH_CN;
				reportF_UList.add(createByParams(wd98phRowNumber, wd98phColNumber, varValue+unit, batchID));
			}
			else if((Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//升温完成上升沿反应釜温度
				int swwcfyfwdRowNumber=ReportF_U.SWWCFYFWD_RN;
				int swwcfyfwdColNumber=ReportF_U.SWWCFYFWD_CN;
				reportF_UList.add(createByParams(swwcfyfwdRowNumber, swwcfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//第一次保温启动上升沿时间
				int dycbwqdsjRowNumber=ReportF_U.DYCBWQDSJ_RN;
				int dycbwqdsjColNumber=ReportF_U.DYCBWQDSJ_CN;
				reportF_UList.add(createByParams(dycbwqdsjRowNumber, dycbwqdsjColNumber, varValue+unit, batchID));
			}
			else if((Constant.DI_YI_CI_BAO_WEN_QI_DONG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//第一次保温启动上升沿反应釜温度
				int dycbwqdfyfwdRowNumber=ReportF_U.DYCBWQDFYFWD_RN;
				int dycbwqdfyfwdColNumber=ReportF_U.DYCBWQDFYFWD_CN;
				reportF_UList.add(createByParams(dycbwqdfyfwdRowNumber, dycbwqdfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.DAO+Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.SHI_JIAN).equals(varName)) {//第一次保温合格到一次降温加酸提醒时间
				int dycbwhgsjRowNumber=ReportF_U.DYCBWHGSJ_RN;
				int dycbwhgsjColNumber=ReportF_U.DYCBWHGSJ_CN;
				reportF_UList.add(createByParams(dycbwhgsjRowNumber, dycbwhgsjColNumber, preValue, batchID));

				int ycjwjstxsjRowNumber=ReportF_U.YCJWJSTXSJ_RN;
				int ycjwjstxsjColNumber=ReportF_U.YCJWJSTXSJ_CN;
				reportF_UList.add(createByParams(ycjwjstxsjRowNumber, ycjwjstxsjColNumber, nxtValue, batchID));

				int ycjwjstxddycbwhgsjcRowNumber=ReportF_U.DYCBWHGDYCJWJSTXSJC_RN;
				int ycjwjstxddycbwhgsjcColNumber=ReportF_U.DYCBWHGDYCJWJSTXSJC_CN;
				reportF_UList.add(createByParams(ycjwjstxddycbwhgsjcRowNumber, ycjwjstxddycbwhgsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//一次降温加酸提醒上升沿反应釜温度
				int ycjwjstxfyfwdRowNumber=ReportF_U.YCJWJSTXFYFWD_RN;
				int ycjwjstxfyfwdColNumber=ReportF_U.YCJWJSTXFYFWD_CN;
				reportF_UList.add(createByParams(ycjwjstxfyfwdRowNumber, ycjwjstxfyfwdColNumber, varValue+unit, batchID));
			}
			else if(Constant.YI_CI_JIANG_WEN_JIA_SUAN_LIANG.equals(varName)) {//一次降温加酸量
				int ycjwjslRowNumber=ReportF_U.YCJWJSL_RN;
				int ycjwjslColNumber=ReportF_U.YCJWJSL_CN;
				reportF_UList.add(createByParams(ycjwjslRowNumber, ycjwjslColNumber, varValue+unit, batchID));
			}
			else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//一次降温加酸合格上升沿反应釜温度
				int ycjwjshgfyfwdRowNumber=ReportF_U.YCJWJSHGFYFWD_RN;
				int ycjwjshgfyfwdColNumber=ReportF_U.YCJWJSHGFYFWD_CN;
				reportF_UList.add(createByParams(ycjwjshgfyfwdRowNumber, ycjwjshgfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_HE_GE+Constant.SHANG_SHENG_YAN+Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU).equals(varName)) {//一次降温加酸合格上升沿一次降温加酸PH输入
				int ycjwjshgssyycjwjsphszRowNumber=ReportF_U.YCJWJSHGSSYYCJWJSPHSZ_RN;
				int ycjwjshgssyycjwjsphszColNumber=ReportF_U.YCJWJSHGSSYYCJWJSPHSZ_CN;
				reportF_UList.add(createByParams(ycjwjshgssyycjwjsphszRowNumber, ycjwjshgssyycjwjsphszColNumber, varValue+unit, batchID));
			}
			else if(Constant.CE_LIANG_BING_SHUI_WU_DIAN_SHU_RU_ZHI.equals(varName)) {//测量冰水雾点输入值
				int clbswdsrzRowNumber=ReportF_U.CLBSWDSRZ_RN;
				int clbswdsrzColNumber=ReportF_U.CLBSWDSRZ_CN;
				reportF_UList.add(createByParams(clbswdsrzRowNumber, clbswdsrzColNumber, varValue+unit, batchID));
			}
			else if(Constant.BAO_WEN_FEN_ZHONG_JI_SHI.equals(varName)) {//保温分钟计时
				int bwfzjs1RowNumber=ReportF_U.BWFZJS1_RN;
				int bwfzjs1ColNumber=ReportF_U.BWFZJS1_CN;
				reportF_UList.add(createByParams(bwfzjs1RowNumber, bwfzjs1ColNumber, varValue+unit, batchID));
			}
			else if(Constant.CE_20_WU_DIAN_SRZ.equals(varName)) {//测20雾点输入值
				int c20wdsrzRowNumber=ReportF_U.C20WDSRZ_RN;
				int c20wdsrzColNumber=ReportF_U.C20WDSRZ_CN;
				reportF_UList.add(createByParams(c20wdsrzRowNumber, c20wdsrzColNumber, varValue+unit, batchID));
			}
			else if((Constant.YI_CI_JIANG_WEN_JIA_SUAN_TI_XING+Constant.XIA_JIANG_YAN+Constant.YI_CI_JIANG_WEN_JIA_SUAN_PH_SHU_RU).equals(varName)) {//一次降温加酸提醒下降沿一次降温加酸PH输入
				int ycjwjstxxjyycjwjsphsrRowNumber=ReportF_U.YCJWJSTXXJYYCJWJSPHSR_RN;
				int ycjwjstxxjyycjwjsphsrColNumber=ReportF_U.YCJWJSTXXJYYCJWJSPHSR_CN;
				reportF_UList.add(createByParams(ycjwjstxxjyycjwjsphsrRowNumber, ycjwjstxxjyycjwjsphsrColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_LIANG_FAN_WEI_XIA_XIAN).equals(varName)) {//加碱量范围下限
				int jjlfwxxRowNumber=ReportF_U.JJLFWXX_RN;
				int jjlfwxxColNumber=ReportF_U.JJLFWXX_CN;
				reportF_UList.add(createByParams(jjlfwxxRowNumber, jjlfwxxColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_PH_SHU_RU).equals(varName)) {//加碱PH输入
				int jjphsrRowNumber=ReportF_U.JJPHSR_RN;
				int jjphsrColNumber=ReportF_U.JJPHSR_CN;
				reportF_UList.add(createByParams(jjphsrRowNumber, jjphsrColNumber, varValue+unit, batchID));
			}
			else if((Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.DAO+Constant.ER_CI_TOU_FEN+Constant.SHI_JIAN).equals(varName)) {//第一次到保温合格到二次投粉时间
				int ectfsjRowNumber=ReportF_U.ECTFSJ_RN;
				int ectfsjColNumber=ReportF_U.ECTFSJ_CN;
				reportF_UList.add(createByParams(ectfsjRowNumber, ectfsjColNumber, nxtValue, batchID));

				int ectfddycbwhgsjcRowNumber=ReportF_U.DYCBWHGDECTFSJC_RN;
				int ectfddycbwhgsjcColNumber=ReportF_U.DYCBWHGDECTFSJC_CN;
				reportF_UList.add(createByParams(ectfddycbwhgsjcRowNumber, ectfddycbwhgsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.ER_CI_JIA_FEN_LIAO_1_ZHONG_LIANG_SHE_DING).equals(varName)) {//二次加料粉料1重量设定
				int ecjfl1zlsdRowNumber=ReportF_U.ECJFL1ZLSD_RN;
				int ecjfl1zlsdColNumber=ReportF_U.ECJFL1ZLSD_CN;
				reportF_UList.add(createByParams(ecjfl1zlsdRowNumber, ecjfl1zlsdColNumber, varValue+unit, batchID));
			}
			else if((Constant.ER_CI_TOU_FEN+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次投粉釜尿素放料阀上升沿时间
				int ectffnsflfssysjRowNumber=ReportF_U.ECTFFNSFLFSSYSJ_RN;
				int ectffnsflfssysjColNumber=ReportF_U.ECTFFNSFLFSSYSJ_CN;
				reportF_UList.add(createByParams(ectffnsflfssysjRowNumber, ectffnsflfssysjColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_TOU_FEN+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次投粉釜尿素放料阀下降沿时间
				int ectffnsflfxjysjRowNumber=ReportF_U.ECTFFNSFLFXJYSJ_RN;
				int ectffnsflfxjysjColNumber=ReportF_U.ECTFFNSFLFXJYSJ_CN;
				reportF_UList.add(createByParams(ectffnsflfxjysjRowNumber, ectffnsflfxjysjColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_TOU_FEN+Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//二次投粉釜尿素放料阀下降沿反应釜温度
				int ectffnsflfxjyfyfwdRowNumber=ReportF_U.ECTFFNSFLFXJYFYFWD_RN;
				int ectffnsflfxjyfyfwdColNumber=ReportF_U.ECTFFNSFLFXJYFYFWD_CN;
				reportF_UList.add(createByParams(ectffnsflfxjyfyfwdRowNumber, ectffnsflfxjyfyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.BAO_WEN_HOU_JIA_ZHU_JI_6_LIANG_SHE_DING).equals(varName)) {//保温后加助剂6量设定
				int bwhjzj6lsdRowNumber=ReportF_U.BWHJZJ6LSD_RN;
				int bwhjzj6lsdCnColNumber=ReportF_U.BWHJZJ6LSD_CN;
				reportF_UList.add(createByParams(bwhjzj6lsdRowNumber, bwhjzj6lsdCnColNumber, varValue+unit, batchID));
			}
			else if((Constant.ER_CI_JIA_215_QI_DONG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次加215启动上升沿时间
				int ecj215qdsjRowNumber=ReportF_U.ECJ215QDSJ_RN;
				int ecj215qdsjColNumber=ReportF_U.ECJ215QDSJ_CN;
				reportF_UList.add(createByParams(ecj215qdsjRowNumber, ecj215qdsjColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_JIA_215_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次加215完成上升沿时间
				int ecj215wcsjRowNumber=ReportF_U.ECJ215WCSJ_RN;
				int ecj215wcsjColNumber=ReportF_U.ECJ215WCSJ_CN;
				reportF_UList.add(createByParams(ecj215wcsjRowNumber, ecj215wcsjColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_JIA_XIAO_LIAO_HE_SHUI_TI_XING+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//次加小料和水提醒下降沿反应釜温度
				int ecjxlhstxxjyfyfwdRowNumber=ReportF_U.ECJXLHSTXXJYFYFWD_RN;
				int ecjxlhstxxjyfyfwdColNumber=ReportF_U.ECJXLHSTXXJYFYFWD_CN;
				reportF_UList.add(createByParams(ecjxlhstxxjyfyfwdRowNumber, ecjxlhstxxjyfyfwdColNumber, preValue, batchID));
			}
			else if((Constant.BAO_WEN_HOU_JIA_SHUI_LIANG_SHE_DING).equals(varName)) {//保温后加水量设定
				int bwhjslsdRowNumber=ReportF_U.BWHJSLSD_RN;
				int bwhjslsdColNumber=ReportF_U.BWHJSLSD_CN;
				reportF_UList.add(createByParams(bwhjslsdRowNumber, bwhjslsdColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_JIA_SHUI_QI_DONG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次加水启动上升沿时间
				int ecjsqdsjRowNumber=ReportF_U.ECJSQDSJ_RN;
				int ecjsqdsjColNumber=ReportF_U.ECJSQDSJ_CN;
				reportF_UList.add(createByParams(ecjsqdsjRowNumber, ecjsqdsjColNumber, preValue, batchID));
			}
			else if((Constant.ER_CI_JIA_SHUI_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.SHI_JIAN).equals(varName)) {//二次加水完成上升沿时间
				int ecj215wcsjRowNumber=ReportF_U.ECJSWCSJ_RN;
				int ecj215wcsjColNumber=ReportF_U.ECJSWCSJ_CN;
				reportF_UList.add(createByParams(ecj215wcsjRowNumber, ecj215wcsjColNumber, preValue, batchID));
			}
			else if((Constant.DI_YI_CI_BAO_WEN_HE_GE+Constant.DAO+Constant.FAN_YING_JIE_SHU+Constant.SHI_JIAN).equals(varName)) {//第一次保温合格到反应结束时间
				int fyjssjRowNumber=ReportF_U.FYJSSJ_RN;
				int fyjssjColNumber=ReportF_U.FYJSSJ_CN;
				reportF_UList.add(createByParams(fyjssjRowNumber, fyjssjColNumber, nxtValue, batchID));

				int dycbwhgdfyjssjcRowNumber=ReportF_U.DYCBWHGDFYJSSJC_RN;
				int dycbwhgdfyjssjcColNumber=ReportF_U.DYCBWHGDFYJSSJC_CN;
				reportF_UList.add(createByParams(dycbwhgdfyjssjcRowNumber, dycbwhgdfyjssjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.YUN_XU_KAI_SHI_PAI_JIAO+Constant.SHANG_SHENG_YAN+Constant.FU+Constant.CHENG_ZHONG).equals(varName)) {//允许开始排胶上升沿釜称重
				int yxkspjfczRowNumber=ReportF_U.YXKSPJFCZ_RN;
				int yxkspjfczColNumber=ReportF_U.YXKSPJFCZ_CN;
				reportF_UList.add(createByParams(yxkspjfczRowNumber, yxkspjfczColNumber, varValue+unit, batchID));
			}
			else if((Constant.ZHONG_JIAN_SHUI_SHU).equals(varName)) {//终检水数
				int zjssRowNumber=ReportF_U.ZJSS_RN;
				int zjssColNumber=ReportF_U.ZJSS_CN;
				reportF_UList.add(createByParams(zjssRowNumber, zjssColNumber, varValue+unit, batchID));
			}
			else if((Constant.ZHONG_JIAN_PH).equals(varName)) {//终检PH
				int zjphRowNumber=ReportF_U.ZJPH_RN;
				int zjphColNumber=ReportF_U.ZJPH_CN;
				reportF_UList.add(createByParams(zjphRowNumber, zjphColNumber, varValue+unit, batchID));
			}
		}
		for (ReportF_U reportF_U : reportF_UList) {
			int rowNumber=Integer.valueOf(reportF_U.getRowNumber());
			int colNumber=Integer.valueOf(reportF_U.getColNumber());
			String batchID = reportF_U.getBatchID();
			int existCount=reportF_UMapper.getCount(rowNumber,colNumber,batchID);
			if(rowNumber==ReportF_M.JQCJXX_RN&&colNumber==ReportF_M.JQCJXX_CN
			 ||rowNumber==ReportF_M.SACJXX_RN&&colNumber==ReportF_M.SACJXX_CN
			 ||rowNumber==ReportF_M.DBCZY_RN&&colNumber==ReportF_M.DBCZY_CN
			 ||rowNumber==ReportF_M.JBCZY_RN&&colNumber==ReportF_M.JBCZY_CN) {
				if(existCount==0)
					count+=reportF_UMapper.add(reportF_U);
				else {
					count+=reportF_UMapper.edit(reportF_U);
				}
			}
			else {
				if(existCount==0)
					count+=reportF_UMapper.add(reportF_U);
			}
		}
		return count;
	}

    @Override
    public List<List<ReportF_U>> getReportFUPageList(String type, String startTime, String endTime, String batchID) {
        List<List<ReportF_U>> reportFUPageList = new ArrayList<List<ReportF_U>>();
        //通过条件查询批次记录
        List<ReportF_U> reportFMList = reportF_UMapper.getReportFUList(type, startTime, endTime, batchID);
        //把查询出来的批次进行分组
        Map<String, List<ReportF_U>> batchGroupMap=createGroupMap(reportFMList);
        Set<String> keySet = batchGroupMap.keySet();//遍历批次分组，把每个批次集合作为每一页的子集合，添加到父集合里
        for (String key : keySet) {
            List<ReportF_U> batchRepFUList = batchGroupMap.get(key);
            reportFUPageList.add(batchRepFUList);
        }
        return reportFUPageList;
    }
    /**
     * 创建批次分组map
     * @param reportFMList
     * @return
     */
    private Map<String, List<ReportF_U>> createGroupMap(List<ReportF_U> reportFMList) {
        //创建map集合
        Map<String, List<ReportF_U>> batchGroupMap=new HashMap<String, List<ReportF_U>>();
        //遍历全部批次记录
        for (ReportF_U reportF_U : reportFMList) {
            //获取批次记录batchID
            String batchID = reportF_U.getBatchID();
            boolean exist=checkBatchIDIfExistInGroupMap(batchID,batchGroupMap);
            if(exist) {
                List<ReportF_U> batchRepFUList = batchGroupMap.get(batchID);
                batchRepFUList.add(reportF_U);
            }
            else {
                List<ReportF_U> batchRepFUList = new ArrayList<ReportF_U>();
                batchRepFUList.add(reportF_U);
                batchGroupMap.put(batchID, batchRepFUList);
            }
        }
        return batchGroupMap;
    }
    /**
     * 验证批次id是否存在与批次组里
     * @param batchID
     * @param batchGroupMap
     * @return
     */
    private boolean checkBatchIDIfExistInGroupMap(String batchID, Map<String, List<ReportF_U>> batchGroupMap) {
        boolean exist=false;
        Set<String> keySet = batchGroupMap.keySet();
        for (String key : keySet) {
            if(key.equals(batchID)) {
                exist=true;
                break;
            }
        }
        return exist;
    }

    @Override
    public List<ReportF_U> getReportFUByBatchID(String batchID) {
        return reportF_UMapper.getReportFUByBatchID(batchID);
    }

	/**
	 * 根据参数创建报表对象
	 * @param rowNumber
	 * @param colNumber
	 * @param value
	 * @param batchID
	 * @return
	 */
	private ReportF_U createByParams(int rowNumber, int colNumber, String value, String batchID) {
		// TODO Auto-generated method stub
		ReportF_U reportF_U=new ReportF_U();
		reportF_U.setRowNumber(rowNumber+"");
		reportF_U.setColNumber(colNumber+"");
		reportF_U.setValue(value);
		reportF_U.setBatchID(batchID);
		
		return reportF_U;
	}

	@Override
	public int resetCTabInp(String batchID) {
		// TODO Auto-generated method stub
		List<ReportF_U> rFUList=new ArrayList<ReportF_U>();
		
		ReportF_U jqcjxxRFU=new ReportF_U();
		jqcjxxRFU.setRowNumber(ReportF_U.JQCJXX_RN+"");
		jqcjxxRFU.setColNumber(ReportF_U.JQCJXX_CN+"");
		rFUList.add(jqcjxxRFU);

		ReportF_U sacjxxRFU=new ReportF_U();
		sacjxxRFU.setRowNumber(ReportF_U.SACJXX_RN+"");
		sacjxxRFU.setColNumber(ReportF_U.SACJXX_CN+"");
		rFUList.add(sacjxxRFU);

		ReportF_U dbczyRFU=new ReportF_U();
		dbczyRFU.setRowNumber(ReportF_U.DBCZY_RN+"");
		dbczyRFU.setColNumber(ReportF_U.DBCZY_CN+"");
		rFUList.add(dbczyRFU);

		ReportF_U jbczyRFU=new ReportF_U();
		jbczyRFU.setRowNumber(ReportF_U.JBCZY_RN+"");
		jbczyRFU.setColNumber(ReportF_U.JBCZY_CN+"");
		rFUList.add(jbczyRFU);
		
		return reportF_UMapper.resetCTabInp(rFUList,batchID);
	}
}

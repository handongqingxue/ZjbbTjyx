package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.dao.*;
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportF_MServiceImpl implements ReportF_MService {

    @Autowired
    private ReportF_MMapper reportF_MMapper;

    @Autowired
    private ERecordService eRecordService;

	public int addByERecordList(List<ERecord> eRecordList) {
		// TODO Auto-generated method stub
		int count=0;
		List<ReportF_M> reportF_MList=new ArrayList<ReportF_M>();
		for (ERecord eRecord : eRecordList) {
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
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.SAN_AN_CHANG_JIA_XIN_XI.equals(varName)) {//三安厂家信息
				int rowNumber=ReportF_M.SACJXX_RN;
				int colNumber=ReportF_M.SACJXX_CN;
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.DANG_BAN_CAO_ZUO_YUAN.equals(varName)) {//当班操作员
				int rowNumber=ReportF_M.DBCZY_RN;
				int colNumber=ReportF_M.DBCZY_CN;
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.JIE_BAN_CAO_ZUO_YUAN.equals(varName)) {//接班操作员
				int rowNumber=ReportF_M.JBCZY_RN;
				int colNumber=ReportF_M.JBCZY_CN;
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
				int rowNumber=ReportF_M.SCBH_RN;
				int colNumber=ReportF_M.SCBH_CN;
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if(Constant.FAN_YING_FU.equals(varName)) {//反应釜
				int rowNumber=ReportF_M.FYF_RN;
				int colNumber=ReportF_M.FYF_CN;
				reportF_MList.add(createByParams(rowNumber, colNumber, varValue, batchID));
			}
			else if((Constant.KAI_SHI+Constant.DAO+Constant.JIE_SHU+Constant.SHI_JIAN).equals(varName)) {//开始到结束时间
				int kssjRowNumber=ReportF_M.KSSJ_RN;
				int kssjColNumber=ReportF_M.KSSJ_CN;
				reportF_MList.add(createByParams(kssjRowNumber, kssjColNumber, preValue, batchID));
				
				int jssjRowNumber=ReportF_M.JSSJ_RN;
				int jssjColNumber=ReportF_M.JSSJ_CN;
				reportF_MList.add(createByParams(jssjRowNumber, jssjColNumber, nxtValue, batchID));
				
				int scgsRowNumber=ReportF_M.SCGS_RN;
				int scgsColNumber=ReportF_M.SCGS_CN;
				reportF_MList.add(createByParams(scgsRowNumber, scgsColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {//甲醛备料开始到甲醛放料完成时间
				int jqblkssjRowNumber=ReportF_M.JQBLKSSJ_RN;
				int jqblkssjColNumber=ReportF_M.JQBLKSSJ_CN;
				reportF_MList.add(createByParams(jqblkssjRowNumber, jqblkssjColNumber, preValue, batchID));
				
				int jqflwcsjRowNumber=ReportF_M.JQFLWCSJ_RN;
				int jqflwcsjColNumber=ReportF_M.JQFLWCSJ_CN;
				reportF_MList.add(createByParams(jqflwcsjRowNumber, jqflwcsjColNumber, nxtValue, batchID));
				
				int jqblksdflwcsjcRowNumber=ReportF_M.JQBLKSDFLWCSJC_RN;
				int jqblksdflwcsjcColNumber=ReportF_M.JQBLKSDFLWCSJC_CN;
				reportF_MList.add(createByParams(jqblksdflwcsjcRowNumber, jqblksdflwcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_BEI_LIAO_KAI_SHI+Constant.DAO+Constant.JIA_QUAN_FANG_LIAO_WAN_CHENG+Constant.FU+Constant.ZHONG_LIANG).equals(varName)) {//甲醛备料开始到甲醛放料完成釜重量
				int jqblksfczRowNumber=ReportF_M.JQBLKSFCZ_RN;
				int jqblksfczColNumber=ReportF_M.JQBLKSFCZ_CN;
				reportF_MList.add(createByParams(jqblksfczRowNumber, jqblksfczColNumber, preValue, batchID));
				
				int jqflwcfczRowNumber=ReportF_M.JQFLWCFCZ_RN;
				int jqflwcfczColNumber=ReportF_M.JQFLWCFCZ_CN;
				reportF_MList.add(createByParams(jqflwcfczRowNumber, jqflwcfczColNumber, nxtValue, batchID));
				
				int jqblksdflwczlcRowNumber=ReportF_M.JQBLKSDFLWCZLC_RN;
				int jqblksdflwczlcColNumber=ReportF_M.JQBLKSDFLWCZLC_CN;
				reportF_MList.add(createByParams(jqblksdflwczlcRowNumber, jqblksdflwczlcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIA_QUAN_SHI_JI_JIN_LIAO_ZHONG_LIANG.equals(varName))) {//甲醛实际进料重量
				int jqsjjlzlRowNumber=ReportF_M.JQSJJLZL_RN;
				int jqsjjlzlColNumber=ReportF_M.JQSJJLZL_CN;
				reportF_MList.add(createByParams(jqsjjlzlRowNumber, jqsjjlzlColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_SHUI_SHI_JI_ZHONG_LIANG.equals(varName))) {//加水实际重量
				int jssjzlRowNumber=ReportF_M.JSSJZL_RN;
				int jssjzlColNumber=ReportF_M.JSSJZL_CN;
				reportF_MList.add(createByParams(jssjzlRowNumber, jssjzlColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_QIAN_PH_SHU_RU_ZHI.equals(varName))) {//加碱前PH输入值
				int jjqphsrzRowNumber=ReportF_M.JJQPHSRZ_RN;
				int jjqphsrzColNumber=ReportF_M.JJQPHSRZ_CN;
				reportF_MList.add(createByParams(jjqphsrzRowNumber, jjqphsrzColNumber, varValue, batchID));
			}
			else if((Constant.JIA_JIAN_LIANG_TI_SHI.equals(varName))) {//加碱量提示
				int jjltsRowNumber=ReportF_M.JJLTS_RN;
				int jjltsColNumber=ReportF_M.JJLTS_CN;
				reportF_MList.add(createByParams(jjltsRowNumber, jjltsColNumber, varValue+unit, batchID));
			}
			else if((Constant.JIA_JIAN_HOU_PH_SHU_RU_ZHI.equals(varName))) {//加碱后PH输入值
				int jjhphsrzRowNumber=ReportF_M.JJHPHSRZ_RN;
				int jjhphsrzColNumber=ReportF_M.JJHPHSRZ_CN;
				reportF_MList.add(createByParams(jjhphsrzRowNumber, jjhphsrzColNumber, varValue, batchID));
			}
			else if((Constant.ZHU_JI_JI_LIANG_GUAN+Constant.BSF_ZJJLG1+"-"+Constant.BSF_ZJJLG2+Constant.CHENG_ZHONG).equals(varName)) {//助剂计量罐1-2称重
				int zjjlg12czRowNumber=ReportF_M.ZJJLG12CZ_RN;
				int zjjlg12czColNumber=ReportF_M.ZJJLG12CZ_CN;
				reportF_MList.add(createByParams(zjjlg12czRowNumber, zjjlg12czColNumber, varValue+unit, batchID));
			}
			else if((Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHI_JIAN).equals(varName)) {//允许一次加助剂到所有助剂加料完成1时间
				int yxycjzjsjRowNumber=ReportF_M.YXYCJZJSJ_RN;
				int yxycjzjsjColNumber=ReportF_M.YXYCJZJSJ_CN;
				reportF_MList.add(createByParams(yxycjzjsjRowNumber, yxycjzjsjColNumber, preValue, batchID));
				
				int syzjjlwc1sjRowNumber=ReportF_M.SYZJJLWC1SJ_RN;
				int syzjjlwc1sjColNumber=ReportF_M.SYZJJLWC1SJ_CN;
				reportF_MList.add(createByParams(syzjjlwc1sjRowNumber, syzjjlwc1sjColNumber, nxtValue, batchID));
				
				int yxycjzjdsyzjjlwc1sjcRowNumber=ReportF_M.YXYCJZJDSYZJJLWC1SJC_RN;
				int yxycjzjdsyzjjlwc1sjcColNumber=ReportF_M.YXYCJZJDSYZJJLWC1SJC_CN;
				reportF_MList.add(createByParams(yxycjzjdsyzjjlwc1sjcRowNumber, yxycjzjdsyzjjlwc1sjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//所有助剂加料完成1上升沿反应釜温度
				int syzjjlwc1fyfwdRowNumber=ReportF_M.SYZJJLWC1FYFWD_RN;
				int syzjjlwc1fyfwdColNumber=ReportF_M.SYZJJLWC1FYFWD_CN;
				reportF_MList.add(createByParams(syzjjlwc1fyfwdRowNumber, syzjjlwc1fyfwdColNumber, varValue+unit, batchID));
			}
			else if((Constant.YUN_XU_YI_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_1+Constant.FU+Constant.ZHONG_LIANG).equals(varName)) {//允许一次加助剂到所有助剂加料完成1釜重量
				int yxycjzjfczRowNumber=ReportF_M.YXYCJZJFCZ_RN;
				int yxycjzjfczColNumber=ReportF_M.YXYCJZJFCZ_CN;
				reportF_MList.add(createByParams(yxycjzjfczRowNumber, yxycjzjfczColNumber, preValue, batchID));
				
				int syzjjlwc1fczRowNumber=ReportF_M.SYZJJLWC1FCZ_RN;
				int syzjjlwc1fczColNumber=ReportF_M.SYZJJLWC1FCZ_CN;
				reportF_MList.add(createByParams(syzjjlwc1fczRowNumber, syzjjlwc1fczColNumber, nxtValue, batchID));
				
				int yxycjzjdsyzjjlwc1zlcRowNumber=ReportF_M.YXYCJZJDSYZJJLWC1ZLC_RN;
				int yxycjzjdsyzjjlwc1zlcColNumber=ReportF_M.YXYCJZJDSYZJJLWC1ZLC_CN;
				reportF_MList.add(createByParams(yxycjzjdsyzjjlwc1zlcRowNumber, yxycjzjdsyzjjlwc1zlcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.FEN_LIAO_ZHONG_LIANG_SHE_DING.equals(varName))) {//粉料重量设定
				int flzlsdRowNumber=ReportF_M.FLZLSD_RN;
				int flzlsdColNumber=ReportF_M.FLZLSD_CN;
				reportF_MList.add(createByParams(flzlsdRowNumber, flzlsdColNumber, varValue+unit, batchID));
			}
			else if((Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.SHANG_SHENG_YAN+Constant.DAO+Constant.XIA_JIANG_YAN+Constant.SHI_JIAN).equals(varName)) {//釜尿素放料阀上升沿到下降沿时间
				int fnsflfssysjRowNumber=ReportF_M.FNSFLFSSYSJ_RN;
				int fnsflfssysjColNumber=ReportF_M.FNSFLFSSYSJ_CN;
				reportF_MList.add(createByParams(fnsflfssysjRowNumber, fnsflfssysjColNumber, preValue, batchID));
				
				int fnsflfxjysjRowNumber=ReportF_M.FNSFLFXJYSJ_RN;
				int fnsflfxjysjColNumber=ReportF_M.FNSFLFXJYSJ_CN;
				reportF_MList.add(createByParams(fnsflfxjysjRowNumber, fnsflfxjysjColNumber, nxtValue, batchID));
				
				int fnsflfssydxjysjcRowNumber=ReportF_M.FNSFLFSSYDXJYSJC_RN;
				int fnsflfssydxjysjcColNumber=ReportF_M.FNSFLFSSYDXJYSJC_CN;
				reportF_MList.add(createByParams(fnsflfssydxjysjcRowNumber, fnsflfssydxjysjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.FU+Constant.NIAO_SU_FANG_LIAO_FA+Constant.XIA_JIANG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//釜尿素放料阀下降沿反应釜温度
				int fnsflfxjyfyfwdRowNumber=ReportF_M.FNSFLFXJYFYFWD_RN;
				int fnsflfxjyfyfwdColNumber=ReportF_M.FNSFLFXJYFYFWD_CN;
				reportF_MList.add(createByParams(fnsflfxjyfyfwdRowNumber, fnsflfxjyfyfwdColNumber, varValue+unit, batchID));
			}
			else if(Constant.JIA_FEN_LIAO_PH_SHU_RU_ZHI.equals(varName)) {//加粉料PH输入值
				int jflphsrzRowNumber=ReportF_M.JFLPHSRZ_RN;
				int jflphsrzColNumber=ReportF_M.JFLPHSRZ_CN;
				reportF_MList.add(createByParams(jflphsrzRowNumber, jflphsrzColNumber, varValue, batchID));
			}
			else if((Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHI_JIAN).equals(varName)) {//升温开始到温度85与二次投料提醒时间
				int swkssjRowNumber=ReportF_M.SWKSSJ_RN;
				int swkssjColNumber=ReportF_M.SWKSSJ_CN;
				reportF_MList.add(createByParams(swkssjRowNumber, swkssjColNumber, preValue, batchID));
				
				int wd85yectltxsjRowNumber=ReportF_M.WD85YECTLTXSJ_RN;
				int wd85yectltxsjColNumber=ReportF_M.WD85YECTLTXSJ_CN;
				reportF_MList.add(createByParams(wd85yectltxsjRowNumber, wd85yectltxsjColNumber, nxtValue, batchID));
				
				int swksdwd85yectltxsjcRowNumber=ReportF_M.SWKSDWD85YECTLTXSJC_RN;
				int swksdwd85yectltxsjcColNumber=ReportF_M.SWKSDWD85YECTLTXSJC_CN;
				reportF_MList.add(createByParams(swksdwd85yectltxsjcRowNumber, swksdwd85yectltxsjcColNumber, ptnValue+unit, batchID));
			}
			else if(varName.startsWith(Constant.ZHENG_QI_YA_LI)) {//蒸汽压力
				int zqylRowNumber=ReportF_M.ZQYL_RN;
				int zqylColNumber=ReportF_M.ZQYL_CN;
				reportF_MList.add(createByParams(zqylRowNumber, zqylColNumber, varValue+unit, batchID));
			}
			else if((Constant.WEN_DU_85_YU_ER_CI_TOU_LIAO_TI_XING+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {//温度85与二次投料提醒上升沿反应釜温度
				int wd85yectltxfyfwdRowNumber=ReportF_M.WD85YECTLTXFYFWD_RN;
				int wd85yectltxfyfwdColNumber=ReportF_M.WD85YECTLTXFYFWD_CN;
				reportF_MList.add(createByParams(wd85yectltxfyfwdRowNumber, wd85yectltxfyfwdColNumber, varValue+unit, batchID));
			}
			else if(Constant.ER_CI_TOU_LIAO_PH_SHU_RU.equals(varName)) {//二次投料PH输入
				int ectlphsrzRowNumber=ReportF_M.ECTLPHSRZ_RN;
				int ectlphsrzColNumber=ReportF_M.ECTLPHSRZ_CN;
				reportF_MList.add(createByParams(ectlphsrzRowNumber, ectlphsrzColNumber, varValue, batchID));
			}
			else if((Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.SHI_JIAN).equals(varName)) {//允许二次加助剂到所有助剂加料完成2时间
				int yxecjzjsjRowNumber=ReportF_M.YXECJZJSJ_RN;
				int yxecjzjsjColNumber=ReportF_M.YXECJZJSJ_CN;
				reportF_MList.add(createByParams(yxecjzjsjRowNumber, yxecjzjsjColNumber, preValue, batchID));
				
				int syzjjlwc2sjRowNumber=ReportF_M.SYZJJLWC2SJ_RN;
				int syzjjlwc2sjColNumber=ReportF_M.SYZJJLWC2SJ_CN;
				reportF_MList.add(createByParams(syzjjlwc2sjRowNumber, syzjjlwc2sjColNumber, nxtValue, batchID));
				
				int yxecjzjdsyzjjlwc2sjcRowNumber=ReportF_M.YXECJZJDSYZJJLWC2SJC_RN;
				int yxecjzjdsyzjjlwc2sjcColNumber=ReportF_M.YXECJZJDSYZJJLWC2SJC_CN;
				reportF_MList.add(createByParams(yxecjzjdsyzjjlwc2sjcRowNumber, yxecjzjdsyzjjlwc2sjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.YUN_XU_ER_CI_JIA_ZHU_JI+Constant.DAO+Constant.SUO_YOU_ZHU_JI_JIA_LIAO_WAN_CHENG_2+Constant.FU+Constant.CHENG_ZHONG).equals(varName)) {//允许二次加助剂到所有助剂加料完成2釜称重
				int yxecjzjfczRowNumber=ReportF_M.YXECJZJFCZ_RN;
				int yxecjzjfczColNumber=ReportF_M.YXECJZJFCZ_CN;
				reportF_MList.add(createByParams(yxecjzjfczRowNumber, yxecjzjfczColNumber, preValue, batchID));
				
				int syzjjlwc2fczRowNumber=ReportF_M.SYZJJLWC2FCZ_RN;
				int syzjjlwc2fczColNumber=ReportF_M.SYZJJLWC2FCZ_CN;
				reportF_MList.add(createByParams(syzjjlwc2fczRowNumber, syzjjlwc2fczColNumber, nxtValue, batchID));
				
				int yxecjzjdsyzjjlwc2zlcRowNumber=ReportF_M.YXECJZJDSYZJJLWC2ZLC_RN;
				int yxecjzjdsyzjjlwc2zlcColNumber=ReportF_M.YXECJZJDSYZJJLWC2ZLC_CN;
				reportF_MList.add(createByParams(yxecjzjdsyzjjlwc2zlcRowNumber, yxecjzjdsyzjjlwc2zlcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.SHENG_WEN_KAI_SHI+Constant.DAO+Constant.SHENG_WEN_WAN_CHENG+Constant.SHI_JIAN).equals(varName)) {
				int swwcsjRowNumber=ReportF_M.SWWCSJ_RN;
				int swwcsjColNumber=ReportF_M.SWWCSJ_CN;
				reportF_MList.add(createByParams(swwcsjRowNumber, swwcsjColNumber, nxtValue, batchID));
				
				int swksdswwcsjcRowNumber=ReportF_M.SWKSDSWWCSJC_RN;
				int swksdswwcsjcColNumber=ReportF_M.SWKSDSWWCSJC_CN;
				reportF_MList.add(createByParams(swksdswwcsjcRowNumber, swksdswwcsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.SHENG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {
				int swwcfyfwdRowNumber=ReportF_M.SWWCFYFWD_RN;
				int swwcfyfwdColNumber=ReportF_M.SWWCFYFWD_CN;
				reportF_MList.add(createByParams(swwcfyfwdRowNumber, swwcfyfwdColNumber, varValue+unit, batchID));
			}
			else if(Constant.WEN_DU_98_PH.equals(varName)) {
				int wd98phRowNumber=ReportF_M.WD98PH_RN;
				int wd98phColNumber=ReportF_M.WD98PH_CN;
				reportF_MList.add(createByParams(wd98phRowNumber, wd98phColNumber, varValue, batchID));
			}
			else if(Constant.CE_LIANG_BSWD_SRZ.equals(varName)) {
				int clbswdsrzRowNumber=ReportF_M.CLBSWDSRZ_RN;
				int clbswdsrzColNumber=ReportF_M.CLBSWDSRZ_CN;
				reportF_MList.add(createByParams(clbswdsrzRowNumber, clbswdsrzColNumber, varValue, batchID));
			}
			else if(Constant.CE_20_WU_DIAN_SRZ.equals(varName)) {
				int c20wdsrzRowNumber=ReportF_M.C20WDSRZ_RN;
				int c20wdsrzColNumber=ReportF_M.C20WDSRZ_CN;
				reportF_MList.add(createByParams(c20wdsrzRowNumber, c20wdsrzColNumber, varValue, batchID));
			}
			else if((Constant.JU_HE_ZHONG_DIAN+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {
				int jhzdfyfwdRowNumber=ReportF_M.JHZDFYFWD_RN;
				int jhzdfyfwdColNumber=ReportF_M.JHZDFYFWD_CN;
				reportF_MList.add(createByParams(jhzdfyfwdRowNumber, jhzdfyfwdColNumber, varValue+unit, batchID));
			}
			else if(Constant.TING_RE_JIANG_WEN_SHUI_SHU_SRZ.equals(varName)) {
				int trjwsssrzRowNumber=ReportF_M.TRJWSSSRZ_RN;
				int trjwsssrzColNumber=ReportF_M.TRJWSSSRZ_CN;
				reportF_MList.add(createByParams(trjwsssrzRowNumber, trjwsssrzColNumber, varValue, batchID));
			}
			else if((Constant.KAI_SHI_JIANG_WEN+Constant.DAO+Constant.TING_ZHI_JIANG_WEN+Constant.SHI_JIAN).equals(varName)) {
				int jhzdsjRowNumber=ReportF_M.JHZDSJ_RN;
				int jhzdsjColNumber=ReportF_M.JHZDSJ_CN;
				reportF_MList.add(createByParams(jhzdsjRowNumber, jhzdsjColNumber, preValue, batchID));
				
				int jwwcsjRowNumber=ReportF_M.JWWCSJ_RN;
				int jwwcsjColNumber=ReportF_M.JWWCSJ_CN;
				reportF_MList.add(createByParams(jwwcsjRowNumber, jwwcsjColNumber, nxtValue, batchID));
				
				int ksjwdtzjwsjcRowNumber=ReportF_M.KSJWDTZJWSJC_RN;
				int ksjwdtzjwsjcColNumber=ReportF_M.KSJWDTZJWSJC_CN;
				reportF_MList.add(createByParams(ksjwdtzjwsjcRowNumber, ksjwdtzjwsjcColNumber, ptnValue+unit, batchID));
			}
			else if((Constant.JIANG_WEN_WAN_CHENG+Constant.SHANG_SHENG_YAN+Constant.FAN_YING_FU+Constant.WEN_DU).equals(varName)) {
				int jwwcfyfwdRowNumber=ReportF_M.JWWCFYFWD_RN;
				int jwwcfyfwdColNumber=ReportF_M.JWWCFYFWD_CN;
				reportF_MList.add(createByParams(jwwcfyfwdRowNumber, jwwcfyfwdColNumber, varValue+unit, batchID));
			}
		}
		
		for (ReportF_M reportF_M : reportF_MList) {
			int rowNumber=Integer.valueOf(reportF_M.getRowNumber());
			int colNumber=Integer.valueOf(reportF_M.getColNumber());
			String batchID = reportF_M.getBatchID();
			int existCount=reportF_MMapper.getCount(rowNumber,colNumber,batchID);
			if(rowNumber==ReportF_M.JQCJXX_RN&&colNumber==ReportF_M.JQCJXX_CN
			 ||rowNumber==ReportF_M.SACJXX_RN&&colNumber==ReportF_M.SACJXX_CN
			 ||rowNumber==ReportF_M.DBCZY_RN&&colNumber==ReportF_M.DBCZY_CN
			 ||rowNumber==ReportF_M.JBCZY_RN&&colNumber==ReportF_M.JBCZY_CN) {
				if(existCount==0)
					count+=reportF_MMapper.add(reportF_M);
				else {
					count+=reportF_MMapper.edit(reportF_M);
				}
			}
			else {
				if(existCount==0)
					count+=reportF_MMapper.add(reportF_M);
			}
		}
		return count;
	}

	public List<List<ReportF_M>> getReportFMPageList(String type, String startTime, String endTime, String batchID) {

		List<List<ReportF_M>> reportFMPageList = new ArrayList<List<ReportF_M>>();
		//通过条件查询批次记录
		List<ReportF_M> reportFMList = reportF_MMapper.getReportFMList(type, startTime, endTime, batchID);
		//把查询出来的批次进行分组
		Map<String, List<ReportF_M>> batchGroupMap=createGroupMap(reportFMList);
		Set<String> keySet = batchGroupMap.keySet();//遍历批次分组，把每个批次集合作为每一页的子集合，添加到父集合里
		for (String key : keySet) {
			List<ReportF_M> batchRepFMList = batchGroupMap.get(key);
			reportFMPageList.add(batchRepFMList);
		}
		return reportFMPageList;
	}

	public List<ReportF_M> getReportFMByBatchID(String batchID) {
		return reportF_MMapper.getReportFMByBatchID(batchID);
	}

	/**
	 * 创建批次分组map
	 * @param reportFMList
	 * @return
	 */
	private Map<String, List<ReportF_M>> createGroupMap(List<ReportF_M> reportFMList) {
		//创建map集合
		Map<String, List<ReportF_M>> batchGroupMap=new HashMap<String, List<ReportF_M>>();
		//遍历全部批次记录
		for (ReportF_M reportF_M : reportFMList) {
			//获取批次记录batchID
			String batchID = reportF_M.getBatchID();
			boolean exist=checkBatchIDIfExistInGroupMap(batchID,batchGroupMap);
			if(exist) {
				List<ReportF_M> batchRepFMList = batchGroupMap.get(batchID);
				batchRepFMList.add(reportF_M);
			}
			else {
				List<ReportF_M> batchRepFMList = new ArrayList<ReportF_M>();
				batchRepFMList.add(reportF_M);
				batchGroupMap.put(batchID, batchRepFMList);
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
	private boolean checkBatchIDIfExistInGroupMap(String batchID, Map<String, List<ReportF_M>> batchGroupMap) {
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

	/**
	 * 根据参数创建报表对象
	 * @param rowNumber
	 * @param colNumber
	 * @param value
	 * @param batchID
	 * @return
	 */
	private ReportF_M createByParams(int rowNumber, int colNumber, String value, String batchID) {
		// TODO Auto-generated method stub
		ReportF_M reportF_M=new ReportF_M();
		reportF_M.setRowNumber(rowNumber+"");
		reportF_M.setColNumber(colNumber+"");
		reportF_M.setValue(value);
		reportF_M.setBatchID(batchID);
		
		return reportF_M;
	}

	@Override
	public int resetCTabInp(String batchID) {
		// TODO Auto-generated method stub
		List<ReportF_M> rFMList=new ArrayList<ReportF_M>();
		
		ReportF_M jqcjxxRFM=new ReportF_M();
		jqcjxxRFM.setRowNumber(ReportF_M.JQCJXX_RN+"");
		jqcjxxRFM.setColNumber(ReportF_M.JQCJXX_CN+"");
		rFMList.add(jqcjxxRFM);

		ReportF_M sacjxxRFM=new ReportF_M();
		sacjxxRFM.setRowNumber(ReportF_M.SACJXX_RN+"");
		sacjxxRFM.setColNumber(ReportF_M.SACJXX_CN+"");
		rFMList.add(sacjxxRFM);

		ReportF_M dbczyRFM=new ReportF_M();
		dbczyRFM.setRowNumber(ReportF_M.DBCZY_RN+"");
		dbczyRFM.setColNumber(ReportF_M.DBCZY_CN+"");
		rFMList.add(dbczyRFM);

		ReportF_M jbczyRFM=new ReportF_M();
		jbczyRFM.setRowNumber(ReportF_M.JBCZY_RN+"");
		jbczyRFM.setColNumber(ReportF_M.JBCZY_CN+"");
		rFMList.add(jbczyRFM);
		
		return reportF_MMapper.resetCTabInp(rFMList,batchID);
	}
}

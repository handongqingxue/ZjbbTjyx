package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportF_MServiceImpl implements ReportF_MService {

    @Autowired
    private ReportF_MMapper reportF_MMapper;
    
	public int addByERecordList(List<ERecord> eRecordList) {
		// TODO Auto-generated method stub
		List<ReportF_M> reportF_MList=new ArrayList<ReportF_M>();
		for (ERecord eRecord : eRecordList) {
			String varName = eRecord.getVarName();
			String varValue = eRecord.getVarValue();
			String unit = eRecord.getUnit();
			String preValue = eRecord.getPreValue();
			String nxtValue = eRecord.getNxtValue();
			String ptnValue = eRecord.getPtnValue();
			String batchID = eRecord.getBatchID();
			
			if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
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
		}
		
		for (ReportF_M reportF_M : reportF_MList) {
			int i=reportF_MMapper.add(reportF_M);
		}
		
		return 0;
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

}

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

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
			String varName = eRecord.getVarName();
			String varValue = eRecord.getVarValue();
			String unit = eRecord.getUnit();
			String preValue = eRecord.getPreValue();
			String nxtValue = eRecord.getNxtValue();
			String ptnValue = eRecord.getPtnValue();
			String batchID = eRecord.getBatchID();
			
			if(Constant.SHENG_CHAN_BIAN_HAO.equals(varName)) {//生产编号
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
		}
		return 0;
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
}

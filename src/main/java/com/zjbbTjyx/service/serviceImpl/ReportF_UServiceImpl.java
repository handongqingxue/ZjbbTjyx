package com.zjbbTjyx.service.serviceImpl;

import com.zjbbTjyx.dao.ReportF_UMapper;
import com.zjbbTjyx.entity.ReportF_U;
import com.zjbbTjyx.service.ReportF_UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportF_UServiceImpl implements ReportF_UService {
    @Autowired
    private ReportF_UMapper reportF_UMapper;

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
}

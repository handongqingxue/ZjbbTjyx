package com.uWinOPCTjyx.controller;

import com.uWinOPCTjyx.entity.ERecord;
import com.uWinOPCTjyx.entity.ReportF_M;
import com.uWinOPCTjyx.service.ERecordService;
import com.uWinOPCTjyx.service.ReportF_MService;
import com.uWinOPCTjyx.util.Constant;
import com.uWinOPCTjyx.util.PlanResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	//http://localhost:8080/UWinOPCTjyx/report/goIndex
	//http://localhost:8080/UWinOPCTjyx/report/goTest
	public static final String MODULE_NAME="report";

    @Autowired
    private ERecordService eRecordService;
	@Autowired
	private ReportF_MService reportF_MService;

    @RequestMapping("/goIndex")
    public String goIndex(HttpServletRequest request){
        
        return MODULE_NAME+"/index";
    }

    @RequestMapping("/goCreateM")
    public String goCreateM(HttpServletRequest request){
        
        return MODULE_NAME+"/createM";
    }

    @RequestMapping("/goSearchM")
    public String goSearchM(HttpServletRequest request){
        
        return MODULE_NAME+"/searchM";
    }

    @RequestMapping("/goTest")
    public String goTest(){
        
        return MODULE_NAME+"/test";
    }
    
    /**
     * 获得左边菜单数据
     */
    @RequestMapping("/getLeftMenuData")
    @ResponseBody
    public Map<String, Object> getLeftMenuData(String type) {
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	Map<String, Object> pcjlMap = eRecordService.getListByPcjl(type);
    	
    	if(Constant.M_WSC.equals(type)) {
    		List<ERecord> mWscPcjlList = (List<ERecord>)pcjlMap.get("mWscPcjlList");
    		map.put("mWscPcjlList", mWscPcjlList);
    	}
    	else if(Constant.U_WSC.equals(type)) {
    		List<ERecord> uWscPcjlList = (List<ERecord>)pcjlMap.get("uWscPcjlList");
    		map.put("uWscPcjlList", uWscPcjlList);
    	}
    	else if(StringUtils.isEmpty(type)) {
    		List<ERecord> mWscPcjlList = (List<ERecord>)pcjlMap.get("mWscPcjlList");
    		map.put("mWscPcjlList", mWscPcjlList);
    		
    		List<ERecord> uWscPcjlList = (List<ERecord>)pcjlMap.get("uWscPcjlList");
    		map.put("uWscPcjlList", uWscPcjlList);
    		
    		List<ERecord> mYscPcjlList = (List<ERecord>)pcjlMap.get("mYscPcjlList");
    		map.put("mYscPcjlList", mYscPcjlList);
    		
    		List<ERecord> uYscPcjlList = (List<ERecord>)pcjlMap.get("uYscPcjlList");
    		map.put("uYscPcjlList", uYscPcjlList);
    	}

    	return map;
	}

    @RequestMapping("/getPcjlListByType")
    @ResponseBody
    public PlanResult getPcjlListByType(String type){
        //返回值对象
        PlanResult result=new PlanResult();
        try {
            List<ERecord> list = eRecordService.getYscPcjlListByType(type);
            result.setMsg("ok");
            result.setStatus(1);
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("no");
        } finally {
            return result;
        }
    }

    @RequestMapping("/getUnCreRepVarList")
    @ResponseBody
    public Map<String,Object> getUnCreRepVarList(String batchID) {
    	Map<String,Object> map = new HashMap<String, Object>();
		
    	List<Map<String, Object>> varMapList=eRecordService.getUnCreRepVarList(batchID);
    	
    	if(varMapList.size()>0) {
    		map.put("message","ok");
            map.put("info","查询成功");
            map.put("varMapList",varMapList);
    	}
    	else {
            map.put("message","no");
            map.put("info","查询失败");
    	}

        return map;
	}
    @RequestMapping("/getReportFMPageList")
    @ResponseBody
	public PlanResult getReportFMPageList(String type, String startTime, String endTime, String batchID){
    	
    	System.out.println("type==="+type);
    	System.out.println("startTime==="+startTime);
    	System.out.println("endTime==="+endTime);
    	System.out.println("batchID==="+batchID);
    	
        //返回值对象
        PlanResult result=new PlanResult();
        try {
            List<List<ReportF_M>> reportFMPageList = reportF_MService.getReportFMPageList(type, startTime, endTime, batchID);
            result.setData(reportFMPageList);
            result.setStatus(Constant.OK_STATUS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("暂无信息");
            result.setStatus(Constant.NO_STATUS);
        } finally {
            return result;
        }
    }

	@RequestMapping(value = "/addReportFByBatchID", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addReportFByBatchID(String batchID) {

		Map<String,Object> json=new HashMap<String, Object>();
		
		int count=0;
		List<ERecord> eRecordList=eRecordService.getListByBatchID(batchID);
		
		String recType = batchID.substring(0,1);
		if(ERecord.M.equals(recType)) {
			count=reportF_MService.addByERecordList(eRecordList);
		}
		
		if(count>0)
			count=eRecordService.updatePCJLReportedByBatchID(batchID);
		
		if (count>0){
			json.put("message","ok");
			json.put("info","生成报表成功");
		}
		else {
			json.put("message","no");
			json.put("info","生成报表失败");
		}
		
		return json;
	}
}

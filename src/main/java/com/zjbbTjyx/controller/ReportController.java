package com.zjbbTjyx.controller;

import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

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
import java.util.UUID;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	//http://localhost:8080/ZjbbTjyx/report/goIndex
	//http://localhost:8080/ZjbbTjyx/report/goPreviewPdf
	//http://localhost:8080/ZjbbTjyx/report/goTest
	public static final String MODULE_NAME="report";

    @Autowired
    private ERecordService eRecordService;
	@Autowired
	private ReportF_MService reportF_MService;
	@Autowired
	private ReportF_UService reportF_UService;
	@Autowired
	private PreviewPdfJsonService previewPdfJsonService;

    @RequestMapping("/goIndex")
    public String goIndex(HttpServletRequest request){
        
        return MODULE_NAME+"/index";
    }

    @RequestMapping("/goCreateM")
    public String goCreateM(HttpServletRequest request){
        
        return MODULE_NAME+"/inc/createM";
    }

    @RequestMapping("/goSearchM")
    public String goSearchM(HttpServletRequest request){
        
        return MODULE_NAME+"/inc/searchM";
    }

	@RequestMapping("/goSearchU")
	public String goSearchU(HttpServletRequest request){

		return MODULE_NAME+"/inc/searchU";
	}

    @RequestMapping("/goPreviewPdfM")
    public String goPreviewPdfM(){
        
        return MODULE_NAME+"/previewPdfM";
    }
	@RequestMapping("/goPreviewPdfU")
	public String goPreviewPdfU(){

		return MODULE_NAME+"/previewPdfU";
	}
	@RequestMapping("/goPreExcelM")
	public String goPreExcelM(){
		return MODULE_NAME+"/preExcelM";
	}

	@RequestMapping("/goPreExcelU")
	public String goPreExcelU(){
		return MODULE_NAME+"/preExcelU";
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
    		List<String> mWscBatchIdList = (List<String>)pcjlMap.get("mWscBatchIdList");
    		map.put("mWscBatchIdList", mWscBatchIdList);
    	}
    	else if(Constant.U_WSC.equals(type)) {
    		List<String> uWscBatchIdList = (List<String>)pcjlMap.get("uWscBatchIdList");
    		map.put("uWscBatchIdList", uWscBatchIdList);
    	}
    	else if(StringUtils.isEmpty(type)) {
    		List<String> mWscBatchIdList = (List<String>)pcjlMap.get("mWscBatchIdList");
    		map.put("mWscBatchIdList", mWscBatchIdList);
    		
    		List<String> uWscBatchIdList = (List<String>)pcjlMap.get("uWscBatchIdList");
    		map.put("uWscBatchIdList", uWscBatchIdList);
    		
    		List<String> mYscGlueTypeList = (List<String>)pcjlMap.get("mYscGlueTypeList");
    		map.put("mYscGlueTypeList", mYscGlueTypeList);
    		
    		List<String> uYscGlueTypeList = (List<String>)pcjlMap.get("uYscGlueTypeList");
    		map.put("uYscGlueTypeList", uYscGlueTypeList);
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

	@RequestMapping("/getReportFUPageList")
	@ResponseBody
	public PlanResult getReportFUPageList(String type, String startTime, String endTime, String batchID){

		//返回值对象
		PlanResult result=new PlanResult();
		try {
			List<List<ReportF_U>> reportFUPageList = reportF_UService.getReportFUPageList(type, startTime, endTime, batchID);
			result.setData(reportFUPageList);
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
	public Map<String, Object> addResportFByBatchID(String batchID) {

		Map<String,Object> json=new HashMap<String, Object>();
		
		int count=0;
		List<ERecord> eRecordList=eRecordService.getListByBatchID(batchID);
		
		String recType = batchID.substring(0,1);
		if(ERecord.M.equals(recType)) {
			count=reportF_MService.addByERecordList(eRecordList);
		}
		else if(ERecord.U.equals(recType)) {
			count=reportF_UService.addByERecordList(eRecordList);
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

	@RequestMapping(value = "/savePreReportHtml", method = RequestMethod.POST)
	@ResponseBody
	public String savePreReportHtml(String repHtmlStr) {
		
		System.out.println("repHtmlStr==="+repHtmlStr);

		PlanResult plan=new PlanResult();
		String json;

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		PreviewPdfJson prePdfJson=new PreviewPdfJson();
		prePdfJson.setUuid(uuid);
		prePdfJson.setData(repHtmlStr);
		
		int count=previewPdfJsonService.add(prePdfJson);
		if(count==0) {
			plan.setStatus(Constant.NO_STATUS);
			plan.setMsg("失败！");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(Constant.OK_STATUS);
			plan.setData(uuid);;
			json=JsonUtil.getJsonFromObject(plan);
		}
		
		return json;
	}
	
	@RequestMapping(value="/getPrePdfJsonByUuid",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String getPrePdfJsonByUuid(String uuid) {

		PlanResult plan=new PlanResult();
		String json;

		PreviewPdfJson ppf = previewPdfJsonService.getByUuid(uuid);
		plan.setData(ppf.getData());
		json=JsonUtil.getJsonFromObject(plan);
		
		return json;
	}

	@RequestMapping("/getReportFMByBatchID")
	@ResponseBody
	public PlanResult getReportFMByBatchID(String batchID){
    	PlanResult result = new PlanResult();
		try {
			List<ReportF_M> reportFMByList = reportF_MService.getReportFMByBatchID(batchID);
			result.setData(reportFMByList);
			result.setStatus(Constant.OK_STATUS);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Constant.NO_STATUS);
			result.setMsg("no");
		} finally {
			return result;
		}
	}

	@RequestMapping("/getReportFUByBatchID")
	@ResponseBody
	public PlanResult getReportFUByBatchID(String batchID){
		PlanResult result = new PlanResult();
		try {
			List<ReportF_U> reportFUByList = reportF_UService.getReportFUByBatchID(batchID);
			result.setData(reportFUByList);
			result.setStatus(Constant.OK_STATUS);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Constant.NO_STATUS);
			result.setMsg("no");
		} finally {
			return result;
		}
	}
}

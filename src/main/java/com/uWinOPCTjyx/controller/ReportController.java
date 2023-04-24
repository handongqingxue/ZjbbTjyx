package com.uWinOPCTjyx.controller;

import com.uWinOPCTjyx.entity.ERecord;
import com.uWinOPCTjyx.entity.ReportF_M;
import com.uWinOPCTjyx.service.ERecordService;
import com.uWinOPCTjyx.service.ReportF_MService;
import com.uWinOPCTjyx.util.PlanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.jvm.hotspot.debugger.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	//http://localhost:8080/UWinOPCTjyx/report/goIndex
	public static final String MODULE_NAME="report";

    @Autowired
    private ERecordService eRecordService;

    @Autowired
    private ReportF_MService reportF_mService;

    @RequestMapping("/goIndex")
    public String goIndex(HttpServletRequest request){
        
    	loadLeftMenuData(request);

        return MODULE_NAME+"/index";
    }

    @RequestMapping("/goCreateM")
    public String goCreateM(HttpServletRequest request){
        
    	loadLeftMenuData(request);

        return MODULE_NAME+"/createM";
    }

    @RequestMapping("/goSearchM")
    public String goSearchM(HttpServletRequest request){
        
    	loadLeftMenuData(request);

        return MODULE_NAME+"/searchM";
    }
    
    /**
     * 加载左边菜单数据
     * @param request
     */
    private void loadLeftMenuData(HttpServletRequest request) {
    	
    	Map<String, Object> pcjlMap = eRecordService.getListByPcjl();
    	
    	request.setAttribute("mYscPcjlList",pcjlMap.get("mYscPcjlList"));
    	request.setAttribute("uYscPcjlList",pcjlMap.get("uYscPcjlList"));
    	request.setAttribute("mWscPcjlList",pcjlMap.get("mWscPcjlList"));
    	request.setAttribute("uWscPcjlList",pcjlMap.get("uWscPcjlList"));
	}

    @RequestMapping("/getListByType")
    @ResponseBody
    public Map<String,Object> getListByType(String type,String CreateTime,String endTime,String batchID){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            List<ERecord> list = eRecordService.getMYscPcjlList(type);
            map.put("getListByType",list);
            map.put("message","ok");
            map.put("info","查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","no");
            map.put("info","查询失败");
        } finally {
            return map;
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
    @RequestMapping("/getReportFMList")
    @ResponseBody
	public PlanResult getReportFMList(String type, String createTime, String endTime, String batchID, Integer currentPage){
        //返回值对象
        PlanResult result=new PlanResult();
        Map<String, Object> reportFMMap = null;
        try {
            reportFMMap = reportF_mService.getReportFMList(type,createTime,endTime,batchID,currentPage);
            result.setData(reportFMMap);
            result.setStatus(1);
            result.setMsg("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("no");
        } finally {
            return result;
        }
    }
}

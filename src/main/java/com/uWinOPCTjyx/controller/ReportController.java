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
    @RequestMapping("/getReportFMList")
    @ResponseBody
	public PlanResult getReportFMList(String type, String createTime, String batchID, String endTime, Integer currentPage){
        //返回值对象
        PlanResult result=new PlanResult();
        try {
            List<List<ReportF_M>> reportFMList = reportF_mService.getReportFMList(type, null, null, null, 0);
            result.setData(reportFMList);
            result.setMsg("ok");
            result.setStatus(1);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("no");
            result.setStatus(0);
        } finally {
            return result;
        }
    }
}

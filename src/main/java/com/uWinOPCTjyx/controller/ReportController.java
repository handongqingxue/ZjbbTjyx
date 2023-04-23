package com.uWinOPCTjyx.controller;

import com.uWinOPCTjyx.entity.ERecord;
import com.uWinOPCTjyx.service.ERecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	public static final String MODULE_NAME="report";

    @Autowired
    private ERecordService eRecordService;

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
    public Map<String,Object> getListByType(String type){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            List<ERecord> list = eRecordService.getListByType(type);
            System.out.println(list.toString()+";");
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

}

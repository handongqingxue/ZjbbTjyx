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
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private ERecordService eRecordService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model){
        Map<String, Object> pcjlMap = eRecordService.getListByPcjl();
        model.addAttribute("mYscPcjlList",pcjlMap.get("mYscPcjlList"));
        model.addAttribute("uYscPcjlList",pcjlMap.get("uYscPcjlList"));
        model.addAttribute("mWscPcjlList",pcjlMap.get("mWscPcjlList"));
        model.addAttribute("uWscPcjlList",pcjlMap.get("uWscPcjlList"));

        return "sys/index";
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

}

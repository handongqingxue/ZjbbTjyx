package com.uWinOPCTjyx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.service.*;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/opc")
public class OPCController {

	@Autowired
	private ZhiLiangZhongJianBzzService zhiLiangZhongJianBzzService;

	@Autowired
	private ShengChanJiLuService shengChanJiLuService;
	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/opcu")
	public String goOpcU(HttpServletRequest request) {
		
		//localhost:8080/UWinOPCTjyx/opc/test
		
		return MODULE_NAME+"/opcu";
	}

	@RequestMapping(value="/opcm")
	public String goOpcM(HttpServletRequest request, Model model) {

		//localhost:8080/UWinOPCTjyx/opc/test
		List<ZhiLiangZhongJianBzz> list = zhiLiangZhongJianBzzService.getList();
		System.out.println("size==="+list.size());
		return MODULE_NAME+"/opcm";
	}


	@RequestMapping("/addSCJL")
	@ResponseBody
	public Map<String, Object> addSCJL(ShengChanJiLu shengChanJiLu){
		System.out.println(shengChanJiLu+"-");
		Map<String,Object> json=new HashMap<String, Object>();
		try {
			int count = shengChanJiLuService.addSCJL(shengChanJiLu);
			if (count>0){
				json.put("message","ok");
				json.put("info","添加成功");
			}
			else {
				json.put("message","no");
				json.put("info","添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return json;
		}
	}
}

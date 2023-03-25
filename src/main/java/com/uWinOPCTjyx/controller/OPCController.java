package com.uWinOPCTjyx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
>>>>>>> 491426194df694570c5cc7984958710382b102ad
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

	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(){
<<<<<<< HEAD
		List<ShengChanJiLu> list = shengChanJiLuService.getList();
		for (ShengChanJiLu shengChanJiLu : list) {
			System.out.println(shengChanJiLu);
		}
=======
>>>>>>> 491426194df694570c5cc7984958710382b102ad
		System.out.println("进来");
		User user=new User(1,"张三");
		return user;
	}
<<<<<<< HEAD

	@RequestMapping("/addSCJL")
	@ResponseBody
	public Map<String, Object> addCSJL(ShengChanJiLu shengChanJiLu){
		System.out.println(shengChanJiLu+"-");
		Map<String,Object> json=new HashMap<String, Object>();
		int i = 0;
		try {
			i = shengChanJiLuService.addSCJL(shengChanJiLu);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (i>0){
			System.out.println("添加成功");
			json.put("message","yes");
			return json;
		}else {
			System.out.println("添加失败");
			json.put("message","no");
			return json;
		}
	}
=======
>>>>>>> 491426194df694570c5cc7984958710382b102ad
}

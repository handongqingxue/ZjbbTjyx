package com.uWinOPCTjyx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/opc")
public class OPCController {

	public static final String MODULE_NAME="opc";
	
	@RequestMapping(value="/test")
	public String goTest(HttpServletRequest request) {
		
		//localhost:8080/UWinOPCTjyx/opc/test
		
		return MODULE_NAME+"/test";
	}
}

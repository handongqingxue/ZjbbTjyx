package com.uWinOPCTjyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {

    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }
}

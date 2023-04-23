package com.uWinOPCTjyx.controller;

import com.uWinOPCTjyx.entity.UserList;
import com.uWinOPCTjyx.service.UserListService;
import com.uWinOPCTjyx.util.PlanResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserListService userListService;

    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public PlanResult login(UserList user, HttpServletRequest request){
        //返回值对象
        PlanResult plan=new PlanResult();
        HttpSession session = request.getSession();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPsd());
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()){
                //使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            plan.setStatus(0);
            plan.setMsg("验证失败");
            return plan;
        }
        UserList principal = (UserList) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("user",principal);
        plan.setStatus(1);
        plan.setMsg("验证成功");
        plan.setUrl("/report/goIndex");
        return plan;
    }

    //注销
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        System.out.println("跳转");
        return "login";
    }

    //注册用户
    @RequestMapping("/addUser")
    public void addUser(UserList user){
        System.out.println("add");
        System.out.println(user.toString());
        userListService.addUser(user);
    }

}

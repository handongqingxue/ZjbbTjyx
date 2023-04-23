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
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserListService userListService;

    @RequestMapping("/loginPage")
    public String loginPage(UserList user){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(UserList user, HttpServletRequest request){
        System.out.println("进来"+user.toString());
        Map<String,Object> json = new HashMap<String, Object>();
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
            json.put("status",0);
            json.put("msg","验证失败");
            return json;
        }
        UserList principal = (UserList) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("user",principal);
        json.put("status",1);
        json.put("msg","验证成功");
        json.put("url","/sys/index");
        return json;

    }

    //注销
    @RequestMapping("/exit")
    @ResponseBody
    public String exit(HttpSession session){
//        Subject current = SecurityUtils.getSubject();
        return null;
    }

    //注册用户
    @RequestMapping("/addUser")
    public void addUser(UserList user){
        System.out.println("add");
        System.out.println(user.toString());
        userListService.addUser(user);
    }

}

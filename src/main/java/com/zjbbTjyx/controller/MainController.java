package com.zjbbTjyx.controller;

import com.zjbbTjyx.entity.UserList;
import com.zjbbTjyx.service.UserListService;
import com.zjbbTjyx.util.PlanResult;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {
    public static final String MODULE_NAME="report";

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
        PlanResult result=new PlanResult();
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
            result.setStatus(0);
            result.setMsg("验证失败");
            return result;
        }
        UserList principal = (UserList) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("user",principal);
        result.setStatus(1);
        result.setMsg("验证成功");
        result.setUrl("/report/goIndex");
        return result;
    }

    //注销
    @RequestMapping("/exit")
    @ResponseBody
    public String exit(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "main/goLogin";
    }

    //添加用户页面
    @RequestMapping("/goAddUserPage")
    public String goAddUserPage(HttpServletRequest request){

        return MODULE_NAME+"/system/user/addUser";
    }
    //注册用户
    @RequestMapping("/addUser")
    public void addUser(UserList user){
        userListService.addUser(user);
    }

    //删除用户
    @RequestMapping("/delUser")
    @ResponseBody
    public PlanResult delUser(Integer id){
        System.out.println(id+"p");
        List<Integer> idList=new ArrayList<Integer>();
        idList.add(id);
        PlanResult planResult=new PlanResult();
        int i = 0;
        try {
            i = userListService.delUser(idList);
            planResult.setMsg("ok");
            planResult.setStatus(1);
            planResult.setData(i);
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setMsg("no");
            planResult.setStatus(0);
        } finally {
            return planResult;
        }
    }
    //查询全部用户
    @RequestMapping("/getUserList")
    @ResponseBody
    public PlanResult getUserList(String UserName,String RealName){
        PlanResult planResult=new PlanResult();
        List<UserList> users = null;
        try {
            users = userListService.getUserList(UserName,RealName);
            planResult.setMsg("查询成功!");
            planResult.setStatus(1);
            planResult.setData(users);
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setMsg("查询失败!");
            planResult.setStatus(0);
        } finally {
            return planResult;
        }
    }


}

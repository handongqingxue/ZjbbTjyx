package com.uWinOPCTjyx.controller;

import com.uWinOPCTjyx.entity.UserList;
import com.uWinOPCTjyx.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public Map<String,Object> login(String userName,String passWord){
        System.out.println("进来"+userName+";"+passWord);
        Map<String,Object> json = new HashMap<String, Object>();
        try {
            UserList user = new UserList();
            user.setUserName(userName);
            user.setPsd(passWord);
            UserList login = userListService.login(user);
            if (login!=null){
                json.put("status",1);
                json.put("msg","登陆成功");
            }else {
                json.put("status",0);
                json.put("msg","登陆失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }

    //注册用户
    @RequestMapping("/addUser")
    public void addUser(UserList user){
        System.out.println("add");
        System.out.println(user.toString());
        userListService.addUser(user);
    }

}

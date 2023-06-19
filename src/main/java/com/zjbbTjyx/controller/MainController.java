package com.zjbbTjyx.controller;

import com.sun.tools.javac.util.Convert;
import com.zjbbTjyx.entity.Role;
import com.zjbbTjyx.entity.UserList;
import com.zjbbTjyx.service.RoleService;
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

    @Autowired
    private RoleService roleService;

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

    //修改用户页面
    @RequestMapping("/goEditUserPage")
    public String goEditUserPage(HttpServletRequest request,Integer Id){
        UserList user = userListService.getUserById(Id);//通过id查询当前用户
        List<Role> roleList = roleService.getRoleList(null, null);//查询全部的角色
        List<Role> roleByUserId = roleService.getRoleByUserId(Id); //查询用户id判断出他的角色id
        request.setAttribute("user",user);//当前用户信息
        request.setAttribute("roleList",roleList);//所有角色的集合
        request.setAttribute("roleByUserId",roleByUserId);//用户已有的角色id
        return MODULE_NAME+"/system/user/editUser";
    }

    //修改用户信息
    @RequestMapping("/editUser")
    @ResponseBody
    public PlanResult editUser(UserList user,Integer RId){
        System.out.println(user+";"+RId);
        PlanResult planResult=new PlanResult();
        try {
            int i = userListService.editUser(user);//修改用户信息
            int i1 = roleService.delUserRoleByUserId(user.getId());//通过userid删除关系表的数据
            int i2 = roleService.addUserRole(user.getId(), RId);//重新写入用户和角色的关系
            planResult.setMsg("ok");
            planResult.setStatus(1);
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setMsg("no");
            planResult.setStatus(0);
        } finally {
            return planResult;
        }
    }
    //角色页面
    @RequestMapping("/goRolePage")
    public String goRolePage(HttpServletRequest request){
        return MODULE_NAME+"/system/role/roleList";
    }

    //添加角色页面
    @RequestMapping("/goAddRolePage")
    public String goAddRolePage(HttpServletRequest request){
        return MODULE_NAME+"/system/role/addRole";
    }


    //注册用户
    @RequestMapping("/addUser")
    @ResponseBody
    public PlanResult addUser(UserList user,String roles){
        PlanResult planResult=new PlanResult();
        String[] split = roles.split(",");//将roles转为数组
        Integer[] roleAll=new Integer[split.length];
        for (int i=0;i<split.length;i++){//将string数组转为integer数组
            roleAll[i]=Integer.parseInt(split[i]);
        }
        try {
            int i = userListService.addUser(user);//执行添加用户表操作
            UserList userName = userListService.getUserByUserName(user.getUserName());//查询这个用户的id
            int i1 = userListService.addUserRole(userName.getId(),roleAll);
            planResult.setStatus(1);
            planResult.setMsg("ok");
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setStatus(0);
            planResult.setMsg("no");
        } finally {
            return planResult;
        }
    }

    //判断该用户名是否存在
    @RequestMapping("/checkUserName")
    @ResponseBody
    public PlanResult checkUserName(String UserName){
       PlanResult planResult=new PlanResult();
        try {
            UserList user = userListService.getUserByUserName(UserName);
            if (user!=null){
              planResult.setMsg("no");
              planResult.setStatus(1);
            }else {
                planResult.setMsg("ok");
                planResult.setStatus(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setMsg("no");
            planResult.setStatus(0);
        } finally {
            return planResult;
        }
    }

    //删除用户
    @RequestMapping("/delUser")
    @ResponseBody
    public PlanResult delUser(Integer id){
        List<Integer> idList=new ArrayList<Integer>();
        idList.add(id);
        PlanResult planResult=new PlanResult();
        int i = 0;
        try {
            i = userListService.delUser(idList);//删除该用户在用户表的数据
            int i1 = userListService.delUserRole(id);//删除用户和角色关系表里面的数据
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
            System.out.println(users.toString());
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
    //查询全部角色
    @RequestMapping("/getRoleList")
    @ResponseBody
    public PlanResult getRoleList(String RoleName,String Detail){
        PlanResult planResult=new PlanResult();
        try {
            List<Role> roleList = roleService.getRoleList(RoleName, Detail);
            planResult.setStatus(1);
            planResult.setMsg("ok");
            planResult.setData(roleList);
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setStatus(0);
            planResult.setMsg("no");
        } finally {
           return planResult;
        }
    }

    //通过id查询角色
    @RequestMapping("/getRoleById")
    @ResponseBody
    public PlanResult getRoleById(Integer Id){
        PlanResult planResult=new PlanResult();
        try {
            Role role = roleService.getRoleByRoleId(Id);
            planResult.setStatus(1);
            planResult.setMsg("ok");
            planResult.setData(role);
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setStatus(0);
            planResult.setMsg("no");
        } finally {
            return planResult;
        }
    }

    //添加角色
//    @RequestMapping("/addRole")
//    @ResponseBody
//    public PlanResult addRole(Role role){
//        PlanResult planResult=new PlanResult();
//        try {
//            int i = roleService.addRole(role);//执行添加角色表操作
//            UserList userName = userListService.getUserByUserName(user.getUserName());//查询这个用户的id
//            int i1 = userListService.addUserRole(userName.getId(), user.getType());
//            planResult.setStatus(1);
//            planResult.setMsg("ok");
//        } catch (Exception e) {
//            e.printStackTrace();
//            planResult.setStatus(0);
//            planResult.setMsg("no");
//        } finally {
//            return planResult;
//        }
//    }

    //判断该角色名是否存在
    @RequestMapping("/checkRoleName")
    @ResponseBody
    public PlanResult checkRoleName(String RoleName){
        PlanResult planResult=new PlanResult();
        try {
            Role role = roleService.getRoleByName(RoleName);
            if (role!=null){
                planResult.setMsg("no");
                planResult.setStatus(1);
            }else {
                planResult.setMsg("ok");
                planResult.setStatus(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            planResult.setMsg("no");
            planResult.setStatus(0);
        } finally {
            return planResult;
        }
    }

    //删除角色
//    @RequestMapping("/delRole")
//    @ResponseBody
//    public PlanResult delRole(Integer id){
//        List<Integer> idList=new ArrayList<Integer>();
//        idList.add(id);
//        PlanResult planResult=new PlanResult();
//        int i = 0;
//        try {
//            i = roleService.delRole(idList);//删除该角色在用户表的数据
//            int i1 = userListService.delUserRole(id);//删除用户和角色关系表里面的数据
//            planResult.setMsg("ok");
//            planResult.setStatus(1);
//            planResult.setData(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//            planResult.setMsg("no");
//            planResult.setStatus(0);
//        } finally {
//            return planResult;
//        }
//    }


}

package com.zjbbTjyx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;
import com.zjbbTjyx.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	//http://localhost:8080/ZjbbTjyx/report/goIndex
	//http://localhost:8080/ZjbbTjyx/report/goPreviewPdf
	//http://localhost:8080/ZjbbTjyx/report/goTest
	public static final String MODULE_NAME="report";

    @Autowired
    private ERecordService eRecordService;
	@Autowired
	private ReportF_MService reportF_MService;
	@Autowired
	private ReportF_UService reportF_UService;
	@Autowired
	private PreviewPdfJsonService previewPdfJsonService;
	@Autowired
	private UserListService userListService;
	@Autowired
	private RoleService roleService;

    @RequestMapping("/goIndex")
    public String goIndex(HttpServletRequest request){
    	
    	request.setAttribute("jqcjxxKey", Constant.JIA_QUAN_CHANG_JIA_XIN_XI);
    	request.setAttribute("sacjxxKey", Constant.SAN_AN_CHANG_JIA_XIN_XI);
    	request.setAttribute("dbczyKey", Constant.DANG_BAN_CAO_ZUO_YUAN);
    	request.setAttribute("jbczyKey", Constant.JIE_BAN_CAO_ZUO_YUAN);
        
        return MODULE_NAME+"/index";
    }
	@RequestMapping("/goTest2")
	public String goTest2(HttpServletRequest request){

		return MODULE_NAME+"/test2";
	}
    @RequestMapping("/goCreateM")
    public String goCreateM(HttpServletRequest request){
        
        return MODULE_NAME+"/inc/createM";
    }

    @RequestMapping("/goSearchM")
    public String goSearchM(HttpServletRequest request){
        
        return MODULE_NAME+"/inc/searchM";
    }

	@RequestMapping("/goSearchU")
	public String goSearchU(HttpServletRequest request){

		return MODULE_NAME+"/inc/searchU";
	}

    @RequestMapping("/goPreviewPdfM")
    public String goPreviewPdfM(){
        
        return MODULE_NAME+"/previewPdfM";
    }
	@RequestMapping("/goPreviewPdfU")
	public String goPreviewPdfU(){

		return MODULE_NAME+"/previewPdfU";
	}
	@RequestMapping("/goPreExcelM")
	public String goPreExcelM(){
		return MODULE_NAME+"/preExcelM";
	}

	@RequestMapping("/goPreExcelU")
	public String goPreExcelU(){
		return MODULE_NAME+"/preExcelU";
	}

    @RequestMapping("/goTest")
    public String goTest(){
        
        return MODULE_NAME+"/test";
    }
    
    /**
     * 获得左边菜单数据
     */
    @RequestMapping("/getLeftMenuData")
    @ResponseBody
    public Map<String, Object> getLeftMenuData(String type) {
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	Map<String, Object> pcjlMap = eRecordService.getListByPcjl(type);

    	if(Constant.M_WSC.equals(type)) {
    		List<ERecord> mWscBatchList = (List<ERecord>)pcjlMap.get("mWscBatchList");
    		map.put("mWscBatchList", mWscBatchList);
    	}
    	else if(Constant.U_WSC.equals(type)) {
    		List<ERecord> uWscBatchList = (List<ERecord>)pcjlMap.get("uWscBatchList");
    		map.put("uWscBatchList", uWscBatchList);
    	}
    	else if(StringUtils.isEmpty(type)) {
    		List<ERecord> mWscBatchList = (List<ERecord>)pcjlMap.get("mWscBatchList");
    		map.put("mWscBatchList", mWscBatchList);
    		
    		List<ERecord> uWscBatchList = (List<ERecord>)pcjlMap.get("uWscBatchList");
    		map.put("uWscBatchList", uWscBatchList);
    		
    		List<String> mYscGlueTypeList = (List<String>)pcjlMap.get("mYscGlueTypeList");
    		map.put("mYscGlueTypeList", mYscGlueTypeList);
    		
    		List<String> uYscGlueTypeList = (List<String>)pcjlMap.get("uYscGlueTypeList");
    		map.put("uYscGlueTypeList", uYscGlueTypeList);
    	}
    	return map;
	}

    @RequestMapping("/getPcjlListByType")
    @ResponseBody
    public PlanResult getPcjlListByType(String type){
        //返回值对象
        PlanResult result=new PlanResult();
        try {
            List<ERecord> list = eRecordService.getYscPcjlListByType(type);
            result.setMsg("ok");
            result.setStatus(1);
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("no");
        } finally {
            return result;
        }
    }

    /**
     * 获得未创建的报表变量(这个方法不用了)
     * @param batchID
     * @return
     */
    @RequestMapping("/getUnCreRepVarList")
    @ResponseBody
    public Map<String,Object> getUnCreRepVarList(String batchID) {
    	Map<String,Object> map = new HashMap<String, Object>();
		
    	List<Map<String, Object>> varMapList=eRecordService.getUnCreRepVarList(batchID);
    	
    	if(varMapList.size()>0) {
    		map.put("message","ok");
            map.put("info","查询成功");
            map.put("varMapList",varMapList);
    	}
    	else {
            map.put("message","no");
            map.put("info","查询失败");
    	}

        return map;
	}
    @RequestMapping("/getReportFMPageList")
    @ResponseBody
	public PlanResult getReportFMPageList(String type, String startTime, String endTime, String batchID){
    	
        //返回值对象
        PlanResult result=new PlanResult();
        try {
            List<List<ReportF_M>> reportFMPageList = reportF_MService.getReportFMPageList(type, startTime, endTime, batchID);
            result.setData(reportFMPageList);
            result.setStatus(Constant.OK_STATUS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("暂无信息");
            result.setStatus(Constant.NO_STATUS);
        } finally {
            return result;
        }
    }

	@RequestMapping("/getReportFUPageList")
	@ResponseBody
	public PlanResult getReportFUPageList(String type, String startTime, String endTime, String batchID){
		//返回值对象
		PlanResult result=new PlanResult();
		try {
			List<List<ReportF_U>> reportFUPageList = reportF_UService.getReportFUPageList(type, startTime, endTime, batchID);
			result.setData(reportFUPageList);
			result.setStatus(Constant.OK_STATUS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("暂无信息");
			result.setStatus(Constant.NO_STATUS);
		} finally {
			return result;
		}
	}
	
	@RequestMapping(value = "/resetCTabInp", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetCTabInp(String batchID) {

		Map<String,Object> json=new HashMap<String, Object>();
		
		String recType = batchID.substring(0,1);
		if(ERecord.M.equals(recType)) {
			reportF_MService.resetCTabInp(batchID);
		}
		else if(ERecord.U.equals(recType)) {
			reportF_UService.resetCTabInp(batchID);
		}
		
		json.put("status","ok");
		json.put("info","复位成功");
		
		return json;
	}
	
	@RequestMapping(value = "/addReportFByBatchID", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addReportFByBatchID(String batchID, String inputJOStr) {

		Map<String,Object> json=new HashMap<String, Object>();
		
		int count=0;
		List<ERecord> eRecordList=eRecordService.getListByBatchID(batchID);
		
		JSONObject inputJO = JSONObject.parseObject(inputJOStr);
		
		ERecord jqcjxxER=new ERecord();
		String jqcjxx = inputJO.getString(Constant.JIA_QUAN_CHANG_JIA_XIN_XI);
		jqcjxxER.setVarName(Constant.JIA_QUAN_CHANG_JIA_XIN_XI);
		jqcjxxER.setVarValue(jqcjxx);
		jqcjxxER.setBatchID(batchID);
		
		eRecordList.add(jqcjxxER);

		ERecord sacjxxER=new ERecord();
		String sacjxx = inputJO.getString(Constant.SAN_AN_CHANG_JIA_XIN_XI);
		sacjxxER.setVarName(Constant.SAN_AN_CHANG_JIA_XIN_XI);
		sacjxxER.setVarValue(sacjxx);
		sacjxxER.setBatchID(batchID);
		
		eRecordList.add(sacjxxER);

		ERecord dbczyER=new ERecord();
		String dbczy = inputJO.getString(Constant.DANG_BAN_CAO_ZUO_YUAN);
		dbczyER.setVarName(Constant.DANG_BAN_CAO_ZUO_YUAN);
		dbczyER.setVarValue(dbczy);
		dbczyER.setBatchID(batchID);
		
		eRecordList.add(dbczyER);

		ERecord jbczyER=new ERecord();
		String jbczy = inputJO.getString(Constant.JIE_BAN_CAO_ZUO_YUAN);
		jbczyER.setVarName(Constant.JIE_BAN_CAO_ZUO_YUAN);
		jbczyER.setVarValue(jbczy);
		jbczyER.setBatchID(batchID);
		
		eRecordList.add(jbczyER);
		
		String recType = batchID.substring(0,1);
		if(ERecord.M.equals(recType)) {
			count=reportF_MService.addByERecordList(eRecordList);
		}
		else if(ERecord.U.equals(recType)) {
			count=reportF_UService.addByERecordList(eRecordList);
		}
		
		if(count>0)
			count=eRecordService.updatePCJLReportedByBatchID(batchID);
		
		if (count>0){
			json.put("message","ok");
			json.put("info","生成报表成功");
		}
		else {
			json.put("message","no");
			json.put("info","生成报表失败");
		}
		
		return json;
	}

	@RequestMapping(value = "/savePreReportHtml", method = RequestMethod.POST)
	@ResponseBody
	public String savePreReportHtml(String repHtmlStr) {

		PlanResult plan=new PlanResult();
		String json;

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		PreviewPdfJson prePdfJson=new PreviewPdfJson();
		prePdfJson.setUuid(uuid);
		prePdfJson.setData(repHtmlStr);
		
		int count=previewPdfJsonService.add(prePdfJson);
		if(count==0) {
			plan.setStatus(Constant.NO_STATUS);
			plan.setMsg("失败！");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(Constant.OK_STATUS);
			plan.setData(uuid);;
			json=JsonUtil.getJsonFromObject(plan);
		}
		
		return json;
	}
	
	@RequestMapping(value="/getPrePdfJsonByUuid",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String getPrePdfJsonByUuid(String uuid) {

		PlanResult plan=new PlanResult();
		String json;

		PreviewPdfJson ppf = previewPdfJsonService.getByUuid(uuid);
		plan.setData(ppf.getData());
		json=JsonUtil.getJsonFromObject(plan);
		
		return json;
	}

	@RequestMapping("/getReportFMByBatchID")
	@ResponseBody
	public PlanResult getReportFMByBatchID(String batchID){
    	PlanResult result = new PlanResult();
		try {
			List<ReportF_M> reportFMByList = reportF_MService.getReportFMByBatchID(batchID);
			result.setData(reportFMByList);
			result.setStatus(Constant.OK_STATUS);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Constant.NO_STATUS);
			result.setMsg("no");
		} finally {
			return result;
		}
	}

	@RequestMapping("/getReportFUByBatchID")
	@ResponseBody
	public PlanResult getReportFUByBatchID(String batchID){
		PlanResult result = new PlanResult();
		try {
			List<ReportF_U> reportFUByList = reportF_UService.getReportFUByBatchID(batchID);
			result.setData(reportFUByList);
			result.setStatus(Constant.OK_STATUS);
			result.setMsg("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Constant.NO_STATUS);
			result.setMsg("no");
		} finally {
			return result;
		}
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
		List<Role> getAllRoleByUserId = roleService.getRoleByUserId(Id); //查询用户id判断出他的角色id
		request.setAttribute("user",user);//当前用户信息
		request.setAttribute("roleList",roleList);//所有角色的集合
		request.setAttribute("getAllRoleByUserId",getAllRoleByUserId);//用户已有的角色id
		return MODULE_NAME+"/system/user/editUser";
	}

	//修改用户信息
	@RequestMapping("/editUser")
	@ResponseBody
	public PlanResult editUser(UserList user,String role){
		PlanResult planResult=new PlanResult();
		String[] split = role.split(",");//将roles转为数组
		Integer[] roleAll=new Integer[split.length];
		for (int i=0;i<split.length;i++){//将string数组转为integer数组
			roleAll[i]=Integer.parseInt(split[i]);
		}
		try {
			int editUser = userListService.editUser(user);//修改用户信息
			int delUserRoleByUserId = userListService.delUserRoleByUserId(user.getId());//通过userid删除关系表的数据
			int addUserRole = userListService.addUserRole(user.getId(), roleAll);//重新写入用户和角色的关系
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
	public PlanResult addUser(UserList user,String role){
		PlanResult planResult=new PlanResult();
		String[] split = role.split(",");//将roles转为数组
		Integer[] roleAll=new Integer[split.length];
		for (int i=0;i<split.length;i++){//将string数组转为integer数组
			roleAll[i]=Integer.parseInt(split[i]);
		}
		try {
			int addUser = userListService.addUser(user);//执行添加用户表操作
			UserList userName = userListService.getUserByUserName(user.getUserName());//查询这个用户的id
			int addUserRole = userListService.addUserRole(userName.getId(),roleAll);
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

	//获得全部操作员
	@RequestMapping("/getOperatorList")
	@ResponseBody
	public PlanResult getOperatorList(){
		PlanResult planResult=new PlanResult();
		try {
			List<UserList> operatorList = userListService.getOperatorList();
			planResult.setStatus(1);
			planResult.setMsg("ok");
			planResult.setData(operatorList);
		} catch (Exception e) {
			e.printStackTrace();
			planResult.setStatus(0);
			planResult.setMsg("no");
		} finally {
			return planResult;
		}
	}
}

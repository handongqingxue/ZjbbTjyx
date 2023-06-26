package com.zjbbTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjbbTjyx.dao.*;
import com.zjbbTjyx.entity.*;
import com.zjbbTjyx.service.*;

@Service
public class TriggerVarServiceImpl implements TriggerVarService {
	
    @Autowired
    private TriggerVarMapper triggerVarMapper;

	public int editFromList(List<TriggerVar> triggerVarList) {
		// TODO Auto-generated method stub
		int editCount=0;
		List<TriggerVar> addTVList=new ArrayList<TriggerVar>();
		List<TriggerVar> editTVList=new ArrayList<TriggerVar>();
		TriggerVar tv=null;
		
		List<String> varNameList=new ArrayList<String>();
		for (TriggerVar triggerVar : triggerVarList) {
			varNameList.add(triggerVar.getVarName());
		}
		
		//批量添加sql语句:https://blog.csdn.net/weixin_45137708/article/details/120866268
		//批量更新sql语句:https://blog.csdn.net/weixin_38423249/article/details/80503491
		List<TriggerVar> existTVList=triggerVarMapper.getListByVarNameList(varNameList);
		for (TriggerVar triggerVar : triggerVarList) {
			String varName = triggerVar.getVarName();
			Float varValue = triggerVar.getVarValue();
			
			tv=new TriggerVar();
			tv.setVarName(varName);
			tv.setVarValue(varValue);
			
			boolean exist=false;
			for (TriggerVar existTV : existTVList) {
				if(varName.equals(existTV.getVarName())) {
					exist=true;
					break;
				}
			}
			
			if(exist) {
				editTVList.add(tv);
			}
			else {
				boolean containF = StringUtils.contains(varName, "F");
				boolean containU = StringUtils.contains(varName, TriggerVar.U);
				if (containF){//判断变量名称里面包含F
					String recType=null;
					if (containU){//包含U就是U类
						recType=TriggerVar.U;
					}
					else {//不是U类就是M类
						recType=TriggerVar.M;
					}
					tv.setRecType(recType);
					
					//System.out.println("varName==="+varName);
					int fWz = varName.lastIndexOf("F");//F的位置
					int uWz = varName.lastIndexOf("U");//U的位置
					//System.out.println("fWz==="+fWz);
					//System.out.println("uWz==="+uWz);
					char f;
					if(fWz+1==uWz)
						f = varName.charAt(fWz + 2);//获得反应釜号
					else
						f = varName.charAt(fWz + 1);//获得反应釜号
					int fyfh=Integer.parseInt(String.valueOf(f));
					tv.setFId(fyfh);
				}
				addTVList.add(tv);
			}
		}

		if(addTVList.size()>0)
			editCount+=triggerVarMapper.addByList(addTVList);
		if(editTVList.size()>0)
			editCount+=triggerVarMapper.editByList(editTVList);
		System.out.println("editCount==="+editCount);
		
		/**
		 * 这块代码是单条添加或编辑的，太耗资源先屏蔽掉
		for (TriggerVar triggerVar : triggerVarList) {
			String varName = triggerVar.getVarName();
			int count=triggerVarMapper.getCountByVarName(varName);
			if(count==0) {
				boolean containF = StringUtils.contains(varName, "F");
				boolean containU = StringUtils.contains(varName, TriggerVar.U);
				if (containF){//判断变量名称里面包含F
					if (containU){//包含U就是U类
						triggerVar.setRecType(TriggerVar.U);
					}else {//不是U类就是M类
						triggerVar.setRecType(TriggerVar.M);
					}
					int fWz = varName.lastIndexOf("F");//F的位置
					char f = varName.charAt(fWz + 1);//获得反应釜号
					int fyfh=Integer.parseInt(String.valueOf(f));
					triggerVar.setFId(fyfh);
				}
				editCount+=triggerVarMapper.add(triggerVar);
			}
			else
				editCount+=triggerVarMapper.editByVarName(triggerVar);
		}
		**/
		
		
		return editCount;
	}

	public List<TriggerVar> getListByFIdList(List<Integer> runFIdList) {
		// TODO Auto-generated method stub
		return triggerVarMapper.getListByFIdList(runFIdList);
	}

}

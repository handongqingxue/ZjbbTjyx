package com.uWinOPCTjyx.service.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class TriggerVarServiceImpl implements TriggerVarService {
	
    @Autowired
    private TriggerVarMapper triggerVarMapper;

	public int editFromList(List<TriggerVar> triggerVarList) {
		// TODO Auto-generated method stub
		int editCount=0;
		for (TriggerVar triggerVar : triggerVarList) {
			String varName = triggerVar.getVarName();
			int count=triggerVarMapper.getCountByVarName(varName);
			if(count==0) {
				boolean containF = StringUtils.contains(varName, "F");
				boolean containU = StringUtils.contains(varName, "U");
				if (containF){//判断变量名称里面包含F
					if (containU){//包含U就是U类
						triggerVar.setRecType("U");
					}else {//不是U类就是M类
						triggerVar.setRecType("M");
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
		return editCount;
	}

	public List<TriggerVar> getListByFIdList(List<Integer> runFIdList) {
		// TODO Auto-generated method stub
		return triggerVarMapper.getListByFIdList(runFIdList);
	}

}

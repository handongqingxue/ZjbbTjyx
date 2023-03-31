package com.uWinOPCTjyx.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.*;

@Service
public class OpcBianLiangServiceImpl implements OpcBianLiangService {

    @Autowired
    private OpcBianLiangMapper opcBianLiangMapper;

	public int edit(OpcBianLiang opcBianLiang) {
		// TODO Auto-generated method stub
		String mc = opcBianLiang.getMc();
		int count=opcBianLiangMapper.getCountByMc(mc);
		if(count==0) {
			boolean existBsfM=false;
			String[] bsfFMArr = Constant.BSF_F_M_ARR;
			for (int i = 0; i < bsfFMArr.length; i++) {
				int bsfStartLoc = mc.indexOf("_")+1;
				int mcLength = mc.length();
				String bsf = mc.substring(bsfStartLoc, mcLength);
				if(bsf.equals(bsfFMArr[i])) {
					existBsfM=true;
					break;
				}
			}
			
			boolean existBsfU=false;
			String[] bsfFUArr = Constant.BSF_F_U_ARR;
			for (int i = 0; i < bsfFUArr.length; i++) {
				int bsfStartLoc = mc.indexOf("_")+1;
				int mcLength = mc.length();
				String bsf = mc.substring(bsfStartLoc, mcLength);
				if(bsf.equals(bsfFUArr[i])) {
					existBsfU=true;
					break;
				}
			}
			
			if(existBsfM) {
				opcBianLiang.setLx(OpcBianLiang.LX_M);
			}
			else if(existBsfU) {
				opcBianLiang.setLx(OpcBianLiang.LX_U);
			}
			
			count=opcBianLiangMapper.add(opcBianLiang);
		}
		else
			count=opcBianLiangMapper.editByMc(opcBianLiang);
		return count;
	}

	public int editFromList(List<OpcBianLiang> opcBianLiangList) {
		// TODO Auto-generated method stub
		int editCount=0;
		for (OpcBianLiang opcBianLiang : opcBianLiangList) {
			String mc = opcBianLiang.getMc();
			int count=opcBianLiangMapper.getCountByMc(mc);
			if(count==0) {
				boolean existBsfM=false;
				String[] bsfFMArr = Constant.BSF_F_M_ARR;
				for (int i = 0; i < bsfFMArr.length; i++) {
					int bsfStartLoc = mc.indexOf("_")+1;
					int mcLength = mc.length();
					String bsf = mc.substring(bsfStartLoc, mcLength-3);
					if(bsf.equals(bsfFMArr[i])) {
						existBsfM=true;
						opcBianLiang.setFyfh(bsf);
						break;
					}
				}
				
				boolean existBsfU=false;
				String[] bsfFUArr = Constant.BSF_F_U_ARR;
				for (int i = 0; i < bsfFUArr.length; i++) {
					int bsfStartLoc = mc.indexOf("_")+1;
					int mcLength = mc.length();
					String bsf = mc.substring(bsfStartLoc, mcLength-3);
					if(bsf.equals(bsfFUArr[i])) {
						existBsfU=true;
						opcBianLiang.setFyfh(bsf);
						break;
					}
				}
				
				if(existBsfM) {
					opcBianLiang.setLx(OpcBianLiang.LX_M);
				}
				else if(existBsfU) {
					opcBianLiang.setLx(OpcBianLiang.LX_U);
				}
				
				editCount+=opcBianLiangMapper.add(opcBianLiang);
			}
			else
				editCount+=opcBianLiangMapper.editByMc(opcBianLiang);
		}
		return editCount;
	}

	public List<OpcBianLiang> getUpSzListByMcQz(String mcQz) {
		// TODO Auto-generated method stub
		List<String> mcList=new ArrayList<String>();
		String[] bsfFMArr = Constant.BSF_F_M_ARR;
		for (int i = 0; i < bsfFMArr.length; i++) {
			mcList.add(mcQz+"_"+bsfFMArr[i]+"_AV");
		}
		
		String[] bsfFUArr = Constant.BSF_F_U_ARR;
		for (int i = 0; i < bsfFUArr.length; i++) {
			mcList.add(mcQz+"_"+bsfFUArr[i]+"_AV");
		}
		
		List<OpcBianLiang> upSzOblList=new ArrayList<OpcBianLiang>();
		List<OpcBianLiang> opcBianLiangList=opcBianLiangMapper.getListByMcList(mcList);
		for (OpcBianLiang opcBianLiang : opcBianLiangList) {
			boolean sz = Boolean.parseBoolean(opcBianLiang.getSz());
			if(sz) {
				upSzOblList.add(opcBianLiang);
			}
		}
		return upSzOblList;
	}

	public int updateSzyssByMcList(int szyss, List<String> mcList) {
		// TODO Auto-generated method stub
		return opcBianLiangMapper.updateSzyssByMcList(szyss,mcList);
	}
}

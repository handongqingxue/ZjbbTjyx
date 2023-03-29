package com.uWinOPCTjyx.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class OpcBianLiangServiceImpl implements OpcBianLiangService {

    @Autowired
    private OpcBianLiangMapper opcBianLiangMapper;

	public int edit(OpcBianLiang opcBianliang) {
		// TODO Auto-generated method stub
		int count=opcBianLiangMapper.getCountByMc(opcBianliang.getMc());
		if(count==0)
			count=opcBianLiangMapper.add(opcBianliang);
		else
			count=opcBianLiangMapper.editByMc(opcBianliang);
		return count;
	}
}

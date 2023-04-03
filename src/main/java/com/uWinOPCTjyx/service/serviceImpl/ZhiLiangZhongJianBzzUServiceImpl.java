package com.uWinOPCTjyx.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class ZhiLiangZhongJianBzzUServiceImpl implements ZhiLiangZhongJianBzzUService {

	@Autowired
	private ZhiLiangZhongJianBzzUMapper zhiLiangZhongJianBzzUDao;
	
	public List<ZhiLiangZhongJianBzzU> getList() {
		// TODO Auto-generated method stub
		return zhiLiangZhongJianBzzUDao.getList();
	}

}

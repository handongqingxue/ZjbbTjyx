package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.ShengChanJiLuMapper;
import com.uWinOPCTjyx.entity.ShengChanJiLu;
import com.uWinOPCTjyx.service.ShengChanJiLuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShengChanJiLuServiceImpl implements ShengChanJiLuService {

    @Autowired
    private ShengChanJiLuMapper shengChanJiLuMapper;

    public List<ShengChanJiLu> getList() {
        return shengChanJiLuMapper.getList();
    }

    public int addSCJL(ShengChanJiLu shengChanJiLu){
        return shengChanJiLuMapper.addSCJL(shengChanJiLu);
    }
}

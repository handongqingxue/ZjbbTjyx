package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PiCiUServiceImpl implements PiCiUService {

    @Autowired
    private PiCiUMapper piCiUMapper;

    public int add(PiCiU piCiU){
        return piCiUMapper.add(piCiU);
    }

    public List<PiCiU> getList() {
        return piCiUMapper.getList();
    }
}

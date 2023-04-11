package com.uWinOPCTjyx.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

@Service
public class ERecordServiceImpl implements ERecordService {

    @Autowired
    private ERecordMapper eRecordMapper;
}

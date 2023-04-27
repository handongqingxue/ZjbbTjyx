package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreviewPdfJsonServiceImpl implements PreviewPdfJsonService {
	
    @Autowired
    private PreviewPdfJsonMapper previewPdfJsonMapper;

	public int add(PreviewPdfJson prePdfJson) {
		// TODO Auto-generated method stub
		return previewPdfJsonMapper.add(prePdfJson);
	}

	public PreviewPdfJson getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return previewPdfJsonMapper.getByUuid(uuid);
	}

}

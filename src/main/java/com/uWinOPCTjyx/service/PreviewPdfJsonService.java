package com.uWinOPCTjyx.service;

import com.uWinOPCTjyx.entity.*;

public interface PreviewPdfJsonService {

	int add(PreviewPdfJson prePdfJson);

	PreviewPdfJson getByUuid(String uuid);

}

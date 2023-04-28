package com.zjbbTjyx.service;

import com.zjbbTjyx.entity.*;

public interface PreviewPdfJsonService {

	int add(PreviewPdfJson prePdfJson);

	PreviewPdfJson getByUuid(String uuid);

}

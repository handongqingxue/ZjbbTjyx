package com.zjbbTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.zjbbTjyx.entity.*;

public interface PreviewPdfJsonMapper {

	int add(PreviewPdfJson prePdfJson);

	PreviewPdfJson getByUuid(@Param("uuid") String uuid);


}

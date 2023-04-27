package com.uWinOPCTjyx.dao;

import org.apache.ibatis.annotations.Param;

import com.uWinOPCTjyx.entity.*;

public interface PreviewPdfJsonMapper {

	int add(PreviewPdfJson prePdfJson);

	PreviewPdfJson getByUuid(@Param("uuid") String uuid);


}

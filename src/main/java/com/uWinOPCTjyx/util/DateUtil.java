package com.uWinOPCTjyx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String YEAR="yyyy";
	public static final String YEAR_TO_SECOND="yyyy-MM-dd HH:mm:ss";

	public static String getTimeStrByFormatStr(Date date,String format) {
		SimpleDateFormat timeSDF = new SimpleDateFormat(format);
		return timeSDF.format(date);
	}
}

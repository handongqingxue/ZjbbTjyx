package com.uWinOPCTjyx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat yyyySDF = new SimpleDateFormat("yyyy");
	
	public static String getYYYY() {
		return yyyySDF.format(new Date());
	}
}

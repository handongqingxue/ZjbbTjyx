package com.uWinOPCTjyx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final int HAO_MIAO=1;
	public static final int MIAO=2;
	public static final int FEN=3;
	
	public static final String YEAR="yyyy";
	public static final String YEAR_TO_SECOND="yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat timeSDF = new SimpleDateFormat(YEAR_TO_SECOND);

	public static String getTimeStrByFormatStr(Date date,String format) {
		SimpleDateFormat timeSDF = new SimpleDateFormat(format);
		return timeSDF.format(date);
	}
	
	public static Long convertStrToLong(String str) {
		Long time = null;
		try {
			Date date = timeSDF.parse(str);
			time = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return time;
		}
	}
	
	public static long betweenTime(long time1,long time2,int flag) {
		long time = time2-time1;
		switch (flag) {
		case HAO_MIAO:
			
			break;
		case MIAO:
			time=time/1000;
			break;
		case FEN:
			time=time/1000*60;
			break;
		}
		return time;
	}
	
	public static void main(String[] args) {
		Date d1,d2;
		try {
			d1 = timeSDF.parse("1997-07-01 00:00:00");
			d2 = timeSDF.parse("1997-07-01 00:01:00");
			long l1 = d1.getTime();
			long l2 = d2.getTime();
			System.out.println("s==="+(l2-l1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

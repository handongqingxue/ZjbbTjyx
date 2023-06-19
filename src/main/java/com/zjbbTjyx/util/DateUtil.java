package com.zjbbTjyx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final int HAO_MIAO=1;
	public static final int MIAO=2;
	public static final int FEN=3;
	public static final int SHI=4;
	public static final int TIAN=5;
	
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
	
	public static long betweenTime(long preTime,long nxtTime,int flag) {
		long ptnTime = nxtTime-preTime;
		switch (flag) {
		case HAO_MIAO:
			
			break;
		case MIAO:
			ptnTime=ptnTime/1000;
			break;
		case FEN:
			ptnTime=ptnTime/1000/60;
			break;
		case SHI:
			ptnTime=ptnTime/1000/60/60;
			break;
		case TIAN:
			ptnTime=ptnTime/1000/60/60/24;
			break;
		}
		return ptnTime;
	}
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

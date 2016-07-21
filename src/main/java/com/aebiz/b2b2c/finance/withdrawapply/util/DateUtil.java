package com.aebiz.b2b2c.finance.withdrawapply.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	/**
	 * 要用到的DATE Format的定义
	 */
	public static String DATE_FORMAT_DATEONLY = "yyyy-MM-dd"; // 年/月/日
	public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日
	public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond
	public static final long MINUTE_MILLI = 60 * 1000; // 一分钟的MilliSecond
	public static final long HOUR_MILLI = 60 * 60 * 1000; // 一小时的MilliSecond
	public static SimpleDateFormat sdfDateTime = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATETIME);
	public static SimpleDateFormat sdfDateOnly = new SimpleDateFormat(DateUtil.DATE_FORMAT_DATEONLY);
	/**
	 * 取得指定日期所在月的最后一天
	 * 
	 * @param timestamp
	 *            , java.sql.Timestamp Object
	 * @return java.sql.Timestamp instance
	 * @since 1.0
	 * @history
	 */
	public static int[] DAY_OF_MONTH_LEAP_YEAR = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static int[] DAY_OF_MONTH_NON_LEAP_YEAR = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	
	/**
	 * 在java.sql.Timestamp Object上增加/减少几个月
	 * 
	 * @param timestamp
	 *            java.sql.Timestamp instance
	 * @param mon
	 *            增加/减少的月数
	 * @return java.sql.Timestamp Object
	 * @since 1.0
	 * @history
	 */
	public static java.sql.Timestamp addMonths(java.sql.Timestamp timestamp, int mon) {
		java.sql.Timestamp out = null;
		GregorianCalendar obj = DateUtil.convertTimestampToCalendar(timestamp);
		// month : 0 -- 11
		int year = obj.get(GregorianCalendar.YEAR);
		int month = obj.get(GregorianCalendar.MONTH) + 1;
		int day = obj.get(GregorianCalendar.DAY_OF_MONTH);
		month += mon;
		if (month < 1) {
			month += 12;
			year--;
		} else if (month > 12) {
			month -= 12;
			year++;
		}
		if (isLastDayOfMonth(obj)) {
			if (obj.isLeapYear(year)) {
				day = DateUtil.DAY_OF_MONTH_LEAP_YEAR[month - 1];
			} else {
				day = DateUtil.DAY_OF_MONTH_NON_LEAP_YEAR[month - 1];
			}
		}
		String temp = DateUtil.formatYMD(year, month, day);
		out = DateUtil.toSqlTimestamp(temp);
		return out;
	}
	/**
	 * 利用缺省的Date格式(YYYY/MM/DD)转换String到java.sql.Timestamp
	 * 
	 * @param sDate
	 *            Date string
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static java.sql.Timestamp toSqlTimestamp(String sDate) {
		if (sDate == null) {
			return null;
		}
		if (sDate.length() != DateUtil.DATE_FORMAT_DATEONLY.length()) {
			return null;
		}
		return toSqlTimestamp(sDate, DateUtil.DATE_FORMAT_DATEONLY);
	}
	
	/**
	 * Format year/month/day to YYYY/MM/DD format
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return YYYY/MM/DD format String
	 */
	private static String formatYMD(int year, int month, int day) {
		String temp = String.valueOf(year) + "/";
		if (month < 10) {
			temp += "0" + String.valueOf(month) + "/";
		} else {
			temp += String.valueOf(month) + "/";
		}
		if (day < 10) {
			temp += "0" + String.valueOf(day);
		} else {
			temp += String.valueOf(day);
		}
		return temp;
	}
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.GregorianCalendar Object
	 * 
	 * @param timestamp
	 *            , java.sql.Timestamp Object
	 * @return java.util.GregorianCalendar Object
	 * @since 1.0
	 * @history
	 * @deprecated please use confertToCalendar(java.sql.Timestamp)
	 */
	public static java.util.GregorianCalendar convertTimestampToCalendar(java.sql.Timestamp timestamp) {
		return convertToCalendar(timestamp);
	}
	
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.GregorianCalendar Object
	 * 
	 * @param timestamp
	 *            , java.sql.Timestamp Object
	 * @return java.util.GregorianCalendar Object
	 * @since 1.0
	 * @history
	 */
	public static java.util.GregorianCalendar convertToCalendar(java.sql.Timestamp timestamp) {
		GregorianCalendar obj = new GregorianCalendar();
		// Modified by ChenJP 2000/11/17
		obj.setTime(DateUtil.convertTimestampToDate(timestamp));
		// 下面的method不能用，long ==> int 精度不对
		// obj.set(GregorianCalendar.MILLISECOND , (int)timestamp.getTime() );
		return obj;
	}
	
	/**
	 * 把java.sql.Timestamp Object 转换为java.util.Date Object
	 * 
	 * @param timestamp
	 *            , java.sql.Timestamp Object
	 * @return java.util.Date Object
	 * @since 1.0
	 * @history
	 */
	public static java.util.Date convertTimestampToDate(java.sql.Timestamp timestamp) {
		java.util.Date date = null;
		/*
		 * modified by ChenJP 2000/11/17 String temp = null; temp =
		 * timestamp.toString (); temp = temp.substring
		 * (0,DateUtil.DATE_FORMAT_DATETIME.length ()); temp = temp.replace
		 * ('-','/'); try{ date = DateUtil.sdfDateTime.parse (temp);
		 * }catch(Exception ex){ PubLogs.systemLogError(null, ex); date = null; }
		 */
		date = new Date(timestamp.getTime());
		return date;
	}
	
	/**
	 * 判断指定的日期是否是一个月的最后一天
	 * 
	 * @param gcal
	 *            GregorianCalendar object
	 */
	private static boolean isLastDayOfMonth(GregorianCalendar obj) {
		int year = obj.get(GregorianCalendar.YEAR);
		int month = obj.get(GregorianCalendar.MONTH) + 1;
		int day = obj.get(GregorianCalendar.DAY_OF_MONTH);
		if (obj.isLeapYear(year)) {
			if (day == DateUtil.DAY_OF_MONTH_LEAP_YEAR[month - 1]) {
				return true;
			}
		} else {
			if (day == DateUtil.DAY_OF_MONTH_NON_LEAP_YEAR[month - 1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 利用缺省的Date格式(YYYY/MM/DD hh:mm:ss)转化String到java.sql.Timestamp
	 * 
	 * @param sDate
	 *            Date string
	 * @param sFmt
	 *            Date format DATE_FORMAT_DATEONLY/DATE_FORMAT_DATETIME
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static java.sql.Timestamp toSqlTimestamp(String sDate, String sFmt) {
		String temp = null;
		if (sDate == null || sFmt == null) {
			return null;
		}
		if (sDate.length() != sFmt.length()) {
			return null;
		}
		if (sFmt.equals(DateUtil.DATE_FORMAT_DATETIME)) {
			temp = sDate.replace('/', '-');
			temp = temp + ".000000000";
		} else if (sFmt.equals(DateUtil.DATE_FORMAT_DATEONLY)) {
			temp = sDate.replace('/', '-');
			temp = temp + " 00:00:00.000000000";
		} else {
			return null;
		}
		
		// java.sql.Timestamp.value() 要求的格式必须为yyyy-mm-dd hh:mm:ss.fffffffff
		return java.sql.Timestamp.valueOf(temp);
	}
	
	/**
	 * 在java.util.DateObject上增加/减少几分钟
	 * 
	 * @param timestamp
	 *            java.util.Date instance
	 * @param days
	 *            增加/减少的分钟数
	 * @return java.util.Date Object
	 * @since 1.0
	 * @history
	 */
	public static java.util.Date addMinute(java.util.Date date, int minute) {
		long temp = date.getTime();

		return new java.util.Date(temp + DateUtil.MINUTE_MILLI * minute);
	}

	/**
	 * minute 为增加或者减少的分钟
	 */
	public static String addMinute(int minute) {
		return DateUtil.toString(new java.util.Date(System.currentTimeMillis() + minute * DateUtil.MINUTE_MILLI), DateUtil.sdfDateTime);
	}
	
	/***************************************************************************
	 * String ==> java.util.Date 的转换函数
	 **************************************************************************/
	/**
	 * 根据缺省的Format(YYYY/MM/DD)转化java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static String toString(java.util.Date dt) {
		if (dt == null) {
			return "";
		}
		return toString(dt, DateUtil.sdfDateOnly);
	}
	
	/**
	 * 根据指定的Format转化java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @param sFmt
	 *            Date format , DATE_FORMAT_DATEONLY or DATE_FORMAT_DATETIME
	 * @return
	 * @since 1.0
	 * @history
	 */
	public static String toString(java.util.Date dt, String sFmt) {
		if (dt == null) {
			return "";
		}
		if (sFmt.equals(DateUtil.DATE_FORMAT_DATETIME)) { // "YYYY/MM/DD
			// HH24:MI:SS"
			return toString(dt, DateUtil.sdfDateTime);
		} else { // Default , YYYY/MM/DD
			return toString(dt, DateUtil.sdfDateOnly);
		}
	}
	
	/**
	 * 利用指定SimpleDateFormat instance转换java.util.Date到String
	 * 
	 * @param dt
	 *            java.util.Date instance
	 * @param formatter
	 *            SimpleDateFormat Instance
	 * @return
	 * @since 1.0
	 * @history
	 */
	private static String toString(java.util.Date dt, SimpleDateFormat formatter) {
		String sRet = null;

		try {
			sRet = formatter.format(dt).toString();
		} catch (Exception ex) {
			sRet = null;
		}

		return sRet;
	}
	
	/**
	 * 取得两个日期之间的日数
	 * 
	 * @return t1到t2间的日数，如果t2 在 t1之后，返回正数，否则返回负数
	 */
	public static long timesBetween(java.sql.Timestamp t1, java.sql.Timestamp t2,Long type) {
		return (t2.getTime() - t1.getTime()) / type;
	}
	
	/**
	 * 在java.sql.Timestamp Object上增加/减少几天
	 * 
	 * @param timestamp
	 *            java.sql.Timestamp instance
	 * @param days
	 *            增加/减少的天数
	 * @return java.sql.Timestamp Object
	 * @since 1.0
	 * @history
	 */
	public static java.sql.Timestamp addDays(java.sql.Timestamp timestamp, int days) {
		/*
		 * 这种方法不能用，取得的MilliSeconds = 0 GregorianCalendar gcal =
		 * DateUtil.convertTimestampToCalendar (timestamp); long temp =
		 * gcal.get(GregorianCalendar.MILLISECOND );
		 */
		java.util.Date date = DateUtil.convertTimestampToDate(timestamp);
		long temp = date.getTime();

		return new java.sql.Timestamp(temp + DateUtil.DAY_MILLI * days);
	}
	
	/**
	 * 获取两个时间差分钟 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String returnTime(String time1,String time2){
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Date d1 = null;
	       Date d2 = null;
	       long diffMinutes =0;
	       try {
	            d1 = format.parse(time1);
	            d2 = format.parse(time2);
	            //毫秒ms
	            long diff = d2.getTime() - d1.getTime();
	    
	            long diffSeconds = diff / 1000 % 60;
	             diffMinutes = diff / (60 * 1000) % 60;
	            long diffHours = diff / (60 * 60 * 1000) % 24;
	            long diffDays = diff / (24 * 60 * 60 * 1000);
	    
	            System.out.print("两个时间相差：");
	            System.out.print(diffDays + " 天, ");
	            System.out.print(diffHours + " 小时, ");
	            System.out.print(diffMinutes + " 分钟, ");
	            System.out.print(diffSeconds + " 秒.");
	    
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       if(diffMinutes>0){
	    	   return Long.toString(diffMinutes);
	       }
			return "0";
	}
	
}

/*
 版权所有 刘大磊 2013-07-01
 作者：刘大磊
 电话：13336390671
 email:ldlqdsd@126.com
 */

package com.delmar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 刘大磊 2014年12月22日 上午10:59:16
 */
public class DateTimeDecorator {
	private final static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final static DateFormat DATE_HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH");
	private final static DateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyyMM");
	private final static DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final static DateFormat TIME_HOUR_MINUTE = new SimpleDateFormat("HH:mm");
	
    public static String getYear(){
        Date currDate = new Date();
        SimpleDateFormat shortFormatter = new SimpleDateFormat("yyyy");
		return shortFormatter.format(currDate);
    }    
    public static String getHourMinute(Date date)
    {
		return TIME_HOUR_MINUTE.format(date);
    }
    public static String getMonth(){
        Date currDate = new Date();
        SimpleDateFormat shortFormatter = new SimpleDateFormat("MM");
		return shortFormatter.format(currDate);
    } 
    
    public static String getDay(){
        Date currDate = new Date();
        SimpleDateFormat shortFormatter = new SimpleDateFormat("dd");
		return shortFormatter.format(currDate);
    }  
    
    public static int getIntWeek(Date date){
    	int intWeek;
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	intWeek = c.get(Calendar.DAY_OF_WEEK);
    	return intWeek;
    } 
    
    
	public static String getDateMonth(Date date)
	{
		if(date==null)return "";
		else
			return YEAR_MONTH_FORMAT.format(date);
	}
	
    
	public static Date stringToLongDate(String dateString)
	{
		
		if(StringUtil.isEmpty(dateString))return null;
		Date date=null;
		try {
			date=DATE_TIME_FORMAT.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date stringToShortDate(String dateString)
	{
		
		if(StringUtil.isEmpty(dateString))return null;
		Date date=null;
		try {
			date=DATEFORMAT.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	public static String dateToLongString(Date date)
	{
		if(date==null) return "";
		else
			return DATE_TIME_FORMAT.format(date);
	}
	
	
	public static String dateToShortString(Date date)
	{
		if(date==null)return "";
		else
			return DATEFORMAT.format(date); 
	}
	
	public static String dateToShortStringHour(Date date)
	{
		if(date==null)return "";
		else
			return DATE_HOUR_FORMAT.format(date);
	}	
	

	private static String time_format="HH:mm:ss";
	//返回格式为HH:mm:ss的时间字符串
	private static final DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public static String getTimeFormat(Date time)
	{
		return sdf.format(time);
	}
	
	private final static DateFormat DATE_TIME_MICROSECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	/**
	 * 把字符值转换为 日期
	 * @param value
	 * @return
	 */
	public  static Date convertValueToDate(String value)
	{
		try {
			if(StringUtil.isEmpty(value))
			{
				return null;
			}
			return DATE_TIME_MICROSECOND_FORMAT.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	public static String getMicrosecondFormatValue(Date date)
	{
		if(date==null)
		{
			return "";
		}
		return DATE_TIME_MICROSECOND_FORMAT.format(date);
	}
}

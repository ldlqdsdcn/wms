/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

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
	private final static DateFormat DATEHOURFORMAT = new SimpleDateFormat("yyyy-MM-dd HH");	
	private final static DateFormat YEARMONTHFORMAT = new SimpleDateFormat("yyyyMM");
	private final static DateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final static DateFormat TIMEHOURMINUTE = new SimpleDateFormat("HH:mm");
	
    public static String getYear(){
        Date currDate = new Date();
        SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy");
        String dateStr = shortformatter.format(currDate);
        return dateStr;
    }    
    public static String getHourMinute(Date date)
    {
          String dateStr = TIMEHOURMINUTE.format(date);
          return dateStr;
    }
    public static String getMonth(){
        Date currDate = new Date();
        SimpleDateFormat shortformatter = new SimpleDateFormat("MM");
        String dateStr = shortformatter.format(currDate);
        return dateStr;
    } 
    
    public static String getDay(){
        Date currDate = new Date();
        SimpleDateFormat shortformatter = new SimpleDateFormat("dd");
        String dateStr = shortformatter.format(currDate);
        return dateStr;
    }  
    
    public static int getIntWeek(Date date){
    	int intWeek = 0;
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	intWeek = c.get(Calendar.DAY_OF_WEEK);
    	return intWeek;
    } 
    
    
	public static String getDateMonth(Date date)
	{
		if(date==null)return "";
		else
			return YEARMONTHFORMAT.format(date); 
	}
	
    
	public static Date stringToLongDate(String dateString)
	{
		
		if(StringUtil.isEmpty(dateString))return null;
		Date date=null;
		try {
			date=DATETIMEFORMAT.parse(dateString);
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
			return DATETIMEFORMAT.format(date);
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
			return DATEHOURFORMAT.format(date); 
	}	
	

	private static String time_format="HH:mm:ss";
	//返回格式为HH:mm:ss的时间字符串
	private static DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public static String getTimeFormat(Date time)
	{
		return sdf.format(time);
	}
	
	private final static DateFormat DATETIMEFORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	public final static Date converValueToDate(String value)
	{
		try {
			if(StringUtil.isEmpty(value))
			{
				return null;
			}
			Date date=DATETIMEFORMAT2.parse(value);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	public final static String getFormatvalue2(Date date)
	{
		if(date==null)
		{
			return "";
		}
		return DATETIMEFORMAT2.format(date);
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		System.out.println(DATETIMEFORMAT2.format(new Date()));
	}
}

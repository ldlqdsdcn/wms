/******************************************************************************
 * 德玛国际物流有限公司  2015-04-01												      *
 *	作者：Charles Luo							                                      *
 * 电话： 
 * email:
 *****************************************************************************/

package com.delmar.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProDateUtil {
	
	
	public static String getCurrentSysShortDateStr()
	{
		return getShortHisDateStr(0);
	}
	
	/**
	 * 获得历史多少天之前的这一天的日期,包括时间
	 * 
	 * @param his
	 */
	public static Date getHisDate(int his) {
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.add(GregorianCalendar.DATE, -1 * his);// 今天前的**天
		return thisday.getTime();
	}
	
	/**
	 * 获得历史多少天之前的这一天的日期，包括时间
	 * 
	 * @返回字符类型
	 */
	
	public static String getLongHisDateStr(int his) {
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.add(GregorianCalendar.DATE, -1 * his);// 今天前的**天
		SimpleDateFormat shortformatter = new SimpleDateFormat(
				"yyyy-MM-dd kk:mm:ss");
		String dateStr = shortformatter.format(thisday.getTime());
		return dateStr;
	}
	
	public static String getShortHisDateStr(int his) {
		Date hisDate = getHisDate(his);
		SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = shortformatter.format(hisDate);
		return dateStr;
	}	
	
	
	/**
	 * 获得历史多少天之前的这一天的日期
	 * 
	 * @param his
	 */
	public static Date getHisDate(Date start, int his) {
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.setTime(start);
		thisday.add(GregorianCalendar.DATE, -1 * his);// 今天前的**天
		return thisday.getTime();
	}
	
	/**
	 * 获得历史多少天之前的这一天的日期,包含时间
	 * 
	 * @param his
	 */
	public static String getLongHisDateStr(Date start, int his) {
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.setTime(start);
		thisday.add(GregorianCalendar.DATE, -1 * his);// 今天前的**天
		SimpleDateFormat shortformatter = new SimpleDateFormat(
				"yyyy-MM-dd kk:mm:ss");
		String dateStr = shortformatter.format(thisday.getTime());
		return dateStr;
	}

	public static String getShortHisDateStr(Date start, int his) {
		Date hisDate = getHisDate(start, his);
		SimpleDateFormat shortformatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = shortformatter.format(hisDate);
		return dateStr;
	}
	
	/***************************************************************************
	 * 得到两个日期之间的差值 返回：天，小时，分钟
	 **************************************************************************/
	public static long getDateDiffMinute(String begindate, String enddate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		ParsePosition pos1 = new ParsePosition(0);
		ParsePosition pos2 = new ParsePosition(0);
		if (begindate.length() == 10)
			begindate = begindate + " 00:00:00";
		if (enddate.length() == 10)
			enddate = enddate + " 00:00:00";
		Date BeginDate = formatter.parse(begindate, pos1);
		Date EndDate = formatter.parse(enddate, pos2);
		long dateDiff = EndDate.getTime() - BeginDate.getTime();
		Float minuteDiff = new Float(dateDiff / 1000 / 60);
		return minuteDiff.intValue();
	}

	public static long getDateDiffHour(String begindate, String enddate) {
		long minuteDiff = getDateDiffMinute(begindate, enddate);
		Float hourDiff = new Float(minuteDiff / 60);
		return hourDiff.intValue();
	}

	public static long getDateDiffDay(String begindate, String enddate) {
		long hourDiff = getDateDiffHour(begindate, enddate);
		Float dayDiff = new Float(hourDiff / 24);
		return dayDiff.intValue();
	}
	
	public static long getDateDiffDay(String begindate) {
		GregorianCalendar thisday = new GregorianCalendar();
		SimpleDateFormat shortformatter = new SimpleDateFormat(
				"yyyy-MM-dd");
		String thisdateStr = shortformatter.format(thisday.getTime());
		
		return getDateDiffDay(begindate, thisdateStr);
	}	
	
	
	/**
	 * 获得历史多少天之后的这一天的日期,包括时间
	 * 
	 * @param his
	 *            hisType加入的时间类型，0天，1小时，2分
	 */
	public static Date getAfterDate(int his, int hisType) {
		GregorianCalendar thisday = new GregorianCalendar();
		if (hisType == 0)
			thisday.add(GregorianCalendar.DATE, his);// 今天后的**天
		if (hisType == 1)
			thisday.add(GregorianCalendar.HOUR, his);// 今天后的**天
		if (hisType == 2)
			thisday.add(GregorianCalendar.MINUTE, his);// 今天后的**天
		return thisday.getTime();
	}
	
	/**
	 * 获得历史多少天之后的这一天的日期,包括时间
	 * 
	 * @param his
	 *            hisType加入的时间类型，0天，1小时，2分,3月，4年
	 */
	public static Date getAfterDate(Date start, int his, int hisType) {
		
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.setTime(start);
		if (hisType == 0)
			thisday.add(GregorianCalendar.DATE, his);// 今天后的**天
		else if (hisType == 1)
			thisday.add(GregorianCalendar.HOUR, his);// 今天后的**小时
		else if (hisType == 2)
			thisday.add(GregorianCalendar.MINUTE, his);// 今天后的**分
		else if (hisType == 3)
			thisday.add(GregorianCalendar.MONTH, his);// 今天后的**月
		else if (hisType == 4)
			thisday.add(GregorianCalendar.YEAR, his);// 今天后的**年

		return thisday.getTime();
	}
	
	/**
	 * 获得历史多少天之后的这一天的日期,包括时间
	 * 
	 * @param his
	 *            hisType加入的时间类型，0天，1小时，2分,3月，4年
	 */
	public static String getAfterDateStr(Date start, int his, int hisType) {
		return new SimpleDateFormat("yyyy-MM-dd").format(getAfterDate(start,his,hisType));
	}	
	
	
	/***
	 * 计算两个日期之间的工作日数,（星期6，星期天，不算工作日）dt1和dt2之间相隔多少工作日
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int DifferWorkDate(String dt1, String dt2) {
		int intReturn = 0;// 返回值，即dt2和dt1之间的工作日数
		long diffDay = getDateDiffDay(dt1, dt2);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date current = formatter.parse(dt1, pos);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(current);

		for (int i = 0; i <= diffDay; i++)// 从dt1开始一天天加，判断临时的日期值是不是星期六或星期天，如果既不是星期六，也不是星期天，则该天为工作日，intReturn加1
		{

			int tt = c.get(GregorianCalendar.DAY_OF_WEEK);
			if (!((tt == 1) || (tt == 7)))
				intReturn++;

			c.add(GregorianCalendar.DATE, 1);
		}
		return intReturn;

	}
	
	//yyyy-MM
	public static String getHisMonth(String currentMonth,int hisNumber)
	{
		//得到年月
		String iyear=currentMonth.substring(0,4);
		String imonth=currentMonth.substring(5,7);
	
		if (Integer.parseInt(imonth)<=hisNumber)
		{
			iyear=String.valueOf(Integer.parseInt(iyear)-1);
			imonth=String.valueOf(12-hisNumber+Integer.parseInt(imonth));
			if (imonth.length()==1)
				imonth="0"+imonth;
			
			return iyear+"-"+imonth;
		}
		else
		{
			imonth=String.valueOf(Integer.parseInt(imonth)-hisNumber);
			if (imonth.length()==1)
				imonth="0"+imonth;
			
			return iyear+"-"+imonth;
			
		}
		
		
		
	}
	
	public static boolean isDate(String dateStr)
	{
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		try
		{
	    	Date current = formatter.parse(dateStr, pos);
	    	return true;
		} catch (Exception e)
		{
			return false;
		}
	}
	
	
	public static String getShortDateStr(Date sDate)
	{
		if (sDate==null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(sDate);
	}
	
	public static String getLongDateStr(Date sDate)
	{
		if (sDate==null)
			return "";		
		return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(sDate);
	}	
	
	public static void main(String args[] )
	{
		System.out.println(getHisMonth("2015-04",12));
	}

	

	
	

}

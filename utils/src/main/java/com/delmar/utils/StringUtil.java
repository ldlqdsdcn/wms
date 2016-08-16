/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘大磊 2014年12月20日 下午9:24:37
 */
public class StringUtil {
	/**
	 * 判断字符串是否为�?""
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value)
	{
		if(value==null) return true;
		if(value.trim().length()==0)return true;
		return false;
	}
	/**
	 * 非空判断
	 * @param dateString
	 * @return
	 */
	public static boolean isNotEmpty(String value)
	{
		return !isEmpty(value);
	}
	
	/** 
	 * @Title:        fullYhStr 
	 * @Description:  单引号变为双引号
	 * @param:        @param value
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月1日 上午11:49:59 
	 */
	public static String fullYhStr(String value)
	{
		return value.replaceAll("'", "''");
	}
	
	
	public static boolean isInteger(String value)
	{
		 Pattern pattern = Pattern.compile("[0-9]*"); 
		 Matcher isNum = pattern.matcher(value);
		 if( !isNum.matches() ){
		      return false; 
		  } 
		   return true; 
	}
	
	
	public static String convertToSqlParam(String value)
	{
		value=value.replaceAll("'", "''");
		value=value.replaceAll(",", "','");
		value="'"+value+"'";
		
		return value;
		
	}
	
	
    public static boolean isEmail(String email){  
        if (null==email || "".equals(email)) return false;    
//      Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email);  
        return m.matches();  
       }  	
}

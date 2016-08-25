/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.utils;


import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

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
	 * @param value
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

	/**
	 * 对象属性转换为字段  例如：userName to user_name
	 * @param property 字段名
	 * @return
	 */
	public static String propertyToField(String property) {
		if (null == property) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char c : chars) {
			if (CharUtils.isAsciiAlphaUpper(c)) {
				sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 字段转换成对象属性 例如：user_name to userName
	 * @param field
	 * @return
	 */
	public static String fieldToProperty(String field) {
		if (null == field) {
			return "";
		}
		char[] chars = field.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				int j = i + 1;
				if (j < chars.length) {
					sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	public static String lowerFirstChar(String name) {
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return  name;

	}
}

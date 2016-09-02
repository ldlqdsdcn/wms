/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

/**
 * @author 刘大磊 2015年1月12日 下午2:03:01
 */
public class EaContext {
	/**
	 * ServletContext;
	 **/
	public static ServletContext ServletContext;
	/**
	 * SpringContext
	 **/
	public static ApplicationContext ApplicationContext;
	/**
	 * 系统跟目录
	 **/
	public static String CONTEXTPATH;
	
	public static final  String TRUE="Y";
	
	public static final String FALSE="N";
	
	public  static <T> T getBean(String name, Class<T> requiredType) 
	{
		return ApplicationContext.getBean(name, requiredType);
	}

}

/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author 刘大磊 2015年1月12日 下午2:03:01
 */
public final class SystemContextHelper {
	private static SystemContextHelper SYSTEM_CONTEXT_HELPER;
	/**
	 * ServletContext;
	 **/
	private final ServletContext servletContext;
	/**
	 * SpringContext
	 **/
	private  final ApplicationContext applicationContext;
	/**
	 * 系统跟目录
	 **/
	private final String contextPath;

	public static final  String TRUE="Y";
	
	public static final String FALSE="N";
	public static synchronized SystemContextHelper init(ServletContext servletContext)
	{
			if(SYSTEM_CONTEXT_HELPER!=null)
			{
				throw new RuntimeException("EaContext 已经实例化");
			}
		ApplicationContext applicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		String contextPath=servletContext.getContextPath();

		SYSTEM_CONTEXT_HELPER=new SystemContextHelper(servletContext,applicationContext,contextPath);
		return SYSTEM_CONTEXT_HELPER;
	}
	private SystemContextHelper(ServletContext servletContext,ApplicationContext applicationContext,String contextPath)
	{
		this.servletContext=servletContext;
		this.applicationContext=applicationContext;
		this.contextPath=contextPath;
	}

	public  static <T> T getBean(String name, Class<T> requiredType) 
	{
		return SYSTEM_CONTEXT_HELPER.applicationContext.getBean(name, requiredType);
	}
	public  static <T> T getBean(Class<T> requiredType)
	{
		return SYSTEM_CONTEXT_HELPER.applicationContext.getBean(requiredType);
	}

}

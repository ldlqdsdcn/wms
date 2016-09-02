/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.content;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月9日 上午9:08:20
 */
public class SessionContent {
	private static Map<Long,String> sessionMap= new Hashtable<>();
	public static  void putUser(Long threadId,String username)
	{
		//Thread.currentThread().getId();
		sessionMap.put(threadId, username);
	}
	public static String getUserId(Long threadId)
	{
		return sessionMap.get(threadId);
	}
	/**
	 * 日志 操作类型
	 */
	public static final String OPERATE_INSERT="I";
	public static final String OPERATE_UPDATE="U";
	public static final String OPERATE_DELETE="D";
	
	/**
	 * 用户全局session
	 */
	public final static String LOGIN_USER_SESSION="loginUser";
}

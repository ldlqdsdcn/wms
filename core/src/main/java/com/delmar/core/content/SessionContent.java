/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.content;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月9日 上午9:08:20
 */
public class SessionContent {
	private static Map<Long,String> sessionMap=new Hashtable<Long,String>(); 
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
	 * 日志 数据类型
	 */
	public static final int TYPE_NUMBER=1;
	public static final int TYPE_CHAR=2;
	public static final int TYPE_DATE=3;
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

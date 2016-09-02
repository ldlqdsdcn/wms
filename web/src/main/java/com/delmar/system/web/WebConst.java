/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web;

/**
 * @author 刘大磊 2015年1月23日 上午10:56:52
 * session 常量
 */
public class WebConst {
	public static final String SESSION_LOGINUSER="loginUser";
	public static final String SESSION_ACTUALUSER="actualUser";
	public static final String SESSION_USERPRIVILEGES="userPrivileges";
	public static final String SESSION_USERCONTENT="userContent";
	public static final String SESSION_SAMEUSER="sameUser";
	//也是采用MAP的形式存储，不过VALUE为关联第三方的Code 字符串
	public static final String SESSION_THIRDPARTYUSERMAP="userthirdpartyUserMap";
	//也是采用MAP的形式存储，不过VALUE为用户的LIST
	public static final String SESSION_THIRDPARTYUSER="userthirdpartyUser";

	public static final String SESSION_SEARCH_CONDITIONS="searchConditions";
	
	
}

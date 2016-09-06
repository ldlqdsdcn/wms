/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/
package com.delmar.sys.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 刘大磊 2015年1月13日 下午4:38:12
 */
public class UserContent implements java.io.Serializable{
	public Map<String,List<Page>> PAGEPRIVILEGES=new HashMap<String,List<Page>>();

	public Map<String,List<Javabean>> JAVABEANPRIVILEGES=new HashMap<String,List<Javabean>>();
	
	public UserSession userSession;
	public UserSession preSession;
	/**
	 * token 串
	 */
	public String token;
}

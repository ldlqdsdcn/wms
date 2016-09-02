/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.delmar.sys.model.UserSession;

/**
 * @author 刘大磊 2015年1月12日 下午2:27:14
 * 	在线用户存放
 */
public class UserOnlineContent {
//	private static Map<Integer,UserSession> USERMAP=new Hashtable<Integer,UserSession>();
	private static Map<Integer,UserSession> WEBUSERMAP=new Hashtable<Integer,UserSession>();
	/**
	 * 获得在线人数
	 * @return
	 */
	public static int getOnlineCount()
	{
		return WEBUSERMAP.keySet().size();
	}
	public static Collection<UserSession> getUsersessionCollection()
	{
		return WEBUSERMAP.values();
	}
	public static UserSession getUserBySessionId(String sessionId)
	{
		Collection<UserSession> userSessionCollection=WEBUSERMAP.values();
		for(UserSession us:userSessionCollection)
		{
			if(us!=null&&sessionId.equals(us.getSessionId()))
			{
				return us;
			}
		}
		return null;
	}
	/**
	 * 获得在线用户
	 */
	public static List<UserSession> getUserList()
	{
		List<UserSession> list=new ArrayList<UserSession>();
		Collection<UserSession> userList=WEBUSERMAP.values();
		java.util.Iterator<UserSession> iterator=userList.iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next());
		}
		
		return list;
	}
	//判断用户是否在线
	public static boolean isonline(Integer userId)
	{
		Collection<UserSession> userList=WEBUSERMAP.values();
		java.util.Iterator<UserSession> iterator=userList.iterator();
		while(iterator.hasNext())
		{
			if(userId.equals(iterator.next().getUserId()))
			{
				return true;
			}
		}
		return false;
	}
	public static UserSession getOnlineUserById(Integer userId)
	{
		Collection<UserSession> userList=WEBUSERMAP.values();
		java.util.Iterator<UserSession> iterator=userList.iterator();
		UserSession us=null;
		while(iterator.hasNext())
		{
			
			us=iterator.next();
			if(userId.equals(us.getUserId()))
			{
				return us;
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.println(WEBUSERMAP.size());
	}
	public static void addUser(UserSession user)
	{
		WEBUSERMAP.put(user.getUserId(), user);
	}
	public static void addWebUser(UserSession user)
	{
		WEBUSERMAP.put(user.getUserId(), user);
	}
	public static void removeWebUser(UserSession user)
	{
		if(user!=null)
		{
			WEBUSERMAP.remove(user.getUserId());
			//WEBUSERMAP.remove(user.getUserId());
		}
	}




}

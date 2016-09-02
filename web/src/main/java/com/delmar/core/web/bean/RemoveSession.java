/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import javax.servlet.http.HttpSession;

import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2015年1月12日 下午1:13:35
 */
public class RemoveSession {
	public static void removeUserSession(HttpSession session)
	{
		User user=(User)session.getAttribute("loginUser");
		/*Date now=new Date();
		if(user!=null)
		{
			UserContent userContent=(UserContent)session.getAttribute("userContent");
			//System.out.println("删除用户");
			UserOnlineContent.removeWebUser(userContent.userSession);
			UserSessionService userSessionService=(UserSessionService)Tools.findBean("userSessionService");
			userContent.userSession.setLogoutDate(now);
			userContent.userSession.setDateMt(now);
			userSessionService.save(userContent.userSession);
			
		}*/
		
		session.removeAttribute("loginUser");
		session.removeAttribute("userContent");
	}
}

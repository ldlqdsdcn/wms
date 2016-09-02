/******************************************************************************
、 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.delmar.core.web.bean.UserOnlineContent;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;

/**
 * @author 刘大磊 2015年1月12日 下午2:29:36
 */
public class OnLineUserListener implements HttpSessionListener{
public void sessionCreated(HttpSessionEvent arg0) {
		
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		Date now=new Date();
		HttpSession session=arg0.getSession();
		
		User user=(User)session.getAttribute("loginUser");
		
		if(user!=null)
		{
			UserContent userContent=(UserContent)session.getAttribute("userContent");
			//System.out.println("删除用户");
		/*	UserOnlineContent.removeUser(userContent.userSession);
			UserSessionService userSessionService=(UserSessionService)Tools.findBean("userSessionService");
			userContent.userSession.setLogoutDate(now);
			userContent.userSession.setDateMt(now);*/
			//userSessionService.save(userContent.userSession);
			session.removeAttribute("loginUser");
			session.removeAttribute("userContent");
		}
	}
}

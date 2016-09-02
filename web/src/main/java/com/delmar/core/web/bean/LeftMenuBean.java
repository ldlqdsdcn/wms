/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import com.delmar.sys.model.User;
import com.delmar.sys.service.PageMenuService;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * @author 刘大磊 2015年1月13日 上午10:15:56
 */
public class LeftMenuBean {
	private static PageMenuService pageMenuService;
	public static String getLeftMenu(HttpSession session)
	{
		I18nInterceptor s=null;
	User user=(User)session.getAttribute("loginUser");
	Locale local=(Locale)	session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");

		if(pageMenuService==null)
		{
			pageMenuService=(PageMenuService)EaContext.ApplicationContext.getBean("pageMenuService");
		}
		
		return pageMenuService.getLeftMenus(user.getId(),local);
	}
}

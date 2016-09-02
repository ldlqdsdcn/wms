/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.dwr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.delmar.sys.SystemConst;
import com.delmar.sys.model.Javabean;
import com.delmar.sys.model.UserContent;
import com.delmar.system.web.WebConst;

/**
 * @author 刘大磊 2015年1月23日 下午2:13:17
 */
public class DwrPrivilegeFilter {
	public static boolean isView(String classname)
	{
		
		
		List<Javabean> urls=getUserContent().JAVABEANPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);

		for(Javabean javabean:urls)
		{
			if(classname.startsWith(javabean.getClassname()))return true;
		}
		return false;
	}
	public static boolean isCreate(String classname)
	{
		List<Javabean> urls=getUserContent().JAVABEANPRIVILEGES.get(SystemConst.PRIVILEGES_ADD);

		for(Javabean url:urls)
		{
			if(classname.startsWith(url.getClassname()))return true;
		}
		return false;
	}
	public static boolean isUpdate(String classname)
	{
		//List<String> urls=getUserContent().updates;
		List<Javabean> urls=getUserContent().JAVABEANPRIVILEGES.get(SystemConst.PRIVILEGES_UPDATE);

		for(Javabean url:urls)
		{
			if(classname.startsWith(url.getClassname()))return true;
		}
		return false;
	}
	public static boolean isDelete(String classname)
	{
		List<Javabean> urls=getUserContent().JAVABEANPRIVILEGES.get(SystemConst.PRIVILEGES_DELETE);
		for(Javabean url:urls)
		{
			if(classname.startsWith(url.getClassname()))return true;
		}
		return false;
	}
	public static boolean isApprove()
	{
		return false;
	}
	public static UserContent getUserContent()
	{
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		return (UserContent)request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
	}


}

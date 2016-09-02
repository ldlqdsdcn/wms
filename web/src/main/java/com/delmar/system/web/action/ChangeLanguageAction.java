/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.bean.UserResource;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 刘大磊 2015年2月3日 上午8:51:10
 */
public class ChangeLanguageAction extends CoreAction {
	public String changeLanguage()
	{
		ServletActionContext.getRequest().getSession().setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", getLocale());
		//ServletActionContext.getRequest().getSession().setAttribute("WW_TRANS_I18N_LOCALE", getLocale());
		//	com.opensymphony.xwork2.ActionContext.getContext().setLocale((Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
		/*System.out.println("~~~~~~~~~~~~~~~~~~~~~"+(Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
		session.setAttribute("WW_TRANS_I18N_LOCALE", (Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));*/
		ServletActionContext.getRequest().getSession().setAttribute("WW_TRANS_I18N_LOCALE", Locale.US.toString());
		UserResource ur=new UserResource(getLocale());
		ServletActionContext.getRequest().getSession().setAttribute("resource", ur);
		Map session = ActionContext.getContext().getSession();
		Object obj = session.get("lang");
		if (obj != null) {
			String lang = obj.toString();
			if (lang.equals(Locale.CHINESE.toString())) {
				ActionContext.getContext().setLocale(Locale.CHINA);
				ServletActionContext.getRequest().getSession().setAttribute("current.lang.choose",Locale.CHINA);
				
			} else if (lang.equals(Locale.ENGLISH.toString())) {
				ActionContext.getContext().setLocale(Locale.US);
				ServletActionContext.getRequest().getSession().setAttribute("current.lang.choose",Locale.US);
					
			}
		} else {
			String local = ActionContext.getContext().getLocale().getLanguage();
			session.put("lang", local);
			ServletActionContext.getRequest().getSession().setAttribute("current.lang.choose",local);
						
		}
		return INPUT;
	}
}

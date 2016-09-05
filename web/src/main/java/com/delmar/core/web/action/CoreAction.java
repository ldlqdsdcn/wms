/*
 * 版权所有 刘大磊 2013-07-01
 *	作者：刘大磊
 * 电话：13336390671
 * email:ldlqdsd@126.com
 **/
package com.delmar.core.web.action;

import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.DmLog;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * @author 刘大磊 2015年2月9日 下午4:22:17
 */
public class CoreAction extends ActionSupport{
	protected static final String LIST="list";
	public static final String NO_PRIVILEGE="nopriv";
	
    /** the logger instance */
    protected DmLog logger =DmLog.getLogger("InfoLogger."+getClass().getName()); 	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#getText(java.lang.String)
	 */
	@Override
	public String getText(String aTextName) {
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		String resource=ur.get(aTextName);
		 if(resource!=null)
	   	   return resource;
		 
		return super.getText(aTextName);
	}
	
	
	/** 
	   获取当前的User
	 * Description:  得到当前的用户的ID, 不用每一次都重写一遍
	 * @return      返回当前用户的userId
	 * author      Charles Luo
	 * @serialData 2015年7月23日 下午2:59:04
	 */
	public Integer getCurrentUser()
	{
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		return up.getUserId();
	}

}

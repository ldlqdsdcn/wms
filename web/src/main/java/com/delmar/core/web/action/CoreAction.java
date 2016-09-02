/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.DmLog;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 刘大磊 2015年2月9日 下午4:22:17
 */
public class CoreAction extends ActionSupport{
	protected static final String LIST="list";
	public static final String NOPRIVILEGE="nopriv";
	
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
	 * @Title:        getCurrentUser 
	 * @Description:  得到当前的用户的ID, 不用每一次都重写一遍
	 * @param:        @return    
	 * @return:       Integer    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月23日 下午2:59:04 
	 */
	public Integer getCurrentUser()
	{
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		return up.getUserId();
	}
	
	
	/** 
	 * @Title:        getCurrentUserThird 
	 * @Description:  得到和此用户关联的第三方的账户
	 * @param:        @param thirdKey 采用system_id以及partytype_id 对应的Value联合作为主键  
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月23日 下午2:59:40 
	 */
	public String getCurrentUserThird(String thirdKey)
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();		
		HashMap<String,String> thirdPartyMap=(HashMap<String,String>)session.getAttribute(WebConst.SESSION_THIRDPARTYUSERMAP);
		if (thirdPartyMap!=null)
		{
			if (thirdPartyMap.containsKey(thirdKey))
				return thirdPartyMap.get(thirdKey);
			else
				return "";
		}
		else
			return "";
	}	
}

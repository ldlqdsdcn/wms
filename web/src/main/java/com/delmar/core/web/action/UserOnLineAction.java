/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.delmar.core.web.bean.UserOnlineContent;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserSession;

/**
 * @author 刘大磊 2015年1月16日 下午4:24:41
 * 在线用户
 */
public class UserOnLineAction extends CoreAction{
	private int onlinecount;

	public String searchList()  {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		
		
		List<UserSession> list=UserOnlineContent.getUserList();
		
		
		onlinecount=list.size();
		
		
		FacesUtils.setValueInHashtableOfSession("userOnlines", list);
		FacesUtils.setValueInHashtableOfSession("onlinecount", onlinecount);
	
		
		
		return "success";
		
	}
}

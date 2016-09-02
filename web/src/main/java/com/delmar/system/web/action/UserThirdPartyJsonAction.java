/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.google.gson.Gson;


/**
 *@ClassName:   UserThirdPartyJsonAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年8月28日 上午10:51:59
 * @version V2.0
 */
public class UserThirdPartyJsonAction extends CoreAction {
	
	@Autowired
	private UserThirdPartyService userThirdPartyService;
	
	protected PrivilegesDataFilter up;
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "用户第三方系统关联");
	}

	
	private void init()
	{
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
	}
	

	public void delete() {
		HttpServletRequest request=ServletActionContext.getRequest();		
		String thirdpartyId=request.getParameter("id");
		try{		
		userThirdPartyService.deleteByPrimaryKey(new Integer(thirdpartyId));
	    HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
  
        response.getWriter().write("success");
        } catch (Exception ex)
        {
        	
        	
        }
        

	}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */

	public void edit() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		init();
		try{	
			
			String thirdpartyId=request.getParameter("id");			
			UserThirdParty userThirdParty= userThirdPartyService.selectByPrimaryKey(new Integer(thirdpartyId));
	        HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");   
            
            
            Gson gson=new Gson();
            
           response.getWriter().write(gson.toJson(userThirdParty));
        } catch (Exception ex)
        {
        	
        	
        }
		
		 

	}

	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */

	public void save() {
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		init();
       try {
    	Gson gson=new Gson();
    	
    	String data = request.getParameter("data");
		if (data != null)
			data = java.net.URLDecoder.decode(data, "UTF-8");

		UserThirdParty userThirdParty= gson.fromJson(data, UserThirdParty.class);
		Date now=new Date();			
		if (userThirdParty.getId().intValue()==0)
		{
		
			userThirdParty.setCreatedBy(up.getLoginUserId());
			userThirdParty.setCreatedByName(up.getLoginUser().getName());
			userThirdParty.setUpdatedBy(up.getLoginUserId());
			userThirdParty.setUpdatedByName(up.getLoginUser().getName());
			userThirdParty.setCreated(now);
			userThirdParty.setUpdated(now);			
		} else
		{
			userThirdParty.setUpdatedBy(up.getLoginUserId());
			userThirdParty.setUpdatedByName(up.getLoginUser().getName());
			userThirdParty.setUpdated(now);					
		}
    	   
		Integer thirdId=userThirdPartyService.save(userThirdParty);
		userThirdParty.setId(thirdId);
		
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
        
        
         
        
         response.getWriter().write(gson.toJson(userThirdParty));		
       } catch (Exception ex)
       {
       	
       	
       }
		
	}


}

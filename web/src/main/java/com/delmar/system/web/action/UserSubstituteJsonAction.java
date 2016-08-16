
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserSubstitute;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UserSubstituteService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;



/**
 *@ClassName:   UserSubstituteJsonAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年7月7日 上午11:30:46
 * @version V2.0
 */
public class UserSubstituteJsonAction extends CoreAction {
	
	@Autowired
	private UserSubstituteService userSubstituteService;
	@Autowired
	private UserService userService;	
	
	protected PrivilegesDataFilter up;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "转换成为别人");
	}
	
	private void init()
	{
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
	}
	

	public String listadd()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		HttpServletRequest request=ServletActionContext.getRequest();
        String id=request.getParameter("id");
        
        List<User> nonUserList=userService.getNonUserSubstituteById(new Integer(id));
        
		FacesUtils.setValueInHashtableOfSession("nonUserList", nonUserList);        
		return "adduser";
	}
	
	public String listsub()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		
		User loginuser=(User)request.getSession().getAttribute(WebConst.SESSION_LOGINUSER);
        
        List<User> userList=userService.getUserSubstituteById(loginuser.getId());
        
		FacesUtils.setValueInHashtableOfSession("userList", userList);   
		
		return "listuser";
		
	}
	
	public void delete() {

		HttpServletRequest request=ServletActionContext.getRequest();		
		String userId=request.getParameter("id");
		String suserId=request.getParameter("sid");
		try{
		Map<String,Integer> param=new HashMap<String,Integer>();
		
		param.put("userid",new Integer(userId));
		param.put("suserid",new Integer(suserId));
		
		userSubstituteService.deleteByExample(param);

	    HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
  
        response.getWriter().write("success");
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
    	   
   		String id=request.getParameter("id");
   		String userIds=request.getParameter("userIds");		
   		String[] userIdArray=userIds.split(",");		
   		for (String oneUserId:userIdArray)
   		{
   		   UserSubstitute userSubstitute=new UserSubstitute();
   		   userSubstitute.setUserId(new Integer(id));
   		   userSubstitute.setSuserId(new Integer(oneUserId));
   	       userSubstituteService.save(userSubstitute);
   		}
   		
    
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
  	    response.getWriter().write("Success");	
       } catch (Exception ex)
       {
       	
       	
       }
		
	}


}

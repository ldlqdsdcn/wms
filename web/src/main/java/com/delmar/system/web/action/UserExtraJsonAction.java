package com.delmar.system.web.action;

import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserExtra;
import com.delmar.sys.service.UserExtraService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.google.gson.Gson;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月28日 上午10:51:13 
 * 类说明 
 */
public class UserExtraJsonAction  extends CoreAction {

	@Autowired
	private UserExtraService userExtraService;
	
	protected PrivilegesDataFilter up;
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "组织附加信息维护");
	}

	
	private void init()
	{
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
	}
	

	public void delete() {
		HttpServletRequest request=ServletActionContext.getRequest();		
		String extraId=request.getParameter("id");
		try{		
			userExtraService.deleteByPrimaryKey(new Integer(extraId));
	    HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
  
        response.getWriter().write("success");
        } catch (Exception ex)
        {

			throw new RuntimeException(ex);
        }
        

	}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */

	public void edit() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		init();
		try{	
			
			String extraId=request.getParameter("id");			
			UserExtra userExtra= userExtraService.selectByPrimaryKey(new Integer(extraId));
	        HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");   
            
            
            Gson gson=new Gson();
            
           response.getWriter().write(gson.toJson(userExtra));
        } catch (Exception ex)
        {
			throw new RuntimeException(ex);
        	
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

		UserExtra userExtra= gson.fromJson(data, UserExtra.class);
    	   
		Integer extraId=userExtraService.save(userExtra);
		userExtra.setId(extraId);
		
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
        
        
         
        
         response.getWriter().write(gson.toJson(userExtra));		
       } catch (Exception ex)
       {

		   throw new RuntimeException(ex);
       }
		
	}
}



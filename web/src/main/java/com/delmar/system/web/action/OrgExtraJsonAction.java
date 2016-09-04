package com.delmar.system.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.OrgExtra;
import com.delmar.sys.service.OrgExtraService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.google.gson.Gson;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月28日 上午10:51:28 
 * 类说明 
 */
public class OrgExtraJsonAction extends CoreAction {

	@Autowired
	private OrgExtraService orgExtraService;
	
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
		orgExtraService.deleteByPrimaryKey(new Integer(extraId));
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
			OrgExtra orgExtra= orgExtraService.selectByPrimaryKey(new Integer(extraId));
	        HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");   
            
            
            Gson gson=new Gson();
            
           response.getWriter().write(gson.toJson(orgExtra));
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

		OrgExtra orgExtra= gson.fromJson(data, OrgExtra.class);

		Integer extraId=orgExtraService.save(orgExtra);
		orgExtra.setId(extraId);
		
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   
        
        
         
        
         response.getWriter().write(gson.toJson(orgExtra));		
       } catch (Exception ex)
       {

		   throw new RuntimeException(ex);
       }
		
	}
}



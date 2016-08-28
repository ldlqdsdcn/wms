package com.delmar.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.portable.ApplicationException;

import com.delmar.core.web.util.FacesUtils;
import com.delmar.utils.DmLog;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月25日 下午4:39:11 
 * 类说明 
 */
public class DMExceptionInterceptor implements Interceptor  {

	public void destroy()
	{}
	
	public void init()
	{}
	
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		
		 String result = "";
		 ActionContext ctx = actionInvocation.getInvocationContext();  		 
		 HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		 DmLog dmLog=DmLog.getLogger(actionInvocation.getClass());
	      try {
	            result = actionInvocation.invoke();
	        } catch (SystemException ex) {
				FacesUtils.setValueInHashtableOfSession("errorMessage","系统出现异常");
				FacesUtils.setValueInHashtableOfSession("errorDetail",ex.getMessage());
				dmLog.error(ex.getMessage());
	            return "error";
	        } catch (ApplicationException ex) {
				FacesUtils.setValueInHashtableOfSession("errorMessage","应用出现异常");
				FacesUtils.setValueInHashtableOfSession("errorDetail",ex.getMessage());
				dmLog.error(ex.getMessage());
	            return "error";
	        } catch (Exception ex) {
				FacesUtils.setValueInHashtableOfSession("errorMessage","系统出现异常");
				FacesUtils.setValueInHashtableOfSession("errorDetail",ex.getMessage());	
				dmLog.error(ex.getMessage());
	            return "error";
	        }
	      
	        return result;   
	}
	
	


}

package com.delmar.core.interceptor;

import com.delmar.sys.model.User;
import com.delmar.system.model.UserFootmark;
import com.delmar.system.service.UserFootmarkService;
import com.delmar.system.web.WebConst;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *@ClassName:   FootmarkInterceptor.java 
 *@Description:  记录操作员点击的足迹
 *
 * @author Charles Luo 
 * @Date: 2015年7月10日 上午10:04:59
 * @version V2.0
 */
public class FootmarkInterceptor implements Interceptor {
	
	
	@Autowired
	private UserFootmarkService userFootmarkService;
	
	
	public void destroy()
	{}
	
	public void init()
	{}
	
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{
		
	   ActionContext ctx = actionInvocation.getInvocationContext();  
	   //Map session=actionInvocation.getInvocationContext().getSession();
	   HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
	   User user=(User)request.getSession().getAttribute(WebConst.SESSION_LOGINUSER);
	   if (user==null)
	   {
		   return actionInvocation.invoke();        
	   }
	   

	   
	   
	   
	   
	   //System.out.println(request.getParameter("url"));  
       //System.out.println(request.getRequestURI());  
       //System.out.println(request.getServletPath()); 
       
      
       String actionName=actionInvocation.getAction().getClass().getName();          
       //String invocationName=invocationAction.substring(invocationAction.lastIndexOf(".")+1,invocationAction.length());
       String actionMethod=actionInvocation.getProxy().getMethod();
       String actionPurpose=actionName;
       
       //System.out.println(invocationAction);
       //System.out.println(invocationName);
       
       //System.out.println("Struts2 中配置的Action："+actionInvocation.getProxy().getActionName());  
       //System.out.println("调用的方法："+actionInvocation.getProxy().getMethod()); 
       
       
       Method[] methods = actionInvocation.getAction().getClass().getMethods();//获取action的所有public方法  
       for(Method method:methods){  
           if(method.getName().startsWith("getPurpose")){//遍历action对象的get方法  
//               if(AbstractVO.class.equals(method.getReturnType().getSuperclass())){//如果方             }
        	   actionPurpose=(String)method.invoke(actionInvocation.getAction(), new Object[]{});
//        	   System.out.println(actionPurpose);
        	   
        	   break;
           }
       }
       
       
	   UserFootmark userFootmark=new UserFootmark();
	   userFootmark.setUserId(user.getId());
	   userFootmark.setRemoteAddr(getIpAddr(request));
	   userFootmark.setVisiteDate(new Date());
	   userFootmark.setActionName(actionName);
	   userFootmark.setActionMethod(actionMethod);
	   userFootmark.setActionPurpose(actionPurpose);
	   userFootmark.setOrgId(user.getOrgId());
	   
	   userFootmarkService.save(userFootmark);
       
       return actionInvocation.invoke();        
	}
	
	
	
	public String getIpAddr(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}   
	

}

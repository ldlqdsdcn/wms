package com.delmar.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.delmar.sys.model.User;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月22日 下午4:38:17 
 * 类说明 
 */
public class Log4jUserFilter implements Filter {

	  private final static String DEFAULT_USERID="anonymous";
	  
	    public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest req=(HttpServletRequest)request;
	        HttpSession session= req.getSession();
	        
	        String remoteAddr = request.getRemoteAddr();
	        MDC.put("ip", remoteAddr); 
	        
	        if (session.getAttribute("loginUser") == null) {
	            MDC.put("userId",DEFAULT_USERID); 
	        }
	        else{
	        	User loginuser=(User)session.getAttribute("loginUser");
	            if (loginuser==null){
	                MDC.put("userId",DEFAULT_USERID);
	            }
	            else
	            {
	                MDC.put("userId",loginuser.getName()+":"+loginuser.getUsername());
	            }
	        }
	        
	        chain.doFilter(request,response);
	    }

	    public void init(FilterConfig fc) throws ServletException {
	        
	        // do nothing
	        
	    }

	    public void destroy() {
//	         do nothing
	    }
	    
}

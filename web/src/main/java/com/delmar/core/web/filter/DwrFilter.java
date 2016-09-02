/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 刘大磊 2015年1月12日 下午1:48:30
 */
public class DwrFilter implements Filter {
	private FilterConfig config;
	String[] logindwrurls=new String[]{"/dwr/interface/userLoginDwr.js","/dwr/engine.js","/dwr/util.js","/dwr/call/plaincall/userLoginDwr.getOrgSelectByClientId.dwr"};
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		/*/delmar_web/dwr/interface/userLoginDwr.js
		/delmar_web/dwr/engine.js*/
		///delmar_web/dwr/util.js
		HttpServletRequest hsr=(HttpServletRequest)request;
		
/*		for(String url:logindwrurls)
		{
			if(hsr.getRequestURI().indexOf(url)!=-1)
			{
				filterChain.doFilter(request, response);
				return;
			}
		}*/
			HttpSession session=((HttpServletRequest)request).getSession();
			if(session.getAttribute("loginUser")==null)
			{
				ServletContext context = config.getServletContext();
				RequestDispatcher rd = context
				.getRequestDispatcher("/frameLogin.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				filterChain.doFilter(request, response);
			}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config=filterConfig;

	}

}

/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.filter;

import java.io.IOException;
import java.util.List;

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

import com.delmar.sys.SystemConst;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.UserContent;

/**
 * @author 刘大磊 2015年1月12日 上午11:42:44
 * 
 * url 过滤权限
 */
public class UrlFilter implements Filter {
	private String[] urls;
	private FilterConfig config;
	private String[] mainpages;

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
	public void doFilter(ServletRequest request1, ServletResponse response1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) request1;
		// HttpServletResponse response = (HttpServletResponse) response1;
		HttpSession session=request.getSession();
		String pageurl = request.getServletPath();
		if (session.getAttribute("loginUser") == null) {
			

			boolean login = false;
			for (String url : urls) {
				if (pageurl.equals(url)) {
					login = true;
					break;
				}
			}
			
			
			if (!login) {
				ServletContext context = config.getServletContext();
				RequestDispatcher rd = context
						.getRequestDispatcher("/frameLogin.jsp");
				rd.forward(request1, response1);

				return;

			}
			
			

		}
		else
		{
			boolean nopage=true;
			for (String url : mainpages) {
				if (pageurl.equals(url)) {
					nopage =false;
					break;
				}
			}
			
		
			if(nopage)
			{
				UserContent userContent=(UserContent)session.getAttribute("userContent");
			boolean noViewPrivilege=true;
			List<Page> pageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
			if(pageList!=null)
			for(Page page:pageList)
			{
				if(pageurl.startsWith(page.getPageUrl()))
				{
					noViewPrivilege=false;
					break;
				}
			}
			if(noViewPrivilege)
			{
				ServletContext context = config.getServletContext();
				RequestDispatcher rd = context
						.getRequestDispatcher("/error/noprivileges.jsp");
				rd.forward(request1, response1);
				return;
			}
			}
		}
		try {
			chain.doFilter(request1, response1);

			
		} catch (Exception e) {
			ServletContext context = config.getServletContext();
			RequestDispatcher rd = context
			.getRequestDispatcher("/error/500.jsp");
			request.setAttribute("javax.servlet.error.exception", e);
			
			e.printStackTrace();
			rd.forward(request1, response1);
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
		String s = arg0.getInitParameter("url");
		String[] loginPages=config.getInitParameter("mainpage").split(",");
		urls = s.split(",");
		
		mainpages=new String[loginPages.length+urls.length];
		for(int i=0;i<urls.length;i++)
		{
			this.mainpages[i]=urls[i];
		}
		for(int i=0;i<loginPages.length;i++)
		{
			this.mainpages[i+urls.length]=loginPages[i];
		}
		
	}

}

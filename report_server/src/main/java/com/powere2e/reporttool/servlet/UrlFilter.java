package com.powere2e.reporttool.servlet;

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


public class UrlFilter implements Filter {
	private String[] urls;
	private FilterConfig config;

	public void destroy() {

	}

	public void doFilter(ServletRequest request1, ServletResponse response1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) request1;
		//HttpServletResponse response = (HttpServletResponse) response1;
		if (request.getSession().getAttribute("username") == null) {
			String pageurl = request.getServletPath();
			
			System.out.println(pageurl);
			boolean login = false;
			for (int i=0;i<urls.length;i++) {
			
				if (pageurl.equals(urls[i])) {
					login = true;
					break;
				}
			}
			if (!login) {
				ServletContext context = config.getServletContext();
				RequestDispatcher rd = context
						.getRequestDispatcher("/frameLogin.jsp");
				rd.forward(request1, response1);
				// response.sendRedirect("/kcyjxt/frameLogin.jsp");
				return;
				

			}

		}
	try{
		chain.doFilter(request1, response1);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}

	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
		String s = arg0.getInitParameter("url");
		urls = s.split(",");
		/*for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i]);
		}*/
	}

}

/**
 * 
 */
package com.powere2e.reporttool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import com.powere2e.reporttool.jxlsprocessor.ReportProcessor;
import com.powere2e.reporttool.manager.AuthenticationManger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author peter
 *
 */
public class JxlsReportProviderServlet extends ReportProviderServlet 
{

	long start = System.currentTimeMillis();
	/**
	 * 
	 */
	public JxlsReportProviderServlet() 
	{
		super();	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
	{
		String token=request.getParameter("token");
		ServletContext application = getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(application);
		AuthenticationManger authenticationManger=wac.getBean(AuthenticationManger.class);
		if(authenticationManger.verifyUser(token))
		{
			//  Clean up the ReportProcessor if it is still in session
			request.getSession().removeAttribute(PROCESSOR_KEY);

			// XLS display type
			request.setAttribute(this.REPORT_TYPE, this.EXCEL_REPORT);

			ReportProcessor processor = new ReportProcessor();
			request.getSession().setAttribute(PROCESSOR_KEY, processor);

			long end = System.currentTimeMillis();
			System.out.println("JxlsReportProviderServlet  doPost runtime================"
					+ (end - start));

			super.doPost(request, response);
		}
		else
		{
			noPrivileges(response);
		}

	}

}

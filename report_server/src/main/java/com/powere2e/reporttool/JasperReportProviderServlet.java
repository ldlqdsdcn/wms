package com.powere2e.reporttool;


import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delmar.reporttool.manager.AuthenticationManger;
import org.apache.log4j.Logger;

import com.powere2e.reporttool.jasperprocessor.ReportProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author peter
 * 
 */
public class JasperReportProviderServlet extends ReportProviderServlet {

	private Logger log = Logger.getLogger(JasperReportProviderServlet.class
			.getName());

	long start = System.currentTimeMillis();

	/**
	 * 
	 */
	public JasperReportProviderServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(application);
		AuthenticationManger authenticationManger=wac.getBean(AuthenticationManger.class);
		String token=request.getParameter("token");

		if(authenticationManger.verifyUser(token))
		{// Clean up the ReportProcessor if it is still in session

			System.out.println("********reporttool has begin********");
//		log.info("********reporttool has begin********");

			request.getSession().removeAttribute(PROCESSOR_KEY);

			ReportProcessor processor = new ReportProcessor();
			request.getSession().setAttribute(PROCESSOR_KEY, processor);

			log.debug("ReportProcessor-"+ request.getSession().getAttribute(PROCESSOR_KEY));

			long end = System.currentTimeMillis();
			log.info("JasperReportProviderServlet doPost runtime:" + (end - start));
			super.doPost(request, response);}
			else
		{
			noPrivileges(response);
		}



	}

}

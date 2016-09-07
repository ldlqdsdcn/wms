/**
 * @File: showReport.java
 */
package com.powere2e.reporttool;

import java.io.BufferedOutputStream;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.powere2e.reporttool.ReportProcessor;

/**
 * @author peter
 * 
 */
public class showReport extends HttpServlet {

	long start = System.currentTimeMillis();

	/**
	 *   
	 */
	public showReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		response.setContentType("application/octet-stream");
		System.out.println("report type=================="
				+ session.getAttribute(ReportProviderServlet.REPORT_TYPE)
						.toString() + "        " + "reportName================"
				+ session.getAttribute("ReportName").toString());

		if (session.getAttribute(ReportProviderServlet.REPORT_TYPE).toString()
				.equalsIgnoreCase(ReportProviderServlet.PDF_REPORT)) {
			// response.setContentType("application/pdf");
			// response.setContentType("application/pdf;charset=GB2312");
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ session.getAttribute("ReportName") + ".pdf\"");
		} else if (session.getAttribute(ReportProviderServlet.REPORT_TYPE)
				.toString()
				.equalsIgnoreCase(ReportProviderServlet.EXCEL_REPORT)) {
			// response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ session.getAttribute("ReportName") + ".xls\"");
		} else {
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ session.getAttribute("ReportName") + ".pdf.zip\"");
		}
		ServletOutputStream outStream = response.getOutputStream();

		ReportProcessor processor = (ReportProcessor) session
				.getAttribute(ReportProviderServlet.PROCESSOR_KEY);

		BufferedOutputStream bos = null;
		bos = new BufferedOutputStream(outStream);

		int i = processor.getReportData().length;

		bos.write(processor.getReportData(), 0, i);
		
		bos.close();

		long end = System.currentTimeMillis();

		System.out.println("showReport runtime================"
				+ (end - start));

		outStream.close();

		// Clean up the session
		// session.removeAttribute(ReportProviderServlet.PROCESSOR_KEY);
	}
}

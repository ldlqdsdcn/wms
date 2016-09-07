package com.powere2e.reporttool;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.delmar.reoprttool.def.DisplayType;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.jasperprocessor.Cache;

/**
 * 
 * @author peter
 * @version $Id: ReportProviderServlet.java,v 1.30 2007/08/23 08:57:29 axular
 *          Exp $
 */
public class ReportProviderServlet extends HttpServlet {
	protected final static String REPORT_TYPE = "DisplayType";

	protected final static String REPORT_NAME = "ReportName";

	protected final static String EXCEL_REPORT = "Excel";

	protected final static String PDF_REPORT = "PDF";


	protected final static String JASERPRINT_KEY = "JasperPrint";

	protected final static String PROCESSOR_KEY = "Pro";

	protected final static String COLOR_KEY = "productColor";
	
	protected final static String VenditionDetailReport_key = "VenditionDetailReport";  //销售明细表
	
	protected final static String VenditionCollectReport_key = "VenditionCollectReport";  //销售汇总表

	private static Properties properties = new Properties();

	private Logger log = Logger
			.getLogger(ReportProviderServlet.class.getName());

	long start = System.currentTimeMillis();

	/**
	 * Constructor
	 */
	public ReportProviderServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		//Map parammap=request.getParameterMap();//报表Cache, 在一段时间内，使用相同的参数，请求相同的报表，直接从cache取出报表临时文件，不再执行数据查询
		//request.getSession().setAttribute("parammap", parammap);//
		//ServletContext   application=this.getServletContext();
		Cache.getInstance();
		//application.getAttribute("");
		String reportfile = null;
		String displayType = null;
		HashMap params = new HashMap();
		HttpSession session = request.getSession();
		HashMap oldParams = (HashMap) session.getAttribute("params");
		boolean paramschanged = false;
		if (oldParams == null)
		{
			paramschanged = true;
		}

		// Get the Parameters
		Enumeration en = request.getParameterNames();

		while (en.hasMoreElements()) 
		{
			String key = en.nextElement().toString();
			log.debug("Parameters-" + key + ":" + request.getParameter(key));
			if (REPORT_NAME.equalsIgnoreCase(key)) 
			{
				reportfile = request.getParameter(key);
				session.removeAttribute(REPORT_NAME);
				session.setAttribute(REPORT_NAME, reportfile);
			}
			else if (REPORT_TYPE.equalsIgnoreCase(key))
			{
				if (EXCEL_REPORT.equalsIgnoreCase(request.getParameter(key)))
				{
					displayType = EXCEL_REPORT;

					session.removeAttribute(REPORT_TYPE);
					session.setAttribute(REPORT_TYPE, displayType);
				}
				else if (PDF_REPORT.equalsIgnoreCase(request
						.getParameter(key))) {
					displayType = PDF_REPORT;

					session.removeAttribute(REPORT_TYPE);
					session.setAttribute(REPORT_TYPE, displayType);
				} 
				else
				{
					displayType = DisplayType.PDFZIP.getDesc();
					session.removeAttribute(DisplayType.PDFZIP.getDesc());
					session.setAttribute(REPORT_TYPE, displayType);
				}
			} else {
				params.put(key, request.getParameter(key));
				
				if(COLOR_KEY.equals(key)){
					if(request.getParameter(key)!=null && !"".equals(request.getParameter(key))){
						if(this.VenditionCollectReport_key.equals(session.getAttribute(REPORT_NAME)) 
								|| this.VenditionDetailReport_key.equals(session.getAttribute(REPORT_NAME)))
					     //	转码
					      params.put(key, "and p.color like'%"+toChinese(request.getParameter(key))+"%'");
						else
						  params.put(key, "and m.color like'%"+toChinese(request.getParameter(key))+"%'");
					}
					if("".equals(request.getParameter(key))){
						params.put(key, "");
					}
				}				
				if (!paramschanged) 
				{
					String oldvalue = oldParams.get(key) == null ? null: oldParams.get(key).toString();
					if ((oldvalue != null && request.getParameter(key) == null)|| (oldvalue == null && request.getParameter(key) != null)	|| !oldvalue.equals(request.getParameter(key)))
					{
						paramschanged = true;
					}
					
				}
			}
		}

		session.removeAttribute("params");
		session.setAttribute("params", params);

		ReportProcessor processor = (ReportProcessor) session
				.getAttribute(PROCESSOR_KEY);
		System.out.println(processor.getClass().getName());
		log.debug("ReportProcessor = " + processor);
		processor.setReportName(reportfile);
		processor.setReporttype(displayType);
		// processor.setParams(params);

		// ***add by Axular*******************************************//
		String sign = (String) params.get("Sign");

		// **从配置文件读取配置信息进行设置****//
		String prefix = getServletContext().getRealPath("/");
		try {
			InputStream is = new FileInputStream(new File(prefix
					+ "/WEB-INF/Pro.properties"));
			properties.load(is);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("Load properties error", ioe);
		}
		/**
		 * 取得ACTIONTYPE的及中文显示
		 */
		// **把ACTIONTYPE按逗号进行分割，取得多种交易类型，并作为参数保存***//
		String actionType = (String) params.get("ActionType");
		if (actionType != null) {
			String[] type = actionType.split(",");
			// **判断是否存在多个交易类型***//
			if (type.length > 1) {
				for (int i = 0; i < type.length; i++) {
					String aType = type[i];
					params.put("ActionType" + aType, aType);
				}
			} else {
				params.put("ActionType00", actionType);
				params.put("ActionType01", "");
				params.put("ActionType02", "");
				params.put("ActionType03", "");
				params.put("ActionType04", "");
			}
			// **读取交易类型并加入参数中**//
			params.put("tenancy", new String(properties.getProperty("tenancy")
					.getBytes("ISO8859_1")));
			params.put("withdraw", new String(properties
					.getProperty("withdraw").getBytes("ISO8859_1")));
			params.put("send", new String(properties.getProperty("send")
					.getBytes("ISO8859_1")));
			params.put("receive", new String(properties.getProperty("receive")
					.getBytes("ISO8859_1")));
			params.put("adjustment", new String(properties.getProperty(
					"adjustment").getBytes("ISO8859_1")));
		}
		/**
		 * 取得MOVETYPE及中文显示 
		 */
		String moveType = (String) params.get("MoveType");
		if (moveType != null) {
			String[] mType = moveType.split(",");
			System.out.println("length================" + mType.length);
			if (mType.length > 1) {
				for (int i = 0; i < mType.length; i++) {
					String moType = mType[i];
					params.put("MoveType" + i, moType);
				}
			} else {
				params.put("MoveType0", moveType);
				params.put("MoveType1", "");
				params.put("MoveType2", "");
				params.put("MoveType3", "");
				params.put("MoveType4", "");
				params.put("MoveType5", "");
				params.put("MoveType6", "");
				params.put("MoveType7", "");
			}
			// **读取交易类型并加入参数中**//
			params.put("TypesName0", properties.getProperty("TypesName0"));
			params.put("TypesName1", properties.getProperty("TypesName1"));
			params.put("TypesName2", properties.getProperty("TypesName2"));
			params.put("TypesName3", properties.getProperty("TypesName3"));
			params.put("TypesName4", properties.getProperty("TypesName4"));
			params.put("TypesName5", properties.getProperty("TypesName5"));
			params.put("TypesName6", properties.getProperty("TypesName6"));
			params.put("TypesName7", properties.getProperty("TypesName7"));
		}

		/**
		 * 取得machinetype及中文显示 
		 */
		String machinetype = (String) params.get("machinetype");
		if (machinetype != null) {
			
			// **读取交易类型并加入参数中**//
			params.put("machinetype1", properties.getProperty("machine.bottle"));
			params.put("machinetype2", properties.getProperty("machine.preform"));
			params.put("machinetype3", properties.getProperty("machine.crystal"));
			
		}
		/**
		 * 产品合格证信息
		 */
		String red=("红证");
        String blue=("蓝证");
        String green=("绿证");
        String yellow=("黄证");
        String white=("白证");
      
        params.put("red",red);
        params.put("blue",blue);
        params.put("green",green);
        params.put("yellow",yellow);
        params.put("white",white);
        
        //andy test 原材料差异报表,计算实际领料的中文参数
        String concerntrate=("%主剂%");
        params.put("andyTemp1",concerntrate);
        
        String sugar=("%白砂糖%");
        params.put("andyTemp2",sugar);        
        
        String bottle=("%瓶胚%");
        params.put("andyTemp3",bottle);
        
        String closure=("%瓶盖%");
        params.put("andyTemp4",closure);
        
        String label=("%标签%");
        params.put("andyTemp5",label);
        
        String barcode=("%条%码%");
        params.put("andyTemp6",barcode);
        
        String pad=("%纸垫%");
        params.put("andyTemp7",pad);
        
        String carton=("%纸箱%");
        params.put("andyTemp8",carton);
        
        String pe=("%PE收缩膜%");
        params.put("andyTemp9",pe);
      
		/**
		*  取得托盘名称的中文显示
		*/
		String CustomerDetail = (String) params.get("CustomerDetail");
		if (CustomerDetail != null) {
			
			// **读取交易类型并加入参数中**//
			params.put("palletone", properties.getProperty("palletone"));
			params.put("pallettwo", properties.getProperty("pallettwo"));
			params.put("palletthree", properties.getProperty("palletthree"));
			
		}
		
		/**
		 * 取得未开票状态的中文显示
		 */
		params.put("invoiceStatus", properties.getProperty("invoiceStatus"));
		
		  
		System.out.println("params===" + params.toString());

		processor.setParams(params);

		// **判断是否符合发送报表邮件的标志***//
		if (("0").equals(sign)) {
			
			processor.setEmail(com.powere2e.reporttool.config.Config.getEmail());
		// **** 设置 SMTP 服务器 ****/
			//processor.setMailSMTPHost(com.powere2e.reporttool.config.Config.getEmail().getEmailserver());
			// **** 设置 邮件用户名 ****/
			//processor.setMailUser(com.powere2e.reporttool.config.Config.getEmail().getMailuser());
			// **** 设置 邮件密码 ****/
			//processor.setMailPassword(com.powere2e.reporttool.config.Config.getEmail().getMailpassword());
			// ** 设置邮件目的地址***/
			// processor.setMailTOAddress(properties.getProperty("mailTOAddress"));
			// ****设置邮件发送地址*****//
			//processor.setMailFromAddress(com.powere2e.reporttool.config.Config.getEmail().getMailfromaddress());
			// ** 设置 邮件主题***//
			//processor.setMailSubject(com.powere2e.reporttool.config.Config.getEmail().getMailsubject());
			// **设置 邮件正文****//
			//processor.setMailBody(com.powere2e.reporttool.config.Config.getEmail().getMailbody());
					
		}

		// *********modify end***********************************************//

		session.removeAttribute(PROCESSOR_KEY);
		session.setAttribute(PROCESSOR_KEY, processor);

		// Parameters changed?
		if (!paramschanged) {
			JasperPrint jprint = (JasperPrint) session
					.getAttribute(JASERPRINT_KEY);

			processor.setJasperPrint(jprint);
		}

		processor.start();

		session.removeAttribute(JASERPRINT_KEY);
		session.setAttribute(JASERPRINT_KEY, processor.getJasperPrint());

		long end = System.currentTimeMillis();
		System.out
				.println("ReportProviderServlet  doPost runtime================"
						+ (end - start));

		log.info("ReportProviderServlet  doPost  runtime:" + (end - start));
		response.sendRedirect("processbar.jsp");
	}
	protected String toChinese(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes, "UTF-8");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	protected void noPrivileges(HttpServletResponse response)
	{
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>你没有权限</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("你没有权限执行该操作");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}

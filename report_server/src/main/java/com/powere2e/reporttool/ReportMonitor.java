/**
 * @File: ReportMonitor.java
 * 
 */
package com.powere2e.reporttool;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRProperties;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.config.Config;
import com.powere2e.reporttool.config.DB;
import com.powere2e.reporttool.datasource.DataSourceProvider;
import com.powere2e.reporttool.jasperprocessor.Cache;
import com.powere2e.reporttool.jasperprocessor.ReportCompiler;

/**
 * @author peter
 * @version $Id: ReportMonitor.java,v 1.36 2008/07/29 02:49:11 solomon Exp $
 */

public class ReportMonitor extends HttpServlet {

	// long start = System.currentTimeMillis();
	private static String Report_Home;
	
	private static Properties properties = new Properties();

	private Logger log = Logger.getLogger(ReportMonitor.class.getName());

	private String n = null;// ��־��¼�Ƿ�ɹ���

	/** Hidden Parameter Command - Button */
	private static final String P_Command = "pageCommand";

	/**
	 * Constructors
	 */
	public ReportMonitor() {

	}

	/**
	 * This method will be initialized automatically when the server is started.
	 * It will register a data source,set the size of cache and set Report_Home.
	 * 
	 * @exception ServletException
	 */
	public void init() throws ServletException {
		// configure the log4j
		/*String prefix = getServletContext().getRealPath("/");
		PropertyConfigurator.configure(prefix + "/WEB-INF/log4j.properties");*/
		ServletContext context = this.getServletConfig().getServletContext();
		log = Logger.getLogger(ReportMonitor.class.getName());
		DB.database_Home=context.getRealPath("WEB-INF/db");
		
		// Properties properties = new Properties();
		
        Config.initialize();
		/*try {
			InputStream is = new FileInputStream(new File(prefix
					+ "/WEB-INF/Pro.properties"));
			properties.load(is);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("Load properties error", ioe);
		}*/

		// Register Data Source
		DataSourceProvider dataSourceProvider = DataSourceProvider
				.getInstance();
		
		dataSourceProvider.registerDataSource(Config.dataSource);
		if (dataSourceProvider.getDataSource() == null) {
			log.warn("Cannot get the Connection");
		}

		log
				.info("Default Report_Home:"
						+ properties.getProperty("Report_Home"));
		setReport_Home(Config.reportHome);

		log.info("Default Cache_Size:" + Config.cachesize);
		setCacheSize(Integer.parseInt(Config.cachesize));
		// Integer.parseInt(properties.getProperty("Cache_Size"))

		
		JRProperties.setProperty(JRProperties.COMPILER_CLASSPATH, context
				.getRealPath("/WEB-INF/lib/jasperreports-1.2.0.jar")
				+ System.getProperty("path.separator")
				+ context.getRealPath("/WEB-INF/classes/"));

		// long end = System.currentTimeMillis();
		// System.out.println("ReportMonitor init
		// runtime================"+(end-start));

	}

	/**
	 * Overides javax.serverlet.http.HttpServlet.doGet
	 * 
	 * @exception: ServletException
	 * @exception: IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Overides javax.serverlet.http.HttpServlet.doPost It will get parameters
	 * from Console.jsp. By these parameters it will reset
	 * Pro.properties,recompile a report file,clean the cache ,reset a
	 * datasource and so on.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @exception ServletException
	 * @exception IOException
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//Enumeration en = request.getParameterNames();
		String p_cmd = request.getParameter(P_Command);

		// Recompile one or all *.xml file
		if (p_cmd.equalsIgnoreCase("recompile")) {
			log.info("Recompile the report "
					+ request.getParameter("ReportName"));
			if (request.getParameter("ReportName") != null
					&& !request.getParameter("ReportName").equals("")) {
				ReportCompiler compiler = new ReportCompiler();
				compiler.compileReport(request.getParameter("ReportName")
						+ ".jrxml");
				compiler.compileReport(request.getParameter("ReportName")
						+ ".xml");

				// Cache.getInstance().removeReport(request.getParameter("ReportName"));
				if (Cache.getInstance().getReport(
						request.getParameter("ReportName")) != null) {
					JasperReport jasperReport = compiler.getJasperReport();
					Cache.getInstance().removeReport(jasperReport.getName());
				}
			} else {
				log.info("Compile all reports");
				ReportCompiler compiler = new ReportCompiler();
				File file = new File(request.getParameter("Report_Home"));
				compiler.compileAllReport(file);
				Cache.getInstance().removeAllReports();
			}
		}

		// Clean cache;
		else if (p_cmd.equalsIgnoreCase("clean")) {
			log.info("clean the cache");
			Cache.getInstance().removeAllReports();
		}
		// Execute Save action
		else {
			/*while (en.hasMoreElements()) {
				String key = en.nextElement().toString();
				log
						.debug("Parameters-" + key + ":"
								+ request.getParameter(key));
				if (!"Recompile".equalsIgnoreCase(key)
						&& !"LogLevel".equalsIgnoreCase(key)
						&& !"ReportName".equalsIgnoreCase(key)) {

					if ("Report_Home".equalsIgnoreCase(key)) {

						File file = new File(new String(request.getParameter(
								key).getBytes("ISO8859_1")));

						if (file.isDirectory()) {
							properties.setProperty(key, new String(request
									.getParameter(key).getBytes("ISO8859_1"))); // Set
							// *.properties
                            
                            Config.setReportHome(new String(request
                                    .getParameter(key).getBytes("ISO8859_1")));
						}

					} else {
						properties.setProperty(key, new String(request
								.getParameter(key).getBytes("ISO8859_1"))); // Set
						// *.properties
					}

				} else if ("LogLevel".equalsIgnoreCase(key)) {
					log.debug("ReSet loglevel " + request.getParameter(key));
					int loglevel = Integer.parseInt(request.getParameter(key));
					Logger root = Logger.getRootLogger();
					switch (loglevel) {
					case 0:
						root.setLevel(Level.DEBUG);
						break;
					case 1:
						root.setLevel(Level.INFO);
						break;
					case 2:
						root.setLevel(Level.WARN);
						System.out.println("loglevel is ---=:" + loglevel);
						break;
					case 3:
						root.setLevel(Level.ERROR);
						break;
					case 4:
						root.setLevel(Level.FATAL);
						break;
					case 5:
						root.setLevel(Level.OFF);
						break;
					default:
						root.setLevel(Level.INFO);
					}
				}
			}*/
			// Save the Properties into Pro.properties file
		/*	try {
				String prefix = getServletContext().getRealPath("/");
				FileOutputStream fos = new FileOutputStream(prefix
						+ "/WEB-INF/Pro.properties");
				properties.store(fos, "This is Pro.properties");
				fos.flush();
				fos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
*/
			// init();

			/*DataSourceProvider dataSourceProvider = DataSourceProvider
					.getInstance();
			dataSourceProvider.registerDataSource(properties);
			if (dataSourceProvider.getDataSource() == null) {
				log.warn("Cannot get the Connection");
			}*/
			com.powere2e.reporttool.config.DB db=com.powere2e.reporttool.config.DB.getInstance();
			try
			{
				
			db.update("update datasource set type='"+request.getParameter("dataSourceType")+"',jndiname='"+request.getParameter("dataResource")+"',jdbcdriver='"
					+request.getParameter("driverClass")+"',jdbcurl='"+request.getParameter("url")+"',username='"+request.getParameter("userName")+"',password='"+request.getParameter("passWord")+"' where key='"+request.getParameter("key")+"'");
			db.update("update email set mailserver='"+request.getParameter("mailSMTPHost")+"',mailfromaddress='"+request.getParameter("mailFromAddress")
					+"',mailsubject='"+request.getParameter("mailSubject")
					+"',mailbody='"+request.getParameter("mailBody")+"',mailuser='"+request.getParameter("mailUser")+"',mailpassword='"+request.getParameter("mailPassword")+"'");
			db.update("update Prop set value='"+request.getParameter("Report_Home")+"' where key='ReportHome'");
			db.update("update Prop set value='"+request.getParameter("Cache_Size")+"' where key='CacheSize'");
			db.update("update Prop set value='"+request.getParameter("LogLevel")+"' where key='LogLevel'");
			db.update("commit;");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.error(e.getMessage());
			}
			
			log.info("Default Report_Home:"
					+ properties.getProperty("Report_Home"));
			Config.initialize();
		
			DataSourceProvider.m_instance.registerDataSource(com.powere2e.reporttool.config.Config.dataSource);
			
			//setReport_Home(properties.getProperty("Report_Home"));
			setReport_Home(Config.reportHome);
			
            
			log.info("Default Cache_Size:"
					+ Config.cachesize);
			//setCacheSize(Integer.parseInt(properties.getProperty("Cache_Size")));
			setCacheSize(Integer.parseInt(Config.cachesize));
		}

		// long end = System.currentTimeMillis();
		// System.out.println("ReportMonitor doPost
		// runtime================"+(end-start));

		response.sendRedirect("Console.jsp");
	}

	/**
	 * Get a Report_Home.
	 * 
	 * @return Report_Home
	 */
	public static String getReport_Home() {
		return Report_Home;
	}

	/**
	 * Set Report_Home.
	 * 
	 * @param report_Home
	 */
	private static void setReport_Home(String report_Home) {
		Report_Home = report_Home;
	}

	/**
	 * Set teh MAX_Size of the Cache.
	 * 
	 * @param size
	 */
	private void setCacheSize(int size) {
		Cache.getInstance().setMAX_COUNT(size);
	}

	/**
	 * Get the properies file.
	 * 
	 * @return the propertied file
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Control the users'access.
	 * 
	 * @param name
	 * @param password
	 * @return n
	 */
	public String logging(String name, String password) {
		// String n = null;
		com.powere2e.reporttool.config.DB db=com.powere2e.reporttool.config.DB.getInstance();
		 java.util.List list=null;
		 try{
		 list=db.query("select * from user where userId='"+name+"'");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		/* if (!name.equalsIgnoreCase("report")) {
			System.out.println("logging name");
			n = "x";
		} else if (!password.equalsIgnoreCase("report")) {
			n = "y";
		} else
			n = "z";*/
		 if(list.size()==0)
			 n= "x";
		 else
		 {
			 n= "y";
			 for(int i=0;i<list.size();i++)
			 {
				 Map map=(Map)list.get(i);
				 if(password.equals(map.get("PASSWORD")))
				 {
					n="z";
					break;
				 }
			 }
		 }
		log.info("Login " + n);
		return n;
	}
}

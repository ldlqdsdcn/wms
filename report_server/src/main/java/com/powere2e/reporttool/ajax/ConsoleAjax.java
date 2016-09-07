package com.powere2e.reporttool.ajax;

import java.io.File;

import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.config.Config;
import com.powere2e.reporttool.datasource.impl.JDBCDataSource;
import com.powere2e.reporttool.datasource.impl.JNDIDataSource;
import com.powere2e.reporttool.jasperprocessor.Cache;
import com.powere2e.reporttool.jasperprocessor.ReportCompiler;

public class ConsoleAjax {
	 private Logger log = Logger.getLogger(ConsoleAjax.class.getName());
	public int cleanCache()
	{
		Cache ca=Cache.getInstance();
		int count=ca.getCount();
		ca.removeAllReports();
		return count;
	}
	public String testConnection()
	{
		com.powere2e.reporttool.config.DataSource ds=Config.getDatasources()[0];
		String conn="Y";
		if("jdbc".equals(ds.getType()))
				{
			JDBCDataSource jdbc=new JDBCDataSource(ds);
			if(!jdbc.isConnect())
			{
				conn="N";
			}
				}
		else
		{
			try {
				JNDIDataSource jndi=new JNDIDataSource(ds);
				if(!jndi.isConnect())
				{
					conn="N";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
		
	}
	public String compileReport(String type,String ReportName )
	{
		ReportCompiler compiler = new ReportCompiler();
		ReportCompiler.cleanCompileMessage();
		String msg="Y";
		
		if("ALL".equals(type))
		{
			try
			{
			log.info("Compile all reports");
			File file = new File(Config.getReportHome());
			compiler.compileAllReport(file);
			Cache.getInstance().removeAllReports();
			}
			catch(Exception e)
			{
				msg="N";
			}
		}
		else
		{
			
		try
		{
			
			msg=compiler.compileReport(ReportName+ ".jrxml");
			if("N".equals(msg))
			{
				msg=compiler.compileReport(ReportName+ ".xml");
			
			}
		// Cache.getInstance().removeReport(request.getParameter("ReportName"));
		if (Cache.getInstance().getReport(ReportName) != null)
		{
			JasperReport jasperReport = compiler.getJasperReport();
			Cache.getInstance().removeReport(jasperReport.getName());
		}
		}
		catch(Exception e)
		{
			msg="N";
		}
		}
		
		
		return msg;
		
	}
}

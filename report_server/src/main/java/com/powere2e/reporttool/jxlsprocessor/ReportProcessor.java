/**
 * 
 */
package com.powere2e.reporttool.jxlsprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

import org.apache.log4j.Logger;

import net.sf.jxls.report.ReportManager;
import net.sf.jxls.report.ReportManagerImpl;
import net.sf.jxls.transformer.XLSTransformer;

import com.powere2e.reporttool.ReportMonitor;
import com.powere2e.reporttool.datasource.DataSource;
import com.powere2e.reporttool.datasource.DataSourceProvider;

/**
 * @author peter
 * 
 */
public class ReportProcessor extends com.powere2e.reporttool.ReportProcessor {
	private Logger log = Logger.getLogger(ReportProcessor.class.getName());

	long start = System.currentTimeMillis();

	public void doIt() 
	{
		DataSource ds = DataSourceProvider.getInstance().getDataSource();
		// HashMap beans = new HashMap();
		Connection conn = ds.getConnection();
		ReportManager reportManager = new ReportManagerImpl(conn, params);
		// We place ReportManager object into the bean context using "rm" key.
		// It means that in XLS template we will be able to execute any SQL
		// query by passing it to rm.exec() method as a parameter.
		params.put("rm", reportManager);
		try {
			String tempalteFileName = ReportMonitor.getReport_Home()
					+ System.getProperty("file.separator") + this.reportname;
			File filename = File.createTempFile(this.reportname, ".xls");
			// System.out.println("the tempalteFileName is
			// =:"+tempalteFileName);
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(tempalteFileName + ".xls", params,
					filename.getAbsolutePath());

			//  modify                //
			File tempfile = new File(filename.getAbsolutePath());
			String sign = (String) params.get("Sign");
			String mailaddress = (String) params.get("Mailaddress");
			log.info("[sign]=" + sign);
			System.out.println("sign=======" + sign + "   "	+ "mailaddress=====" + mailaddress); 
			// ***调用订阅报表功能***//
			sendMail(tempfile, sign, mailaddress);
			// modify end                     //
			
			
			// Export the data
			FileInputStream stream = new FileInputStream(filename);
			reportData = new byte[stream.available()];
			stream.read(reportData);
			stream.close();
			filename.deleteOnExit();

			long end = System.currentTimeMillis();
			System.out
					.println("jxls ReportProcessor  doIt runtime================"
							+ (end - start));

			ds.closeDataSource(conn);
		} 
		catch (Exception e)
		{
			/** *********add by axular*********** */
			ds.closeDataSource(conn);
			/** **********add end**************** */
			e.printStackTrace();
			// log.error("", e);
		}
	}

	public int getProcess() {
		return 100;
	}
}

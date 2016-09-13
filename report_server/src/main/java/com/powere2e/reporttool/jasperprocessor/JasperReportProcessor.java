/**
 * 
 */
package com.powere2e.reporttool.jasperprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.datasource.DataSource;
import com.powere2e.reporttool.datasource.DataSourceProvider;

/**
 * @author peter
 * @version $Id: ReportProcessor.java,v 1.20 2007/12/11 08:53:35 axular Exp $
 */

public class JasperReportProcessor extends com.powere2e.reporttool.ReportProcessor {

	private static final Logger log = Logger.getLogger(JasperReportProcessor.class);
	private JasperPrint jasperPrint = null;

	private JasperReport jasperReport = null;

	private short process = 0;

	private short countSpeed = 100; // 0.1 second

	private long start = System.currentTimeMillis();

	public int getProcess() {
		return this.process;
	}

	public JasperPrint getJasperPrint() {
		return this.jasperPrint;
	}

	/**
	 * Overrides java.lang.Thread.run(). In this method will execute full
	 * process from compile report file to export report file.
	 */
	public void doIt() {
		log.debug(this.jasperPrint);

		if (this.jasperPrint != null) {
			exportReport();
		}

		log.info("this.reportname:" + this.reportname);
		jasperReport = Cache.getInstance().getReport(this.reportname);

		log.debug("Load JasperReport from cache-" + jasperReport);

		if (jasperReport == null) {
			ReportCompiler compiler = new ReportCompiler();
			compiler.compileReport(this.reportname + ".jrxml");
			compiler.compileReport(this.reportname + ".xml");
			jasperReport = compiler.getJasperReport();

			Cache.getInstance().loadReport(jasperReport);
		}
		if (jasperReport == null) {
			log.error("cannot compile report");
			return;
		}

		log.debug("jasperReport-" + jasperReport);
		
		/**********  modify by axular***********/		 
		DataSource ds = DataSourceProvider.getInstance().getDataSource();
		Connection conn = ds.getConnection();
		/********* modify end*******************/
		try {
			SimpleDateFormat sdf = (SimpleDateFormat) DateFormat
					.getDateInstance();
			sdf.applyPattern("yyyy-MM-dd");

			// Convert the Parameters
			JRParameter[] jrparams = jasperReport.getParameters();

			for (int i = 0; i < jrparams.length; i++) {
				JRParameter jrparam = jrparams[i];
				String value = (String) params.get(jrparam.getName());
				if (jrparam.getValueClassName().equals("java.lang.Integer")) {
					if (value != null && value.length() > 0
							&& !"null".equals(value))
						params.put(jrparam.getName(), Integer.valueOf(value));
					else
						params.put(jrparam.getName(), null);
				} else if (jrparam.getValueClassName().equals("java.util.Date")) {
					if (value != null && value.length() > 0
							&& !"null".equals(value)) {
						params.put(jrparam.getName(), sdf.parse(value));
					} else
						params.put(jrparam.getName(), null);
				}
			}

			/*******modify by axular**********/
//			DataSource ds = DataSourceProvider.getInstance().getDataSource();
//			Connection conn = ds.getConnection();
			/************modify end***********/
			if (ds.isJDBCConnection()) {
				this.jasperPrint = JasperFillManager.fillReport(jasperReport,
						this.params, conn);
			}

			else {

				this.jasperPrint = JasperFillManager.fillReport(jasperReport,
						this.params, conn);
			}

			ds.closeDataSource(conn);
		} catch (Exception e) {
			/********  add by axualr **********/
			ds.closeDataSource(conn);
			/*********add end ************/
			e.printStackTrace();
			log.error("", e);
			return;
		}

		exportReport();
		long end = System.currentTimeMillis();
		System.out.println("jasper ReportProcessor doIt runtime:" + (end - start));
		log.info("jasper ReportProcessor doIt runtime:" + (end - start));


	}

	/**
	 * Export report.
	 * 
	 */
	private void exportReport() {
		log.info("starting to export report");
		try {
			// File filename = File.createTempFile("report", ".rpt");
			File filename;
			// = File.createTempFile(reportname, ".rpt");
			if(reporttype==null)
			{
				reporttype="PDF";
			}
			if (this.reporttype.equalsIgnoreCase("Excel")) {
				filename = File.createTempFile(reportname, ".xls");
//				filename = new File(reportname+".xls");
//				System.out.println("1111========="+filename.getPath());
				JRXlsExporter exporter = new JRXlsExporter();

				exporter.setParameter(
						JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						new Boolean(false));
				exporter
						.setParameter(
								JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
								new Boolean(true));
				exporter.setParameter(
						JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
						new Boolean(false));
				exporter.setParameter(
						JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
						new Boolean(true));
				exporter
						.setParameter(JRExporterParameter.OUTPUT_FILE, filename);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						this.jasperPrint);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						this.jasperPrint);
				// exporter.setParameter(JRExporterParameter.PROGRESS_MONITOR,
				// this);
				exporter.exportReport();
			} else if (this.reporttype.equalsIgnoreCase("PDF")) {

				filename = File.createTempFile(reportname, ".PDF");
//				filename = new File(reportname+".PDF");
				JRPdfExporter exporter = new JRPdfExporter();
				exporter
						.setParameter(JRExporterParameter.OUTPUT_FILE, filename);

				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						this.jasperPrint);
				// exporter.setParameter(JRExporterParameter.PROGRESS_MONITOR,
				// this);
				exporter.exportReport();
			} else {
				// File tempFile = File.createTempFile("report", ".pdf");
				File tempFile = File.createTempFile(reportname, ".pdf");
//				File tempFile = new File(reportname+".pdf");
				JRPdfExporter exporter = new JRPdfExporter();
				exporter
						.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
				// exporter.setParameter(JRExporterParameter.OUTPUT_FILE,
				// filename);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,
						this.jasperPrint);
				exporter.exportReport();

				filename = compressFile(tempFile);
			}
			String sign = (String) params.get("Sign");
			String mailaddress = (String)params.get("Mailaddress");
			log.info("[sign]=" + sign);
			System.out.println("sign=======" + sign+"   "+"mailaddress====="+mailaddress);
			//***调用订阅报表功能***//
			sendMail(filename,sign,mailaddress);

//			processcounter.adjust((short) 80);
			FileInputStream stream = new FileInputStream(filename);
			reportData = new byte[stream.available()];
			stream.read(reportData);
			
//			processcounter.adjust((short) 90);
			filename.delete();
			stream.close();
			 filename.deleteOnExit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out
				.println("jasper ReportProcessor  exportReport runtime================"
						+ (end - start));
	}

	public void destroy() {
		super.destroy();
	}

	/**
	 * Create the ZIP file
	 * 
	 * @param file
	 * @return
	 */
	public File compressFile(File file) {
		String outFilename = file + ".zip";
		// Create a buffer for reading the files
		byte[] buf = null;
		try {
			// Create the ZIP file
			FileInputStream in = new FileInputStream(file);
			buf = new byte[in.available()];
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outFilename));

			// Compress the file
			// Add ZIP entry to output stream.
			out.putNextEntry(new ZipEntry(file.getName()));
			// Transfer bytes from the file to the ZIP file
			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			// Complete the entry
			out.closeEntry();
			in.close();
			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File zipFlie = new File(outFilename);
		long end = System.currentTimeMillis();
		System.out
				.println("jasp ReportProcessor compressFile runtime================"
						+ (end - start));
		log.info(" ReportProcessor compressFile runtime:" + (end - start));
		return zipFlie;
	}

}

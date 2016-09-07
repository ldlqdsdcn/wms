/**
 * @File: ReportCompiler.java
 */
package com.powere2e.reporttool.jasperprocessor;

import java.io.File;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.ReportMonitor;

/**
 * @author peter
 *
 */
public class ReportCompiler
{
	
	static long start = System.currentTimeMillis();
    private Logger log = Logger.getLogger(ReportProcessor.class.getName());
    
    private JasperReport jasperReport = null;
    private static String compileMessage = null;//Save the message of compile exception.
    static
    {    	
    	 System.setProperty("jasper.reports.compile.keep.java.file", "false");
        String classpath = JRProperties.getProperty(JRProperties.COMPILER_CLASSPATH);
        String[] classpaths =  classpath.split("jasperreports");
        //String ireport = classpaths[0] + "ireport.jar";
        
        StringBuffer newClasspath = new StringBuffer(classpath);
        try
        {
            File libFolder = new File(classpaths[0]); 
            if(libFolder.exists() && libFolder.isDirectory())
            {
                File[] libs = libFolder.listFiles();
                for(int i=0; libs != null && i<libs.length; i++)
                {
                    newClasspath.append(";").append(libs[i].getAbsolutePath());
                }
            }
        }
        catch (Exception e)
        {            
        }
        JRProperties.setProperty(JRProperties.COMPILER_CLASSPATH, newClasspath.toString()); 
        
        long end = System.currentTimeMillis();
		System.out.println("ReportCompiler  static runtime================"
				+ (end - start));
        
    }
    /**
     * Conctructor
     */
    public ReportCompiler()
    {       
    	
    }
    
    /**
     * Compile a report.
     * @param fileName
     */
    public String compileReport(String fileName)
    {
    	String msg="Y";
	  File xmlfile = new File(ReportMonitor.getReport_Home()
                    + System.getProperty("file.separator") + fileName);
    	if(xmlfile.exists() && xmlfile.isFile())
    	    compileReport(xmlfile);
        else
        {
            log.warn("cannot find the file-" + xmlfile.getAbsolutePath());
        msg="N";
        }
    	long end = System.currentTimeMillis();
		System.out.println("ReportCompiler  compileReport(fileName) runtime================"
				+ (end - start));
		return msg;
    }
    
    /**
     * Compile a *.xml / *.jrxml file to *.jasper file.
     * @param file
     */
    public void compileReport (File file)
    {
    	  //String target = file.getAbsolutePath().replaceAll(".xml", ".jasper").replaceAll(".jrxml", ".jasper");
        String target = file.getAbsolutePath().replaceAll(".jrxml", ".jasper").replaceAll(".xml", ".jasper");
        
        try
        {
            File targetfile = new File(target);
            if(!targetfile.exists() || targetfile.lastModified() < file.lastModified())
            {
                JasperCompileManager.compileReportToFile(file.getAbsolutePath(), target);
                targetfile = new File(target);
                targetfile.setLastModified(file.lastModified());                
            }
            
            jasperReport = (JasperReport)JRLoader.loadObject(targetfile);
            
            long end = System.currentTimeMillis();
    		System.out.println("ReportCompiler  compileReport(file) runtime================"
    				+ (end - start));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            log.error("compile error:" , e);
            compileMessage = e.getMessage();
        }
    }
    
    /**
     * Compile all reports.
     * @param file
     */
    public void compileAllReport(File file)
    {
    	
    	File parent = file;
		File child[]=parent.listFiles();
		for(int i=0;i<child.length;i++)
		{
			if(child[i].isFile())
			{
				if(child[i].getName().endsWith(".xml")||child[i].getName().endsWith(".XML"))
                compileReport(child[i]);				
			} 
    	} 
		long end = System.currentTimeMillis();
		System.out.println("ReportCompiler  compileAllReport(file) runtime================"
				+ (end - start));
    }

    /**
     * Get jasperReprt.
     * @return jasperReport
     */
    public JasperReport getJasperReport ()
    {
    	 return jasperReport;
    }

	/**
	 * Get the message of compile exception.
	 *
	 */
	public static String getCompileMessage() 
	{
		System.out.println("the compile messages is :"+compileMessage);
		return compileMessage ;
	}

	public static void cleanCompileMessage() {
		ReportCompiler.compileMessage = null;
	}
    
    

}

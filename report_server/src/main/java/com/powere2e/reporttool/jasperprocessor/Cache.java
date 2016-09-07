package com.powere2e.reporttool.jasperprocessor;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;

import com.powere2e.reporttool.model.ExcelModel;

/**
 * 
 * @author peter
 * 
 */
public class Cache
{
    private static Cache m_instance = new Cache();
    private HashMap container = new HashMap();
    //private int MAX_COUNT    = 15;
    private int MAX_COUNT = 45;
    private int count = 0;
 //   private Map reportDateMap=new HashMap();
    
    /**
     * Constructors
     */
    private Cache()
    {
        super();
    }
    /**
     * Get instance of Cache
     * @return m_instance the instance of Cache
     */
    public static Cache getInstance()
    {
        return m_instance;
    }
    
    /**
     * Get a container.
     * @return container which is instance of HashMap
     */
    public HashMap getContainer()
    {
    	return this.container;
    }
    /**
     * Get a report from HashMap
     * @param key
     * @return JasperReport
     */
    public JasperReport getReport(String key)
    {
        if(container.containsKey(key))
        {
            return (JasperReport)container.get(key);
        }
        return null;
    }
    
    /**
     * Load a report when the amount of report files in the cache less than MAX.
     * @param report
     */
    protected synchronized void loadReport(JasperReport report)
    {
        if(this.count >= MAX_COUNT)
            return;
        
        container.put(report.getName(), report);
        count++;
    }
    /**
     * Remove a report file in the Report Home.
     * @param key The key is the name of report file which is will be removed.
     */
    public synchronized void removeReport(String key)
    {
        if(container.containsKey(key))
        {
            container.remove(key);
            count--;
            System.out.println("清除缓存中的报表！！！！！！！！！");
        }
    }
    
    /**
     * Remove all the reports in the Report Home
     *
     */
    public synchronized void removeAllReports()
    {
        container.clear();
        count = 0;
    }
    
    public int getMAX_COUNT ()
    {
        return MAX_COUNT;
    }

    public void setMAX_COUNT (int max_count )
    {
        MAX_COUNT = max_count;
    }
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
    
    
 /*   public synchronized void addExcelDate(Map paramMap,ExcelModel excelModel)
    {
    	reportDateMap.put(paramMap, excelModel);
    }
    public synchronized void removeAllExcelDate()
    {
    	reportDateMap.clear();
    }
    
    public ExcelModel getExcelDate(Map paramMap)
    {
    	if(reportDateMap.containsKey(paramMap))
    		return (ExcelModel)reportDateMap.get(paramMap);
    	return null;
    }*/
    
    
}

/**
 * @File: ReportMonitor.java
 */
package com.powere2e.reporttool.datasource;

import java.util.Properties;

import com.powere2e.reporttool.datasource.impl.JDBCDataSource;
import com.powere2e.reporttool.datasource.impl.JNDIDataSource;

/**
 * @author peter
 *
 */
public class DataSourceProvider
{
    public static DataSourceProvider m_instance = new DataSourceProvider();
    
    private DataSource ds = null;
    private com.powere2e.reporttool.config.DataSource dataSource=null;
    private String connMessage = null;
    
    /**
     * Constructors
     */
    private DataSourceProvider()
    {
        super(); 
        com.powere2e.reporttool.config.DataSource[] dses=com.powere2e.reporttool.config.Config.getDatasources();
    	dataSource=dses[0];
    }
    
    /**
     * Get a instance of DataSource.
     * @return the instance of DataSourceProvider.
     */
    public static DataSourceProvider getInstance()
    {
    	
        return m_instance;
    }
    
    /**
     * Register a data source by the property in the properties file.
     * @param dataSource
     */
    public void registerDataSource(com.powere2e.reporttool.config.DataSource dataSource)
    {
    	if(ds != null)
    	{
    		//ds.closeDataSource();
    		ds = null;    		
    	}
    	
    	this.connMessage = null;
    	
        try
        {        	
        	if(dataSource.getType().equalsIgnoreCase("jdbc"))
            {
                ds = new JDBCDataSource(dataSource);
            }
            else if(dataSource.getType().equalsIgnoreCase("jndi"))
            {            	
                ds = new JNDIDataSource(dataSource);                
            }            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.connMessage = e.getMessage();
        }
    }
    /**
     * Get a specific date source.
     * @return the instance of the class which extends DataSource.
     */
    public DataSource getDataSource()
    {
        return ds;
    }

	public String getConnMessage() 
	{
		return connMessage;
	}

    
}

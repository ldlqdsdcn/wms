/**
 * @File DataSource.java
 */
package com.powere2e.reporttool.datasource.impl;

import java.util.Properties;

import com.powere2e.reporttool.datasource.DataSource;

/**
 * @author peter
 *
 */
public class EmptyDataSource implements DataSource
{
    public Properties properties = null;
    /**
     * Constructs a new datasource.
     */
    public EmptyDataSource()
    {
        super();        
    }
    
    /**
     * Constructs a new datasource. 
     * @param properties
     */
    public EmptyDataSource(Properties properties)
    {         
        this.properties = properties;
    }
    
    /* (non-Javadoc)
	 * @see com.powere2e.reporttool.datasourceImpl.DataSource#isJDBCConnection()
	 */
    public boolean isJDBCConnection()
    { 
        return false; 
    }
    
    
    /* (non-Javadoc)
	 * @see com.powere2e.reporttool.datasourceImpl.DataSource#getConnection()
	 */
    public java.sql.Connection getConnection()
    { 
        return null; 
    }
    /* (non-Javadoc)
	 * @see com.powere2e.reporttool.datasourceImpl.DataSource#getConnMessage()
	 */
    public String getConnMessage()
    {
    	return null;
    }
    
    public net.sf.jasperreports.engine.JRDataSource getJRDataSource() 
    { 
        return new net.sf.jasperreports.engine.JREmptyDataSource(); 
    }
    /* (non-Javadoc)
	 * @see com.powere2e.reporttool.datasourceImpl.DataSource#closeDataSource()
	 */
    public void closeDataSource(java.sql.Connection conn)
    {        
    }
}

/**
 * @File JNDIDataSource.java
 */
package com.powere2e.reporttool.datasource.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.datasource.DataSource;


/**
 * @author leo
 *
 */
public class JNDIDataSource implements DataSource
{
	private static javax.sql.DataSource dataSource = null;
	private String dataResource = null;
	private boolean connect=true;
	private Logger log = Logger.getLogger(JNDIDataSource.class.getName());
	
	/**
	 * Constructs a new JNDIDataSource.
	 * Get datasource from JNDI server.
	 * 
	 * @throws NamingException
	 */
    public JNDIDataSource(com.powere2e.reporttool.config.DataSource dataSourceParam) throws Exception
    {
    	dataResource = dataSourceParam.getJNDIName();
    	
    	Context initCtx = new InitialContext();
	    Context envCtx = (Context)initCtx.lookup("java:comp/env");
	    dataSource = (javax.sql.DataSource)envCtx.lookup(dataResource);	                
    	
	    // Test the connection
	    if (dataSource != null)
	    {
	    	Connection conn = dataSource.getConnection();
            conn.close();
	    }
	    else
	    {
	    	connect=false;
	    }
    }

    /**
     * Get the connection from connect pool.
     * Overrides DataSource.getConnection.
     * 
     * @return conn
     * @throws SQLException
     */
    public Connection getConnection()
    {
    	try
    	{
            return dataSource.getConnection();
    	}
    	catch(Exception e)
    	{    		
            log.error("getConnection", e);
    	}
        return null;
    }
    
    
    /**
     * Return true if DataSource is JDBCDataSource
     * Overrides DataSource.isJDBCConnection
     * 
     * @return true
     */
    public boolean isJDBCConnection()
    { 
        return true; 
    }
    
    /**
     * Close the connection
     * Overrides DataSource.closeDataSource
     */
    public void closeDataSource (Connection conn)
    {
        try
        {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

}

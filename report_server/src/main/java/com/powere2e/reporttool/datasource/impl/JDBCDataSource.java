/**
 * @File JDBCDataSource
 */
package com.powere2e.reporttool.datasource.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

import com.powere2e.reporttool.datasource.DataSource;


/**
 * @author peter
 *
 */
public class JDBCDataSource implements DataSource
{	
    private String driverclass = null; 
    private String url = null;
    private String userName = null;
    private String passWord = null;
    private boolean connect=true;
    //private Connection conn = null;
    
    private Logger log = Logger.getLogger(JDBCDataSource.class.getName());
    
    /**
     * Constructs a new JDBCDataSource.
     * 
     * @throws Exception
     */
    public JDBCDataSource() throws Exception
    {   
    }
    
    /**
     * Constructs a new JDBCDataSource with the attributes in the Pro.properties file.
     * 
     * @param properties
     * @throws Exception
     */
    public JDBCDataSource(com.powere2e.reporttool.config.DataSource dataSource)
    {  
    	try{
        driverclass = dataSource.getJdbcdriver();
        url=dataSource.getJdbcurl();
        userName=dataSource.getUsername();
        passWord=dataSource.getPassword();
        
        Class.forName(driverclass);
        
        // Test the connection
	   Connection conn = DriverManager.getConnection(url, userName, passWord);
	   conn.close();
    	}catch(Exception e)
    	{
    		connect=false;
    		log.error(e.fillInStackTrace());
    	}
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
    * Get a JDBCConnection.
    * Overrides DataSource.getConnection
    * 
    * @return conn
    */
    public Connection getConnection()
    {	
    	try
    	{
            return DriverManager.getConnection(url, userName, passWord);
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();    		
    	}
    	//System.out.println("connMessage is ----=:"+ getConnMessage());
    	return null;
    }
   
       
    /**
     * Close the JDBCDataSource
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

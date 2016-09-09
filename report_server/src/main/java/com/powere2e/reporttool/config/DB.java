/**
 * @File: DB.java 
 *
 */
package com.powere2e.reporttool.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DB
{
	public static String database_Home;
    private Connection conn;
    private static DB db = null;
    private DB()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:file:"+database_Home+"/mydb", "sa", "");  
        }
        catch (Exception e)
        {
            e.printStackTrace();            
        }      
    }
    
    public static DB getInstance()
    {
       ///if(db == null)
           return new DB();
      //return db;
    }
    public PreparedStatement getPreparedStatement(String sql)
    {
    	PreparedStatement pars=null;
    	try{
    	this.open();
    	pars=conn.prepareStatement(sql);
    	}
    	catch(Exception e)
    	{
    	e.printStackTrace();	
    	}
    	return pars;
    	
    }
    public boolean HasTable(String name) 
    {
    	//    	判断某一个表是否存在
        boolean result = false;
        try
        {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet set = meta.getTables(null, null, name.toUpperCase(), null);
            while (set.next())
            {
                result = true;
            }
        }
        catch (Exception eHasTable)
        {
            System.err.println(eHasTable);
            eHasTable.printStackTrace();
        }
        return result;
    }
    
    public List query (String expression ) throws SQLException
    {
        Statement st = null;
        ResultSet rs = null;
        this.open();
        st = conn.createStatement(); // statement objects can be reused with

        rs = st.executeQuery(expression); // run the query
         
        List result = new LinkedList();
        ResultSetMetaData meta   = rs.getMetaData();
        int colmax = meta.getColumnCount();
        
        while (rs.next()) 
        {
        	 HashMap mp = new HashMap();
            for (int i = 0; i < colmax; ++i) 
            {
               
                mp.put(meta.getColumnName(i + 1), rs.getObject(i + 1)); 
                
            }
            result.add(mp);
        }
        rs.close();
        st.close(); 
        
        return result;
    }

    // use for SQL commands CREATE, DROP, INSERT and UPDATE
    public void update (String expression )
    {
    	try{
        Statement st = null;
        this.open();
        st = conn.createStatement(); // statements
        
        int i = st.executeUpdate(expression); // run the query
        if (i == -1)
        {
            System.out.println("db error : " + expression);
        }
        st.close();
        conn.commit();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    } // void update()

    protected void finalize () throws Throwable
    {
        db.update("SHUTDOWN;");
        conn.close();
        super.finalize();
    }
    
    public void close() throws SQLException
    {
    	if(!conn.isClosed())
    	{
        conn.commit();
        conn.close();
    	}
    }
    
    public void open() throws SQLException
    {
        if(conn != null && !conn.isClosed())
            return;
        
        conn = DriverManager.getConnection("jdbc:hsqldb:file:"+database_Home+"\\mydb", "sa", "");  
    }
}

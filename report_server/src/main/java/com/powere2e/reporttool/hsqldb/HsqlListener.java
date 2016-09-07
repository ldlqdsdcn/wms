package com.powere2e.reporttool.hsqldb;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.Server;
import java.sql.*;
public class HsqlListener implements ServletContextListener{
 
 private String url;
 

 public void contextDestroyed(ServletContextEvent arg0) {
  try {  
            Class.forName("org.hsqldb.jdbcDriver");  
 
            Connection conn = null;  
            Statement state = null;  
 
            try {

                conn = DriverManager.getConnection(url, "sa", "");
                state = conn.createStatement();  
                state.executeUpdate("SHUTDOWN;");  
            } catch (SQLException ex1) {  
                System.err.println("服务器关闭异常" + ex1);
            } finally {  

                if (state != null) {  
                    try {  
                        state.close();  
                          } catch (SQLException ex1) {
                        System.err.println(ex1);  
                    }  
                }  
                if (conn != null) {
                    try {  
                        conn.close();  
                        conn = null;  
                    } catch (SQLException ex1) {  
                        System.err.println(ex1);  
                    }  
                }  
            }  
        } catch (ClassNotFoundException ex) {  
            System.err.println(  
                "HsqldbListener : contextDestoryed : error : " + ex);  
        }
  
 }

 public void contextInitialized(ServletContextEvent sce) {
  String dbpath =sce.getServletContext().getInitParameter("dbpath");
  String dbport =sce.getServletContext().getInitParameter("dbport");
  String dbname =sce.getServletContext().getInitParameter("dbname");
  
  url="jdbc:hsqldb:hsql://localhost:" + dbport + "/"+dbname;
  String conpath = sce.getServletContext().getRealPath("/");
  String databasePath = conpath + "/"+ dbpath + "/" + dbname;
  
  Server server = new Server();
  server.setDatabaseName(0, dbname);
  server.setDatabasePath(0, databasePath);
  server.setPort(new Integer(dbport).intValue());
  server.setSilent(true);
  server.start();
  try {
   Thread.sleep(1000);
  } catch (Exception e) {
   e.printStackTrace();
  }  
 }

}


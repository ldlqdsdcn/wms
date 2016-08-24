package com.delmar.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.delmar.utils.StringUtil;

import java.sql.*;

public class DBUtil {
	
	// 数据库ProLimsSH
	private static DruidDataSource cpdsProLimsSH = null;
	private static DBUtil dbconn = null;
	
	public static DBUtil getInstance(){
		if (dbconn == null){
			dbconn = new DBUtil();
			return dbconn;
		}
		return dbconn;
	}

	/**
	 * 得到主键
	 * @return
	 */
	public String createId(String TableTag) {
		String newId = "";
    	Connection conn = getConnectionProLimsSH();
		PreparedStatement stm = null;
		PreparedStatement stmUpdate = null;
		ResultSet rs = null;
		
		if (StringUtil.isNotEmpty(TableTag)){
			try {
				
				stm = conn.prepareStatement("select maxValue from syscreateid where name = ?");
				stm.setString(1, TableTag);
				rs = stm.executeQuery();
				
				if(rs.next()){
					long createID = Long.parseLong(rs.getString("maxValue")) + 1;
					newId = String.valueOf(createID);
					stmUpdate = conn.prepareStatement("update syscreateid set maxValue = ? where name = ?;");
					stmUpdate.setString(1, newId);
					stmUpdate.setString(2, TableTag);
					stmUpdate.executeUpdate();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try {
					conn.close();
					rs.close();
					stm.close();
					stmUpdate.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return newId;
	}
	
	/**
	 * 数据库连接：EBusiness
	 * @return
	 */
	public Connection getConnectionProLimsSH() {
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		 String url = "jdbc:jtds:sqlserver://172.20.7.250:1433;databaseName=ProLimsSH";
	    String username = "sa";
	    String password = "delmarchina1~5";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}

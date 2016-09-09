package com.powere2e.reporttool.datasource;
/**
 * @author peter
 *
 */
public interface DataSource 
{
	/**
	 * Test whether the datasoure is JDBCDataSource.
	 * 
	 * @return false
	 */
	 boolean isJDBCConnection();

	/**
	 * Get a connection.
	 * @return null
	 */
	 java.sql.Connection getConnection();

	/**
	 * close the data source.
	 *
	 */
	 void closeDataSource(java.sql.Connection conn);

}
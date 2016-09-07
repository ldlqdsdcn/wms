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
	public abstract boolean isJDBCConnection();

	/**
	 * Get a connection.
	 * @return null
	 */
	public abstract java.sql.Connection getConnection();

	/**
	 * close the data source.
	 *
	 */
	public abstract void closeDataSource(java.sql.Connection conn);

}
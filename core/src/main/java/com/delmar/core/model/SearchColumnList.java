/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.model;

import java.util.ArrayList;
import java.util.List;

import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月15日 下午3:45:18
 */
public class SearchColumnList {
	//SELECT CONVERT(char(10), getdate(), 120)  
	//SELECT CONVERT(char(19), getdate(), 120)
	// MYSQL SQLSERVER ORACLE
private static final String DATABASE_TYPE="SQLSERVER";

private static final String DATABASE_MYSQL="MYSQL";
private static final String DATABASE_SQLSERVER="SQLSERVER";
/*輸出格式：2008-02-27 00:25:13

SELECT CONVERT(char(19), getdate(), 120)
輸出格式：2008-02-27

SELECT CONVERT(char(10), getdate(), 12)
輸出格式：2008.02.27

SELECT CONVERT(char(10), getdate(), 102)
輸出格式：08.02.27

SELECT CONVERT(char(8), getdate(), 2)
輸出格式：2008/02/27

SELECT CONVERT(char(10), getdate(), 111)
輸出格式：08/02/27*/
private List<SearchColumnValue> columnValueList;
	
	public SearchColumnList()
	{
		columnValueList= new ArrayList<>();
	}
	public SearchColumnList(List<SearchColumnValue> columnList)
	{
		this.columnValueList=columnList;
	}
	public void addColumnValue(SearchColumnValue columnValue)
	{
		columnValueList.add(columnValue);
	}
	public void removeColumn(int index)
	{
		columnValueList.remove(index);
	}
	public List<SearchColumnValue> getColumnValueList() {
		return columnValueList;
	}
	public String buildSql()
	{
		StringBuilder sb=new StringBuilder("");
		if(columnValueList.size()==0)return null;
		for(int i=0;i<columnValueList.size();i++)
		{
			SearchColumnValue scv=columnValueList.get(i);
			if(i>0)
			{
				sb.append(" ").append(scv.getLink()).append(" ");
			}
			int datatype=scv.getSearchColumn().getDataType();
			if(StringUtil.isNotEmpty(scv.getSearchColumn().getRemark()))
			{
				String sbColumn=scv.getSearchColumn().getColumnName()+" "+scv.getRelOper();
				if(datatype==2)
				{
					sbColumn=sbColumn+scv.getValue();
					//sb.append();
				}
				if(datatype==1)
				{
					sbColumn=sbColumn+"'"+scv.getValue()+"'";
				}
				
				
				sb.append(" ").append(scv.getSearchColumn().getRemark().replace("?", sbColumn)).append(" ");
			}
			else
			{
				
				
				if(datatype==3)
				{
					if(DATABASE_MYSQL.equals(DATABASE_TYPE))
					{
						sb.append("to_days(");
					}
					else if(DATABASE_SQLSERVER.equals(DATABASE_TYPE))
					{
						sb.append("SELECT CONVERT(char(10), ");
					}
					
				}
				
					sb.append(scv.getSearchColumn().getColumnName());
					if(datatype==3)
					{
						
						if(DATABASE_MYSQL.equals(DATABASE_TYPE))
						{
							sb.append(")");
						}
						else if(DATABASE_SQLSERVER.equals(DATABASE_TYPE))
						{
							sb.append(", 120) ");
						}

					}
					sb.append(" ");
				sb.append(scv.getRelOper());
				
				if(datatype==2)
				{
					sb.append(scv.getValue());
				}
				if(datatype==1)
				{
					sb.append("'").append(scv.getValue()).append("'");
					
				}
				if(datatype==3)
				{
					if(DATABASE_MYSQL.equals(DATABASE_TYPE))
					{
						sb.append("to_days('").append(scv.getValue()).append("')");
					}
					else if(DATABASE_SQLSERVER.equals(DATABASE_TYPE))
					{
						sb.append("'");
						sb.append(scv.getValue());
						sb.append("'");
					}
					
					
				}
				sb.append(" ");
			}
			
			
		}
		
		return sb.toString();
	}
}

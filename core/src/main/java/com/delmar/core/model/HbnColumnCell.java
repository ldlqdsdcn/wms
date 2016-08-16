package com.delmar.core.model;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 下午6:54:34 
 * 类说明 
 */
public class HbnColumnCell {
	
	private String column;//字段名
	private String columnName; //值
	
	public HbnColumnCell() {
		
	}
	
	public HbnColumnCell(String column,
			             String columnName) {
		this.column=column;
		this.columnName=columnName;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}	
	
	
	public String createColumn()
	{
		if (columnName.equals(""))
		  return column+",";
		else
		  return column+" as "+columnName+",";
	}
		
	

}

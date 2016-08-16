package com.delmar.core.model;

import java.util.List;

/**
 * @author 刘大磊 2015年3月16日 下午3:08:45
 */
public class SearchColumn extends CoreModel {
	public static final int DATATYPE_INT=1;
	public static final int DATATYPE_STRING=2;
	public static final int DATATYPE_DATE=3;
	
	/**
	 *大于 
	 */
	public static final String GreaterThan="1";
	
	/**
	 * 大于等于
	 */
	public static final String Greaterthanorequalto="2";
	
	/**
	 * 小于
	 */
	public static final String LessThan="3";	
	/**
	 * 小于等于
	 */
	public static final String LessThanorequalto="4";
	
	/**
	 * 等于
	 */
	public static final String Equal="5";
	
	/**
	 * showType
	 */
	/**
	 * 基本输入
	 */
	public static final int SHOW_TYPE_TEXT=1;
	
	/**
	 * 日期选择
	 */
	public static final int SHOW_TYPE_DATE=2;
	
	/**
	 * 下拉选择框
	 */
	public static final int SHOW_TYPE_SELECT=3;
	
	/**
	 * google autocomplish 文本框
	 */
	public static final int SHOW_TYPE_AUTOCOMPLISH=4;
	
    private Integer searchId;

    private String columnName;

    private Integer dataType;

    private Integer showType;

    private String relOper;

    private String tableName;

    private String value;

    private String name;

    private String coditions;

    private String remark;

    private String columnShowName;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getRelOper() {
        return relOper;
    }

    public void setRelOper(String relOper) {
        this.relOper = relOper == null ? null : relOper.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCoditions() {
        return coditions;
    }

    public void setCoditions(String coditions) {
        this.coditions = coditions == null ? null : coditions.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getColumnShowName() {
        return columnShowName;
    }

    public void setColumnShowName(String columnShowName) {
        this.columnShowName = columnShowName == null ? null : columnShowName.trim();
    }

	/**
	 * @return the relOperList
	 */
	public String[]  getRelOperList() {
		if(this.relOper!=null)
		{
			String[] relOperList=this.relOper.split(",");
			return relOperList;
		}
		return null;
	}

	/**
	 * @param relOperList the relOperList to set
	 */
	public void setRelOperList(String[] relOperList) {
		StringBuilder sb=new StringBuilder();
		boolean isfirst=true;
		if(relOperList!=null)
		{
			for(String s:relOperList)
			{
				if(isfirst)
				{
					isfirst=false;
					
				}
				else
				{
					sb.append(",");
				}
				sb.append(s);
			}
		}
		this.relOper=sb.toString();
	}
    
}
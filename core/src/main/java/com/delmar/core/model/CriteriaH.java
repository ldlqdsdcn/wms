package com.delmar.core.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Hibernate公共查询，队列
 * @author tcldl
 *
 */
public class CriteriaH {
	private List<FieldH> fields=new ArrayList<FieldH>();
	private Integer strrow=-1;
	
	private Integer rows=-1;
	
	private Class className;
	
	public CriteriaH()
	{
		
	}
	public CriteriaH(Class className)
	{
		this.className=className;
	}
	public CriteriaH(Class className,Integer strrow,Integer rows)
	{
		this.className=className;
		this.strrow=strrow;
		this.rows=rows;
	}
	
	public void addField(FieldH fieldH)
	{
		fields.add(fieldH);
	}
	public void addField(String fieldName,String operator,Object value)
	{
		fields.add(new FieldH(fieldName,operator,value));
	}

	public List<FieldH> getFields() {
		return fields;
	}

	public void setFields(List<FieldH> fields) {
		this.fields = fields;
	}

	public Integer getStrrow() {
		return strrow;
	}

	public void setStrrow(Integer strrow) {
		this.strrow = strrow;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}
}

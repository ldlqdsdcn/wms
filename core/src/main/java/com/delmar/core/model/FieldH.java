package com.delmar.core.model;
/**
 * Hibernate 公共查询字段
 * @author tcldl
 *
 */
public class FieldH {
	private String fieldName;
	private String operator;
	private Object value;
	
	public FieldH(String fieldName,String operator,Object value)
	{
		this.fieldName=fieldName;
		this.operator=operator;
		this.value=value;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}

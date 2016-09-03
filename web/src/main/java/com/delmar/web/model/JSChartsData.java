package com.delmar.web.model;

/**
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月19日 下午6:19:40 
 * 类说明 
 */
public class JSChartsData {

	private String unit;
	private String value;
	
	public JSChartsData()
	{
		
	}
	
	public JSChartsData(String unit,String value)
	{
		this.unit=unit;
		this.value=value;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

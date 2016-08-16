package com.delmar.web.model;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月19日 下午6:21:19 
 * 类说明 
 */
public class JSChartsOption {
	
	private String set;
	private String value;
	
	public JSChartsOption()
	{
		
	}
	
	public JSChartsOption(String set,String value)
	{
		this.set=set;
		this.value=value;
	}

	
	
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}

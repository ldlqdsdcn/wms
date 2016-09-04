package com.delmar.web.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月19日 下午6:23:32 
 * 类说明 
 */
public class JSChartsItem {
   
	  private String type;
	  private String id;
	  private List<JSChartsData> data;
	  
	  
	  public JSChartsItem()
	  {
		  data=new ArrayList<JSChartsData>();  
	  }
	  
	  public JSChartsItem(String type,String id)
	  {
		  this.type=type;
		  this.id=id;
		  data=new ArrayList<JSChartsData>();
	  }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<JSChartsData> getData() {
		return data;
	}


	public void setData(List<JSChartsData> data) {
		this.data = data;
	}
	  
	public void addOneData(String unit,String value)
	{
		data.add(new JSChartsData(unit,value));	
	}
	
	public void addOneData(String unit,BigDecimal value)
	{
		data.add(new JSChartsData(unit,value.toString()));	
	}
	
	public void addOneData(String unit,BigInteger value)
	{
		data.add(new JSChartsData(unit,value.toString()));	
	}
	
	public void addOneData(String unit,Integer value)
	{
		data.add(new JSChartsData(unit,value.toString()));	
	}	
	
	
	  
}

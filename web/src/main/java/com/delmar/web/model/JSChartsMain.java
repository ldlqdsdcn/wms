package com.delmar.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月19日 下午6:17:15 
 * 类说明 
 */
public class JSChartsMain {
    
	private List<JSChartsItem> datasets;
	private List<JSChartsOption> optionset;
	
	
	public JSChartsMain()
	{
		datasets=new ArrayList<JSChartsItem>();
		optionset=new ArrayList<JSChartsOption>();
		
	}


	public List<JSChartsItem> getDatasets() {
		return datasets;
	}


	public void setDatasets(List<JSChartsItem> datasets) {
		this.datasets = datasets;
	}


	public List<JSChartsOption> getOptionset() {
		return optionset;
	}


	public void setOptionset(List<JSChartsOption> optionset) {
		this.optionset = optionset;
	}
	

	
	
	

}

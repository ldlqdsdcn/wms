package com.delmar.cargo.model;

import java.math.BigDecimal;

import com.delmar.utils.ResourceMessage;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月27日 上午11:24:34 
 * 类说明 
 */
public class BusinessPerformance {
	
	private String planeOcean;
	private String perforType;
	private String code;
	private Integer outIn;
	private String companyId;
	private String name;
	private Integer inumber;
	private BigDecimal profit;
	private String iYear;
	private String imonth;
	
	private String outInTrl;
	private String planeOceanTrl;

	public String getPlaneOcean() {
		if (planeOcean==null)
			planeOcean="";
		return planeOcean;
	}
	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}
	public String getPerforType() {
		return perforType;
	}
	public void setPerforType(String perforType) {
		this.perforType = perforType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getOutIn() {
		if (outIn==null)
			outIn=new Integer(-1);		
		return outIn;
	}
	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
	public Integer getInumber() {
		return inumber;
	}
	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}
	public String getImonth() {
		return imonth;
	}
	public void setImonth(String imonth) {
		this.imonth = imonth;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getiYear() {
		return iYear;
	}
	public void setiYear(String iYear) {
		this.iYear = iYear;
	}
	public String getOutInTrl() {
		if (outIn.intValue()==0)  //出口
			outInTrl=ResourceMessage.getMessage("business.shipment.export");
		else
			outInTrl=ResourceMessage.getMessage("business.shipment.import");		
		return outInTrl;
	}
	public void setOutInTrl(String outInTrl) {
		this.outInTrl = outInTrl;
	}
	public String getPlaneOceanTrl() {
		if (planeOcean.equalsIgnoreCase("Ocean")) 
			planeOceanTrl=ResourceMessage.getMessage("business.shipment.ocean");
		else if (planeOcean.equalsIgnoreCase("Air")) 
			planeOceanTrl=ResourceMessage.getMessage("business.shipment.air");
		else if (planeOcean.equalsIgnoreCase("Other"))  
			planeOceanTrl=ResourceMessage.getMessage("business.shipment.other");
		else
			planeOceanTrl="";		
		return planeOceanTrl;
	}
	public void setPlaneOceanTrl(String planeOceanTrl) {
		this.planeOceanTrl = planeOceanTrl;
	}

	
	
	

}

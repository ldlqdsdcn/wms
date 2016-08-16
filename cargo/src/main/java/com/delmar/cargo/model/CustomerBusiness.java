package com.delmar.cargo.model;

import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ResourceMessage;


/**
 *@ClassName:   CustomerBusiness.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月26日 下午9:03:15
 * @version V2.0
 */
public class CustomerBusiness implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cusCode;

	private String cusType;
	private String planeOcean;
	private String fcllcl;
	private Integer outIn;
	private String relatedCusCode;
	private String trustType;
	private String firstEtd;
	protected String lastEtd;
	private String airPortFrom;
	private String airPortTo;
	private String salesCode;
	private String salesName;
	private String companyId;
	private String remark;
	private String operator;
	private String operatorName;
	private String createDate;
	private String arrivePort;
	private String shipperRela;
	private String consignRela;
	
	
	private String cusName;
	
	private Integer dayToNow;
	private String outInTrl;
	private String planeOceanTrl;
	

	// Constructors

	/** default constructor */
	public CustomerBusiness() {
	}

	/** minimal constructor */
	public CustomerBusiness(Integer id) {
		this.id = id;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCusCode() {
		return this.cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusType() {
		return this.cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getPlaneOcean() {
		return this.planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public String getFcllcl() {
		return this.fcllcl;
	}

	public void setFcllcl(String fcllcl) {
		this.fcllcl = fcllcl;
	}

	public Integer getOutIn() {
		return this.outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public String getRelatedCusCode() {
		return this.relatedCusCode;
	}

	public void setRelatedCusCode(String relatedCusCode) {
		this.relatedCusCode = relatedCusCode;
	}

	public String getTrustType() {
		return this.trustType;
	}

	public void setTrustType(String trustType) {
		this.trustType = trustType;
	}

	public String getFirstEtd() {
		return this.firstEtd;
	}

	public void setFirstEtd(String firstEtd) {
		this.firstEtd = firstEtd;
	}

	public String getLastEtd() {
		return this.lastEtd;
	}

	public void setLastEtd(String lastEtd) {
		this.lastEtd = lastEtd;
	}

	public String getAirPortFrom() {
		return this.airPortFrom;
	}

	public void setAirPortFrom(String airPortFrom) {
		this.airPortFrom = airPortFrom;
	}

	public String getAirPortTo() {
		return this.airPortTo;
	}

	public void setAirPortTo(String airPortTo) {
		this.airPortTo = airPortTo;
	}

	public String getSalesCode() {
		return this.salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getArrivePort() {
		return this.arrivePort;
	}

	public void setArrivePort(String arrivePort) {
		this.arrivePort = arrivePort;
	}

	public String getShipperRela() {
		return this.shipperRela;
	}

	public void setShipperRela(String shipperRela) {
		this.shipperRela = shipperRela;
	}

	public String getConsignRela() {
		return this.consignRela;
	}

	public void setConsignRela(String consignRela) {
		this.consignRela = consignRela;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public Integer getDayToNow() {
		dayToNow=new Integer(String.valueOf(ProDateUtil.getDateDiffDay(lastEtd)));
		return dayToNow;
	}

	public void setDayToNow(Integer dayToNow) {
		this.dayToNow = dayToNow;
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

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	
	


}
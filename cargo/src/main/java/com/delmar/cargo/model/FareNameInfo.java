package com.delmar.cargo.model;

/**
 * FareNameInfo entity. @author MyEclipse Persistence Tools
 */

public class FareNameInfo implements java.io.Serializable {

	// Fields

	private String fareCode;
	private String fareName;
	private String fareCname;
	private String fareType;
	private Double vatrate;
	private String remark;
	private String outIn;
	private String planeOcean;
	private String standBy1;
	private String beUse;
	private String standBy2;
	private String standBy3;

	// Constructors

	/** default constructor */
	public FareNameInfo() {
	}

	/** minimal constructor */
	public FareNameInfo(String fareCode) {
		this.fareCode = fareCode;
	}


	// Property accessors

	public String getFareCode() {
		return this.fareCode;
	}

	public void setFareCode(String fareCode) {
		this.fareCode = fareCode;
	}

	public String getFareName() {
		return this.fareName;
	}

	public void setFareName(String fareName) {
		this.fareName = fareName;
	}

	public String getFareCname() {
		return this.fareCname;
	}

	public void setFareCname(String fareCname) {
		this.fareCname = fareCname;
	}

	public String getFareType() {
		return this.fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public Double getVatrate() {
		return this.vatrate;
	}

	public void setVatrate(Double vatrate) {
		this.vatrate = vatrate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOutIn() {
		return this.outIn;
	}

	public void setOutIn(String outIn) {
		this.outIn = outIn;
	}

	public String getPlaneOcean() {
		return this.planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public String getStandBy1() {
		return this.standBy1;
	}

	public void setStandBy1(String standBy1) {
		this.standBy1 = standBy1;
	}

	public String getBeUse() {
		return this.beUse;
	}

	public void setBeUse(String beUse) {
		this.beUse = beUse;
	}

	public String getStandBy2() {
		return this.standBy2;
	}

	public void setStandBy2(String standBy2) {
		this.standBy2 = standBy2;
	}

	public String getStandBy3() {
		return this.standBy3;
	}

	public void setStandBy3(String standBy3) {
		this.standBy3 = standBy3;
	}

}
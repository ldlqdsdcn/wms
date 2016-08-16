package com.delmar.cargo.model;

/**
 * PortInfo entity. @author MyEclipse Persistence Tools
 */

public class PortInfo implements java.io.Serializable {

	// Fields

	private String portCode;
	private String portName;
	private String portCname;
	private String countryCode;
	private String remark;
	private String mark;
	private String planeOcean;
	private String area;
	private String oceanPortStation;
	private String airPortStation;
	private String standCity;
	private Integer beUse;
	private String dcmsId;

	// Constructors

	/** default constructor */
	public PortInfo() {
	}

	/** minimal constructor */
	public PortInfo(String portCode, String countryCode) {
		this.portCode = portCode;
		this.countryCode = countryCode;
	}

	/** full constructor */
	public PortInfo(String portCode, String portName, String portCname,
			String countryCode, String remark, String mark, String planeOcean,
			String area, String oceanPortStation, String airPortStation,
			String standCity, Integer beUse, String dcmsId) {
		this.portCode = portCode;
		this.portName = portName;
		this.portCname = portCname;
		this.countryCode = countryCode;
		this.remark = remark;
		this.mark = mark;
		this.planeOcean = planeOcean;
		this.area = area;
		this.oceanPortStation = oceanPortStation;
		this.airPortStation = airPortStation;
		this.standCity = standCity;
		this.beUse = beUse;
		this.dcmsId = dcmsId;
	}

	// Property accessors

	public String getPortCode() {
		return this.portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortCname() {
		return this.portCname;
	}

	public void setPortCname(String portCname) {
		this.portCname = portCname;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPlaneOcean() {
		return this.planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOceanPortStation() {
		return this.oceanPortStation;
	}

	public void setOceanPortStation(String oceanPortStation) {
		this.oceanPortStation = oceanPortStation;
	}

	public String getAirPortStation() {
		return this.airPortStation;
	}

	public void setAirPortStation(String airPortStation) {
		this.airPortStation = airPortStation;
	}

	public String getStandCity() {
		return this.standCity;
	}

	public void setStandCity(String standCity) {
		this.standCity = standCity;
	}

	public Integer getBeUse() {
		return this.beUse;
	}

	public void setBeUse(Integer beUse) {
		this.beUse = beUse;
	}

	public String getDcmsId() {
		return this.dcmsId;
	}

	public void setDcmsId(String dcmsId) {
		this.dcmsId = dcmsId;
	}

}
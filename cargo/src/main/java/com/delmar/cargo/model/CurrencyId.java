package com.delmar.cargo.model;

/**
 * CurrencyId entity. @author MyEclipse Persistence Tools
 */

public class CurrencyId implements java.io.Serializable {

	// Fields

	private String code;
	private String areaId;

	// Constructors

	/** default constructor */
	public CurrencyId() {
	}



	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}



}
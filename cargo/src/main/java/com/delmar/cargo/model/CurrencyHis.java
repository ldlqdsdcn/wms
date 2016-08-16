package com.delmar.cargo.model;

/**
 * CurrencyHis entity. @author MyEclipse Persistence Tools
 */

public class CurrencyHis implements java.io.Serializable {

	// Fields

	private CurrencyHisId id;
	
	private Double rate; 

	// Constructors

	/** default constructor */
	public CurrencyHis() {
	}

	/** full constructor */
	public CurrencyHis(CurrencyHisId id) {
		this.id = id;
	}

	// Property accessors

	public CurrencyHisId getId() {
		return this.id;
	}

	public void setId(CurrencyHisId id) {
		this.id = id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	

}
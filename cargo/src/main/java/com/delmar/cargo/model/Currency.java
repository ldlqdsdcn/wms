package com.delmar.cargo.model;

/**
 * Currency entity. @author MyEclipse Persistence Tools
 */

public class Currency implements java.io.Serializable {

	// Fields

	private CurrencyId id;
	private Double selling;
	private Double buying;
	private String name;
	private String cname;
	private Double rate;
	private String doDate;
	private String remark;
	private Integer beUse;
	private Integer beOnlyCalc;

	// Constructors

	/** default constructor */
	public Currency() {
	}

	/** minimal constructor */
	public Currency(CurrencyId id) {
		this.id = id;
	}


	// Property accessors

	public CurrencyId getId() {
		return this.id;
	}

	public void setId(CurrencyId id) {
		this.id = id;
	}

	public Double getSelling() {
		return this.selling;
	}

	public void setSelling(Double selling) {
		this.selling = selling;
	}

	public Double getBuying() {
		return this.buying;
	}

	public void setBuying(Double buying) {
		this.buying = buying;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getDoDate() {
		return this.doDate;
	}

	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBeUse() {
		return this.beUse;
	}

	public void setBeUse(Integer beUse) {
		this.beUse = beUse;
	}

	public Integer getBeOnlyCalc() {
		return this.beOnlyCalc;
	}

	public void setBeOnlyCalc(Integer beOnlyCalc) {
		this.beOnlyCalc = beOnlyCalc;
	}

}
package com.delmar.cargo.model;

/**
 * CurrencyHisId entity. @author MyEclipse Persistence Tools
 */

public class CurrencyHisId implements java.io.Serializable {

	// Fields

	private String startDate;
	private String endDate;
	private String code;
	private String areaId;

	// Constructors

	/** default constructor */
	public CurrencyHisId() {
	}

	/** minimal constructor */
	public CurrencyHisId(String code) {
		this.code = code;
	}


	// Property accessors

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CurrencyHisId)) return false;

		CurrencyHisId that = (CurrencyHisId) o;

		if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
		if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
		if (code != null ? !code.equals(that.code) : that.code != null) return false;
		return areaId != null ? areaId.equals(that.areaId) : that.areaId == null;

	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStartDate() == null ? 0 : this.getStartDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		result = 37 * result
				+ (getAreaId() == null ? 0 : this.getAreaId().hashCode());
		return result;
	}

}
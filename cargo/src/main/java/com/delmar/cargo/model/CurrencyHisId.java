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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CurrencyHisId))
			return false;
		CurrencyHisId castOther = (CurrencyHisId) other;

		return ((this.getStartDate() == castOther.getStartDate()) || (this
				.getStartDate() != null && castOther.getStartDate() != null && this
				.getStartDate().equals(castOther.getStartDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getCode() == castOther.getCode()) || (this.getCode() != null
						&& castOther.getCode() != null && this.getCode()
						.equals(castOther.getCode())))
				&& ((this.getAreaId() == castOther.getAreaId()) || (this
						.getAreaId() != null && castOther.getAreaId() != null && this
						.getAreaId().equals(castOther.getAreaId())));
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
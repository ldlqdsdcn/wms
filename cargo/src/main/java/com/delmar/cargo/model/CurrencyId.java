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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CurrencyId))
			return false;
		CurrencyId castOther = (CurrencyId) other;

		return ((this.getCode() == castOther.getCode()) || (this.getCode() != null
				&& castOther.getCode() != null && this.getCode().equals(
				castOther.getCode())))
				&& ((this.getAreaId() == castOther.getAreaId()) || (this
						.getAreaId() != null && castOther.getAreaId() != null && this
						.getAreaId().equals(castOther.getAreaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		result = 37 * result
				+ (getAreaId() == null ? 0 : this.getAreaId().hashCode());
		return result;
	}

}
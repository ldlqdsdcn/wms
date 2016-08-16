package com.delmar.cargo.model;

import com.delmar.core.model.CoreModel;

/**
 * CityInfo entity. @author MyEclipse Persistence Tools
 */

public class CityInfo extends CoreModel implements java.io.Serializable {

	// Fields

	private String code;
	private String name;
	private String cname;
	private String remark;
	private String nameType;
	private String beUse;
	private String remark2;

	// Constructors

	/** default constructor */
	public CityInfo() {
	}

	/** minimal constructor */
	public CityInfo(Integer id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public CityInfo(Integer id, String code, String name, String cname,
			String remark, String nameType, String beUse, String remark2) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.cname = cname;
		this.remark = remark;
		this.nameType = nameType;
		this.beUse = beUse;
		this.remark2 = remark2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNameType() {
		return this.nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getBeUse() {
		return this.beUse;
	}

	public void setBeUse(String beUse) {
		this.beUse = beUse;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

}
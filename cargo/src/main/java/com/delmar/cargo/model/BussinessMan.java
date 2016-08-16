package com.delmar.cargo.model;

/**
 * BussinessMan entity. @author MyEclipse Persistence Tools
 */

public class BussinessMan implements java.io.Serializable {

	// Fields

	private String code;
	private String companyId;
	private String companyGroup;
	private String name;
	private String area;
	private String type;
	private String dept;
	private String remark;
	private Integer beUse;
	private String email;
	private String responseTo;
	private String telephone;
	private String mobileNo;
	private String jobDate;
	private Integer sydays;
	private String relatedUser;

	// Constructors

	/** default constructor */
	public BussinessMan() {
	}

	/** minimal constructor */
	public BussinessMan(String code, String companyGroup) {
		this.code = code;
		this.companyGroup = companyGroup;
	}

	/** full constructor */
	public BussinessMan(String code, String companyId, String companyGroup,
			String name, String area, String type, String dept, String remark,
			Integer beUse, String email, String responseTo, String telephone,
			String mobileNo, String jobDate, Integer sydays, String relatedUser) {
		this.code = code;
		this.companyId = companyId;
		this.companyGroup = companyGroup;
		this.name = name;
		this.area = area;
		this.type = type;
		this.dept = dept;
		this.remark = remark;
		this.beUse = beUse;
		this.email = email;
		this.responseTo = responseTo;
		this.telephone = telephone;
		this.mobileNo = mobileNo;
		this.jobDate = jobDate;
		this.sydays = sydays;
		this.relatedUser = relatedUser;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyGroup() {
		return this.companyGroup;
	}

	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResponseTo() {
		return this.responseTo;
	}

	public void setResponseTo(String responseTo) {
		this.responseTo = responseTo;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getJobDate() {
		return this.jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public Integer getSydays() {
		return this.sydays;
	}

	public void setSydays(Integer sydays) {
		this.sydays = sydays;
	}

	public String getRelatedUser() {
		return this.relatedUser;
	}

	public void setRelatedUser(String relatedUser) {
		this.relatedUser = relatedUser;
	}

}
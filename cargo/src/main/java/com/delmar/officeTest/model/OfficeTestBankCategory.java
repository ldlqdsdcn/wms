package com.delmar.officeTest.model;
// default package

import java.util.HashSet;
import java.util.Set;

import com.delmar.common.model.BusCoreModel;
import com.delmar.core.model.CoreModel;

/**
 * OfficeTestBankCategory entity. @author MyEclipse Persistence Tools
 */

public class OfficeTestBankCategory extends CoreModel {

	// Fields

	private String code;
	private String name;
	private String remark;
	private Integer testBankId;

	// Constructors

	/** default constructor */
	public OfficeTestBankCategory() {
	}

	/** minimal constructor */
	public OfficeTestBankCategory(Integer id, OfficeTestBank officeTestBank) {
		this.id = id;
	}

	/** full constructor */
	public OfficeTestBankCategory(Integer id, 
			String code, String name, String remark) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTestBankId() {
		return testBankId;
	}

	public void setTestBankId(Integer testBankId) {
		this.testBankId = testBankId;
	}

}
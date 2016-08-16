package com.delmar.officeTest.model;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.delmar.common.model.BusCoreModel;
import com.delmar.core.model.CoreModel;

/**
 * OfficeTestBank entity. @author MyEclipse Persistence Tools
 */

public class OfficeTestBank extends CoreModel {

	// Fields

	private String code;
	private String name;
	private Integer timeLimit;
	private Integer questionCount;
	private Integer beCalcScore;
	private Integer beOpen;
	private Integer beState;
	private String remark;
	private Integer clientId;
	private Integer orgId;
	private Integer userId;
	private String userName;
	private Date created;
	private Integer createdBy;
	private String createdByName;
	private Date updated;
	private Integer updatedBy;
	private String updatedByName;
	private Set officeTestExams = new HashSet(0);
	private Set officeTestBankCategories = new HashSet(0);
	private Set officeTestQuestions = new HashSet(0);
	
	private boolean hasBankCategorys;

	// Constructors

	/** default constructor */
	public OfficeTestBank() {
	}

	/** minimal constructor */
	public OfficeTestBank(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public OfficeTestBank(Integer id, String code, String name,
			Integer timeLimit, Integer questionCount, Integer beCalcScore,
			Integer beOpen, Integer beState, String remark, Integer clientId,
			Integer orgId, Integer userId, String userName, Timestamp created,
			Integer createdBy, String createdByName, Timestamp updated,
			Integer updatedBy, String updatedByName, Set officeTestExams,
			Set officeTestBankCategories, Set officeTestQuestions) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.timeLimit = timeLimit;
		this.questionCount = questionCount;
		this.beCalcScore = beCalcScore;
		this.beOpen = beOpen;
		this.beState = beState;
		this.remark = remark;
		this.clientId = clientId;
		this.orgId = orgId;
		this.userId = userId;
		this.userName = userName;
		this.created = created;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.updatedByName = updatedByName;
		this.officeTestExams = officeTestExams;
		this.officeTestBankCategories = officeTestBankCategories;
		this.officeTestQuestions = officeTestQuestions;
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

	public Integer getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getQuestionCount() {
		return this.questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}

	public Integer getBeCalcScore() {
		return this.beCalcScore;
	}

	public void setBeCalcScore(Integer beCalcScore) {
		this.beCalcScore = beCalcScore;
	}

	public Integer getBeOpen() {
		return this.beOpen;
	}

	public void setBeOpen(Integer beOpen) {
		this.beOpen = beOpen;
	}

	public Integer getBeState() {
		return this.beState;
	}

	public void setBeState(Integer beState) {
		this.beState = beState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedByName() {
		return this.createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Set getOfficeTestExams() {
		return this.officeTestExams;
	}

	public void setOfficeTestExams(Set officeTestExams) {
		this.officeTestExams = officeTestExams;
	}

	public Set getOfficeTestBankCategories() {
		return this.officeTestBankCategories;
	}

	public void setOfficeTestBankCategories(Set officeTestBankCategories) {
		this.officeTestBankCategories = officeTestBankCategories;
	}

	public Set getOfficeTestQuestions() {
		return this.officeTestQuestions;
	}

	public void setOfficeTestQuestions(Set officeTestQuestions) {
		this.officeTestQuestions = officeTestQuestions;
	}

	public boolean isHasBankCategorys() {
		if (officeTestBankCategories == null) {
			return false;
		}
		
		if (officeTestBankCategories.size() > 0) {
			return true;
		}
		
		return false;
	}

	public void setHasBankCategorys(boolean hasBankCategorys) {
		this.hasBankCategorys = hasBankCategorys;
	}


}
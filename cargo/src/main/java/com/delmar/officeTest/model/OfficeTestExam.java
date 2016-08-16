package com.delmar.officeTest.model;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.delmar.common.model.BusCoreModel;
import com.delmar.core.model.CoreModel;

/**
 * OfficeTestExam entity. @author MyEclipse Persistence Tools
 */

public class OfficeTestExam extends CoreModel {

	// Fields

	private OfficeTestBank officeTestBank;
	private Integer examUserId;
	private Integer examScore;
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
	private Integer beCancel;
	private String examUserName;
	private Integer timeUsed;
	private Integer testBankId;
	private Set officeTestExamDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public OfficeTestExam() {
	}

	/** minimal constructor */
	public OfficeTestExam(Integer id, OfficeTestBank officeTestBank) {
		this.id = id;
		this.officeTestBank = officeTestBank;
	}

	/** full constructor */
	public OfficeTestExam(Integer id, OfficeTestBank officeTestBank,
			Integer examUserId, Integer examScore, Integer clientId,
			Integer orgId, Integer userId, String userName, Timestamp created,
			Integer createdBy, String createdByName, Timestamp updated,
			Integer updatedBy, String updatedByName, Integer beCancel,
			String examUserName, Integer timeUsed, Set officeTestExamDetails) {
		this.id = id;
		this.officeTestBank = officeTestBank;
		this.examUserId = examUserId;
		this.examScore = examScore;
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
		this.beCancel = beCancel;
		this.examUserName = examUserName;
		this.timeUsed = timeUsed;
		this.officeTestExamDetails = officeTestExamDetails;
	}

	// Property accessors

	public OfficeTestBank getOfficeTestBank() {
		return this.officeTestBank;
	}

	public void setOfficeTestBank(OfficeTestBank officeTestBank) {
		this.officeTestBank = officeTestBank;
	}

	public Integer getExamUserId() {
		return this.examUserId;
	}

	public void setExamUserId(Integer examUserId) {
		this.examUserId = examUserId;
	}

	public Integer getExamScore() {
		return this.examScore;
	}

	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
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

	public Integer getBeCancel() {
		return this.beCancel;
	}

	public void setBeCancel(Integer beCancel) {
		this.beCancel = beCancel;
	}

	public String getExamUserName() {
		return this.examUserName;
	}

	public void setExamUserName(String examUserName) {
		this.examUserName = examUserName;
	}

	public Integer getTimeUsed() {
		return this.timeUsed;
	}

	public void setTimeUsed(Integer timeUsed) {
		this.timeUsed = timeUsed;
	}

	public Set getOfficeTestExamDetails() {
		return this.officeTestExamDetails;
	}

	public void setOfficeTestExamDetails(Set officeTestExamDetails) {
		this.officeTestExamDetails = officeTestExamDetails;
	}

	public Integer getTestBankId() {
		return testBankId;
	}

	public void setTestBankId(Integer testBankId) {
		this.testBankId = testBankId;
	}

}
package com.delmar.officeTest.model;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.delmar.common.model.BusCoreModel;
import com.delmar.core.model.CoreModel;

/**
 * OfficeTestQuestion entity. @author MyEclipse Persistence Tools
 */

public class OfficeTestQuestion extends CoreModel {

	// Fields

	private OfficeTestBankCategory officeTestBankCategory;
	private OfficeTestBank officeTestBank;
	private String content;
	private String optionOne = "";
	private String optionTwo = "";
	private String optionThree = "";
	private String optionFour = "";
	private String optionFive = "";
	private String answer = "";
	private Integer point;
	private String remark = "";
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
	private Integer typeId;
	private Integer testBankId;
	private Integer categoryId;
	private Set officeTestExamDetails = new HashSet(0);
	
	
	private String categoryName;
	private String typeName;
	
	
	private int questionTh;

	// Constructors

	/** default constructor */
	public OfficeTestQuestion() {
	}

	/** minimal constructor */
	public OfficeTestQuestion(Integer id,
			OfficeTestBankCategory officeTestBankCategory, Integer typeId) {
		this.id = id;
		this.officeTestBankCategory = officeTestBankCategory;
		this.typeId = typeId;
	}

	/** full constructor */
	public OfficeTestQuestion(Integer id,
			OfficeTestBankCategory officeTestBankCategory,
			OfficeTestBank officeTestBank, String content, String optionOne,
			String optionTwo, String optionThree, String optionFour,
			String optionFive, String answer, Integer point, String remark,
			Integer clientId, Integer orgId, Integer userId, String userName,
			Timestamp created, Integer createdBy, String createdByName,
			Timestamp updated, Integer updatedBy, String updatedByName,
			Integer typeId, Set officeTestExamDetails) {
		this.id = id;
		this.officeTestBankCategory = officeTestBankCategory;
		this.officeTestBank = officeTestBank;
		this.content = content;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionThree = optionThree;
		this.optionFour = optionFour;
		this.optionFive = optionFive;
		this.answer = answer;
		this.point = point;
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
		this.typeId = typeId;
		this.officeTestExamDetails = officeTestExamDetails;
	}

	// Property accessors

	public OfficeTestBankCategory getOfficeTestBankCategory() {
		return this.officeTestBankCategory;
	}

	public void setOfficeTestBankCategory(
			OfficeTestBankCategory officeTestBankCategory) {
		this.officeTestBankCategory = officeTestBankCategory;
	}

	public OfficeTestBank getOfficeTestBank() {
		return this.officeTestBank;
	}

	public void setOfficeTestBank(OfficeTestBank officeTestBank) {
		this.officeTestBank = officeTestBank;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptionOne() {
		return this.optionOne;
	}

	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}

	public String getOptionTwo() {
		return this.optionTwo;
	}

	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}

	public String getOptionThree() {
		return this.optionThree;
	}

	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}

	public String getOptionFour() {
		return this.optionFour;
	}

	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}

	public String getOptionFive() {
		return this.optionFive;
	}

	public void setOptionFive(String optionFive) {
		this.optionFive = optionFive;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getQuestionTh() {
		return questionTh;
	}

	public void setQuestionTh(int questionTh) {
		this.questionTh = questionTh;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
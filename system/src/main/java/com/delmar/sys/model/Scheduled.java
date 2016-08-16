package com.delmar.sys.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

public class Scheduled  extends CoreModel{

    private String code;
	private String name;

    private String className;

    private String isactive;

    private Integer runType;
    private Integer delay;
    
    private String timeYear;
    private String timeMonth;
    private String timeDay;
    private String timeWeek;
    private String timeTime;
    
    

    private Integer userId;

    private String userName;

    private Integer orgId;

    private Integer clientId;
	private Client client;
	private Org org;
    private String remark;

    private Integer createdBy;
    
    private String createdByName;

    private Date created;

    private Integer updatedBy;
    
    private String updatedByName;

    private Date updated;
    
    private Long nextRunTime;
    
    private Long runCount;
    
    private String previousrun;

	public Integer getRunType() {
		
		return runType;
	}

	public void setRunType(Integer runType) {
		this.runType = runType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getTimeYear() {
		return timeYear;
	}

	public void setTimeYear(String timeYear) {
		this.timeYear = timeYear;
	}

	public String getTimeMonth() {
		return timeMonth;
	}

	public void setTimeMonth(String timeMonth) {
		this.timeMonth = timeMonth;
	}

	public String getTimeDay() {
		return timeDay;
	}

	public void setTimeDay(String timeDay) {
		this.timeDay = timeDay;
	}

	public String getTimeWeek() {
		return timeWeek;
	}

	public void setTimeWeek(String timeWeek) {
		this.timeWeek = timeWeek;
	}

	public String getTimeTime() {
		return timeTime;
	}

	public void setTimeTime(String timeTime) {
		this.timeTime = timeTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}



	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getNextRunTime() {
		return nextRunTime;
	}

	public void setNextRunTime(Long nextRunTime) {
		this.nextRunTime = nextRunTime;
	}

	public Long getRunCount() {
		return runCount;
	}

	public void setRunCount(Long runCount) {
		this.runCount = runCount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	/**
	 * @return the previousrun
	 */
	public String getPreviousrun() {
		return previousrun;
	}

	/**
	 * @param previousrun the previousrun to set
	 */
	public void setPreviousrun(String previousrun) {
		this.previousrun = previousrun;
	}
    
    
    
}
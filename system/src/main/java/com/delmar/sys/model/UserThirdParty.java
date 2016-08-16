package com.delmar.sys.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

public class UserThirdParty extends CoreModel {

    private String password;

    private String partyUser;

    private Integer sysUserId;
    
    private Integer systemId;

    private Date created;

    private Integer createdBy;

    private String createdByName;

    private Date updated;

    private Integer updatedBy;

    private String updatedByName;

    private Integer partyTypeId;
    
    private String remark;
    
    private String systemName;
    private String partyTypeName;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPartyUser() {
		return partyUser;
	}
	public void setPartyUser(String partyUser) {
		this.partyUser = partyUser;
	}
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
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
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
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
	public Integer getPartyTypeId() {
		return partyTypeId;
	}
	public void setPartyTypeId(Integer partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}
	
    
    
    
}
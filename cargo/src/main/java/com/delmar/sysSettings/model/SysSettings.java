package com.delmar.sysSettings.model;
// default package

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.model.CoreModel;

/**
 * SysSettings entity. @author MyEclipse Persistence Tools
 */

public class SysSettings extends CoreModel {

	// Fields

	private SysSettingsItem sysSettingsItem;
	private String setValueString;
	private Integer userId;
	private String userName;
	private Integer orgId;
	private Integer clientId;
	private Integer setUserId;
	private Integer setOrgId;
	private Integer setClientId;
	private Date created;
	private Integer createdBy;
	private String createdByName;
	private Date updated;
	private Integer updatedBy;
	private String updatedByName;
	private Integer itemId;
	private Integer indexOrder;
	private Set sysSettingsValues = new HashSet(0);
	private List<SysSettingsValues> sysSettingsValuesList = new ArrayList<SysSettingsValues>();
	
	// Constructors

	/** default constructor */
	public SysSettings() {
	}

	/** minimal constructor */
	public SysSettings(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettings(Integer id, SysSettingsItem sysSettingsItem,
			String setValueString, 
			Integer userId, String userName, Integer orgId, Integer clientId,
			Integer setUserId, Integer setOrgId, Integer setClientId,
			Timestamp created, Integer createdBy, String createdByName,
			Timestamp updated, Integer updatedBy, String updatedByName,
			Set sysSettingsValues) {
		this.id = id;
		this.sysSettingsItem = sysSettingsItem;
		this.setValueString = setValueString;
		this.userId = userId;
		this.userName = userName;
		this.orgId = orgId;
		this.clientId = clientId;
		this.setUserId = setUserId;
		this.setOrgId = setOrgId;
		this.setClientId = setClientId;
		this.created = created;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.updatedByName = updatedByName;
		this.sysSettingsValues = sysSettingsValues;
	}

	// Property accessors

	public SysSettingsItem getSysSettingsItem() {
		return this.sysSettingsItem;
	}

	public void setSysSettingsItem(SysSettingsItem sysSettingsItem) {
		this.sysSettingsItem = sysSettingsItem;
	}

	public String getSetValueString() {
		return this.setValueString;
	}

	public void setSetValueString(String setValueString) {
		this.setValueString = setValueString;
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

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getSetUserId() {
		return this.setUserId;
	}

	public void setSetUserId(Integer setUserId) {
		this.setUserId = setUserId;
	}

	public Integer getSetOrgId() {
		return this.setOrgId;
	}

	public void setSetOrgId(Integer setOrgId) {
		this.setOrgId = setOrgId;
	}

	public Integer getSetClientId() {
		return this.setClientId;
	}

	public void setSetClientId(Integer setClientId) {
		this.setClientId = setClientId;
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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Set<SysSettingsValues> getSysSettingsValues() {
		return sysSettingsValues;
	}

	public void setSysSettingsValues(Set<SysSettingsValues> sysSettingsValues) {
		this.sysSettingsValues = sysSettingsValues;
	}

	public Integer getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}

	public List<SysSettingsValues> getSysSettingsValuesList() {
		return sysSettingsValuesList;
	}

	public void setSysSettingsValuesList(List<SysSettingsValues> sysSettingsValuesList) {
		this.sysSettingsValuesList = sysSettingsValuesList;
	}


}
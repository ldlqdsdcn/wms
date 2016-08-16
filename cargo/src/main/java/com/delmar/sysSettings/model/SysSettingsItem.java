package com.delmar.sysSettings.model;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.delmar.core.model.CoreModel;

/**
 * SysSettingsItem entity. @author MyEclipse Persistence Tools
 */

public class SysSettingsItem extends CoreModel {

	// Fields

	private String value;
	private String name;
	private Integer setLevel;
	private String remark;
	private Integer userId;
	private String userName;
	private Integer orgId;
	private Integer clientId;
	private Date created;
	private Integer createdBy;
	private String createdByName;
	private Date updated;
	private Integer updatedBy;
	private String updatedByName;
	private Integer setType;
	private Integer indexOrder;
	private Set sysSettingsItemTrls = new HashSet(0);
	private Set sysSettingses = new HashSet(0);
	private Set sysSettingsItemValues = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysSettingsItem() {
	}

	/** minimal constructor */
	public SysSettingsItem(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettingsItem(Integer id, String value, String name,
			Integer setLevel, String remark, Integer userId, String userName,
			Integer orgId, Integer clientId, Timestamp created,
			Integer createdBy, String createdByName, Timestamp updated,
			Integer updatedBy, String updatedByName, Integer setType,Integer indexOrder,
			Set sysSettingsItemTrls, Set sysSettingses,
			Set sysSettingsItemValues) {
		this.id = id;
		this.value = value;
		this.name = name;
		this.setLevel = setLevel;
		this.remark = remark;
		this.userId = userId;
		this.userName = userName;
		this.orgId = orgId;
		this.clientId = clientId;
		this.created = created;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.updatedByName = updatedByName;
		this.setType = setType;
		this.indexOrder = indexOrder;
		this.sysSettingsItemTrls = sysSettingsItemTrls;
		this.sysSettingses = sysSettingses;
		this.sysSettingsItemValues = sysSettingsItemValues;
	}

	// Property accessors


	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSetLevel() {
		return this.setLevel;
	}

	public void setSetLevel(Integer setLevel) {
		this.setLevel = setLevel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getSetType() {
		return this.setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
	}

	public Set getSysSettingsItemTrls() {
		return this.sysSettingsItemTrls;
	}

	public void setSysSettingsItemTrls(Set sysSettingsItemTrls) {
		this.sysSettingsItemTrls = sysSettingsItemTrls;
	}

	public Set getSysSettingses() {
		return this.sysSettingses;
	}

	public void setSysSettingses(Set sysSettingses) {
		this.sysSettingses = sysSettingses;
	}

	public Set getSysSettingsItemValues() {
		return this.sysSettingsItemValues;
	}

	public void setSysSettingsItemValues(Set sysSettingsItemValues) {
		this.sysSettingsItemValues = sysSettingsItemValues;
	}

	public Integer getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}

}
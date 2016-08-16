package com.delmar.sysSettings.model;

import com.delmar.core.model.CoreModel;
// default package

/**
 * SysSettingsItemTrl entity. @author MyEclipse Persistence Tools
 */

public class SysSettingsItemTrl extends CoreModel {

	// Fields

	private SysSettingsItem sysSettingsItem;
	private String language;
	private String name;
	private String remark;
	private Integer itemId;

	// Constructors

	/** default constructor */
	public SysSettingsItemTrl() {
	}

	/** minimal constructor */
	public SysSettingsItemTrl(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettingsItemTrl(Integer id, SysSettingsItem sysSettingsItem,
			String language, String name, String remark) {
		this.id = id;
		this.sysSettingsItem = sysSettingsItem;
		this.language = language;
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public SysSettingsItem getSysSettingsItem() {
		return this.sysSettingsItem;
	}

	public void setSysSettingsItem(SysSettingsItem sysSettingsItem) {
		this.sysSettingsItem = sysSettingsItem;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}
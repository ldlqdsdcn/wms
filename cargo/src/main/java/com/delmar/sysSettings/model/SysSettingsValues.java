package com.delmar.sysSettings.model;

import com.delmar.core.model.CoreModel;

/**
 * SysSettingsValues entity. @author MyEclipse Persistence Tools
 */

public class SysSettingsValues extends CoreModel {

	// Fields
	private Integer settingsId;
	private Integer itemvalueId;
	
	private SysSettingsItemValue sysSettingsItemValue;
	private SysSettings sysSettings;

	// Constructors

	/** default constructor */
	public SysSettingsValues() {
	}

	/** minimal constructor */
	public SysSettingsValues(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettingsValues(Integer id,
			SysSettingsItemValue sysSettingsItemValue, SysSettings sysSettings) {
		this.id = id;
		this.sysSettingsItemValue = sysSettingsItemValue;
		this.sysSettings = sysSettings;
	}

	public SysSettingsItemValue getSysSettingsItemValue() {
		return this.sysSettingsItemValue;
	}

	public void setSysSettingsItemValue(
			SysSettingsItemValue sysSettingsItemValue) {
		this.sysSettingsItemValue = sysSettingsItemValue;
	}

	public SysSettings getSysSettings() {
		return this.sysSettings;
	}

	public void setSysSettings(SysSettings sysSettings) {
		this.sysSettings = sysSettings;
	}

	public Integer getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(Integer settingsId) {
		this.settingsId = settingsId;
	}

	public Integer getItemvalueId() {
		return itemvalueId;
	}

	public void setItemvalueId(Integer itemvalueId) {
		this.itemvalueId = itemvalueId;
	}

}
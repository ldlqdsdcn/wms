package com.delmar.sysSettings.model;

import com.delmar.core.model.CoreModel;
// default package

/**
 * SysSettingsItemValueTrl entity. @author MyEclipse Persistence Tools
 */

public class SysSettingsItemValueTrl extends CoreModel implements Comparable<SysSettingsItemValueTrl>{

	// Fields

	private SysSettingsItemValue sysSettingsItemValue;
	private String language;
	private String name;
	private String remark;
	private Integer itemvalueId;

	// Constructors

	/** default constructor */
	public SysSettingsItemValueTrl() {
	}

	/** minimal constructor */
	public SysSettingsItemValueTrl(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettingsItemValueTrl(Integer id,
			SysSettingsItemValue sysSettingsItemValue, String language,
			String name, String remark) {
		this.id = id;
		this.sysSettingsItemValue = sysSettingsItemValue;
		this.language = language;
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public SysSettingsItemValue getSysSettingsItemValue() {
		return this.sysSettingsItemValue;
	}

	public void setSysSettingsItemValue(
			SysSettingsItemValue sysSettingsItemValue) {
		this.sysSettingsItemValue = sysSettingsItemValue;
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

	public Integer getItemvalueId() {
		return itemvalueId;
	}

	public void setItemvalueId(Integer itemvalueId) {
		this.itemvalueId = itemvalueId;
	}

	public int compareTo(SysSettingsItemValueTrl trl) {
		return this.getLanguage().compareTo(trl.getLanguage());
	}

}
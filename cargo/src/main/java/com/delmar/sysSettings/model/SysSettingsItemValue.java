package com.delmar.sysSettings.model;
// default package

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.delmar.core.model.CoreModel;

/**
 * SysSettingsItemValue entity. @author MyEclipse Persistence Tools
 */

public class SysSettingsItemValue extends CoreModel {

	// Fields

	private SysSettingsItem sysSettingsItem;
	private String value;
	private String name;
	private String remark;
	private Integer itemId;
	private Integer defaulted;
	private Set sysSettingsItemValueTrls = new HashSet(0);
	private List<SysSettingsItemValueTrl> sysSettingsItemValueTrlList = new ArrayList<SysSettingsItemValueTrl>();

	// Constructors

	/** default constructor */
	public SysSettingsItemValue() {
	}

	/** minimal constructor */
	public SysSettingsItemValue(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysSettingsItemValue(Integer id, SysSettingsItem sysSettingsItem,
			String value, String name, String remark,
			Set sysSettingsItemValueTrls) {
		this.id = id;
		this.sysSettingsItem = sysSettingsItem;
		this.value = value;
		this.name = name;
		this.remark = remark;
		this.sysSettingsItemValueTrls = sysSettingsItemValueTrls;
	}

	// Property accessors

	public SysSettingsItem getSysSettingsItem() {
		return this.sysSettingsItem;
	}

	public void setSysSettingsItem(SysSettingsItem sysSettingsItem) {
		this.sysSettingsItem = sysSettingsItem;
	}

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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getSysSettingsItemValueTrls() {
		return this.sysSettingsItemValueTrls;
	}

	public void setSysSettingsItemValueTrls(Set sysSettingsItemValueTrls) {
		this.sysSettingsItemValueTrls = sysSettingsItemValueTrls;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getDefaulted() {
		return defaulted;
	}

	public void setDefaulted(Integer defaulted) {
		this.defaulted = defaulted;
	}

	public List<SysSettingsItemValueTrl> getSysSettingsItemValueTrlList() {
		return sysSettingsItemValueTrlList;
	}

	public void setSysSettingsItemValueTrlList(List<SysSettingsItemValueTrl> sysSettingsItemValueTrlList) {
		this.sysSettingsItemValueTrlList = sysSettingsItemValueTrlList;
	}

}
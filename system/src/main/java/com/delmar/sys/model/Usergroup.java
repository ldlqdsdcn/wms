package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class Usergroup  extends CoreModel{

	
    private String name;

    private String remark;

    private Integer orgId;
    private Org org;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

	/**
	 * @return the org
	 */
	public Org getOrg() {
		return org;
	}

	/**
	 * @param org the org to set
	 */
	public void setOrg(Org org) {
		this.org = org;
	}
    
}
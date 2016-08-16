package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class UserorgAccess extends CoreModel{

    private Integer userId;

    private Integer orgId;

    private Org org;
    public static Integer TYPE_ORG=1;
    public static Integer TYPE_USER=2;
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



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }


    
}
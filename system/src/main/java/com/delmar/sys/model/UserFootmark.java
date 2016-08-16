package com.delmar.sys.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

public class UserFootmark extends CoreModel {


    private String actionMethod;

    private Date visiteDate;

    private String actionName;

    private String actionPurpose;

    private Integer userId;

    private Integer orgId;

    private String remark;

    private String remoteAddr;

    private String remoteHost;

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public Date getVisiteDate() {
		return visiteDate;
	}

	public void setVisiteDate(Date visiteDate) {
		this.visiteDate = visiteDate;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionPurpose() {
		return actionPurpose;
	}

	public void setActionPurpose(String actionPurpose) {
		this.actionPurpose = actionPurpose;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
    
    

 
}
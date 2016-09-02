package com.delmar.base.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年3月25日 上午11:53:35
 */
public class EventType extends CoreModel{

    private String shortcode;

    private String fullname;

    private Integer newstate;

    private Integer internalonly;

    private Integer clientId;

    private Integer orgId;

    private Integer userId;

    private String userName;

    private Date created;

    private Integer createdby;

    private String createdbyname;

    private Date updated;

    private Integer updatedby;

    private String updatedbyname;
    
    private Integer indexorder;
    
    private Integer eventtypestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode == null ? null : shortcode.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public Integer getNewstate() {
        return newstate;
    }

    public void setNewstate(Integer newstate) {
        this.newstate = newstate;
    }

    public Integer getInternalonly() {
        return internalonly;
    }

    public void setInternalonly(Integer internalonly) {
        this.internalonly = internalonly;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public String getCreatedbyname() {
        return createdbyname;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname == null ? null : createdbyname.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public String getUpdatedbyname() {
        return updatedbyname;
    }

    public void setUpdatedbyname(String updatedbyname) {
       this.updatedbyname = updatedbyname == null ? null : updatedbyname.trim();
    }

	/**
	 * @return the indexorder
	 */
	public Integer getIndexorder() {
		return indexorder;
	}

	/**
	 * @param indexorder the indexorder to set
	 */
	public void setIndexorder(Integer indexorder) {
		this.indexorder = indexorder;
	}

	/**
	 * @return the eventtypestatus
	 */
	public Integer getEventtypestatus() {
		return eventtypestatus;
	}

	/**
	 * @param eventtypestatus the eventtypestatus to set
	 */
	public void setEventtypestatus(Integer eventtypestatus) {
		this.eventtypestatus = eventtypestatus;
	}
    
}
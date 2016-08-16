package com.delmar.corebus.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年3月26日 上午8:07:08
 */
public class RealtransEvent  extends CoreModel{

    private Integer realtransId;

    private Integer eventId;

    private Integer clientId;

    private Integer orgId;

    private Integer userId;

    private String userName;

    private Date created;

    private Integer createdby;

    private Date updated;

    private Integer updatedby;

    private String createdbyname;

    private String updatedbyname;

    private Event event;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRealtransId() {
        return realtransId;
    }

    public void setRealtransId(Integer realtransId) {
        this.realtransId = realtransId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

    public String getCreatedbyname() {
        return createdbyname;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname == null ? null : createdbyname.trim();
    }

    public String getUpdatedbyname() {
        return updatedbyname;
    }

    public void setUpdatedbyname(String updatedbyname) {
        this.updatedbyname = updatedbyname == null ? null : updatedbyname.trim();
    }

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
    
}
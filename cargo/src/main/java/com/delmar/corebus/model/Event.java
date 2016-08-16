package com.delmar.corebus.model;

import java.util.Date;

import com.delmar.base.model.EventType;
import com.delmar.core.model.CoreModel;

public class Event  extends CoreModel{

    private Integer eventtypeid;

    private Date eventdate;

    private String opsreference;

    private String remark;

    private Integer cityid;
    
    private String eventdesc;
    
    private EventType eventType;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventtypeid() {
        return eventtypeid;
    }

    public void setEventtypeid(Integer eventtypeid) {
        this.eventtypeid = eventtypeid;
    }

    public Date getEventdate() {
        return eventdate;
    }

    public void setEventdate(Date eventdate) {
        this.eventdate = eventdate;
    }

    public String getOpsreference() {
        return opsreference;
    }

    public void setOpsreference(String opsreference) {
        this.opsreference = opsreference == null ? null : opsreference.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

	/**
	 * @return the eventdesc
	 */
	public String getEventdesc() {
		return eventdesc;
	}

	/**
	 * @param eventdesc the eventdesc to set
	 */
	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
    
}
package com.powere2e.reporttool.model;

import java.util.Date;
import java.util.Map;

public class Scheduler {
	private Integer id;
	private String name;
	private Integer interval;
	private String active;
	private String description;
	private Date bgndate;
	private Date enddate;
	private String classname;
	
	public Scheduler(Map map)
	{
		id=(Integer)map.get("ID");
		name=(String)map.get("NAME");
		interval=(Integer)map.get("INTERVAL");
		active=(String)map.get("ACTIVE");
		description=(String)map.get("DESCRIPTION");
		bgndate=(Date)map.get("BGNDATE");
		enddate=(Date)map.get("ENDDATE");
		classname=(String)map.get("CLASSNAME");
	}
	
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
		this.name = name;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBgndate() {
		return bgndate;
	}
	public void setBgndate(Date bgndate) {
		this.bgndate = bgndate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}

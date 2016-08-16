package com.delmar.crm.model;

import java.util.Date;

import com.delmar.common.model.BusCoreModel;

/**
 * @author 刘大磊 2015年3月11日 下午1:45:25
 * @version V2.0
 */
public class Linkrecord extends BusCoreModel {
    
    private String linkRecordId;
	private Integer customerId;//
    private String customerName;
    private Customer customer;

    private Integer linkmanId;
    private String linkmanName;
    private Linkman linkman;    
    
    private String  contactTitle;
    private Date  contactDate;
    private String  contactRecord;
    private Integer contactModeId;
    private String contactModeName;
    private Integer forceOnId;
    private String forceOnName;
    private Date nextTime;
    private String nextGoal;
    private String nextComments;
    private Integer resultId;
    private String remark;
    
    
    
    
	public String getLinkRecordId() {
		return linkRecordId;
	}
	public void setLinkRecordId(String linkRecordId) {
		this.linkRecordId = linkRecordId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getLinkmanId() {
		return linkmanId;
	}
	public void setLinkmanId(Integer linkmanId) {
		this.linkmanId = linkmanId;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public Linkman getLinkman() {
		return linkman;
	}
	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	
	
	public String getContactRecord() {
		return contactRecord;
	}
	public void setContactRecord(String contactRecord) {
		this.contactRecord = contactRecord;
	}
	public Integer getContactModeId() {
		return contactModeId;
	}
	public void setContactModeId(Integer contactModeId) {
		this.contactModeId = contactModeId;
	}
	public String getContactModeName() {
		return contactModeName;
	}
	public void setContactModeName(String contactModeName) {
		this.contactModeName = contactModeName;
	}
	public Integer getForceOnId() {
		return forceOnId;
	}
	public void setForceOnId(Integer forceOnId) {
		this.forceOnId = forceOnId;
	}
	public String getForceOnName() {
		return forceOnName;
	}
	public void setForceOnName(String forceOnName) {
		this.forceOnName = forceOnName;
	}
	public Date getNextTime() {
		return nextTime;
	}
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}
	public String getNextGoal() {
		return nextGoal;
	}
	public void setNextGoal(String nextGoal) {
		this.nextGoal = nextGoal;
	}
	
	
	public String getNextComments() {
		return nextComments;
	}
	public void setNextComments(String nextComments) {
		this.nextComments = nextComments;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
    
    
    
    
   
    
}
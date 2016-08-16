package com.delmar.crm.model;

import java.util.Date;

import com.delmar.common.model.BusCoreModel;

public class CustomerTrace  extends BusCoreModel {


    private Integer fromStatus;
    private String fromStatusName;
    

    private Integer toStatus;
    private String toStatusName;

    private Integer changeType;

    private Date changeTime;

    private Integer customerId;
    private String customerName;
    private Customer customer;
    
    private String remark;
    
	public Integer getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(Integer fromStatus) {
		this.fromStatus = fromStatus;
	}
	public Integer getToStatus() {
		return toStatus;
	}
	public void setToStatus(Integer toStatus) {
		this.toStatus = toStatus;
	}
	public Integer getChangeType() {
		return changeType;
	}
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFromStatusName() {
		return fromStatusName;
	}
	public void setFromStatusName(String fromStatusName) {
		this.fromStatusName = fromStatusName;
	}
	public String getToStatusName() {
		return toStatusName;
	}
	public void setToStatusName(String toStatusName) {
		this.toStatusName = toStatusName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
    
    

}
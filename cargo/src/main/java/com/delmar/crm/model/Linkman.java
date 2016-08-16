package com.delmar.crm.model;

import java.util.Date;

import com.delmar.common.model.BusCoreModel;
import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2015年3月4日 下午4:19:13
 * @version V2.0
 */
public class Linkman extends BusCoreModel{

    private String linkCode;//联系人编码

    private String name;//联系人姓名
    private String ename;//英文名   
    private String department;//所在部门    

    private Integer callId;//联系人称呼
    private String callName;

    private Date birthday;//生日    

    private String phoneNo;//手机号码

    private String email;//电子邮件

    private Integer customerId;//客户名称
    
    private Customer customer;
    
    private String isMain;//是否主联系人



    private String position;//联系人职务

    private String busPhone;//办公电话

    private String fax;//联系传真

    private String postCode;//邮政编码

    private String familyPhone;//家庭电话

    private String qq;

    private String skyper;
    
    private String address;//联系地址

    private String other;//其他联系

    private String hobby;//爱好

    private String taboo;//忌讳
    
    private Date nextLinkTime;
    private Date firstLinkTime;
    private Date lastLinkTime;

    private String remark;//备注

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getCallId() {
		return callId;
	}

	public void setCallId(Integer callId) {
		this.callId = callId;
	}

	public String getCallName() {
		return callName;
	}

	public void setCallName(String callName) {
		this.callName = callName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIsMain() {
		if (isMain==null)
			isMain="";
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSkyper() {
		return skyper;
	}

	public void setSkyper(String skyper) {
		this.skyper = skyper;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}

	
	
	public Date getNextLinkTime() {
		return nextLinkTime;
	}

	public void setNextLinkTime(Date nextLinkTime) {
		this.nextLinkTime = nextLinkTime;
	}

	public Date getFirstLinkTime() {
		return firstLinkTime;
	}

	public void setFirstLinkTime(Date firstLinkTime) {
		this.firstLinkTime = firstLinkTime;
	}

	public Date getLastLinkTime() {
		return lastLinkTime;
	}

	public void setLastLinkTime(Date lastLinkTime) {
		this.lastLinkTime = lastLinkTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
    


   
}
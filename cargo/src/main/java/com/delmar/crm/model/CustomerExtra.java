package com.delmar.crm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * CRM模块 客户档案的扩展对象
 * @author 刘大磊 (Allen Liu) 2015年3月4日 下午4:19:25
 * @version V2.0  
 * 
 */
public class CustomerExtra  extends CoreModel{
	
	
	private Integer cusTypeId;
    private String cusTypeName;
    private Integer cusSourceId;
    private String cusSourceName;
    private Integer industryId;
    private String industryName;
    private String ename;
    private String eaddress;
    private String majorBusiness;
    private String busDirection;
    private BigDecimal busVolume;
    private String volumeUnit;
    private String busVolumeDesc;
    private String  postCode;
    private String  websiteAddress;
    private Date nextLinkTime;
    private Date firstLinkTime;
    private Date lastLinkTime;
    private String bankName;
    private String bankAccount;
    private BigDecimal longtitude;
    private BigDecimal latitude;
    private String propLabel;
    
	public Integer getCusTypeId() {
		return cusTypeId;
	}
	public void setCusTypeId(Integer cusTypeId) {
		this.cusTypeId = cusTypeId;
	}
	public String getCusTypeName() {
		return cusTypeName;
	}
	public void setCusTypeName(String cusTypeName) {
		this.cusTypeName = cusTypeName;
	}
	public Integer getCusSourceId() {
		return cusSourceId;
	}
	public void setCusSourceId(Integer cusSourceId) {
		this.cusSourceId = cusSourceId;
	}
	public String getCusSourceName() {
		return cusSourceName;
	}
	public void setCusSourceName(String cusSourceName) {
		this.cusSourceName = cusSourceName;
	}
	public Integer getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEaddress() {
		return eaddress;
	}
	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}
	public String getMajorBusiness() {
		return majorBusiness;
	}
	public void setMajorBusiness(String majorBusiness) {
		this.majorBusiness = majorBusiness;
	}
	public String getBusDirection() {
		return busDirection;
	}
	public void setBusDirection(String busDirection) {
		this.busDirection = busDirection;
	}
	public BigDecimal getBusVolume() {
		return busVolume;
		
	}
	public void setBusVolume(BigDecimal busVolume) {
		if (busVolume==null)
			busVolume=new BigDecimal(0);
		this.busVolume = busVolume;
	}
	public String getVolumeUnit() {
		if (busVolume==null)
			busVolume=new BigDecimal(0);		
		return volumeUnit;
	}
	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}
	public String getBusVolumeDesc() {
		return busVolumeDesc;
	}
	public void setBusVolumeDesc(String busVolumeDesc) {
		this.busVolumeDesc = busVolumeDesc;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public BigDecimal getLongtitude() {

		return longtitude;
	}
	public void setLongtitude(BigDecimal longtitude) {
		this.longtitude = longtitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public String getPropLabel() {
		return propLabel;
	}
	public void setPropLabel(String propLabel) {
		this.propLabel = propLabel;
	}

    
 
    
}
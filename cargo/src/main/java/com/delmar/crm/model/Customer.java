package com.delmar.crm.model;

import com.delmar.base.model.City;
import com.delmar.base.model.CityTrl;
import com.delmar.common.model.BusCoreModel;



/**
 * CRM模块 客户档案
 * @author 刘大磊 (Allen Liu) 2015年3月4日 下午4:19:25
 * @version V2.0  
 * 
 */

public class Customer  extends BusCoreModel{
	

    private String cusCode;
    private String opsCode;
    private String name;
    
    private Integer countryId;
    private String countryName;
    private CityTrl country;
    private Integer provinceId;
    private Integer provinceName;
    private CityTrl province;
    private Integer cityId;
    private String cityName;
    private CityTrl city;
    private String address;
    
    private String legalPerson;    
    private String telephone;    
    private String email;    
    private Integer statusId;    
    private String statusName;    
    
    private String remark;

    private String isactive;
    
    private CustomerExtra customerExtra;
    
    public Customer()
    {
    
    	if (customerExtra==null)
    		customerExtra=new CustomerExtra();
    	
    }

	public String getCusCode() {
		return cusCode;
	}

	public void setCuscode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getOpsCode() {
		return opsCode;
	}

	public void setOpsCode(String opsCode) {
		this.opsCode = opsCode;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(Integer provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public CustomerExtra getCustomerExtra() {
		return customerExtra;
	}

	public void setCustomerExtra(CustomerExtra customerExtra) {
		this.customerExtra = customerExtra;
	}

	public CityTrl getCountry() {
		return country;
	}

	public void setCountry(CityTrl country) {
		this.country = country;
	}

	public CityTrl getProvince() {
		return province;
	}

	public void setProvince(CityTrl province) {
		this.province = province;
	}

	public CityTrl getCity() {
		return city;
	}

	public void setCity(CityTrl city) {
		this.city = city;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
		
	}
    
    
    
    
    
    
   
}
package com.delmar.base.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年3月27日 下午2:30:03
 */
public class Companys extends CoreModel{

    private String code;

    private String name;

    private String mobileno;

    private String telephone;

    private String fax;

    private String email;

    private String contact;

    private String address;

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
    
    private Integer relateclientId;
    
    private Integer relateorgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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



    /**
	 * @return the updatedby
	 */
	public Integer getUpdatedby() {
		return updatedby;
	}

	/**
	 * @param updatedby the updatedby to set
	 */
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
	 * @return the relateclientId
	 */
	public Integer getRelateclientId() {
		return relateclientId;
	}

	/**
	 * @param relateclientId the relateclientId to set
	 */
	public void setRelateclientId(Integer relateclientId) {
		this.relateclientId = relateclientId;
	}

	/**
	 * @return the relateorgId
	 */
	public Integer getRelateorgId() {
		return relateorgId;
	}

	/**
	 * @param relateorgId the relateorgId to set
	 */
	public void setRelateorgId(Integer relateorgId) {
		this.relateorgId = relateorgId;
	}
    
}
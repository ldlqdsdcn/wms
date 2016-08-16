/**
 * 
 * */
package com.delmar.common.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;

public class BusCoreModel extends CoreModel {

	private Integer clientId;
	private Client client;
	private Integer orgId;
	private Org org;
	private Integer userId;
	private String userName;
	private User user;
	private Integer createdBy;
	private Date  created;
	private String createdByName;
	private User createdByUser;
	
	private Integer updatedBy;
	private Date  updated;
	private String updatedByName;
	
	private User updatedByUser;
	
	private Integer flag;
	
	
	public BusCoreModel()
	{
		
	}


	public Integer getClientId() {
		return clientId;
	}


	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public Org getOrg() {
		return org;
	}


	public void setOrg(Org org) {
		this.org = org;
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
		this.userName = userName;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public String getCreatedByName() {
		return createdByName;
	}


	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}


	public User getCreatedByUser() {
		return createdByUser;
	}


	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}


	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getUpdated() {
		return updated;
	}


	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public String getUpdatedByName() {
		return updatedByName;
	}


	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}


	public User getUpdatedByUser() {
		return updatedByUser;
	}


	public void setUpdatedByUser(User updatedByUser) {
		this.updatedByUser = updatedByUser;
	}


	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	

}

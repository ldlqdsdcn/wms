package com.delmar.base.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;

public class Datadict extends CoreModel{
	
	/**
	 * 基本运费
	 */
	public static final int FREIGHTTYPE_FREIGHT=0;
	/**
	 * 运费附加费
	 */
	public static final int FREIGHTTYPE_ADDITIONAL=1;
	/**
	 * 杂费
	 */
	public static final int FREIGHTTYPE_OTHERS=2;
	/**
	 * 固定价格
	 */
	public static final String BEFIXED_FIXED="Y";
	/**
	 * 浮动价格
	 */
	public static final String BEFIXED_FLOAT="N";
	
	
	/**
	 * 0 表示所有
	 */
	public static final int CONTAINER_TYPE_ALL_ID=0;
	
	/**
	 * 1整箱
	 */
	public static final int CONTAINER_TYPE__FCL_ID=1;
	
	/**
	 * 2散箱
	 */
	public static final int CONTAINER_TYPE__LCL_ID=2;
	
	public static final int LCL_ID=29;
	public static final String LCL_CODE="LCL";
	

	/**
	 *取 base_chargename id 
	 */
	public static final int  KG_MKGS_ID=23;
	public static final int  KG_45KGS_ID=24;
	public static final int  KG_100KGS_ID=25;
	public static final int  KG_300KGS_ID=26;
	public static final int  KG_1000KGS_ID=27;
	public static final int  KG_500KGS_ID=28;
    
	public static final String KG_MKGS_CODE="MKGS";
	public static final String KG_45KGS_CODE="45KGS";
	public static final String KG_100KGS_CODE="100KGS";
	public static final String KG_300KGS_CODE="300KGS";
	public static final String KG_500KGS_CODE="500KGS";
	public static final String KG_1000KGS_CODE="1000KGS";
	
	
	public static final String TR_AIR_CODE="01";
	public static final String TR_OCEAN_CODE="02";
	

    private String value;

    private String name;

    private String remark;
    
    private Integer isActive;
    private Integer indexOrder;

    private Integer datadictTypeId;

    private DatadictType datadictType;
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
    
    
    
    /**
	 * @return the datadictType
	 */
	public DatadictType getDatadictType() {
		return datadictType;
	}

	/**
	 * @param datadictType the datadictType to set
	 */
	public void setDatadictType(DatadictType datadictType) {
		this.datadictType = datadictType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    
    

    public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}

	public Integer getDatadictTypeId() {
        return datadictTypeId;
    }

    public void setDatadictTypeId(Integer datadictTypeId) {
        this.datadictTypeId = datadictTypeId;
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
    
    
    
}
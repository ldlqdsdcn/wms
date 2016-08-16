package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class DatadictType extends CoreModel {
	public static final String ACCESS_LEVEL="ACCESS_LEVEL";
	/**
	 * 运输类型 Air Ocean Land
	 */
	public static final String MODE="MODE";
	
	/**应收应付
	 * */
	public static final String CREDITDEBIT="CREDITDEBIT";	
	
	/**收支状态
	 * */
	public static final String INVOICESTATUS="INVOICESTATUS";	
	/**客户类型  Shipper还是Consignee
	 * */	
	public static final String CLIENTTYPE="CLIENTTYPE";
	
	/**
	 * 已走货 /未走货
	 * **/
	public static final String HAVEBUSINESS="HAVEBUSINESS";
	
	/**
	 * 排序类型 货量/毛利
	 * **/
	public static final String SORTTYPE="SORTTYPE";	
	
	/**
	 * 委托类型
	 * */
	public static final String TRUSTTYPE="TRUSTTYPE";
	/*
	 * For CRM
	 * */
	
	/**
	 * 客户来源  B_CRM_CUSTOMER
	 */
	public static final String CUSSOURCE="cussource";
	
	/**
	 * 所属行业 B_CRM_CUSTOMER_EXTRA
	 */
	public static final String INDUSTRY="industry";
	/**
	 * 客户性质  同行、直客 B_CRM_CUSTOMER
	 */
	public static final String CUSTYPE="custype";
	
	/**
	 * 客户性质和属性
	 * **/
	public static final String CUSSTATUS="CUSSTATUS";
	/**
	 * 称呼  B_CRM_LINKMAN
	 */
	public static final String CALL="CALL";
	
	/**
	 * LinkRecord*/
	/**
	 * 联系方式  电话等
	 * */
	public static final String LINKRECORD_CONTACTMODE="CONTACTMODE";
	/**
	 * 关注点等
	 * */
	public static final String LINKRECORD_FORCEON="FORCEON";
	
	/**
	 * 结果评价
	 * */
	public static final String LINKRECORD_RESULT="LINKRECORDRESULT";
	
	
	
	
	public static final String CUSTOMERPROPERTY="customerproperty";
	
	
	public static final String EBUSINESS_STATUS="ebusiness_status";
	
	public static final String THRIDPARTY_SYSTEM="thridparty_system";
	public static final String THRIDPARTY_PARTYTYPE="thridparty_parttype";
	public static final String USER_USERTYPE="userType";
	public static final String STATIONSTATUS="STATIONSTATUS";
	public static final String PACKAGELIST="PACKAGELIST";
	public static final String BOOLEANLIST = "BOOLEANLIST";
	public static final String QUESTIONTYPE = "QUESTIONTYPE";
	public static final String SELECTTYPE = "SELECTTYPE";
	public static final String USER_EXTRAPROP="user_extraprop";
	public static final String CLIENT_EXTRAPROP="client_extraprop";
	public static final String ORG_EXTRAPROP="org_extraprop";
	public static final String CUSTOMER_EXTRAPROPLABEL="customer_extraproplabel";
	
	
    private String value;

    private String name;

    private String remark;
    
    private Integer bePublic;

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

	public Integer getBePublic() {
		return bePublic;
	}

	public void setBePublic(Integer bePublic) {
		this.bePublic = bePublic;
	}
    
    
    
}
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.service.impl;

import java.math.BigDecimal;

/**
 * @author 刘大磊 2015年3月19日 下午6:37:55
 */
public class EBusinessParam {
	/**
	 * 根据报价传输过去的报价号
	 * **/
	 private String quoteNo;
	 
	 /**
	 * 发货人名称
	 */
	 private String shipperName;
	 
	 /**
	 * 发货人联系方式
	 */
	 private String shipperContact;
	 /**
	 * 发货人手机
	 */

	 private String shipperMobile;
	 /**
	 * 发货人省份
	 */

	 private String shipperProvince;
	 /**
	 * 发货人城市
	 */

	 private String shipperCity;
	 /**
	 * 发货人区
	 */

	 private String shipperDistrict;
	 /**
	 * 发货人街道
	 */

	 private String shipperAddress;
	 
	 /**
	 * 收件人姓名
	 */

	 private String consigneeName;	 
	 /**
	 * 收件人联系方式
	 */

	 private String consigneeContact;	 
	 /**
	 * 收件人电话
	 */

	 private String consigneeMobile;
	 /**
	 * 收件人省份
	 */

	 private String consigneeProvince;
	 /**
	 * 收件人城市
	 */

	 private String consigneeCity;
	 /**
	 * 收件人区
	 */

	 private String consigneeDistrict;
	 /**
	 * 收件人地址
	 */

	 private String consigneeAddress;	 
	 /**
	 * 货描
	 */

	 private String cargoDesc;
	 /**
	 * 货物数量
	 */

	 private Integer goodsNumber;
	 /**
	 * 货物重量
	 */

	 private BigDecimal goodsWeight;
	 /**
	 * 货物大小
	 */

	 private BigDecimal goodsSize;	 
	 /**
	 * 尺寸
	 */

	 private String goodsDimension;

	 private String remark;
	 
	 private Integer userId;
	 private String userName;
	 
	 
	 
	 private int providerId;

	/**
	 * @return the quoteNo
	 */
	public String getQuoteNo() {
		return quoteNo;
	}

	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	/**
	 * @return the shipperName
	 */
	public String getShipperName() {
		return shipperName;
	}

	/**
	 * @param shipperName the shipperName to set
	 */
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 * @return the shipperContact
	 */
	public String getShipperContact() {
		return shipperContact;
	}

	/**
	 * @param shipperContact the shipperContact to set
	 */
	public void setShipperContact(String shipperContact) {
		this.shipperContact = shipperContact;
	}

	/**
	 * @return the shipperMobile
	 */
	public String getShipperMobile() {
		return shipperMobile;
	}

	/**
	 * @param shipperMobile the shipperMobile to set
	 */
	public void setShipperMobile(String shipperMobile) {
		this.shipperMobile = shipperMobile;
	}

	/**
	 * @return the shipperProvince
	 */
	public String getShipperProvince() {
		return shipperProvince;
	}

	/**
	 * @param shipperProvince the shipperProvince to set
	 */
	public void setShipperProvince(String shipperProvince) {
		this.shipperProvince = shipperProvince;
	}

	/**
	 * @return the shipperCity
	 */
	public String getShipperCity() {
		return shipperCity;
	}

	/**
	 * @param shipperCity the shipperCity to set
	 */
	public void setShipperCity(String shipperCity) {
		this.shipperCity = shipperCity;
	}

	/**
	 * @return the shipperDistrict
	 */
	public String getShipperDistrict() {
		return shipperDistrict;
	}

	/**
	 * @param shipperDistrict the shipperDistrict to set
	 */
	public void setShipperDistrict(String shipperDistrict) {
		this.shipperDistrict = shipperDistrict;
	}

	/**
	 * @return the shipperAddress
	 */
	public String getShipperAddress() {
		return shipperAddress;
	}

	/**
	 * @param shipperAddress the shipperAddress to set
	 */
	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}

	/**
	 * @return the consigneeName
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * @param consigneeName the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	/**
	 * @return the consigneeContact
	 */
	public String getConsigneeContact() {
		return consigneeContact;
	}

	/**
	 * @param consigneeContact the consigneeContact to set
	 */
	public void setConsigneeContact(String consigneeContact) {
		this.consigneeContact = consigneeContact;
	}

	/**
	 * @return the consigneeMobile
	 */
	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	/**
	 * @param consigneeMobile the consigneeMobile to set
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	/**
	 * @return the consigneeProvince
	 */
	public String getConsigneeProvince() {
		return consigneeProvince;
	}

	/**
	 * @param consigneeProvince the consigneeProvince to set
	 */
	public void setConsigneeProvince(String consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}

	/**
	 * @return the consigneeCity
	 */
	public String getConsigneeCity() {
		return consigneeCity;
	}

	/**
	 * @param consigneeCity the consigneeCity to set
	 */
	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	/**
	 * @return the consigneeDistrict
	 */
	public String getConsigneeDistrict() {
		return consigneeDistrict;
	}

	/**
	 * @param consigneeDistrict the consigneeDistrict to set
	 */
	public void setConsigneeDistrict(String consigneeDistrict) {
		this.consigneeDistrict = consigneeDistrict;
	}

	/**
	 * @return the consigneeAddress
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	/**
	 * @param consigneeAddress the consigneeAddress to set
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	/**
	 * @return the cargoDesc
	 */
	public String getCargoDesc() {
		return cargoDesc;
	}

	/**
	 * @param cargoDesc the cargoDesc to set
	 */
	public void setCargoDesc(String cargoDesc) {
		this.cargoDesc = cargoDesc;
	}

	/**
	 * @return the goodsNumber
	 */
	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	/**
	 * @param goodsNumber the goodsNumber to set
	 */
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	/**
	 * @return the goodsWeight
	 */
	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	/**
	 * @param goodsWeight the goodsWeight to set
	 */
	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	/**
	 * @return the goodsSize
	 */
	public BigDecimal getGoodsSize() {
		return goodsSize;
	}

	/**
	 * @param goodsSize the goodsSize to set
	 */
	public void setGoodsSize(BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}

	/**
	 * @return the goodsDimension
	 */
	public String getGoodsDimension() {
		return goodsDimension;
	}

	/**
	 * @param goodsDimension the goodsDimension to set
	 */
	public void setGoodsDimension(String goodsDimension) {
		this.goodsDimension = goodsDimension;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	 
	 
}

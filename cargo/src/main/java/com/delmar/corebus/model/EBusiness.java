package com.delmar.corebus.model;

import java.math.BigDecimal;

import com.delmar.common.model.BusCoreModel;

/**
 * @author 刘大磊 2015年3月18日 下午3:43:04
 */
public class EBusiness extends BusCoreModel {
	
	public static final int STATUS_DRAFT=100;
	
	public static final int STATUS_COMPLETE=101;
	
    private String businessno;

    private String mode;

    private String remark;

    private Integer bTransactionId;

    private String shippercode;

    private String shippername;

    private String shippercontact;

    private String shipperprovince;

    private String shippercity;

    private String shipperaddress;

    private String consigneecode;

    private String consigneename;

    private String consigneemobile;

    private String consigneecontact;

    private String consigneeprovince;

    private String consigneedistrict;

    private String consigneeaddress;

    private Integer goodsnumber;

    private BigDecimal goodsweight;

    private BigDecimal goodssize;

    private String goodsdimension;

    private String consigneecity;

    private String shipperdistrict;

    private String shippermobile;

    private Integer status;

    private String cargodesc;
    
    
    private Transaction transaction;
    
    private BigDecimal totalAmount;
    
  

    public String getBusinessno() {
        return businessno;
    }

    public void setBusinessno(String businessno) {
        this.businessno = businessno == null ? null : businessno.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

   

    public Integer getbTransactionId() {
        return bTransactionId;
    }

    public void setbTransactionId(Integer bTransactionId) {
        this.bTransactionId = bTransactionId;
    }

    public String getShippercode() {
        return shippercode;
    }

    public void setShippercode(String shippercode) {
        this.shippercode = shippercode == null ? null : shippercode.trim();
    }

    public String getShippername() {
        return shippername;
    }

    public void setShippername(String shippername) {
        this.shippername = shippername == null ? null : shippername.trim();
    }

    public String getShippercontact() {
        return shippercontact;
    }

    public void setShippercontact(String shippercontact) {
        this.shippercontact = shippercontact == null ? null : shippercontact.trim();
    }

    public String getShipperprovince() {
        return shipperprovince;
    }

    public void setShipperprovince(String shipperprovince) {
        this.shipperprovince = shipperprovince == null ? null : shipperprovince.trim();
    }

    public String getShippercity() {
        return shippercity;
    }

    public void setShippercity(String shippercity) {
        this.shippercity = shippercity == null ? null : shippercity.trim();
    }

    public String getShipperaddress() {
        return shipperaddress;
    }

    public void setShipperaddress(String shipperaddress) {
        this.shipperaddress = shipperaddress == null ? null : shipperaddress.trim();
    }

    public String getConsigneecode() {
        return consigneecode;
    }

    public void setConsigneecode(String consigneecode) {
        this.consigneecode = consigneecode == null ? null : consigneecode.trim();
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename == null ? null : consigneename.trim();
    }

    public String getConsigneemobile() {
        return consigneemobile;
    }

    public void setConsigneemobile(String consigneemobile) {
        this.consigneemobile = consigneemobile == null ? null : consigneemobile.trim();
    }

    public String getConsigneecontact() {
        return consigneecontact;
    }

    public void setConsigneecontact(String consigneecontact) {
        this.consigneecontact = consigneecontact == null ? null : consigneecontact.trim();
    }

    public String getConsigneeprovince() {
        return consigneeprovince;
    }

    public void setConsigneeprovince(String consigneeprovince) {
        this.consigneeprovince = consigneeprovince == null ? null : consigneeprovince.trim();
    }

    public String getConsigneedistrict() {
        return consigneedistrict;
    }

    public void setConsigneedistrict(String consigneedistrict) {
        this.consigneedistrict = consigneedistrict == null ? null : consigneedistrict.trim();
    }

    public String getConsigneeaddress() {
        return consigneeaddress;
    }

    public void setConsigneeaddress(String consigneeaddress) {
        this.consigneeaddress = consigneeaddress == null ? null : consigneeaddress.trim();
    }

    public Integer getGoodsnumber() {
        return goodsnumber;
    }

    public void setGoodsnumber(Integer goodsnumber) {
        this.goodsnumber = goodsnumber;
    }

    public BigDecimal getGoodsweight() {
        return goodsweight;
    }

    public void setGoodsweight(BigDecimal goodsweight) {
        this.goodsweight = goodsweight;
    }

    public BigDecimal getGoodssize() {
        return goodssize;
    }

    public void setGoodssize(BigDecimal goodssize) {
        this.goodssize = goodssize;
    }

    public String getGoodsdimension() {
        return goodsdimension;
    }

    public void setGoodsdimension(String goodsdimension) {
        this.goodsdimension = goodsdimension == null ? null : goodsdimension.trim();
    }

    public String getConsigneecity() {
        return consigneecity;
    }

    public void setConsigneecity(String consigneecity) {
        this.consigneecity = consigneecity == null ? null : consigneecity.trim();
    }

    public String getShipperdistrict() {
        return shipperdistrict;
    }

    public void setShipperdistrict(String shipperdistrict) {
        this.shipperdistrict = shipperdistrict == null ? null : shipperdistrict.trim();
    }

    public String getShippermobile() {
        return shippermobile;
    }

    public void setShippermobile(String shippermobile) {
        this.shippermobile = shippermobile == null ? null : shippermobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCargodesc() {
        return cargodesc;
    }

    public void setCargodesc(String cargodesc) {
        this.cargodesc = cargodesc == null ? null : cargodesc.trim();
    }

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
    
}
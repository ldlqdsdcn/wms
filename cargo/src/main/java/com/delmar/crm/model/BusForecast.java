package com.delmar.crm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.delmar.base.model.Currency;
import com.delmar.base.model.PortTrl;
import com.delmar.common.model.BusCoreModel;

public class BusForecast extends BusCoreModel {

    private String busForecastId;
	private Integer outIn;

    private String planeOcean;

    private Integer polId;
    private PortTrl pol;

    private Integer poaId;
    private PortTrl poa;

    private BigDecimal goodsWeight;

    private BigDecimal goodsSize;

    private Integer teuNum;

    private BigDecimal profit;

    private Date fromDate;

    private Date toDate;

    private Integer currencyId;
    private Integer linkRecordId;
    
    private Linkrecord linkRecord;
    private String linkRecordCode;
    private Currency currency;

    private String goodsDesc;

    private Integer customerId;
    private Customer customer;
    private String remark;
    
   

	public String getBusForecastId() {
		return busForecastId;
	}

	public void setBusForecastId(String busForecastId) {
		this.busForecastId = busForecastId;
	}

	public Integer getOutIn() {
		return outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public String getPlaneOcean() {
		return planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public Integer getPolId() {
		return polId;
	}

	public void setPolId(Integer polId) {
		this.polId = polId;
	}

	public Integer getPoaId() {
		return poaId;
	}

	public void setPoaId(Integer poaId) {
		this.poaId = poaId;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public BigDecimal getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}

	public Integer getTeuNum() {
		return teuNum;
	}

	public void setTeuNum(Integer teuNum) {
		this.teuNum = teuNum;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public PortTrl getPol() {
		return pol;
	}

	public void setPol(PortTrl pol) {
		this.pol = pol;
	}

	public PortTrl getPoa() {
		return poa;
	}

	public void setPoa(PortTrl poa) {
		this.poa = poa;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getLinkRecordId() {
		return linkRecordId;
	}

	public void setLinkRecordId(Integer linkRecordId) {
		this.linkRecordId = linkRecordId;
	}

	public Linkrecord getLinkRecord() {
		return linkRecord;
	}

	public void setLinkRecord(Linkrecord linkRecord) {
		this.linkRecord = linkRecord;
	}

	public String getLinkRecordCode() {
		return linkRecordCode;
	}

	public void setLinkRecordCode(String linkRecordCode) {
		this.linkRecordCode = linkRecordCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
    
    


}
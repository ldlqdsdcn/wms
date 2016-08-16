package com.delmar.crm.model;

import java.math.BigDecimal;

import com.delmar.base.model.Currency;
import com.delmar.base.model.Unit;
import com.delmar.core.model.CoreModel;

/**
 * CRM模块 销售员交接单当中的价格
 * @author Charles 2015年4月20日 上午9:17:51
 * @version V2.0
 */
public class SaleSheetQuota extends CoreModel {
	
	private Integer sellbuyId;
	private String sellbuyName;
	private String freight;
	private Integer chargeId;
	private String chargeName;
	private BigDecimal price;
	private Integer currencyId;
	private Currency currency;
	private BigDecimal currencyRate;
	private Integer priceUnitId;
	private Unit privceUnit;
	private Integer salesheetId;
	private String remark;
	private Integer flag;
	
	
	public Integer getSellbuyId() {
		return sellbuyId;
	}
	public void setSellbuyId(Integer sellbuyId) {
		this.sellbuyId = sellbuyId;
	}
	public String getSellbuyName() {
		return sellbuyName;
	}
	public void setSellbuyName(String sellbuyName) {
		this.sellbuyName = sellbuyName;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public Integer getChargeId() {
		return chargeId;
	}
	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}
	public Integer getPriceUnitId() {
		return priceUnitId;
	}
	public void setPriceUnitId(Integer priceUnitId) {
		this.priceUnitId = priceUnitId;
	}
	public Unit getPrivceUnit() {
		return privceUnit;
	}
	public void setPrivceUnit(Unit privceUnit) {
		this.privceUnit = privceUnit;
	}
	public Integer getSalesheetId() {
		return salesheetId;
	}
	public void setSalesheetId(Integer salesheetId) {
		this.salesheetId = salesheetId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	

}

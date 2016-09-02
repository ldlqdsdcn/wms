/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.busmodel;

import java.math.BigDecimal;

import com.delmar.base.model.Currency;

/**
 * @author 刘大磊 2014年12月23日 上午9:35:31
 * 详细报价
 */
public class QuotaBusModel {
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * @return the price
	 */
	private Currency curr;
	/**
	 * @return the curr
	 */
	public Currency getCurr() {
		return curr;
	}
	/**
	 * @param curr the curr to set
	 */
	public void setCurr(Currency curr) {
		this.curr = curr;
	}
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public BigDecimal getLocalPrice()
	{
	  if (curr==null)
		  return price;
	  else
   	      return price.multiply(curr.getRate()) ;
	}
	
	
}

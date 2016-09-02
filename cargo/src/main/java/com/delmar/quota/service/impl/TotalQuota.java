/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.delmar.quota.service.busmodel.QuotaBusModel;

/**
 * @author 刘大磊 2014年12月25日 上午10:42:22
 * 每个报价单总价格
 */
public class TotalQuota {
	/**
	 * 
	 */
	public TotalQuota(int masterrateId,List<QuotaBusModel> quotaList) {
		this.masterrateId=masterrateId;
		this.quotaList=quotaList;
	}
	/**
	 * 主报价单Id
	 */
	private int masterrateId;
	
	/**
	 * 报价明细
	 */
	private List<QuotaBusModel> quotaList;
	
	/**
	 * 备注
	 * */
	private String remark;
	/**
	 * @return the masterrateId
	 */
	public int getMasterrateId() {
		return masterrateId;
	}
	/**
	 * @param masterrateId the masterrateId to set
	 */
	public void setMasterrateId(int masterrateId) {
		this.masterrateId = masterrateId;
	}
	/**
	 * @return the quotaList
	 */
	public List<QuotaBusModel> getQuotaList() {
		return quotaList;
	}
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @param quotaList the quotaList to set
	 */
	public void setQuotaList(List<QuotaBusModel> quotaList) {
		this.quotaList = quotaList;
	}
	public BigDecimal getTotalPrice()
	{
		BigDecimal total=new BigDecimal(0);
		for(QuotaBusModel busModel:quotaList)
		{
			total.add(busModel.getPrice().multiply(busModel.getCurr().getRate()));
		}
		return total;
	}
}

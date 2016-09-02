/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.busmodel;

import java.util.List;

/**
 * @author 刘大磊 2014年12月23日 上午9:35:01
 */
public class QuotaBusModelResult  {
	
	private int masterId;
	/**
	 * 报价列表
	 */
	private List<QuotaBusModel> quotaList;
	/**
	 * 备注
	 */
	private String remark;

	
	

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	/**
	 * @return the quotaList
	 */
	public List<QuotaBusModel> getQuotaList() {
		return quotaList;
	}

	/**
	 * @param quotaList the quotaList to set
	 */
	public void setQuotaList(List<QuotaBusModel> quotaList) {
		this.quotaList = quotaList;
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
}

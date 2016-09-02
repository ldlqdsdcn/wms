
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.rate.service;

import java.math.BigDecimal;

import com.delmar.core.service.CoreService;
import com.delmar.rate.model.Ratedetail;

/**
 * @author 刘大磊 2014-12-22 17:09:41
 */
public interface RatedetailService extends CoreService<Ratedetail> {

	
	public Ratedetail getRateDetailBy(int masterId,String chargeCode,String fcllcl) throws Exception;
	
	/**
	 * 获得最终报价
	 * @param totalPrice   总报价
	 * @param minValue   最小值
	 * @param maxValue  最大值
	 * @return
	 */
	public BigDecimal caculatePrice(BigDecimal totalPrice,BigDecimal minValue,BigDecimal maxValue);
}

/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.rate.service;

import com.delmar.core.service.CoreService;
import com.delmar.rate.model.Ratemarkup;

/**
 * @author 刘大磊 2014-12-22 17:09:41
 */
public interface RatemarkupService extends CoreService<Ratemarkup> {
	/**
	 * @param nameaccountId 用户名
	 * @param ratemasterId 报价单编号
	 * @param chargenameId 报价费用 
	 * @return
	 */
	public Ratemarkup getRatemarkup(int nameaccountId,int ratemasterId,int chargenameId,int freighttype);
	
	

}
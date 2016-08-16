/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.quota.service;

import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;

/**
 * @author 刘大磊 2014年12月25日 上午8:47:37
 */
public interface QuotaService {
	/**
	 * @param quotaBusParam
	 * @return
	 * 
	 */
	public QuotaBusModelResult quote(QuotaBusParam quotaBusParam) throws QuotaParamException,DataNotFondException;
}

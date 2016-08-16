
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.CompanysSop;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-18 11:41:34
 */
public interface CompanysSopService extends CoreService<CompanysSop> {
	/**
	 * @param ids
	 */
	public void deleteCompanysSopList(Integer[] ids);
}
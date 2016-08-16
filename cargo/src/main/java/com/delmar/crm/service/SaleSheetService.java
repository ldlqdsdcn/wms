
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service;

import com.delmar.crm.model.SaleSheet;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-04-21 08:38:38
 */
public interface SaleSheetService extends CoreService<SaleSheet> {
	/**
	 * @param ids
	 */
	public void deleteSaleSheetList(Integer[] ids);
}
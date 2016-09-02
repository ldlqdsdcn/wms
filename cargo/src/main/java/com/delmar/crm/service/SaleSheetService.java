
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
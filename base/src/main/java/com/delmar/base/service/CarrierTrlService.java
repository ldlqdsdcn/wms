
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.CarrierTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-02-05 16:23:46
 */
public interface CarrierTrlService extends CoreService<CarrierTrl> {
	/**
	 * @param ids
	 */
	public void deleteCarrierTrlList(Integer[] ids);
}

/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.UnitExtra;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-16 18:01:56
 */
public interface UnitExtraService extends CoreService<UnitExtra> {
	/**
	 * @param ids
	 */
	public void deleteUnitExtraList(Integer[] ids);
}

/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
	void deleteUnitExtraList(Integer[] ids);
}

/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.CityTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-02-09 19:51:15
 */
public interface CityTrlService extends CoreService<CityTrl> {
	/**
	 * @param ids
	 */
	public void deleteCityTrlList(Integer[] ids);
}
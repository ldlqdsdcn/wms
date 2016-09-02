
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.CityTrl;
import com.delmar.base.model.PortTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-02-06 16:25:41
 */
public interface PortTrlService extends CoreService<PortTrl> {
	/**
	 * @param ids
	 */
	public void deletePortTrlList(Integer[] ids);
	
	public PortTrl getPortTrl(String language,Integer portId);
}
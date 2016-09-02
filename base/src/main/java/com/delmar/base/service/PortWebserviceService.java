
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.Port;
import com.delmar.base.model.PortWebservice;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-19 11:21:06
 */
public interface PortWebserviceService extends CoreService<PortWebservice> {
	/**
	 * @param ids
	 */
	public void deletePortWebserviceList(Integer[] ids);
	
	/**
	 * 根据关联表Port Id 选择系统中对应的Port
	 * @param name
	 * @param integer 
	 * @param serviceType 
	 * @return
	 */
	public Port getPortByPortWebserviceName(String name, String serviceType, Integer integer);
}
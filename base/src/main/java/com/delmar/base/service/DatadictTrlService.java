
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-02-06 11:07:48
 */
public interface DatadictTrlService extends CoreService<DatadictTrl> {
	/**
	 * @param ids
	 */
	public void deleteDatadictTrlList(Integer[] ids);
	public void updateIndexOrder(Integer indexOrder,Integer datadictId) ;

}
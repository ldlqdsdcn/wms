
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.EventTypeMode;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-25 15:22:29
 */
public interface EventTypeModeService extends CoreService<EventTypeMode> {
	/**
	 * @param ids
	 */
	public void deleteEventTypeModeList(Integer[] ids);
}
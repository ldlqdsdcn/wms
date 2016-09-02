/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import java.util.List;

import com.delmar.base.model.EventType;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2014-12-31 09:57:16
 */
public interface EventTypeDao extends CoreDao<EventType> {

	/**
	 * @param mode
	 * @return
	 */
	List<EventType> getEventTypeListByMode(String mode);

}

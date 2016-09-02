/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.dao;

import com.delmar.sys.model.Scheduled;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-08-24 16:48:41
 */
public interface ScheduledDao extends CoreDao<Scheduled> {

	/**
	 * @param className
	 * @return
	 */
	Scheduled getScheduledByClassName(String className);

}

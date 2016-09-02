/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.dao;

import com.delmar.sys.model.MobileClient;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-07-21 18:27:53
 */
public interface MobileClientDao extends CoreDao<MobileClient> {

	/**
	 * @return
	 */
	MobileClient getMobileClientByMaxVersion();

}

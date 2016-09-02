/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.dao;

import com.delmar.sys.model.UserSession;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-01-13 11:57:59
 */
public interface UserSessionDao extends CoreDao<UserSession> {

	/**
	 * @param id
	 * @return
	 */
	UserSession getPreLoginSession(Integer userId);

}


/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.UserSession;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-13 11:57:59
 */
public interface UserSessionService extends CoreService<UserSession> {

	/**
	 * @param userId
	 * @return
	 */
	UserSession getPreLoginSession(Integer userId);

}
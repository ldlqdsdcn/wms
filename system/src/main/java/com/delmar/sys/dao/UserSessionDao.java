/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
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

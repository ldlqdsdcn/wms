/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserSessionDao;
import com.delmar.sys.model.UserSession;
import com.delmar.sys.service.UserSessionService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 11:57:59
 */
@Service("userSessionService")
public class UserSessionServiceImpl extends CoreServiceImpl<UserSession> implements
		UserSessionService {
	@Autowired
	private UserSessionDao userSessionDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserSession> getCoreDao() {
		return userSessionDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UserSessionService#getPreLoginSession(java.lang.Integer)
	 */
	@Override
	public UserSession getPreLoginSession(Integer userId) {
		return userSessionDao.getPreLoginSession(userId);
	}

	
}

/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserRoleDao;
import com.delmar.sys.model.UserRole;
import com.delmar.sys.service.UserRoleService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends CoreServiceImpl<UserRole> implements
		UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserRole> getCoreDao() {
		return userRoleDao;
	}

	
}

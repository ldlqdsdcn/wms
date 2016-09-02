/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserSubstituteDao;
import com.delmar.sys.model.UserSubstitute;
import com.delmar.sys.service.UserSubstituteService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-07-07 11:00:49
 */
@Service("userSubstituteService")
public class UserSubstituteServiceImpl extends CoreServiceImpl<UserSubstitute> implements
		UserSubstituteService {
	@Autowired
	private UserSubstituteDao userSubstituteDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserSubstitute> getCoreDao() {
		return userSubstituteDao;
	}
	public void deleteUserSubstituteList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			userSubstituteDao.deleteByPrimaryKey(id);
		}
	}
	
}

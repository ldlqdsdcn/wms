/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UsergroupDao;
import com.delmar.sys.model.Usergroup;
import com.delmar.sys.service.UsergroupService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-20 16:14:21
 */
@Service("usergroupService")
public class UsergroupServiceImpl extends CoreServiceImpl<Usergroup> implements
		UsergroupService {
	@Autowired
	private UsergroupDao usergroupDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Usergroup> getCoreDao() {
		return usergroupDao;
	}
	public void deleteUsergroups(Integer[] ids)
	{
			if(ids==null||ids.length==0)
			{
				return;
			}
			
			for(Integer id:ids)
			{
				this.usergroupDao.deleteByPrimaryKey(id);
			}
	}
	
}

/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserFootmarkDao;
import com.delmar.sys.model.UserFootmark;
import com.delmar.sys.service.UserFootmarkService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-07-10 14:40:37
 */
@Service("userFootmarkService")
public class UserFootmarkServiceImpl extends CoreServiceImpl<UserFootmark> implements
		UserFootmarkService {
	@Autowired
	private UserFootmarkDao userFootmarkDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserFootmark> getCoreDao() {
		return userFootmarkDao;
	}
	public void deleteUserFootmarkList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			userFootmarkDao.deleteByPrimaryKey(id);
		}
	}
	
}

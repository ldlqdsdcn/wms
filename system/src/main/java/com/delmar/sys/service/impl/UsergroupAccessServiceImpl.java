/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.CoreService;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.UsergroupAccessDao;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UsergroupAccess;
import com.delmar.sys.service.UsergroupAccessService;

/**
 * @author 刘大磊 22015-01-22 10:48:19
 */
@Service("usergroupAccessService")
public class UsergroupAccessServiceImpl extends CoreServiceImpl<UsergroupAccess> implements
UsergroupAccessService {
	@Autowired
	private UsergroupAccessDao usergourpAccessDao;
	/** (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UsergroupAccess> getCoreDao() {
		return usergourpAccessDao;
	}
	public void deleteUsergourpAccessList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			usergourpAccessDao.deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UsergroupAccessService#selectUserAccessUserId(java.lang.Integer, java.lang.Integer)
	 */
	
	@Override
	public List<Integer> selectUserAccessUserId(Integer orgId, Integer userId) {

		return usergourpAccessDao.selectUserAccessUserId(orgId, userId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UsergroupAccessService#selectUserAccessUserId2(java.lang.Integer)
	 */
	@Override
	public List<Integer> selectUserAccessUserId2(Integer userId) {

		return usergourpAccessDao.selectUserAccessUserId2(userId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UsergroupAccessService#getUserUsergroupId(java.lang.Integer)
	 */
	@Override
	public List<User> getUserUsergroupId(Integer id) {
		List<UsergroupAccess> usergroupAccessList=usergourpAccessDao.getUserGroupAccessByUserGroupId(id);
		
		 List<User> userList=new ArrayList<User>();
		 if(usergroupAccessList!=null)
		 for(UsergroupAccess ua:usergroupAccessList)
		 {
			 userList.add(ua.getUser());
		 }
		 
		return userList;
	}
	
}

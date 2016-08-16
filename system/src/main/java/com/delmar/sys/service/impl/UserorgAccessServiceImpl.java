/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserorgAccessDao;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.UserorgAccessService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("userorgAccessService")
public class UserorgAccessServiceImpl extends CoreServiceImpl<UserorgAccess> implements
		UserorgAccessService {
	@Autowired
	private UserorgAccessDao userorgAccessDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserorgAccess> getCoreDao() {
		return userorgAccessDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UserorgAccessService#selectUserorgAccessByUserId(java.lang.Integer)
	 */
	@Override
	public List<UserorgAccess> selectUserorgAccessByUserId(Integer userId) {

		return userorgAccessDao.selectUserorgAccessByUserId(userId);
	}

	
}

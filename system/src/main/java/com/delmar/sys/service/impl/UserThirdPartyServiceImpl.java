/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserThirdPartyDao;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-05-29 15:11:15
 */
@Service("userThirdPartyService")
public class UserThirdPartyServiceImpl extends CoreServiceImpl<UserThirdParty> implements
		UserThirdPartyService {
	@Autowired
	private UserThirdPartyDao userThirdPartyDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserThirdParty> getCoreDao() {
		return userThirdPartyDao;
	}
	public void deleteUserThirdPartyList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			userThirdPartyDao.deleteByPrimaryKey(id);
		}
	}
	
}

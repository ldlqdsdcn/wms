/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CorePageDao;
import com.delmar.core.model.CorePage;
import com.delmar.core.service.CorePageService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22016-08-26 17:08:24
 */
@Service("corePageService")
public class CorePageServiceImpl extends CoreServiceImpl<CorePage> implements
		CorePageService {
	@Autowired
	private CorePageDao corePageDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CorePage> getCoreDao() {
		return corePageDao;
	}
	public void deleteCorePageList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			corePageDao.deleteByPrimaryKey(id);
		}
	}
	
}

/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.ChangelogDao;
import com.delmar.core.model.Changelog;
import com.delmar.core.service.ChangelogService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-09 10:48:25
 */
@Service("changelogService")
public class ChangelogServiceImpl extends CoreServiceImpl<Changelog> implements
		ChangelogService {
	@Autowired
	private ChangelogDao changelogDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Changelog> getCoreDao() {
		return changelogDao;
	}

	
}

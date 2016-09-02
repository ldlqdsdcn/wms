/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.LanguageTrlDao;
import com.delmar.core.model.LanguageTrl;
import com.delmar.core.service.LanguageTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-02-05 11:57:46
 */
@Service("languageTrlService")
public class LanguageTrlServiceImpl extends CoreServiceImpl<LanguageTrl> implements
		LanguageTrlService {
	@Autowired
	private LanguageTrlDao languageTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<LanguageTrl> getCoreDao() {
		return languageTrlDao;
	}
	public void deleteLanguageTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			languageTrlDao.deleteByPrimaryKey(id);
		}
	}
	
}

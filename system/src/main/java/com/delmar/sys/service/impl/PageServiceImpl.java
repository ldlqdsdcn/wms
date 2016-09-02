/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.PageDao;
import com.delmar.sys.model.Page;
import com.delmar.sys.service.PageService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("pageService")
public class PageServiceImpl extends CoreServiceImpl<Page> implements
		PageService {
	@Autowired
	private PageDao pageDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Page> getCoreDao() {
		return pageDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.PageService#deletePages(java.lang.Integer[])
	 */
	@Override
	public void deletePages(Integer[] ids) {
		if(ids!=null)
		for(Integer id:ids)
		{
			pageDao.deleteByPrimaryKey(id);
		}
		
	}

	
}

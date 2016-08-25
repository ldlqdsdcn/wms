/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.PageDao;
import com.delmar.core.model.Page;
import com.delmar.core.service.PageService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22016-08-25 16:30:43
 */
@Service("corePageService")
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
	public void deletePageList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			pageDao.deleteByPrimaryKey(id);
		}
	}
	
}

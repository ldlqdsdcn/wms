/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.SearchDao;
import com.delmar.core.model.Search;
import com.delmar.core.service.SearchService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-02 10:59:26
 */
@Service("searchService")
public class SearchServiceImpl extends CoreServiceImpl<Search> implements
		SearchService {
	@Autowired
	private SearchDao searchDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Search> getCoreDao() {
		return searchDao;
	}
	public void deleteSearchList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			searchDao.deleteByPrimaryKey(id);
		}
	}
	
}

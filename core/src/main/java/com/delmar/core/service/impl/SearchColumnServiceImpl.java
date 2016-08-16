/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.SearchColumnDao;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchColumnService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-02 10:59:26
 */
@Service("searchColumnService")
public class SearchColumnServiceImpl extends CoreServiceImpl<SearchColumn> implements
		SearchColumnService {
	@Autowired
	private SearchColumnDao searchColumnDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<SearchColumn> getCoreDao() {
		return searchColumnDao;
	}
	public void deleteSearchColumnList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			searchColumnDao.deleteByPrimaryKey(id);
		}
	}
	
}

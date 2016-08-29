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
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.dao.SearchColumnDao;
/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
@Service("searchService")
public class SearchServiceImpl extends CoreServiceImpl<Search> implements
		SearchService {
	@Autowired
	private SearchDao searchDao;
    @Autowired
    private SearchColumnDao searchColumnDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Search> getCoreDao() {
		return searchDao;
	}
	public void deleteSearchList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveSearch(Search search,List<SearchColumn> searchColumnList) {
	Integer id=save(search);
		for(SearchColumn searchColumn: searchColumnList)
		{
			searchColumn.setSearchId(id);
			searchColumnDao.save(searchColumn);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> searchColumnParam=new HashMap<String,Object>();
searchColumnParam.put("searchId",id);
searchColumnDao.deleteByExample(searchColumnParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<SearchColumn> getSearchColumnListBySearchId(Integer searchId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("searchId",searchId);
		return searchColumnDao.selectByExample(param);
	}
}

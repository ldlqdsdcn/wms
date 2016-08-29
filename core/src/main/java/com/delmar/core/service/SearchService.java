
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.CommonSearchParam;
import com.delmar.core.model.CommonSearchResult;
import com.delmar.core.model.Search;
import com.delmar.core.service.CoreService;
import com.delmar.core.model.SearchColumn;
import java.util.List;
/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
public interface SearchService extends CoreService<Search> {
	/**
	 * @param ids
	 */
	public void deleteSearchList(Integer[] ids);
    public List<SearchColumn> getSearchColumnListBySearchId(Integer searchId);

	public Integer saveSearch(Search search,List<SearchColumn> searchColumnList);
	public List<CommonSearchResult> selectCommonList(CommonSearchParam commonSearchParam);
	public List<CommonSearchResult> getCommonSearchListByColumnId(Integer columnId);
	public Search getSearchByPageUrl(String pageUrl);


}

/******************************************************************************
 * 刘大磊  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.dto.SearchColumnDto;
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
	 void deleteSearchList(Integer[] ids);
     List<SearchColumn> getSearchColumnListBySearchId(Integer searchId);

	 Integer saveSearch(Search search,List<SearchColumn> searchColumnList);
	 List<CommonSearchResult> selectCommonList(CommonSearchParam commonSearchParam);
	 List<CommonSearchResult> getCommonSearchListByColumnId(Integer columnId);
	 Search getSearchByPageUrl(String pageUrl);

	 String buildSqlBySearchColumnList(List<SearchColumnDto> searchColumnDtoList);

}
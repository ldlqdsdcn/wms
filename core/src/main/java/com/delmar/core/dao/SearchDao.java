/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.dao;

import com.delmar.core.model.CommonSearchParam;
import com.delmar.core.model.CommonSearchResult;
import com.delmar.core.model.Search;
import com.delmar.core.dao.CoreDao;

import java.util.List;

/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
public interface SearchDao extends CoreDao<Search> {
    List<CommonSearchResult> selectCommonList(CommonSearchParam commonSearchParam);

}

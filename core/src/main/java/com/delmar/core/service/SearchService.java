
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Search;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-02 10:59:26
 */
public interface SearchService extends CoreService<Search> {
	/**
	 * @param ids
	 */
	public void deleteSearchList(Integer[] ids);
}
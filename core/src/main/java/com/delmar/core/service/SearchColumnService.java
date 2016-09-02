
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-02 10:59:26
 */
public interface SearchColumnService extends CoreService<SearchColumn> {
	/**
	 * @param ids
	 */
	 void deleteSearchColumnList(Integer[] ids);
}
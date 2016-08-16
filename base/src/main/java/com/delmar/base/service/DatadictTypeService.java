
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.DatadictType;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-21 10:29:53
 */
public interface DatadictTypeService extends CoreService<DatadictType> {
	/**
	 * @param ids
	 */
	public void deleteDatadictTypeList(Integer[] ids);
	
	public DatadictType getDatadictType(String value);
	
}
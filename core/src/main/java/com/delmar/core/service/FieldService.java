
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Field;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2016-08-25 16:30:43
 */
public interface FieldService extends CoreService<Field> {
	/**
	 * @param ids
	 */
	public void deleteFieldList(Integer[] ids);
}
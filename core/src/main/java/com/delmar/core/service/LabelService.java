
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Label;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2016-08-27 08:23:58
 */
public interface LabelService extends CoreService<Label> {
	/**
	 * @param ids
	 */
	public void deleteLabelList(Integer[] ids);
}
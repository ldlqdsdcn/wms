
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service;

import com.delmar.core.service.CoreService;
import com.delmar.corebus.model.EBusinessAssign;

/**
 * @author 刘大磊 2015-03-18 16:04:12
 */
public interface EBusinessAssignService extends CoreService<EBusinessAssign> {
	/**
	 * @param ids
	 */
	public void deleteEBusinessAssignList(Integer[] ids);
}
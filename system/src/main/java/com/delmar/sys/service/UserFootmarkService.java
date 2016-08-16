
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.UserFootmark;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-07-10 14:40:37
 */
public interface UserFootmarkService extends CoreService<UserFootmark> {
	/**
	 * @param ids
	 */
	public void deleteUserFootmarkList(Integer[] ids);
}
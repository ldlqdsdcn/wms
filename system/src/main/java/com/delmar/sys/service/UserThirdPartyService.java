
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.UserThirdParty;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-05-29 15:11:15
 */
public interface UserThirdPartyService extends CoreService<UserThirdParty> {
	/**
	 * @param ids
	 */
	public void deleteUserThirdPartyList(Integer[] ids);
}
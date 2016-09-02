
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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

/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.UserExtra;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-08-27 18:03:14
 */
public interface UserExtraService extends CoreService<UserExtra> {
	/**
	 * @param ids
	 */
	public void deleteUserExtraList(Integer[] ids);
	public String getExtraPropValue(String propCode,Integer userId);
}
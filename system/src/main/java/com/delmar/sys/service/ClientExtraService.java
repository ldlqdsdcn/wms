
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.ClientExtra;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-08-27 18:04:57
 */
public interface ClientExtraService extends CoreService<ClientExtra> {
	/**
	 * @param ids
	 */
	public void deleteClientExtraList(Integer[] ids);

	public String getExtraPropValue(String propCode,Integer clientId);

}
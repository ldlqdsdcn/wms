
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.Client;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-23 14:47:25
 */
public interface ClientService extends CoreService<Client> {

	/**
	 * @param ids
	 */
	void deleteList(Integer[] ids);
	
	public Client getClientByValue(String value);

}
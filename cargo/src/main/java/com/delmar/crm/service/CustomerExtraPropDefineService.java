
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.crm.model.CustomerExtraPropDefine;

/**
 * @author 刘大磊 2015-09-08 15:44:27
 */
public interface CustomerExtraPropDefineService extends CoreService<CustomerExtraPropDefine> {
	/**
	 * @param ids
	 */
	public void deleteCustomerExtraPropDefineList(Integer[] ids);
	public List selectPropByUserId(Integer userId);
}
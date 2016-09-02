
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service;

import java.util.List;

import com.delmar.crm.model.CustomerTrace;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-08-14 14:07:47
 */
public interface CustomerTraceService extends CoreService<CustomerTrace> {
	/**
	 * @param ids
	 */
	public void deleteCustomerTraceList(Integer[] ids);
	public List<CustomerTrace> selectByCustomerId(Integer customerId);
	public void saveCustomerTrace(Integer currentStatusId,Integer oldStatusId,CustomerTrace customerTrace);
	public void deleteByCustomerId(Integer customerId);
	public void deleteById(Integer id);
}
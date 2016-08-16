
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service;

import com.delmar.core.service.CoreService;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.crm.model.Linkman;

/**
 * @author 刘大磊 2015-01-23 17:47:07
 */
public interface CustomerService extends CoreService<Customer> {
	/**
	 * @param ids
	 */
	public void deleteCustomerList(Integer[] ids);
	
	public void saveCustomer(Customer customer,Linkman linkman,CustomerExtra exter);
	public void deleteById(Integer id);
	
	public String getNamebyId(Integer id);
}
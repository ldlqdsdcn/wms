/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerExterDao;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.crm.service.CustomerExterService;

/**
 * @author 刘大磊 22015-03-04 16:06:05
 */
@Service("customerExterService")
public class CustomerExterServiceImpl extends CoreServiceImpl<CustomerExtra> implements
		CustomerExterService {
	@Autowired
	private CustomerExterDao customerExterDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CustomerExtra> getCoreDao() {
		return customerExterDao;
	}
	public void deleteCustomerExterList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			customerExterDao.deleteByPrimaryKey(id);
		}
	}
	
	public void updateTimesData(Integer id)
	{
		customerExterDao.updateTimesData(id);	
	}
			
	
}

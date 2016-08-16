package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.CustomerBusinessDao;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.cargo.service.CustomerBusinessService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月26日 下午9:07:59 
 * 类说明 
 */

@Service("customerBusinessService")
public class CustomerBusinessServiceImpl extends CoreHibernateServiceImpl<CustomerBusiness> implements CustomerBusinessService {

	@Autowired
	private CustomerBusinessDao customerBusinessDao;
	
	
	@Override
	public CoreHibernateDao<CustomerBusiness> getCoreDao()
	{
	   return customerBusinessDao;	
	}
}

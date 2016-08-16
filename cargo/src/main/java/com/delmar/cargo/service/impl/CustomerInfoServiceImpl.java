package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.CustomerInfoDao;
import com.delmar.cargo.model.CustomerInfo;
import com.delmar.cargo.service.CustomerInfoService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:27:38 
 * 类说明 
 */
@Service("customerInfoService")
public class CustomerInfoServiceImpl extends CoreHibernateServiceImpl<CustomerInfo> implements CustomerInfoService {

	@Autowired
	private CustomerInfoDao customerInfoDao;
	
	
	@Override
	public CoreHibernateDao<CustomerInfo> getCoreDao()
	{
	   return customerInfoDao;	
	}
}

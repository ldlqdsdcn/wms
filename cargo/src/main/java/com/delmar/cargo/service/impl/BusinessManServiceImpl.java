package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.BusinessManDao;
import com.delmar.cargo.model.BussinessMan;
import com.delmar.cargo.service.BusinessManService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 上午11:07:52 
 * 类说明 
 */
@Service("businessManService")
public class BusinessManServiceImpl  extends CoreHibernateServiceImpl<BussinessMan> implements BusinessManService {

	@Autowired
	private BusinessManDao businessManDao;
	
	
	@Override
	public CoreHibernateDao<BussinessMan> getCoreDao()
	{
	   return businessManDao;	
	}
}

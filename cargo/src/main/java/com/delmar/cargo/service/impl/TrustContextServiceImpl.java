package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.TrustContextDao;
import com.delmar.cargo.model.TrustContext;
import com.delmar.cargo.service.TrustContextService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:28:39 
 * 类说明 
 */
@Service("trustContextService")
public class TrustContextServiceImpl extends CoreHibernateServiceImpl<TrustContext> implements TrustContextService {

	@Autowired
	private TrustContextDao trustContextDao;
	
	
	@Override
	public CoreHibernateDao<TrustContext> getCoreDao()
	{
	   return trustContextDao;	
	}
	
	public String getTrustFileCodeByHawb(String hawb)
	{
		return trustContextDao.getTrustFileCodeByHawb(hawb);	
	}
	
	public String getTrustFileCodeByMawb(String mawb)
	{
		return trustContextDao.getTrustFileCodeByMawb(mawb);
	}


}

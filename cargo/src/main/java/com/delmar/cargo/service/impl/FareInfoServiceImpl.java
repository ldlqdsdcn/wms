package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.FareInfoDao;
import com.delmar.cargo.model.FareInfo;
import com.delmar.cargo.service.FareInfoService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:27:47 
 * 类说明 
 */
@Service("fareInfoService")
public class FareInfoServiceImpl extends CoreHibernateServiceImpl<FareInfo> implements FareInfoService {

	@Autowired
	private FareInfoDao fareInfoDao;
	
	
	@Override
	public CoreHibernateDao<FareInfo> getCoreDao()
	{
	   return fareInfoDao;	
	}
}

package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.FareNameInfoDao;
import com.delmar.cargo.model.FareNameInfo;
import com.delmar.cargo.service.FareNameInfoService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:27:58 
 * 类说明 
 */
@Service("fareNameInfoService")
public class FareNameInfoServiceImpl extends CoreHibernateServiceImpl<FareNameInfo> implements FareNameInfoService {

	@Autowired
	private FareNameInfoDao fareNameInfoDao;
	
	
	@Override
	public CoreHibernateDao<FareNameInfo> getCoreDao()
	{
	   return fareNameInfoDao;	
	}
}

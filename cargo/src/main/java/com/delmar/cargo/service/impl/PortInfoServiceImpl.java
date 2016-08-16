package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.PortInfoDao;
import com.delmar.cargo.model.PortInfo;
import com.delmar.cargo.service.PortInfoService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:28:16 
 * 类说明 
 */
@Service("portInfoService")
public class PortInfoServiceImpl extends CoreHibernateServiceImpl<PortInfo> implements PortInfoService {

	@Autowired
	private PortInfoDao portInfoDao;
	
	
	@Override
	public CoreHibernateDao<PortInfo> getCoreDao()
	{
	   return portInfoDao;	
	}
}

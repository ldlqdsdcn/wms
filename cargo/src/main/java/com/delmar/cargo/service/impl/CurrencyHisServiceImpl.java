package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.CurrencyHisDao;
import com.delmar.cargo.model.CurrencyHis;
import com.delmar.cargo.service.CurrencyHisService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:07:35 
 * 类说明 
 */
@Service("cargoCurrencyHisService")
public class CurrencyHisServiceImpl extends CoreHibernateServiceImpl<CurrencyHis> implements CurrencyHisService {

	@Autowired
	private CurrencyHisDao currencyHisDao;
	
	
	@Override
	public CoreHibernateDao<CurrencyHis> getCoreDao()
	{
	   return currencyHisDao;	
	}
}

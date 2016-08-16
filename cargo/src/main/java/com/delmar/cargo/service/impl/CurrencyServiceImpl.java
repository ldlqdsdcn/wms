package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.CurrencyDao;
import com.delmar.cargo.model.Currency;
import com.delmar.cargo.service.CurrencyService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:27:25 
 * 类说明 
 */
@Service("cargoCurrencyService")
public class CurrencyServiceImpl extends CoreHibernateServiceImpl<Currency> implements CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;
	
	
	@Override
	public CoreHibernateDao<Currency> getCoreDao()
	{
	   return currencyDao;	
	}
}

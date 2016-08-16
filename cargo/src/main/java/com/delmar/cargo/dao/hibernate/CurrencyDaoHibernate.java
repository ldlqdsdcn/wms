package com.delmar.cargo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.CurrencyDao;
import com.delmar.cargo.model.Currency;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:14:59 
 * 类说明 
 */
@Repository("cargoCurrencyDao") 
public class CurrencyDaoHibernate   extends CoreHibernateDaoImpl<Currency> implements CurrencyDao {
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return Currency.class;
	}

}

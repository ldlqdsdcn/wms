package com.delmar.cargo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.CurrencyHisDao;
import com.delmar.cargo.model.CurrencyHis;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:15:11 
 * 类说明 
 */
@Repository("cargoCurrencyHisDao") 
public class CurrencyHisDaoHibernate   extends CoreHibernateDaoImpl<CurrencyHis> implements CurrencyHisDao {
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return CurrencyHis.class;
	}

}

/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.cargo.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.base.model.City;
import com.delmar.cargo.dao.CityDao;
import com.delmar.cargo.model.CityInfo;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/**
 * @author 刘大磊 2015年4月27日 下午2:24:47
 */
@Repository("cargoCityDao") 
public class CityDaoHibernate  extends CoreHibernateDaoImpl<CityInfo>  implements CityDao{
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.cargo.dao.CityDao#getCityList()
	 */
	public List<CityInfo> getCityList() {
	 Session session=sessionFactory.openSession();
	 Query query=session.createQuery("from CityInfo order by id");
	 
	 return query.setFirstResult(2000).setMaxResults(100).list();
		
	
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return CityInfo.class;
	}

	
	
	
}

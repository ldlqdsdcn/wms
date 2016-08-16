package com.delmar.cargo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.CustomerBusinessDao;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月26日 下午9:05:15 
 * 类说明 
 */
@Repository("customerBusinessDao") 
public class CustomerBusinessDaoHibernate extends CoreHibernateDaoImpl<CustomerBusiness> implements CustomerBusinessDao {

	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return CustomerBusiness.class;
	}

	
}

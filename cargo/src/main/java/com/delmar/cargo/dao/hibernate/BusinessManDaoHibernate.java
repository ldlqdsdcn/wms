package com.delmar.cargo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.BusinessManDao;
import com.delmar.cargo.model.BussinessMan;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 上午11:06:07 
 * 类说明 
 */
@Repository("businessManDao") 
public class BusinessManDaoHibernate extends CoreHibernateDaoImpl<BussinessMan> implements BusinessManDao {

	
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return BussinessMan.class;
	}

}

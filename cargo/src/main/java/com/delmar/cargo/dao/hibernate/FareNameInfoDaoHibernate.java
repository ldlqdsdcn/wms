package com.delmar.cargo.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.FareNameInfoDao;
import com.delmar.cargo.model.FareNameInfo;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:15:41 
 * 类说明 
 */
@Repository("fareNameInfoDao") 
public class FareNameInfoDaoHibernate   extends CoreHibernateDaoImpl<FareNameInfo> implements FareNameInfoDao {
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return FareNameInfo.class;
	}

}

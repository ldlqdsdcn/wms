package com.delmar.station.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delmar.common.dao.BaseHibernateDao;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.station.dao.EDIResponseInfoDao;
import com.delmar.station.model.EDIResponseInfo;
@Repository("EDIResponseInfoDaoHibernate")
public class EDIResponseInfoDaoHibernate extends BaseHibernateDao<EDIResponseInfo> implements EDIResponseInfoDao{

	@Override
	public Class getModelClass() {
		return EDIResponseInfo.class;
	}

	public EDIResponseInfo saveOrUpdate(EDIResponseInfo edirInfo) {
		if (edirInfo != null) {
			if (edirInfo.isnew()) {
				getCurrentSession().save(edirInfo);
				getCurrentSession().flush();
			} else {
				getCurrentSession().merge(edirInfo);
				
			}
			getCurrentSession().clear();
			return edirInfo;
		}
		
		return edirInfo;
	}

	public EDIResponseInfo getEDIRByTrustFileCode(String trustFileCode) {
		
		String sql = " from EDIResponseInfo where trustFileCode='" + trustFileCode + "'  " +
	    			"and EDIType='DCMS'";
		
		List<EDIResponseInfo> list = getCurrentSession().createQuery(sql).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return new EDIResponseInfo();
	}

	public void updateTrustFileInfoFCRDate(String currentDate, String trustFileCode) {
		StringBuffer sqlStr = new StringBuffer();
	    sqlStr.append("update TrustFileInfo set jcfinishdate = '" +currentDate+"' where trustfilecode= '" +trustFileCode+ "'"); 
	    getCurrentSession().createSQLQuery(sqlStr.toString()).executeUpdate();
	    getCurrentSession().flush();
	    getCurrentSession().clear();
	}

}

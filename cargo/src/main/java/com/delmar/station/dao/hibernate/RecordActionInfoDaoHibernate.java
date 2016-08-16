package com.delmar.station.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.delmar.common.dao.BaseHibernateDao;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.station.dao.RecordActionInfoDao;
import com.delmar.station.model.RecordActionInfo;

@Repository("recordActionInfoDaoHibernate")
public class RecordActionInfoDaoHibernate extends BaseHibernateDao<RecordActionInfo> implements RecordActionInfoDao{

	@Override
	public Class getModelClass() {
		return RecordActionInfo.class;
	}

	public RecordActionInfo saveOrUpdate(RecordActionInfo inf) {
		
		if (inf != null) {
			if (inf.getRid() == null || inf.getRid().length() <= 0) {
				inf.setRid(String.valueOf(getSyscreateId("RecordActionInfo")));
				getCurrentSession().save(inf);
				getCurrentSession().flush();
			} else {
				getCurrentSession().merge(inf);
			}
			getCurrentSession().clear();
			return inf;
		}
		
		return null;
	}

}

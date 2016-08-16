package com.delmar.station.dao;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.station.model.RecordActionInfo;

public interface RecordActionInfoDao extends CoreHibernateDao<RecordActionInfo>{

	public RecordActionInfo saveOrUpdate(RecordActionInfo inf);

}

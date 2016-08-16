package com.delmar.station.dao;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.station.model.EDIResponseInfo;

public interface EDIResponseInfoDao extends CoreHibernateDao<EDIResponseInfo>{

	public EDIResponseInfo saveOrUpdate(EDIResponseInfo edirInfo);

	public EDIResponseInfo getEDIRByTrustFileCode(String trustFileCode);

	public void updateTrustFileInfoFCRDate(String currentDate,
			String trustFileCode);

}

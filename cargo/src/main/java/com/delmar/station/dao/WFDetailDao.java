package com.delmar.station.dao;

import java.util.List;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.model.HbnHsql;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.model.WFDetail;

public interface WFDetailDao extends CoreHibernateDao<WFDetail>{

	public WFDetail selectByPrimaryKey(Integer id);

	public WFDetail saveOrUpdate(WFDetail wfDetail);

	public void deleteByPrimaryKey(Integer id);

	public List<WFDetail> getWFDetailByMasterId(Integer masterID);
	
	public List<ObjWFDetail> searchWFDetailList(HbnHsql hbnhsql);

	public List<ObjWFDetail> searchWFDetailList(Integer masterId);

	public ObjWFDetail sumRecordByMasterId(Integer masterId);


}

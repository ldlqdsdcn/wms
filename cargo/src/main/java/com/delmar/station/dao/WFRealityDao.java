package com.delmar.station.dao;

import java.util.List;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WFReality;

public interface WFRealityDao extends CoreHibernateDao<WFReality>{

	public WFReality getWFRealityById(Integer id);
	public WFReality getWFRealityByMasterId(Integer masterId);
	public WFReality saveOrUpdate(WFReality wfReality);
	public void delete(WFReality wfReality);

}

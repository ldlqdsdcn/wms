package com.delmar.station.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.delmar.common.dao.BaseHibernateDao;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.station.dao.WFRealityDao;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WFReality;

@Repository("WFRealityDaoHibernate")
public class WFRealityDaoHibernate extends BaseHibernateDao<WFReality> implements WFRealityDao{

	@Override
	public Class getModelClass() {
		return WFReality.class;
	}
	
	public void delete(WFReality wfReality) {
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("delete from WFReality a where a.id = " + wfReality.getId());
		getCurrentSession().createQuery(fromHql.toString()).executeUpdate();
	}

	public WFReality getWFRealityById(Integer id) {
		if (id == null || String.valueOf(id).length() <= 0) {
			return null;
		}
		List<WFReality> list = new ArrayList<WFReality>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WFReality a where a.id = " + id);
		Query query = getCurrentSession().createQuery(fromHql.toString());
		if (query == null) {
			return null;
		}
		
		list = query.list();
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}

	public WFReality getWFRealityByMasterId(Integer masterId) {
		if (masterId == null || String.valueOf(masterId).length() <= 0) {
			return null;
		}
		List<WFReality> list = new ArrayList<WFReality>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WFReality a where a.masterID = " + masterId);
		Query query = getCurrentSession().createQuery(fromHql.toString());
		if (query == null) {
			return null;
		}
		
		list = query.list();
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}

	public WFReality saveOrUpdate(WFReality wfReality) {
		if (wfReality != null) {
			if (wfReality.isnew()) {
				getCurrentSession().save(wfReality);
				getCurrentSession().flush();
			} else {
				getCurrentSession().merge(wfReality);
				getCurrentSession().flush();
			}
			getCurrentSession().clear();
			return wfReality;
		}
		
		return wfReality;
	}

}

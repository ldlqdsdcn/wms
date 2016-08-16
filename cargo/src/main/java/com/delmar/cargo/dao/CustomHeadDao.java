package com.delmar.cargo.dao;

import java.util.List;
import java.util.Map;

import com.delmar.cargo.model.CustomHead;
import com.delmar.core.dao.CoreHibernateDao;

public interface CustomHeadDao extends CoreHibernateDao<CustomHead>{

	public List<CustomHead> getCustomHeadByMap(Map<String, Object> map);

}

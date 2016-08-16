package com.delmar.common.dao;

import java.util.List;

import com.delmar.common.DBUtil;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.core.model.CoreModel;

public class BaseHibernateDao <T extends CoreModel> extends CoreHibernateDaoImpl<T>{

	@Override
	public Class getModelClass() {
		return null;
	}
	
	public Integer getSyscreateId(String TableTag) {
		
		if (TableTag == null || TableTag.length() <= 0) {
			return null;
		}
		
		return Integer.parseInt(DBUtil.getInstance().createId(TableTag));
	}
	
	public Integer getMaxId(String tableName) {
		
		String sql = " select top 1 id from " + tableName + " order by id desc";
		List<Object[]> list = getCurrentSession().createSQLQuery(sql).list();
		return Integer.parseInt(String.valueOf(list.get(0))) + 1;
	}
	
}

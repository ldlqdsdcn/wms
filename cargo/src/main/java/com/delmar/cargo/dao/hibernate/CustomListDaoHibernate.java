package com.delmar.cargo.dao.hibernate;


import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.CustomListDao;
import com.delmar.cargo.model.CustomHead;
import com.delmar.cargo.model.CustomList;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

@Repository("CustomListDaoHibernate") 
public class CustomListDaoHibernate extends CoreHibernateDaoImpl<CustomList> implements CustomListDao{

	@Override
	public Class getModelClass() {
		return CustomHead.class;
	}

	public List<CustomList> getCustomListByPreEntryId(String pre_entry_id) {
		
		StringBuffer sqlStr = new StringBuffer();
	    sqlStr.append("select * from Custom_List where 1=1 "); 
	    
	    if(String.valueOf(pre_entry_id).length() > 0){
	    		sqlStr.append(" and pre_entry_id = '"+ pre_entry_id +"'"); 
	    }
	    
	    SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlStr.toString());
	    
	    return sqlQuery.setResultTransformer(Transformers.aliasToBean(CustomList.class)).list();
	}

}

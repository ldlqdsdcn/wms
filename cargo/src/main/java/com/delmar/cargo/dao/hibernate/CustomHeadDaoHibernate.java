package com.delmar.cargo.dao.hibernate;


import java.util.List;
import java.util.Map;

import org.apache.log4j.helpers.Transform;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.CustomHeadDao;
import com.delmar.cargo.model.CustomHead;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

@Repository("CustomHeadDaoHibernate") 
public class CustomHeadDaoHibernate extends CoreHibernateDaoImpl<CustomHead> implements CustomHeadDao{

	@Override
	public Class getModelClass() {
		return CustomHead.class;
	}

	public List<CustomHead> getCustomHeadByMap(Map<String, Object> map) {
		
		StringBuffer sqlStr = new StringBuffer();
	    sqlStr.append("select * from Custom_Head where 1=1 "); 
	    
	    //经营单位
	    if(map.containsKey("customsTradeNameHid") && String.valueOf(map.get("customsTradeNameHid")).length() > 0){
	    		sqlStr.append(" and trade_name = '"+ String.valueOf(map.get("customsTradeNameHid")) +"'"); 
	    }
	    
	    //提单号
	    if(map.containsKey("customsHblNoHid") && String.valueOf(map.get("customsHblNoHid")).length() > 0){
    		sqlStr.append(" and hbl_no = '" + String.valueOf(map.get("customsHblNoHid")) + "'"); 
	    }
	    
	    //主单号
	    if(map.containsKey("customsBillNoHid") && String.valueOf(map.get("customsBillNoHid")).length() > 0){
    		sqlStr.append(" and bill_no = '"+ String.valueOf(map.get("customsBillNoHid")) +"'"); 
	    }
	    
	    SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlStr.toString());
	    
	    return sqlQuery.setResultTransformer(Transformers.aliasToBean(CustomHead.class)).list();
	}
	
}

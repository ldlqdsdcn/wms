/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.DatadictTrlDao;
import com.delmar.base.model.DatadictTrl;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-02-06 11:07:48
 */
@Repository("datadictTrlDao") 
public class DatadictTrlDaoMybatis extends CoreDaoMyBatis<DatadictTrl> implements DatadictTrlDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.DatadictTrlMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.DatadictTrlDao#getDatadictTrlByTypeId(java.lang.Integer, java.lang.String)
	 */
	public List<DatadictTrl> getDatadictTrlByTypeId(Integer typeId,
			String language,Integer clientId) {
		Map param=new HashMap();
		if (clientId!=null)
		  param.put("accessString", "datadict_id in(select id from base_datadict where datadict_type_id="+typeId+" and client_id="+clientId+" and IsActive=1) and language='"+language+"'");
		else
		   param.put("accessString", "datadict_id in(select id from base_datadict where datadict_type_id="+typeId+" and IsActive=1) and language='"+language+"' ");
		param.put("orderByClause", " indexOrder asc");		
		return this.selectByExample(param);
	}
	
	public List<DatadictTrl> getDatadictTrlByTypeIdAndDate(Integer typeId,String requestDate,
			String language,Integer clientId) {
		Map param=new HashMap();
		param.put("accessString", "datadict_id in(select id from base_datadict where datadict_type_id="+typeId+"  and client_id="+clientId+" and updated>=convert(datetime,'"+requestDate+"')) and language='"+language+"'");
		return this.selectByExample(param);
	}	
	
	public void updateIndexOrder(Integer indexOrder,Integer datadictId) {
		Map param=new HashMap();
		param.put("indexOrder", indexOrder);
		param.put("datadictId", datadictId);		
		sqlSessionTemplate.update(this.getSqlName()+".updateIndexOrder", param);
	}		



}

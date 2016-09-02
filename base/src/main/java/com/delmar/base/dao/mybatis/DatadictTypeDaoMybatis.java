/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.DatadictTypeDao;
import com.delmar.base.model.DatadictType;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-21 10:29:53
 */
@Repository("datadictTypeDao") 
public class DatadictTypeDaoMybatis extends CoreDaoMyBatis<DatadictType> implements DatadictTypeDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.DatadictTypeMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.DatadictTypeDao#datadictTypeDao(java.lang.String)
	 */
	public DatadictType datadictTypeDao(String value) {
		Map param=new HashMap();
		param.put("value", value);
		return this.getByExample(param);
	}



}

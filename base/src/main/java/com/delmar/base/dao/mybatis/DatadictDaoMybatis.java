/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.DatadictDao;
import com.delmar.base.model.Datadict;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
@Repository("datadictDao") 
public class DatadictDaoMybatis extends CoreDaoMyBatis<Datadict> implements DatadictDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.DatadictMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.DatadictDao#getDatadictListByTypeId(java.lang.Integer)
	 */
	public List<Datadict> getDatadictListByTypeId(Integer typeId,Integer clientId) {
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("datadictTypeId", typeId);
		if (clientId!=null)
  		  param.put("clientId", clientId);
		param.put("orderByClause", " indexOrder asc");		
		return this.selectByExample(param);
	}
	
	public void updateIndexOrder(Integer indexOrder,Integer id) {
		Map param=new HashMap();
		param.put("indexOrder", indexOrder);
		param.put("id", id);		
		sqlSessionTemplate.update(this.getSqlName()+".updateIndexOrder", param);
	}		



}

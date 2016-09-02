/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.sys.dao.OrgDao;
import com.delmar.sys.model.Org;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
@Repository("orgDao") 
public class OrgDaoMybatis extends CoreDaoMyBatis<Org> implements OrgDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.OrgMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.OrgDao#getOrgs(java.util.Map)
	 */
	@Override
	public List getOrgs(Map<String, Object> param) {

		return sqlSessionTemplate.selectList(getSqlName()+".getOrgs", param);
	}

	

}

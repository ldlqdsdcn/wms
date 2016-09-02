/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.PortDao;
import com.delmar.base.model.Port;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
@Repository("portDao") 
public class PortDaoMybatis extends CoreDaoMyBatis<Port> implements PortDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.PortMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.PortDao#selectPortByMode(java.util.Map)
	 */
	public List<Port> selectPortByMode(Map<String,Object> param) {
		return this.sqlSessionTemplate.selectList(getSqlName() +".selectPortByMode", param);
	}



}

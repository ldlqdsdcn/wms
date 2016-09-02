/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.EBusinessDao;
import com.delmar.corebus.model.EBusiness;

/**
 * @author 刘大磊 2014-12-26 10:54:30
 */
@Repository("eBusinessDao") 
public class EBusinessDaoMybatis extends CoreDaoMyBatis<EBusiness> implements EBusinessDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.EBusinessMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.corebus.dao.EBusinessDao#maxBusinessno(java.lang.String)
	 */
	public String selectMaxBusinessNo(String prefix) {
		return sqlSessionTemplate.selectOne(getSqlName()+".selectMaxBusinessNo", prefix);
	}



}

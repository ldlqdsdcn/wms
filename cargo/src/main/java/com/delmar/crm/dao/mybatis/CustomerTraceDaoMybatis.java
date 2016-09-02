/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.crm.dao.CustomerTraceDao;
import com.delmar.crm.model.CustomerTrace;

/**
 * @author 刘大磊 2015-08-14 14:07:47
 */
@Repository("customerTraceDao") 
public class CustomerTraceDaoMybatis extends CoreDaoMyBatis<CustomerTrace> implements CustomerTraceDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.CustomerTraceMapper";
	}
	
	
	public List<CustomerTrace> selectByCustomerId(Integer customerId)
	{
		return sqlSessionTemplate.selectList(this.getSqlName()+".selectByCustomerId", customerId);	
	}



}

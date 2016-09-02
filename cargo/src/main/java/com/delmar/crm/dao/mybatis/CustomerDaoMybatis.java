/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-23 17:47:07
 */
@Repository("customerDao") 
public class CustomerDaoMybatis extends CoreDaoMyBatis<Customer> implements CustomerDao {
	
	protected static final String selectName_SQL=".selectNameById";

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {		
		return "com.delmar.crm.mybatis.sql.CustomerMapper";
	}

	
	
	public String getNamebyId(Integer id)
	{
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}


}

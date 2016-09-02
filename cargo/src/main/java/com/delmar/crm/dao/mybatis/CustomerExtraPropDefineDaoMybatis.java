/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.crm.dao.CustomerExtraPropDefineDao;
import com.delmar.crm.model.CustomerExtraPropDefine;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-09-08 15:44:27
 */
@Repository("customerExtraPropDefineDao") 
public class CustomerExtraPropDefineDaoMybatis extends CoreDaoMyBatis<CustomerExtraPropDefine> implements CustomerExtraPropDefineDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.CustomerExtraPropDefineMapper";
	}



}

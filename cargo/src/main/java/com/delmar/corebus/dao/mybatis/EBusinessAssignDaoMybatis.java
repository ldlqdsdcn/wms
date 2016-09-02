/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.EBusinessAssignDao;
import com.delmar.corebus.model.EBusinessAssign;

/**
 * @author 刘大磊 2015-03-18 16:04:12
 */
@Repository("eBusinessAssignDao") 
public class EBusinessAssignDaoMybatis extends CoreDaoMyBatis<EBusinessAssign> implements EBusinessAssignDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.EBusinessAssignMapper";
	}



}

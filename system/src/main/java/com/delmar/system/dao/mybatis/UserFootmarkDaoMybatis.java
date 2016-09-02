/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.system.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.system.dao.UserFootmarkDao;
import com.delmar.system.model.UserFootmark;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-02 10:18:25
 */
@Repository("userFootmarkDao") 
public class UserFootmarkDaoMybatis extends CoreDaoMyBatis<UserFootmark> implements UserFootmarkDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.system.mybatis.sql.UserFootmarkMapper";
	}



}

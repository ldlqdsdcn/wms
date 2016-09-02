/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.sys.dao.UserSessionDao;
import com.delmar.sys.model.UserSession;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-13 11:57:59
 */
@Repository("userSessionDao") 
public class UserSessionDaoMybatis extends CoreDaoMyBatis<UserSession> implements UserSessionDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.UserSessionMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UserSessionDao#getPreLoginSession(java.lang.Integer)
	 */
	@Override
	public UserSession getPreLoginSession(Integer userId) {
		try
		{
		return (UserSession)this.sqlSessionTemplate.selectOne(getSqlName() +".getPreLoginSession", userId);
		}
		catch(Exception e)
		{
			return null;
		}
	}



}

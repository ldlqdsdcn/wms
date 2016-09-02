/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.UserorgAccessDao;
import com.delmar.sys.model.UserorgAccess;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
@Repository("userorgAccessDao") 
public class UserorgAccessDaoMybatis extends CoreDaoMyBatis<UserorgAccess> implements UserorgAccessDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.UserorgAccessMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UserorgAccessDao#selectUserorgAccessByUserId(java.lang.Integer)
	 */
	@Override
	public List<UserorgAccess> selectUserorgAccessByUserId(Integer userId) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", userId);
		return selectByExample(param);
	}



}

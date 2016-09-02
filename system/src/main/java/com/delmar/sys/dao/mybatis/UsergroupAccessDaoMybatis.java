/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.UsergroupAccessDao;
import com.delmar.sys.model.UsergroupAccess;

/**
 * @author 刘大磊 2015-01-22 10:48:19
 */
@Repository("usergroupAccessDao") 
public class UsergroupAccessDaoMybatis extends CoreDaoMyBatis<UsergroupAccess> implements UsergroupAccessDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.UsergroupAccessMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UsergroupDao#selectUserAccessUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Integer> selectUserAccessUserId(Integer orgId, Integer userId) {
		Map<String,Integer> param=new HashMap<String,Integer>();
		param.put("orgId", orgId);
		param.put("userId", userId);
		return this.sqlSessionTemplate.selectList(getSqlName()+".selectUserAccessUserId", param);
	}
	
	public List<Integer> selectUserAccessUserId2(Integer userId)
	{
		List<Integer> list=sqlSessionTemplate.selectList(getSqlName()+".selectUserAccessUserId2", userId);
		if(list==null)
		{
			list=new ArrayList<Integer>();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UsergroupAccessDao#getUserGroupAccessByUserGroupId(java.lang.Integer)
	 */
	@Override
	public List<UsergroupAccess> getUserGroupAccessByUserGroupId(Integer id) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("usergroupId", id);
		return this.selectByExample(param);
	}

}

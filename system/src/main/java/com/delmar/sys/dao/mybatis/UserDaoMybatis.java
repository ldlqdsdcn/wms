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
import com.delmar.sys.dao.UserDao;
import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2014-12-23 14:47:25
 */
@Repository("userDao") 
public class UserDaoMybatis extends CoreDaoMyBatis<User> implements UserDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.UserMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UserDao#validateUsername(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer validateUsername(String username, Integer id) {
		Map<String,String> map=new HashMap<String,String> ();
		map.put("username", username);
		if(id!=null)
		{
			map.put("accessString", " and id <> "+id);
		}
		
		return (Integer)sqlSessionTemplate.selectOne(getSqlName()+".countByExample",map);
	}
	public User getUserById(Integer userId)
	{
		return sqlSessionTemplate.selectOne(getSqlName()+".getUserById", userId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UserDao#getUsers(java.util.Map)
	 */
	@Override
	public List getUsers(Map<String, Object> param) {

		return sqlSessionTemplate.selectList(getSqlName()+".getUsers", param);
	}

	public List getUserOrgByRoleId(Integer roleId)
	{
		return sqlSessionTemplate.selectList(getSqlName()+".getUserOrgByRoleId", roleId);
	}

	public List getNonUserOrgByRoleId(Integer roleId)
	{
		return sqlSessionTemplate.selectList(getSqlName()+".getNonUserOrgByRoleId", roleId);
	}
	
	public List getUserSubstituteById(Integer userId)
	{
		return sqlSessionTemplate.selectList(getSqlName()+".getUserSubstituteById", userId);
	}
	
	public List getNonUserSubstituteById(Integer userId)
	{
		return sqlSessionTemplate.selectList(getSqlName()+".getNonUserSubstituteById", userId);
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.UserDao#getNonUserOrgByUsergroupId(java.lang.Integer)
	 */
	@Override
	public List<User> getNonUserOrgByUsergroupId(Integer usergroupId,Integer orgId) {
		Map param=new HashMap();
		param.put("usergroupId", usergroupId);
		param.put("orgId", orgId);
		 return sqlSessionTemplate.selectList(getSqlName()+".getNonUserOrgByUsergroupId", param);
	}	
	
}

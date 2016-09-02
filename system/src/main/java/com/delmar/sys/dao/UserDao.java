/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.dao;

import java.util.List;
import java.util.Map;

import com.delmar.sys.model.User;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2014-12-23 14:47:25
 */
public interface UserDao extends CoreDao<User> {

	public Integer validateUsername(String username, Integer id);

	/**
	 * @param param
	 * @return
	 */
	public List getUsers(Map<String, Object> param);
	
	public User getUserById(Integer userId);
	public List getUserOrgByRoleId(Integer roleId);
	public List getNonUserOrgByRoleId(Integer roleId);
	public List getUserSubstituteById(Integer userId);
	public List getNonUserSubstituteById(Integer userId);

	/**
	 * @param usergroupId
	 * @return
	 */
	public List<User> getNonUserOrgByUsergroupId(Integer usergroupId,Integer orgId);

}

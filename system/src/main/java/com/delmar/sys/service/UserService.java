
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;
import java.util.Map;

import com.delmar.core.service.CoreService;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;

/**
 * @author 刘大磊 2014-12-23 14:47:25
 */
public interface UserService extends CoreService<User> {
	/**
	 * 用户登录，查询用户
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	/**
	 * @param userId
	 * @return
	 */
	public UserContent getUserPrivileges(Integer userId);
	/**
	 * @param username
	 * @param id
	 * @return
	 */
	public boolean validateUsernameExist(String username, Integer id);

	/**
	 * @param user
	 * @param roles
	 * @param saveList
	 * @param saveGroupList
	 * @return
	 */
	public Integer saveUser(User user, List<Integer> roles,
			String[] orgList,String[] saveGroupList);
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
	
	public void deleteById(Integer id);
	/**
	 * @param integer
	 * @return
	 */
	public List<User> getNonUserOrgByUsergroupId(Integer integer,Integer orgId);

}
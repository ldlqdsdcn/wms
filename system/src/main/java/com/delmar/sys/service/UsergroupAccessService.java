
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;

import com.delmar.sys.model.User;
import com.delmar.sys.model.UsergroupAccess;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-22 10:48:19
 */
public interface UsergroupAccessService extends CoreService<UsergroupAccess> {
	/**
	 * @param ids
	 */
	public void deleteUsergourpAccessList(Integer[] ids);
	@Deprecated
	public List<Integer>selectUserAccessUserId(Integer orgId,Integer userId);
	
	public List<Integer> selectUserAccessUserId2(Integer userId);
	/**
	 * @param id
	 * @return
	 */
	public List<User> getUserUsergroupId(Integer id);
}
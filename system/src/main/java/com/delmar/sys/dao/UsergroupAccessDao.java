/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.dao;

import java.util.List;

import com.delmar.sys.model.UsergroupAccess;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-01-22 10:48:19
 */
public interface UsergroupAccessDao extends CoreDao<UsergroupAccess> {
	public List<Integer>selectUserAccessUserId(Integer orgId,Integer userId);
	public List<Integer> selectUserAccessUserId2(Integer userId);
	/**
	 * @param id
	 * @return
	 */
	public List<UsergroupAccess> getUserGroupAccessByUserGroupId(Integer id);
}

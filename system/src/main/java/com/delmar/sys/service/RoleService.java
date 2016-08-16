
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;

import com.delmar.sys.model.Privilege;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.RoleModuleContent;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
public interface RoleService extends CoreService<Role> {

	/**
	 * @param id
	 * @return
	 */
	List<Privilege> getPrivileges(Integer id);

	/**
	 * @param id
	 * @return
	 */
	List<RoleModuleContent> getRoleModuleContent(Integer id);

	/**
	 * @param role
	 * @param rmContentListSession
	 * @return
	 */
	Integer saveRoleContent(Role role,
			List<RoleModuleContent> rmContentListSession);

}
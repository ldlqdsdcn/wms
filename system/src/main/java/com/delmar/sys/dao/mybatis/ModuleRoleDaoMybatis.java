/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.ModuleRoleDao;
import com.delmar.sys.model.ModuleRole;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
@Repository("moduleRoleDao") 
public class ModuleRoleDaoMybatis extends CoreDaoMyBatis<ModuleRole> implements ModuleRoleDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.ModuleRoleMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.ModuleRoleDao#selectModuleRoleByModuleIdAndRoleId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ModuleRole selectModuleRoleByModuleIdAndRoleId(Integer moduleId,
			Integer roleId) {
		Map param=new HashMap();
		param.put("moduleId", moduleId);
		param.put("roleId", roleId);
		List<ModuleRole> list=this.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}



}

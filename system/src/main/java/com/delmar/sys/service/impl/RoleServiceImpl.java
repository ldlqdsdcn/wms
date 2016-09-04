/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.ModuleDao;
import com.delmar.sys.dao.ModuleRoleDao;
import com.delmar.sys.dao.OperatorDao;
import com.delmar.sys.dao.PrivilegeDao;
import com.delmar.sys.dao.RoleDao;
import com.delmar.sys.model.Module;
import com.delmar.sys.model.ModuleRole;
import com.delmar.sys.model.Operator;
import com.delmar.sys.model.Privilege;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.RoleModuleContent;
import com.delmar.sys.service.RoleService;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("roleService")
public class RoleServiceImpl extends CoreServiceImpl<Role> implements
		RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private ModuleRoleDao moduleRoleDao;
	@Autowired
	private PrivilegeDao privilegeDao;
	@Autowired
	private OperatorDao operatorDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Role> getCoreDao() {
		return roleDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.RoleService#getPrivileges(java.lang.Integer)
	 */
	@Override
	public List<Privilege> getPrivileges(Integer roleId) {
		if(roleId!=null&&roleId==0)
		{
			roleId=null;
		}
		List<Privilege> list=new ArrayList<Privilege>();
		Map map=new HashMap();
		map.put("roleId", roleId);
		List<Module>modules=moduleDao.selectByExample(map);
		if(roleId==null)
		{
			for(Module module:modules)
			{
				ModuleRole mr=new ModuleRole();
				mr.setModuleId(module.getId());
				mr.setModule(module);
				Privilege pri=new Privilege();
				pri.setModuleRole(mr);

				list.add(pri);
			}
			return list;
		}
		else
		{
			
			List<ModuleRole> mrList=moduleRoleDao.selectByExample(map);
			for(ModuleRole mr:mrList)
			{
				
			}
			
			
			
			List<Privilege> priList=privilegeDao.selectByExample(map);
			for(Module module:modules)
			{
				boolean nothas=true;
				for(int i=priList.size()-1;i>=0;i--)
				{
					Privilege pr=priList.get(i);
					ModuleRole mr=moduleRoleDao.selectByPrimaryKey(pr.getModuleRoleId());
					pr.setModuleRole(mr);
					if(mr!=null&&module.getId().equals(pr.getModuleRole().getModuleId()))
					{
						pr.getModuleRole().setModule(module);
						list.add(pr);
						priList.remove(i);
						nothas=false;
						break;
					}
				}
				if(nothas)
				{
					Privilege pr=new Privilege();
					ModuleRole mr=new ModuleRole();
					mr.setRoleId(roleId);
					mr.setModuleId(module.getId());
					mr.setModule(module);
					pr.setModuleRole(mr);
					list.add(pr);
				}
			}
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.RoleService#getRoleModuleContent(java.lang.Integer)
	 */
	@Override
	public List<RoleModuleContent> getRoleModuleContent(Integer roleId) {
		List<Module>modules=moduleDao.selectByExample(null);
		List<RoleModuleContent> contentList=new ArrayList<RoleModuleContent>();
		for(Module mod:modules)
		{
			//获取所有可操作
			List<Operator> operatorList=operatorDao.selectByExample(null);
			
			
			Map param=new HashMap();
			param.put("accessString", " module_role_id in( select id from sys_module_role where module_id="+mod.getId()+" and role_id="+roleId+" )");
	
			ModuleRole mr=this.moduleRoleDao.selectModuleRoleByModuleIdAndRoleId(mod.getId(), roleId);
			List<Privilege> roList=this.privilegeDao.selectByExample(param);
			List<Integer> operIds=new ArrayList<Integer>();
			for(Privilege ro:roList)
			{				
				operIds.add(ro.getOperatorId());
			}
			RoleModuleContent pmc=new RoleModuleContent(mod,operatorList,operIds,mr==null?null:mr.getDataFilterLevel());
			contentList.add(pmc);
		}
		return contentList;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.RoleService#saveRoleContent(com.delmar.sys.model.Role, java.util.List)
	 */
	@Override
	public Integer saveRoleContent(Role role,
			List<RoleModuleContent> rmContentListSession) {
		roleDao.save(role);
		for(RoleModuleContent rmc:rmContentListSession)
		{
			Integer modId=rmc.getModule().getId();

			
			Map param=new HashMap();
			param.put("accessString", " module_role_id in( select id from sys_module_role where module_id="+modId+" and role_id="+role.getId()+" )");
			
			privilegeDao.deleteByExample(param);
			//System.out.println(privilegeDao.selectByExample(null).size());
			List<Integer> operIds=rmc.getOperIds();
			Privilege op=new Privilege();
			for(Integer operId:operIds)
			{
				ModuleRole mr=moduleRoleDao.selectModuleRoleByModuleIdAndRoleId(modId, role.getId());
				if(mr==null)
				{
					mr=new ModuleRole();
					mr.setModuleId(modId);
					mr.setRoleId(role.getId());
				}
					mr.setDataFilterLevel(rmc.getAccessLevel());
					this.moduleRoleDao.save(mr);


				op.setModuleRoleId(mr.getId());
				op.setOperatorId(operId);
				privilegeDao.insert(op);
			}
			
		}
		return role.getId();
	}

	
}

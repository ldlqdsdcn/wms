/******************************************************************************
 * 刘大磊  2013-07-01												      *
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
import com.delmar.sys.dao.JavabeanDao;
import com.delmar.sys.dao.ModuleDao;
import com.delmar.sys.dao.ModuleJavabeanDao;
import com.delmar.sys.dao.ModuleMenuDao;
import com.delmar.sys.dao.ModulePageDao;
import com.delmar.sys.dao.PageDao;
import com.delmar.sys.dao.PageMenuDao;
import com.delmar.sys.model.Javabean;
import com.delmar.sys.model.Module;
import com.delmar.sys.model.ModuleJavabean;
import com.delmar.sys.model.ModuleMenu;
import com.delmar.sys.model.ModulePage;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.PageMenu;
import com.delmar.sys.service.ModuleService;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("moduleService")
public class ModuleServiceImpl extends CoreServiceImpl<Module> implements
		ModuleService {
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private JavabeanDao javabeanDao;
	@Autowired
	private ModuleJavabeanDao moduleJavabeanDao;
	@Autowired
	private ModulePageDao modulePageDao;
	@Autowired
	private ModuleMenuDao moduleMenuDao;
	@Autowired
	private PageDao pageDao;
	@Autowired
	private PageMenuDao pageMenuDao;

	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Module> getCoreDao() {
		return moduleDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		if(ids!=null)
			for(Integer id:ids)
			{
				moduleDao.deleteByPrimaryKey(id);
			}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#saveModuleJavabean(com.delmar.sys.model.Module, java.util.List)
	 */
	@Override
	public Integer saveModuleJavabean(Module module, List<Integer> idList) {
		boolean isnew=module.getId()==null?true:false;
		Integer id=moduleDao.save(module);
		if(!isnew)
		{
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("moduleId", module.getId());
			this.moduleJavabeanDao.deleteByExample(param);
		}
		for(Integer javabeanId:idList)
		{
			ModuleJavabean modJavabean=new ModuleJavabean();
			modJavabean.setModuleId(id);
			modJavabean.setJavabeanId(javabeanId);
			moduleJavabeanDao.insert(modJavabean);
		}
		return id;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#saveModulePage(com.delmar.sys.model.Module, java.util.List)
	 */
	@Override
	public Integer saveModulePage(Module module, List<Integer> idList) {
		boolean isnew=module.getId()==null?true:false;
		Integer id=moduleDao.save(module);
		//Integer id=module.getId();
		Map map=new HashMap();
		map.put("moduleId", id);
		if(!isnew)
		{
			modulePageDao.deleteByExample(map);
		}
		for(Integer pageId:idList)
		{
			ModulePage page=new ModulePage();
			page.setModuleId(id);
			page.setPageId(pageId);
			modulePageDao.save(page);
		}
		return id;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#saveModule(com.delmar.sys.model.Module, java.util.List)
	 */
	@Override
	public Integer saveModule(Module module, List<Integer> idList) {
		boolean isnew=module.getId()==null?true:false;
		Integer id=moduleDao.save(module);
		//Integer id=module.getId();
		Map map=new HashMap();
		map.put("moduleId", id);
		if(!isnew)
			moduleMenuDao.deleteByExample(map);
			for(Integer menuId:idList)
			{
				ModuleMenu mm=new ModuleMenu();
				mm.setModuleId(id);
				//mm.setModId(id);
				mm.setMenuId(menuId);
				this.moduleMenuDao.save(mm);
			}
		return id;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#getModulePages(java.lang.Integer)
	 */
	@Override
	public List<ModulePage> getModulePages(Integer modId) {
		List<Page> pages=this.pageDao.selectByExample(null);
		List<ModulePage> list=new ArrayList<ModulePage>();
		if(modId==null||modId==0)
		{
			for(Page page:pages)
			{
				ModulePage module=new ModulePage();
				module.setPage(page);
				module.setPageId(page.getId());
				list.add(module);
			}
			return list;
		}
		Map map=new HashMap();
		map.put("moduleId", modId);
		
		List<ModulePage> mpages= modulePageDao.selectByExample(map);
		
		
		for(Page page:pages)
		{
			boolean nothas=true;
			for(int j=mpages.size()-1;j>=0;j--)
			{
				ModulePage m=mpages.get(j);
				if(m.getPageId().equals(page.getId()))
				{
					nothas=false;
					m.setPage(page);
					list.add(m);
					mpages.remove(j);
					break;
				}
			}
			if(nothas)
			{
				ModulePage module=new ModulePage();
				module.setPageId(page.getId());
				module.setPage(page);
				list.add(module);
			}
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#getModuleMenus(java.lang.Integer)
	 */
	@Override
	public List<ModuleMenu> getModuleMenus(Integer modId) {
		Map param=new HashMap();
		param.put("menuType", "N");
		List<PageMenu> pagemenus=this.pageMenuDao.selectByExample(param);
		List<ModuleMenu> list=new ArrayList<ModuleMenu>();
		if(modId==null||modId==0)
		{
			for(PageMenu pm:pagemenus)
			{
				ModuleMenu module=new ModuleMenu();
				module.setMenuId(pm.getId());
				module.setPageMenu(pm);
				list.add(module);
			}
			return list;
		}
		Map map=new HashMap();
		map.put("moduleId", modId);
		
		List<ModuleMenu> menus= moduleMenuDao.selectByExample(map);
		
		
		for(PageMenu pm:pagemenus)
		{
			boolean nothas=true;
			for(int j=menus.size()-1;j>=0;j--)
			{
				ModuleMenu m=menus.get(j);
				if(m.getMenuId().equals(pm.getId()))
				{
					nothas=false;
					m.setPageMenu(pm);
					list.add(m);
					menus.remove(j);
					break;
				}
			}
			if(nothas)
			{
				ModuleMenu module=new ModuleMenu();
				module.setMenuId(pm.getId());
				module.setPageMenu(pm);
				list.add(module);
			}
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ModuleService#getJavabeans(java.lang.Integer)
	 */
	@Override
	public List<ModuleJavabean> getJavabeans(Integer modId) {
		List<Javabean> mjbList=javabeanDao.selectByExample(null);
		 
		 List<ModuleJavabean> modJavabeanList=new ArrayList<ModuleJavabean>();
			Map map=new HashMap();
			map.put("moduleId", modId);
		 List<ModuleJavabean> javabeanList=moduleJavabeanDao.selectByExample(map);
		 for(Javabean jb:mjbList)
		 {
			 ModuleJavabean mod=new ModuleJavabean();
			 mod.setJavabean(jb);
			 mod.setJavabeanId(jb.getId());
			Integer id=null;
			 for(ModuleJavabean mjb:javabeanList)
			 {
				 if(jb.getId().equals(mjb.getJavabeanId()))
				 {
					 id=mjb.getId();
					 break;
				 }
			 }
			 mod.setId(id);
			 modJavabeanList.add(mod);
		 }
		 
		 
		
		return modJavabeanList;
	}

	
}

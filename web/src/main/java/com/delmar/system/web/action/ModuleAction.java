/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.delmar.core.web.action.CoreEditAction;
import com.delmar.sys.model.Module;
import com.delmar.sys.model.ModuleJavabean;
import com.delmar.sys.model.ModuleMenu;
import com.delmar.sys.model.ModulePage;
import com.delmar.sys.service.ModuleService;
import com.delmar.utils.ResourceMessage;

/**
 * @author 刘大磊 2015年1月14日 下午3:55:28
 */
public class ModuleAction extends CoreEditAction {

	private Module module=new Module();
	private ModuleService moduleService;
	private List<ModulePage> modulePageList=new ArrayList<ModulePage>();
	private List<ModuleMenu> moduleMenuList=new ArrayList<ModuleMenu>();
	private List<ModuleJavabean> javabeanList=new ArrayList<ModuleJavabean>();

	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "模块信息");
	}
	
	@Override
	public void editForm() {
		if(id==null||id==0)
		{
			module=new Module();
		}
		else
		{
			module=this.moduleService.selectByPrimaryKey(id);
			modulePageList=moduleService.getModulePages(id);
			moduleMenuList=moduleService.getModuleMenus(id);
			javabeanList=moduleService.getJavabeans(id);
			
		}
		

	}
	public String save()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		List<Integer> idList=new ArrayList<Integer>();
		boolean isnew= module.getId() == null;
		if(ids!=null)
		{
			for(String id:ids)
			idList.add(Integer.parseInt(id));
		}
		if(isnew)
		{
			module.setInit("N");
		}
		id=moduleService.saveModule(module, idList);
		if(isnew)
		getIdList().add(id);
		addActionMessage("保存模块信息成功！");
		return edit();
	}
	public String savePage()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		List<Integer> idList=new ArrayList<Integer>();
		boolean isnew= module.getId() == null;
		if(ids!=null)
		{
			for(String id:ids)
			idList.add(Integer.parseInt(id));
		}
		id=moduleService.saveModulePage(module, idList);
		if(isnew)
			getIdList().add(id);
		addActionMessage("保存模块信息成功！");
		return editPage();
	}
	public String saveJavabean()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		List<Integer> idList=new ArrayList<Integer>();
		boolean isnew= module.getId() == null;
		if(ids!=null)
		{
			for(String id:ids)
			idList.add(Integer.parseInt(id));
		}
		id=moduleService.saveModuleJavabean(module, idList);
		if(isnew)
			getIdList().add(id);
		addActionMessage("保存模块信息成功！");
		return editJavabean();
	}
	public String editPage()
	{
		editForm();
		return "editPage";
	}
	public String editJavabean()
	{
		editForm();
		return "editJavabean";
	}
	@Override
	public String getModuleName() {
		
		return "module";
	}

	@Override
	public List search() {
		
		return moduleService.selectByExample(null);
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Override
	public void deleteList(Integer[] ids) 
	{
		moduleService.deleteList(ids);
		
	}

	@Override
	public String delete() {
		
		moduleService.deleteByPrimaryKey(module.getId());
		search();
		return "list";
	}

	@Override
	public Integer getModelId() {
		
		return module.getId();
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<ModulePage> getModulePageList() {
		return modulePageList;
	}

	public void setModulePageList(List<ModulePage> modulePageList) {
		this.modulePageList = modulePageList;
	}

	public List<ModuleMenu> getModuleMenuList() {
		return moduleMenuList;
	}

	public void setModuleMenuList(List<ModuleMenu> moduleMenuList) {
		this.moduleMenuList = moduleMenuList;
	}
	public List<ModuleJavabean> getJavabeanList() {
		return javabeanList;
	}

}

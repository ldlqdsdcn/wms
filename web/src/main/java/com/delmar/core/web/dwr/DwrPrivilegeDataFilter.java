package com.delmar.core.web.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.DelmarConst;
import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.SystemConst;
import com.delmar.sys.model.ModulePage;
import com.delmar.sys.model.ModuleRole;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.ModulePageService;
import com.delmar.sys.service.ModuleRoleService;
import com.delmar.system.web.WebConst;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月30日 上午9:18:23 
 * 类说明 
 */
public class DwrPrivilegeDataFilter {
	
	private static ModulePageService modulePageService;	
	private static ModuleRoleService moduleRoleService;
	private static DatadictTypeService datadictTypeService;
	private static DatadictService datadictService;
	
	private static List<Datadict> accessList;	
	
	private User user;
	
	private static DwrPrivilegeDataFilter instance;
	
	static {
		

		datadictTypeService=EaContext.ApplicationContext.getBean("datadictTypeService",DatadictTypeService.class);
		datadictService=EaContext.ApplicationContext.getBean("datadictService",DatadictService.class);
		moduleRoleService=EaContext.ApplicationContext.getBean("moduleRoleService",ModuleRoleService.class);
		modulePageService=EaContext.ApplicationContext.getBean("modulePageService",ModulePageService.class);
		accessList=datadictService.getDatadictListByTypeValue(DatadictType.ACCESS_LEVEL);		
		
	}
	
	
	public static DwrPrivilegeDataFilter getInstance()
	{
		if (instance==null)
			instance=new DwrPrivilegeDataFilter();
		
		return instance;
	}

	
	
	public String getOrgListStr()
	{
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		user= (User)request.getSession().getAttribute(WebConst.SESSION_ACTUALUSER);
		UserContent userContent=	(UserContent)request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		
		String url=request.getServletPath();
		
		
		
		String userorgListStr=(String)request.getSession().getAttribute("userorgaccessListStr");
		
		int access_level=getAccessDataLevel(url,userContent);
		String accessString =null;
		
		if (access_level==DelmarConst.ACCESS_LEVEL_ALL)
			accessString="ALL";
		else
			accessString=userorgListStr;
	
		
		return accessString;
		
		
	}
	
	
	/**
	 * 获取访问的数据权限的级别
	 * @param url 页面url
	 * @param userContent 当前登录对象 
	 * **/
	
	private Integer getAccessDataLevel(String url,UserContent userContent)
	{
		
		List<Page> pageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
			
			List<Integer>moduleIdList=new ArrayList<Integer>();
			for(Page page:pageList)
			{
				if(url.startsWith(page.getPageUrl()))
				{
					Map param=new HashMap();
					param.put("pageId", page.getId());
					List<ModulePage> mpList=modulePageService.selectByExample(param);
					if(mpList!=null)
					{
						for(ModulePage mp:mpList)
						{
							moduleIdList.add(mp.getModuleId());
						}
					}
					
				}
			}
			List<ModuleRole> moduleRoleList=new ArrayList<ModuleRole>();
			List<Role> roleList=user.getRoles();
			
			
			for(Role r:roleList)
			{
				for(Integer modId:moduleIdList)
				{
					Map param=new HashMap();
					param.put("moduleId", modId);
					param.put("roleId", r.getId());
					
					List<ModuleRole> mrList=moduleRoleService.selectByExample(param);
					if(mrList!=null&&mrList.size()>0)
					{
						moduleRoleList.addAll(mrList);
					}
					
				}
			}
			int access_level=4;
			for(ModuleRole mr:moduleRoleList)
			{
				int dataLevel=getDataLevel(mr.getDataFilterLevel());
				if(dataLevel<access_level)
				{
					access_level=dataLevel;
				}
			}
		
			
			return access_level;
		}	
	
	
	private int getDataLevel(int dataFileter)
	{
			Datadict datadictAccess=null;
			
			for(Datadict dict:accessList)
			{
				if(dataFileter==dict.getId())
				{
					datadictAccess=dict;
					break;
				}
			}
			if(datadictAccess.getValue().equals("ACCESS_ALL"))
			{
				return 1;
			}
			if(datadictAccess.getValue().equals("ACCESS_ORG"))
			{
				return 2;
			}
			if(datadictAccess.getValue().equals("ACCESS_USERGROUP"))
			{
				return 3;
			}
			if(datadictAccess.getValue().equals("ACCESS_USER"))
			{
				return 4;
			}

		return 4;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

}

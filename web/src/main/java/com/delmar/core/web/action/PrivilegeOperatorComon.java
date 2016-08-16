/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.SystemConst;
import com.delmar.sys.model.Module;
import com.delmar.sys.model.ModulePage;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.UserContent;
import com.delmar.sys.service.ModulePageService;
import com.delmar.sys.service.ModuleService;
import com.delmar.system.web.WebConst;

/**
 * @author 刘大磊 2015年1月30日 上午9:02:38
 */
public class PrivilegeOperatorComon {
	public static boolean isView(HttpServletRequest request)
	{
		return isView(null,request);
	}
	public static boolean isView(String explicitPath,HttpServletRequest request)
	{
		String path;
		if (explicitPath!=null)
		   path=explicitPath;
		else
		   path=request.getServletPath();
		
		//System.out.println(request.getContextPath());
		 UserContent uc=	 (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		List<Page> urls=uc.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
		for(Page url:urls)
		{
			if(path.startsWith(url.getPageUrl()))return true;
		}
		return false;
	}
	
	public static boolean isCreate(HttpServletRequest request)
	{
		return isCreate(null,request);
	}
	
	public static boolean isCreate(String explicitPath,HttpServletRequest request)
	{
		String path;
		if (explicitPath!=null)
		   path=explicitPath;
		else
		   path=request.getServletPath();

		 UserContent uc=	 (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		List<Page> urls=uc.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_ADD);
		for(Page url:urls)
		{
			//System.out.print(url.getPageUrl());
			if(path.startsWith(url.getPageUrl()))return true;
		}
		return false;
	}
	
	public static boolean isUpdate(HttpServletRequest request)
	{
		return isUpdate(null,request);
	}
	
	public static boolean isUpdate(String explicitPath,HttpServletRequest request)
	{
		String path;
		if (explicitPath!=null)
		   path=explicitPath;
		else
		   path=request.getServletPath();

		 UserContent uc=	 (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		List<Page> urls=uc.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_UPDATE);
		for(Page url:urls)
		{
			if(path.startsWith(url.getPageUrl()))return true;
		}
		return false;
	}
	
	public static boolean isDelete(HttpServletRequest request)
	{
		return isDelete(null,request);
	}
	
	public static boolean isDelete(String explicitPath,HttpServletRequest request)
	{
		String path;
		if (explicitPath!=null)
		   path=explicitPath;
		else
		   path=request.getServletPath();

		 UserContent uc=	 (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		List<Page> urls=uc.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_DELETE);
		for(Page url:urls)
		{
			if(path.startsWith(url.getPageUrl()))return true;
		}
		return false;
	}
	
	public static Module getModule(HttpServletRequest request)
	{
		return getModule(null,request);
	}
	
	public static Module getModule(String explicitPath,HttpServletRequest request)
	{
		String path;
		if (explicitPath!=null)
		   path=explicitPath;
		else
		   path=request.getServletPath();

		 UserContent uc=	 (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
		List<Page> urls=uc.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
		for(Page url:urls)
		{
			if(path.startsWith(url.getPageUrl()))
			{
				ModulePageService modulePageService=EaContext.getBean("modulePageService", ModulePageService.class);
				Map<String,Object> param=new HashMap<String,Object>();
				param.put("pageId", url.getId());
				ModulePage mp=modulePageService.getByExample(param);
				ModuleService moduleService=EaContext.getBean("moduleService", ModuleService.class);
				return moduleService.selectByPrimaryKey(mp.getModuleId());
			}
		}
		return null;
	}
}

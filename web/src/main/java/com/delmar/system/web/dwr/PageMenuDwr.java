/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.sys.model.PageMenu;
import com.delmar.sys.service.PageMenuService;

/**
 * @author 刘大磊 2015年1月14日 下午1:45:40
 */
@Repository("pageMenuDwr") 
public class PageMenuDwr {
	private static final Logger logger=Logger.getLogger(PageMenuDwr.class);
	@Autowired
	private PageMenuService pageMenuService;
	public PageMenu[] getPagemenus(Integer parentMenuId)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		Map map=new HashMap();
		if(parentMenuId==null)
		{
			map.put("accessString", " PARENT_MENU_ID =0  ");
		}
		else
		{
			map.put("accessString", " PARENT_MENU_ID = "+parentMenuId);
		}
		
		map.put("orderByClause", " seq_no asc");
		
		List<PageMenu> menuList=pageMenuService.selectByExample(map);
		//System.out.println(menuList.size());
		PageMenu[] menus=new PageMenu[menuList.size()];
		for(int i=0;i<menuList.size();i++)
		{
			menus[i]=menuList.get(i);
		}
		return menus;
	}
  	public PageMenu getPageMenu(Integer menuId, Integer parentMenuId)
	{
  		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		PageMenu pm=new PageMenu();
		if(menuId==null||menuId==0)
		{
			pm.setMenuType("N");
			pm.setIcoPath("");
		if(parentMenuId!=null)
		{
			PageMenu parentMenu=(PageMenu)pageMenuService.selectByPrimaryKey(parentMenuId);
			pm.setParentMenu(parentMenu);
			pm.setParentMenuId(parentMenuId);
		}
		}
		else
		{
			pm=(PageMenu)pageMenuService.selectByPrimaryKey(menuId);
			
			PageMenu parentMenu=(PageMenu)pageMenuService.selectByPrimaryKey(pm.getParentMenuId());
			pm.setParentMenu(parentMenu);
		}
		return pm;
	}
	
	/**
	 * 保存菜单信息
	 * @param pageMenu
	 * @return
	 */
	public PageMenu savePagemenu(PageMenu pageMenu)
	{
		
		
		if(!DwrPrivilegeFilter.isCreate(this.getClass().getName()))
		{
			if(pageMenu.getId()==null)
			return null;
		}
		if(!DwrPrivilegeFilter.isUpdate(this.getClass().getName()))
		{
			if(pageMenu.getId()!=null)
			return null;
		}
		if(pageMenu.getParentMenuId()==null)
		{
			pageMenu.setParentMenuId(0);
		}
		 Integer id= pageMenuService.save(pageMenu);
		 pageMenu.setId(id);
		 return pageMenu;
		
	}
	@SuppressWarnings("unchecked")
	public String removeMenu(Integer menuId)
	{
		if(!DwrPrivilegeFilter.isDelete(this.getClass().getName()))
		{
			return null;
		}
		
		PageMenu temp=(PageMenu)pageMenuService.selectByPrimaryKey(menuId);
		if("Y".equals(temp.getMenuType()))
		{
		
			Map map=new HashMap();
			map.put("parentMenuId", menuId);
			Integer count=pageMenuService.countObjects(map);
			if(count>0)
			{
				return "N";
				
			}
			
		}
		pageMenuService.deleteByPrimaryKey(menuId);
		return "Y";
	}
}

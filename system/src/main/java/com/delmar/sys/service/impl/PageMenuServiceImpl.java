/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.DelmarConst;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.PageMenuDao;
import com.delmar.sys.model.PageMenu;
import com.delmar.sys.service.PageMenuService;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("pageMenuService")
public class PageMenuServiceImpl extends CoreServiceImpl<PageMenu> implements
		PageMenuService {
	@Autowired
	private PageMenuDao pageMenuDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<PageMenu> getCoreDao() {
		return pageMenuDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.PageMenuService#getLeftMenus(java.lang.Integer)
	 */
	@Override
	public String getLeftMenus(Integer userId,	Locale local) {
	
		List<PageMenu>pagemenus=pageMenuDao.getPageMenusbyUserId(userId);
		List<PageMenu>parentMenus=new ArrayList<PageMenu>();
		for(PageMenu pm:pagemenus)
		{
			this.getParentMenuId(parentMenus, pm.getParentMenuId());
		}
		pagemenus.addAll(parentMenus);
		
		StringBuilder sb=new StringBuilder();
		Comparator<PageMenu> compar=new Comparator<PageMenu>()
		{
			public int compare(PageMenu o1, PageMenu o2) {
				if(o1.getSeqNo()==null||o2.getSeqNo()==null)return 0;
				return o1.getSeqNo().compareToIgnoreCase(o2.getSeqNo());
				
			}
		};
		java.util.Collections.sort(pagemenus,compar);
		for(int i=0;i<pagemenus.size();i++)
		{
			
			PageMenu pm=pagemenus.get(i);
			if(pm.getParentMenuId()==null)
			{
				
				sb.append(getPageMenu(pm,"0",local));
			}else
			{
				sb.append(getPageMenu(pm,pm.getParentMenuId()+"",local));
			}
		}
	
		return sb.toString();
	}

	private StringBuilder getPageMenu(PageMenu pm,String parentMenuId,Locale local)
	{
		//java.util.Locale.CANADA
		 ResourceBundle bundle=null;
		if(local==null)
		{
			bundle=ResourceBundle.getBundle(ResourceMessage.BUNDLE_NAME);
		}
		else
		{
			bundle=ResourceBundle.getBundle(ResourceMessage.BUNDLE_NAME,local);
		}

		//d.add(1,0,'<fmt:message key="menu.system"/>');
		StringBuilder sb=new StringBuilder("d.add(");
		sb.append(pm.getId()).append(",").append(parentMenuId);
		sb.append(",'");
		
		if(StringUtil.isNotEmpty(pm.getMessagekey()))
		{
			try
			{
			sb.append(bundle.getString(pm.getMessagekey()));
			}
			catch(Exception e)
			{
				//System.out.println(pm.getMessagekey()+"  "+pm.getName());
				sb.append(pm.getName());
			}
		}
		else
		{
			sb.append(pm.getName());
		}
		
		sb.append("'");
		if("N".equals(pm.getMenuType()))
		{
			sb.append(",'javascript:page_go(\\'").append(pm.getPageUrl())
			.append("\\',\\'")
			.append(pm.getName())
			.append("\\')'");
		}
		else
		{
			
		}
		if(StringUtil.isNotEmpty(pm.getIcoPath()))
		{
			if(DelmarConst.TRUE.equals(pm.getMenuType()))
			{
				sb.append(",''");
			}
			sb.append(",'','','images/menu/");
			sb.append(pm.getIcoPath()).append("'");
		}
		
		
		sb.append(");\n");
		
		return sb;
	}
	private void getParentMenuId(List<PageMenu> parentMenus,Integer parentMenuId)
	{
		if(parentMenuId==null||parentMenuId==0)return;
		if(!hasParentMenu(parentMenus,parentMenuId))
		{
			PageMenu pm=pageMenuDao.selectByPrimaryKey(parentMenuId);
			parentMenus.add(pm);
			getParentMenuId(parentMenus, pm.getParentMenuId());
		}
		
		//return null;
	}
	private boolean hasParentMenu(List<PageMenu> parentMenus,Integer parentMenuId)
	{
		for(PageMenu pm:parentMenus)
		{
			if(pm.getId().equals(parentMenuId)) return true;
		}
		return false;
	}
}

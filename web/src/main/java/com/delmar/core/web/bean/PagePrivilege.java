/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.bean;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.delmar.sys.SystemConst;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.UserContent;

/**
 * @author 刘大磊 2015年1月14日 下午12:00:29
 */
public class PagePrivilege {
	public static PagePrivilege build(HttpSession session,String pageUrl)
	{
		UserContent userContent=(UserContent)session.getAttribute("userContent");
		PagePrivilege pp=new PagePrivilege();
		List<Page> addPageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_ADD);
		if(addPageList!=null)
		for(Page page:addPageList)
		{
			if(pageUrl.startsWith(page.getPageUrl()))
			{
				pp.create=true;
				break;
			}
		}
		List<Page> updatePageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_UPDATE);
		for(Page page:updatePageList)
		{
			if(pageUrl.startsWith(page.getPageUrl()))
			{
				pp.update=true;
				break;
			}
		}
		List<Page> viewPageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
		for(Page page:viewPageList)
		{
			if(pageUrl.startsWith(page.getPageUrl()))
			{
				pp.view=true;
				break;
			}
		}
		List<Page> deletePageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_DELETE);
		for(Page page:deletePageList)
		{
			if(pageUrl.startsWith(page.getPageUrl()))
			{
				pp.delete=true;
				break;
			}
		}
/*		for(String url:userContent.approves)
		{
			if(pageUrl.startsWith(url))
			{
				pp.approve=true;
				break;
			}
		}*/
		session.setAttribute("pagePri", pp);
		return pp;
	}
	private PagePrivilege()
	{
		this.create=false;
		this.update=false;
		this.view=false;
		this.delete=false;
		this.approve=false;
	}
	
	private boolean create;
	private boolean update;
	private boolean delete;
	private boolean view;
	private boolean approve;
	public boolean isCreate() {
		return create;
	}
	public void setCreate(boolean create) {
		this.create = create;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public boolean isApprove() {
		return approve;
	}
	public void setApprove(boolean approve) {
		this.approve = approve;
	}
	
}

/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.crm.web.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.service.LinkmanService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年3月12日 下午3:50:09
 */
@Repository("linkmanDwr") 
public class LinkmanDwr {
	@Autowired
	private LinkmanService linkmanService;
	
	public Linkman[] getLinkmanList(String name,Integer customerId)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		StringBuilder sb=new StringBuilder("");
		boolean hasname=false;
		if(StringUtil.isNotEmpty(name))
		{
			hasname=true;
			sb.append("name like '%");
			sb.append(StringUtil.fullYhStr(name.trim()));
			sb.append("%' ");
		}
		
		if(customerId!=null)
		{
			if(hasname)
			{
				sb.append(" and ");
			}
			sb.append("customer_id=")
			   .append(customerId);
		}
		Map<String,Object>param=new HashMap<String,Object>();
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String accessString=up.getDWRAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=sb.toString();
		else
			accessString=accessString+" and "+ sb.toString();

		
		param.put("accessString",accessString);
		List<Linkman> linkmanList=linkmanService.selectByExample(param);
		Linkman[] linkmanArray=new Linkman[linkmanList.size()];
		linkmanList.toArray(linkmanArray);
		return linkmanArray;
	}
	
	public String getLinkmanGson(Integer customerId)
	{
		Linkman[] linkmanarray=getLinkmanList("",customerId);
		//System.out.println((new Gson()).toJson(linkmanarray));
		return (new Gson()).toJson(linkmanarray);
	
		
	}
}

package com.delmar.crm.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Repository("linkrecordDwr") 
public class LinkrecordDwr {
	@Autowired
	private LinkrecordService linkrecordService;
	
	public Linkrecord[] getLinkrecordList(String id,String idType)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		StringBuilder sb=new StringBuilder("");
		if(id!=null)
		{
			if (idType.equals("customer"))
			  sb.append("customer_Id=")
			     .append(id);
			else if (idType.equals("linkman"))
				sb.append("linkman_Id=")
			     .append(id);	
			else if (idType.equals("linkrecordid"))
				sb.append("linkrecord_id='")
			     .append(StringUtil.fullYhStr(id)).append("'");				
		}
		Map<String,Object>param=new HashMap<String,Object>();
		
		
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String accessString=up.getDWRAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=sb.toString();
		else
			accessString=accessString+" and "+ sb.toString();
			
		
	
		
		param.put("accessString",accessString);		
		param.put("orderByClause"," contact_date desc ");		
		List<Linkrecord> linkrecordList=linkrecordService.selectByExample(param);
		Linkrecord[] linkrecordArray=new Linkrecord[linkrecordList.size()];
		linkrecordList.toArray(linkrecordArray);
		return linkrecordArray;
	}
	
	
	public Linkrecord[] getLinkrecordList(String id,Integer customerId,String idType)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		StringBuilder sb=new StringBuilder("");
		if(id!=null)
		{
		   sb.append("linkrecord_id='")
			     .append(StringUtil.fullYhStr(id)).append("'");	
		   
			if(customerId!=null)
			{
	    		sb.append(" and customer_id=")
				   .append(customerId);
			}
					   
		}
		

		Map<String,Object>param=new HashMap<String,Object>();
		
		
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String accessString=up.getDWRAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=sb.toString();
		else
			accessString=accessString+" and "+ sb.toString();
			
		
	
		
		param.put("accessString",accessString);		
		param.put("orderByClause"," contact_date desc ");		
		List<Linkrecord> linkrecordList=linkrecordService.selectByExample(param);
		Linkrecord[] linkrecordArray=new Linkrecord[linkrecordList.size()];
		linkrecordList.toArray(linkrecordArray);
		return linkrecordArray;
	}	
	
	public String getLinkrecordGson(Integer customerId,String idType)
	{
		Linkrecord[] linkrecordarray=getLinkrecordList(customerId.toString(),idType);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();  
		return gson.toJson(linkrecordarray);
	}
	
	
   	
}

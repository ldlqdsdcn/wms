/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CityTrlService;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年2月10日 下午1:12:31
 */
@Repository("cityDwr") 
public class CityDwr {
	@Autowired
	private CityService cityService;
	@Autowired
	private CityTrlService cityTrlService;
	
	public CityTrl[] getCityList(String value)
	{
		//System.out.println("bgn");
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(value==null)
			value="";
		Map param=new HashMap();
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		param.put("accessString", "language='"+ur.getLocale().toString()+"' and name like '%"+value.trim()+"%'   and id in (SELECT  id FROM base_city where levelint>=3)");
		List<CityTrl> list=cityTrlService.selectByExample(param);
		if(list!=null)
		{
			
			if(list.size()>10)
			{
				list=list.subList(0,10);
			}
		CityTrl[] cityTrlArray=new CityTrl[list.size()];
		list.toArray(cityTrlArray);
		//System.out.println("comp");
		return cityTrlArray;
		}
		return null;
	}
	public CityTrl[] getCityProvinceList(String value,String countryId)
	{
		//System.out.println("bgn");
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(value==null)
			value="";
		Map param=new HashMap();
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		
		StringBuilder sb=new StringBuilder("");
		 if(StringUtil.isNotEmpty(countryId))
		{
			sb.append("and parent_id=").append(countryId);
		}
		
		
		param.put("accessString", "language='"+ur.getLocale().toString()+"' and  ((name like '%"+value.trim()+"%') or (remark like '%"+value.trim()+"%'))   and  city_id in( select id from base_city where levelint=2 "+ sb.toString()+")");
		List<CityTrl> list=cityTrlService.selectByExample(param);
		if(list!=null)
		{
			
			if(list.size()>10)
			{
				list=list.subList(0,10);
			}
		CityTrl[] cityTrlArray=new CityTrl[list.size()];
		list.toArray(cityTrlArray);
		//System.out.println("comp");
		return cityTrlArray;
		}
		return null;
	}
	public CityTrl[] getCityCountryList(String value)
	{
		//System.out.println("bgn");
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(value==null)
			value="";
		Map param=new HashMap();
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		param.put("accessString", "language='"+ur.getLocale().toString()+"' and ((name like '%"+value.trim()+"%') or (remark like '%"+value.trim()+"%'))  and  city_id in( select id from base_city where levelint=1)");
		List<CityTrl> list=cityTrlService.selectByExample(param);
		if(list!=null)
		{
			
			if(list.size()>10)
			{
				list=list.subList(0,10);
			}
		CityTrl[] cityTrlArray=new CityTrl[list.size()];
		list.toArray(cityTrlArray);
		//System.out.println("comp");
		return cityTrlArray;
		}
		return null;
	}
	public CityTrl[] getCityCityList(String value,String countryId,String provinceId)
	{
		//System.out.println("bgn");
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(value==null)
			value="";
		Map param=new HashMap();
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		StringBuilder sb=new StringBuilder("");
		if(StringUtil.isNotEmpty(provinceId))
		{
			sb.append("and parent_id=").append(provinceId);
		}
		else if(StringUtil.isNotEmpty(countryId))
		{
			sb.append("and parent_id in (select id from base_city where parent_id=").append(countryId).append(")");
		}
			
		
		param.put("accessString", "language='"+ur.getLocale().toString()+"' and  ((name like '%"+value.trim()+"%') or (remark like '%"+value.trim()+"%'))  and  city_id in( select id from base_city where levelint=3 "+sb.toString()+")");
		List<CityTrl> list=cityTrlService.selectByExample(param);
		if(list!=null)
		{
			
			if(list.size()>10)
			{
				list=list.subList(0,10);
			}
		CityTrl[] cityTrlArray=new CityTrl[list.size()];
		list.toArray(cityTrlArray);
		//System.out.println("comp");
		return cityTrlArray;
		}
		return null;
	}
}

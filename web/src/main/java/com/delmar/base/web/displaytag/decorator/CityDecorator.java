/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.displaytag.decorator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.City;
import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CityTrlService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年2月10日 上午10:47:47
 */
public class CityDecorator implements DisplaytagColumnDecorator {

	private CityService cityService=EaContext.getBean("cityService", CityService.class);
	private CityTrlService cityTrlService=EaContext.getBean("cityTrlService", CityTrlService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0==null)
		{
			return null;
		}
		if(arg0 instanceof Integer)
		{
			
		
			HttpServletRequest request=(HttpServletRequest)arg1.getRequest();
			UserResource ur=(UserResource)request.getSession().getAttribute("resource");
			Map<String,Object>param=new HashMap<String,Object>();
			Integer carrierId=(Integer)arg0;
			param.put("cityId", carrierId);
			param.put("language", ur.getLocale().toString());
			
			CityTrl cityTrl=cityTrlService.getByExample(param);
			if(cityTrl==null)
			{
				City city=cityService.selectByPrimaryKey((Integer)arg0);
				return	city==null?null:city.getCname();
			}
			else
			{
				return cityTrl.getName();
			}
			
		}
		return null;
	}

}

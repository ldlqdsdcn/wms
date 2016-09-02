/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.displaytag.decorator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTrlService;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年3月23日 上午11:44:14
 */
public class DatadictValueDecorator implements DisplaytagColumnDecorator {
	private DatadictService datadictService=EaContext.getBean("datadictService", DatadictService.class);
	private DatadictTrlService datadictTrlService=EaContext.getBean("datadictTrlService", DatadictTrlService.class);
	private DatadictTypeService datadictTypeService=EaContext.getBean("datadictTypeService", DatadictTypeService.class);
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
			
			DatadictType dt=datadictTypeService.getDatadictType(DatadictType.EBUSINESS_STATUS);
			Map<String,Object> param1=new HashMap<String,Object>();
			param1.put("datadictTypeId", dt.getId());
			param1.put("value", arg0+"");
			List<Datadict> list=datadictService.selectByExample(param1);
			if(list!=null)
			for(Datadict dict:list)
			{
				HttpServletRequest request=(HttpServletRequest)arg1.getRequest();
				UserResource ur=(UserResource)request.getSession().getAttribute("resource");
				Map<String,Object>param=new HashMap<String,Object>();
				param.put("datadictId", dict.getId());
				param.put("language", ur.getLocale().toString());
				
				DatadictTrl carrierTrl=datadictTrlService.getByExample(param);
				if(carrierTrl==null)
				{
					Datadict datadict=datadictService.selectByPrimaryKey((Integer)arg0);
					return	datadict==null?null:datadict.getName();
				}
				else
				{
					return carrierTrl.getName();
				}
				
			}
			}
			
			
		
		
		return null;
	}
}

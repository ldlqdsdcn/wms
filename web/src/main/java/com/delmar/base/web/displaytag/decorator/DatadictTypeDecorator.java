/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.displaytag.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.web.bean.EaContext;

/**
 * @author 刘大磊 2015年3月5日 下午4:39:43
 */
public class DatadictTypeDecorator implements DisplaytagColumnDecorator {
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
			DatadictType dt=datadictTypeService.selectByPrimaryKey((Integer)arg0);
			if(dt!=null)
			{
				return	dt.getName();
			}
		}
		return null;
	}

}

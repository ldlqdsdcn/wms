/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.crm.web.displaytag.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.core.web.bean.EaContext;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.service.LinkmanService;

/**
 * 
 */
public class LinkmanDecorator implements DisplaytagColumnDecorator {

	private LinkmanService linkmanService=EaContext.getBean("linkmanService", LinkmanService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0!=null)
		{
			Integer value=(Integer)arg0;
			StringBuilder sb=new StringBuilder("");
			Linkman user=linkmanService.selectByPrimaryKey(value);
			if(user!=null)
			{
				return user.getName();
			}
			else
			{
				return null;
			}
		}
		
		
		return null;
	}


}

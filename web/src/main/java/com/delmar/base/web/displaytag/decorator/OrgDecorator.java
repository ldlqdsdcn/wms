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

import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.sys.model.Org;
import com.delmar.sys.service.OrgService;

/**
 * @author 刘大磊 2015年3月11日 下午4:56:06
 */
public class OrgDecorator implements  DisplaytagColumnDecorator {
	private OrgService orgService=SystemContextHelper.getBean("orgService", OrgService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0!=null)
		{
			Integer orgId=(Integer)arg0;
			Org org=orgService.selectByPrimaryKey(orgId);
			if(org!=null)
			{
				return org.getName();
			}
		}
		return null;
	}



}

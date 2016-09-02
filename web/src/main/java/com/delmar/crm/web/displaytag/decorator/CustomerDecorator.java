/******************************************************************************
 * 刘大磊  2013-07-01												  *
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
import com.delmar.crm.model.Customer;
import com.delmar.crm.service.CustomerService;

/**
 * @author 刘大磊 2015年3月10日 下午4:40:01
 */
public class CustomerDecorator implements DisplaytagColumnDecorator {

	private CustomerService customerService=EaContext.getBean("customerService", CustomerService.class);
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
			Customer user=customerService.selectByPrimaryKey(value);
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

/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.displaytag.decorator;

import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * @author 刘大磊 2015年3月25日 下午5:58:51
 */
public class DateDecorator implements DisplaytagColumnDecorator {

	/** (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object value, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		
		return com.delmar.utils.DateTimeDecorator.dateToShortString((Date)value);
	}

}

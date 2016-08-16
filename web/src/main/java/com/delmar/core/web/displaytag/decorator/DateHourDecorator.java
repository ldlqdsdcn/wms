package com.delmar.core.web.displaytag.decorator;

import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DateHourDecorator implements DisplaytagColumnDecorator {

	/** (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object value, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		
		return com.delmar.utils.DateTimeDecorator.dateToShortStringHour((Date)value);
	}

}

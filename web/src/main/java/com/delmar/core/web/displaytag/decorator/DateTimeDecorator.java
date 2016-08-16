/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.displaytag.decorator;

import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * @author 刘大磊 2015年1月16日 下午4:57:25
 */
public class DateTimeDecorator implements DisplaytagColumnDecorator{
	public Object decorate(Object value, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		
		return com.delmar.utils.DateTimeDecorator.dateToLongString((Date)value);
	}


}

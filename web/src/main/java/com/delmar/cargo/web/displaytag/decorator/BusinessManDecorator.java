package com.delmar.cargo.web.displaytag.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.cargo.model.BussinessMan;
import com.delmar.cargo.service.BusinessManService;
import com.delmar.core.web.bean.EaContext;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 上午10:45:54 
 * 类说明 
 */
public class BusinessManDecorator  implements DisplaytagColumnDecorator {

	private BusinessManService businessManService=EaContext.getBean("businessManService", BusinessManService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0!=null)
		{
			String value=(String)arg0;
			BussinessMan person=businessManService.getObject(value);
			if(person!=null)
			{
				return  person.getName();
			}
			else
			{
				return null;
			}
		}
		
		
		return null;
	}


}

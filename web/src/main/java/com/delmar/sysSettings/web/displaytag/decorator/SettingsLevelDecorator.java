package com.delmar.sysSettings.web.displaytag.decorator;


import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.Datadict;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.bean.EaContext;

public class SettingsLevelDecorator implements DisplaytagColumnDecorator {

	private DatadictService datadictService=EaContext.getBean("datadictService", DatadictService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if (arg0==null) {
			return "";
		}
		if (arg0 instanceof Integer) {
			
			Integer carrierId=(Integer)arg0;
			
			Datadict datadict = datadictService.selectByPrimaryKey(carrierId);
			return	datadict == null ? "" : datadict.getName();
		}
		
		return "";
	}

}


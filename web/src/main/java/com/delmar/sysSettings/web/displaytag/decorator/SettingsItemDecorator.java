package com.delmar.sysSettings.web.displaytag.decorator;


import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.service.SysSettingsItemService;

public class SettingsItemDecorator implements DisplaytagColumnDecorator {

	private SysSettingsItemService sysSettingsItemService=SystemContextHelper.getBean("SysSettingsItemService", SysSettingsItemService.class);
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
			
			SysSettingsItem sysSettingsItem = sysSettingsItemService.selectByPrimaryKey(carrierId);
			return	sysSettingsItem == null ? "" : sysSettingsItem.getName();
		}
		
		return "";
	}

}


package com.delmar.sysSettings.web.displaytag.decorator;


import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.sysSettings.enums.InputTypeEnum;

public class SettingsInputDecorator implements DisplaytagColumnDecorator {

	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if (arg0==null) {
			return "";
		}
			
		InputTypeEnum[] inputTypes = InputTypeEnum.values();
		for (InputTypeEnum inputType : inputTypes) {
			if (inputType.getValue().equals(arg0)) {
				return inputType.getName();
			}
		}
		
		return "";
	}

}


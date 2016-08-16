package com.delmar.officeTest.web.displaytag.decorator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.Carrier;
import com.delmar.base.model.CarrierTrl;
import com.delmar.base.service.CarrierService;
import com.delmar.base.service.CarrierTrlService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.service.OfficeTestBankService;

public class BooleanDecorator implements DisplaytagColumnDecorator {

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
			
			if ("0".equals(String.valueOf(arg0))) {
				return "否";
			} else {
				return "是";
			}
		}
		
		return "";
	}

}


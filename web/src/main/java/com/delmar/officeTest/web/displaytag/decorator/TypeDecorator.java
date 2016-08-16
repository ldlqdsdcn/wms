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
import com.delmar.base.model.Datadict;
import com.delmar.base.service.CarrierService;
import com.delmar.base.service.CarrierTrlService;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.service.OfficeTestBankService;

public class TypeDecorator implements DisplaytagColumnDecorator {

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
			
			HttpServletRequest request=(HttpServletRequest)arg1.getRequest();
			UserResource ur=(UserResource)request.getSession().getAttribute("resource");
			Map<String,Object>param=new HashMap<String,Object>();
			Integer carrierId=(Integer)arg0;
			param.put("carrierId", carrierId);
			param.put("language", ur.getLocale().toString());
			
			Datadict datadict = datadictService.selectByPrimaryKey(carrierId);
			return	datadict == null ? "" : datadict.getName();
		}
		
		return "";
	}

}


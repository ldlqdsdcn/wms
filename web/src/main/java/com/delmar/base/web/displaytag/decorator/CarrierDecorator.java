/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.displaytag.decorator;

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

/**
 * @author 刘大磊 2015年2月5日 上午10:44:39
 * 
 * 船公司装饰类
 */
public class CarrierDecorator implements DisplaytagColumnDecorator {

	private CarrierService carrierService=EaContext.getBean("carrierService", CarrierService.class);
	private CarrierTrlService carrierTrlService=EaContext.getBean("carrierTrlService", CarrierTrlService.class);
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0==null)
		{
			return null;
		}
		if(arg0 instanceof Integer)
		{
			
		
			HttpServletRequest request=(HttpServletRequest)arg1.getRequest();
			UserResource ur=(UserResource)request.getSession().getAttribute("resource");
			Map<String,Object>param=new HashMap<String,Object>();
			Integer carrierId=(Integer)arg0;
			param.put("carrierId", carrierId);
			param.put("language", ur.getLocale().toString());
			
			CarrierTrl carrierTrl=carrierTrlService.getByExample(param);
			if(carrierTrl==null)
			{
				Carrier carrier=carrierService.selectByPrimaryKey((Integer)arg0);
				return	carrier==null?null:carrier.getCname();
			}
			else
			{
				return carrierTrl.getName();
			}
			
		}
		
		
		return null;
	}

}

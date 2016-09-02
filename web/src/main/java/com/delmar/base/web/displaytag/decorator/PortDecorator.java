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

import com.delmar.base.model.Port;
import com.delmar.base.model.PortTrl;
import com.delmar.base.service.PortService;
import com.delmar.base.service.PortTrlService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年2月9日 下午5:12:28
 */
public class PortDecorator implements DisplaytagColumnDecorator {
	private PortService portService=EaContext.getBean("portService", PortService.class);
	private PortTrlService portTrlService=EaContext.getBean("portTrlService", PortTrlService.class);
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
			param.put("portId", carrierId);
			param.put("language", ur.getLocale().toString());
			
			PortTrl portTrl=portTrlService.getByExample(param);
			if(portTrl==null)
			{
				Port port=portService.selectByPrimaryKey((Integer)arg0);
				return	port==null?null:port.getPortcname();
			}
			else
			{
				return portTrl.getName();
			}
			
		}
		return null;
	}

}

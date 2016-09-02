/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.displaytag.decorator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.model.PortMode;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTrlService;
import com.delmar.base.service.PortModeService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年2月9日 下午5:16:46
 */
public class PortModeDecorator implements DisplaytagColumnDecorator {
	private PortModeService portModeService=EaContext.getBean("portModeService", PortModeService.class);
	private DatadictService datadictService=EaContext.getBean("datadictService", DatadictService.class);
	private DatadictTrlService datadictTrlService=EaContext.getBean("datadictTrlService", DatadictTrlService.class);
	/** (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		HttpServletRequest request=(HttpServletRequest)arg1.getRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		String language=ur.getLocale().toString();
		Integer value=(Integer)arg0;
		StringBuilder sb=new StringBuilder("");
		Map param=new HashMap();
		param.put("basePortId", value);
		List<PortMode> portModeList=portModeService.selectByExample(param);
		if(portModeList!=null&&portModeList.size()>0)
		{
			List<DatadictTrl> list=datadictService.getDatadictTrlByValue(DatadictType.MODE, language);
			for(PortMode pm:portModeList)
			{
				for(DatadictTrl trl:list)
				{
					if(pm.getDatadictId().equals(trl.getDatadictId()))
					{
						sb.append(trl.getName()+" ");
						list.remove(trl);
						break;
					}
				}
			
			
			}
		
		

			
		}
		return sb.toString();
	}

}

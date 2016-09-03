package com.delmar.base.web.dwr;

import com.delmar.base.model.PortTrl;
import com.delmar.base.service.PortService;
import com.delmar.base.service.PortTrlService;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *@ClassName:   PortDwr.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年8月13日 下午9:50:41
 * @version V2.0
 */
@Repository("portDwr") 
public class PortDwr {
	@Autowired
	private PortService portService;
	@Autowired
	private PortTrlService portTrlService;
	
	public PortTrl[] getPortList(String value)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(value==null)
			value="";
		Map param=new HashMap();
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		param.put("accessString", "language='"+ur.getLocale().toString()+"' and  ((name like '%"+value.trim()+"%') or (remark like '%"+value.trim()+"%'))   ");		
		List<PortTrl> list=portTrlService.selectByExample(param);
		if(list!=null)
		{
			
			if(list.size()>10)
			{
				list=list.subList(0,10);
			}
		PortTrl[] portTrlArray=new PortTrl[list.size()];
		list.toArray(portTrlArray);
		return portTrlArray;
		}
		return null;
	}
	

}

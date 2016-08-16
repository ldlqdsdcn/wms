/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system.web.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.model.Client;
import com.delmar.sys.service.ClientService;

/**
 * @author 刘大磊 2015年1月16日 下午1:36:51
 */
public class SelectList {
	public static String getClientList(HttpServletRequest request)
	{
		ClientService clientService=EaContext.ApplicationContext.getBean("clientService", ClientService.class);
		StringBuffer sb=new StringBuffer("<select id='clientId'>");
		List<Client> clientList=clientService.selectByExample(null);
		for(Client client:clientList)
		{
			sb.append("<option value='").append(client.getId()).append("'>").append(client.getName())
			.append("</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}
}

/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.ClientService;
import com.delmar.sys.service.OrgService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;

/**
 * @author 刘大磊 2015年1月22日 下午5:02:40
 */
public class UserLoginPageSelectList {
	private  ClientService clientService;
	private  OrgService orgService;
	List<Client> clientList=new ArrayList<Client>();
	List<Org> orgList;
	/**
	 * 
	 */
	public UserLoginPageSelectList() {
		if(clientService==null)
			clientService=SystemContextHelper.getBean("clientService", ClientService.class);
		if(orgService==null)
			orgService=SystemContextHelper.getBean("orgService",OrgService.class);
	}
	public  String  getOrgsSelectList(HttpSession session)
	{

		PrivilegesDataFilter up=(PrivilegesDataFilter)session.getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Map<String,Object>param=new HashMap<String,Object>();
		
		List<UserorgAccess> userorgAccessList=(List<UserorgAccess>)session.getAttribute("userorgaccessList");
		
		
		if(up.getLoginClientId()==null)
		{
			param.put("sysClientId", clientList.get(0).getId());
		}
		else
		{
			param.put("sysClientId", up.getLoginClientId());
		}
		
		orgList=orgService.selectByExample(param);
		StringBuffer sb=new StringBuffer();
		sb.append("<select id='orgId' name='orgId' style='width:300px;'> ");
		for(Org org:orgList)
		{
			boolean beExist=false;
			for (UserorgAccess userorg:userorgAccessList)
			{
				if (userorg.getOrgId().equals(org.getId()))
				{
					beExist=true;
					break;
				}
			}
			
			if (beExist==false)
				continue;
			
			
			sb.append("<option value='").append(org.getId()).append("' ");
			if(org.getId().equals(up.getLoginOrgId()))
			{
				sb.append(" selected=\"selected\" ");
			}
			
			sb.append(">");
			
			sb.append(org.getName());
			sb.append("</option>");
		}
		sb.append("</select>");
		
		return sb.toString();
	}
	
	public String getClientSelectList(HttpSession session)
	{
		StringBuffer sb=new StringBuffer();
		PrivilegesDataFilter up=(PrivilegesDataFilter)session.getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Integer clientId=up.getLoginClientId();
		
		clientList.add(clientService.selectByPrimaryKey(clientId));
		
		sb.append("<select id='clientId' name='clientId' onchange='javascript:getOrgs()' style='width:300px;'> ");
		for(Client client:clientList)
		{
			sb.append("<option value='").append(client.getId()).append("' ");
			if(client.getId().equals(up.getLoginClientId()))
			{
				sb.append(" selected=\"selected\" ");
			}
			
			sb.append(">");
			sb.append(client.getName());
			sb.append("</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}
}

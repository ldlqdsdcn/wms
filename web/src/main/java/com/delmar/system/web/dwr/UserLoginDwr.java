/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UsergroupAccessService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;

/**
 * @author 刘大磊 2015年1月22日 下午5:37:09
 */
@Repository("userLoginDwr")
public class UserLoginDwr {
	@Autowired
	private OrgService orgService;
	@Autowired
	private UsergroupAccessService  usergroupAccessService;
	
	public String getOrgSelectByClientId(Integer clientId)
	{
		
		
		WebContext webContext = WebContextFactory.get();
		System.out.println("RequestURI="+webContext.getHttpServletRequest().getRequestURI());
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("sysClientId", clientId);
		List<Org> orgList=orgService.selectByExample(param);
		StringBuffer sb=new StringBuffer("");
		for(Org o:orgList)
		{
			sb.append("<option value='").append(o.getId()).append("'>");
			sb.append(o.getName());
			sb.append("</option>");
		}
		
		
		
		
		return sb.toString();
	}
	public String selectClientAndOrg(Integer clientId,String clientName,Integer orgId,String orgName)
	{
		
		
		WebContext webContext = WebContextFactory.get();
		PrivilegesDataFilter up=(PrivilegesDataFilter)webContext.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		User user=(User)webContext.getSession().getAttribute(WebConst.SESSION_ACTUALUSER);
		up.setLoginClientId(clientId);
		up.setLoginClientName(clientName);
		up.setLoginOrgId(orgId);
		up.setLoginOrgName(orgName);
		
		List<Integer> users=usergroupAccessService.selectUserAccessUserId(orgId, user.getId());
		Integer[] userarray=new Integer[users.size()];
		users.toArray(userarray);
		//up.setAccessUsers(userarray);
		up.build();
		webContext.getSession().setAttribute(WebConst.SESSION_USERPRIVILEGES, up);
		return "Y";
	}
}

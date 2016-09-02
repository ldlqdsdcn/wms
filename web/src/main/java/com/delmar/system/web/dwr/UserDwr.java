/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.User;
import com.delmar.sys.model.Usergroup;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.RoleService;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UsergroupService;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月16日 下午5:29:40
 */
@Repository("userDwr") 
public class UserDwr {
	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UsergroupService usergroupService;

	public Usergroup[] getUsergroupsListId(Integer[] orgIds)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		if(orgIds!=null)
		{
			List<Usergroup> groupList=new ArrayList<Usergroup>();
			for(Integer orgId:orgIds)
			{
				if(orgId==null)
				{
					continue;
				}
				Map<String, Integer> param=new HashMap<String, Integer> ();
				param.put("orgId", orgId);
				List<Usergroup> usergroupList=usergroupService.selectByExample(param);
				if(usergroupList!=null)
				groupList.addAll(usergroupList);
			}
			Usergroup[] usergroupArray=new Usergroup[groupList.size()];
			groupList.toArray(usergroupArray);
			return usergroupArray;
		}
	
		
		return null;
	}
	
	
	public User[] getUserList(String userName,Integer orgId)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		if (userName.equals(""))
		  return null;
		
				
		Map<String, String> param=new HashMap<String, String> ();
		StringBuilder sb=new StringBuilder();
		
		if(orgId!=null)
		{
			sb.append(" and org_id=").append(orgId.toString());
		}
		
		sb.append(" and userName like '%").append(StringUtil.fullYhStr(userName)).append("%' ");
		
		param.put("accessString", " 1=1 "+sb.toString());
		List<User> userList=userService.selectByExample(param);	

	    User[] userArray=new User[userList.size()];
	    userList.toArray(userArray);
	    return userArray;
	}
	
	
	public Org[] getOrgsByClientId(Integer clientId)
	{
		
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		Map param=new HashMap();
		param.put("sysClientId", clientId);
		List<Org> orgList=orgService.selectByExample(param);
		Org[] orgArray=new Org[orgList.size()];
		orgList.toArray(orgArray);
		return orgArray;
	}
	public List<Org> testOrgList()
	{
		return orgService.selectByExample(null);
	}
	
	public String getDepartemtns(Integer[] depaIds,Integer roleCount)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		
		StringBuilder sb=new StringBuilder("");
		List<Org> orgs=orgService.selectByExample(null);
		for(int i=0;i<orgs.size();i++)
		{
			Org depa=orgs.get(i);
			sb.append("<input type='checkbox' name='orgs' value='").append(depa.getId()).append("' ");
			if(hasRole(depa.getId(),depaIds))
			{
				sb.append(" checked='checked'  ");
			}
			sb.append(" onclick='getRoles()'/>&nbsp;");
			sb.append(depa.getName()).append("&nbsp;&nbsp;&nbsp;");
			
			
			
			if((i+1)%roleCount==0)
			{
				
				
				sb.append("<br>");
			}
		}
		return sb.toString();
	}
	public String getUsergroupByOrgid(Integer orgId)
	{
		
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		StringBuilder sb=new StringBuilder("");
		
		
		
		
		return sb.toString();
	}
	
	
	public String getRoles(Integer[] orgIds,Integer[] roleIds,Integer roleCount)
	{
		/*if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}*/

		return this.getRoles2(orgIds, roleIds, roleCount);
	}
	public String getRoles2(Integer[] orgIds,Integer[] roleIds,Integer roleCount)
	{

		
		
		StringBuilder sb=new StringBuilder("");
		if(orgIds!=null)
		{
			List<Role> roles=roleService.selectByExample(null);
			
			for(int i=0;i<roles.size();i++)
			{
				createRoleCheckBox(sb,roles.get(i),roleIds);
				if((i+1)%roleCount==0)
				{
					sb.append("<br>");
				}
			}
			
			
		}
		else
		{
			
		}
		
		return sb.toString();
	}
	
	private void createRoleCheckBox(StringBuilder sb,Role role,Integer[] roleIds)
	{
		sb.append("<input type='checkbox' name='roles' value='").append(role.getId()).append("' ");
		if(hasRole(role.getId(),roleIds))
		{
			sb.append(" checked='checked' ");
		}
		sb.append("/>&nbsp;");
		sb.append(role.getName()).append("&nbsp;&nbsp;&nbsp;");
	}
	
	private boolean hasRole(Integer roleId,Integer[] roleIds)
	{
		if(roleIds==null)
		{
			return false;
		}
		for(Integer id:roleIds)
		{
			if(roleId.equals(id))
			{
				return true;
			}
		}
		
		return false;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



}

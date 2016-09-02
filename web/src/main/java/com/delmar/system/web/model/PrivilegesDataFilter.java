/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContextFactory;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.DelmarConst;
import com.delmar.core.excep.MissingParameterException;
import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.SystemConst;
import com.delmar.sys.model.ModulePage;
import com.delmar.sys.model.ModuleRole;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.ModulePageService;
import com.delmar.sys.service.ModuleRoleService;
import com.delmar.sys.service.UserRoleService;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UsergroupAccessService;
import com.delmar.sys.service.UserorgAccessService;
import com.delmar.system.web.WebConst;

/**
 * @author 刘大磊 2015年1月27日 上午9:55:03
 * 
 * 数据过滤权限设置
 */
public class PrivilegesDataFilter {
	private static UserService userService;
	private static UserorgAccessService userorgAccessService;
	private static UsergroupAccessService usergroupAccessService;
	private static ModuleRoleService moduleRoleService;
	private static UserRoleService userRoleService;
	private static ModulePageService modulePageService;
	private static DatadictTypeService datadictTypeService;
	private static DatadictService datadictService;
	private static List<Datadict> accessList;
	static
	{
		userService=EaContext.ApplicationContext.getBean("userService",UserService.class);
		userorgAccessService=EaContext.ApplicationContext.getBean("userorgAccessService",UserorgAccessService.class);
		usergroupAccessService=EaContext.ApplicationContext.getBean("usergroupAccessService",UsergroupAccessService.class);
		moduleRoleService=EaContext.ApplicationContext.getBean("moduleRoleService",ModuleRoleService.class);
		userRoleService=EaContext.ApplicationContext.getBean("userRoleService",UserRoleService.class);
		modulePageService=EaContext.ApplicationContext.getBean("modulePageService",ModulePageService.class);
		datadictTypeService=EaContext.ApplicationContext.getBean("datadictTypeService",DatadictTypeService.class);
		datadictService=EaContext.ApplicationContext.getBean("datadictService",DatadictService.class);
		accessList=datadictService.getDatadictListByTypeValue(DatadictType.ACCESS_LEVEL);
	}
	/**
	 * @param loginClientId 登录选择实体
	 * @param loginOrgId  登录选择组织
	 * @param accessUsers 可访问用户组
	 */
	public PrivilegesDataFilter(Integer loginClientId,Integer loginOrgId,Integer userId)
	{
		this.loginClientId=loginClientId;
		this.loginOrgId=loginOrgId;

	}
	public PrivilegesDataFilter()
	{
		
	}
	public PrivilegesDataFilter loginClientId(Integer loginClientId)
	{
		this.loginClientId=loginClientId;
		return this;
	}
	public PrivilegesDataFilter loginOrgId(Integer loginOrgId)
	{
		this.loginOrgId=loginOrgId;
		return this;
	}
	public PrivilegesDataFilter userId(Integer userId)
	{
		this.userId=userId;
		return this;
	}
	public PrivilegesDataFilter user(User user)
	{
		this.user=user;
		return this;
	}
	public PrivilegesDataFilter accessUsers( Integer[] accessUsers)
	{
		return this;
	}
	public PrivilegesDataFilter build()
	{
		if(userId==null)
		{
			throw new MissingParameterException("user id 不允许为空");
		}
		if(loginOrgId==null)
		{
			throw new MissingParameterException("org id 不允许为空");
		}
		if(loginClientId==null)
		{
			throw new MissingParameterException("client id 不允许为空");
		}
		if(this.user==null)
		{
			user=this.userService.selectByPrimaryKey(userId);
		}
		
		
		
		
		StringBuilder orgsb=new StringBuilder("org_id in(0");
		List<UserorgAccess> userorgList=this.userorgAccessService.selectUserorgAccessByUserId(userId);
		if(userorgList!=null)
		{
			for(UserorgAccess ua:userorgList)
			{
				orgsb.append(",");
				orgsb.append(ua.getOrgId());
			}
		}
		orgsb.append(")");
		this.allAccessString=orgsb.toString();
		
		this.orgAccessString=" org_id="+loginOrgId;
		
		StringBuilder usersb=new StringBuilder("user_id in(");
		usersb.append(userId.intValue());
		List<Integer> accessUserIdList=usergroupAccessService.selectUserAccessUserId2(userId);
		for(Integer userId:accessUserIdList)
		{
			usersb.append(",");
			usersb.append(userId);
		}
		usersb.append(")");
		this.groupAccessString=usersb.toString();
		this.userAccessString="user_id="+userId;
		return this;
	}
	
	/**
	 * 登录实体
	 */
	private Integer loginClientId;
	private String loginClientName;
	/**
	 * 登录组织
	 */
	private Integer loginOrgId;
	private String loginOrgName;
	
	/**登陆用户ID*/
	
	private Integer loginUserId;
	private User loginUser;
	
	
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 登录用户 可能可 当前登录的user 不一样。比如当前用户为管理员，可以登录为业务员1 
	 */
	private User user;
	
	private Integer userClientId;
	private String userClientName;
	private Integer userOrgId;
	private String userOrgName;
	
	
	 /**  
	 * @Fields allAccessString : 集团的权限   
	 */  
	private String allAccessString;
	/**
	 * 
	 */
	private String orgAccessString;
	
	/**
	 * 
	 */
	private String groupAccessString;
	
	
	
	
	public String getAllAccessString() {
		return allAccessString;
	}
	public void setAllAccessString(String allAccessString) {
		this.allAccessString = allAccessString;
	}
	/**
	 * @return the groupAccessString
	 */
	public String getGroupAccessString() {
		return groupAccessString;
	}

	private String userAccessString;
	
	/**
	 * @return the orgAccessString
	 */
	public String getOrgAccessString() {
		return orgAccessString;
	}
	/**
	 * @return the userAccessString
	 */
	public String getUserAccessString() {
		return userAccessString;
	}
	/**
	 * @return the loginClientId
	 */
	public Integer getLoginClientId() {
		return loginClientId;
	}
	/**
	 * @param loginClientId the loginClientId to set
	 */
	public void setLoginClientId(Integer loginClientId) {
		this.loginClientId = loginClientId;
	}
	/**
	 * @return the loginOrgId
	 */
	public Integer getLoginOrgId() {
		return loginOrgId;
	}
	/**
	 * @param loginOrgId the loginOrgId to set
	 */
	public void setLoginOrgId(Integer loginOrgId) {
		this.loginOrgId = loginOrgId;
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public String getLoginClientName() {
		return loginClientName;
	}
	public void setLoginClientName(String loginClientName) {
		this.loginClientName = loginClientName;
	}
	public String getLoginOrgName() {
		return loginOrgName;
	}
	public void setLoginOrgName(String loginOrgName) {
		this.loginOrgName = loginOrgName;
	}
	
	
	
	public Integer getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	
	
	
	
	public Integer getUserClientId() {
		return userClientId;
	}
	public void setUserClientId(Integer userClientId) {
		this.userClientId = userClientId;
	}
	public String getUserClientName() {
		return userClientName;
	}
	public void setUserClientName(String userClientName) {
		this.userClientName = userClientName;
	}
	public Integer getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Integer userOrgId) {
		this.userOrgId = userOrgId;
	}
	public String getUserOrgName() {
		return userOrgName;
	}
	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}
	/**
	 * 获取访问的数据权限的级别
	 * @param url 页面url
	 * @param userContent 当前登录对象 
	 * **/
	
	private Integer getAccessDataLevel(String url,UserContent userContent)
	{
		List<Page> pageList=userContent.PAGEPRIVILEGES.get(SystemConst.PRIVILEGES_VIEW);
			
			List<Integer>moduleIdList=new ArrayList<Integer>();
			for(Page page:pageList)
			{
				if(url.startsWith(page.getPageUrl()))
				{
					Map param=new HashMap();
					param.put("pageId", page.getId());
					List<ModulePage> mpList=modulePageService.selectByExample(param);
					if(mpList!=null)
					{
						for(ModulePage mp:mpList)
						{
							moduleIdList.add(mp.getModuleId());
						}
					}
					
				}
			}
			List<ModuleRole> moduleRoleList=new ArrayList<ModuleRole>();
			List<Role> roleList=user.getRoles();
			
			
			for(Role r:roleList)
			{
				for(Integer modId:moduleIdList)
				{
					Map param=new HashMap();
					param.put("moduleId", modId);
					param.put("roleId", r.getId());
					
					List<ModuleRole> mrList=this.moduleRoleService.selectByExample(param);
					if(mrList!=null&&mrList.size()>0)
					{
						moduleRoleList.addAll(mrList);
					}
					
				}
			}
			int access_level=4;
			for(ModuleRole mr:moduleRoleList)
			{
				int dataLevel=getDataLevel(mr.getDataFilterLevel());
				if(dataLevel<access_level)
				{
					access_level=dataLevel;
				}
			}
		
			
			return access_level;
		}		
	
	/**
	 * 
	 * 获取权限过滤访问字符串
	 * @param url 页面url
	 * @param userContent 当前登录对象
	 * @return
	 */
	private String getAccessString(String url,UserContent userContent)
	{
	
		int access_level=getAccessDataLevel(url,userContent);
		String accessString ="";
		switch(access_level)
		{
		case DelmarConst.ACCESS_LEVEL_ALL:
			accessString=this.allAccessString;
		break;
		case DelmarConst.ACCESS_LEVEL_ORG:
			accessString=this.getOrgAccessString();
		break;
		case DelmarConst.ACCESS_LEVEL_USERGROUP:
			accessString=this.getGroupAccessString();
		break;
		case DelmarConst.ACCESS_LEVEL_USER	:
			accessString=this.getUserAccessString();
			break;
			
		}
		
    	accessString=accessString+" and client_id="+this.loginClientId;
			
		return accessString;
	}
	/**
	 * @return
	 */
	public String getAccessStringByStruts2() {
		String url=ServletActionContext.getRequest().getServletPath();
	
		UserContent userContent=	(UserContent)ServletActionContext.getRequest().getSession().getAttribute(WebConst.SESSION_USERCONTENT);

		return this.getAccessString(url, userContent);
	}

	
	public Integer getAccessDataLevelByStruts2() {
		String url=ServletActionContext.getRequest().getServletPath();
	
		UserContent userContent=	(UserContent)ServletActionContext.getRequest().getSession().getAttribute(WebConst.SESSION_USERCONTENT);

		return this.getAccessDataLevel(url, userContent);
	}	
	
	//获取DWR中的
	public String getDWRAccessStringByStruts2() {
		String url=WebContextFactory.get().getHttpServletRequest().getServletPath();
	
		UserContent userContent=	(UserContent)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERCONTENT);

		return this.getAccessString(url, userContent);
	}

	
	public Integer getDWRAccessDataLevelByStruts2() {
		String url=WebContextFactory.get().getHttpServletRequest().getServletPath();
	
		UserContent userContent=	(UserContent)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERCONTENT);

		return this.getAccessDataLevel(url, userContent);
	}	
	
	
	private int getDataLevel(int dataFileter)
	{
			Datadict datadictAccess=null;
			
			for(Datadict dict:accessList)
			{
				if(dataFileter==dict.getId())
				{
					datadictAccess=dict;
					break;
				}
			}
			if(datadictAccess.getValue().equals("ACCESS_ALL"))
			{
				return 1;
			}
			if(datadictAccess.getValue().equals("ACCESS_ORG"))
			{
				return 2;
			}
			if(datadictAccess.getValue().equals("ACCESS_USERGROUP"))
			{
				return 3;
			}
			if(datadictAccess.getValue().equals("ACCESS_USER"))
			{
				return 4;
			}

		return 4;
	}
}

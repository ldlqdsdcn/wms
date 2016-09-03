/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;
             
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Datadict;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.bean.UserOnlineContent;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;
import com.delmar.sys.model.UserSession;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.ClientService;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UserSessionService;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.sys.service.UsergroupAccessService;
import com.delmar.sys.service.UserorgAccessService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.MD5;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

/**
 * @author 刘大磊 2015年1月13日 上午11:02:00
 * 用户登录
 */
public class UserLoginAction extends CoreAction {
	private String username;

	private String password;
	
	//本机的信息
    private String ip="";//获得本机IP
    private String address="";//获得本机名称	
	
	private User userInfo;
	@Resource(name="userService")
	private UserService userService;

	@Autowired
	private UserSessionService userSessionService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private UserorgAccessService userorgAccessService;	
	@Autowired
	private UsergroupAccessService usergroupAccessService;		
	@Autowired
	private UserThirdPartyService userThirdPartyService;	
	@Autowired
	private DatadictService datadictService;	
	
	/**
	 * @param userSessionService the userSessionService to set
	 */
	public void setUserSessionService(UserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}

	public String login()
	{
		User user=userService.getUserByUsername(username);
		if(user==null)
		{
			addActionMessage("该用户不存在！");
			return INPUT;
		}
		else
		{
			MD5 md5=new MD5();
			String md5password=md5.getMD5ofStr(password);
		
			if(md5password.equals(user.getPassword()))
			{
				if (user.getIsActive().intValue()==0)
				{
					addActionMessage("该用户已经被禁用！");
					
					userInfo=user;
					
					return "changep";
				}
				userInitCommon(user);
				userInit(user,false);
				return SUCCESS;
			}
			else
			{
				addActionMessage("密码输入错误！");
				return INPUT;
			}
		}

	}
	
	
	public String changePass()
	{
		User user=userService.getUserByUsername(username);
		if(user==null)
		{
			addActionMessage("该用户不存在！");
			return INPUT;
		}
		else
		{
			MD5 md5=new MD5();
			String md5password=md5.getMD5ofStr(password);
		
			if(md5password.equals(user.getPassword()))
			{
				if (user.getIsActive().intValue()==0)
				{
					addActionMessage("该用户已经被禁用！");
					return "changep";
				}
				userInit(user,false);
				return SUCCESS;
			}
			else
			{
				addActionMessage("密码输入错误！");
				return INPUT;
			}
		}

	}
	
	public String sublogin()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		HttpSession session=request.getSession();
		
		String sid=request.getParameter("sid");
		if (StringUtil.isEmpty(sid))
			return "relogin";
		
		if ("A".equals(sid))
		{
			User loginuser=(User)session.getAttribute(WebConst.SESSION_LOGINUSER);
			sid=loginuser.getId().toString();
		}
		
		if (StringUtil.isInteger(sid)==false)
			return "relogin";
		
		
		User user=userService.getUserById(new Integer(sid));
		if (user==null)
			return "relogin";
		
		
		
		userInit(user,true);
		return SUCCESS;	
		
	}
	
	private void userInitCommon(User user)
	{
		
		Cookie cookie=new Cookie("userName",user.getUsername());
		cookie.setMaxAge(60*60*24*7);
		
		ServletActionContext.getResponse().addCookie(cookie);

		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
	
		try
		{
		  InetAddress addr = InetAddress.getLocalHost();
		  ip=addr.getHostAddress().toString();//获得本机IP
		  address=addr.getHostName().toString();//获得本机名称
		  
		  session.setAttribute("localIpAddress", ip);
		  session.setAttribute("localHostName", address);
		  
		
		} catch (Exception ex)
		{
			
		}		
		

		
		
		
	}
	
	
	/** 
	 * @Title:        userInit 
	 * @Description:  TODO
	 * @param:        @param user
	 * @param:        @param beSub   是否是替代登陆
	 * @return:       void    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月7日 下午3:53:14 
	 */
	private void userInit(User user,boolean beSub) 
	{
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
	    /*Get user privileges*/
		UserContent userContent=userService.getUserPrivileges(user.getId());
		if (beSub==false)
		   session.setAttribute(WebConst.SESSION_LOGINUSER, user);
		
		session.setAttribute(WebConst.SESSION_ACTUALUSER, user);
		userContent.preSession=userSessionService.getPreLoginSession(user.getId());
		

		
		Date now=new Date();
		UserSession userSession=new UserSession();
		userSession.setCreated(now);
		userSession.setUpdated(now);
		userSession.setLoginDate(now);
		userSession.setUserId(user.getId());
		
		userSession.setUser(user);
		userSession.setSessionId(session.getId());
		userSession.setRemoteAddr(ip);
		userSession.setRemoteHost(address);
		Integer id=userSessionService.save(userSession);
		userSession.setId(id);
		userContent.userSession=userSession;
		/*Put user privileges into session*/
		session.setAttribute(WebConst.SESSION_USERCONTENT, userContent);
		//UserOnlineContent.USERMAP.put(userSession);
		/*Record user login session*/
		UserOnlineContent.addWebUser(userSession);

		
		//用户登陆信息
		PrivilegesDataFilter up=new PrivilegesDataFilter();
		//此处设置初始的Login的登陆
		up.setUserId(user.getId());
		up.setUser(user);
		if (beSub)
		{
			User loginuser=(User)session.getAttribute(WebConst.SESSION_LOGINUSER);
			up.setLoginUserId(loginuser.getId());
			up.setLoginUser(loginuser);
			
			if (loginuser.getId().intValue()==user.getId().intValue())  //和Login一样的
				session.setAttribute(WebConst.SESSION_SAMEUSER,"true");
			else
				session.setAttribute(WebConst.SESSION_SAMEUSER,"false");
		}
		else
		{
			up.setLoginUserId(user.getId());
			up.setLoginUser(user);
			session.setAttribute(WebConst.SESSION_SAMEUSER,"true");
		}
		
		up.setLoginClientId(user.getClientId());
		up.setUserClientId(user.getClientId());
   		Client client=clientService.selectByPrimaryKey(user.getClientId());
		user.setClient(client);
		up.setLoginClientName(client.getName());
		up.setUserClientName(client.getName());
		
		
		 up.setLoginOrgId(user.getOrgId());		
		 up.setUserOrgId(user.getOrgId());
		 Org org=orgService.selectByPrimaryKey(user.getOrgId());
		 user.setOrg(org);
		 up.setLoginOrgName(org.getName());
		 up.setUserOrgName(org.getName());


	 	up.build();
		
		//这里再选择出用户能够访问的组织
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("userId", user.getId());		
		List<UserorgAccess> orgAccessList=userorgAccessService.selectByExample(param);
		
		if (orgAccessList.size()==1)
		{
			//此处的目的是为了避免用户所属的组织和选择的一个组织不一致，所以以选择的为准。
			up.setLoginOrgId(orgAccessList.get(0).getOrgId());
		 }else if (orgAccessList.size()>1)
		 {
			session.setAttribute("orgMultiple", "true");
			up.setLoginOrgId(null);
		 }
			 
		
		
		session.setAttribute("userorgaccessList", orgAccessList);
		
		StringBuffer userOrgAccessBuf=new StringBuffer();
		List<ObjSelect> userOrgAccessList=new ArrayList<ObjSelect>();
		for (UserorgAccess userorgaccess:orgAccessList)
		{
			if (userOrgAccessBuf.length()==0)
				userOrgAccessBuf.append(userorgaccess.getOrgId());
			else
				userOrgAccessBuf.append(","+userorgaccess.getOrgId());		
			
			ObjSelect oneSelect=new ObjSelect();
			oneSelect.setId(userorgaccess.getOrgId());
			oneSelect.setValue(userorgaccess.getOrg().getValue());
			oneSelect.setName(userorgaccess.getOrg().getName());	
			
			userOrgAccessList.add(oneSelect);
		}
		
		session.setAttribute("userorgaccessListStr", userOrgAccessBuf.toString());		
		session.setAttribute("userOrgAccessSelectList", userOrgAccessList);
		
				
		
	
		List<Integer> groupUserIdList=usergroupAccessService.selectUserAccessUserId2(user.getId());
		
		session.setAttribute("groupUserIdList", groupUserIdList);
		
		StringBuffer userIdsAccessBuf=new StringBuffer();
		for (Integer oneUserId:groupUserIdList)
		{
			if (userIdsAccessBuf.length()==0)
				userIdsAccessBuf.append(oneUserId.intValue());
			else
				userIdsAccessBuf.append(","+oneUserId.intValue());		
		}
		
		if (userIdsAccessBuf.length()==0)
			userIdsAccessBuf.append(user.getId().intValue());
		
		session.setAttribute("usergroupaccessListStr", userIdsAccessBuf.toString());		
		
		

 		  
		session.setAttribute(WebConst.SESSION_USERPRIVILEGES, up);
		
		if(session.getAttribute("resource")==null)
		{
			com.delmar.core.web.bean.UserResource ur=new com.delmar.core.web.bean.UserResource( request.getLocale());
			session.setAttribute("resource", ur);
			session.setAttribute("WW_TRANS_I18N_LOCALE", request.getLocale());
			
		}
		
		//这里再选择出用户所关联的第三方账号
		Map<String,Object> paramThird=new HashMap<String,Object>();
		paramThird.put("sysuserid", user.getId());		
		List<UserThirdParty> thirdPartyList=userThirdPartyService.selectByExample(paramThird);
		//装换到HashMap
		Map<String,Object> thirdPartyMap=new HashMap<String,Object>();
		HashMap<String,String> thirdPartyUserMap=new HashMap<String,String>();
		for (UserThirdParty oneObj:thirdPartyList)
		{
			Datadict systemDataDict=datadictService.selectByPrimaryKey(oneObj.getSystemId());
			Datadict partyTypeDataDict=datadictService.selectByPrimaryKey(oneObj.getPartyTypeId());
			
			String key=systemDataDict.getValue()+partyTypeDataDict.getValue();
			List<UserThirdParty> valueList;
			if (thirdPartyMap.containsKey(key))
			{
				valueList=(List<UserThirdParty>)thirdPartyMap.get(key);
				valueList.add(oneObj);
				
				thirdPartyUserMap.put(key, thirdPartyUserMap.get(key) +","+oneObj.getPartyUser());
				
			}
			else
			{
				valueList=new ArrayList<UserThirdParty>();
				valueList.add(oneObj);
			    thirdPartyMap.put(key, valueList);
			    thirdPartyUserMap.put(key, oneObj.getPartyUser());
			}
		}

		session.setAttribute(WebConst.SESSION_THIRDPARTYUSER, thirdPartyMap);
		session.setAttribute(WebConst.SESSION_THIRDPARTYUSERMAP, thirdPartyUserMap);
	}
	
	
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	
}

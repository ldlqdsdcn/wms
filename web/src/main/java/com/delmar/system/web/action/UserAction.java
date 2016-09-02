/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserExtra;
import com.delmar.sys.model.UserSubstitute;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.model.Usergroup;
import com.delmar.sys.model.UsergroupAccess;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.ClientService;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserExtraService;
import com.delmar.sys.service.UserRoleService;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.sys.service.UsergroupAccessService;
import com.delmar.sys.service.UsergroupService;
import com.delmar.sys.service.UserorgAccessService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.dwr.UserDwr;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.MD5;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ProDirFileUtil;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月16日 下午5:27:52
 */
public class UserAction extends CoreEditPrivAction{
	@Autowired
	private UserService userService;
	private UserDwr userDwr;
	private String passwordConfirm;
	private String oldPassword;
	private String orgString;


	private String roleString;
	private List<Org> orgs;

	private List<Client> clients;
	
	private List<DatadictTrl> userTypeList;
	private List<UserSubstitute> userSubstituteList;
	
	private List<Usergroup> usergroupList;	
	
	
	private List<UsergroupAccess> userGroupaccessList;
	private List<UserorgAccess> userOrgaccessList;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private UserorgAccessService userorgAccessService;
	@Autowired
	private UsergroupService usergroupService;
	@Autowired
	private UsergroupAccessService usergourpAccessService;
	@Autowired
	private UserThirdPartyService userThirdPartyService;	
	@Autowired
	private UserExtraService userExtraService;
	@Autowired
	private DatadictService datadictService;	
	

	private User user=new User();
	
	private File userPic;
	private String userPicContentType;
	private String userPicFileName;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "用户信息");
	}
	
	
	@Override
	public String delete() {
		
		return this.list();
	}

	@Override
	public void deleteList(Integer[] ids)
	{
		if(ids!=null)
		{
			for(Integer id:ids)
			{
				userService.deleteByPrimaryKey(id);
			}
		}
	}
	/**
	 * 组织和实体是否修改
	 * 当用户选择用户组权限后，组织和实体不允许修改。
	 * @return
	 */
	public boolean isModifyClientOrg()
	{
		if(this.user.isnew())
		{
			return true;
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", user.getId());
		int count=usergourpAccessService.countObjects(param);
		if(count>0)
		{
			return false;
		}
		return true;
	}
	
	
	public String saveForm()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		if(userService.validateUsernameExist(user.getUsername(), user.getId()))
		{
			userInit();
			addActionMessage("该用户名已经存在，保存失败！");
			return EDIT;
		}
		
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);		
		if(user.isnew())
		{
			user.setCreated(new Date());
			user.setCreatedBy(up.getLoginUserId());
			user.setCreatedByName(up.getLoginUser().getName());
			user.setPassNextDate(ProDateUtil.getHisDate(-90));  //密码过期日期
		}
		
		user.setUpdated(new Date());
		user.setUpdatedBy(up.getLoginUserId());
		user.setUpdatedByName(up.getLoginUser().getName());
		
		if (user.getIsActive()==null)
			user.setIsActive(1);
		
		
				List<Integer> roles=new ArrayList<Integer>();
				String[] rolevalues=request.getParameterValues("roles") ;
				for(String value:rolevalues)
				{
					roles.add(new Integer(value));
				}
				boolean isnew=user.getId()==null?true:false;
				if(user.getPassword().length()<30)
				{
					MD5 md5=new MD5();
					user.setPassword(md5.getMD5ofStr(user.getPassword()));
					
					if (!isnew)   //判断密码是否做过改动，如果改动过，则需要修改密码更改日期 
					{
					   if (!oldPassword.equals(user.getPassword()))
  				       {
						   user.setPassNextDate(ProDateUtil.getHisDate(-90));  //密码过期日期						   
					    }
					}
				}
				String[] ids=request.getParameterValues("ids");
				
				String[] ugids=request.getParameterValues("ugids");
				
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("orgId", user.getOrgId());
			usergroupList=	this.usergroupService.selectByExample(param);
			
			List<UserorgAccess> saveList=new ArrayList<UserorgAccess>();
			
			//对User当中的图片进行处理
			
			if ("image/pjpeg".equals(userPicContentType)
				||("image/gif").equals(userPicContentType)
				||("image/jpeg").equals(userPicContentType)
				||("image/png").equals(userPicContentType))
			{
				String newFileName="user"+user.getId().toString()+userPicFileName.substring(userPicFileName.lastIndexOf("."));
				//在服务器创建文件
				String realpath=ServletActionContext.getServletContext().getRealPath("/userPic");
				File destFile=new File(realpath,newFileName);
				if (!destFile.getParentFile().exists())
					destFile.getParentFile().mkdir();
				
				try{
					InputStream inStream=new FileInputStream(userPic);
					OutputStream outStream=new FileOutputStream(destFile);
					ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);					
					byte[] data=new byte[1024];
					int len=0;
					while ((len=inStream.read(data))!=-1)
					{
						outStream.write(data,0,len);
						bos.write(data,0,len);
					}
					
					
					
					
					outStream.flush();
					outStream.close();
					inStream.close();
					bos.close();
					
					user.setUserPic(bos.toByteArray());
					
					
							
					
					
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			

				
				id=this.userService.saveUser(user, roles,ids,ugids);
				if(isnew)
				{
					this.getIdList().add(id);
					addActionMessage("添加用户信息成功！");
				}
				else
				{
					addActionMessage("修改用户信息成功！");
				}
	
		return edit();
	}
	@Override
	public void createForm() {
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");		
		user=new User();
		Integer[] ids=null;
		
		Integer[] ids2=null;
		clients=clientService.selectByExample(null);			
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("sysClientId", clients.get(0).getId());
		this.orgs=orgService.selectByExample(param);
		param=new HashMap<String,Object>();
		param.put("orgId", orgs.get(0).getId());
		usergroupList=this.usergroupService.selectByExample(param);
		ids=new Integer[]{orgs.get(0).getId()};
		user.setOrgId(orgs.get(0).getId());
		
		//Integer[] orgIds,Integer[] roleIds,Integer roleCount
		this.roleString=userDwr.getRoles(ids, ids2, 5);
		passwordConfirm=user.getPassword();
		oldPassword=user.getPassword();
		
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		userTypeList=datadictService.getDatadictTrlByValue(DatadictType.USER_USERTYPE,ur.getLocale().toString());		
		
		

		
		
	}
	@Override
	public void editForm() {
		user=userService.getUserById(id);
		
		userSubstituteList=userService.getUserSubstituteById(id);
		


		
		
		
		//Check whether userpic existed or not 
		String newFileName="user"+user.getId().toString()+".png";
		String realpath=ServletActionContext.getServletContext().getRealPath("/userPic");
		String fileName=realpath+"/"+newFileName;
		File file2 = new File(fileName);
		if (!file2.getParentFile().exists())
			file2.getParentFile().mkdir();
		
		//判断文件是否存在并且是一个文件
		if (!(file2.exists() && file2.isFile())){	
			ProDirFileUtil.getFile(user.getUserPic(), realpath, newFileName);
		}
		
		userInit();
	}
	private void userInit()
	{
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		
		Integer[] ids=null;
		
		Integer[] ids2=null;
		List<Role> roles=user.getRoles();
		if(roles==null||roles.size()==0)
		{
			
			ids2=null;
		}
		else
		{
			ids2=new Integer[roles.size()];
			for(int i=0;i<roles.size();i++)
			{
				ids2[i]=roles.get(i).getId();
			}
			
		}
		//获取实体列表
		this.clients=this.clientService.selectByExample(null);
		Map<String,Object>param1=new HashMap<String,Object>();
		param1.put("sysClientId", user.getClientId());
		
		//获取组织列表
		this.orgs=orgService.selectByExample(param1);
		param1=new HashMap<String,Object>();
		param1.put("orgId", user.getOrgId());
	
		
		if(user.getOrgId()==null||user.getOrgId()==0)
		{
			ids=new Integer[]{orgs.get(0).getId()};
		}
		else
		{
			ids=new Integer[]{user.getOrgId()};
		}
		//可访问组织列表
		this.userOrgaccessList=new ArrayList<UserorgAccess>();
		for(Org org:this.orgs)
		{
			UserorgAccess uoa=new UserorgAccess();
			uoa.setOrg(org);
			uoa.setOrgId(org.getId());
			userOrgaccessList.add(uoa);
		}
		
		
		//得到System List
		userTypeList=datadictService.getDatadictTrlByValue(DatadictType.USER_USERTYPE,ur.getLocale().toString());
		
		/**
		 * 用户可访问用户组
		 */
		userGroupaccessList=new ArrayList<UsergroupAccess>();
		
		
		//可访问用户组
		usergroupList=new ArrayList<Usergroup>();
		
	
			
		if(id!=null&&id!=0)
		{
			//设置可访问组织
			Map param=new HashMap();
			param.put("accessString", " user_id="+id);
			List<UserorgAccess> userorgaccessList=userorgAccessService.selectByExample(param);
			for(UserorgAccess uoa:userOrgaccessList)
			{
				for(UserorgAccess u:userorgaccessList)
				{
					if(uoa.getOrgId().equals(u.getOrgId()))
					{
						uoa.setId(u.getId());
						uoa.setUserId(u.getUserId());
					}
				}
				Map groupMap=new HashMap();
				groupMap.put("orgId", uoa.getOrgId());
				List<Usergroup> orgUsergroupList=usergroupService.selectByExample(groupMap);
				
				usergroupList.addAll(orgUsergroupList);
			}
			
			for(Usergroup ug:usergroupList)
			{
				UsergroupAccess ua=new UsergroupAccess();
				ua.setUsergroupId(ug.getId());
				ua.setUserId(id);
				ua.setUsergroup(ug);
				this.userGroupaccessList.add(ua);
			}
			
			
	
			
			param.put("accessString", "  user_id="+id);
			//设置可访问用户组
			List<UsergroupAccess> usergroupaccessList=this.usergourpAccessService.selectByExample(param);
			for(UsergroupAccess uoa:this.userGroupaccessList)
			{
				for(UsergroupAccess u:usergroupaccessList)
				{
					if(uoa.getUsergroupId().equals(u.getUsergroupId()))
					{
						uoa.setId(u.getId());
						uoa.setUserId(u.getUserId());
					}
				}
			}
			
			
			
		    
			
			
			
			
			
		}
		
		this.roleString=userDwr.getRoles2(ids, ids2, 5);
		passwordConfirm=user.getPassword();
		

		
		
	}
	@Override
	public Integer getModelId() {
		
		return user.getId();
	}

	@Override
	public String getModuleName() {
		
		return "user";
	}

	@Override
	public List search() {
		Map<String,Object> param=new HashMap();
		//统一配置查询界面
		//Charels 隐藏掉
		
/*		SearchColumnList searchColumnList=(SearchColumnList)FacesUtils.getValueInHashtableOfSession("searchColumnList");
		if(searchColumnList!=null)
		{
			param.put("accessString", searchColumnList.buildSql());
		}*/
		
		StringBuilder sb=new StringBuilder();
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String invalid=request.getParameter("invalid");
		FacesUtils.setValueInHashtableOfSession("invalid", invalid);
		if (StringUtil.isEmpty(invalid))  //是否包含作废的
			FacesUtils.setValueInHashtableOfSession("invalid", "N");
		
		if (StringUtil.isEmpty(invalid))  //是否包含作废的
		{
			sb.append(" and IsActive=1 ");	
		}
		
         String name=request.getParameter("name");
         FacesUtils.setValueInHashtableOfSession("name", name);		
		if (StringUtil.isNotEmpty(name))  //是否包含作废的
		{
			sb.append(" and name like '%").append(StringUtil.fullYhStr(name)).append("%' ");	
		}	
		
		if (sb.length()>0)
			param.put("accessString", sb.toString());
		
		
		
		return userService.getUsers(param);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserDwr(UserDwr userDwr) {
		this.userDwr = userDwr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @return the orgString
	 */
	public String getOrgString() {
		return orgString;
	}

	/**
	 * @param orgString the orgString to set
	 */
	public void setOrgString(String orgString) {
		this.orgString = orgString;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	/**
	 * @return the orgs
	 */
	public List<Org> getOrgs() {
		return orgs;
	}

	/**
	 * @param orgs the orgs to set
	 */
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}
	/**
	 * @return the userOrgaccessList
	 */
	public List<UserorgAccess> getUserOrgaccessList() {
		return userOrgaccessList;
	}

	/**
	 * @param userOrgaccessList the userOrgaccessList to set
	 */
	public void setUserOrgaccessList(List<UserorgAccess> userOrgaccessList) {
		this.userOrgaccessList = userOrgaccessList;
	}

	/**
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients the clients to set
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	/**
	 * @return the usergroupList
	 */
	public List<Usergroup> getUsergroupList() {
		return usergroupList;
	}

	/**
	 * @param usergroupList the usergroupList to set
	 */
	public void setUsergroupList(List<Usergroup> usergroupList) {
		this.usergroupList = usergroupList;
	}

	/**
	 * @return the userGroupaccessList
	 */
	public List<UsergroupAccess> getUserGroupaccessList() {
		return userGroupaccessList;
	}

	/**
	 * @param userGroupaccessList the userGroupaccessList to set
	 */
	public void setUserGroupaccessList(List<UsergroupAccess> userGroupaccessList) {
		this.userGroupaccessList = userGroupaccessList;
	}


	public File getUserPic() {
		return userPic;
	}

	public void setUserPic(File userPic) {
		this.userPic = userPic;
	}

	public String getUserPicContentType() {
		return userPicContentType;
	}

	public void setUserPicContentType(String userPicContentType) {
		this.userPicContentType = userPicContentType;
	}

	public String getUserPicFileName() {
		return userPicFileName;
	}

	public void setUserPicFileName(String userPicFileName) {
		this.userPicFileName = userPicFileName;
	}

	public List<DatadictTrl> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<DatadictTrl> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public List<UserSubstitute> getUserSubstituteList() {
		return userSubstituteList;
	}

	public void setUserSubstituteList(List<UserSubstitute> userSubstituteList) {
		this.userSubstituteList = userSubstituteList;
	}




	
}

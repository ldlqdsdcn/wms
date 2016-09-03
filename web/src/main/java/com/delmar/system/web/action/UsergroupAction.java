/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.model.Usergroup;
import com.delmar.sys.model.UsergroupAccess;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UsergroupAccessService;
import com.delmar.sys.service.UsergroupService;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月20日 下午4:17:07
 */
public class UsergroupAction extends CoreEditPrivAction {
	private Usergroup usergroup;
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserService userService;
	@Autowired
	private UsergroupAccessService usergroupAccessService;
	private List<Org> orgList;
	
	@Autowired
	private UsergroupService usergroupService;
	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "用户组信息");
	}
	public void saveUsergroupUser()
	{
		if((PrivilegeOperator.isUpdate()))
		{
		

		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   		
		try{
		String usergroupId=request.getParameter("usergroupId");
		String userIds=request.getParameter("userIds");		
		String[] userIdArray=userIds.split(",");		
		for (String oneUserId:userIdArray)
		{
			UsergroupAccess usergroupAccess=new UsergroupAccess();
			usergroupAccess.setUserId(new Integer(oneUserId));
			usergroupAccess.setUsergroupId(Integer.parseInt(usergroupId));
			usergroupAccessService.save(usergroupAccess);
		}
		  response.getWriter().write("Success");	
		} catch (Exception ex)
		{
            try
            {
			response.getWriter().write("Failure");
            }
            catch (Exception exex)
            {}
			
		}
		}
	}
	public void delUsergroupUser()
	{
		if((PrivilegeOperator.isUpdate()))
		{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   		
		try{
		
		String roleId=request.getParameter("usergroupId");
		String userId=request.getParameter("userId");
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", new Integer(userId));
		param.put("usergroupId", new Integer(roleId));

		usergroupAccessService.deleteByExample(param);
		response.getWriter().write("Success");	
		} catch (Exception ex)
		{
            try
            {
			response.getWriter().write("Failure");
            }
            catch (Exception exex)
            {}
			
		}
		}
		
	}
	
	
	
	public String addUserGroupUser()
	{
		if(!(!PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		HttpServletRequest request=ServletActionContext.getRequest();
        String roleId=request.getParameter("usergroupId");
        String orgId=request.getParameter("orgId");
        List<User> nonUserList=userService.getNonUserOrgByUsergroupId(new Integer(roleId),new Integer(orgId));
        
		FacesUtils.setValueInHashtableOfSession("nonUserList", nonUserList);        
		return "adduser";
	}
	private void init()
	{
		orgList=orgService.selectByExample(null);
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "userGroup";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		usergroupService.deleteByPrimaryKey(usergroup.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		usergroupService.deleteUsergroups(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return usergroup.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		usergroup=usergroupService.selectByPrimaryKey(id);
		
		
		
		init();
		List<User> rmUserList=usergroupAccessService.getUserUsergroupId(id);
		FacesUtils.setValueInHashtableOfSession("rmUserList", rmUserList);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return usergroupService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		usergroup=new Usergroup();
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		if(StringUtil.isEmpty(usergroup.getName()))
		{
			addActionMessage("用户组名称不允许为空");
			init();
			return "edit";
		}
		usergroupService.save(usergroup);
		this.id=usergroup.getId();
		edit();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Usergroup getUsergroup() {
		return usergroup;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}
	/**
	 * @return the orgList
	 */
	public List<Org> getOrgList() {
		return orgList;
	}

	/**
	 * @param orgList the orgList to set
	 */
	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}
}

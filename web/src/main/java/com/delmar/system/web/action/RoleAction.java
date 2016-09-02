/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.model.CoreModel;
import com.delmar.core.web.action.CoreEditAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Operator;
import com.delmar.sys.model.Privilege;
import com.delmar.sys.model.Role;
import com.delmar.sys.model.RoleModuleContent;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserRole;
import com.delmar.sys.service.RoleService;
import com.delmar.sys.service.UserRoleService;
import com.delmar.sys.service.UserService;

/**
 * @author 刘大磊 2015年1月15日 上午11:16:10
 */
public class RoleAction extends CoreEditAction {

	private RoleService roleService;
	private Role role;
	@Autowired
	private DatadictTypeService datadictTypeService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	
	private List<DatadictTrl> datadictList;
	
	private List<Privilege> priList=new ArrayList<Privilege>();
	private List<RoleModuleContent> rmContentList;

	
	public List<Privilege> getPriList() {
		return priList;
	}
	
	private void init()
	{
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		
		datadictList=datadictService.getDatadictTrlByValue(DatadictType.ACCESS_LEVEL, ur.getLocale().toString());
		
	}
	
	public void setPriList(List<Privilege> priList) {
		this.priList = priList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleAction() {
	 role=new Role();
	}
	
	@Override
	public void editForm() {
		
		init();
		if(id!=null&&id!=0)
		{

			role=roleService.selectByPrimaryKey(id);
		}
		else
		{
			role=new Role();

		}
		priList=roleService.getPrivileges(id);
		rmContentList=roleService.getRoleModuleContent(id);
		FacesUtils.setValueInHashtableOfSession("rmContentList", rmContentList);
		try
		{
		FacesUtils.setValueInHashtableOfSession("privilegesString", getPrivilegesString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//得到对应的用户
		
		List<User> rmUserList=userService.getUserOrgByRoleId(id);
		FacesUtils.setValueInHashtableOfSession("rmUserList", rmUserList);

		

	}
	
	private String getPrivilegesString()
	{
		int count=0;
		StringBuilder sb=new StringBuilder("");
		for(RoleModuleContent rmContent:rmContentList)
		{
			List<Operator> operatorList=rmContent.getOperatorList();
			List<Integer> opearIds=rmContent.getOperIds();
			if(opearIds==null||opearIds.size()==0)
			{
				continue;
			}
			count ++;
			sb.append("<tr class='");
			if(count%2==0)
			{
				sb.append("odd");
			}
			else
			{
				sb.append("even");
			}
			sb.append("'>");
			
			sb.append("<td>");
			
			sb.append(rmContent.getModule().getName());
			
			sb.append("</td>");
			
			sb.append("<td>");

			if(operatorList!=null&&opearIds!=null)
			for(Operator opea:operatorList)
			{
				
				for(Integer poerId:opearIds)
				{
					if(poerId.equals(opea.getId()))
					{
						sb.append(opea.getName());
						sb.append("&nbsp;&nbsp;");
					}
				}
	
			}
			sb.append("</td>");
			sb.append("<td>");
			Integer accessLevel=rmContent.getAccessLevel();
			if(accessLevel!=null)
			{
				for(DatadictTrl datadict:datadictList)
				{
					if(datadict.getDatadictId()==accessLevel.intValue())
					{
						sb.append(datadict.getName());
						break;
					}
				}
			}

			
			sb.append("</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	
	public String save()
	{
		boolean isnew=role.getId()==null?true:false;
		//id=roleService.saveRole(role, priList);
		List<RoleModuleContent> rmContentListSession=(List<RoleModuleContent>)FacesUtils.getValueInHashtableOfSession("rmContentList");
		for(int i=0;i<rmContentListSession.size();i++ )
		{
			RoleModuleContent rmc=rmContentList.get(i);
			if(rmc==null)
			{
				rmContentListSession.get(i).setOperIds(new ArrayList<Integer>());
			}
			else
			{
				rmContentListSession.get(i).setOperIds(rmc.getOperIds());	
			}
			rmContentListSession.get(i).setAccessLevel(rmc.getAccessLevel());
			
		}
		id=roleService.saveRoleContent(role, rmContentListSession);
		if(isnew)
		{
			getIdList().add(id);
			addActionMessage("添加角色成功");
		}
		else
		{
			addActionMessage("修改角色成功");
		}
		return edit();
	}
	
	
	public String addRoleUser()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		HttpServletRequest request=ServletActionContext.getRequest();
        String roleId=request.getParameter("roleId");
        
        List<User> nonUserList=userService.getNonUserOrgByRoleId(new Integer(roleId));
        
		FacesUtils.setValueInHashtableOfSession("nonUserList", nonUserList);        
		return "adduser";
	}
	
	
	public void saveRoleUser()
	{
		if((PrivilegeOperator.isUpdate()))
		{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   		
		try{
		String roleId=request.getParameter("roleId");
		String userIds=request.getParameter("userIds");		
		String[] userIdArray=userIds.split(",");		
		for (String oneUserId:userIdArray)
		{
		   UserRole oneuserrole=new UserRole();
		   oneuserrole.setRoleId(new Integer(roleId));
		   oneuserrole.setUserId(new Integer(oneUserId));
		   userRoleService.save(oneuserrole);
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
	
	
	
	public void delRoleUser()
	{
		if((PrivilegeOperator.isUpdate()))
		{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   		
		try{
		
		String roleId=request.getParameter("roleId");
		String userId=request.getParameter("userId");
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", new Integer(userId));
		param.put("roleId", new Integer(roleId));

 		userRoleService.deleteByExample(param);

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
	
	
	@Override
	public List<CoreModel> search() {
		List list=roleService.selectByExample(null);
		return list;
		
	}

	@Override
	public String getModuleName() {
		
		return "role";
	}

	@Override
	public void deleteList(Integer[] ids) {
		for(Integer id:ids)
		roleService.deleteByPrimaryKey(id);
	}

	

	@Override
	public Integer getModelId() {
		
		return role.getId();
	}

	@Override
	public String delete() {
		roleService.deleteByPrimaryKey(role.getId());
		
		return "list";
	}

	public List<RoleModuleContent> getRmContentList() {
		return rmContentList;
	}

	public void setRmContentList(List<RoleModuleContent> rmContentList) {
		this.rmContentList = rmContentList;
	}

	/**
	 * @return the datadictList
	 */
	public List<DatadictTrl> getDatadictList() {
		return datadictList;
	}

	/**
	 * @param datadictList the datadictList to set
	 */
	public void setDatadictList(List<DatadictTrl> datadictList) {
		this.datadictList = datadictList;
	}







}

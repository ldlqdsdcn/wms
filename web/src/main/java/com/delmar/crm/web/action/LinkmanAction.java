/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.crm.web.action;

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
import com.delmar.core.DelmarConst;
import com.delmar.core.service.CoreCreatCodeService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.web.model.CreatCodeConst;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

/**
 * @author 刘大磊 2015年3月10日 下午2:43:59
 */
public class LinkmanAction extends CoreEditPrivAction {
	private Linkman linkman;
	
	private List<DatadictTrl> callList;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表	
	@Autowired
	private UserService userService;
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private CustomerService customerService;	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private CoreCreatCodeService coreCreatCodeService;
	

	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CRM联系人记录");
	}
	
	@Override
	public String list() {
		
		//判断用户有没有权限
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","group");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());
			FacesUtils.setValueInHashtableOfSession("beGroup","false");
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		
		
		
		return super.list();
	}
	
			
	private void init()
	{
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);		
		callList=datadictService.getDatadictTrlByValue(DatadictType.CALL,ur.getLocale().toString(),up.getLoginClientId());

		
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		
		return "linkman";
	}

	@Override
	public boolean validateDelete(Integer id)
	{
		Linkman linkmanObj=linkmanService.selectByPrimaryKey(id);
		if (linkmanObj.getIsMain().equals("Y"))
		{
			FacesUtils.setValueInHashtableOfSession("errorMessage",getText("linkman.operate.candelete.major"));			
			return false;
		}
		else
			return true;
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		if (validateDelete(linkman.getId())==false)
		{
			  FacesUtils.setValueInHashtableOfSession("errorMessage",getText("linkman.operate.candelete.major"));
			  return "error";
		}
		
		linkmanService.deleteById(linkman.getId());
		return this.list();
	}
	

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		linkmanService.deleteLinkmanList(ids);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String starLevel=request.getParameter("starLevel");
		FacesUtils.setValueInHashtableOfSession("starLevel", starLevel);
		String cuscode=request.getParameter("cuscode");
		FacesUtils.setValueInHashtableOfSession("cuscode", cuscode);
		String name=request.getParameter("name");
		FacesUtils.setValueInHashtableOfSession("name", name);
		String orgId=request.getParameter("orgId");
		FacesUtils.setValueInHashtableOfSession("orgId",orgId);
		String org=request.getParameter("org");
		FacesUtils.setValueInHashtableOfSession("org",org);
		return linkman.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		linkman=linkmanService.selectByPrimaryKey(id);
		FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkman_list.action"));
		
		if(this.linkman!=null)
		{
	
			Org org=orgService.selectByPrimaryKey(linkman.getOrgId());
			linkman.setOrg(org);
			
			User user=userService.selectByPrimaryKey(linkman.getUserId());
			linkman.setUser(user);		
			
			User createdby=userService.selectByPrimaryKey(linkman.getCreatedBy());
			linkman.setCreatedByUser(createdby);
			User updateby=userService.selectByPrimaryKey(linkman.getUpdatedBy());
			linkman.setUpdatedByUser(updateby);
			
			Customer customer=customerService.selectByPrimaryKey(linkman.getCustomerId());
			linkman.setCustomer(customer);
			
		}		
		init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		init();
		
		//判断用户有没有权限
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","group");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());
		
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String customerId=request.getParameter("customerId");
		FacesUtils.setValueInHashtableOfSession("customerId", customerId);
		String customerName=request.getParameter("customerName");
		FacesUtils.setValueInHashtableOfSession("customerName", customerName);
		
		String linkCode=request.getParameter("linkCode");
		FacesUtils.setValueInHashtableOfSession("linkCode", linkCode);
		String linkManName=request.getParameter("linkManName");
		FacesUtils.setValueInHashtableOfSession("linkManName", linkManName);
		
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	

		
		String visitDateBegin=request.getParameter("visitDateBegin");
		FacesUtils.setValueInHashtableOfSession("visitDateBegin", visitDateBegin);	

		
		String visitDateEnd=request.getParameter("visitDateEnd");
		FacesUtils.setValueInHashtableOfSession("visitDateEnd",visitDateEnd);	
		
		
		String birthDayBegin=request.getParameter("birthDayBegin");
		FacesUtils.setValueInHashtableOfSession("birthDayBegin", birthDayBegin);	

		
		String birthDayEnd=request.getParameter("birthDayEnd");
		FacesUtils.setValueInHashtableOfSession("birthDayEnd",birthDayEnd);	
		
		String overDays=request.getParameter("overDays");
		FacesUtils.setValueInHashtableOfSession("overDays",overDays);		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);		
		
		
		
		StringBuffer sb=new StringBuffer("");
		//权限过滤代码
		Map<String,Object> param=new HashMap<String,Object>();
		
		if(StringUtil.isNotEmpty(customerId))
		{
			sb.append("and customer_id="+customerId);			
		}
		else
		{
			if(StringUtil.isNotEmpty(customerName))
			{
				sb.append("and customer_id in (select id from b_crm_customer where name like '%"+customerName.trim()+"%' )");
			}
		}
		if(StringUtil.isNotEmpty(linkCode))
		{
			sb.append("and linkcode like '%").append(StringUtil.fullYhStr(linkCode.trim())).append("%' ");
		}
		if(StringUtil.isNotEmpty(linkManName))
		{
			sb.append("and name like '%").append(StringUtil.fullYhStr(linkManName.trim())).append("%' ");
		}
		
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
		}
		
		if(StringUtil.isNotEmpty(visitDateBegin))
		{
			sb.append("and lastLinkTime>='").append(visitDateBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(visitDateEnd))
		{
			sb.append("and lastLinkTime<='").append(visitDateEnd.trim()).append("' ");
		}
		
		if(StringUtil.isNotEmpty(birthDayBegin))
		{
			sb.append("and birthDay>='").append(birthDayBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(birthDayEnd))
		{
			sb.append("and birthDay<='").append(birthDayEnd.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(overDays))
		{
			sb.append("and lastLinkTime<='").append(ProDateUtil.getLongHisDateStr(Integer.parseInt(visitDateEnd.trim()))).append("' ");
		}
		
		if(StringUtil.isNotEmpty(userName))
		{
			sb.append(" and user_id in (Select id from sys_user where  ((name in (").append(StringUtil.convertToSqlParam(userName)).append(")) or (username in (").append(StringUtil.convertToSqlParam(userName)).append(")))) ");
		}		
		
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);		
		
	       if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
			{
		        if (StringUtil.isNotEmpty(orgIds))
		        {
		        	sb.append("and org_id in (").append(orgIds.trim()).append(") ");
		        }  		
			}
			else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
			{
		        if (StringUtil.isNotEmpty(orgIds))
		        {
		        	sb.append("and org_id in (").append(orgIds.trim()).append(") ");
		        }  		
				
			}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
			{
				if(beGroup.equals("false"))
				{
					sb.append("and user_id=").append(up.getUserId()).append(" ");
				}		
				
			}
			else
			{
				
			}
		
		

		if (StringUtil.isNotEmpty(sb.toString()))
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");
			param.put("topnumber", " top 20 ");
		}		
		
		logger.debug(up.getAccessStringByStruts2());
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		
		param.put("accessString", accessString);
		param.put("orderByClause", " lastLinkTime desc");		
		return linkmanService.selectByExample(param);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		
		init();
		
		Customer currentCustom=null;
		if (linkman!=null)
		{
		   if (linkman.getCustomerId()!=null)
			   currentCustom=customerService.selectByPrimaryKey(linkman.getCustomerId());
		}
		
		this.linkman=new Linkman();
		
		linkman.setLinkCode("L"+coreCreatCodeService.getMaxValue(CreatCodeConst.LINKMAN_LINKCODE, up.getLoginClientId()).toString());
		Date now=new Date();
		if (currentCustom!=null)
		{
		   linkman.setCustomerId(currentCustom.getId());
		   linkman.setCustomer(currentCustom);
		}
		linkman.setIsMain("N");
		linkman.setUser(up.getUser());
		linkman.setUserId(up.getUserId());
		linkman.setUserName(up.getUser().getName());		
		linkman.setCreatedBy(up.getLoginUserId());
		linkman.setCreatedByName(up.getLoginUser().getName());
		linkman.setCreatedByUser(up.getLoginUser());
		linkman.setUpdatedBy(up.getLoginUserId());
		linkman.setUpdatedByName(up.getLoginUser().getName());
		linkman.setUpdatedByUser(up.getLoginUser());
		linkman.setCreated(now);
		linkman.setUpdated(now);
		linkman.setClientId(up.getLoginClientId());
		linkman.setOrgId(up.getLoginOrgId());
		linkman.setClient(new Client(up.getLoginClientId(),up.getLoginClientName()));		
		linkman.setOrg(new Org(up.getLoginOrgId(),up.getLoginOrgName()));		
		
		
		linkman.setFlag(0);
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String from=request.getParameter("from");
		String fromid=request.getParameter("fromid");
		
		if ("customer".equals(from))  //说明是从客户档案处过来的
		{
			Customer customer=customerService.selectByPrimaryKey(new Integer(fromid));
			linkman.setCustomerId(customer.getId());
			linkman.setCustomer(customer);
			
		    FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/customer_list.action"));			
		}
		else
		    FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkman_list.action"));			
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		Date now=new Date();
		 up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		if(linkman.isnew())
		{
			linkman.setCreated(now);
			linkman.setCreatedBy(up.getLoginUserId());
			linkman.setCreatedByName(up.getLoginUser().getName());			
			linkman.setUserId(up.getUserId());
			linkman.setUserName(up.getUser().getName());
			linkman.setOrgId(up.getLoginOrgId());
			linkman.setClientId(up.getLoginClientId());
			linkman.setFlag(0);
		}
		linkman.setUpdated(now);
		linkman.setUpdatedBy(up.getLoginUserId());
		linkman.setUpdatedByName(up.getLoginUser().getName());
			
		
		//此处需要更新其他联系人的主联系人状态
		//System.out.println(linkman.getIsMain()+"/AAAA");
		if (linkman.getIsMain().equals("Y"))
		{
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("ismain", "Y");
			param.put("customerId", linkman.getCustomerId());	
			Linkman majorlinkman=linkmanService.getByExample(param);
			//主联系人肯定会有的，所以不用判断是否为空
			//出现问题的时候，主联系人就是没有的
			if (majorlinkman!=null)
			{
			if (linkman.getId()!=majorlinkman.getId())
			{
				//得到之前的主联系人，目的是为了得到他的ID, 为后面的更新做准备
				majorlinkman.setIsMain("N");
				linkmanService.update(majorlinkman);			
			}
			}
		} 
		
		linkmanService.save(linkman);
		this.id=linkman.getId();
		
		edit();
		return "edit";
	}

	/**
	 * @return the linkman
	 */
	public Linkman getLinkman() {
		return linkman;
	}

	/**
	 * @param linkman the linkman to set
	 */
	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}
	/**
	 * @return the callList
	 */
	public List<DatadictTrl> getCallList() {
		return callList;
	}
	/**
	 * @param callList the callList to set
	 */
	public void setCallList(List<DatadictTrl> callList) {
		this.callList = callList;
	}


	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}


	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

}
